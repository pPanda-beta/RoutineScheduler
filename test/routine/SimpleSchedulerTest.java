package routine;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

import routine.model.Allotment;

import static routine.SamplesFactory.SAMPLE_ROOMS;

/**
 * Created by Palash_Das on 24-04-2017.
 */
public class SimpleSchedulerTest {
	
	SimpleScheduler simpleScheduler;
	
	@Before
	public void setUp() throws Exception {
		simpleScheduler = new SimpleScheduler(
				SAMPLE_ROOMS,
				SamplesFactory.SAMPLE_SUBJECTS,
				SamplesFactory.SAMPLE_SEM_SUBJECTS,
				SamplesFactory.SAMPLE_TEACHER_PREFERENCES
		);
	}
	
	@Test
	public void testSampleRoutine() throws Exception {
		Map<DayOfWeek, Map<String, List<Allotment>>> routine = simpleScheduler.getRoutine();
		
		StringBuilder text = new StringBuilder();
		text.append("Routine : \n");
		routine.forEach((day, deptClasses) -> {
			deptClasses.forEach((year, row) -> {
				text.append(day + " - " + year + " - " + row.toString() + "\n");
			});
		});
		System.out.println(text);
		
	}
}