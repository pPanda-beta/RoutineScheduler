package routine;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import routine.holder.RoomHolder;
import routine.holder.SlotHolder;
import routine.holder.SubjectHolder;
import routine.holder.TeacherHolder;

import static routine.SamplesFactory.SAMPLE_ROOMS;
import static routine.SamplesFactory.SAMPLE_SEM_SUBJECTS;
import static routine.SamplesFactory.SAMPLE_TEACHER_PREFERENCES;

/**
 * Created by Palash_Das on 24-04-2017.
 */
public class SimpleSolverTest {
	
	SimpleSolver solver;
	
	@Before
	public void setUp() throws Exception {
		solver = new SimpleSolver(new SlotHolder(),
				new SubjectHolder(SAMPLE_SEM_SUBJECTS.entrySet().iterator().next().getValue()),
				new RoomHolder(SAMPLE_ROOMS),
				new TeacherHolder(SAMPLE_TEACHER_PREFERENCES)
		);
	}
	
	@Test
	public void testSmallDataWith100Round() throws Exception {
		Set<SimpleSolver.RoutineCell> routineCells = solver.evaluateSolution(10000);
		routineCells.forEach(routineCell -> {
			System.out.println(routineCell.room + "," + routineCell.teacher + "," + routineCell.subject + "," + routineCell.combinedSlot);
		});
	}
}