package routine;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import routine.model.Allotment;

import static java.time.DayOfWeek.MONDAY;

/**
 * Created by Palash_Das on 23-04-2017.
 */

public class SamplesFactory {
	
	public static final List<String> SAMPLE_ROOMS = Arrays.asList("1012", "2006", "5008", "LAB2", "LAB5", "LAB7");
	
	public static final List<String> SAMPLE_SUBJECTS = Arrays.asList("M(CS)401", "M401", "CS401", "CS402", "IT401", "HU491", "M(CS)491", "CS491", "IT491", "HU601", "IT601", "IT602", "IT603", "IT604B", "IT605D", "IT691", "IT692", "IT693", "HU801A", "IT801D", "IT802B", "IT891", "IT892");
	
	public static final Map<String, List<String>> SAMPLE_SEM_SUBJECTS = new HashMap<String, List<String>>() {{
		put("2nd", Arrays.asList("M(CS)401", "M401", "CS401", "CS402", "IT401", "HU491", "M(CS)491", "CS491", "IT491"));
		put("3rd", Arrays.asList("HU601", "IT601", "IT602", "IT603", "IT604B", "IT605D", "IT691", "IT692", "IT693"));
		put("4th", Arrays.asList("HU801A", "IT801D", "IT802B", "IT891", "IT892"));
	}};
	
	public static final HashMap<String, List<String>> SAMPLE_TEACHER_PREFERENCES = new HashMap<String, List<String>>() {{
		put("SSR", Arrays.asList("M(CS)401", "M(CS)491"));
		put("KD", Arrays.asList("M401"));
		put("MSK", Arrays.asList("CS401", "CS491"));
		put("PSS", Arrays.asList("CS402"));
		put("PB", Arrays.asList("IT401", "IT491", "IT892"));
		put("JB", Arrays.asList("HU491"));
		put("RM", Arrays.asList("HU601"));
		put("SM", Arrays.asList("IT605D", "IT801D", "IT891", "IT892"));
		put("PS", Arrays.asList("HU801A", "IT802B", "IT891"));
		put("AG", Arrays.asList("IT601", "IT691"));
		//put("AC", Arrays.asList("M401"));
		put("SS", Arrays.asList("IT602", "IT692"));
		put("BS", Arrays.asList("IT603", "IT693"));
		put("TBH", Arrays.asList("CS402"));
		put("TB", Arrays.asList("IT604B"));
		//put("AM", Arrays.asList("M401"));
	}};
	
	public static final TreeMap<DayOfWeek, Map<String, List<Allotment>>> SAMPLE_ROUTINE = new TreeMap<DayOfWeek, Map<String, List<Allotment>>>() {{
		put(MONDAY, new TreeMap<String, List<Allotment>>() {{
			put("2nd", Arrays.asList(
					new Allotment("P.D.", "CS201", "1011",  LocalTime.of(11, 10), 2),
					new Allotment("M.B.", "CS293", "Lab3", LocalTime.of(10, 20), 3),
					new Allotment("P.D.", "CS401","1010", LocalTime.parse("09:30:00"), 2)
			));
			put("4th", Arrays.asList(
					new Allotment("P.D.", "CS401", "1010", LocalTime.parse("09:30:00"), 2)
			));
		}});
	}};
	
	public Allotment dummyAllotment() {
		return new Allotment("P.D.", "CS201", "1011",  LocalTime.NOON, 2);
	}
	
	public Scheduler dummyScheduler() {
		HashMap<String, List<String>> semSubjects = new HashMap<String, List<String>>() {{
			put("2nd", SAMPLE_SUBJECTS.subList(0, 2));
		}};
		
		return new Scheduler(SAMPLE_ROOMS, SAMPLE_SUBJECTS, semSubjects, SAMPLE_TEACHER_PREFERENCES) {
			@Override
			public Map<DayOfWeek, Map<String, List<Allotment>>> getRoutineByYear() {
				return SAMPLE_ROUTINE;
			}
		};
	}
};
