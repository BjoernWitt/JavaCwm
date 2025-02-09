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

import org.ogrehus.jcwm.api.foundation.datatypes.QueryExpression;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.core.BooleanExpression;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.Catalog;
import org.ogrehus.jcwm.api.resource.relational.CheckConstraint;
import org.ogrehus.jcwm.api.resource.relational.DefaultValueExpression;
import org.ogrehus.jcwm.api.resource.relational.ForeignKey;
import org.ogrehus.jcwm.api.resource.relational.PrimaryKey;
import org.ogrehus.jcwm.api.resource.relational.Procedure;
import org.ogrehus.jcwm.api.resource.relational.RelationalPackage;
import org.ogrehus.jcwm.api.resource.relational.SQLDistinctType;
import org.ogrehus.jcwm.api.resource.relational.SQLIndex;
import org.ogrehus.jcwm.api.resource.relational.SQLSimpleType;
import org.ogrehus.jcwm.api.resource.relational.SQLStructuredType;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.Table;
import org.ogrehus.jcwm.api.resource.relational.Trigger;
import org.ogrehus.jcwm.api.resource.relational.UniqueConstraint;
import org.ogrehus.jcwm.api.resource.relational.View;
import org.ogrehus.jcwm.api.resource.relational.util.ActionOrientation;
import org.ogrehus.jcwm.api.resource.relational.util.ConditionTiming;
import org.ogrehus.jcwm.api.resource.relational.util.Deferability;
import org.ogrehus.jcwm.api.resource.relational.util.EventManipulation;
import org.ogrehus.jcwm.api.resource.relational.util.ProcedureType;
import org.ogrehus.jcwm.api.resource.relational.util.ReferentialRule;
import org.ogrehus.jcwm.impl.foundation.datatypes.FunQueryExpression;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcBLOB;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcBigInt;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcBinary;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcBit;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcBoolean;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcCLOB;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcChar;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcDatalink;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcDate;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcDecimal;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcDouble;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcFloat;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcInteger;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcLongNVarChar;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcLongVarBinary;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcLongVarChar;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcNCLOB;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcNChar;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcNULL;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcNVarChar;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcNumeric;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcOther;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcREF;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcReal;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcRowId;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcSmallInt;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcSqlXml;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcTime;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcTimestamp;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcTinyInt;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcVarBinary;
import org.ogrehus.jcwm.impl.resource.relational.util.types.FunJdbcVarChar;


