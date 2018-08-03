package org.prototype.solution.model.seating;

public class StudentsGroup {
	private Subject subject;
	private int numberOfUnseatedStudents;
	
	public StudentsGroup(Subject subject, int numberOfStudents ) {
		this.subject = subject;
		this.numberOfUnseatedStudents = numberOfStudents;
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public int getNumberOfUnseatedStudents() {
		return numberOfUnseatedStudents;
	}
	
	public void setNumberOfUnseatedStudents(int numberOfUnseatedStudents) {
		this.numberOfUnseatedStudents = numberOfUnseatedStudents;
	}
	
	@Override
	public String toString() {
		return "StudentsGroup [ "
				+ "subject = " + subject 
				+ ", numberOfStudents = " + numberOfUnseatedStudents 
				+ " ]";
	}
}
