package routine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

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
	
	public static final int[][] STRATEGIES = new int[][]{
			{1, 3},
			{2, 2},
			{1, 2, 1},
			{1, 1, 2},
			{2, 1, 1},
			{3},
			{2},
			{1}
	};
	
	private final SlotHolder slotHolder;
	private final SubjectHolder subjectHolder;
	private final RoomHolder roomHolder;
	private final TeacherHolder teacherHolder;
	private Set<RoutineCell> routineCells;
	
	public SimpleSolver(SlotHolder slotHolder, SubjectHolder subjectHolder, RoomHolder roomHolder, TeacherHolder teacherHolder) {
		this.slotHolder = slotHolder;
		this.subjectHolder = subjectHolder;
		this.roomHolder = roomHolder;
		this.teacherHolder = teacherHolder;
	}
	
	public Set<RoutineCell> evaluateSolutionSerially(int noOfRounds) {
		return evaluateSolution(noOfRounds, this::processOneRoundSerially);
	}
	
	public Set<RoutineCell> evaluateSolutionRandomly(int noOfRounds) {
		return evaluateSolution(noOfRounds, this::processOneRoundRandomly);
	}
	
	private Set<RoutineCell> evaluateSolution(int noOfRounds, Function<Integer, Boolean> processTechnique) {
		routineCells = new HashSet<>();
		while (noOfRounds-- > 0) {
			if (!processTechnique.apply(noOfRounds)) {
				continue;
			}
			if (subjectHolder.totalRemainingMinutes() <= 0) {
				return routineCells;
			}
		}
		return null;
	}
	
	private boolean processOneRoundSerially(int roundNumber) {
		return strategy(STRATEGIES[roundNumber % STRATEGIES.length]);
	}
	
	private boolean processOneRoundRandomly(int roundNumber) {
		Random random = new Random();
		return strategy(STRATEGIES[random.nextInt(STRATEGIES.length)]);
	}
	
	private boolean strategy(int... periods) {
		List<RoutineCell> finalCells = new ArrayList<>();
		List<DayTimeSlot> totalSlots = null;
		
		try {
			int totalPeriods = Arrays.stream(periods).reduce(Integer::sum).getAsInt();
			totalSlots = slotHolder.popConsecutiveSlots(totalPeriods);
			
			int startIndex = 0;
			for (int period : periods) {
				List<DayTimeSlot> subSlots = totalSlots.subList(startIndex, startIndex + period);
				RoutineCell cell = arrangeResources(subSlots);
				finalCells.add(cell);
				startIndex += period;
			}
			
			verifyUniqueSubjects(finalCells);
		} catch (Exception e) {
			if (totalSlots != null) {
				slotHolder.putBackSlots(totalSlots);
			}
			return false;
		}
		finalCells.forEach(this::allocateResources);
		return true;
	}
	
	private void verifyUniqueSubjects(List<RoutineCell> finalCells) {
		if (finalCells.stream()
				.map(cell -> cell.subject.toString())
				.distinct()
				.count() != finalCells.size()
				) {
			throw new RuntimeException("Same subjects are chosen more than once");
		}
	}
	
	private RoutineCell arrangeResources(List<DayTimeSlot> slots) {
		RoutineCell cell = new RoutineCell();

		cell.slots = slots;
		cell.combinedSlot = DayTimeSlot.compose(slots);
		cell.subject = subjectHolder.getSubjectSuitableFor(cell.combinedSlot);
		cell.teacher = teacherHolder.getSuitableTeacherFor(cell.subject, cell.combinedSlot);
		cell.room = roomHolder.getFreeRoomDuring(cell.subject, cell.combinedSlot);

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
	
	public static class RoutineCell {
		DayTimeSlot combinedSlot;
		List<DayTimeSlot> slots;
		Subject subject;
		Room room;
		Teacher teacher;
		String yearAndSection;
	}
}
