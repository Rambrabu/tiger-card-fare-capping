package com.tc.farecapping.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.tc.farecapping.exceptions.FareCappingException;
import com.tc.farecapping.processor.CSVFileReader;
import com.tc.farecapping.services.FareCalculatorService;

@RunWith(SpringRunner.class)
@WebMvcTest(FareController.class)
@ActiveProfiles("test")
public class FareControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	FareCalculatorService fareCalculatorService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUpload() throws Exception {
		StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("24/01/2022,10:20,2,1\n");
        csvBuilder.append("24/01/2022,10:45,1,1");
        InputStream is = new ByteArrayInputStream(csvBuilder.toString().getBytes());

        MultipartFile mockFile = new MockMultipartFile("fare", "fare.csv", "plain/text", is);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/farecalculate").file((MockMultipartFile) mockFile))
        .andExpect(status().isOk());
	}
	
	@Test
	public void testUploadResult() throws IOException, FareCappingException {
		StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("24/01/2022,10:20,2,1\n");
        csvBuilder.append("24/01/2022,10:45,1,1");
        InputStream is = new ByteArrayInputStream(csvBuilder.toString().getBytes());

        MultipartFile mockFile = new MockMultipartFile("fare", "fare.csv", "plain/text", is);
        Double result = fareCalculatorService.calculate(CSVFileReader.readFile(mockFile));
        assertTrue(result == 60);
	}
	
	@Test
	public void testUploadNoJourneyFound() throws Exception {
		StringBuilder csvBuilder = new StringBuilder();
        InputStream is = new ByteArrayInputStream(csvBuilder.toString().getBytes());

        MultipartFile mockFile = new MockMultipartFile("fare", "fare.csv", "plain/text", is);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/farecalculate").file((MockMultipartFile) mockFile))
        .andExpect(status().isNotFound());
	}

}
