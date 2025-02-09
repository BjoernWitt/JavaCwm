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
import org.ogrehus.jcwm.api.resource.relational.util.types.JdbcNumeric;
import org.ogrehus.jcwm.impl.resource.relational.FunSQLSimpleType;

public class FunJdbcNumeric 
extends 
	FunSQLSimpleType
implements
	JdbcNumeric 
{

	public FunJdbcNumeric( Integer numericPrecision, Short numericScale ) {
		this( null, numericPrecision, numericScale, null );
	}	



	public FunJdbcNumeric( Integer numericPrecision, Short numericScale, Short numericPrecisionRadix ) {
		this( null, numericPrecision, numericPrecisionRadix, numericScale );
	}



	public FunJdbcNumeric( 
		  String name
		, Integer numericPrecision
		, Short numericPrecisionRadix
		, Short numericScale 
	) {
		super( name==null?"NUMERIC":name, Visibility._public, Boolean.TRUE );
		this.setNumericPrecision( numericPrecision );
		this.setNumericScale( numericScale );
		this.setNumericPrecisionRadix( numericPrecisionRadix==null?10:numericPrecisionRadix );
		this.typeNumber = Types.NUMERIC;
	}
}