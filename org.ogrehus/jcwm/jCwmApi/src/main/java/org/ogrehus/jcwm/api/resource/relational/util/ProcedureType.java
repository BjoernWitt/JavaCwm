/*
 * GNU Lesser General Public License v3.0
 * https://www.gnu.org/licenses/lgpl-3.0-standalone.html
 * 
 * Copyright (C) 2025 Björn Witt
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 */
package org.ogrehus.jcwm.api.resource.relational.util;

import java.sql.DatabaseMetaData;

/**
 * A Procedure can be either a Function or a true Procedure. 
 * <p>
 * This indicates whether this object returns a value or not.
 * </p>
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>procedure</code> - Returns no value.</li>
 * <li><code>function</code> - Returns a value.</li>
 * <li><code>unknown</code> - nobody knows</li> 
 * <li>The default value is <code>unknown</code>.</li>
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public enum ProcedureType {
	  procedure
	, cursorProcedure
	, function
	, scalarFunction
	, tableFunction	
	, rowFunction	
	, unknown
	;



	public static final ProcedureType getDefault() { 
		return ProcedureType.unknown; 
	} 	



	public static ProcedureType byJDBCProcedures( Short jdbcProcedureType ) {
		if ( jdbcProcedureType == null ) {
			return procedure;
		}
		switch ( jdbcProcedureType.intValue() ) {
		case DatabaseMetaData.procedureNoResult: return procedure;
		case DatabaseMetaData.procedureReturnsResult: return cursorProcedure;
		default: return unknown;
		}
	}



	public static Short toJDBCProcedures( ProcedureType procedureType ) {
		if ( procedureType == null ) {
			return DatabaseMetaData.procedureResultUnknown;
		}
		
		switch ( procedureType ) {
		case procedure: return DatabaseMetaData.procedureNoResult;
		case cursorProcedure:  return DatabaseMetaData.procedureReturnsResult;
		default: return DatabaseMetaData.procedureResultUnknown;
		}
	}



	public static ProcedureType byJDBCFunctions( Short jdbcFuncitonType ) {
		if ( jdbcFuncitonType == null ) {
			return function;
		}
		
		switch ( jdbcFuncitonType.intValue() ) {
		case DatabaseMetaData.functionNoTable: return scalarFunction;
		case DatabaseMetaData.functionReturnsTable: return tableFunction;
		default: return unknown;
		}
	}




	public static Short toJDBCFunctions( ProcedureType procedureType ) {
		if ( procedureType == null ) {
			return DatabaseMetaData.procedureResultUnknown;
		}
		switch ( procedureType ) {
		case scalarFunction: return DatabaseMetaData.functionNoTable;
		case tableFunction:  return DatabaseMetaData.procedureReturnsResult;
		default: return DatabaseMetaData.procedureResultUnknown;
		}
	}



	public static Boolean isQuery( ProcedureType procedureType ) {
		switch( procedureType ) {
		case tableFunction:
		case cursorProcedure:
			return Boolean.TRUE;
		default:
			return Boolean.FALSE;
		}
	}
}