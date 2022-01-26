package com.tc.farecapping.processor;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.bean.CsvToBeanBuilder;
import com.tc.farecapping.constants.Constants;
import com.tc.farecapping.exceptions.FareCappingException;
import com.tc.farecapping.exceptions.InvalidFileException;
import com.tc.farecapping.model.Journey;

import java.io.*;

public class CSVFileReader {
	
	public static List<Journey> readFile(MultipartFile file) throws InvalidFileException{
		//List<Journey> journeys = new ArrayList<Journey>();
		Reader reader;
		List<Journey> journeys;
		try {
			reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			journeys = new CsvToBeanBuilder(reader)
	                .withType(Journey.class)
	                .build()
	                .parse();
		} catch (IOException e) {
			throw new InvalidFileException(Constants.INVALID_FILE, e);
		}
		
		return journeys;
		
	}

}
