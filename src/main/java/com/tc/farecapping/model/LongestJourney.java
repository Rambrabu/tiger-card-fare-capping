package com.tc.farecapping.model;

import java.util.Objects;

public class LongestJourney {
	
	private Integer startZone;
	
	private Integer endZone;
	
	public LongestJourney(Integer startZone, Integer endZone){
		this.startZone = startZone;
		this.endZone = endZone;
	}
	
	public Integer getStartZone() {
		return startZone;
	}

	public void setStartZone(Integer startZone) {
		this.startZone = startZone;
	}

	public Integer getEndZone() {
		return endZone;
	}

	public void setEndZone(Integer endZone) {
		this.endZone = endZone;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(endZone, startZone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LongestJourney other = (LongestJourney) obj;
		return Objects.equals(endZone, other.endZone) && Objects.equals(startZone, other.startZone);
	}
	
}
