/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routine.model;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Palash_Das
 */
public class AllotmentTest {
	
	public AllotmentTest() {
	}
	
	@Test
	public void testOverlapping() {
		Allotment a1 = new Allotment("", "", "", LocalTime.parse("11:10:00"), 2);
		Allotment a2 = new Allotment("", "", "", LocalTime.parse("12:00:00"), 2);
		
		assertTrue(a1.isOverlappingWith(a2));
	}
	
	@Test
	public void testNonOverlapping() {
		Allotment a1 = new Allotment("", "", "", LocalTime.parse("11:10:00"), 1);
		Allotment a2 = new Allotment("", "", "", LocalTime.parse("12:00:00"), 2);
		
		assertFalse(a1.isOverlappingWith(a2));
	}
	
	@Test
	public void testOverlappingForSameInterval() {
		Allotment a1 = new Allotment("", "", "", LocalTime.parse("11:10:00"), 2);
		Allotment a2 = new Allotment("", "", "", LocalTime.parse("11:10:00"), 2);
		
		assertTrue(a1.isOverlappingWith(a2));
	}
}
