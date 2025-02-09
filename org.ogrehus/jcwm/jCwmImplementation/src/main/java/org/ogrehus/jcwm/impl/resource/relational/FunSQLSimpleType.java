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

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.SQLDataType;
import org.ogrehus.jcwm.api.resource.relational.SQLDistinctType;
import org.ogrehus.jcwm.api.resource.relational.SQLSimpleType;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumn;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunDataType;

/**
 * A simple data-type used with an SQL column. 
 * <p>
 * Examples are:
 * </p>
 * <ul>
 * <li>Integer</li>
 * <li>Varchar</li>
 * <li>LOB</li>
 * <li>CLOB</li>
 * <li>etc.</li>
 * </ul>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunSQLSimpleType
extends
	GFunDataType<TypeSystem, GColumn<?, ?>>
implements
	  SQLDataType<TypeSystem, GColumn<?, ?>>
	, SQLSimpleType
{


	/**
	 * Creates a new instance of SQLSimpleType by specific parameters.
	 * 
	 * @param name An identifier for the SQLSimpleType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the SQLSimpleType within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this SQLSimpleType is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 *
	 */
	public FunSQLSimpleType( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility, notInstantiable );
		this.sqlDistinctTypes = new HashSet<SQLDistinctType>();
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



 	public TypeSystem setNamespace( TypeSystem typeSystem ) {
 		return super.setNamespaceGeneric( typeSystem );
 	}



//====================================================================================================================================================
// SQLSimpleTypes capabilities
//====================================================================================================================================================



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Integer getCharacterMaximumLength() {
		return this.characterMaximumLength;
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @param characterMaximumLength corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * 
	 */
	public Integer setCharacterMaximumLength( Integer characterMaximumLength ) {
		Integer old = this.characterMaximumLength;
		this.characterMaximumLength = characterMaximumLength;

		return old;
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Integer getCharacterOctetLength() {
		return this.characterOctetLength;
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @param characterOctetLength corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Integer setCharacterOctetLength( Integer characterOctetLength ) {
		Integer old = this.characterOctetLength;
		this.characterOctetLength = characterOctetLength;
		return old;
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Integer getNumericPrecision() {
		return this.numericPrecision;	
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @param numericPrecision corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Integer setNumericPrecision( Integer numericPrecision ) {
		Integer old = this.numericPrecision;
		this.numericPrecision = numericPrecision;

		return old;
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Short</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Short getNumericPrecisionRadix() {
		return this.numericPrecisionRadix;
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Short</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @param numericPrecisionRadix corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Short setNumericPrecisionRadix( Short numericPrecisionRadix ) {
		Short old = this.numericPrecisionRadix;
		this.numericPrecisionRadix = numericPrecisionRadix;
		return old;
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Short</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Short getNumericScale() {
		return this.numericScale;
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i> : Short</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * 
	 * @param numericScale corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Short setNumericScale( Short numericScale ) {
		Short old = this.numericScale;
		this.numericScale = numericScale;

		return old;
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Integer getDateTimePrecision() {
		return this.dateTimePrecision;
	}



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @param dateTimePrecision corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * @return corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 */
	public Integer setDateTimePrecision( Integer dateTimePrecision ) {
		Integer old = this.dateTimePrecision;
		this.dateTimePrecision = dateTimePrecision;
		return old;
	}



	/**
	 * Ends
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @return The distinct type that use this simpleType
	 * 
	 */
	public Set<SQLDistinctType> getSqlDistinctTypes() {
		return this.sqlDistinctTypes;
	}



	public Boolean addSqlDistinctType( SQLDistinctType distinctType ) {
		if ( distinctType != null && this.sqlDistinctTypes.add( distinctType ) ) {
			distinctType.setSqlSimpleType( this );
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}



	public Boolean removeSqlDistinctType( SQLDistinctType distinctType ){
		if ( this.sqlDistinctTypes.remove( distinctType ) ) {
			distinctType.removeSqlSimpleType(); // remove bi-directional reference
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
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



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * 
	 */
	protected Integer characterMaximumLength = null;



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * 
	 */
	protected Integer characterOctetLength = null;



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * 
	 */
	protected Integer numericPrecision = null;



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * 
	 */
	protected Short numericPrecisionRadix = null;



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * 
	 */
	protected Short numericScale = null;



	/**
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 */
	protected Integer dateTimePrecision = null;



	/**
	 * Ends
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * 
	 */
	protected Set<SQLDistinctType> sqlDistinctTypes = null;



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