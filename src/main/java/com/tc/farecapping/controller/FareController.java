package com.tc.farecapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tc.farecapping.model.Journey;
import com.tc.farecapping.processor.CSVFileReader;
import com.tc.farecapping.services.FareCalculatorService;

@RestController
public class FareController {
	
	@Autowired
	FareCalculatorService fareCalculatorService;
	
	@PostMapping("/farecalculate")
	public ResponseEntity<Double> upload(@RequestParam("file") MultipartFile file) {
		
		try {
			List<Journey> journeys = CSVFileReader.readFile(file);
			
			if(CollectionUtils.isEmpty(journeys)) {
				return new ResponseEntity<>(0.00, HttpStatus.NOT_FOUND);
			}
			
			Double result = fareCalculatorService.calculate(journeys);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>(0.00, HttpStatus.BAD_REQUEST);
		}
		
	}
}
