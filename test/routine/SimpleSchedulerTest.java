package routine;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import routine.model.Allotment;

import static org.junit.Assert.fail;
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
		routine = simpleScheduler.getRoutineByYear();
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
	public void testSampleRoutineFor2Sections() throws Exception {
		Map<String, List<String>> semSubjectsForBothSections = new TreeMap<>();
		SEM_SUBJECTS.forEach((year, subjects) -> {
			semSubjectsForBothSections.put(year + "A", subjects);
			semSubjectsForBothSections.put(year + "B", subjects);
		});
		List<String> doubleRooms = ROOMS.stream()
				.map(roomNo -> Arrays.asList(roomNo, roomNo + "Extra"))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		
		simpleScheduler = new SimpleScheduler(doubleRooms, SUBJECTS, semSubjectsForBothSections, TEACHER_PREFERENCES);
		routine = simpleScheduler.getRoutineByYear();
		
		testSampleRoutine();
	}
	
	@Test
	public void noTwoAllotmentsOverlapWithEachOther() throws Exception {
		routine.values().forEach(this::verifyOneDaysClasses);
	}
	
	private void verifyOneDaysClasses(Map<String, List<Allotment>> deptClasses) {
		List<Allotment> allotments = deptClasses.values().stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		Helper.forEachPair(allotments, (allotment1, allotment2) -> {
			if (allotment1.isOverlappingWith(allotment2)) {
				fail(allotment1 + " is overlapping with " + allotment2);
			}
		});
	}
}