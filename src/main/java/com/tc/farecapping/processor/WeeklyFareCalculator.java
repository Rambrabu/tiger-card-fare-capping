package com.tc.farecapping.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.tc.farecapping.model.FromToZoneKeyConfig;
import com.tc.farecapping.constants.Constants;
import com.tc.farecapping.model.Journey;
import com.tc.farecapping.exceptions.FareCappingException;

public class WeeklyFareCalculator implements FareCalculator<Double> {

	@Override
	public Double calculate(List<Journey> journeys, Map<String, Double> dailyResultMap,
			Map<String, FromToZoneKeyConfig> longestJouney) throws FareCappingException {
		Map<Integer, Double> perWeekFare = new HashMap<Integer, Double>();
		Double weeklyLongestJourneyCap = 0.00;

		if (CollectionUtils.isEmpty(dailyResultMap)) {
			throw new FareCappingException(Constants.NO_RESULT_FROM_DAILY_CALCULATOR);
		}

		try {
			for (Map.Entry<String, Double> dailyResult : dailyResultMap.entrySet()) {
				Double weeklyLimitCap = Constants.getWeeklyCapFare().get(longestJouney.get(dailyResult.getKey()));
				if (weeklyLimitCap > weeklyLongestJourneyCap) {
					weeklyLongestJourneyCap = weeklyLimitCap;
				}

				String journeyDate = dailyResult.getKey();
				Double dailyFare = dailyResult.getValue();

				LocalDate parsedDate = LocalDate.parse(journeyDate,
						DateTimeFormatter.ofPattern(Constants.DATE_FORMATTER));

				if (perWeekFare.containsKey(getWeekOfYear(parsedDate))) {
					Double totalFareForWeek = perWeekFare.get(getWeekOfYear(parsedDate)) + dailyResult.getValue();
					perWeekFare.put(getWeekOfYear(parsedDate),
							totalFareForWeek > weeklyLongestJourneyCap ? weeklyLongestJourneyCap : totalFareForWeek);
				} else {
					weeklyLongestJourneyCap = 0.00;
					perWeekFare.put(getWeekOfYear(parsedDate), dailyFare);
				}
			}
		} catch (Exception e) {
			throw new FareCappingException(Constants.WEEKLY_CALCULATOR_FAILED_MSG, e);
		}

		return perWeekFare.values().stream().reduce(0.0, Double::sum);
	}

	@Override
	public void setFareCalculator(FareCalculator<Double> fareStrategy) {
		// TODO Auto-generated method stub

	}

	private static int getWeekOfYear(LocalDate localDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

}
