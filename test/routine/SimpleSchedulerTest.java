package routine;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import routine.model.Allotment;

import static org.junit.Assert.fail;
import static routine.SamplesFactory.SAMPLE_ROOMS;
import static routine.SamplesFactory.SAMPLE_TEACHER_PREFERENCES;
import static routine.TestDataSet1.ROOMS;
import static routine.TestDataSet1.SEM_SUBJECTS;
import static routine.TestDataSet1.SUBJECTS;
import static routine.TestDataSet1.TEACHER_PREFERENCES;

/**
 * Created by Palash_Das on 24-04-2017.
 */
public class SimpleSchedulerTest {
	
	SimpleScheduler simpleScheduler;
	private Map<DayOfWeek, Map<String, List<Allotment>>> routine;
	
	@Before
	public void setUp() throws Exception {
		simpleScheduler = new SimpleScheduler(ROOMS, SUBJECTS, SEM_SUBJECTS, TEACHER_PREFERENCES);
		routine = simpleScheduler.getRoutine();
	}
	
	@Test
	public void testSampleRoutine() throws Exception {
		StringBuilder text = new StringBuilder();
		text.append("Routine : \n");
		routine.forEach((day, deptClasses) -> {
			deptClasses.forEach((year, row) -> {
				text.append(day + " - " + year + " - " + row.toString() + "\n");
			});
		});
		System.out.println(text);
	}
	
	@Test
	public void noTwoAllotmentsOverlapWithEachOther() throws Exception {
		routine.values().forEach(this::verifyOneDaysClasses);
	}
	
	private void verifyOneDaysClasses(Map<String, List<Allotment>> deptClasses) {
		List<Allotment> allotments = deptClasses.values().stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		
		for (Allotment allotment1 : allotments) {
			for (Allotment allotment2 : allotments) {
				if (allotment1 != allotment2 && allotment1.isOverlappingWith(allotment2)) {
					fail(allotment1 + " is overlapping with " + allotment2);
				}
			}
		}
	}
}