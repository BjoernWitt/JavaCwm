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
package org.ogrehus.jcwm.api.resource.relational;

import org.ogrehus.jcwm.api.foundation.datatypes.util.generic.GTypeAlias;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumn;

/**
 * A data-type defined as a Distinct Type, per [SQL] standard.
 * <p>
 * SQL:1999 specifies the SQL:1999 object model. It adds User Defined Types (UDTs) to SQL. There are two types of UDTs:
 * </p>
 * <ul> 
 * <li><b>distinct types</b> - A distinct type is based on a built-in data type, such as INTEGER, but whose values cannot be directly mixed in
 * operation with that built-in type.</li>
 * <li><b>structured types</b> - An internal structure such as an address type that might have street, state, and postal code attributes as part of
 * the structure.</li>
 * </ul>
 * 
 * <p>
 * Structured types can be given specific user-defined functions and participate in type hierarchies with other structured types. Single inheritance
 * is supported. Multiple inheritance of interfaces (as in Java) is not supported. Both distinct and structured types can be used as the data type for
 * columns, SQL variables, a field of a row type, or as attribute of another UDT.
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
public interface SQLDistinctType
extends
	  SQLDataType<Schema, GColumn<?, ?>>
	, GTypeAlias<Schema, GColumn<?, ?>, GClassifier<?, ?, ?, ?, ?, ?>>
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	Schema setNamespace( Schema schema );



	Boolean addImporter( TypeSystem typeSystem );



	Boolean addDependency( Dependency dependency );



	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// Classifier capabilities	
//====================================================================================================================================================



	/**
	 * Appends a Feature that will be owned by this TypeAlias.
	 * 
	 * @param column that will be owned by this TypeAlias.
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addFeature( TableColumn column );



	/**
	 * Appends a Feature that will be owned by this TypeAlias.
	 * 
	 * @param column that will be owned by this TypeAlias.
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addFeature( ViewColumn column );



	/**
	 * Appends a Feature that will be owned by this TypeAlias.
	 * 
	 * @param column that will be owned by this TypeAlias.
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addFeature( QueryColumn column );



//====================================================================================================================================================
// SQLDistinctType capabilities	
//====================================================================================================================================================



	/**
	 * The length of fixed length character or byte strings. Maximum length if length is variable.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @return The length of fixed length character or byte strings. Maximum length if length is variable. Can be <code>null</code> if no value exists.
	 * 
	 */
	Integer getLength();



	/**
	 * The length of fixed length character or byte strings. Maximum length if length is variable.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @param length The length of fixed length character or byte strings. Maximum length if length is variable.
	 * 
	 * @return Old value of the property <code>length</code>. Can be <code>null</code> if none exists.
	 * 
	 */
	Integer setLength( Integer length );



	/**
	 * The total number of digits in the field.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @return The total number of digits in the field. Can be <code>null</code> if none exists.
	 * 
	 */
	Integer getPrecision();



	/**
	 * The total number of digits in the field.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @param precision The total number of digits in the field.
	 * 
	 * @return Old value of the property <code>precision</code> or <code>null</code> if no value exists.
	 * 
	 */
	Integer setPrecision( Integer precision );



	/**
	 * The number of digits on the right of the decimal separator.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li> 
	 * </ul>
	 * 
	 * @return The number of digits on the right of the decimal separator. Can be <code>null</code> if none exists.
	 * 
	 */
	Integer getScale();



	/**
	 * The number of digits on the right of the decimal separator.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @param scale The number of digits on the right of the decimal separator.
	 * 
	 * @return Old value of the property <code>scale</code>.
	 * 
	 */
	Integer setScale( Integer scale );



	/**
	 * The SQLSimpleType used to define the SQLDstinctType.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : SQLSimpleType</li>
	 * <li><i>definedBy</i> : SQLDistinctTypeWithSQLSimpleType</li> 
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @return The SQLSimpleType used to define the SQLDstinctType.
	 * 
	 */
	SQLSimpleType getSqlSimpleType();



	/**
	 * The SQLSimpleType used to define the SQLDstinctType.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : SQLSimpleType</li>
	 * <li><i>definedBy</i> : SQLDistinctTypeWithSQLSimpleType</li> 
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @param sqlSimpleType The SQLSimpleType used to define the SQLDstinctType. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>sqlSimpleType</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>sqlSimpleType</code> is <code>null</code>.
	 * 
	 */
	SQLSimpleType setSqlSimpleType( SQLSimpleType sqlSimpleType );



	Boolean removeSqlSimpleType();
}