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

import java.sql.ResultSetMetaData;

/**
 * Indicates if null values are valid in this column.
 * 
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>columnNoNulls</code> - </li>
 * <li><code>columnNullable</code> - </li>
 * <li><code>columnNullableUnknown</code> - </li>
 * <li>The default value is <code>columnNullableUnknown</code>.</li>
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public enum Nullable {
	  columnNoNulls
	, columnNullable
	, columnNullableUnknown
	;

	public static Nullable getDefault() { 
		return columnNullableUnknown; 
	} 	



	public static Nullable byJDBC( Short nullable ) {
		if ( nullable == null ) {
			return columnNullableUnknown;
		}
		switch ( nullable.intValue() ) {
		case ResultSetMetaData.columnNoNulls: return columnNoNulls;
		case ResultSetMetaData.columnNullable: return columnNullable;
		default: return columnNullableUnknown;
		}
	}



	public static Short toJDBC( Nullable nullable ) {
		if ( nullable == null ) {
			return ResultSetMetaData.columnNullableUnknown;
		}
		switch ( nullable ) {
		case columnNoNulls: return ResultSetMetaData.columnNoNulls;
		case columnNullable: return ResultSetMetaData.columnNullable;
		default: return ResultSetMetaData.columnNullableUnknown;
		}
	}
}