package routine.holder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import routine.Constants;
import routine.model.DayTimeSlot;

/**
 * Created by Palash_Das on 23-04-2017.
 */

//Stores some slots and gives when needed
public class SlotHolder {
	
	Set<DayTimeSlot> availableSlots;
	Set<DayTimeSlot> reusableSlots = new TreeSet<>();
	
	public SlotHolder() {
		availableSlots = Constants.generateSlotsForWholeWeek();
	}
	
	public List<DayTimeSlot> popConsecutiveSlots(int no) {
		List<DayTimeSlot> slots = getConsecutiveSlotsIfPossible(no);
		if (slots != null) {
			availableSlots.removeAll(slots);
		}
		return slots;
	}
	
	public void putBackSlots(Collection<DayTimeSlot> slots) {
		reusableSlots.addAll(slots);
	}
	
	private List<DayTimeSlot> getConsecutiveSlotsIfPossible(int no) {
		List<DayTimeSlot> result = new ArrayList<>();
		if (availableSlots.isEmpty()) {
			availableSlots.addAll(reusableSlots);
			reusableSlots.clear();
		}
		result.add(availableSlots.iterator().next());
		for (DayTimeSlot slot : availableSlots) {
			DayTimeSlot lastAddedSlot = result.get(result.size() - 1);
			if (!lastAddedSlot.isConsecutiveWith(slot)) {
				result.clear();
			}
			result.add(slot);
			if (result.size() == no) {
				return result;
			}
		}
		return null;
	}
}
