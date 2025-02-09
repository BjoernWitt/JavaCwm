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
package org.ogrehus.jcwm.impl.resource.relational.util.types;

import java.sql.Types;

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.util.types.JdbcLongNVarChar;
import org.ogrehus.jcwm.impl.resource.relational.FunSQLSimpleType;

public class FunJdbcLongNVarChar 
extends 
	FunSQLSimpleType
implements
	JdbcLongNVarChar
{

	public FunJdbcLongNVarChar( Integer numericPrecision ) {
		this( null, numericPrecision );
	}



	public FunJdbcLongNVarChar( String name, Integer numericPrecision ) {
		super( name==null?"LONGVARCHAR":name, Visibility._public, Boolean.TRUE );
		this.setNumericPrecision( numericPrecision );
		this.typeNumber = Types.LONGNVARCHAR;
	}



	public Boolean isBinary() {
		return Boolean.FALSE;
	}
}