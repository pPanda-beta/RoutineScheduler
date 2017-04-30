package routine;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	private List<SimpleSolver.RoutineCell> allRoutineCells = new ArrayList<>();
	private RoutineFormatter formatter;
	private Map<String, SimpleSolver> solverOfYear = new HashMap<>();
	
	public SimpleScheduler(List<String> rooms, List<String> subjects, Map<String, List<String>> semSubjects, Map<String, List<String>> teacherPreferences) {
		super(rooms, subjects, semSubjects, teacherPreferences);
		
		validateSubjects(subjects, semSubjects, teacherPreferences);
		constructSolvers(rooms, semSubjects, teacherPreferences);
		buildRoutines();
	}
	
	private void validateSubjects(List<String> subjects, Map<String, List<String>> semSubjects, Map<String, List<String>> teacherPreferences) {
		if (!Validator.isValidSemSubject(subjects, semSubjects)) {
			throw new IllegalArgumentException("Subjects and semester preferences are inconsistent");
		}
		
		if (!Validator.isValidTeacherPreference(subjects, teacherPreferences)) {
			throw new IllegalArgumentException("Subjects and teacher preferences are inconsistent");
		}
	}
	
	private void constructSolvers(List<String> rooms, Map<String, List<String>> semSubjects, Map<String, List<String>> teacherPreferences) {
		RoomHolder roomHolder = new RoomHolder(rooms);
		TeacherHolder teacherHolder = new TeacherHolder(teacherPreferences);
		
		semSubjects.forEach((year, subjectsOfThatYear) -> solverOfYear.put(year, new SimpleSolver(new SlotHolder(), new SubjectHolder(subjectsOfThatYear), roomHolder, teacherHolder)));
	}
	
	public Map<DayOfWeek, Map<String, List<Allotment>>> getRoutineByYear() {
		return formatter.getRoutineByYear();
	}
	
	@Override
	public List<SimpleSolver.RoutineCell> getAllRoutineCells() {
		return allRoutineCells;
	}
	
	@Override
	public RoutineFormatter getFormatter() {
		return formatter;
	}
	
	private void buildRoutines() {
		solverOfYear.forEach((year, solver) -> {
			Set<SimpleSolver.RoutineCell> routineCellsOfThatYear = solver.evaluateSolutionSerially(MAXIMUM_NO_OF_ROUNDS);
			if (routineCellsOfThatYear == null) {
				routineCellsOfThatYear = solver.evaluateSolutionRandomly(MAXIMUM_NO_OF_ROUNDS);
			}
			if (routineCellsOfThatYear == null) {
				throw new RuntimeException("Sorry, Routine can't be build, very tight situation");
			}
			routineCellsOfThatYear.stream()
					.peek(cell -> cell.yearAndSection = year)
					.forEach(allRoutineCells::add);
		});
		formatter = new RoutineFormatter(allRoutineCells);
	}
}