package com.tc.farecapping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.opencsv.bean.CsvBindByPosition;
import com.tc.farecapping.validation.Zone;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Journey {
	
	public Journey(String date, String time, Integer fromZone, Integer toZone) {
		super();
		this.date = date;
		this.time = time;
		this.fromZone = fromZone;
		this.toZone = toZone;
	}
	
	@CsvBindByPosition(position = 0)
	private String date;

	@CsvBindByPosition(position = 1)
	private String time;
	
	@CsvBindByPosition(position = 2)
	@Zone
	private Integer fromZone;

	@CsvBindByPosition(position = 3)
	@Zone
	private Integer toZone;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getFromZone() {
		return fromZone;
	}

	public void setFromZone(Integer fromZone) {
		this.fromZone = fromZone;
	}

	public Integer getToZone() {
		return toZone;
	}

	public void setToZone(Integer toZone) {
		this.toZone = toZone;
	}
	
}
