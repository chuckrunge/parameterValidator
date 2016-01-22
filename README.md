# parameterValidator
A standard method to validate those parms they passed you.
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

Two methods are used.  The primary method is called parameterCheck.  A single parameter is needed consisting of an array of Objects representing a parameter list.  For example, a method called with (Integer parm1, String parm2) can test it's input by running parameterCheck with Object[] parms={"Integer", parm1, "String", parm2}.  Note that types are passed as strings, and seperated by commas.

parameterCheck returns an integer that will be 0 (zero) if the list is valid.

The second method is objectCheck.  Each type/object pair is passed to objectCheck, but you can do this yourself if desired.

objectCheck also returns an integer that will be 0 (zero) if the parm is valid. 

Many basic types are hard-coded, but other types can be passed in as long as they are fully qualified names, i.e. "java.lang.String" or your own custom object.

This was developed based on an idea to help a large application that was plagued by null pointer errors.  A small, routine update could be applied to any method receiving data from outside the system, and exceptions could be captured and reported without "swallowing" a null that would blow up later. 