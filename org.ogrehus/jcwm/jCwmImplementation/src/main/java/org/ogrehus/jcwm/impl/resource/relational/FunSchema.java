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

import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCManager;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.Catalog;
import org.ogrehus.jcwm.api.resource.relational.CheckConstraint;
import org.ogrehus.jcwm.api.resource.relational.NamedColumnSet;
import org.ogrehus.jcwm.api.resource.relational.Procedure;
import org.ogrehus.jcwm.api.resource.relational.SQLDistinctType;
import org.ogrehus.jcwm.api.resource.relational.SQLStructuredType;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.SQLIndex;
import org.ogrehus.jcwm.api.resource.relational.Table;
import org.ogrehus.jcwm.api.resource.relational.Trigger;
import org.ogrehus.jcwm.api.resource.relational.View;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunCwmPackage;

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
public class FunSchema
extends
	GFunCwmPackage<
		  JDBCManager
		, Catalog
		, Schema
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
		, GModelElement<?, ?, ?, ?>
	>
implements
	Schema
{


	/**
	 * Factory method that creates a new instance of Schema by specific parameters.
	 * 
	 * @param name An identifier for the Schema within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Schema within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Schema, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	public FunSchema( String name, Visibility visibility ) {
		super( name, visibility );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Catalog setNamespace( Catalog catalog ) {
		return super.setNamespaceGeneric( catalog );
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



	public Boolean addImporter( Schema schema ) {
		return super.addImporterGeneric( schema );
	}



//====================================================================================================================================================
// GNamespace capabilities
//====================================================================================================================================================



	/**
	 * Adds an SQLDistinctType to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement�s visibility attribute states whether the element
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
	public Boolean addOwnedElement( SQLDistinctType distinctType ) {
		return super.addOwnedElementGeneric( distinctType );
	}



	/**
	 * Adds an Trigger to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement�s visibility attribute states whether the element
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this Schema to the Trigger for inverse traversal.
	 * </p> 
	 * 
	 * @param trigger A Trigger in this Schema.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addOwnedElement( Trigger trigger ) {
		return super.addOwnedElementGeneric( trigger );
	}



	/**
	 * Adds a View to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement visibility attribute states whether the element is
	 * visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this Schema to the View for inverse traversal.
	 * </p> 
	 * 
	 * @param view A view in this Schema.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addOwnedElement( View view ) {
		return super.addOwnedElementGeneric( view );
	}



	/**
	 * Adds a Table to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement�s visibility attribute states whether the element
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
	public Boolean addOwnedElement( Table table ) {
		return super.addOwnedElementGeneric( table );
	}



	/**
	 * Adds a Procedure to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement visibility attribute states whether the element
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
	public Boolean addOwnedElement( Procedure procedure ) {
		return super.addOwnedElementGeneric( procedure );
	}



	/**
	 * Adds a SqlIndex to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Schema. The ModelElement visibility attribute states whether the element is
	 * visible outside the namespace.
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
	public Boolean addOwnedElement( SQLIndex sqlIndex ) {
		return super.addOwnedElementGeneric( sqlIndex );
	}



	public Boolean addOwnedElement( SQLStructuredType sqlStructuredType ) {
		return super.addOwnedElementGeneric( sqlStructuredType );
	}



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
	public Set<NamedColumnSet<?>> getNamedColumnSets() {
		Set<NamedColumnSet<?>> specific = new HashSet<NamedColumnSet<?>>();
		for ( ModelElement owned : this.ownedElements ) {
			if ( owned instanceof NamedColumnSet<?> ) {
				specific.add( (NamedColumnSet<?>)owned );
			}
		}
		
		return specific;
	}



	public NamedColumnSet<?> getNamedColumnSet( String simpleName ) {
		if ( simpleName != null ) {
			for ( ModelElement owned : this.ownedElements ) {
				if ( owned instanceof NamedColumnSet<?> && simpleName.equals( owned.getSimpleName() ) ) {
					return (NamedColumnSet<?>)owned;
				}
			}
		}

		return null;
	}



	public Set<Table> getTables() {
		return getOwnedElementsByType( Table.class );
	}



	public Set<View> getViews() {
		return getOwnedElementsByType( View.class );
	}



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
	public Catalog getCatalog() {
		return getNamespace();
	}



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
	 * @return A Collection of Trigger. Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	public Set<Trigger> getTriggers() {
		return super.getOwnedElementsGeneric( Trigger.class );
	}



	public Trigger getTrigger( String simpleName ) {
		return getOwnedElement( Trigger.class, simpleName );
	}



	public Boolean addTrigger(Trigger trigger) {
		return addOwnedElement( trigger );
	}



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
	 * @return A Collection of Procedures. Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	public Set<Procedure> getProcedures() {
		return super.getOwnedElementsGeneric( Procedure.class );
	}



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
	 * @return A Collection of SQLIndex. Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	public Set<SQLIndex> getSQLIndexes() {
		return super.getOwnedElementsGeneric( SQLIndex.class );
	}



	public Boolean addSQLIndex( SQLIndex sqlIndex ) {
		return addOwnedElement( sqlIndex );
	}



	public SQLIndex getSQLIndex( String simpleName ) {
		return super.getOwnedElement( SQLIndex.class, simpleName );
	}



	/**
	 * A rule that specifies the values allowed in one or more columns of every 
	 * row of a table.
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
	 * @return A Collection of SQLIndex. Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	public Set<CheckConstraint> getCheckConstraints() {
		return super.getOwnedElementsGeneric( CheckConstraint.class );
	}



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
	public Set<SQLDistinctType> getSQLDistinctTypes() {
		return super.getOwnedElementsGeneric( SQLDistinctType.class );
	}



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
	public Set<SQLStructuredType> getSQLStrucuredTypes() {
		return super.getOwnedElementsGeneric( SQLStructuredType.class );
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunSchema" );
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}
}