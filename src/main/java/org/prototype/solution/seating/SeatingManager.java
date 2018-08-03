package org.prototype.solution.seating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.prototype.solution.model.seating.StudentsGroup;
import org.prototype.solution.model.seating.Subject;

public class SeatingManager {
	private final int MAX_COLUMNS;
	private final int MAX_ROWS;
	
	private final List<StudentsGroup> groups = new ArrayList<>();
	private final Subject[][] seating;
	private final int[] currentSeat = new int[2];
	private final int totalNumberOfStudents;
	
	public SeatingManager(int numberOfMathsStudents, int numberOfPhysicsStudents, int numberOfChemistryStudents) {
		totalNumberOfStudents = numberOfMathsStudents + numberOfPhysicsStudents + numberOfChemistryStudents;
		initializeGroups(numberOfMathsStudents, numberOfPhysicsStudents, numberOfChemistryStudents);
		
		MAX_ROWS = 2;
		MAX_COLUMNS = (int) Math.ceil(((double) totalNumberOfStudents)/MAX_ROWS);
		
		seating = new Subject[2][MAX_COLUMNS];
		setNextAvailableSeat(-1, -1);
	}
	
	/**
	 * This is a factory method to create the Seating Manager based on the total number of students.
	 * Allocates the number of students to each group based on total number of students. 
	 * 
	 * @param totalNumberOfStudents - the total number of students.
	 * @returns the seating manager
	 */
	public static SeatingManager createSeatingManager(int totalNumberOfStudents) {
		int numberOfStudentsPerGroup = totalNumberOfStudents / 3;
		int additionalStudents = totalNumberOfStudents % 3;
		int numberOfMathStudents = numberOfStudentsPerGroup + (additionalStudents > 0 ? 1 : 0);
		int numberOfPhysicsStudents = numberOfStudentsPerGroup + (additionalStudents > 1 ? 1 : 0);
		return new SeatingManager(numberOfMathStudents, numberOfPhysicsStudents, numberOfStudentsPerGroup);
	}

	/**
	 * Initializes the students groups. 
	 * 
	 * @param numberOfMathStudents      - the number of Math students
	 * @param numberOfPhysicsStudents   - the number of Physics students
	 * @param numberOfChemistryStudents - the number of Chemistry students
	 */
	private void initializeGroups(int numberOfMathStudents, int numberOfPhysicsStudents, int numberOfChemistryStudents) {
		if (Math.abs(numberOfPhysicsStudents - numberOfMathStudents) > 1 
				|| Math.abs(numberOfChemistryStudents - numberOfMathStudents) > 1
				|| Math.abs(numberOfPhysicsStudents - numberOfChemistryStudents) > 1) {
			throw new IllegalArgumentException("Unsupported proportions of the students received.");
		}
		
		groups.add(new StudentsGroup(Subject.MATH, numberOfMathStudents));
		groups.add(new StudentsGroup(Subject.PHYSICS, numberOfPhysicsStudents));
		groups.add(new StudentsGroup(Subject.CHEMISTRY, numberOfChemistryStudents));
		
		// sorting in descending order
		groups.sort((a, b) -> b.getNumberOfUnseatedStudents() - a.getNumberOfUnseatedStudents()); 
	}
	
	/**
	 * Arranges the Seating based on the number of students in each group.
	 * 
	 * @return the arranged seating order.
	 */
	public Subject[][] arrangeSeating() {
		int groupIndex = 0;
		int numberOfGroups = groups.size();
		
		for (int index = 0; index < totalNumberOfStudents; index++) {
			StudentsGroup group = nextGroupWithUnseatedStudents(groupIndex);
			if (group == null) {
				throw new IllegalStateException("No group left with unseated students, but attempted to fill next seat.");
			}
			
			moveToNextSeat();
			seating[currentSeat[0]][currentSeat[1]] = group.getSubject();
			groupIndex = groups.indexOf(group) + 1;
			if (groupIndex >= numberOfGroups) {
				groupIndex = 0;
			}
		}		
		
		for (Subject[] seatingRow: seating) {
			System.out.println(Arrays.asList(seatingRow));
		}

		return seating;
	}
	
	/**
	 * Retrieves the next group with un-seated students. 
	 * 
	 * @param groupIndex - the current group index to start the search.
	 * @return the next group with un-seated students.
	 */
	private StudentsGroup nextGroupWithUnseatedStudents(int groupIndex) {
		int numberOfGroups = groups.size();
		if (groupIndex < 0 || groupIndex >= numberOfGroups) {
			return null;
		}
		
		int originalGroupIndex = groupIndex;
		StudentsGroup group = null;
		do {
			group = groups.get(groupIndex);
			if (group.getNumberOfUnseatedStudents() == 0) {
				groupIndex = (groupIndex == numberOfGroups - 1) ? groupIndex + 1 : 0;
			}
		} while(groupIndex != originalGroupIndex && groupIndex < numberOfGroups);
		
		
		return group;
	}
	
	/**
	 * Sets the current seat position. 
	 * 
	 * @param row    - the row index number of the current seat
	 * @param column - the column index number of the current seat
	 */
	private void setNextAvailableSeat(int row, int column) {
		currentSeat[0] = row;
		currentSeat[1] = column;
	}
	
	/**
	 * Moves the current seat to the next seat. 
	 */
	private void moveToNextSeat() {
		if (MAX_ROWS == 0 || MAX_COLUMNS == 0) {
			throw new IllegalStateException("Hall Capicity is zero. Zero seats available.");
		}
		
		int currentRow = currentSeat[0];
		int currentColumn = currentSeat[1];
		
		if (currentRow == -1 && currentColumn == -1) { // initialize to the first seat
			setNextAvailableSeat(0, 0);
		} else if (currentRow < MAX_ROWS - 1) { // move to next row, if the current row is not the last row
			setNextAvailableSeat(currentRow + 1, currentColumn);
		} else if (currentColumn < MAX_COLUMNS - 1) { // move to next column, if the current column is not the last column
			setNextAvailableSeat(0, currentColumn + 1);			
		} else {// house full
			throw new IllegalStateException("Reached maximum seating capacity. No more seats left.");
		}
	}
}

