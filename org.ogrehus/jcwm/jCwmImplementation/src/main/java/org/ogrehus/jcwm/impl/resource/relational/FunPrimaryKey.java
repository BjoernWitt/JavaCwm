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
package org.ogrehus.jcwm.impl.resource.relational;

import org.ogrehus.jcwm.api.resource.relational.PrimaryKey;
import org.ogrehus.jcwm.api.resource.relational.util.Deferability;


/**
 * There is only one UniqueConstraint of type PrimaryKey per Table. 
 * <p>
 * It is implemented specifically by each RDBMS. 
 * </p>
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunPrimaryKey
extends
	FunUniqueConstraint
implements
	PrimaryKey
{


	/**
	 * Creates a new instance of UniqueConstraint by specific parameters.
	 * <p>
	 * A condition to define uniqueness of rows in a table. An example of UniqueConstraint is a primary key.
	 * </p>
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul> 
	 * 
	 * @param name An identifier for the UniqueConstraint within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param deferrability The type of the validity of the UniqueConstraint that is to be tested at each statement or at the end of a transaction. 
	 * Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>deferability</code> is <code>null</code>.
	 * 
	 */
	public FunPrimaryKey( String name, Deferability deferrability ) {
		super( name, deferrability );
	}	



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunPrimaryKey" );
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		return out.toString();
	}
}