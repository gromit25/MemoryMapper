package com.jutools;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jutools.stat.RTParameter;
import com.jutools.stat.RTStatistic;

public class StatTest {

	@Test
	public void testRTParameter1() throws Exception {
		
		double[] values = {10, 12, 14, 18, 20, 22, 24, 25, 28, 30};
		
		RTParameter stat = new RTParameter();
		for(double value: values) {
			stat.add(value);
		}
		
		assertEquals(10, stat.getCount());
		assertEquals(203.0, stat.getSum(), 0.0);
		assertEquals(20.3, stat.getMean(), 0.0);
		assertEquals(41.21, stat.getVariance(), 0.01);
		assertEquals(6.419, stat.getStd(), 0.01);
		assertEquals(-0.1505, stat.getSkewness(), 0.01);
		assertEquals(-1.1948, stat.getKurtosis(), 0.01);
	}
	
	@Test
	public void testRTStatistic1() throws Exception {
		
		double[] values = {10, 12, 14, 18, 20, 22, 24, 25, 28, 30};
		
		RTStatistic stat = new RTStatistic();
		for(double value: values) {
			stat.add(value);
		}
		
		System.out.println(stat.toString());
		
//		assertEquals(10, stat.getCount());
//		assertEquals(203.0, stat.getSum(), 0.0);
//		assertEquals(20.3, stat.getMean(), 0.0);
//		assertEquals(41.21, stat.getVariance(), 0.01);
//		assertEquals(6.419, stat.getStd(), 0.01);
//		assertEquals(-0.966, stat.getSkewness(), 0.01);
//		assertEquals(-1.1948, stat.getKurtosis(), 0.01);
	}

}
