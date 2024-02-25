package com.jutools;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import com.jutools.mathexp.MathResult;

/**
 * MathUtil 클래스의 테스트 케이스
 * 
 * @author jmsohn
 */
public class MathUtilTest {

	@Test
	public void testCalculate1_1() {
		try {
			
			double result = MathUtil.calculate("-123.45");
			assertEquals(-123.45, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate1_2() {
		try {
			
			double result = MathUtil.calculate("-12345.67");
			assertEquals(-12345.67, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate2_1() {
		try {
			
			double result = MathUtil.calculate("(-123.45)");
			assertEquals(-123.45, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}

	@Test
	public void testCalculate3_1() {
		try {
			
			double result = MathUtil.calculate("3 * 4");
			assertEquals(12, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate3_2() {
		try {
			
			double result = MathUtil.calculate("3 * 4 / 2");
			assertEquals(6, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate3_3() {
		try {
			
			double result = MathUtil.calculate("3000 * 4 / 2");
			assertEquals(6000, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}

	@Test
	public void testCalculate3_4() {
		try {
			
			double result = MathUtil.calculate("5%2");
			assertEquals(1, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate3_5() {
		try {
			
			double result = MathUtil.calculate("6%2.5");
			assertEquals(1, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate3_6() {
		try {
			
			double result = MathUtil.calculate("6%2.5*2/10");
			assertEquals(0.2, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate4_1() {
		try {
			
			double result = MathUtil.calculate("3*4 + 2");
			assertEquals(14, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate4_2() {
		try {
			
			double result = MathUtil.calculate("3*(4 + 2000)");
			assertEquals(6012, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate4_3() {
		try {
			
			double result = MathUtil.calculate("3 +4 - 2");
			assertEquals(5, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate4_4() {
		try {
			
			double result = MathUtil.calculate("3.14 +4000 - 2");
			assertEquals(4001.14, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}

	
	@Test
	public void testCalculate4_5() {
		try {
			
			double result = MathUtil.calculate("3*4/ 2 + -2 * 2.5 - 1");
			assertEquals(0, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate4_6() {
		try {
			
			double result = MathUtil.calculate("3000*4/(2000 + -1000.0)* 2.5 - 1");
			assertEquals(29, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate4_7() {
		try {
			
			double result = MathUtil.calculate("3000 / 0");
			assertTrue(Double.isInfinite(result));
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate5_1() {
		try {
			
			double result = MathUtil.calculate("300 kB");
			assertEquals(300000, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate5_2() {
		try {
			
			double result = MathUtil.calculate("300 / 30 kB");
			assertEquals(10000, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate5_3() {
		try {
			
			double result = MathUtil.calculate("3*4/ 2 + -2 * 2.5 MB");
			assertEquals(1000000, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate5_4() {
		try {
			
			MathResult result = MathUtil.calculateWithUnit("3*4/ 2 + -2 * 2.5 MiB");
			assertEquals(1048576, result.getValue(), 0);
			assertEquals("B", result.getBaseUnit());
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate5_5() {
		try {
			
			double result = MathUtil.calculate("3*4/ 2 + -2 * 2.5 daM");
			assertEquals(10, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate6_1() {
		try {
			
			HashMap<String, Object> values = new HashMap<String, Object>();
			values.put("val", (float)12);
			
			double result = MathUtil.calculate("3 + val", values);
			assertEquals(15, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate6_2() {
		try {
			
			HashMap<String, Object> values = new HashMap<String, Object>();
			values.put("val", -1000);
			
			double result = MathUtil.calculate("3000*4/(2000 + val )* 2.5 - 1", values);
			assertEquals(29, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate7_1() {
		try {
			
			double result = MathUtil.calculate("round(12.34)");
			assertEquals(12, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate7_2() {
		try {
			
			double result = MathUtil.calculate("round(1 + 2.2) + 3");
			assertEquals(6, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate7_3() {
		try {
			
			MathUtil.calculate("round (1 + 2) + 3");
			fail("exception is expected!");
			
		} catch(Exception ex) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCalculate7_4() {
		try {
			
			double result = MathUtil.calculate("pow(2, 3)");
			assertEquals(8, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testCalculate7_5() {
		try {
			
			double result = MathUtil.calculate("16 * (1/pow(2, 3)) + 2");
			assertEquals(4, result, 0);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			fail("exception is occured");
		}
	}
	
	@Test
	public void testToThousandCommaStr1_1() {
		String value = MathUtil.toThousandCommaStr((int)10000);
		assertEquals("10,000", value);
	}
	
	@Test
	public void testToThousandCommaStr1_2() {
		String value = MathUtil.toThousandCommaStr(Integer.MAX_VALUE);
		assertEquals("2,147,483,647", value);
	}
	
	@Test
	public void testToThousandCommaStr1_3() {
		String value = MathUtil.toThousandCommaStr(Integer.MIN_VALUE);
		assertEquals("-2,147,483,648", value);
	}

	@Test
	public void testToThousandCommaStr2_1() {
		String value = MathUtil.toThousandCommaStr((long)10000);
		assertEquals("10,000", value);
	}
	
	@Test
	public void testToThousandCommaStr2_2() {
		String value = MathUtil.toThousandCommaStr(Long.MAX_VALUE);
		assertEquals("9,223,372,036,854,775,807", value);
	}
	
	@Test
	public void testToThousandCommaStr2_3() {
		String value = MathUtil.toThousandCommaStr(Long.MIN_VALUE);
		assertEquals("-9,223,372,036,854,775,808", value);
	}
	
	@Test
	public void testToThousandCommaStr3_1() {
		String value = MathUtil.toThousandCommaStr((float)10000.123);
		assertEquals("10,000.123", value);
	}
	
	@Test
	public void testToThousandCommaStr3_2() {
		String value = MathUtil.toThousandCommaStr((float)10000);
		assertEquals("10,000", value);
	}
	
	@Test
	public void testToThousandCommaStr3_3() {
		String value = MathUtil.toThousandCommaStr(Float.MAX_VALUE);
		assertEquals("340,282,346,638,528,860,000,000,000,000,000,000,000", value);
	}
	
	@Test
	public void testToThousandCommaStr3_4() {
		String value = MathUtil.toThousandCommaStr(Float.MIN_VALUE);
		assertEquals("0", value);
	}
	
	@Test
	public void testToThousandCommaStr3_5() {
		String value = MathUtil.toThousandCommaStr(Float.MAX_VALUE * -1);
		assertEquals("-340,282,346,638,528,860,000,000,000,000,000,000,000", value);
	}
	
	@Test
	public void testToThousandCommaStr4_1() {
		String value = MathUtil.toThousandCommaStr((double)10000.123);
		assertEquals("10,000.123", value);
	}
	
	@Test
	public void testToThousandCommaStr4_2() {
		String value = MathUtil.toThousandCommaStr((float)10000);
		assertEquals("10,000", value);
	}
	
	@Test
	public void testToThousandCommaStr4_3() {
		String value = MathUtil.toThousandCommaStr(Double.MAX_VALUE);
		assertEquals("179,769,313,486,231,570,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000", value);
	}
	
	@Test
	public void testToThousandCommaStr4_4() {
		String value = MathUtil.toThousandCommaStr(Double.MIN_VALUE);
		assertEquals("0", value);
	}
	
	@Test
	public void testToThousandCommaStr4_5() {
		String value = MathUtil.toThousandCommaStr(Double.MAX_VALUE * -1);
		assertEquals("-179,769,313,486,231,570,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000", value);
	}
	
	@Test
	public void testToUnitExp1() throws Exception {
		String unitExp = MathUtil.toUnitExp(1234567.0).toString();
		assertEquals("1.23M", unitExp);
	}

	@Test
	public void testToUnitExp2() throws Exception {
		String unitExp = MathUtil.toUnitExp(0.01234).toString(2);
		assertEquals("12.33m", unitExp);
	}
	
	@Test
	public void testToUnitExp3() throws Exception {
		String unitExp = MathUtil.toUnitExp(123.0).toString();
		assertEquals("123.00", unitExp);
	}
	
	@Test
	public void testToUnitExp4() throws Exception {
		String unitExp = MathUtil.toUnitExp(-1234567.0).toString();
		assertEquals("-1.23M", unitExp);
	}
	
	@Test
	public void testToBitUnitExp1() throws Exception {
		String unitExp = MathUtil.toBitUnitExp(123456).toString();
		assertEquals("120.56Ki", unitExp);
	}
	
	@Test
	public void testToBitUnitExp2_1() throws Exception {
		String unitExp = MathUtil.toBitUnitExp(1024.0).toString();
		assertEquals("1.00Ki", unitExp);
	}

	@Test
	public void testToBitUnitExp2_2() throws Exception {
		String unitExp = MathUtil.toBitUnitExp(1023.0).toString();
		assertEquals("1023.00", unitExp);
	}
	
	@Test
	public void testToBitUnitExp3_1() throws Exception {
		String unitExp = MathUtil.toBitUnitExp(1048576.0).toString();
		assertEquals("1.00Mi", unitExp);
	}
	
	@Test
	public void testToBitUnitExp3_2() throws Exception {
		String unitExp = MathUtil.toBitUnitExp(1048576.0 - 1).toString();
		assertEquals("1023.99Ki", unitExp);
	}
	
	@Test
	public void testToBitUnitExp4_1() throws Exception {
		String unitExp = MathUtil.toBitUnitExp(1073741824.0).toString();
		assertEquals("1.00Gi", unitExp);
	}
	
	@Test
	public void testToBitUnitExp4_2() throws Exception {
		String unitExp = MathUtil.toBitUnitExp(1073741824.0 - 1).toString();
		assertEquals("1023.99Mi", unitExp);
	}
	
	@Test
	public void testToBitUnitExp5_1() throws Exception {
		String unitExp = MathUtil.toBitUnitExp(1099511627776.0).toString();
		assertEquals("1.00Ti", unitExp);
	}
	
	@Test
	public void testToBitUnitExp5_2() throws Exception {
		String unitExp = MathUtil.toBitUnitExp(1099511627776.0 - 1).toString();
		assertEquals("1023.99Gi", unitExp);
	}
	
	@Test
	public void testToBitUnitExp6_1() throws Exception {
		String unitExp = MathUtil.toBitUnitExp(1125899906842624.0).toString();
		assertEquals("1.00Pi", unitExp);
	}
	
	@Test
	public void testToBitUnitExp6_2() throws Exception {
		String unitPrefixExp = MathUtil.toBitUnitExp(1125899906842624.0 - 1).toString();
		System.out.println(unitPrefixExp);
	}
	
	@Test
	public void testToBitUnitExp7_1() throws Exception {
		String unitPrefixExp = MathUtil.toBitUnitExp(1152921504606846976.0).toString();
		System.out.println(unitPrefixExp);
	}
	
	@Test
	public void testToBitUnitExp7_2() throws Exception {
		String unitPrefixExp = MathUtil.toBitUnitExp(1152921504606846976.0 - 1).toString();
		System.out.println(unitPrefixExp);
	}
	
	@Test
	public void testToBitUnitExp8_1() throws Exception {
		String unitPrefixExp = MathUtil.toBitUnitExp(1180591620717411303424.0).toString();
		System.out.println(unitPrefixExp);
	}
	
	@Test
	public void testToBitUnitExp8_2() throws Exception {
		String unitPrefixExp = MathUtil.toBitUnitExp(1180591620717411303424.0 - 1).toString();
		System.out.println(unitPrefixExp);
	}
	
	@Test
	public void testToBitUnitExp9_1() throws Exception {
		String unitPrefixExp = MathUtil.toBitUnitExp(1208925819614629174706176.0).toString();
		System.out.println(unitPrefixExp);
	}
	
	@Test
	public void testToBitUnitExp9_2() throws Exception {
		String unitPrefixExp = MathUtil.toBitUnitExp(1208925819614629174706176.0 - 1).toString();
		System.out.println(unitPrefixExp);
	}
	
	@Test
	public void testToBitUnitExp9_3() throws Exception {
		String unitPrefixExp = MathUtil.toBitUnitExp(1237940039285380274899124224.0).toString();
		System.out.println(unitPrefixExp);
	}
}
