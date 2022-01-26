package com.tc.farecapping.services.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.tc.farecapping.controller.FareController;
import com.tc.farecapping.exceptions.FareCappingException;
import com.tc.farecapping.model.Journey;
import com.tc.farecapping.processor.DailyFareCalculator;
import com.tc.farecapping.processor.FareCalculator;
import com.tc.farecapping.processor.WeeklyFareCalculator;

@RunWith(SpringRunner.class)
@WebMvcTest(FareController.class)
@ActiveProfiles("test")
class FareCalculatorServiceImplTest {
	
	@InjectMocks
	FareCalculatorServiceImpl fareCalculatorService;
	
	FareCalculator<Double> dailyFareCalculator = new DailyFareCalculator();
	FareCalculator<Double> weeklyFareCalculator = new WeeklyFareCalculator();
	
	List<Journey> journeys;

	@Before
	public void setUp() throws Exception {
		journeys = new ArrayList<Journey>();
		dailyFareCalculator.setFareCalculator(weeklyFareCalculator);
	}

	@Test
	public void testCalculate() throws FareCappingException {
		Journey journey1 = new Journey("24/01/2022","10:20",2,1);
		Journey journey2 = new Journey("24/01/2022","10:45",1,1);
		journeys.add(journey1);
		journeys.add(journey2);
		Double result = dailyFareCalculator.calculate(journeys, null, null);
		assertTrue(result == 60);
	}
	
	@Test(expected = FareCappingException.class)
	public void testCalculateWithNoJourney() throws FareCappingException {
		Double result = dailyFareCalculator.calculate(journeys, null, null);
		assertTrue(result == 60);
	}

}
