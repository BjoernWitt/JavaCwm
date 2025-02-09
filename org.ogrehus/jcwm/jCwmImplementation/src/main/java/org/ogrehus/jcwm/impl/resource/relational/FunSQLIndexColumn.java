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

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.SQLIndex;
import org.ogrehus.jcwm.api.resource.relational.SQLIndexColumn;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.Table;
import org.ogrehus.jcwm.api.resource.relational.TableColumn;
import org.ogrehus.jcwm.impl.foundation.keyindexes.util.generic.GFunIndexedFeature;


/**
 * Associates an index with its columns.
 * 
 * <p>
 * This is really an association (link) class. It is associated with one index and one column.
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
public class FunSQLIndexColumn
extends
	GFunIndexedFeature< 
		  Table
		, Schema
		, SQLIndex
		, TableColumn
	>
implements
	SQLIndexColumn
{


	/**
	 * Creates a new instance of SQLIndexColumn by specific parameters.
	 * 
	 * @param name An identifier for the SQLIndexColumn within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param column Identifies the StructuralFeature instance for which this SQLIndexColumn instance is relevant. Must not be <code>null</code>.
	 * 
	 * @param index The Index instance for which this SQLIndexColumn instance is relevant. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>column</code> is <code>null</code>.
	 * 
	 */
	public FunSQLIndexColumn( SQLIndex sqlIndex, TableColumn column ) {
		this( column.getSimpleName(), sqlIndex, column );
	}



	/**
	 * Creates a new instance of SQLIndexColumn by specific parameters.
	 * 
	 * @param name An identifier for the SQLIndexColumn within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param column Identifies the StructuralFeature instance for which this SQLIndexColumn instance is relevant. Must not be <code>null</code>.
	 * 
	 * @param index The Index instance for which this SQLIndexColumn instance is relevant. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>column</code> is <code>null</code>.
	 * 
	 */
	public FunSQLIndexColumn( String name, SQLIndex sqlIndex, TableColumn column ) {
		super( name, Visibility._public, sqlIndex, column );
	}
}