package routine;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import routine.SimpleSolver.RoutineCell;
import routine.model.DayTimeSlot;
import routine.model.Room;
import routine.model.Subject;
import routine.model.Teacher;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

/**
 * Created by Palash_Das on 29-04-2017.
 */
public class RoutineFormatterTest {
	
	RoutineFormatter formatter;
	
	@Before
	public void setUp() throws Exception {
		formatter = new RoutineFormatter(Arrays.asList(
				makeRoutineCell(new DayTimeSlot(MONDAY, "09:30", "11:10"), "1A", "PD", "CS102", "1001"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "09:30", "11:10"), "2B", "MB", "CS201", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "09:30", "11:10"), "1B", "NC", "CS103", "1002"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "09:30", "12:00"), "2A", "MKN", "CS293", "LAB5"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "11:10", "12:00"), "1A", "PD", "CS102", "1001"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "11:10", "12:00"), "2B", "MB", "CS201", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "11:10", "12:00"), "1B", "TC", "CS104", "1002"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "12:00", "12:50"), "1A", "NC", "CS103", "1001"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "12:00", "12:50"), "2B", "MB", "CS201", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "12:00", "12:50"), "1B", "PD", "CS102", "1002"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "12:00", "12:50"), "2A", "MKN", "CS205", "1002Extra"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "13:30", "15:10"), "1B", "NC", "CS103", "1001"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "13:30", "15:10"), "3A", "PD", "CS302", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "13:30", "16:00"), "1A", "UD", "CS191", "LAB5"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "13:30", "16:00"), "2B", "MKN", "CS293", "LAB5Extra"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "15:10", "16:00"), "1B", "PD", "CS102", "1001"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "16:00", "16:50"), "1A", "NC", "CS103", "1001"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "16:00", "16:50"), "2B", "MKN", "CS205", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(MONDAY, "16:00", "16:50"), "1B", "PD", "CS102", "1002"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "09:30", "10:20"), "2A", "PD", "CS202", "1002"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "09:30", "11:10"), "1A", "TC", "CS104", "1001"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "09:30", "11:10"), "2B", "MKN", "CS205", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "09:30", "12:00"), "1B", "UD", "CS191", "LAB5"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "10:20", "11:10"), "3B", "PD", "CS302", "1002"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "11:10", "12:00"), "1A", "MB", "CS101", "1001"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "11:10", "12:00"), "2B", "NC", "CS203", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "11:10", "12:00"), "3B", "PD", "CS302", "1002"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "12:00", "12:50"), "1A", "MKN", "CS105", "1001"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "12:00", "12:50"), "2B", "NC", "CS203", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "12:00", "12:50"), "1B", "MB", "CS101", "1002"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "12:00", "12:50"), "3A", "PD", "CS302", "1002Extra"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "13:30", "15:10"), "1B", "TC", "CS104", "1001"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "13:30", "15:10"), "2A", "MB", "CS201", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "13:30", "16:00"), "2B", "ARS", "CS292", "LAB5"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "15:10", "16:00"), "1A", "MKN", "CS105", "1001"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "15:10", "16:00"), "3B", "PD", "CS302", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "15:10", "16:50"), "2A", "MB", "CS201", "1002"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "16:00", "16:50"), "1A", "MKN", "CS105", "1001"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "16:00", "16:50"), "2B", "NC", "CS203", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(TUESDAY, "16:00", "16:50"), "3B", "PD", "CS302", "1002Extra"),
				makeRoutineCell(new DayTimeSlot(WEDNESDAY, "09:30", "11:10"), "2B", "TC", "CS204", "1001"),
				makeRoutineCell(new DayTimeSlot(WEDNESDAY, "09:30", "11:10"), "1B", "MKN", "CS105", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(WEDNESDAY, "09:30", "12:00"), "2A", "UD", "CS291", "LAB5"),
				makeRoutineCell(new DayTimeSlot(WEDNESDAY, "11:10", "12:00"), "2B", "NC", "CS203", "1001"),
				makeRoutineCell(new DayTimeSlot(WEDNESDAY, "11:10", "12:00"), "1B", "MKN", "CS105", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(WEDNESDAY, "12:00", "12:50"), "2B", "TC", "CS204", "1001"),
				makeRoutineCell(new DayTimeSlot(WEDNESDAY, "12:00", "12:50"), "1B", "MKN", "CS105", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(WEDNESDAY, "12:00", "12:50"), "2A", "NC", "CS203", "1002"),
				makeRoutineCell(new DayTimeSlot(WEDNESDAY, "16:00", "16:50"), "2B", "TC", "CS204", "1001"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "09:30", "10:20"), "1A", "PD", "CS102", "1001"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "09:30", "11:10"), "2A", "TC", "CS204", "1002"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "10:20", "12:00"), "3B", "MB", "CS301", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "10:20", "12:50"), "1A", "UD", "CS192", "LAB5"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "11:10", "12:00"), "2A", "NC", "CS203", "1001"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "12:00", "12:50"), "2A", "TC", "CS204", "1001"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "12:00", "12:50"), "3B", "MB", "CS301", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "13:30", "14:20"), "2A", "TC", "CS204", "1002Extra"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "13:30", "15:10"), "1A", "NC", "CS103", "1001"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "13:30", "15:10"), "2B", "PD", "CS202", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "14:20", "16:50"), "2A", "ARS", "CS292", "LAB5"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "15:10", "16:50"), "1A", "MB", "CS101", "1001"),
				makeRoutineCell(new DayTimeSlot(THURSDAY, "15:10", "16:50"), "2B", "PD", "CS202", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "09:30", "11:10"), "1A", "TC", "CS104", "1001"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "09:30", "11:10"), "1B", "MB", "CS101", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "09:30", "11:10"), "2A", "MKN", "CS205", "1002"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "09:30", "12:00"), "2B", "UD", "CS291", "LAB5"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "11:10", "12:00"), "1A", "MKN", "CS105", "1001"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "11:10", "12:00"), "1B", "MB", "CS101", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "11:10", "12:50"), "2A", "NC", "CS203", "1002Extra"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "12:00", "12:50"), "1A", "MB", "CS101", "1001"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "12:00", "12:50"), "2B", "MKN", "CS205", "1001Extra"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "12:00", "12:50"), "1B", "PD", "CS102", "1002"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "13:30", "14:20"), "2A", "PD", "CS202", "1001"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "13:30", "16:00"), "1B", "UD", "CS192", "LAB5"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "14:20", "15:10"), "2A", "PD", "CS202", "1001"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "15:10", "16:00"), "2A", "PD", "CS202", "1001"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "16:00", "16:50"), "1B", "TC", "CS104", "1001"),
				makeRoutineCell(new DayTimeSlot(FRIDAY, "16:00", "16:50"), "2A", "MKN", "CS205", "1002")
		));
	}
	
	@Test
	public void displayTeacherWiseRoutine() throws Exception {

	}
	
	private static RoutineCell makeRoutineCell(DayTimeSlot totalSlot,
	                                           String batch,
	                                           String teacherName,
	                                           String subjectCode,
	                                           String roomNo) {
		return new RoutineCell() {{
			this.combinedSlot = totalSlot;
			this.room = new Room(roomNo);
			this.teacher = new Teacher(teacherName, null);
			this.subject = new Subject(subjectCode);
			this.yearAndSection = batch;
		}};
	}
}