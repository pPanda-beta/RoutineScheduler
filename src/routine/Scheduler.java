package routine;

import routine.model.Allotment;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

/**
 * Created by Palash_Das on 23-04-2017.
 */

public abstract class Scheduler {
	
	protected final List<String> rooms, subjects;
	protected final Map<String, List<String>> semSubjects, teacherPreferences;
	
	public Scheduler(List<String> rooms, List<String> subjects, Map<String, List<String>> semSubjects, Map<String, List<String>> teacherPreferences) {
		this.rooms = rooms;
		this.subjects = subjects;
		this.semSubjects = semSubjects;
		this.teacherPreferences = teacherPreferences;
	}
	
	public static Scheduler getSimpleScheduler(List<String> rooms, List<String> subjects, Map<String, List<String>> semSubjects, Map<String, List<String>> teacherPreferences) {
		return new SimpleScheduler(rooms, subjects, semSubjects, teacherPreferences);
	}
	
	public abstract List<SimpleSolver.RoutineCell> getAllRoutineCells();
	
	public abstract RoutineFormatter getFormatter();
	
	//Day vs year and its list of allotments
	@Deprecated
	public abstract Map<DayOfWeek, Map<String, List<Allotment>>> getRoutineByYear();
}
