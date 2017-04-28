package routine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import routine.holder.RoomHolder;
import routine.holder.SlotHolder;
import routine.holder.SubjectHolder;
import routine.holder.TeacherHolder;
import routine.model.DayTimeSlot;
import routine.model.Room;
import routine.model.Subject;
import routine.model.Teacher;

/**
 * Created by Palash_Das on 24-04-2017.
 */

public class SimpleSolver {
	
	private final SlotHolder slotHolder;
	private final SubjectHolder subjectHolder;
	private final RoomHolder roomHolder;
	private final TeacherHolder teacherHolder;
	private Set<RoutineCell> routineCells;
	
	public class RoutineCell {
		DayTimeSlot combinedSlot;
		List<DayTimeSlot> slots;
		Subject subject;
		Room room;
		Teacher teacher;
	}
	
	public SimpleSolver(SlotHolder slotHolder, SubjectHolder subjectHolder, RoomHolder roomHolder, TeacherHolder teacherHolder) {
		this.slotHolder = slotHolder;
		this.subjectHolder = subjectHolder;
		this.roomHolder = roomHolder;
		this.teacherHolder = teacherHolder;
	}
	
	public Set<RoutineCell> evaluateSolution(int noOfRounds) {
		routineCells = new HashSet<>();
		while (noOfRounds-- > 0) {
			if (!processOneRound(noOfRounds)) {
				continue;
			}
			if (subjectHolder.totalRemainingMinutes() <= 0) {
				return routineCells;
			}
		}
		return null;
	}
	
	private boolean processOneRound(int roundNumber) {
		int[][] strategies = {
				{1, 3},
				{2, 2},
				{1, 2, 1},
				{1, 1, 2},
				{2, 1, 1},
				{3},
				{2},
				{1}
		};
//		Random random = new Random();
//		return strategy(strategies[random.nextInt(strategies.length)]);
		return strategy(strategies[roundNumber % strategies.length]);
	}
	
	private boolean strategy(int... periods) {
		int totalPeriods = Arrays.stream(periods).reduce(Integer::sum).getAsInt();
		List<DayTimeSlot> totalSlots = slotHolder.popConsecutiveSlots(totalPeriods);
		if (totalSlots == null) {
			return false;
		}
		
		List<RoutineCell> finalCells = new ArrayList<>();
		
		int startIndex = 0;
		for (int period : periods) {
			List<DayTimeSlot> subSlots = totalSlots.subList(startIndex, startIndex + period);
			RoutineCell cell = arrangeResources(subSlots);
			if (cell == null) {
				slotHolder.putBackSlots(totalSlots);
				return false;
			}
			finalCells.add(cell);
			startIndex += period;
		}
		
		if (finalCells.stream()
				.map(cell -> cell.subject.toString())
				.distinct()
				.count() != finalCells.size()
				) {
			slotHolder.putBackSlots(totalSlots);
			return false;
		}
		
		finalCells.forEach(this::allocateResources);
		return true;
	}
	
	private RoutineCell arrangeResources(List<DayTimeSlot> slots) {
		DayTimeSlot turboSlot = DayTimeSlot.compose(slots);
		
		Subject subject = subjectHolder.getSubjectSuitableFor(turboSlot);
		if (subject == null) {
			return null;
		}
		
		Teacher teacher = teacherHolder.getSuitableTeacherFor(subject, turboSlot);
		if (teacher == null) {
			return null;
		}
		
		Room room = roomHolder.getFreeRoomDuring(subject, turboSlot);
		if (room == null) {
			return null;
		}
		
		RoutineCell cell = new RoutineCell();
		
		cell.combinedSlot = turboSlot;
		cell.slots = slots;
		cell.subject = subject;
		cell.teacher = teacher;
		cell.room = room;
		
		return cell;
	}
	
	private void allocateResources(RoutineCell cell) {
		if (!cell.subject.assignFor(cell.combinedSlot)) {
			throw new RuntimeException("Subject can't be assigned");
		}
		if (!cell.teacher.assignFor(cell.slots)) {
			throw new RuntimeException("Teacher can't be assigned");
		}
		if (!cell.room.assignFor(cell.slots)) {
			throw new RuntimeException("Room can't be assigned");
		}
		routineCells.add(cell);
	}
}
