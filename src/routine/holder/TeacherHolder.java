package routine.holder;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import routine.model.DayTimeSlot;
import routine.model.Subject;
import routine.model.Teacher;

/**
 * Created by Palash_Das on 24-04-2017.
 */

public class TeacherHolder {
	
	private final Set<Teacher> availableTeachers = new TreeSet<Teacher>((t1, t2) -> {
		int cmp = t1.noOfAssignedSlot() - t2.noOfAssignedSlot();
		if (cmp == 0) {
			cmp = t1.toString().compareTo(t2.toString());
		}
		return cmp;
	});
	
	public TeacherHolder(Map<String, List<String>> preferences) {
		preferences.forEach((name, subjectNames) -> {
			List<Subject> subjects = subjectNames.stream()
					.map(Subject::new)
					.collect(Collectors.toList());
			availableTeachers.add(new Teacher(name, subjects));
		});
	}
	
	public Teacher getSuitableTeacherFor(Subject subject, DayTimeSlot slot) {
		return availableTeachers.stream()
				.filter(teacher -> teacher.isFreeDuring(slot))
				.filter(teacher -> teacher.canTeach(subject))
				.sorted(Comparator.comparing(Teacher::noOfAssignedSlot))
				.findFirst()
				.orElse(null);
	}
}
