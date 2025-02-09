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

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.foundation.datatypes.QueryExpression;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.core.BooleanExpression;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.util.ActionOrientation;
import org.ogrehus.jcwm.api.resource.relational.util.ConditionTiming;
import org.ogrehus.jcwm.api.resource.relational.util.Deferability;
import org.ogrehus.jcwm.api.resource.relational.util.EventManipulation;
import org.ogrehus.jcwm.api.resource.relational.util.ProcedureType;
import org.ogrehus.jcwm.api.resource.relational.util.ReferentialRule;


public abstract class RelationalPackage {


	/**
	 * Creates a new instance of the RelationalPackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * </p>
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class
	 * <code>org.ogrehus.jcwm.api.resource.relational.RelationalPackage</code>.
	 * 
	 * @return A new instance of ResourcePackage, determined by the specific classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends
	 * <code>org.ogrehus.jcwm.api.resource.relational.RelationalPackage</code> or if the class does not provide a simple public constructor without
	 * any parameters.
	 * 
	 */
	public static final RelationalPackage create( String classPath )
	throws 
		ClassNotFoundException
	{
		try {
			Object foundation = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( foundation instanceof RelationalPackage ) {
				return (RelationalPackage)foundation;
			}
			throw new ClassNotFoundException( "Invalid Class, it must extend org.ogrehus.jcwm.api.resource.relational.ResourcePackage:" + classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid RelationalPackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid RelationalPackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid RelationalPackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}



	/**
	 * Factory method that creates a new instance of Catalog by specific parameters.
	 * <p>
	 * A Catalog is the unit of logon and identification. It also identifies the scope of SQL statements: the tables contained in a catalog can be
	 * used in a single SQL statement.
	 * </p>
	 * 
	 * @param name An identifier for the Catalog within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Catalog within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param defaultCharacterSetName The name of the default character set used for the values in the column. Must not be <code>null</code>.
	 * 
	 * @param defaultCollationName The name of the default collation sequence used to sort the data values in the column. This applies only to columns
	 * whose data-type is a form of character string. Can't be <code>null</code>.
	 * 
	 * @return A new instance of Catalog, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>,
	 * <code>defaultCharacterSetName</code>, <code>defaultCollationName</code>  is <code>null</code>. 
	 * 
	 */
	abstract public Catalog createCatalog( String name, String defaultCharacterSetName, String defaultCollationName	);



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
	abstract public CheckConstraint createCheckConstraint(
		  String name
		, BooleanExpression body
		, Deferability deferability 
	);



	/**
	 * Factory method that creates a new instance of CheckConstraint by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>deferability</b> - <code>Visibility.getDefault</code>.
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
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>body</code> is <code>null</code>.
	 * 
	 */
	abstract public CheckConstraint createCheckConstraint( String name, BooleanExpression body );



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
	 * @param uniqueConstraint Identifies the UniqueConstraint instance that serves as the primary key for this ForeignKey instance.
	 * 
	 * @param deleteRule The disposition of the data records containing the foreign key value when the record of the matching primary key is deleted.
	 * Must not be <code>null</code>.
	 * 
	 * @param updateRule The disposition of the data records containing the foreign key value when the record of the matching primary key is updated.
	 * Must not be <code>null</code>.
	 * 
	 * @param deferability Indicates if the validity of the ForeignKey is to be tested at each statement or at the end of a transaction. Must not
	 * be <code>null</code>.
	 * 
	 * @return A new instance of Foreign Key, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>uniqueConstraint</code>,
	 * <code>deleteRule</code>, <code>updateRule</code> or <code>deferability</code> is <code>null</code>. 
	 * 
	 */
	abstract public ForeignKey createForeignKey(
		  String name
		, UniqueConstraint uniqueConstraint
		, ReferentialRule deleteRule
		, ReferentialRule updateRule
		, Deferability deferability
	);



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
	 * @param deferrability The type of the validity of the PrimaryKey that is to be tested at each statement or at the end of a transaction. Must
	 * not be <code>null</code>.
	 * 
	 * @return A new instance of PrimaryKey, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>deferability</code> is <code>null</code>.
	 * 
	 */
	abstract public PrimaryKey createPrimaryKey( String name, Deferability deferability );



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
	 * @param body A specification of the Procedure in some appropriate form (such as a programming language). The exact form of a Methods 
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
	abstract public Procedure createProcedure( String name, ProcedureExpression body, ProcedureType type );



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
	 * @param body A specification of the Procedure in some appropriate form (such as a programming language). The exact form of a Methods
	 * specification and knowledge of the language in which it is described is outside the scope of the CWM. Must not be <code>null</code>.
	 * 
	 * @param type Function or Procedure. This indicates whether this object returns a value or not. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Procedure, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>,* <code>isQuery</code>,
	 * <code>body</code> or <code>type</code> is <code>null</code>.
	 * 
	 */
	abstract public Procedure createProcedure(
		  String name
		, Operation specification
		, ProcedureExpression body
		, ProcedureType type
	);



	abstract public DefaultValueExpression createDefaultValueExpression( String columnDef );



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
     * <b>Default values:</b>
     * <ul>
     * <li><b>visibility</b> - <code>Visibility._public</code>.
     * </ul>
     *  
	 * @param name An identifier for the Schema within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Schema, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> is <code>null</code>.
	 * 
	 */
	abstract public Schema createSchema( String name );



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
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 * 
	 */
	abstract public SQLSimpleType createSQLSimpleType( String name, Visibility visibility, Boolean notInstantiable );



	abstract public SQLSimpleType createSQLSimpleType( 
		  Integer jdbcType 			// => SQL type from java.sql.Types
		, String  typeName			// => SQL type name, for a UDT type the type name is fully qualified
		, Integer precision  		// => precision
		, Integer length			// => length in bytes of data
		, Short   scale				// => scale -  null is returned for data types where SCALE is not applicable.
		, Short   radix 			// => radix
		, Integer charOctedLength	// => the maximum length of binary and character based columns. For any other datatype the returned value is a NULL	
	);



	/**
	 * Creates a new instance of SQLDistinctType by specific parameters.
	 * 
	 * @param name An identifier for the SQLDistinctType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the SQLDistinctType within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this SQLDistinctType is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
	 * @param type Classifier instance for which this TypeAlias instance acts as an alias. Can't be <code>null</code>.
	 * 
	 * @param sqlSimpleType The SQLSimpleType used to define the SQLDstinctType. Must not be <code>null</code>.
	 * 
	 * @return A new instance of SQLDistinctType, by initial parameters
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>notInstantiable</code>,
	 * <code>type</code> or <code>sqlSimpleType</code> is <code>null</code>.
	 *
	 */
	abstract public SQLDistinctType createSQLDistinctType( 
		  String name
		, Visibility visibility
		, Boolean notInstantiable
		, SQLSimpleType sqlSimpleType
	);



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
	abstract public SQLDistinctType createSQLDistinctType( String name, SQLSimpleType sqlSimpleType	);



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
	abstract public SQLStructuredType createSQLStructuredType( String name, Visibility visibility, Boolean notInstantiable );



	/**
	 * Creates a new instance of SQLStructuredType by specific parameters.
	 * 
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
	abstract public SQLStructuredType createSQLStructuredType( String name	);



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
	 * <code>isPartitioning</code>, <code>spannedClass</code>, <code>firstIndexedFeature</code>, <code>filterCondition</code>, 
	 * <code>isNullable</code> or <code>autoUpdate</code> is <code>null</code>.
	 * 
	 */
	abstract public SQLIndex createSQLIndex(
		  String name
		, Boolean isUnique
		, Boolean isSorted
		, Boolean isPartitioning
		, Table spannedTable
		, String filterCondition
		, Boolean isNullable
		, Boolean autoUpdate
	);



////	/**
////	 * Factory method that creates a new instance of SQLIndexColumn by specific parameters.
////	 * 
////	 * @param name An identifier for the SQLIndexColumn within its containing Namespace. Must not be <code>null</code>.
////	 * 
////	 * @param visibility Specifies extent of the visibility of the SQLIndexColumn within its owning Namespace. Must not be <code>null</code>.
////	 * 
////	 * @param feature Identifies the StructuralFeature instance for which this SQLIndexColumn instance is relevant. Must not be <code>null</code>.
////	 * 
////	 * @param index The Index instance for which this SQLIndexColumn instance isrelevant. Must not be <code>null</code>. 
////	 * 
////	 * @return A new instance of SQLIndexColumn, by initial parameters.
////	 * 
////	 * @throws NullPointerException Is thrown if one of the parameter: 
////	 * <code>name</code>, <code>visibility</code> or <code>feature</code> is 
////	 * <code>null</code>. 
////	 * 
////	 */
////	abstract public SQLIndexColumn createSQLIndexColumn( String name, Visibility visibility, Column feature	);



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
	abstract public Table createTable( String name, Boolean isSystem, Boolean isTemporary );



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
	 * @param isTemporary <code>true</code> if the table content is temporary. SQL92 standards provide two types of temporary tables (local 
	 * Temporary and Global Temporary). A System Table, like a part of the system catalog), <code>false</code> otherwise. Must not
	 * be <code>null</code>.
	 *
	 * @return A new instance of Table by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> is <code>null</code>.
	 * 
	 */
	abstract public Table createTable( String name );



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
	abstract public Table createSystemTable( String name );



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
	abstract public Trigger createTrigger(
		  String name
		, EventManipulation eventManipulation
		, BooleanExpression actionCondition
		, ProcedureExpression actionStatement
		, ActionOrientation actionOrientation
		, ConditionTiming conditionTiming
		, String conditionReferenceNewTable
		, String conditionReferenceOldTable
		, Table table
	);



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
	 * @param deferability The type of the validity of the UniqueConstraint that is to be tested at each statement or at the end of a transaction.
	 * Must not be <code>null</code>.
	 * 
	 * @return A new instance of UniqueConstraint, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>deferability</code> is <code>null</code>.
	 * 
	 */
	abstract public UniqueConstraint createUniqueConstraint( String name, Deferability deferability );



	/**
	 * Factory method that creates a new instance of View by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>notInstantiable</b> - <code>false</code>.
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
	abstract public View createView( String name, QueryExpression queryExpression );



	abstract public View createView( String name );
}