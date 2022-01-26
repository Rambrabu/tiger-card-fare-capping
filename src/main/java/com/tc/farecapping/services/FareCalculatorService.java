package com.tc.farecapping.services;

import java.util.List;

import com.tc.farecapping.model.Journey;
import com.tc.farecapping.exceptions.FareCappingException;

public interface FareCalculatorService {
	
	public Double calculate(List<Journey> journeys) throws FareCappingException;

}
