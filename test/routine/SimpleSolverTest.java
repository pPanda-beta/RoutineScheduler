package routine;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import routine.holder.RoomHolder;
import routine.holder.SlotHolder;
import routine.holder.SubjectHolder;
import routine.holder.TeacherHolder;

import static org.junit.Assert.fail;
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
	public void testSmallDataWith10000Round() throws Exception {
		Set<SimpleSolver.RoutineCell> routineCells = solver.evaluateSolutionSerially(10000);
		routineCells.forEach(routineCell -> {
			System.out.println(routineCell.room + "," + routineCell.teacher + "," + routineCell.subject + "," + routineCell.combinedSlot);
		});
	}
	
	@Test
	public void solverReturnsNonOverlappingCells() throws Exception {
		Set<SimpleSolver.RoutineCell> routineCells = solver.evaluateSolutionSerially(Constants.MAXIMUM_NO_OF_ROUNDS);
		
		verifyNonOverlappingCellsFor(routineCells);
	}
	
	@Test
	public void differentSolverReturnsNonOverlappingCells() throws Exception {
		RoomHolder roomHolder = new RoomHolder(TestDataSet1.ROOMS);
		TeacherHolder teacherHolder = new TeacherHolder(TestDataSet1.TEACHER_PREFERENCES);
		
		Set<SimpleSolver.RoutineCell> routineCells = TestDataSet1.SEM_SUBJECTS.values().stream()
				.map(SubjectHolder::new)
				.map(subjectHolder -> new SimpleSolver(new SlotHolder(),
						subjectHolder,
						roomHolder,
						teacherHolder))
				.map(simpleSolver -> simpleSolver.evaluateSolutionSerially(Constants.MAXIMUM_NO_OF_ROUNDS))
				.flatMap(Collection::stream)
				.collect(Collectors.toSet());

//		Set<SimpleSolver.RoutineCell> routineCells = new HashSet<>();
//		TestDataSet1.SEM_SUBJECTS.forEach((year, subjectNames) -> {
//			SimpleSolver solverForYear = new SimpleSolver(new SlotHolder(),
//					new SubjectHolder(subjectNames),
//					roomHolder,
//					teacherHolder);
//
//			routineCells.addAll(solverForYear.evaluateSolutionSerially(Constants.MAXIMUM_NO_OF_ROUNDS));
//		});
		
		verifyNonOverlappingCellsFor(routineCells);
	}
	
	private void verifyNonOverlappingCellsFor(Set<SimpleSolver.RoutineCell> routineCells) {
		Helper.forEachPair(routineCells, (cell1, cell2) -> {
			if (cell1.room.toString().equals(cell2.room.toString())
					&& cell1.combinedSlot.isOverlappingWith(cell2.combinedSlot)) {
				fail("{ " + cell1.subject + "," + cell1.teacher + "," + cell1.combinedSlot + "}"
						+ " is overlapping with "
						+ "{" + cell2.subject + "," + cell2.teacher + "," + cell2.combinedSlot + "}"
						+ " in room " + cell1.room);
			}
		});
	}
}
