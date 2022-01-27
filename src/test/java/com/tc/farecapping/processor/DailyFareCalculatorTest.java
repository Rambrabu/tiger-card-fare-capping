package com.tc.farecapping.processor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.tc.farecapping.controller.FareController;
import com.tc.farecapping.exceptions.FareCappingException;
import com.tc.farecapping.model.Journey;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest(FareController.class)
@ActiveProfiles("test")
public class DailyFareCalculatorTest extends TestCase {
	
	List<Journey> journeys;
	FareCalculator<Double> dailyFareCalculator;

	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		journeys = new ArrayList<Journey>();
		dailyFareCalculator = new DailyFareCalculator();
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

}
