package routine.model;

import org.junit.Test;

import java.util.Arrays;

import static java.time.DayOfWeek.MONDAY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static routine.Constants.ALL_DAY_SLOT_TIMES;

/**
 * Created by Palash_Das on 23-04-2017.
 */
public class TeacherTest {
	@Test
	public void assignForReturnsTrueForAvailableSlotAssignment() throws Exception {
		Teacher teacher = new Teacher("", Arrays.asList());
		
		assertTrue(teacher.assignFor(new DayTimeSlot(MONDAY, ALL_DAY_SLOT_TIMES.get(5))));
		assertEquals(1, teacher.noOfAssignedSlot());
	}
	
	@Test
	public void isFreeDuringReturnsFalseForReservedSlot() throws Exception {
		Teacher teacher = new Teacher("", Arrays.asList());
		
		DayTimeSlot slot = new DayTimeSlot(MONDAY, "13:30", "14:20");
		DayTimeSlot slot2 = new DayTimeSlot(MONDAY, "13:00", "14:00");
		DayTimeSlot slot3 = new DayTimeSlot(MONDAY, "13:50", "14:50");
		
		assertTrue(teacher.assignFor(slot));
		assertFalse(teacher.isFreeDuring(slot2));
		assertFalse(teacher.isFreeDuring(slot3));
	}
	
	@Test
	public void isFreeDuringReturnsTrueForUnreservedSlot() throws Exception {
		Teacher teacher = new Teacher("", Arrays.asList());
		
		DayTimeSlot slot = new DayTimeSlot(MONDAY, "13:30", "14:20");
		DayTimeSlot slot2 = new DayTimeSlot(MONDAY, "12:00", "13:30");
		DayTimeSlot slot3 = new DayTimeSlot(MONDAY, "14:30", "14:50");
		
		assertTrue(teacher.assignFor(slot));
		assertTrue(teacher.isFreeDuring(slot2));
		assertTrue(teacher.isFreeDuring(slot3));
	}
}