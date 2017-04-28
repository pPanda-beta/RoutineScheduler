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
 * Created by Palash_Das on 28-04-2017.
 */

public class TestDataSet1 {
	
	public static final List<String> ROOMS = Arrays.asList("1001", "1002", "1003", "LAB5");
	
	public static final List<String> SUBJECTS = Arrays.asList("CS101", "CS102", "CS103", "CS104", "CS105", "CS191", "CS192", "CS201", "CS202", "CS203", "CS204", "CS205", "CS291", "CS292", "CS293", "CS301", "CS302", "CS391");
	
	public static final Map<String, List<String>> SEM_SUBJECTS = new HashMap<String, List<String>>() {{
		put("1", Arrays.asList("CS101", "CS102", "CS103", "CS104", "CS105", "CS191", "CS192"));
		put("2", Arrays.asList("CS201", "CS202", "CS203", "CS204", "CS205", "CS291", "CS292", "CS293"));
		put("3", Arrays.asList("CS301", "CS302", "CS391"));
	}};
	
	public static final HashMap<String, List<String>> TEACHER_PREFERENCES = new HashMap<String, List<String>>() {{
		put("MB", Arrays.asList("CS101", "CS201", "CS301"));
		put("PD", Arrays.asList("CS102", "CS202", "CS302"));
		put("NC", Arrays.asList("CS103", "CS203"));
		put("TC", Arrays.asList("CS104", "CS204"));
		put("MKN", Arrays.asList("CS105", "CS205", "CS293"));
		put("UD", Arrays.asList("CS191", "CS291", "CS192"));
		put("ARS", Arrays.asList("CS292", "CS391"));
		
	}};
};
