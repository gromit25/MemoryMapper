package com.jutools;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * type(class)와 관련 Utility 클래스
 * 
 * @author jmsohn
 */
public class TypeUtil {
	
	/**
	 * obj의 타입에 따라 parsing 및 변환을 수행하여 반환하는 메소드
	 * 
	 * @param <T> 변환할 obj의 타입
	 * @param obj 객체
	 * @param type 변환할 obj의 타입의 클래스
	 * @param parser 문자열일 경우 파싱하는 메소드(람다 함수)
	 * @return parsing 및 변환 완료된 값
	 */
	private static <T> T toXXX(Object obj, Class<T> type, Function<String, T> parser) throws Exception {
		
		// 입력 값 검증
		if(obj == null) {
			throw new NullPointerException("obj is null");
		}
		
		if(type == null) {
			throw new NullPointerException("type is null");
		}
		
		if(parser == null) {
			throw new NullPointerException("parser is null");
		}
		
		// 변환할 obj 타입에 따라 변환 작업 수행
		if(type.isAssignableFrom(obj.getClass()) == true) {
			return type.cast(obj);
		} else if(obj instanceof String) {
			return parser.apply(obj.toString());
		} else {
			throw new Exception("Unexpected type:" + obj.getClass());
		}
	}
	
	/**
	 * obj를 boolean으로 변환함<br>
	 * -> obj는 boolean, Boolean, boolean의 문자열 중 하나이어야 함
	 * 
	 * @param obj 변환할 obj
	 * @return 변환된 boolean 값
	 */
	public static boolean toBoolean(Object obj) throws Exception {
		return toXXX(obj, Boolean.class, str -> {
			return Boolean.parseBoolean(str);
		});
	}

	/**
	 * obj를 int로 변환함<br>
	 * -> obj는 int, Integer, int의 문자열 중 하나이어야 함
	 * 
	 * @param obj 변환할 obj
	 * @return 변환된 int 값
	 */
	public static int toInteger(Object obj) throws Exception {
		return toXXX(obj, Integer.class, str -> {
			return Integer.parseInt(str);
		});
	}

	/**
	 * obj를 long으로 변환함<br>
	 * -> obj는 long, Long, long의 문자열 중 하나이어야 함
	 * 
	 * @param obj 변환할 obj
	 * @return 변환된 long 값
	 */
	public static long toLong(Object obj) throws Exception {
		return toXXX(obj, Long.class, str -> {
			return Long.parseLong(str);
		});
	}
	
	/**
	 * obj를 float로 변환함<br>
	 * -> obj는 float, Float, float의 문자열 중 하나이어야 함
	 * 
	 * @param obj 변환할 obj
	 * @return 변환된 float 값
	 */
	public static float toFloat(Object obj) throws Exception {
		return toXXX(obj, Float.class, str -> {
			return Float.parseFloat(str);
		});
	}

	/**
	 * obj를 double로 변환함<br>
	 * -> obj는 double, Double, double의 문자열 중 하나이어야 함
	 * 
	 * @param obj 변환할 obj
	 * @return 변환된 double 값
	 */
	public static double toDouble(Object obj) throws Exception {
		return toXXX(obj, Double.class, str -> {
			return Double.parseDouble(str);
		});
	}
	
	/**
	 * 객체의 필드에 값을 설정<br>
	 * private, protected 필드에 값을 설정할 수 있도록 함<br>
	 * 이 메소드를 사용할 경우 illegal access warning이 발생함
	 * 이는 java 실행시 "--illegal-access=warn" 옵션을 추가하여 방지할 수 있음
	 * 
	 * @param obj 객체
	 * @param fieldName 설정할 필드명
	 * @param value 필드에 설정할 값
	 */
	public static void setField(Object obj, String fieldName, Object value) throws Exception {
		
		// 입력값 검증
		if(obj == null) {
			throw new NullPointerException("obj is null");
		}
		
		if(StringUtil.isEmpty(fieldName) == true) {
			throw new NullPointerException("fieldName is empty");
		}
		
		// field 정보를 가져옴
		Class<?> objClass = obj.getClass();
		Field field = objClass.getDeclaredField(fieldName);
		if(field == null) {
			throw new Exception(fieldName + " is not found in " + objClass);
		}
		
		// 접근 가능하도록 수정
		field.setAccessible(true);
		
		// 값을 설정함
		field.set(obj, value);
	}
}