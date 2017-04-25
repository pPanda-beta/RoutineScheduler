package routine.model;

import java.util.Set;
import java.util.TreeSet;

import routine.Constatnts;

/**
 * Created by Palash_Das on 24-04-2017.
 */

public abstract class Reservable {
	protected final Set<DayTimeSlot> freeSlots;
	protected final Set<DayTimeSlot> assignedSlots;
	
	Reservable() {
		freeSlots = Constatnts.generateSlotsForWholeWeek();
		assignedSlots = new TreeSet<>();
	}
	
	public boolean isFreeDuring(DayTimeSlot timeSlot) {
		return assignedSlots.stream()
				.filter(timeSlot::isOverlappingWith)
				.count() == 0;
	}
	
	public boolean assignFor(DayTimeSlot slot) {
		if (!freeSlots.contains(slot)) {
			return false;
		}
		freeSlots.remove(slot);
		assignedSlots.add(slot);
		return true;
	}
	
	public int noOfAssignedSlot() {
		return assignedSlots.size();
	}
}