public class FunRelationalPackage
extends 
	RelationalPackage
{


	/**
	 * Factory method that creates a new instance of Catalog by specific parameters.
	 * <p>
	 * A Catalog is the unit of login and identification. It also identifies the scope of SQL statements: the tables contained in a catalog can be
	 * used in a single SQL statement.
	 * </p>
	 * 
	 * @param name An identifier for the Catalog within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param defaultCharacterSetName The name of the default character set used for the values in the column. Must not be <code>null</code>.
	 * 
	 * @param defaultCollationName The name of the default collation sequence used to sort the data values in the column. This applies only to columns
	 * whose data-type is a form of character string. Can't be <code>null</code>.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @return A new instance of Catalog, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>defaultCharacterSetName</code>,
	 * <code>defaultCollationName</code>  is <code>null</code>.
	 * 
	 */
	public Catalog createCatalog( String name , String defaultCharacterSetName, String defaultCollationName	) {
		return new FunCatalog( name, Visibility._public, defaultCharacterSetName, defaultCollationName );
	}



	/**
	 * Factory method that creates a new instance of CheckConstraint by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @param name An identifier for the CheckConstraint within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param body A BooleanExpression that must be true when evaluated for an instance of a system to be well formed. A boolean expression defining
	 * the CheckConstraint. Expressions are written as strings in a designated language. For the model to be well formed, the expression must always
	 * yield a true value when evaluated for instances of the constrained elements at any time when the system is stable; that is, not during the
	 * execution of an atomic operation. Must not be <code>null</code>.
	 * 
	 * @param deferrability A textual description of the type of information this Description represents. Must not be <code>null</code>.
	 * 
	 * @return A new instance of CheckConstraint, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>body</code>
	 * is <code>null</code>.
	 * 
	 */
	public CheckConstraint createCheckConstraint( String name, BooleanExpression body, Deferability deferability ) {
		return new FunCheckConstraint( name, body, deferability );
	}



	/**
	 * Factory method that creates a new instance of CheckConstraint by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>deferrability</b> - <code>Deferrability.getDefault()</code>.
	 * </ul>
	 * 
	 * @param name An identifier for the CheckConstraint within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param body A BooleanExpression that must be true when evaluated for an instance of a system to be well formed. A boolean expression defining
	 * the CheckConstraint. Expressions are written as strings in a designated language. For the model to be well formed, the expression must always
	 * yield a true value when evaluated for instances of the constrained elements at any time when the system is stable; that is, not during the
	 * execution of an atomic operation. Must not be <code>null</code>.
	 * 
	 * @return A new instance of CheckConstraint, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>body</code> is <code>null</code>.
	 * 
	 */
	public CheckConstraint createCheckConstraint( String name, BooleanExpression body ) {
		return new FunCheckConstraint( name, body, Deferability.getDefault() );
	}



//	/**
//	 * Factory method that creates a new instance of this ColumnValue by specific parameters.
//	 * 
//	 * @param name An identifier for the ColumnValue within its containing Namespace. Must not be <code>null</code>.
//	 * 
//	 * @param visibility Specifies extent of the visibility of the ColumnValue within its owning Namespace. Must not be 
//     * <code>null</code>.
//	 * 
//	 * @param dataType The DataType that declares the structure of the Instance. Must not be <code>null</code>.  
//	 * 
//	 * @param value A string representation of the value. Must not be <code>null</code>.
//	 * 
//	 * @return A new instance of DataValue, by initial parameters.
//	 * 
//	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, 
//     * <code>classifier</code> or <code>value</code> is <code>null</code>. 
//	 * 
//	 */
//	abstract public ColumnValue createColumnValue( String name, Visibility visibility, DataType dataType, String value );
//	
//	
//	/**
//	 * Factory method that creates a new instance of GRowSet by specific parameters.
//	 * <p>
//	 * An instance of a ColumnSet.
//	 * </p>
//	 * 
//	 * 
//	 * @param name An identifier for the GRowSet within its containing Namespace. Must not be <code>null</code>.
//	 * 
//	 * @param visibility Specifies extent of the visibility of the GRowSet within its owning Namespace. Must not be 
//     * <code>null</code>.
//	 *
//	 * @param classifier The Classifier that declares the structure of the GRowSet. Must not be <code>null</code>.
//	 * 
//	 * @return A new instance of GRowSet, by initial parameters. 
//	 * 
//	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or 
//     * <code>classifier</code> is <code>null</code>.  
//	 * 
//	 */		
//	abstract public GRowSet createRowSet( String name, Visibility visibility, Classifier classifier );	



	/**
	 * Factory method that creates a new instance of Foreign Key by specific parameters.
	 * 
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * <p>
	 * A Foreign Key associates columns from one table with columns of another table.
	 * </p>
	 * 
	 * @param name An identifier for the Foreign Key within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param uniqueConstraint Identifies the UniqueConstraint instance that serves as the �primary key� for this ForeignKey instance.
	 * 
	 * @param deleteRule The disposition of the data records containing the foreign key value when the record of the matching primary key is deleted.
	 * Must not be <code>null</code>.
	 * 
	 * @param updateRule The disposition of the data records containing the foreign key value when the record of the matching primary key is updated.
	 * Must not be <code>null</code>.
	 * 
	 * @param deferability Indicates if the validity of the ForeignKey is to be tested at each statement or at the end of a transaction. Must not be
	 * <code>null</code>.
	 * 
	 * @return A new instance of Foreign Key, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>uniqueConstraint</code>, <code>deleteRule</code>,
	 * <code>updateRule</code> or <code>deferability</code> is <code>null</code>.
	 * 
	 */
	public ForeignKey createForeignKey(
		  String name
		, UniqueConstraint uniqueConstraint
		, ReferentialRule deleteRule
		, ReferentialRule updateRule
		, Deferability deferability
	) {
		return new FunForeignKey( name, uniqueConstraint, deleteRule, updateRule, deferability );
	}



//	/**
//	 * Factory method that creates a new instance of NamedColumnSet by specific parameters.
//	 * <p>
//	 * A NamedColumnSet is a cataloged set of columns, which may be Table or View.
//	 * </p>
//     * <p>
//     * Note for typed tables: It is assumed that the typed table will own a set of columns conforming to the type they 
//     * are OF. This set of columns allows the manipulation of the table by products that ignore this [SQL] extension. It
//     * also allows the columns of type REF, to be copied to a column with a SCOPE reference.
//     * </p> 
//	 * 
//	 * @param name An identifier for the NamedColumnSet within its containing Namespace. Must not be <code>null</code>.
//	 * 
//	 * @param visibility Specifies extent of the visibility of the NamedColumnSet within its owning Namespace. Must not 
//     * be <code>null</code>.
//	 * 
//	 * @param notInstantiable <code>true</code> if this NamedColumnSet is abstract and can not be instantiated, 
//     * <code>false</code> otherwise.
//	 *
//	 * @return A new instance of NamedColumnSet by initial parameters.
//	 * 
//	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or 
//	 * <code>notInstantiable</code> is <code>null</code>.
//	 * 
//	 */	
//	abstract public NamedColumnSet createNamedColumnSet( String name, Visibility visibility, Boolean notInstantiable );	



	/**
	 * Factory method that creates a new instance of PrimaryKey by specific parameters.
	 * <p>
	 * A condition to define uniqueness of rows in a table. An example of UniqueConstraint is a primary key.
	 * </p>
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul> 
	 * 
	 * @param name An identifier for the PrimaryKey within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param deferrability The type of the validity of the PrimaryKey that is to be tested at each statement or at the end of a transaction. Must not
	 * be <code>null</code>.
	 * 
	 * @return A new instance of PrimaryKey, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>deferability</code> is <code>null</code>.
	 * 
	 */
	public PrimaryKey createPrimaryKey( String name, Deferability deferability	) {
		return new FunPrimaryKey( name, deferability );
	}



	/**
	 * Factory method that creates a new instance of Procedure by specific parameters.
	 * 
	 * <p>
	 * A Procedure describes Relational DBMS Stored procedures and functions.
	 * </p>
	 * 
	 * @param name An identifier for the Procedure within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Procedure within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param isQuery Specifies whether an execution of the Procedure leaves the state of the system unchanged. <code>true</code> indicates that the
	 * state is unchanged; <code>false</code> indicates that side-effects may occur. Must not be <code>null</code>.
	 * 
	 * @param body A specification of the Procedure in some appropriate form (such as a programming language). The exact form of a Method
	 * specification and knowledge of the language in which it is described is outside the scope of the CWM. Must not be <code>null</code>.
	 * 
	 * @param type Function or Procedure. This indicates whether this object returns a value or not. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Procedure, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, <code>isQuery</code>,
	 * <code>body</code> or <code>type</code> is <code>null</code>.
	 * 
	 */
	public Procedure createProcedure( String name, ProcedureExpression body, ProcedureType type	) {
		return new FunProcedure( name, body, type );
	}



	/**
	 * Factory method that creates a new instance of Procedure by specific parameters.
	 * 
	 * <p>
	 * A Procedure describes Relational DBMS Stored procedures and functions.
	 * </p>
	 * 
	 * @param name An identifier for the Procedure within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param specification Must not be <code>null</code>.
	 * 
	 * @param body A specification of the Procedure in some appropriate form (such as a programming language). The exact form of a Method
	 * specification and knowledge of the language in which it is described is outside the scope of the CWM. Must not be <code>null</code>.
	 * 
	 * @param type Function or Procedure. This indicates whether this object returns a value or not. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Procedure, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, <code>isQuery</code>,
	 * <code>body</code> or <code>type</code> is <code>null</code>.
	 * 
	 */
	public Procedure createProcedure( 
		  String name
		, Operation specification 
		, ProcedureExpression body
		, ProcedureType type
	) {
		return new FunProcedure( name, specification, body, type );
	}



//	/**
//	 * Factory method that creates a new instance of QueryRow by specific parameters.
//	 * 
//	 * @param name An identifier for the QueryRow within its containing Namespace. Must not be <code>null</code>.
//	 * 
//	 * @param visibility Specifies extent of the visibility of the QueryRow within its owning Namespace. Must not be 
//     * <code>null</code>.
//	 *
//	 * @param columnSet The Classifier that declares the structure of the QueryRow. Must not be <code>null</code>.
//	 * 
//	 * @return A new instance of QueryRow, by initial parameters. 
//	 * 
//	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or 
//     * <code>classifier</code> is <code>null</code>.  
//	 * 
//	 */		
//	abstract public QueryRow createRow( String name, Visibility visibility, ColumnSet columnSet );



	/**
	 * Factory method that creates a new instance of Schema by specific parameters.
	 * 
	 * @param name An identifier for the Schema within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @return A new instance of Schema, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	public Schema createSchema( String name	) {
		return new FunSchema( name, Visibility._public );
	}



	/**
	 * Factory method that creates a new instance of SQLIndex by specific parameters.
	 * 
	 * <p>
	 * An Index on a table. 
	 * </p>
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul> 
	 * 
	 * @param name An identifier for the SQLIndex within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param isUnique The isUnique attribute is <code>true</code> if the SQLIndex instance guarantees all of its instances have a unique key value.
	 * Must not be <code>null</code>.
	 * 
	 * @param isSorted If <code>true</code>, the SQLIndex instance is maintained in a sorted order. Must not be <code>null</code>.
	 * 
	 * @param isPartitioning If <code>true</code>, this SQLIndex instance is used as a partitioning index. Must not be <code>null</code>.
	 * 
	 * @param spannedClass Identifies the Class instance spanned by the SQLIndex instance. Must not be <code>null</code>.
	 * 
	 * @param filterCondition Which subset of the table is indexed. Must not be <code>null</code>.
	 *
	 * @param isNullable <code>true</code> if the entries in this Index can be a null-value, <code>false</code> otherwise. Must not
	 * be <code>null</code>.
	 *  
	 * @param autoUpdate <code>true</code> if the index is updated automatically, <code>false</code> otherwise. Must not be <code>null</code>.
	 * 
	 * @return A new instance of SQLIndex, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>isUnique</code>, <code>isSorted</code>,
	 * <code>isPartitioning</code>, <code>spannedClass</code>, <code>firstIndexedFeature</code>, <code>filterCondition</code>, <code>isNullable</code>
	 * or <code>autoUpdate</code> is <code>null</code>.
	 * 
	 */
	public SQLIndex createSQLIndex(
		  String name
		, Boolean isUnique
		, Boolean isSorted
		, Boolean isPartitioning
		, Table table
		, String filterCondition
		, Boolean isNullable
		, Boolean autoUpdate
	) {
		return new FunSQLIndex( name
							  , isUnique
							  , isSorted
							  , isPartitioning
							  , table
							  , filterCondition
							  , isNullable
							  , autoUpdate 
						  );
	}



	/**
	 * Creates a new instance of SQLSimpleType by specific parameters.
	 * 
	 * @param name An identifier for the SQLSimpleType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the SQLSimpleType within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this SQLSimpleType is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
     * @return A new instance of SQLSimpleType by specific parameters.
     * 
     * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
     * is <code>null</code>.
	 *
	 */
	public SQLSimpleType createSQLSimpleType(
		  Integer jdbcType 					// => SQL type from java.sql.Types
		, String  typeName					// => SQL type name, for a UDT type the type name is fully qualified
		, Integer numericPrecision			// => precision
		, Integer characterMaximumLength	// => length in bytes of data
		, Short   numericScale				// => scale -  null is returned for data types where SCALE is not applicable.
		, Short   radix 					// => radix
		, Integer charOctedLength			// => the maximum length of binary and character based columns. For any other datatype the returned value is a NULL	
	) {
		if ( jdbcType == null ) {
			throw new NullPointerException( "The parameter: jdbcType must not be null." );
		}
		if ( jdbcType == Types.DISTINCT ) {
			throw new IllegalArgumentException( 
				"The parameter: jdbcType must not equal java.sql.Types.DISTINCT! Use methode createSQLDistinctType(...) instead. " );
		}
		if ( jdbcType == Types.STRUCT ) {
			throw new IllegalArgumentException( "The parameter: jdbcType must not equal java.sql.Types.STRUCT! Use methode createSQLStucturedType(...) instead. " );
		}		
		switch( jdbcType ) {
		case Types.BIT:				return new FunJdbcBit( typeName );
		case Types.BIGINT:			return new FunJdbcBigInt( typeName );
		case Types.BINARY:			return new FunJdbcBinary( typeName, characterMaximumLength );
		case Types.BLOB:			return new FunJdbcBLOB( typeName, characterMaximumLength );
		case Types.BOOLEAN:			return new FunJdbcBoolean( typeName );
		case Types.CHAR:			return new FunJdbcChar( typeName, characterMaximumLength );
		case Types.CLOB:			return new FunJdbcCLOB( typeName, characterMaximumLength );
		case Types.DATALINK:		return new FunJdbcDatalink( typeName );
		case Types.DATE:			return new FunJdbcDate( typeName, numericPrecision );
		case Types.DECIMAL:			return new FunJdbcDecimal( typeName, numericPrecision, numericScale );		
		case Types.DOUBLE:			return new FunJdbcDouble( typeName, numericPrecision, numericScale, radix );
		case Types.FLOAT:			return new FunJdbcFloat( typeName, numericPrecision, radix );
		case Types.INTEGER:			return new FunJdbcInteger( typeName, numericPrecision, radix );
		case Types.LONGVARBINARY: 	return new FunJdbcLongVarBinary( typeName, characterMaximumLength );		
		case Types.LONGVARCHAR:		return new FunJdbcLongVarChar( typeName, characterMaximumLength );
		case Types.LONGNVARCHAR: 	return new FunJdbcLongNVarChar( typeName, characterMaximumLength );
		case Types.NCHAR:			return new FunJdbcNChar( typeName, characterMaximumLength );
		case Types.NCLOB:			return new FunJdbcNCLOB( typeName, characterMaximumLength );
		case Types.NULL: 			return new FunJdbcNULL( typeName );
		case Types.NUMERIC:			return new FunJdbcNumeric( typeName, numericPrecision, numericScale, radix );
		case Types.NVARCHAR:		return new FunJdbcNVarChar( typeName, characterMaximumLength );
		case Types.OTHER:			return new FunJdbcOther( typeName );
		case Types.REAL:			return new FunJdbcReal( typeName, numericPrecision, radix );
		case Types.REF:				return new FunJdbcREF( typeName );
		case Types.ROWID:			return new FunJdbcRowId( typeName );
		case Types.SMALLINT:		return new FunJdbcSmallInt(typeName, numericPrecision, radix );
		case Types.SQLXML:			return new FunJdbcSqlXml( typeName );
		case Types.TIME:			return new FunJdbcTime( typeName, numericPrecision );
		case Types.TIMESTAMP:		return new FunJdbcTimestamp( typeName, numericPrecision );
		case Types.TINYINT:			return new FunJdbcTinyInt( typeName, numericPrecision, radix );
		case Types.VARBINARY:		return new FunJdbcVarBinary(typeName, characterMaximumLength );	
		case Types.VARCHAR:			return new FunJdbcVarChar( typeName, characterMaximumLength );
		default:
			throw new IllegalArgumentException( "The parameter: jdbcType (=" + jdbcType + ") is unknown. " );
		}
	}



	@Override
	public SQLDistinctType createSQLDistinctType(
		  String name
		, Visibility visibility
		, Boolean notInstantiable
		, SQLSimpleType sqlSimpleType
	) {
		return new FunSQLDistinctType( name, visibility, notInstantiable, sqlSimpleType );
	}



	/**
	 * Creates a new instance of SQLDistinctType by specific parameters.
	 * 
	 * <b>Default initialization values:</b>
	 * <ul>
	 * <li><b>visibility</b> - taken from parameter <code>sqlSimpleType</code></li>
	 * <li><b>notInstantiable</b> - taken from parameter <code>sqlSimpleType</code></li> 
	 * </ul>
	 * 
	 * @param name An identifier for the SQLDistinctType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param sqlSimpleType The SQLSimpleType used to define the SQLDstinctType. Must not be <code>null</code>.
	 * 
	 * @return A new instance of SQLDistinctType, by initial parameters
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>notInstantiable</code>,
	 * <code>type</code> or <code>sqlSimpleType</code> is <code>null</code>.
	 *
	 */
	public SQLDistinctType createSQLDistinctType( String name, SQLSimpleType sqlSimpleType	) {
		return new FunSQLDistinctType( name, sqlSimpleType );
	}



	
	/**
	 * Creates a new instance of SQLSimpleType by specific parameters.
	 * 
	 * @param name An identifier for the SQLSimpleType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the SQLSimpleType within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this SQLSimpleType is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
     * @return A new instance of SQLSimpleType by specific parameters.
     * 
     * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
     * is <code>null</code>.
	 *
	 */
	public SQLSimpleType createSQLSimpleType( String name, Visibility visibility, Boolean notInstantiable ) {
		return new FunSQLSimpleType( name, visibility, notInstantiable );	
	}



	@Override
	public DefaultValueExpression createDefaultValueExpression( String columnDef ) {
		return new FunDefaultValueExpression( columnDef );
	}



	/**
	 * Creates a new instance of SQLStructuredType by specific parameters.
	 * 
	 * @param name An identifier for the SQLStructuredType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the SQLStructuredType within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this SQLStructuredType is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
     * @return A new instance of SQLStructuredType by specific parameters.
     * 
     * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
     * is <code>null</code>.
	 *
	 */
	public SQLStructuredType createSQLStructuredType( String name, Visibility visibility, Boolean notInstantiable ) {
		return new FunSQLStructuredType( name, visibility, notInstantiable );
	}



	/**
	 * Creates a new instance of SQLStructuredType by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>notInstantiable</b> - <code>false</code> (not abstract). 
	 * </ul>
	 * 
	 * @param name An identifier for the SQLStructuredType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @return A new instance of SQLStructuredType by specific parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> is <code>null</code>.
	 *
	 */	
	public SQLStructuredType createSQLStructuredType( String name	) {
		return new FunSQLStructuredType( name );
	}



	/**
	 * Factory method that creates a new instance of Table by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>notInstantiable</b> - <code>false</code> (not abstract). 
	 * </ul>	
	 * @param name An identifier for the Table within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param  isSystem <code>true</code> if the table is a System Table, like a part of the system catalog), <code>false</code> otherwise. Must not
	 * be <code>null</code>.
	 * 
	 * @param  isTemporary <code>true</code> if the table content is temporary. SQL92 standards provide two types of temporary tables (local Temporary
	 * and Global Temporary). A System Table, like a part of the system catalog), <code>false</code> otherwise. Must not be <code>null</code>.
	 *
	 * @return A new instance of Table by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>isSystem</code> or <code>isTemporary</code>
	 * is <code>null</code>.
	 * 
	 */	
	public Table createTable( String name, Boolean isSystem, Boolean isTemporary ) {
		return new FunTable( name, isSystem, isTemporary );
	}



	/**
	 * Factory method that creates a new instance of Table by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>notInstantiable</b> - <code>false</code> (not abstract).
	 * <li><b>isSystem</b> - <code>false</code>
	 * <li><b>isTemporary</b> - <code>false</code>
	 * </ul>
	 * 
	 * @param  isTemporary <code>true</code> if the table content is temporary. SQL92 standards provide two types of temporary tables (local Temporary
	 * and Global Temporary). A System Table, like a part of the system catalog), <code>false</code> otherwise. Must not be <code>null</code>.
	 *
	 * @return A new instance of Table by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> is <code>null</code>.
	 * 
	 */
	public Table createTable( String name ) {
		return createTable( name, Boolean.FALSE, Boolean.FALSE );
	}



	/**
	 * Factory method that creates a new instance of Table by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>notInstantiable</b> - <code>false</code> (not abstract).
	 * <li><b>isSystem</b> - <code>true</code>
	 * <li><b>isTemporary</b> - <code>false</code>
	 * </ul>
	 * 
	 * @param  isTemporary <code>true</code> if the table content is temporary. SQL92 standards provide two types of temporary tables (local Temporary
	 * and Global Temporary). A System Table, like a part of the system catalog), <code>false</code> otherwise. Must not be <code>null</code>.
	 *
	 * @return A new instance of Table by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> is <code>null</code>.
	 * 
	 */
	public Table createSystemTable( String name ) {
		return createTable( name, Boolean.TRUE, Boolean.FALSE );
	}



	/**
	 * Factory method that creates a new instance of Trigger by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @param name An identifier for the Trigger within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param eventManipulation Type of event that are using the current Trigger. Must not be <code>null</code>. 
	 *
	 * @param actionCondition A boolean expression that defines when the trigger has to be executed. Must not be <code>null</code>.
	 * 
	 * @param actionStatement The Trigger action itself. Must not be <code>null</code>.
	 * 
	 * @param actionOrientation Indicates if the trigger is called once per statement execution or before or after each row of the table is modified.
	 * Must not be <code>null</code>.
	 * 
	 * @param conditionTiming Indicates if the trigger activity is run before or after the statement or row is modified. Must not
	 * be <code>null</code>.
	 * 
	 * @param conditionReferenceNewTable The alias for the owning table name, used in the actionStatement, to represent the state of the table after
	 * the insert/delete/update. Must not be <code>null</code>.
	 * 
	 * @param conditionReferenceOldTable The alias for the name of the owning table, used in the actionStatement, to represent the state of the table
	 * before the update/delete/insert. Must not be <code>null</code>.
	 * 
	 * @param table The table that owns this Trigger. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Trigger by specific parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>eventManipulation</code>,
	 * <code>actionCondition</code>, <code>actionStatement</code>,<code>actionOrientation</code>, <code>conditionTiming</code>,
	 * <code>conditionReferenceNewTable</code>, <code>conditionReferenceOldTable</code> or <code>table</code> is <code>null</code>.
	 * 
	 */
	@Override	
	public Trigger createTrigger( 
		  String name
		, EventManipulation eventManipulation
		, BooleanExpression actionCondition
		, ProcedureExpression actionStatement
		, ActionOrientation actionOrientation
		, ConditionTiming conditionTiming
		, String conditionReferenceNewTable
		, String conditionReferenceOldTable
		, Table table
	) {
		return new FunTrigger( 
			name
		  , eventManipulation
		  , actionCondition
		  , actionStatement
		  , actionOrientation
		  , conditionTiming
		  , conditionReferenceNewTable
		  , conditionReferenceOldTable
		  , table );
	}



	/**
	 * Factory method that creates a new instance of UniqueConstraint by specific parameters.
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
	 * @return A new instance of UniqueConstraint, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>deferability</code> is <code>null</code>.
	 * 
	 */
	public UniqueConstraint createUniqueConstraint( String name, Deferability deferability	) {
		return new FunUniqueConstraint( name, deferability );
	}



	/**
	 * Factory method that creates a new instance of View by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>notInstantiable</b> - <code>false</code>.
	 * <li><b>readOnly</b> - <code>true</code>. 
	 * </ul>
	 * 
	 * 
	 * @param name An identifier for the View within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param queryExpression The query associated with the View.
	 * 
	 * @return A new instance of View by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>queryExpression</code> is <code>null</code>.
	 * 
	 */
	public View createView( String name, QueryExpression queryExpression ) {
		return new FunView( name, Boolean.TRUE, queryExpression );
	}



	public View createView( String name ) {
		return new FunView( name, Boolean.TRUE, new FunQueryExpression( "" ) );
	}
}