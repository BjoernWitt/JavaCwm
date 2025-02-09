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

import java.sql.Types;

import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.resource.relational.QueryColumn;
import org.ogrehus.jcwm.api.resource.relational.SQLDataType;
import org.ogrehus.jcwm.api.resource.relational.SQLDistinctType;
import org.ogrehus.jcwm.api.resource.relational.SQLSimpleType;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.TableColumn;
import org.ogrehus.jcwm.api.resource.relational.ViewColumn;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumn;
import org.ogrehus.jcwm.api.resource.relational.util.types.SqlTypeCharacters;
import org.ogrehus.jcwm.api.resource.relational.util.types.SqlTypeDateTime;
import org.ogrehus.jcwm.api.resource.relational.util.types.SqlTypeNumeric;
import org.ogrehus.jcwm.api.resource.relational.util.types.SqlTypeScaled;
import org.ogrehus.jcwm.impl.foundation.datatypes.util.generic.GFunTypeAlias;

/**
 * A data-type defined as a Distinct Type, per [SQL] standard.
 * 
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
public class FunSQLDistinctType
extends
	GFunTypeAlias<Schema, GColumn<?, ?>, GClassifier<?, ?, ?, ?, ?, ?>>
implements
	  SQLDataType<Schema, GColumn<?, ?>>
	, SQLDistinctType
{


	/**
	 * Creates a new instance of SQLDistinctType by specific parameters.
	 * 
	 * @param name An identifier for the SQLDistinctType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the SQLDistinctType within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this SQLDistinctType is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
	 * @param sqlSimpleType The SQLSimpleType used to define the SQLDstinctType. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>notInstantiable</code>,
	 * <code>type</code> or <code>sqlSimpleType</code> is <code>null</code>.
	 * 
	 */
	public FunSQLDistinctType( String name, Visibility visibility, Boolean notInstantiable, SQLSimpleType sqlSimpleType	) {
		super( name, visibility, notInstantiable, sqlSimpleType );
		setSqlSimpleType( sqlSimpleType );
		this.length    = sqlSimpleType.getCharacterMaximumLength();
		this.precision = sqlSimpleType.getNumericPrecision();
		this.typeNumber = Types.DISTINCT;
	}



	/**
	 * Creates a new instance of SQLDistinctType by initial parameters.
	 * 
	 * @param name An identifier for the SQLDistinctType within its containing Namespace. If <code>null</code> the name of the SQLSimpleType is used.
	 * 
	 * @param sqlSimpleType The SQLSimpleType used to define the SQLDstinctType. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>sqlSimpleType</code> is <code>null</code>.
	 * 
	 */
	public FunSQLDistinctType( String name, SQLSimpleType sqlSimpleType	) {
		this( ((name==null)?sqlSimpleType.getSimpleName():name)
			 , sqlSimpleType.getVisibility()
			 , sqlSimpleType.isAbstract()
			 , sqlSimpleType 
		);
	}	



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Schema setNamespace( Schema schema ) {
		return super.setNamespaceGeneric( schema );
	}



	public Boolean addImporter( TypeSystem typeSystem ) {
		return super.addImporterGeneric( typeSystem );
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



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
	public Boolean addFeature( TableColumn column ) {
		return super.addFeatureGeneric( column );
	}



	/**
	 * Appends a Feature that will be owned by this TypeAlias.
	 * 
	 * @param column that will be owned by this TypeAlias. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addFeature( ViewColumn column ) {
		return super.addFeatureGeneric( column );
	}



	/**
	 * Appends a Feature that will be owned by this TypeAlias.
	 * 
	 * @param column that will be owned by this TypeAlias. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addFeature( QueryColumn column ) {
		return super.addFeatureGeneric( column );
	}



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
	 * @return The length of fixed length character or byte strings. Maximum length if length is variable. Can be <code>null</code> if no value
	 * exists.
	 * 
	 */
	public Integer getLength() {
		if ( this.length == null && this.type instanceof SqlTypeCharacters ) {
			SqlTypeCharacters characterType = (SqlTypeCharacters)this.type;
			if ( characterType.getCharacterOctetLength() != null ) {
				return characterType.getCharacterOctetLength();
			}
			return characterType.getCharacterMaximumLength();
		}
		return this.length;
	}



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
	public Integer getPrecision() {
		if ( this.precision == null ) {
			if ( this.type instanceof SqlTypeNumeric ) {
				return ((SqlTypeNumeric)this.type).getNumericPrecision();
			}
			if ( this.type instanceof SqlTypeDateTime ) {
				SqlTypeDateTime dateType = (SqlTypeDateTime)this.type;
				return dateType.getDateTimePrecision();
			}
		}
		return this.precision;
	}



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
	public Integer getScale() {
		if ( this.scale == null && this.type instanceof SqlTypeScaled ) {
			return Integer.valueOf( ((SqlTypeScaled)this.type).getNumericScale() );
		}
		return this.scale;
	}



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
	public Integer setLength( Integer length ) {
		Integer old = this.length;
		this.length = length;
		return old;
	}



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
	public Integer setPrecision( Integer precision ) {
		Integer old = this.precision;
		this.precision = precision;
		return old;	
	}



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
	public Integer setScale( Integer scale ) {
		Integer old = this.scale;
		this.scale = scale;
		return old;	
	}



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
	public SQLSimpleType getSqlSimpleType() {
		return this.sqlSimpleType;
	}



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
	public SQLSimpleType setSqlSimpleType( SQLSimpleType sqlSimpleType ) {
		if ( sqlSimpleType != null && sqlSimpleType.equals( this.sqlSimpleType ) ) {
			return sqlSimpleType; // no changes, cause its the same namespace
		}

		SQLSimpleType old = this.sqlSimpleType; // return value 
		if ( old != null && old.getSqlDistinctTypes().contains( this ) ) {
			old.removeSqlDistinctType( this );
		}

		this.sqlSimpleType = sqlSimpleType;
		setTypeGeneric( sqlSimpleType );
		if ( this.sqlSimpleType != null ) {
			if ( !this.sqlSimpleType.getSqlDistinctTypes().contains( this ) ) {
				this.sqlSimpleType.addSqlDistinctType( this );
			}
		}

		return old;
	}



	public Boolean removeSqlSimpleType() {
		if ( this.sqlSimpleType == null ) {
			return Boolean.TRUE;
		}
		SQLSimpleType old = this.sqlSimpleType;
		this.sqlSimpleType = null;

		return old.removeSqlDistinctType( this );
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
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunSQLDistinctType" );
		if ( this.typeNumber != null ) {
			out.append( " | typeNumber=" + this.typeNumber );
		}
		if ( getLength() != null ) {
			out.append( " | length=" + getLength() );
		}
		if ( getPrecision() != null ) {
			out.append( " | precision=" + getPrecision() );
		}
		if ( getScale() != null ) {
			out.append( " | scale=" + getScale() );
		}
		if ( this.sqlSimpleType != null ) {
			out.append( " | sqlSimpleType=" + this.sqlSimpleType.getSimpleName() 
					  + "<" + this.sqlSimpleType.getClass().getSimpleName() + ">" );
		}
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
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
	 * @param typeNumber The number assigned to the data-type by the owning RDBMS.
	 * 
	 * @return Old value of the property <code>typeNumber</code>.
	 * 
	 */
	protected Integer length = null;



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
	 * @param typeNumber The number assigned to the data-type by the owning RDBMS.
	 * 
	 * @return Old value of the property <code>typeNumber</code>.
	 * 
	 */
	protected Integer precision = null;



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
	 * @param typeNumber The number assigned to the data-type by the owning RDBMS.
	 * 
	 * @return Old value of the property <code>typeNumber</code>.
	 * 
	 */
	protected Integer scale = null;



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
	 * @param typeNumber The number assigned to the data-type by the owning RDBMS.
	 * 
	 * @return Old value of the property <code>typeNumber</code>.
	 * 
	 */
	protected SQLSimpleType sqlSimpleType = null;
}