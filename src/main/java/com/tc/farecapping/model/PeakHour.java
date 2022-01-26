package com.tc.farecapping.model;

import java.time.LocalTime;
import java.util.Objects;

public class PeakHour {
	
	private LocalTime startTime;

	private LocalTime endTime;
	
	public PeakHour(LocalTime startTime, LocalTime endTime){
		
		this.startTime = startTime;
		this.endTime = endTime;
		
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endTime, startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeakHour other = (PeakHour) obj;
		return Objects.equals(endTime, other.endTime) && Objects.equals(startTime, other.startTime);
	}
}
