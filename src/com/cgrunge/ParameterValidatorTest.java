package com.cgrunge;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParameterValidatorTest {
	
	ParameterValidatorTest(){};
	
	/*
	 * test parameter validation class
	 */
	public static void main(String args[]) {
		
		boolean result = true;
		ParameterValidator validator = new ParameterValidator();

		//test data
		String obj1 = "String";
		Double obj2 = 14.5d;
		Date obj3 = new GregorianCalendar().getTime();
		float obj4 = 14.2f;
		BigDecimal obj5 = new BigDecimal(14.2);
		int i=0;
		short j=0;
		boolean boo = true; 
		Object obj6 = new Object();
		
		//example 1 - execute method with one parm
		List<Object> fooList = new ArrayList<Object>();
		result = testParm1(fooList);
		assert(result == true);
		//...and with one good parm
		List<Object> barList = new ArrayList<Object>();
		barList.add("yeah");
		result = testParm1(barList);
		assert(result == false);
		
		//example 2 - execute method with multiple parms
		result = testParm2(i, j, boo);
		assert(result == true);
		
		//example 3 - execute method with many types of parms
		result = testParm3(obj1,obj2,obj3,obj4,obj5,i,j,boo,obj6);
		assert(result == true);
		
		//example 4 - single string parm (commons util is null proof)
		result = validator.isStringEmpty(null);
		assert(result == true);
		result = validator.isStringEmpty("something");
		assert(result == false);
		
		//example 5 - string array
		String[] foo = null;
		result = validator.isArrayEmpty(foo);
		assert(result == true);
		String[] bar = {"y","zzz"};
		result = validator.isArrayEmpty(bar);
		assert(result == false);

		//example 6 -hashset
		Set<Object> fooSet = new HashSet<Object>();
		result = validator.isSetEmpty(fooSet);
		assert(result == true);
		Set<Object> barSet = new HashSet<Object>();
		barSet.add("yeah");
		result = validator.isSetEmpty(barSet);
		assert(result == false);

		//example 7 - hashmap
		Map<Object,Object> fooMap = new HashMap<Object,Object>();
		result = validator.isMapEmpty(fooMap);
		assert(result == true);
		Map<Object,Object> barMap = new HashMap<Object,Object>();
		barMap.put("yeah","Oh,yeah");
		result = validator.isMapEmpty(barMap);
		assert(result == false);
		
		//example 8 - pass validator as a parm
		result = testParm4(obj1, validator);
		assert(result == false);
		
		//example 9 - verify type of one object
		if(validator.objectCheck("Date", obj3)<0)
			console("object is not a date");
		
		console("parameter validator test completed successfully");
		
	}
	
	//example 1 - simple method with single parm
	public static boolean testParm1(List<Object> szList) {
		ParameterValidator validator = new ParameterValidator();
		//can use type specific validation
		return validator.isListEmpty(szList);
	}
	
	//example 2 - simple method with multiple parms
	public static boolean testParm2(int i, short s, boolean b) {
		ParameterValidator validator = new ParameterValidator();
		//parameter check handles multiple types
		Object[] p = {"int", i, "short", s, "boolean", b};
		//execute validation and check for non-zero
		if(validator.parameterCheck(p)==0)
			return true; 
		else
			return false;
	}
	
	//example 3 - method with many parms
	public static boolean testParm3(
			String obj1, 
			double obj2,
			Date obj3, 
			Float obj4,  
			BigDecimal obj5, 
			int i,
			short j,
			boolean boo,
			Object obj6) {
		ParameterValidator validator = new ParameterValidator();
		//parameter check handles multiple types
		Object p[]= {"java.lang.String", obj1, //can use fully qualified class names
				"double", obj2,
				"Date", obj3, 
				"Float", obj4,  
				"BigDecimal", obj5, 
				"int",i,
				"short",j,
				"boolean",boo,
				"Object", obj6};
		//execute validation and check for non-zero return
		if(validator.parameterCheck(p)==0)
			return true; 
		else
			return false;
	}

	//example 8 - simple method with single parm and a validator
	public static boolean testParm4(String sz, ParameterValidator validator) {
		return validator.isStringEmpty(sz);
	}
	
	//utility method
	public static void console(String sz) {
		System.out.println(sz);
	}

}
