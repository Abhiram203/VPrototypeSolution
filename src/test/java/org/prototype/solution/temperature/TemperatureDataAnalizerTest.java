package org.prototype.solution.temperature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.prototype.solution.common.PrototypeUtil;

public class TemperatureDataAnalizerTest {

	//* * * * * * * * Test cases for TemperatureDataAnalizer constructor * * * * * * * * 
	
	@Test (expected = IllegalArgumentException.class)
	public void testTemperatureDataAnalizer_nullPropertyPath() throws IOException {
		new TemperatureDataAnalizer(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testTemperatureDataAnalizer_emptyPropertyPath() throws IOException {
		new TemperatureDataAnalizer(PrototypeUtil.EMPTY_STRING);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testTemperatureDataAnalizer_blankPropertyPath() throws IOException {
		new TemperatureDataAnalizer(" ");
	}
	
	//* * * * * * * * Test cases for TemperatureDataAnalizer.retrieveAverageTemperaturesGroupedByEpoch() * * * * * * * *
	
	@Test
	public void testRetrieveAverageTemperaturesGroupedByEpoch_nullTestData() throws IOException {
		TemperatureDataAnalizer dataAnalizer = new TemperatureDataAnalizer("test_prototype.properties");
		List<String> averageTemperatures = dataAnalizer.retrieveAverageTemperaturesGroupedByEpoch(null);
		assertNotNull(averageTemperatures);
		assertEquals(0, averageTemperatures.size());
	}
	
	@Test
	public void testRetrieveAverageTemperaturesGroupedByEpoch_emptyTestData() throws IOException {
		TemperatureDataAnalizer dataAnalizer = new TemperatureDataAnalizer("test_prototype.properties");
		List<String> averageTemperatures = dataAnalizer.retrieveAverageTemperaturesGroupedByEpoch(Collections.emptyList());
		assertNotNull(averageTemperatures);
		assertEquals(0, averageTemperatures.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRetrieveAverageTemperaturesGroupedByEpoch_allCurruptTestData() throws IOException {
		new TemperatureDataAnalizer("test_prototype.properties")
			.retrieveAverageTemperaturesGroupedByEpoch(Arrays.asList("", "1,1,1, 1", ".1,2. 120fa"));		
	}
	
	@Test
	public void testRetrieveAverageTemperaturesGroupedByEpoch_sampleDataEpoch1() throws IOException {
		TemperatureDataAnalizer dataAnalizer = new TemperatureDataAnalizer("test_prototype.properties");
		List<String> averageTemperatures = dataAnalizer.retrieveAverageTemperaturesGroupedByEpoch(retrieveProblemSampleRequest());
		assertNotNull(averageTemperatures);
		assertEquals(retrieveProblemSampleResponse_Epoch1(), averageTemperatures);
	}
	
	@Test
	public void testRetrieveAverageTemperaturesGroupedByEpoch_sampleDataEpoch5() throws IOException {
		TemperatureDataAnalizer dataAnalizer = new TemperatureDataAnalizer("test_prototype_epoch5.properties");
		List<String> averageTemperatures = dataAnalizer.retrieveAverageTemperaturesGroupedByEpoch(retrieveProblemSampleRequest());
		assertNotNull(averageTemperatures);
		assertEquals(retrieveProblemSampleResponse_Epoch5(), averageTemperatures);
	}	
	

	@Test
	public void testRetrieveAverageTemperaturesGroupedByEpoch_customDataEpoch1() throws IOException {
		TemperatureDataAnalizer dataAnalizer = new TemperatureDataAnalizer("test_prototype.properties");
		List<String> averageTemperatures = dataAnalizer.retrieveAverageTemperaturesGroupedByEpoch(retrieveCustomSampleRequest());
		assertNotNull(averageTemperatures);
		assertEquals(retrieveCustomSampleResponse_Epoch1(), averageTemperatures);
	}
	
	@Test
	public void testRetrieveAverageTemperaturesGroupedByEpoch_customDataEpoch2() throws IOException {
		TemperatureDataAnalizer dataAnalizer = new TemperatureDataAnalizer("test_prototype_epoch2.properties");
		List<String> averageTemperatures = dataAnalizer.retrieveAverageTemperaturesGroupedByEpoch(retrieveCustomSampleRequest());
		assertNotNull(averageTemperatures);
		assertEquals(retrieveCustomSampleResponse_Epoch2(), averageTemperatures);
	}
	
	@Test
	public void testRetrieveAverageTemperaturesGroupedByEpoch_testData1Epoch5() throws IOException {
		TemperatureDataAnalizer dataAnalizer = new TemperatureDataAnalizer("test_prototype_epoch5.properties");
		List<String> averageTemperatures = dataAnalizer.retrieveAverageTemperaturesGroupedByEpoch(retrieveCustomSampleRequest());
		assertNotNull(averageTemperatures);
		assertEquals(retrieveCustomSampleResponse_Epoch5(), averageTemperatures);
	}
	
	@Test
	public void testRetrieveAverageTemperaturesGroupedByEpoch_testData1Epoch25() throws IOException {
		TemperatureDataAnalizer dataAnalizer = new TemperatureDataAnalizer("test_prototype_epoch25.properties");
		List<String> averageTemperatures = dataAnalizer.retrieveAverageTemperaturesGroupedByEpoch(retrieveCustomSampleRequest());
		assertNotNull(averageTemperatures);
		assertEquals(retrieveCustomSampleResponse_Epoch25(), averageTemperatures);
	}
	
	
	//* * * * * * * * Sample and Custom request/responses for various epochs  * * * * * * * *
	
	//* * * * * * * * Sample input provided in the challenge and corresponding expected responses* * * * * * * *
	
	private static List<String> retrieveProblemSampleRequest() {
		return Arrays.asList(
				"1,10000,40",
				"1,10002,45", 
				"1,11015,50", 
				"2,10005,42", 
				"2,11051,45", 
				"2,12064,42", 
				"2,13161,42");
	}
	
	private static List<String> retrieveProblemSampleResponse_Epoch1() {
		return Arrays.asList(
				"10000-10999: 42.33",
				"11000-11999: 47.50",
				"12000-12999: 42.00",
				"13000-13999: 42.00");
	}
	
	private static List<String> retrieveProblemSampleResponse_Epoch5() {
		return Arrays.asList("10000-14999: 43.71");
	}
	
	//* * * * * * * * Custom sample request and corresponding expected responses * * * * * * * *
	
	private static List<String> retrieveCustomSampleRequest() {
		return Arrays.asList(
				"1,10000, 40", "1,10002,45", "1,11015,50",
				"1,15000, 40", "1,16002,45", "1,18015,50",
				"1,17000, 40", "1,18002,45", "1,19015,50",
				
				"2,10005,42", "2,11051,45", "2,12064,42",
				"2,15005,42", "2,17051,45",	"2,18064,42",
				"2,12005,42", "2,21051,45", "2,12064,42",
				"2,13161,42");
	}
	
	private static List<String> retrieveCustomSampleResponse_Epoch1() {
		return Arrays.asList(
				"10000-10999: 42.33",
				"11000-11999: 47.50",
				"12000-12999: 42.00",
				"13000-13999: 42.00", 
				"15000-15999: 41.00", 
				"16000-16999: 45.00", 
				"17000-17999: 42.50", 
				"18000-18999: 45.67", 
				"19000-19999: 50.00", 
				"21000-21999: 45.00"
				);
	}
	
	private static List<String> retrieveCustomSampleResponse_Epoch2() {
		return Arrays.asList(
				"10000-11999: 44.40", 
				"12000-13999: 42.00", 
				"14000-15999: 41.00", 
				"16000-17999: 43.33", 
				"18000-19999: 46.75", 
				"20000-21999: 45.00"
				);
	}	
	
	private static List<String> retrieveCustomSampleResponse_Epoch5() {
		return Arrays.asList(
				"10000-14999: 43.33", 
				"15000-19999: 44.33", 
				"20000-24999: 45.00"
				);
	}		

	private static List<String> retrieveCustomSampleResponse_Epoch25() {
		return Arrays.asList("10000-34999: 43.89");
	}	
}
