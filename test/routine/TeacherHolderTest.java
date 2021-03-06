package routine;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import routine.holder.TeacherHolder;
import routine.model.DayTimeSlot;
import routine.model.Subject;
import routine.model.Teacher;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Palash_Das on 24-04-2017.
 */
public class TeacherHolderTest {
	
	TeacherHolder teacherHolder;
	
	@Before
	public void setUp() throws Exception {
		teacherHolder = new TeacherHolder(new HashMap<String, List<String>>() {{
			put("P.D.", Arrays.asList("IT401"));
			put("A.B.", Arrays.asList("CS401", "IT401"));
		}});
	}
	
	@Test
	public void cantGetTeacherForUnknownSubject() throws Exception {
		assertNull(teacherHolder.getSuitableTeacherFor(new Subject("ME101"), new DayTimeSlot(DayOfWeek.MONDAY, "11:10", "12:00")));
	}
	
	@Test
	public void cantGetTeacherForReservedSlot() throws Exception {
		DayTimeSlot slot = new DayTimeSlot(DayOfWeek.MONDAY, "11:10", "12:00");
		Subject cs401 = new Subject("CS401");
		
		Teacher PD = teacherHolder.getSuitableTeacherFor(cs401, slot);
		PD.assignFor(slot);
		
		assertNull(teacherHolder.getSuitableTeacherFor(cs401, slot));
	}
	
	@Test
	public void getDifferentTeacherIfSlotIsNotAvailable() throws Exception {
		DayTimeSlot slot = new DayTimeSlot(DayOfWeek.MONDAY, "11:10", "12:00");
		Subject it401 = new Subject("IT401");
		
		Teacher t1 = teacherHolder.getSuitableTeacherFor(it401, slot);
		assertNotNull(t1);
		t1.assignFor(slot);
		
		Teacher t2 = teacherHolder.getSuitableTeacherFor(it401, slot);
		assertNotNull(t2);
		assertNotEquals(t1, t2);
	}
}
