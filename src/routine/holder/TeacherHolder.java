package routine.holder;

import java.util.*;
import java.util.stream.Collectors;

import routine.model.DayTimeSlot;
import routine.model.Reservable;
import routine.model.Subject;
import routine.model.Teacher;

/**
 * Created by Palash_Das on 24-04-2017.
 */

public class TeacherHolder {
	
	private final Comparator<Teacher> compareWhoTeachesMoreSubjects = Teacher::teachesMoreSubjectsThan;
	private final Comparator<Teacher> compareWhoTeachesLessSubjects = compareWhoTeachesMoreSubjects.reversed();
	
	private final Set<Teacher> availableTeachers = new TreeSet<Teacher>(
			compareWhoTeachesLessSubjects
					.thenComparing(Teacher::noOfAssignedSlot)
					.thenComparing(Teacher::toString)
	);
	
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
//				.sorted(Comparator.comparing(Teacher::noOfAssignedSlot))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("No teacher available"));
	}
	
	public Collection<DayTimeSlot> getCommonSlots() {
		return Reservable.getCommonSlotsFrom(new HashSet<>(availableTeachers));
	}
}
