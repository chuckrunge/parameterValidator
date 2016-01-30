# parameterValidator
A standard method to validate method parameters.
Copyright (C) 2016  Chuck Runge
Lombard, IL.
CGRunge001@GMail.com

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA. 

*===============================================*
        parameterValidator
*===============================================*

ParameterValidator is a class to perform basic validation tasks for any parameter list.  Each parameter is tested for null values, and each one is checked that it is of the type specified.

Two primary methods are provided.  The primary method is called parameterCheck.  A single parameter is needed consisting of an array of Objects representing a parameter list.  For example, a method called with (Integer parm1, String parm2) can test it's input by running parameterCheck with Object[] parms={"Integer", parm1, "String", parm2}.  Note that types are passed as strings, and seperated by commas.

parameterCheck returns an integer that will be 0 (zero) if the list is valid.

The second method is objectCheck.  Each type/object pair is passed to objectCheck from parameterCheck, but you can do this yourself if desired.

objectCheck also returns an integer that will be 0 (zero) if the parm is valid. 

Many basic types are hard-coded, but other types can be passed in as long as they are fully qualified names, i.e. "java.lang.String" or your own custom object.

This was developed based on an idea to help a large application that was plagued by null pointer errors.  A small, routine update could be applied to any method receiving data, and exceptions could be captured and reported without "swallowing" a null pointer that would blow up somewhere else. 

ADDITIONAL METHODS

Several methods from Apache Commons are too good to ignore.  ArrayUtils and StringUtils are required imports, and the rest are found in the java.util class.  Thin wrapper methods are provided as follows for String, Array, List, Set, and Map.

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
	 

EXAMPLES

The class ParameterValidatorTest is provided with full source code for all the examples.

	//example 1 - execute method with one parm.  Testparm1 is the invoked method that will test it's parameters on entry.
	
	public static boolean testParm1(List<Object> szList) {
		ParameterValidator validator = new ParameterValidator();
		//can use type specific validation
		return validator.isListEmpty(szList);
	}
	
	A type-specific method for a List collection is exeecuted.  A new validator object is instantiate, and a boolean value is returned.
	
		//an empty array - "isEmpty?" returns true
		List<Object> fooList = new ArrayList<Object>();
		result = testParm1(fooList);
		assert(result == true);
		
		//an array with data - "isEmpty?" returns false
		List<Object> barList = new ArrayList<Object>();
		barList.add("yeah");
		result = testParm1(barList);
		assert(result == false);
		
		
		//example 2 - execute method with multiple parms.  Method testParm2 is expecting three parameters - and int, a short, and a boolean.  
		
		public static boolean testParm2(int i, short s, boolean b) {
			ParameterValidator validator = new ParameterValidator();
			Object[] p = {"int", i, "short", s, "boolean", b};
			//execute validation and check for non-zero
			if(validator.parameterCheck(p)==0)
				return true; 
			else
				return false;
		}

		The method receives three parameters, which are validated at compile time.  But to check for null pointers, the types and parameters are loaded into an Object array.  The parameterCheck method will return a non-zero value on an error condition, and the return value is translated into a boolean return value.
		
		result = testParm2(i, j, boo);
		assert(result == true);
		
		
		//example 3 - execute method with many types of parms.  Method testParm3 is expecting nine different types of parameters.
		
		public static boolean testParm3(
			String obj1, double obj2, Date obj3, Float obj4,  
			BigDecimal obj5, int i, short j, boolean boo, Object obj6) {			
			ParameterValidator validator = new ParameterValidator();
		
		//Note: We can pass any fully qualified class name
			Object p[]= {"java.lang.String", obj1, 
				"double", obj2, "Date", obj3, "Float", obj4, "BigDecimal", obj5, 
				"int",i, "short",j, "boolean",boo, "Object", obj6};
			if(validator.parameterCheck(p)==0)
				return true; 
			else
				return false;
		}

		testParm3 is an elaboration of testParm2, and just expands the number of parameters and types.  However, use of a fully qualified class name illustrates how an unsupported class, like you might invent yourself, can be tested.
		
		result = testParm3(obj1,obj2,obj3,obj4,obj5,i,j,boo,obj6);
		assert(result == true);
	
	
		//example 4 - single string parm (commons util is null proof).  The validator is called directly to examine a single string.  Apache Commons provides the underlying utility method.
		
		result = validator.isStringEmpty(null);
		assert(result == true); //isEmpty? returns true
		result = validator.isStringEmpty("something");
		assert(result == false);
		
		
		//example 5 - a string array.  The parameter is a single object containing multiple strings in an array.  The validator is called directly.
		
		String[] foo = null;
		result = validator.isArrayEmpty(foo);
		assert(result == true);
		String[] bar = {"y","zzz"};
		result = validator.isArrayEmpty(bar);
		assert(result == false);

		
		//example 6 - a hashset.  The parameter is a HashSet, and the validator is called directly.
		
		Set<Object> fooSet = new HashSet<Object>();
		result = validator.isSetEmpty(fooSet);
		assert(result == true); //isEmpty? returns true
		Set<Object> barSet = new HashSet<Object>();
		barSet.add("yeah");
		result = validator.isSetEmpty(barSet);
		assert(result == false);

		
		//example 7 - hashmap.  The parameter is a HashMap, and the validator is called directly.
		
		Map<Object,Object> fooMap = new HashMap<Object,Object>();
		result = validator.isMapEmpty(fooMap);
		assert(result == true);  //isEmpty? returns true
		Map<Object,Object> barMap = new HashMap<Object,Object>();
		barMap.put("yeah","Oh,yeah");
		result = validator.isMapEmpty(barMap);
		assert(result == false);
		
		
		//example 8 - pass the validator in as another parameter.  If you were building an application from scratch, only one line of code is required to validate parameters.
		
		public static boolean testParm4(String sz, ParameterValidator validator) {
			return validator.isStringEmpty(sz);
		}
	
		And here we execute the method.
		result = testParm4(obj1, validator);
		assert(result == false);
		
		
		//example 9 - verify type of one object.  This time, we execute objectType directly.  A non-zero return code indicates there was a problem.
		
		if(validator.objectCheck("Date", obj3)<0)
			console("object is not a date");
