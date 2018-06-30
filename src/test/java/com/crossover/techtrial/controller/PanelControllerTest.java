package com.crossover.techtrial.controller;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * PanelControllerTest class will test all APIs in PanelController.java.
 * @author Crossover
 *
 */

	@RunWith(SpringJUnit4ClassRunner.class)
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	public class PanelControllerTest {
	  
	  MockMvc mockMvc;
	  
	  @Mock
	  private PanelController panelController;
	  
	  @Autowired
	  private TestRestTemplate template;
	
	  @Before
	  public void setup() throws Exception {
	    mockMvc = MockMvcBuilders.standaloneSetup(panelController).build();
	  }
	
	  @Test
	  public void testPanelShouldBeRegistered() throws Exception {
	    HttpEntity<Object> panel = getHttpEntity(
	        "{\"serial\": \"test567890123457\", \"longitude\": \"54.123232\"," 
	            + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
	    ResponseEntity<Panel> response = template.postForEntity(
	        "/api/register", panel, Panel.class);
	    Assert.assertEquals(202,response.getStatusCode().value());
	    
	  }
	  
	  @Test
	  public void testPanelShouldNotBeRegisteredBadSerialInput() throws Exception {
		  HttpEntity<Object> panel = getHttpEntity(
			        "{\"serial\": \"12345678900987654\", \"longitude\": \"54.123232\"," 
			            + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
		  ResponseEntity<Panel> response = template.postForEntity(
			        "/api/register", panel, Panel.class);
		  Assert.assertEquals(400,response.getStatusCode().value());
		  
		  
		  
		  HttpEntity<Object> panel2 = getHttpEntity(
			        "{\"serial\": \"123456789000001\", \"longitude\": \"54.123232\"," 
			            + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }"); 
		  ResponseEntity<Panel> response2 = template.postForEntity(
			        "/api/register", panel2, Panel.class);
		  Assert.assertEquals(400,response2.getStatusCode().value());
		    
		  
		  
		  HttpEntity<Object> panel3 = getHttpEntity(
		            "{\"serial\": \"test567890123457\", \"longitude\": \"54.123232\"," 
		                + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
		  ResponseEntity<Panel> response3 = template.postForEntity(
		            "/api/register", panel3, Panel.class);
		  Assert.assertEquals(400,response3.getStatusCode().value());
		  
		  
	  }
	  
	  @Test
	  public void testPanelShouldNotBeRegisteredBadLonghitudeInput() throws Exception {
		  HttpEntity<Object> panel = getHttpEntity(
			        "{\"serial\": \"1234567890000002\", \"longitude\": \"54.1232324\"," 
			            + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
		  ResponseEntity<Panel> response = template.postForEntity(
			        "/api/register", panel, Panel.class);
		  Assert.assertEquals(400,response.getStatusCode().value());
		  
		  
		  
		  HttpEntity<Object> panel2 = getHttpEntity(
			        "{\"serial\": \"1234567890000003\", \"longitude\": \"180.123232\"," 
			            + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
		  ResponseEntity<Panel> response2 = template.postForEntity(
			        "/api/register", panel2, Panel.class);
		  Assert.assertEquals(400,response2.getStatusCode().value());
		  
		  
		  
		  HttpEntity<Object> panel3 = getHttpEntity(
			        "{\"serial\": \"1234567890000004\", \"longitude\": \"-180.123232\"," 
			            + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
		  ResponseEntity<Panel> response3 = template.postForEntity(
			        "/api/register", panel3, Panel.class);
		  Assert.assertEquals(400,response3.getStatusCode().value());
	  }
	  
	  @Test
	  public void testPanelShouldNotBeRegisteredBadLatitudeInput() throws Exception {
		  HttpEntity<Object> panel = getHttpEntity(
			        "{\"serial\": \"1234567890000005\", \"longitude\": \"54.123232\"," 
			            + " \"latitude\": \"54.1232232\",\"brand\":\"tesla\" }");
		  ResponseEntity<Panel> response = template.postForEntity(
			        "/api/register", panel, Panel.class);
		  Assert.assertEquals(400,response.getStatusCode().value());
		  
		  
		  
		  HttpEntity<Object> panel2 = getHttpEntity(
			        "{\"serial\": \"1234567890000006\", \"longitude\": \"54.123232\"," 
			            + " \"latitude\": \"90.123232\",\"brand\":\"tesla\" }");
		  ResponseEntity<Panel> response2 = template.postForEntity(
			        "/api/register", panel2, Panel.class);
		  Assert.assertEquals(400,response2.getStatusCode().value());
		  
		  
		  
		  HttpEntity<Object> panel3 = getHttpEntity(
			        "{\"serial\": \"1234567890000007\", \"longitude\": \"54.123232\"," 
			            + " \"latitude\": \"-90.123232\",\"brand\":\"tesla\" }");
		  ResponseEntity<Panel> response3 = template.postForEntity(
			        "/api/register", panel3, Panel.class);
		  Assert.assertEquals(400,response3.getStatusCode().value());
	  }
	  
	  @Test
	  public void testHourlyElectricityShouldBeSaved() throws Exception{
			 HourlyElectricity hourlyElectricity = new HourlyElectricity();
			 hourlyElectricity.setGeneratedElectricity((long) 628);
			 hourlyElectricity.setReadingAt(LocalDateTime.of(2018, Month.JUNE, 28, 00, 00, 00));

			 ResponseEntity<HourlyElectricity> responseElectricity = template.postForEntity(
				        "/api/panels/"+"test567890123457"+"/hourly", hourlyElectricity, HourlyElectricity.class);
			 Assert.assertEquals(200,responseElectricity.getStatusCode().value());
			  
	  }
	  
	  @Test
	  public void testHourlyElectricityShouldNotBeSaved() throws Exception{
			 HourlyElectricity hourlyElectricity = new HourlyElectricity();
			 hourlyElectricity.setGeneratedElectricity((long) 628);
			 hourlyElectricity.setReadingAt(LocalDateTime.of(2018, Month.JUNE, 28, 00, 00, 00));
			 
			 ResponseEntity<HourlyElectricity> responseElectricity = template.postForEntity(
				        "/api/panels/"+"test000000000001"+"/hourly", hourlyElectricity, HourlyElectricity.class);
			 Assert.assertEquals(404,responseElectricity.getStatusCode().value());
			  
	  }

	  @Test
	  public void testShouldGetHourlyElectricityByPanelSerial() throws Exception {
		  ResponseEntity<HourlyElectricity> response = template.getForEntity("/api/panels/"+"test567890123457"+"/hourly", HourlyElectricity.class);
		  Assert.assertEquals(200,response.getStatusCode().value()); 
	  }
	  
	  @Test
	  public void testShouldNotGetHourlyElectricityByPanelSerial() throws Exception {
		  ResponseEntity<HourlyElectricity> response = template.getForEntity("/api/panels/"+"test00000000001"+"/hourly", HourlyElectricity.class);
		  Assert.assertEquals(404,response.getStatusCode().value()); 
	  }
	  
	  @Test
	  public void testShouldGetDailyElectricityByPanel() throws Exception{
		  ResponseEntity<DailyElectricity[]> resopnse = template.getForEntity("/api/panels/"+"test567890123457"+"/daily", DailyElectricity[].class); 
		  Assert.assertEquals(200, resopnse.getStatusCode().value());
	  }
	  
	  @Test
	  public void testShouldNotGetDailyElectricityByPanel() throws Exception{
		  ResponseEntity<DailyElectricity[]> resopnse = template.getForEntity("/api/panels/"+"testPanelX000001"+"/daily", DailyElectricity[].class);
		  Assert.assertEquals(404, resopnse.getStatusCode().value());
	  }
	  
  private HttpEntity<Object> getHttpEntity(Object body) { 
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<Object>(body, headers);
  }
}
