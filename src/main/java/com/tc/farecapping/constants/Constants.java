package com.tc.farecapping.constants;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tc.farecapping.model.FromToZoneKeyConfig;
import com.tc.farecapping.model.PeakHour;

public class Constants {
	
	public static final String INVALID_FILE = "Un Supported file format";
	
	public static final String FAILED_MSG = "Fare Calculation process failed";
	
	public static final String NO_JOURNEYS_FOUND = "No Journeys to process";
	
	public static final String NO_RESULT_FROM_DAILY_CALCULATOR = "Failed due to improoer result during Daily Fare Calculation";
	
	public static final String WEEKLY_CALCULATOR_FAILED_MSG = "Weekly Fare Calculation process failed";
	
	public static final String DAILY_CALCULATOR_FAILED_MSG = "Daily Fare Calculation process failed";
	
	public static final String DATE_FORMATTER = "dd/MM/yyyy";
	
	private static final Map<FromToZoneKeyConfig, Double> dailyCapFare = new HashMap<>();
	
	private static final Map<FromToZoneKeyConfig, Double> weeklyCapFare = new HashMap<>();
	
	private static final Map<FromToZoneKeyConfig, Double> peakHourCapFare = new HashMap<>();
	
	private static final Map<FromToZoneKeyConfig, Double> offPeakHourCapFare = new HashMap<>();
	
	private static final Map<DayOfWeek, List<PeakHour>> peakHours = new HashMap<>();
	
	public static Map<DayOfWeek, List<PeakHour>> getPeakHours()
	{
		List<PeakHour> weakDayPeakHours= new ArrayList<PeakHour>();
		List<PeakHour> weakEndPeakHours= new ArrayList<PeakHour>();
		weakDayPeakHours.add(new PeakHour(LocalTime.of(7, 0, 0), LocalTime.of(10, 30, 0)));
		weakDayPeakHours.add(new PeakHour(LocalTime.of(17, 0, 0), LocalTime.of(20, 0, 0)));

		weakEndPeakHours.add(new PeakHour(LocalTime.of(9, 0, 0), LocalTime.of(11, 0, 0)));
		weakEndPeakHours.add(new PeakHour(LocalTime.of(18, 0, 0), LocalTime.of(22, 0, 0)));

		peakHours.put(DayOfWeek.MONDAY, weakDayPeakHours);
		peakHours.put(DayOfWeek.TUESDAY, weakDayPeakHours);
		peakHours.put(DayOfWeek.WEDNESDAY, weakDayPeakHours);
		peakHours.put(DayOfWeek.THURSDAY, weakDayPeakHours);
		peakHours.put(DayOfWeek.FRIDAY, weakDayPeakHours);
		peakHours.put(DayOfWeek.SATURDAY, weakEndPeakHours);
		peakHours.put(DayOfWeek.SUNDAY, weakEndPeakHours);
		return peakHours;
	}
	
	public static Map<FromToZoneKeyConfig, Double> getDailyCapFare()
	{
		dailyCapFare.put(new FromToZoneKeyConfig(1,1), 100.00);
		dailyCapFare.put(new FromToZoneKeyConfig(1,2), 120.00);
		dailyCapFare.put(new FromToZoneKeyConfig(2,1), 120.00);
		dailyCapFare.put(new FromToZoneKeyConfig(2,2), 80.00);
		return dailyCapFare;
	}
	
	public static Map<FromToZoneKeyConfig, Double> getWeeklyCapFare()
	{
		weeklyCapFare.put(new FromToZoneKeyConfig(1,1), 500.00);
		weeklyCapFare.put(new FromToZoneKeyConfig(1,2), 600.00);
		weeklyCapFare.put(new FromToZoneKeyConfig(2,1), 600.00);
		weeklyCapFare.put(new FromToZoneKeyConfig(2,2), 400.00);
		return weeklyCapFare;
	}
	
	public static Map<FromToZoneKeyConfig, Double> getPeakHourCapFare()
	{
		peakHourCapFare.put(new FromToZoneKeyConfig(1,1), 30.00);
		peakHourCapFare.put(new FromToZoneKeyConfig(1,2), 35.00);
		peakHourCapFare.put(new FromToZoneKeyConfig(2,1), 35.00);
		peakHourCapFare.put(new FromToZoneKeyConfig(2,2), 25.00);
		return peakHourCapFare;
	}
	
	public static Map<FromToZoneKeyConfig, Double> getOffPeakHourCapFare()
	{
		offPeakHourCapFare.put(new FromToZoneKeyConfig(1,1), 25.00);
		offPeakHourCapFare.put(new FromToZoneKeyConfig(1,2), 30.00);
		offPeakHourCapFare.put(new FromToZoneKeyConfig(2,1), 30.00);
		offPeakHourCapFare.put(new FromToZoneKeyConfig(2,2), 20.00);
		return offPeakHourCapFare;
	}

}
