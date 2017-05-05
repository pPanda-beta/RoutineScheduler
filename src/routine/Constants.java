package routine;

import routine.model.DayTimeSlot;
import routine.model.TimeSlot;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

/**
 * Created by Palash_Das on 23-04-2017.
 */

public class Constants {
	
	public static final int MAXIMUM_NO_OF_ROUNDS = 12999;
	
	public static final int DURATION_OF_EACH_PERIOD = 50;
	
	public static final List<String[]> ALL_DAY_SLOT_TIMES = Arrays.asList(
			new String[]{"09:30", "10:20"},
			new String[]{"10:20", "11:10"},
			new String[]{"11:10", "12:00"},
			new String[]{"12:00", "12:50"},
			new String[]{"13:30", "14:20"},
			new String[]{"14:20", "15:10"},
			new String[]{"15:10", "16:00"},
			new String[]{"16:00", "16:50"}
	);
	
	public static final List<DayOfWeek> workingDays = Arrays.stream(DayOfWeek.values())
			.filter(day -> day != SUNDAY && day != SATURDAY)
			.collect(Collectors.toList());
	
	public static final Set<TimeSlot> ALL_SLOTS = ALL_DAY_SLOT_TIMES.stream()
			.map(TimeSlot::new)
			.collect(Collectors.toSet());
	
	public static Set<DayTimeSlot> generateSlotsForWholeWeek() {
		Set<DayTimeSlot> weekSlots = new TreeSet<>();
		for (DayOfWeek day : workingDays) {
			ALL_SLOTS.forEach(slot -> weekSlots.add(new DayTimeSlot(day, slot)));
		}
		return weekSlots;
	}
}
