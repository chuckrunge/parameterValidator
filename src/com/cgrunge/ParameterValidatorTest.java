package com.cgrunge;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

public class ParameterValidatorTest {
	
	ParameterValidatorTest(){};
	
	/*
	 * test parameter validation class
	 */
	public static void main(String args[]) {
		
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

		//standard object array normally containing input parms
		Object parms[] = {"java.lang.String", obj1, 
				"double", obj2,
				"Date", obj3, 
				"Float", obj4,  
				"BigDecimal", obj5, 
				"int",i,
				"short",j,
				"boolean",boo,
				"Object", obj6};
		
		//execute validation and check for non-zero
		if(validator.parameterCheck(parms)==0)
			System.out.format("parameterCheck success\n");
		else
			System.out.format("parameterCheck fail\n");
				
	}

}
