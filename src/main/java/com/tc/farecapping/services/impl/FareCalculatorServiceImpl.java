package com.tc.farecapping.services.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tc.farecapping.constants.Constants;
import com.tc.farecapping.model.Journey;
import com.tc.farecapping.exceptions.FareCappingException;
import com.tc.farecapping.processor.DailyFareCalculator;
import com.tc.farecapping.processor.FareCalculator;
import com.tc.farecapping.processor.WeeklyFareCalculator;
import com.tc.farecapping.services.FareCalculatorService;

@Service
public class FareCalculatorServiceImpl implements FareCalculatorService {

	@Override
	public Double calculate(List<Journey> journeys) throws FareCappingException
	{
		try
		{
			FareCalculator<Double> dailyFareCalculator = new DailyFareCalculator();
			FareCalculator<Double> weeklyFareCalculator = new WeeklyFareCalculator();
			dailyFareCalculator.setFareCalculator(weeklyFareCalculator);
			
			if(CollectionUtils.isEmpty(journeys)) {
				throw new FareCappingException(Constants.NO_JOURNEYS_FOUND);
			}
			
			return dailyFareCalculator.calculate(journeys, null, null);
		}
		catch (Exception e)
		{
			throw new FareCappingException(Constants.FAILED_MSG, e);
		}
	}

}
