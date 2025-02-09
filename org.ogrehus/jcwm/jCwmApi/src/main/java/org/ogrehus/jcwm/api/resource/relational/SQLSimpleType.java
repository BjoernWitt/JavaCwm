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

import java.util.Set;

import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDataType;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumn;

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
 * TODO:
 * <ul>
 * <li>USER-DEFINED => USER_DEFINED_TYPE_CATALOG, USER_DEFINED_TYPE_SCHEMA,USER_DEFINED_TYPE_NAME ) IS NOT NULL </li>
 * <li>REF          => USER_DEFINED_TYPE_CATALOG, USER_DEFINED_TYPE_SCHEMA,USER_DEFINED_TYPE_NAME ) IS NOT NULL </li>
 * <li>ARRAY        => MAXIMUM_CARDINALITY IS NOT NULL</li>
 * <li>ROW          => ALL NULL</li>  
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
public interface SQLSimpleType
extends
	  GDataType<TypeSystem, GColumn<?, ?>>
	, SQLDataType<TypeSystem, GColumn<?, ?>> 
{


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
	Integer getCharacterMaximumLength();



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
	Integer setCharacterMaximumLength( Integer characterMaximumLength );



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
	Integer getCharacterOctetLength();



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
	Integer setCharacterOctetLength( Integer characterOctetLength );



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
	Integer getNumericPrecision();



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
	Integer setNumericPrecision( Integer numericPrecision );



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
	Short getNumericPrecisionRadix();



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
	Short setNumericPrecisionRadix( Short numericPrecisionRadix );



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
	Short getNumericScale();



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
	Short setNumericScale( Short numericScale );



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
	Integer getDateTimePrecision();



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
	Integer setDateTimePrecision( Integer dateTimePrecision );



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
	Set<SQLDistinctType> getSqlDistinctTypes();



	Boolean addSqlDistinctType( SQLDistinctType distinctType );



	Boolean removeSqlDistinctType( SQLDistinctType distinctType );
}