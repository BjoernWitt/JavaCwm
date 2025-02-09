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

import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.StructuralFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.dependencyclient.ClientDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.importedelements.Importer;
import org.ogrehus.jcwm.api.resource.relational.util.Nullable;


/**
 * A column in a result set, a view, a table, or an SQLStructuredType.
 *
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation-C-3]</b> The scale attribute is valid only if the precision attribute is specified.</li>
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
public interface Column
extends
	  StructuralFeature
	, ClientDependency<Dependency>
	, Importer<Catalog>
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Adds a dependency to this Column.
	 * 
	 * @param dependency A Dependency in witch this Column is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds an importer (Package) to this Column, that will contain this Column as imported.
	 * 
	 * @param catalog The specific package, that will contain this Column.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( Catalog catalog );



//====================================================================================================================================================
// StructuralFeature capabilities	
//====================================================================================================================================================



	/**
	 * Sets a new reference to this <code>type</code>.
	 *  
	 * <ul>
	 * <li><i>class</i>: GClassifier</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param sqlDistinctType The new SQLDataType describing the type of this Column.
	 * 
	 * @return The old SQLDataType describing the type of this Column. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>dataType</code> is <code>null</code>. 
	 * 
	 */
	SQLDataType<?, ?> setType( SQLDistinctType sqlDistinctType );



	/**
	 * Sets a new reference to this <code>type</code>.
	 *  
	 * <ul>
	 * <li><i>class</i>: GClassifier</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param sqlSimpleType The new SQLDataType describing the type of this Column.
	 * 
	 * @return The old SQLDataType describing the type of this Column. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>dataType</code> is <code>null</code>. 
	 * 
	 */
	SQLDataType<?, ?> setType( SQLSimpleType sqlSimpleType );



	/**
	 * Sets a new reference to this <code>type</code>.
	 *  
	 * <ul>
	 * <li><i>class</i>: GClassifier</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param sqlStructuredType The new SQLDataType describing the type of this Column.
	 * 
	 * @return The old SQLDataType describing the type of this Column. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>dataType</code> is <code>null</code>. 
	 * 
	 */
	SQLDataType<?, ?> setType( SQLStructuredType sqlStructuredType );



