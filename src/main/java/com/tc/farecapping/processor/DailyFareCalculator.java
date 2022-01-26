package com.tc.farecapping.processor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tc.farecapping.model.FromToZoneKeyConfig;
import com.tc.farecapping.constants.Constants;
import com.tc.farecapping.model.Journey;
import com.tc.farecapping.model.PeakHour;
import com.tc.farecapping.exceptions.FareCappingException;

public class DailyFareCalculator implements FareCalculator<Double> {
	
	private FareCalculator<Double> fareCalculator = null;

	public void setFareCalculator(FareCalculator<Double> fareCalculator) {
		this.fareCalculator = fareCalculator;
	}

	@Override
	public Double calculate(List<Journey> journeys, Map<String, Double> resultMap, Map<String, FromToZoneKeyConfig> longestJouney) throws FareCappingException{
		Map<String, Double> perDayFare = new HashMap<String, Double>();
		Map<String, FromToZoneKeyConfig> longestJouneyOfTheDay = new HashMap<String, FromToZoneKeyConfig>();
		Double dailyLongestJourneyCap = 0.00;
		try {
			for(Journey journey: journeys) {
				Double perJourneyFare = getJourneyFare(journey);
				FromToZoneKeyConfig fromToZoneKey = new FromToZoneKeyConfig(journey.getFromZone(),journey.getToZone());
				Double dailyLimitCap = Constants.getDailyCapFare().get(fromToZoneKey);
				
				if(dailyLimitCap > dailyLongestJourneyCap) {
					dailyLongestJourneyCap =  dailyLimitCap;
					longestJouneyOfTheDay.put(journey.getDate(),fromToZoneKey);
				}
				
				if(perDayFare.containsKey(journey.getDate())) { 
					Double totalFareForDay = perDayFare.get(journey.getDate())+perJourneyFare;
					perDayFare.put(journey.getDate(), totalFareForDay>dailyLongestJourneyCap?dailyLongestJourneyCap:totalFareForDay);
				}else {
					dailyLongestJourneyCap = 0.00;
					perDayFare.put(journey.getDate(), perJourneyFare);
				}
			}
		}catch (Exception e) {
			throw new FareCappingException(Constants.FAILED_MSG, e);
		}
		 
		return fareCalculator.calculate(journeys, perDayFare, longestJouneyOfTheDay);
	}
	
	private Double getJourneyFare(Journey journey) {
		FromToZoneKeyConfig fromToZoneKey = new FromToZoneKeyConfig(journey.getFromZone(),journey.getToZone());
		return isPeakHours(journey)? Constants.getPeakHourCapFare().get(fromToZoneKey): Constants.getOffPeakHourCapFare().get(fromToZoneKey);
	}
	
	private boolean isPeakHours(Journey journey)
	{
		DayOfWeek dayOfWeek = LocalDate.parse(journey.getDate(), DateTimeFormatter.ofPattern(Constants.DATE_FORMATTER)).getDayOfWeek();
		List<PeakHour> peakHours = Constants.getPeakHours().get(dayOfWeek);
		LocalTime journeyTime = getJourneyTime(journey.getTime());
		for (PeakHour peakHour : peakHours)
		{
			if(journeyTime.isAfter(peakHour.getStartTime()) && journeyTime.isBefore(peakHour.getEndTime()))
				return true;	
		}
		return false;
	}
	
	private LocalTime getJourneyTime(String time)
	{
		int[] timeData = List.of(time.split(":")).stream().mapToInt(Integer::parseInt).toArray();
		return LocalTime.of(timeData[0], timeData[1], 0);
	}

}
