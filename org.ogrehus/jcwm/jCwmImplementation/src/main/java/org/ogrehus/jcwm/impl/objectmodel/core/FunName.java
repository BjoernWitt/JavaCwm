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
package org.ogrehus.jcwm.impl.objectmodel.core;

import org.ogrehus.jcwm.api.objectmodel.core.Name;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

public class FunName
extends
	FunDataType
implements
	Name
{


	public  static final Name   DEFAULT   = new FunName( "" );



	private static final String MY_NAME   = "Name";



	private static final Name   NAME      = new FunName( MY_NAME );



	public  static final String FUN_NAME = "__________!__________FunName";



	protected String value = null;



	public FunName( String value ) {
		super( FUN_NAME, Visibility._public, Boolean.FALSE );
		this.value = value;
	}



	@Override
	public Name setName( String name ) {
		throw new UnsupportedOperationException( "Unable to Change the name of the DataType: Name! Use rename instead.");
	}



	public String rename( String rename ) {
		if ( name == null ) {
			throw new NullPointerException( "Parameter: name must not be null, use a zerostring as default instead." );
		}
		String old = this.value;
		this.value = rename;
		return old;
	}



	@Override
	public Name getName() {
		return NAME; // is always NAME
	}


	@Override
	public String toString() {
		return this.value;
	}
}