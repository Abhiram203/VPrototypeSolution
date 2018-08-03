package org.prototype.solution.temperature;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.prototype.solution.common.PrototypeProperties;
import org.prototype.solution.common.PrototypeUtil;
import org.prototype.solution.model.temperature.SensorData;

public class TemperatureDataAnalizer {
	private static final String COMMA = ",";
	private static final long DEFAULT_EPOCH_PERIOD = 1000;	
	private final PrototypeProperties properties;
	
	public TemperatureDataAnalizer(String propertyFilePath) throws IOException {
		properties = new PrototypeProperties(propertyFilePath);
	}
	
	/**
	 * Retrieves the average temperatures grouped by epoch. 
	 * 
	 * @param sensorData - the sensor data list
	 * @return the average temperatures of each epoch. 
	 */
	public List<String> retrieveAverageTemperaturesGroupedByEpoch(List<String> sensorData) {
		
		return mapAverageTemperaturesGroupedByEpoch(sensorData, properties.getLongProperty(PrototypeProperties.TEMPERATURE_SENSOR_EPOCH_KEY, DEFAULT_EPOCH_PERIOD))
				.entrySet()
				.stream()
				.map(TemperatureDataAnalizer::toOutputFormat)
				.collect(Collectors.toList());
	}
	
	
	/**
	 * maps the average temperature from the given sensor data grouped by the configured epoch. 
	 * 
	 * @param sensorData  - the sensor data whose average temperature is to be calculated.
	 * @param epochPeriod - the epoch period  
	 * @return map keyed by epoch valued by its average temperature. 
	 */
	private static Map<String, Double> mapAverageTemperaturesGroupedByEpoch (List<String> sensorData, long epochPeriod) {
		// retrieve starting epoch from sensor data
		long initalEpochStartTime = parseSensorData(sensorData).findFirst().map(SensorData::getReportedTime).orElseGet(() -> 0L) / 1000 * 1000; 
		
		return parseSensorData(sensorData)
				.collect(Collectors.groupingBy(
							data -> retrieveEpoch(data, initalEpochStartTime, epochPeriod), 
							LinkedHashMap::new, 
							Collectors.averagingDouble(SensorData::getTemperature)));
	}
	
	/**
	 * Parses the given list of {@link String raw sensor data} to a list of {@link SensorData sensor data}.
	 * 
	 * @param sensorData - list of raw sensor data
	 * @return the parsed sensor data.
	 */
	private static Stream<SensorData> parseSensorData(List<String> sensorData) {
		if (PrototypeUtil.isEmpty(sensorData)) {
			return Stream.empty();
		}
		
		return sensorData.stream()
				.map(TemperatureDataAnalizer::parseSensorData)
				.filter(Objects::nonNull)
				.sorted((a, b) -> Long.compare(a.getReportedTime(), b.getReportedTime()));
	}
	
	/**
	 * Parses the given raw data string to {@link SensorData sensor data}.
	 * 
	 * @param rawData - the raw data to be parsed.
	 * @return the parsed {@code sensor data}.
	 */
	private static SensorData parseSensorData (String rawData) {
		if (PrototypeUtil.isEmptyString(rawData)) {
			return null;
		}
		
		String[] rawDataParts = rawData.trim().split(COMMA);
		if (rawDataParts.length != 3) {
			throw new IllegalArgumentException("Invalid sensor data received. Input string: " + rawData);
		}
		
		SensorData sensorData = new SensorData();
		sensorData.setId(Integer.parseInt(rawDataParts[0].trim()));
		sensorData.setReportedTime(Long.parseLong(rawDataParts[1].trim()));
		sensorData.setTemperature(Float.parseFloat(rawDataParts[2].trim()));
		
		return sensorData;
	}
	
	/**
	 * Retrieves the epoch for the given {@link SensorData sensor data}.
	 * 
	 * @param sensorData            - the sensor data whose epoch is to be retrieved.
	 * @param initialEpochStartTime - the start time of the initial epoch.
	 * @param epochPeriod           - the epoch period/duration.
	 * @return the retrieved epoch.
	 */
	private static String retrieveEpoch(SensorData sensorData, long initialEpochStartTime, long epochPeriod) {
		// temperature 
		long temperatuerReportedTime = sensorData.getReportedTime() / 1000 * 1000;
		
		long epochStartTime = ((temperatuerReportedTime  - initialEpochStartTime) / epochPeriod) * epochPeriod + initialEpochStartTime;
		
		return epochStartTime + "-" + (epochStartTime + epochPeriod - 1);
	}
	
	/**
	 * Formats the given entry to the desired output format.
	 *  
	 * @param entry - the entry to be formated.
	 * @return the formatted output entry. 
	 */
	private static String toOutputFormat(Entry<String, Double> entry) {		
		return entry.getKey() + ": " + String.format("%.2f", entry.getValue());
	}	
}
