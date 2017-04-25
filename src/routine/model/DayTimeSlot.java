package routine.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

/**
 * Created by Palash_Das on 23-04-2017.
 */

public class DayTimeSlot extends TimeSlot implements Comparable<DayTimeSlot> {
	
	private final DayOfWeek day;
	
	public DayTimeSlot(DayOfWeek day, TimeSlot slot) {
		super(slot.start, slot.end);
		this.day = day;
	}
	
	public DayTimeSlot(DayOfWeek day, String... times) {
		this(day, new TimeSlot(times));
	}
	
	public static DayTimeSlot compose(Collection<DayTimeSlot> dayTimeSlots) {
		DayOfWeek day = dayTimeSlots.iterator().next().day;
		LocalTime minStart = LocalTime.MAX;
		LocalTime maxEnd = LocalTime.MIN;
		
		for (DayTimeSlot slot : dayTimeSlots) {
			if (!day.equals(slot.day)) {
				return null;
			}
			minStart = minStart.compareTo(slot.start) <= 0 ? minStart : slot.start;
			maxEnd = maxEnd.compareTo(slot.end) >= 0 ? maxEnd : slot.end;
		}
		return new DayTimeSlot(day, new TimeSlot(minStart, maxEnd));
	}
	
	@Override
	public int compareTo(DayTimeSlot other) {
		int cmp = day.compareTo(other.day);
		if (cmp == 0) {
			cmp = start.compareTo(other.start);
		}
		if (cmp == 0) {
			cmp = end.compareTo(other.end);
		}
		return cmp;
	}
	
	public boolean isOverlappingWith(DayTimeSlot other) {
		return day.equals(other.day) && super.isOverlappingWith(other);
	}
	
	public boolean isConsecutiveWith(DayTimeSlot other) {
		return day.equals(other.day) && end.equals(other.start);
	}
	
	public long durationInMinutes() {
		return start.until(end, ChronoUnit.MINUTES);
	}
	
	@Override
	public String toString() {
		return " " + day + " - " + start + " - " + end + " ";
	}
	
	public DayOfWeek getDay() {
		return day;
	}
}
