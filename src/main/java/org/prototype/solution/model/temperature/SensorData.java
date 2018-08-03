package org.prototype.solution.model.temperature;

public class SensorData {
	private int id;
	private long reportedTime;
	private float temperature;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public long getReportedTime() {
		return reportedTime;
	}
	
	public void setReportedTime(long reportedTime) {
		this.reportedTime = reportedTime;
	}
	
	public float getTemperature() {
		return temperature;
	}
	
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public String toString() {
		return "SensorData [ " +
				"id = " + id 
				+ ", reportedTime = " + reportedTime
				+ ", temperature = " + temperature 
				+ " ]";
	}
}