//====================================================================================================================================================
// Column capabilities	
//====================================================================================================================================================



	/**
	 * The name of the character set used for the values in the column. 
	 * <p>
	 * This field applies only to columns whose data-type is a character string.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return The name of the character set used for the values in the column. Can't be <code>null</code>. 
	 * 
	 */
	String getCharacterSetName();



	/**
	 * The name of the character set used for the values in the column. 
	 * <p>
	 * This field applies only to columns whose data-type is a character string.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param characterSetName The name of the character set used for the values in the column. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>characterSetName</code>. Can't be <code>null</code>. 
	 * 
	 * @throws NullPointerException If the parameter <code>characterSetName</code> is <code>null</code>.
	 * 
	 */
	String setCharacterSetName( String characterSetName );



	/**
	 * The name of the collation sequence used to sort the data values in the column.
	 * <p>
	 * This applies only to columns whose data-type is a form of character string.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return The name of the collation sequence used to sort the data values in the column. Can't be <code>null</code>. 
	 * 
	 */
	String getCollationName();



	/**
	 * The name of the collation sequence used to sort the data values in the column. 
	 * <p>
	 * This applies only to columns whose data-type is a form of character string.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param collationName The name of the collation sequence used to sort the data values in the column. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>collationName</code>. Can't be <code>null</code>. 
	 * 
	 * @throws NullPointerException If the parameter <code>collationName</code> is <code>null</code>.
	 * 
	 */
	String setCollationName( String collationName );



	/**
	 * Indicates if null values are valid in this column.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : NullableType</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return Indicates if null values are valid in this column.
	 * 
	 */
	Nullable getNullable();



	/**
	 * Indicates if null values are valid in this column.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i> : NullableType</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param nullable Indicates if null values are valid in this column.
	 * 
	 * @return Old value of the property <code>nullable</code>. Can't be <code>null</code>. 
	 * 
	 * @throws NullPointerException If the parameter <code>nullable</code> is <code>null</code>.
	 * 
	 */
	Nullable setNullable( Nullable nullable );



	/**
	 * The length of fixed length character or byte strings. 
	 * <p>
	 * Maximum length if length is variable. 
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @return The length of fixed length character or byte strings. May be <code>null</code> if none exists. 
	 * 
	 */
	Integer getLength();



	/**
	 * The length of fixed length character or byte strings. 
	 * <p>
	 * Maximum length if length is variable. 
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @param length The length of fixed length character or byte strings.
	 * 
	 * @return Old value of the property <code>length</code>. May be <code>null</code>.
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
	 * <li><i>constraints</i> : Scale must be specified when precision is 
	 * specified</li>  
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[Foundation-C-3]</b> The scale attribute is valid only if the precision attribute is specified.</li>
	 * </ul>
	 * 
	 * @return The total number of digits in the field. May be <code>null</code> if none exists. 
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
	 * <li><i>constraints</i> : Scale must be specified when precision is specified</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[Foundation-C-3]</b> The scale attribute is valid only if the precision attribute is specified.</li>
	 * </ul>
	 * 
	 * @param precision The total number of digits in the field.
	 * 
	 * @return Old value of the property <code>precision</code>. May be <code>null</code>, if no value exists. 
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
	 * <li><i>type</i>          : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @return The number of digits on the right of the decimal separator. May be <code>null</code>, if none exists.
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
	 * <li><i>type</i>          : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @param scale The number of digits on the right of the decimal separator. May be <code>null</code>, if no value exists.
	 * 
	 * @return Old value of the property <code>scale</code>. May be <code>null</code>, if no value exists. 
	 * 
	 */
	Integer setScale( Integer scale );



	/**
	 * Registers a defaultValue for this column. 
	 * <p>
	 * Default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be null).
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @return The value for this column if no other is specified. May be <code>null</code>, if no defaultValue exists.
	 * 
	 */
	String getDefaultValue();



	/**
	 * Registers a defaultValue for this column. 
	 * <p>
	 * Default value for the column, which should be interpreted as a string when the value is enclosed in single 
	 * quotes (may be null).
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @param defaultValue The new default value for this column.
	 * 
	 * @return The old value for this column. May be <code>null</code>, if no defaultValue exists.
	 * 
	 * 
	 */
	String setDefaultValue( String defaultValue );



	/**
	 * Associates Columns of a StructuredType with the Type they reference in the REF clause.
	 * 
	 * <p>
	 * The column, used in an SQLStructuredType is a REF to a type. This references the REFed SQLStructuredType.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : SQLStructuredType</li>
	 * <li><i>defined by</i> : ColumnRefStructuredType::referencedTableType</li> 
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>inverse</i> : SQLStructuredType::referencingColumn</li>
	 * </ul>
	 * 
	 * @return The column, used in an SQLStructuredType is a REF to a type. This references the REFed SQLStructuredType. May be <code>null</code> if 
	 * none exists.
	 * 
	 */
	SQLStructuredType getReferencedTableType();



	/**
	 * Registers the autoIncrement.
	 *  
	 * @param isAutoIncrement <code>YES</code> enables the autoIncrement, <code>NO</code> disables the autoIncrement and
	 * <code>null</code> declares autoIncrement is unknown.
	 *  
	 * @return The old value of the property <code>autoIncrement</code>.
	 * 
	 */
	Boolean setAutoIncrement( String isAutoIncrement );



	/**
	 * Registers the autoIncrement.
	 *  
	 * @param isAutoIncrement <code>true</code> enables the autoIncrement, <code>false</code> disables the autoIncrement and <code>null</code> 
	 * declares autoIncrement is unknown.
	 *  
	 * @return The old value of the property <code>autoIncrement</code>.
	 * 
	 */
	Boolean setAutoIncrement( Boolean isAutoIncrement );



	/**
	 * Returns whether the autoIncrement is enabled, disabled or unknown.
	 * 
	 * @return <code>true</code> autoIncrement is enabled, <code>false</code> the autoIncrement is disabled and <code>null</code> declares 
	 * autoIncrement is unknown.
	 * 
	 */
	Boolean isAutoIncrement();



	/**
	 * Associates Columns of a StructuredType with the Type they reference in the REF clause.
	 * 
	 * <p>
	 * The column, used in an SQLStructuredType is a REF to a type. This references the REFed SQLStructuredType.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : SQLStructuredType</li>
	 * <li><i>defined by</i> : ColumnRefStructuredType::referencedTableType</li> 
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>inverse</i> : SQLStructuredType::referencingColumn</li>
	 * </ul>
	 * 
	 * @param sqlStructuredType The column, used in an SQLStructuredType is a REF to a type. This references the REFed SQLStructuredType.
	 * 
	 * @return Old value of the property <code>sqlStructuredType</code>. May be <code>null</code>, if no value exists.
	 * 
	 */
	SQLStructuredType setReferencedTableType( SQLStructuredType sqlStructuredType );



	Boolean removeReferencedTableType();



	/**
	 * Associates Columns with NamedColumnSets they reference in their OPTIONS clause.
	 * <p>
	 * Reference to the NamedColumnSet (Table or View) indicated in the SCOPE clause of the Column definition.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : NamedColumnSet</li>
	 * <li><i>defined by</i> : ColumnOptionsColumnSet::optionScopeColumnSet</li> 
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>inverse</i> : NamedColumnSet::optionScopeColumn</li>
	 * </ul>
	 * 
	 * @return Reference to the NamedColumnSet (Table or View) indicated in the SCOPE clause of the Column definition. May be <code>null</code>, if 
	 * none exists.
	 *   
	 */
	NamedColumnSet<?> getOptionScopeColumnSet();



	/**
	 * Associates Columns with NamedColumnSets they reference in their OPTIONS clause.
	 * <p>
	 * Reference to the NamedColumnSet (Table or View) indicated in the SCOPE clause of the Column definition.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : NamedColumnSet</li>
	 * <li><i>defined by</i> : ColumnOptionsColumnSet::optionScopeColumnSet</li> 
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>inverse</i> : NamedColumnSet::optionScopeColumn</li>
	 * </ul>
	 * 
	 * @param namedColumnSet Reference to the NamedColumnSet (Table or View) indicated in the SCOPE clause of the Column definition. 
	 * 
	 * @return Old value of the property <code>namedColumnSet</code>. May be <code>null</code>, if no value exists. 
	 * 
	 */
	NamedColumnSet<?> setOptionScopeColumnSet( NamedColumnSet<?> namedColumnSet );



	Boolean removeOptionScopeColumnSet();
}