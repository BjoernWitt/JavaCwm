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

import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCManager;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;

/**
 * A schema is a named collection of tables.
 * 
 * <p>
 * In addition to owning Tables and/or Views, Schemas also own Procedures and Triggers.
 * </p>
 * 
 * <p>
 * The Relational package, as do the other data packages, define top-level containers (Catalog, Schema) that extend the ObjectModel Package class. 
 * ColumnSet and SQLStructuredType extend Class. The Columns contained in the ColumnSet are extensions of the ObjectModel Attribute. The data type of 
 * a column (SQLDataType) inherits from ObjectModel Classifier. This structuring of the classes will be particularly useful to describe the object 
 * extensions of SQL.
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
public interface Schema
extends
	GCwmPackage<
		  JDBCManager
		, Catalog
		, Schema
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
		, GModelElement<?, ?, ?, ?>
	>
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	Catalog setNamespace( Catalog catalog );



	Boolean addDependency( Dependency dependency );



	Boolean addConstraint( Constraint constraint );



	Boolean addImporter( Schema schema );



//====================================================================================================================================================
// GNamespace capabilities	
//====================================================================================================================================================



	/**
	 * Adds an SQLDistinctType to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement's visibility attribute states whether the element
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this Schema to the SQLDistinctType for inverse traversal.
	 * </p> 
	 * 
	 * @param distinctType of this Schema.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( SQLDistinctType distinctType );



	/**
	 * Adds an Trigger to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement visibility attribute states whether the element 
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this Schema to the Trigger for inverse traversal.
	 * </p> 
	 * 
	 * @param trigger of this Schema.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( Trigger trigger );



	/**
	 * Adds a View to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement's visibility attribute states whether the element 
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this Schema to the View for inverse traversal.
	 * </p> 
	 * 
	 * @param view of this Schema.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( View view );



	/**
	 * Adds a Table to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement visibility attribute states whether the element 
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this Schema to the Table for inverse traversal.
	 * </p> 
	 * 
	 * @param table of this Schema.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addOwnedElement( Table table );



	/**
	 * Adds a Procedure to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement's visibility attribute states whether the element 
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this Schema to the Procedure for inverse traversal.
	 * </p> 
	 * 
	 * @param procedure of this Schema.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( Procedure procedure );



	/**
	 * Adds a SqlIndex to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement's visibility attribute states whether the element 
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this Schema to the SqlIndex for inverse traversal.
	 * </p> 
	 * 
	 * @param sqlIndex of this Schema.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( SQLIndex sqlIndex );



	/**
	 * Adds a SQLStructuredType to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement's visibility attribute states whether the element 
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this Schema to the SQLStructuredType for inverse traversal.
	 * </p> 
	 * 
	 * @param sqlStructuredType of this Schema.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( SQLStructuredType sqlStructuredType );



//====================================================================================================================================================
// Schema capabilities	
//====================================================================================================================================================



	/**
	 * A ColumnSet represents any form of relational data. 
	 * <p>
	 * A NamedColumnSet is a cataloged version of a ColumnSet, which is owned by a Schema. A NamedColumnSet can be a logical View or a physical Table.
	 * Instead of being a NamedColumnSet, a ColumnSet can be a QueryColumnSet, which is the result of an SQL query.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: NamedColumnSet</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: NamedColumnSet::namespace</li>
	 * </ul>
	 *  
	 * @return A Collection of NamedColumnSet. Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	Set<NamedColumnSet<?>> getNamedColumnSets();



	NamedColumnSet<?> getNamedColumnSet( String simpleName );



	Set<Table> getTables();



	Set<View> getViews();



//	Set<TypedTable> getTypedTables();



//	Set<Synonym> getSysnonyms();



	/**
	 * Returns the owning Namespace, that is a Catalog for a Schema.
	 * <p>
	 * This method is only a wrapping method getNamespace for easier use of API.
	 * </p> 
	 * 
	 * @return The owning Catalog if one exists, <code>null</code> otherwise.
	 * 
	 * @see #getNamespace()
	 * 
	 */
	Catalog getCatalog();



	/**
	 * A trigger is an action run by the DBMS when specified events occur on the table owning the Trigger.
	 * 
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: Trigger</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: Trigger::namespace</li>
	 * </ul>
	 *  
	 * @return A Set of Triggers. Can't be <code>null</code>, but an empty Set.
	 * 
	 */
	Set<Trigger> getTriggers();



	Trigger getTrigger( String simpleName );



	Boolean addTrigger( Trigger trigger );



	/**
	 * A Procedure describes Relational DBMS Stored procedures and functions.
	 * 
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: Procedure</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: Procedure::namespace</li>
	 * </ul>
	 * 
	 * @return A Set of Procedures. Can't be <code>null</code>, but an empty Set.
	 * 
	 */
	Set<Procedure> getProcedures();



	/**
	 * An Index on a table.
	 * 
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: SQLIndex</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: SQLIndex::namespace</li>
	 * </ul>
	 * 
	 * @return A Set of SQLIndexes. Can't be <code>null</code>, but an empty Set.
	 * 
	 */
	Set<SQLIndex> getSQLIndexes();



	Boolean addSQLIndex( SQLIndex sqlIndex );



	SQLIndex getSQLIndex( String simpleName );




	/**
	 * A rule that specifies the values allowed in one or more columns of every row of a table.
	 * 
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: CheckConstraint</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: CheckConstraint::namespace</li>
	 * </ul>
	 * 
	 * @return A Set of CheckConstraints. Can't be <code>null</code>, but an empty Set.
	 * 
	 */
	Set<CheckConstraint> getCheckConstraints();



	/**
	 * A user defined distinct Type.
	 * 
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: SQLDistinctType</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: SQLDistinctType::namespace</li>
	 * </ul>
	 * 
	 * @return A Set of SQLDistinctTypes. Can't be <code>null</code>, but an empty Set.
	 * 
	 */
	Set<SQLDistinctType> getSQLDistinctTypes();



	/**
	 * A user defined structured Type.
	 * 
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: SQLStructuredType</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: SQLStructuredType::namespace</li>
	 * </ul>
	 * 
	 * @return A Set of SQLStructuredTypes. Can't be <code>null</code>, but an empty Set.
	 * 
	 */
	Set<SQLStructuredType> getSQLStrucuredTypes();
}