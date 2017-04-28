package routine;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Palash_Das on 24-04-2017.
 */

public class Validator {
	
	
	public static boolean isValidSemSubject(List<String> subjects, Map<String, List<String>> semSubjects) {
		Set<String> preferedAllSubjects = new HashSet<>();
		semSubjects.forEach((year, subjectsOfThatYear) -> preferedAllSubjects.addAll(subjectsOfThatYear));
		
		return subjects.containsAll(preferedAllSubjects) && preferedAllSubjects.containsAll(subjects);
	}
	
	public static boolean isValidTeacherPreference(List<String> subjects, Map<String, List<String>> teacherPreferences) {
		Set<String> preferedAllSubjects = new HashSet<>();
		teacherPreferences.forEach((teacher, subjectsOfChoice) -> preferedAllSubjects.addAll(subjectsOfChoice));
		
/*		subjects.stream()
				.filter(s -> !preferedAllSubjects.contains(s))
				.forEach(System.out::println);
		*/
		return subjects.containsAll(preferedAllSubjects) && preferedAllSubjects.containsAll(subjects);
	}
}
