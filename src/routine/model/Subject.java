package routine.model;

import static routine.Constants.DURATION_OF_EACH_PERIOD;

/**
 * Created by Palash_Das on 24-04-2017.
 */

public class Subject {
	
	private final String code;
	private final boolean isLab;
	private long remainingMinutes;
	
	public Subject(String code) {
		this.code = code;
		this.isLab = code.contains("9");
		this.remainingMinutes = (isLab ? 3 : 4) * DURATION_OF_EACH_PERIOD;
	}
	
	public boolean canFitIn(DayTimeSlot slot) { //TODO : very complex concept
//		return !isLab ^ slot.durationInMinutes() >= 150;
		long slotSize = slot.durationInMinutes();
		if (isLab) {
			return slotSize >= 3 * DURATION_OF_EACH_PERIOD;
		}
		return slotSize <= remainingMinutes && slotSize <= 2 * DURATION_OF_EACH_PERIOD;
	}
	
	public void assignFor(DayTimeSlot slot) {
		if (!canFitIn(slot)) {
			throw new RuntimeException("Subject can't be assigned");
		}
		remainingMinutes -= slot.durationInMinutes();
	}
	
	public boolean isLabSubject() {
		return isLab;
	}
	
	public long getRemainingMinutes() {
		return remainingMinutes;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Subject)) return false;
		
		Subject subject = (Subject) o;
		
		if (isLab != subject.isLab) return false;
		return code != null ? code.equals(subject.code) : subject.code == null;
		
	}
	
	@Override
	public int hashCode() {
		int result = code != null ? code.hashCode() : 0;
		result = 31 * result + (isLab ? 1 : 0);
		return result;
	}
	
	@Override
	public String toString() {
		return code;
	}
}
