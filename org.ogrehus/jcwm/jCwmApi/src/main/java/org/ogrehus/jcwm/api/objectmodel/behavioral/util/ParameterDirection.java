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
package org.ogrehus.jcwm.api.objectmodel.behavioral.util;

import java.sql.DatabaseMetaData;

/**
 * In the meta-model ParameterDirectionKind defines an enumeration that denotes if a Parameter is used for supplying an argument and/or for returning 
 * a value. 
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>_in</code> - An input Parameter (may not be modified).</li>
 * <li><code>_out</code> - An output Parameter (may be modified to communicate information to the caller).</li>
 * <li><code>_inout</code> - An input Parameter that may be modified.</li>
 * <li><code>_return</code> - A return value of a call.</li>
 * <li><code>_result</code> - A result column in <code>ResultSet</code></li> 
 * 
 * <li>The default value is <code>_in</code>.</li>
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public enum ParameterDirection {


	  _in		// An input Parameter (may not be modified).
	, _out		// An output Parameter (may be modified to communicate information to the caller).
	, _inout	// An input Parameter that may be modified.
	, _return	// A return value of a call.
	, _result;  // A result column in <code>ResultSet</code>



	public static final ParameterDirection getDefault() { return ParameterDirection._in; } 



	public static final ParameterDirection byJDBCProcedure( short parameterKind ) {
		switch( parameterKind ) {
		case DatabaseMetaData.procedureColumnIn:     return _in;
		case DatabaseMetaData.procedureColumnOut:    return _out;
		case DatabaseMetaData.procedureColumnInOut:  return _inout;
		case DatabaseMetaData.procedureColumnReturn: return _return;
		case DatabaseMetaData.procedureColumnResult: return _result;
		default: return getDefault();
		}
	}



	public static final Short toJDBCProcedure( ParameterDirection parameterDirection ) {
		if ( parameterDirection == null ) {
			return DatabaseMetaData.procedureColumnUnknown; 
		}
		switch( parameterDirection ) {
		case _in:     return DatabaseMetaData.procedureColumnIn;
		case _out:    return DatabaseMetaData.procedureColumnOut;
		case _inout:  return DatabaseMetaData.procedureColumnInOut;
		case _return: return DatabaseMetaData.procedureColumnReturn;
		case _result: return DatabaseMetaData.procedureColumnResult;
		default: return DatabaseMetaData.procedureColumnUnknown;
		}
	}



	public static final ParameterDirection byJDBCFunction( short parameterKind ) {
		switch( parameterKind ) {
		case DatabaseMetaData.functionColumnIn:     return _in;
		case DatabaseMetaData.functionColumnOut:    return _out;
		case DatabaseMetaData.functionColumnInOut:  return _inout;
		case DatabaseMetaData.functionReturn:		return _return;
		case DatabaseMetaData.functionColumnResult: return _result;
		default: return getDefault();
		}
	}



	public static final Short toJDBCFunction( ParameterDirection parameterDirection ) {
		if ( parameterDirection == null ) {
			return DatabaseMetaData.functionColumnUnknown; 
		}
		switch( parameterDirection ) {
		case _in:     return DatabaseMetaData.functionColumnIn;
		case _out:    return DatabaseMetaData.functionColumnOut;
		case _inout:  return DatabaseMetaData.functionColumnInOut;
		case _return: return DatabaseMetaData.functionReturn;
		case _result: return DatabaseMetaData.functionColumnResult;
		default: return DatabaseMetaData.functionColumnUnknown;
		}
	}
}