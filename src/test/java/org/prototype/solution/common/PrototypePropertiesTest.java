package org.prototype.solution.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.prototype.solution.common.PrototypeProperties;
import org.prototype.solution.common.PrototypeUtil;

public class PrototypePropertiesTest {
	
	//* * * * * * * * Test cases for PrototypeProperties constructor * * * * * * * * 
	
	@Test (expected = IllegalArgumentException.class)
	public void testPrototypeProperties_nullFilePath() throws IOException {
		new PrototypeProperties(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPrototypeProperties_emptyFilePath() throws IOException {
		new PrototypeProperties(PrototypeUtil.EMPTY_STRING);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPrototypeProperties_blankFilePath() throws IOException {
		new PrototypeProperties(PrototypeUtil.EMPTY_STRING);
	}
	
	@Test
	public void testPrototypeProperties_paddedFilePath() throws IOException {
		PrototypeProperties properties = new PrototypeProperties("    test_prototype.properties   ");
		assertNotNull(properties.keySet());
		assertEquals(2, properties.keySet().size());
	}
	
	@Test
	public void testPrototypeProperties_correctFilePath() throws IOException {
		PrototypeProperties properties = new PrototypeProperties("test_prototype.properties");
		assertNotNull(properties.keySet());
		assertEquals(2, properties.keySet().size());
	}
	
	//* * * * * * * * Test cases for PrototypeProperties.getLongProperty() * * * * * * * * 
	
	@Test
	public void testGetLongProperty_nullString() throws IOException {
		PrototypeProperties properties = new PrototypeProperties("test_prototype.properties");
		long defaultValue = 2; 
		assertEquals(defaultValue, properties.getLongProperty(null, defaultValue));
	}
	
	@Test
	public void testGetLongProperty_emptyString() throws IOException {
		PrototypeProperties properties = new PrototypeProperties("test_prototype.properties");
		long defaultValue = 2; 
		assertEquals(defaultValue, properties.getLongProperty(PrototypeUtil.EMPTY_STRING, defaultValue));
	}
	
	@Test
	public void testGetLongProperty_blankString() throws IOException {
		PrototypeProperties properties = new PrototypeProperties("test_prototype.properties");
		long defaultValue = 2; 
		assertEquals(defaultValue, properties.getLongProperty("   ", defaultValue));		
	}
	
	@Test
	public void testGetLongProperty_nonNumericProperty() throws IOException {
		PrototypeProperties properties = new PrototypeProperties("test_prototype.properties");
		long defaultValue = 2; 
		assertEquals(defaultValue, properties.getLongProperty("org.prototype.solution.test.nonnumeric", defaultValue));
	}
	
	@Test
	public void testGetLongProperty_paddedNumericProperty() throws IOException {
		PrototypeProperties properties = new PrototypeProperties("test_prototype.properties");
		long defaultValue = 2; 
		assertEquals(1, properties.getLongProperty("   org.prototype.solution.temperature.sensor.data.epoch   ", defaultValue));		
	}
	
	@Test
	public void testGetLongProperty_numericProperty() throws IOException {
		PrototypeProperties properties = new PrototypeProperties("test_prototype.properties");
		long defaultValue = 2; 
		assertEquals(1, properties.getLongProperty(PrototypeProperties.TEMPERATURE_SENSOR_EPOCH_KEY, defaultValue));			
	}
}
