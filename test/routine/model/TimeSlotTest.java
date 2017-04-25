package routine.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Palash_Das on 23-04-2017.
 */
public class TimeSlotTest {
	
	@Test
	public void equalsReturnsTrueForSameSlots() throws Exception {
		TimeSlot ts1 = new TimeSlot("11:00", "12:10");
		TimeSlot ts2 = new TimeSlot("11:00", "12:10");
		
		assertTrue(ts1.equals(ts2));
	}
}