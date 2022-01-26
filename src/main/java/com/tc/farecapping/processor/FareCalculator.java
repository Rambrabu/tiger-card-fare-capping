package com.tc.farecapping.processor;

import java.util.List;
import java.util.Map;

import com.tc.farecapping.model.FromToZoneKeyConfig;
import com.tc.farecapping.model.Journey;
import com.tc.farecapping.exceptions.FareCappingException;


public interface FareCalculator<T> {
	
	public T calculate(List<Journey> journeys, Map<String, Double> resultMap , Map<String, FromToZoneKeyConfig> longestJouney) throws FareCappingException;
	
	public void setFareCalculator(FareCalculator<T> fareStrategy);

}
