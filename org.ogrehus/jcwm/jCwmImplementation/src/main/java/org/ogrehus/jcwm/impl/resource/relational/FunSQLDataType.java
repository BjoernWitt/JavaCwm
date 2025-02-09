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

import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.resource.relational.SQLDataType;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumn;
import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunClassifier;


/**
 * An SQLDataType is used to reference any datatype associated with a column.
 * 
 * <p>
 * In addition to owning Tables and/or Views, Schemas also own Procedures and Triggers.
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
public class FunSQLDataType
extends
	GFunClassifier<Schema, TypeSystem, Dependency, Constraint, GModelElement<?, ?, ?, ?>, GColumn<?, ?>>
implements 
	SQLDataType<Schema, GColumn<?, ?>>
{


	/**
	 * Creates a new instance of SQLDataType by specific parameters.
	 * 
	 * @param name An identifier for the SQLDataType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the SQLDataType within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this SQLDataType is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
	 * @return A new instance of SQLDataType, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>jdbcType</code>, <code>typeName</code> or <code>length</code>
	 * is <code>null</code>.
	 * 
	 */
	public FunSQLDataType( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility, notInstantiable );
	}



//====================================================================================================================================================
// SQLDataType capabilities 
//====================================================================================================================================================



	/**
	 * The number assigned to the data-type by the owning RDBMS.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 * @return The number assigned to the data-type by the owning RDBMS or <code>null</code> if none exists.
	 * 
	 */
	public Integer getTypeNumber() {
		return this.typeNumber;
	}



	/**
	 * The number assigned to the data-type by the owning RDBMS.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 * @param typeNumber The number assigned to the data-type by the owning RDBMS.
	 * 
	 * @return Old value of the property <code>typeNumber</code> or <code>null</code> if none exists.
	 * 
	 */
	public Integer setTypeNumber( Integer typeNumber ) {
		Integer old = this.typeNumber;
		this.typeNumber = typeNumber;
		return old;
	}



	@Override
	public Boolean addImporter(TypeSystem importer) {
		throw new IllegalArgumentException("not yet implemented"); // clear Package issues for SQLTypes first
	}



	@Override
	public Boolean addDependency(Dependency dependency) {
		throw new IllegalArgumentException("not yet implemented"); // clear Package issues SQLTypes first
	}



	@Override
	public Boolean addConstraint(Constraint constraint) {
		throw new IllegalArgumentException("not yet implemented"); // clear Package issues SQLTypes first
	}



//====================================================================================================================================================
// Properties 
//====================================================================================================================================================



	/**
	 * The number assigned to the data-type by the owning RDBMS.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @param typeNumber The number assigned to the data-type by the owning RDBMS.
	 * 
	 * @return Old value of the property <code>typeNumber</code>.
	 * 
	 */
	protected Integer typeNumber = null;
}