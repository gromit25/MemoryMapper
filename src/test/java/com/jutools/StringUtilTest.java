package com.jutools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.CharBuffer;

import org.junit.jupiter.api.Test;

class StringUtilTest {
	
	@Test
	void test() {
		
		System.out.println(Math.pow(0.0, 0.0));

	}

	@Test
	void testEscape1() {
		try {
			
			String testMsg = "hello world!\\n이름:\\tJohn doe";
			String result = StringUtil.escape(testMsg);
			
			assertEquals("hello world!\n이름:\tJohn doe", result);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	void testEscape2() {
		try {
			
			String testMsg = "\\0\\b\\f\\n\\r\\t\\\\\\\'\\\"";
			String result = StringUtil.escape(testMsg);
			
			assertEquals("\0\b\f\n\r\t\\\'\"", result);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	void testEscape3() {
		try {
			
			// "가나다라마바사"
			String testMsg = "\\uAC00\\uB098\\uB2E4\\uB77C\\uB9C8\\uBC14\\uC0AC"; 
			String result = StringUtil.escape(testMsg);
			
			assertEquals("가나다라마바사", result);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	void testFind1() {
		try {
			
			String testMsg = "hello world!"; 
			int[] result = StringUtil.find(testMsg, "hello", "world");
			
			assertEquals(0, result[0]);
			assertEquals(6, result[1]);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	void testFind2() {
		try {
			
			String testMsg = "aaabbbbbb"; 
			int[] result = StringUtil.find(testMsg, "aabb", "bb");
			
			assertEquals(1, result[0]);
			assertEquals(3, result[1]);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	void testFind3() {
		try {
			
			String testMsg = "aaabbbbbb"; 
			int[] result = StringUtil.find(testMsg, "ccc", "bb");
			
			assertEquals(-1, result[0]);
			assertEquals(3, result[1]);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
}
