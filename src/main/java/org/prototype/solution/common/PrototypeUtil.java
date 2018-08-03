package org.prototype.solution.common;

import java.util.Collection;
import java.util.logging.Logger;

public class PrototypeUtil {
	public static final String EMPTY_STRING = "";
	
	private static final Logger LOG = Logger.getLogger(PrototypeUtil.class.getName());
	
	/**
	 * Trims the given string to not null value. 
	 * 
	 * @param string - the string to be trimmed. 
	 * @return trimmed string if string is not empty. Otherwise, returns {@code EMPTY_STRING}.
	 */
	public static String trimToNotNullString(String string) {
		return string == null ? PrototypeUtil.EMPTY_STRING : string.trim();
	}
	
	
	/**
	 * Informs if the given string is empty. 
	 * 
	 * @param string - the string to be inspected.
	 * @return {@code true} if the given string is null/empty/blank. Otherwise, returns false.
	 */
	public static boolean isEmptyString(String string) {
		return trimToNotNullString(string).isEmpty();
	}
	
	/**
	 * Informs if the given collection is empty. 
	 * 
	 * @param collection - the collection to be inspected.
	 * @return {@code true} if the given collection is null or empty. Otherwise, returns false.
	 */
	public static <T> boolean isEmpty(Collection<T> collection) {
		return collection == null || collection.isEmpty();
	}
	
	/**
	 * Parses the given string to long value. 
	 * 
	 * @param longString - the string to be parsed.
	 * @return the parsed long value.
	 */
	public static Long parseLong(String longString) {
		if (PrototypeUtil.isEmptyString(longString)) {
			return null;
		}
		
		try {
			return Long.valueOf(PrototypeUtil.trimToNotNullString(longString));
		} catch (NumberFormatException nfe) {
			LOG.severe("Error occurred while parsing string to long. String: " + longString);
		}
		
		return null;
	}
}
