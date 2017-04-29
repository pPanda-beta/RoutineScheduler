package routine.model;

import org.junit.Before;
import org.junit.Test;

import static java.time.DayOfWeek.MONDAY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static routine.Constants.ALL_DAY_SLOT_TIMES;

/**
 * Created by Palash_Das on 23-04-2017.
 */
public class ReservableTest {
	
	private Reservable reservable;
	
	@Before
	public void setUp() throws Exception {
		reservable = new Reservable() {
		};
	}
	
	@Test
	public void assignForReturnsTrueForAvailableSlotAssignment() throws Exception {
		reservable.assignFor(new DayTimeSlot(MONDAY, ALL_DAY_SLOT_TIMES.get(5)));
		assertEquals(1, reservable.noOfAssignedSlot());
	}
	
	@Test
	public void isFreeDuringReturnsFalseForReservedSlot() throws Exception {
		DayTimeSlot slot = new DayTimeSlot(MONDAY, "13:30", "14:20");
		DayTimeSlot slot2 = new DayTimeSlot(MONDAY, "13:00", "14:00");
		DayTimeSlot slot3 = new DayTimeSlot(MONDAY, "13:50", "14:50");
		
		reservable.assignFor(slot);
		assertFalse(reservable.isFreeDuring(slot2));
		assertFalse(reservable.isFreeDuring(slot3));
	}
	
	@Test
	public void isFreeDuringReturnsTrueForUnreservedSlot() throws Exception {
		DayTimeSlot slot = new DayTimeSlot(MONDAY, "13:30", "14:20");
		DayTimeSlot slot2 = new DayTimeSlot(MONDAY, "12:00", "13:30");
		DayTimeSlot slot3 = new DayTimeSlot(MONDAY, "14:30", "14:50");
		
		reservable.assignFor(slot);
		assertTrue(reservable.isFreeDuring(slot2));
		assertTrue(reservable.isFreeDuring(slot3));
	}
	
	@Test
	public void cantAssignForSameSlotMoreThanOnce() throws Exception {
		DayTimeSlot slot = new DayTimeSlot(MONDAY, "13:30", "14:20");
		
		reservable.assignFor(slot);
		try {
			reservable.assignFor(slot);
			fail();
		} catch (RuntimeException e) {
		}
	}
}