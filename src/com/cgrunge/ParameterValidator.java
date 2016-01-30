//ParameterValidator - standard validation for method parameters
//Copyright (C) 2016  Chuck Runge
//Lombard, IL.
//CGRunge001@GMail.com

//This program is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License
//as published by the Free Software Foundation; either version 2
//of the License, or (at your option) any later version.

//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.

//You should have received a copy of the GNU General Public License
//along with this program; if not, write to the Free Software
//Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

package com.cgrunge;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class ParameterValidator {

	ParameterValidator() {}
	
	/*
	 * generic validation routine for method parameters 
	 */
	public int parameterCheck(Object[] value) {
		
		int retVal= 0;
		String type = null;
		
		//loop through input
		for(int i=0;i<value.length;i++) {
			//array contains first a class then a value
			if((i%2)!=0) {
				if (value[i] == null){
					console(i, value[i], type);
					retVal = 1; //fail
				} else {
					//check isInstanceOf
					if(objectCheck(type, value[i])== 0) {
						//success!
					} else {
						console(i, null, type);
						retVal = 1; //fail
					}
				}
			} else {
				//save type to test against value
				type = (String) value[i];
			}
		}
		
		return retVal;
	}

	/*
	 * check one object against it's type
	 */
	public int objectCheck(String classIn, Object obj) {
		int retVal = 1;
		String classNameSz = null;
		if(classIn == null || obj == null) {
			return retVal;
		}
		String classSz = classIn.toLowerCase();
		if("string".equals(classSz)) classNameSz = "java.lang.String";
		if("int".equals(classSz)) classNameSz = "java.lang.Integer";
		if("short".equals(classSz)) classNameSz = "java.lang.Short";
		if("long".equals(classSz)) classNameSz = "java.lang.Long";
		if("byte".equals(classSz)) classNameSz = "java.lang.Byte";
		if("float".equals(classSz)) classNameSz = "java.lang.Float";
		if("double".equals(classSz)) classNameSz = "java.lang.Double";
		if("char".equals(classSz)) classNameSz = "java.lang.Char";
		if("boolean".equals(classSz)) classNameSz = "java.lang.Boolean";
		if("date".equals(classSz)) classNameSz = "java.util.Date";
		if("bigdecimal".equals(classSz)) classNameSz = "java.math.BigDecimal";
		if("object".equals(classSz)) classNameSz = "java.lang.Object";
		if(classNameSz == null){
			classNameSz = classIn;
		}
		if(obj.getClass().getName() == classNameSz) {
			return 0;
		} else {
			System.out.println("Error! "+obj.getClass().getName()+" was input instead of "+classIn);
			return retVal;			
		}
		
	}
	
	public boolean isStringEmpty(String sz) {
		return StringUtils.isEmpty(sz);
	}
	public boolean isArrayEmpty(Object[] obj) {
		return ArrayUtils.isEmpty(obj);
	}
	public boolean isListEmpty(List<Object> obj) {
		return obj.equals(Collections.<Object>emptyList());
	}
	public boolean isSetEmpty(Set<Object> obj) {
		return obj.equals(Collections.<Object>emptySet());
	}
	public boolean isMapEmpty(Map<Object, Object> obj) {
		return obj.equals(Collections.<Object,Object>emptyMap());
	}
	
	
	void console(int i, Object obj, String type) {

		if(obj == null)
			System.out.format("Error! parameter %d - type %s is required\n", (i/2+1), type);
		//else
			//System.out.format("parameter %d - type %s is OK\n", (i/2+1), type);
	}

}
