package com.jutools;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PublishUtilTest {

	@Test
	public void testConsole1() throws Exception {
		
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("test message 1");
		messages.add("test message 2");
		
		Map<String, Object> values = new HashMap<>();
		values.put("messages", messages);
		
		try {
			
			File formatFile = new File("resources/publisher/testformat.xml");
			PublishUtil.publishToConsole(formatFile, values);
			
			assertTrue(true);
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			fail("fail");
		}
	}
	
	@Test
	public void testExcel1() throws Exception {
		
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("test message 1");
		messages.add("test message 2");
		
		Map<String, Object> values = new HashMap<>();
		values.put("messages", messages);
		
		try {
			
			File formatFile = new File("resources/publisher/testExcelformat.xml");
			File outFile = new File("C:\\data\\publish\\test.xlsx");
			PublishUtil.publishToExcel(formatFile, outFile, values);
			
			assertTrue(true);
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			fail("fail");
		}
	}

}
