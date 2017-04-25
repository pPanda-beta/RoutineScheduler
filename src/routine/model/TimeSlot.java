package routine.model;

import java.time.LocalTime;

/**
 * Created by Palash_Das on 23-04-2017.
 */
public class TimeSlot {
	protected final LocalTime start, end;
	
	public TimeSlot(LocalTime start, LocalTime end) {
		this.start = start;
		this.end = end;
	}
	
	public TimeSlot(String... times) {
		this(LocalTime.parse(times[0]), LocalTime.parse(times[1]));
	}
	
	public static TimeSlot compose(TimeSlot slot1, TimeSlot slot2) {
		return new TimeSlot(slot1.start, slot2.end);
	}
	
	public LocalTime getStart() {
		return start;
	}
	
	public boolean isOverlappingWith(TimeSlot other) {
		//x1 <= y2 && y1 <= x2
		return start.isBefore(other.end) && other.start.isBefore(end);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TimeSlot)) return false;
		
		TimeSlot timeSlot = (TimeSlot) o;
		
		if (start != null ? !start.equals(timeSlot.start) : timeSlot.start != null) return false;
		return end != null ? end.equals(timeSlot.end) : timeSlot.end == null;
		
	}
	
	@Override
	public int hashCode() {
		int result = start != null ? start.hashCode() : 0;
		result = 31 * result + (end != null ? end.hashCode() : 0);
		return result;
	}
}
