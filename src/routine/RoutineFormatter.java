package routine;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import routine.model.Allotment;
import routine.model.Reservable;

/**
 * Created by Palash_Das on 29-04-2017.
 */

//Manipulates Data for routine
public class RoutineFormatter {
	
	private Map<DayOfWeek, Map<String, List<Allotment>>> resultYearWise = new TreeMap<>();
	private Map<String, Map<DayOfWeek, Map<String, List<Allotment>>>> resultTeacherWise = new TreeMap<>();
	private Map<String, Map<DayOfWeek, Map<String, List<Allotment>>>> resultRoomWise = new TreeMap<>();
	
	public RoutineFormatter(Collection<SimpleSolver.RoutineCell> allRoutineCells) {
		allRoutineCells.stream()
				.sorted((cell1, cell2) -> cell1.combinedSlot.compareTo(cell2.combinedSlot))
				.peek(this::addCellToYearWiseRoutine)
				.peek(cell -> addCellToReservableWiseRoutine(cell.teacher, resultTeacherWise, cell))
				.forEach(cell -> addCellToReservableWiseRoutine(cell.room, resultRoomWise, cell));
	}
	
	private static void addCellToRoutine(Map<DayOfWeek, Map<String, List<Allotment>>> routine, SimpleSolver.RoutineCell cell) {
		String yearAndSection = cell.yearAndSection;
		DayOfWeek day = cell.combinedSlot.getDay();
		
		routine.putIfAbsent(day, new TreeMap<>());
		routine.get(day)
				.putIfAbsent(yearAndSection, new ArrayList<>());
		routine.get(day)
				.get(yearAndSection)
				.add(cellToAllotment(cell));
	}
	
	private static Allotment cellToAllotment(SimpleSolver.RoutineCell cell) {
		return new Allotment(cell.teacher.toString(),
				cell.subject.toString(),
				cell.room.toString(),
				cell.combinedSlot.getStart(),
				(int) cell.combinedSlot.durationInMinutes() / Constants.DURATION_OF_EACH_PERIOD);
	}
	
	public Map<DayOfWeek, Map<String, List<Allotment>>> getRoutineByYear() {
		return resultYearWise;
	}
	
	public Map<String, Map<DayOfWeek, Map<String, List<Allotment>>>> getRoutineByTeacher() {
		return resultTeacherWise;
	}
	
	public Map<String, Map<DayOfWeek, Map<String, List<Allotment>>>> getRoutineByRoom() {
		return resultRoomWise;
	}
	
	private void addCellToYearWiseRoutine(SimpleSolver.RoutineCell cell) {
		addCellToRoutine(resultYearWise, cell);
	}
	
	private void addCellToReservableWiseRoutine(Reservable identity, Map<String, Map<DayOfWeek, Map<String, List<Allotment>>>> resultRoutine, SimpleSolver.RoutineCell cell) {
		resultRoutine.putIfAbsent(identity.toString(), new TreeMap<>());
		addCellToRoutine(resultRoutine.get(identity.toString()), cell);
	}
}
