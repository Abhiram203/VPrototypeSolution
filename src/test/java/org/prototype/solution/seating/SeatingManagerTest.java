package org.prototype.solution.seating;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.prototype.solution.model.seating.Subject;

public class SeatingManagerTest {
	
	@Test
	public void testSeatingManager_factoryMethodArrangeSeating0() {
		SeatingManager manager = SeatingManager.createSeatingManager(0);
		Subject[][] seating = manager.arrangeSeating();
		assertEquals(2, seating.length);
		assertEquals(0, seating[0].length);
		assertEquals(0, seating[1].length);
	}
	
	@Test
	public void testSeatingManager_constructorArrangeSeating9() {
		SeatingManager manager = new SeatingManager(3, 3, 3);
		Subject[][] seating = manager.arrangeSeating();
		assertEquals(2, seating.length);
		assertEquals(5, seating[0].length);
		assertEquals(5, seating[1].length);
		assertArrayEquals(getExpectedResponseForTotalStudents9(), seating);
	}
	
	@Test
	public void testSeatingManager_factoryMethodArrangeSeating9() {
		SeatingManager manager = SeatingManager.createSeatingManager(9);
		Subject[][] seating = manager.arrangeSeating();
		assertEquals(2, seating.length);
		assertEquals(5, seating[0].length);
		assertEquals(5, seating[1].length);
		assertArrayEquals(getExpectedResponseForTotalStudents9(), seating);
	}
	
	@Test
	public void testSeatingManager_constructorArrangeSeating10() {
		SeatingManager manager = new SeatingManager(4, 3, 3);
		Subject[][] seating = manager.arrangeSeating();
		assertEquals(2, seating.length);
		assertEquals(5, seating[0].length);
		assertEquals(5, seating[1].length);
		assertArrayEquals(getExpectedResponseForTotalStudents10(), seating);
	}
	
	@Test
	public void testSeatingManager_factoryMethodArrangeSeating10() {
		SeatingManager manager = SeatingManager.createSeatingManager(10);
		Subject[][] seating = manager.arrangeSeating();
		assertEquals(2, seating.length);
		assertEquals(5, seating[0].length);
		assertEquals(5, seating[1].length);
		assertArrayEquals(getExpectedResponseForTotalStudents10(), seating);
	}
	
	@Test
	public void testSeatingManager_constructorArrangeSeating11() {
		SeatingManager manager = new SeatingManager(4, 4, 3);
		Subject[][] seating = manager.arrangeSeating();
		assertEquals(2, seating.length);
		assertEquals(6, seating[0].length);
		assertEquals(6, seating[1].length);
		assertArrayEquals(getExpectedResponseForTotalStudents11(), seating);
	}
	
	@Test
	public void testSeatingManager_factoryMethodArrangeSeating11() {
		SeatingManager manager = SeatingManager.createSeatingManager(11);
		Subject[][] seating = manager.arrangeSeating();
		assertEquals(2, seating.length);
		assertEquals(6, seating[0].length);
		assertEquals(6, seating[1].length);
		assertArrayEquals(getExpectedResponseForTotalStudents11(), seating);
	}	
	
	// * * * * * Seating Arrangement * * * * * 
	public Subject[][] getExpectedResponseForTotalStudents9() {
		Subject[] row1Seating = (Subject[]) Arrays.asList(Subject.MATH, Subject.CHEMISTRY, Subject.PHYSICS, Subject.MATH, Subject.CHEMISTRY).toArray();
		Subject[] row2Seating = (Subject[]) Arrays.asList(Subject.PHYSICS, Subject.MATH, Subject.CHEMISTRY, Subject.PHYSICS, null).toArray();
		return buildTwoRowSeating(row1Seating, row2Seating);
	}
	
	public Subject[][] getExpectedResponseForTotalStudents10() {
		Subject[] row1Seating = (Subject[]) Arrays.asList(Subject.MATH, Subject.CHEMISTRY, Subject.PHYSICS, Subject.MATH, Subject.CHEMISTRY).toArray();
		Subject[] row2Seating = (Subject[]) Arrays.asList(Subject.PHYSICS, Subject.MATH, Subject.CHEMISTRY, Subject.PHYSICS, Subject.MATH).toArray();
		return buildTwoRowSeating(row1Seating, row2Seating);
	}
	
	public Subject[][] getExpectedResponseForTotalStudents11() {
		Subject[] row1Seating = (Subject[]) Arrays.asList(Subject.MATH, Subject.CHEMISTRY, Subject.PHYSICS, Subject.MATH, Subject.CHEMISTRY, Subject.PHYSICS).toArray();
		Subject[] row2Seating = (Subject[]) Arrays.asList(Subject.PHYSICS, Subject.MATH, Subject.CHEMISTRY, Subject.PHYSICS, Subject.MATH, null).toArray();
		return buildTwoRowSeating(row1Seating, row2Seating);
	}
	
	public Subject[][] buildTwoRowSeating(Subject[] row1Seating, Subject[] row2Seating) {
		Subject[][] seating = new Subject[2][Math.max(row1Seating.length, row2Seating.length)];
		seating[0] = row1Seating;
		seating[1] = row2Seating;
		return seating;
	}
}
