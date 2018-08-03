package org.prototype.solution.common;

import java.io.IOException;
import java.util.Properties;

public class PrototypeProperties extends Properties{
	public static final String TEMPERATURE_SENSOR_EPOCH_KEY = "org.prototype.solution.temperature.sensor.data.epoch";
	
	private static final long serialVersionUID = -4597213544579842695L;
	
	public PrototypeProperties(String propertiesFilePath) throws IOException {
		if (PrototypeUtil.isEmptyString(propertiesFilePath)) {
			throw new IllegalArgumentException("Provided Property File Path is null or empty");
		}
		
		this.load(PrototypeProperties.class.getClassLoader().getResourceAsStream(propertiesFilePath.trim()));
	}
	
	/**
	 * Gets the long property value corresponding to the given property key. If the long value is not found for the given property key, default value 
	 * is returned. 
	 * 
	 * @param propertyKey  - the property key whose corresponding long value is to be retrieved.
	 * @param defaultValue - the default value to be returned. 
	 * @return the long property value. 
	 */
	public long getLongProperty(String propertyKey, long defaultValue) {
		Long propertyValue = PrototypeUtil.parseLong(getProperty(PrototypeUtil.trimToNotNullString(propertyKey)));
		return propertyValue != null ? propertyValue.longValue() : defaultValue; 
	}
}