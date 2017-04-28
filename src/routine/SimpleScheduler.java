package routine;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import routine.holder.RoomHolder;
import routine.holder.SlotHolder;
import routine.holder.SubjectHolder;
import routine.holder.TeacherHolder;
import routine.model.Allotment;

import static routine.Constants.MAXIMUM_NO_OF_ROUNDS;

/**
 * Created by Palash_Das on 23-04-2017.
 */
public class SimpleScheduler extends Scheduler {
	
	private Map<DayOfWeek, Map<String, List<Allotment>>> resultYearWise = new TreeMap<>();
	private Map<String, SimpleSolver> solverOfYear = new HashMap<>();
	private boolean generated = false;
	
	public SimpleScheduler(List<String> rooms, List<String> subjects, Map<String, List<String>> semSubjects, Map<String, List<String>> teacherPreferences) {
		super(rooms, subjects, semSubjects, teacherPreferences);
		
		validateSubjects(subjects, semSubjects, teacherPreferences);
		
		RoomHolder roomHolder = new RoomHolder(rooms);
		TeacherHolder teacherHolder = new TeacherHolder(teacherPreferences);
		
		semSubjects.forEach((year, subjectsOfThatYear) -> solverOfYear.put(year, new SimpleSolver(new SlotHolder(), new SubjectHolder(subjectsOfThatYear), roomHolder, teacherHolder)));
	}
	
	private void validateSubjects(List<String> subjects, Map<String, List<String>> semSubjects, Map<String, List<String>> teacherPreferences) {
		if (!Validator.isValidSemSubject(subjects, semSubjects)) {
			throw new IllegalArgumentException();
		}
		
		if (!Validator.isValidTeacherPreference(subjects, teacherPreferences)) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public Map<DayOfWeek, Map<String, List<Allotment>>> getRoutineByYear() {
		buildRoutines();
		return resultYearWise;
	}
	
	private void buildRoutines() {
		if (generated) {
			return;
		}
		solverOfYear.forEach((year, solver) -> {
			Set<SimpleSolver.RoutineCell> routineCellsOfThatYear = solver.evaluateSolutionSerially(MAXIMUM_NO_OF_ROUNDS);
			if (routineCellsOfThatYear == null) {
				routineCellsOfThatYear = solver.evaluateSolutionRandomly(MAXIMUM_NO_OF_ROUNDS);
			}
			if (routineCellsOfThatYear == null) {
				throw new RuntimeException("Sorry, Routine can't be build, very tight situation");
			}
			routineCellsOfThatYear.stream()
					.sorted((cell1, cell22) -> cell1.combinedSlot.compareTo(cell22.combinedSlot))
					.forEach(cell -> addCellToYearWiseRoutine(year, cell));
		});
		generated = true;
	}
	
	private void addCellToYearWiseRoutine(String year, SimpleSolver.RoutineCell cell) {
		DayOfWeek day = cell.combinedSlot.getDay();
		
		resultYearWise.putIfAbsent(day, new TreeMap<>());
		resultYearWise.get(day).putIfAbsent(year, new ArrayList<>());
		
		resultYearWise.get(day).get(year)
				.add(new Allotment(cell.teacher.toString(),
						cell.subject.toString(),
						cell.room.toString(),
						cell.combinedSlot.getStart(),
						(int) cell.combinedSlot.durationInMinutes() / Constants.DURATION_OF_EACH_PERIOD)
				);
	}
}
