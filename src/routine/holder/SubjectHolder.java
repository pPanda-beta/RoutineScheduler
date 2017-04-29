package routine.holder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import routine.model.DayTimeSlot;
import routine.model.Subject;

/**
 * Created by Palash_Das on 24-04-2017.
 */

public class SubjectHolder {
	
	private final Set<Subject> subjects = new HashSet<>();
	
	public SubjectHolder(Collection<String> subjectCodes) {
		subjectCodes.stream()
				.map(Subject::new)
				.forEach(subjects::add);
	}
	
	public long totalRemainingMinutes() {
		return subjects.stream()
				.map(Subject::getRemainingMinutes)
				.reduce(Long::sum)
				.orElse(-1L);
	}
	
	public Subject getSubjectSuitableFor(DayTimeSlot slot) {
		return subjects.stream()
				.filter(subject -> subject.getRemainingMinutes() > 0)
				.filter(subject -> subject.canFitIn(slot))
				//	.sorted(Comparator.comparing(subject -> -subject.getRemainingMinutes()))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("No available subject found"));
	}
}
