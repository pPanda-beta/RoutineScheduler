package routine;

import org.junit.Before;
import org.junit.Test;
import routine.holder.SlotHolder;
import routine.model.DayTimeSlot;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * Created by Palash_Das on 24-04-2017.
 */
public class SlotHolderTest {
	
	SlotHolder slotHolder;
	
	@Before
	public void setUp() throws Exception {
		slotHolder = new SlotHolder();
	}
	
	@Test
	public void cantPopMoreThan4() throws Exception {
		assertNull(slotHolder.popConsecutiveSlots(5));
	}
	
	@Test
	public void popsSingleSlot() throws Exception {
		assertNotNull(slotHolder.popConsecutiveSlots(1));
	}
	
	@Test
	public void popsConsecutiveSlots() throws Exception {
		List<DayTimeSlot> slots = slotHolder.popConsecutiveSlots(4);
		
		slots.stream().reduce((slot, nextSlot) -> {
			if (!slot.isConsecutiveWith(nextSlot)) {
				fail();
			}
			return nextSlot;
		});
	}
}
