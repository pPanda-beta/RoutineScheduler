package routine.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import routine.Constants;

/**
 * Created by Palash_Das on 24-04-2017.
 */

public abstract class Reservable {
	protected final Set<DayTimeSlot> freeSlots;
	protected final Set<DayTimeSlot> assignedSlots;
	
	Reservable() {
		freeSlots = Constants.generateSlotsForWholeWeek();
		assignedSlots = new TreeSet<>();
	}
	
	public boolean isFreeDuring(DayTimeSlot timeSlot) {
		return assignedSlots.stream()
				.filter(timeSlot::isOverlappingWith)
				.count() == 0;
	}
	
	public void assignFor(DayTimeSlot slot) {
		assignFor(Collections.singletonList(slot));
	}
	
	public void assignFor(Collection<DayTimeSlot> slots) {
		if (!freeSlots.containsAll(slots)) {
			throw new RuntimeException("Object of " + this.getClass().getSimpleName() +" can't be assigned");
		}
		freeSlots.removeAll(slots);
		assignedSlots.addAll(slots);
	}
	
	public int noOfAssignedSlot() {
		return assignedSlots.size();
	}
}
