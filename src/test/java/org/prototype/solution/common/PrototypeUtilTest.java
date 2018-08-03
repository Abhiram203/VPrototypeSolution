package org.prototype.solution.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.prototype.solution.common.PrototypeUtil;

public class PrototypeUtilTest {
	
	//* * * * * * * * Test cases for PrototypeUtil.trimToNotNullString() * * * * * * * * 
	
	@Test
	public void testTrimToNotNullString_nullString() {
		assertEquals(PrototypeUtil.EMPTY_STRING, PrototypeUtil.trimToNotNullString(null));
	}
	
	@Test
	public void testTrimToNotNullString_emptyString() {
		assertEquals(PrototypeUtil.EMPTY_STRING, PrototypeUtil.trimToNotNullString(PrototypeUtil.EMPTY_STRING));
	}
	
	@Test
	public void testTrimToNotNullString_blankString() {
		assertEquals(PrototypeUtil.EMPTY_STRING, PrototypeUtil.trimToNotNullString("   "));
	}
	
	@Test
	public void testTrimToNotNullString_paddedNonEmptyString() {
		String paddedNonEmptyString = "   I'm a padded non-empty string  ";
		assertEquals(paddedNonEmptyString.trim(), PrototypeUtil.trimToNotNullString(paddedNonEmptyString));
	}
	
	@Test
	public void testTrimToNotNullString_nonEmptyString() {
		String nonEmptyString = "I'm not an empty string";
		assertEquals(nonEmptyString, PrototypeUtil.trimToNotNullString(nonEmptyString));
	}
	
	//* * * * * * * * Test cases for PrototypeUtil.isEmptyString() * * * * * * * * 
	
	@Test
	public void testIsEmptyString_nullString() {
		assertTrue(PrototypeUtil.isEmptyString(null));
	}
	
	@Test
	public void testIsEmptyString_emptyString() {
		assertTrue(PrototypeUtil.isEmptyString(PrototypeUtil.EMPTY_STRING));
	}
	
	@Test
	public void testIsEmptyString_blankString() {
		assertTrue(PrototypeUtil.isEmptyString(" "));
	}
	
	@Test
	public void testIsEmptyString_paddedNonEmptyString() {
		assertFalse(PrototypeUtil.isEmptyString("   I'm a padded non-empty string  "));
	}
	
	@Test
	public void testIsEmptyString_nonEmptyString() {
		assertFalse(PrototypeUtil.isEmptyString("I'm not an empty string"));
	}
	
	//* * * * * * * * Test cases for PrototypeUtil.isEmpty() * * * * * * * * 
	
	@Test
	public void testIsEmpty_nullCollection() {
		assertTrue(PrototypeUtil.isEmpty(null));
	}
	
	@Test
	public void testIsEmpty_emptyCollection() {
		assertTrue(PrototypeUtil.isEmpty(Collections.emptyList()));
	}
	
	@Test
	public void testIsEmpty_singleItemCollection() {
		assertFalse(PrototypeUtil.isEmpty(Arrays.asList("Apple")));
	}
	
	@Test
	public void testIsEmpty_notNullCollection() {
		assertFalse(PrototypeUtil.isEmpty(Arrays.asList("Apple", "Orange", "Mango")));
	}
	
	//* * * * * * * * Test cases for PrototypeUtil.parseLong() * * * * * * * *
	
	@Test
	public void testParseLong_null() {
		assertNull(PrototypeUtil.parseLong(null));
	}
	
	@Test
	public void testParseLong_emptyString() {
		assertNull(PrototypeUtil.parseLong(PrototypeUtil.EMPTY_STRING));
	}
	
	@Test
	public void testParseLong_blankString() {
		assertNull(PrototypeUtil.parseLong("   "));
	}
	
	@Test
	public void testParseLong_nonNumericString() {
		assertNull(PrototypeUtil.parseLong("I'm a non numeric string"));
	}
	
	@Test
	public void testParseLong_decimalString() {
		assertNull(PrototypeUtil.parseLong("123.4"));
	}
	
	@Test
	public void testParseLong_paddedNumericString() {
		String paddedNumericString = "   123   ";
		assertEquals(Long.valueOf(paddedNumericString.trim()), PrototypeUtil.parseLong(paddedNumericString));
	}
	
	@Test
	public void testParseLong_numericString() {
		String numericString = "123";
		assertEquals(Long.valueOf(numericString), PrototypeUtil.parseLong(numericString));
	}
}
