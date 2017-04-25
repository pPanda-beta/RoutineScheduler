package routine.model;

import org.junit.Before;
import org.junit.Test;

import static java.time.DayOfWeek.MONDAY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Palash_Das on 24-04-2017.
 */
public class SubjectTest {
	
	Subject labSub, theorySub;
	
	@Before
	public void setUp() throws Exception {
		labSub = new Subject("CS291");
		theorySub = new Subject("CS201");
	}
	
	@Test
	public void labShouldNotFitIn2Periods() throws Exception {
		assertFalse(labSub.canFitIn(new DayTimeSlot(MONDAY, "11:00", "12:50")));
	}
	
	@Test
	public void labShouldFitIn3Periods() throws Exception {
		assertTrue(labSub.canFitIn(new DayTimeSlot(MONDAY, "10:20", "12:50")));
	}
	
	@Test
	public void theoryShouldNotFitIn3Periods() throws Exception {
		assertFalse(theorySub.canFitIn(new DayTimeSlot(MONDAY, "10:20", "12:50")));
	}
	
	
	@Test
	public void theoryShouldFitIn1Period() throws Exception {
		assertTrue(theorySub.canFitIn(new DayTimeSlot(MONDAY, "10:20", "11:10")));
	}
	
	@Test
	public void theoryShouldFitIn2Periods() throws Exception {
		assertTrue(theorySub.canFitIn(new DayTimeSlot(MONDAY, "09:30", "11:10")));
	}
}