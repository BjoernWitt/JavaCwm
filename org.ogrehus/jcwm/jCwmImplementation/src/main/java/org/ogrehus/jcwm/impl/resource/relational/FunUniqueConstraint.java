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

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.ForeignKey;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.Table;
import org.ogrehus.jcwm.api.resource.relational.TableColumn;
import org.ogrehus.jcwm.api.resource.relational.UniqueConstraint;
import org.ogrehus.jcwm.api.resource.relational.util.Deferability;
import org.ogrehus.jcwm.impl.foundation.keyindexes.util.generic.GFunUniqueKey;


/**
 * A condition to define uniqueness of rows in a table. 
 * <p>
 * An example of UniqueConstraint is a primary key.
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
public class FunUniqueConstraint
extends
	GFunUniqueKey<
		  Table
		, Schema
		, ForeignKey
		, TableColumn
	>
implements
	UniqueConstraint
{


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
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>deferability</code> is <code>null</code>.
	 * 
	 */
	public FunUniqueConstraint( String name, Deferability deferrability ) {
		super( name, Visibility._public );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the Table that contains this UniqueConstraint.
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a
	 * kind of virtual namespace).
	 * </p>
	 * <p>
	 * The pathname of Table names starting from the root package provides a unique designation for every KeyRelationship.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: Table</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Table::ownedElement</li>
	 * </ul>
	 * 
	 * @param table The new Table that contains this UniqueConstraint.
	 * 
	 * @return The old table that contains this UniqueConstraint before, or <code>null</code> none was assigned.
	 * 
	 */
	public Table setNamespace( Table table ) {
		return super.setNamespaceGeneric( table );
	}



	/**
	 * Adds an importer (Package) to this UniqueConstraint, that will contain this UniqueConstarint as imported.
	 * 
	 * @param Schema The specific package, that will contain this UniqueCosntraint.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addImporter( Schema schema ) {
		return super.addImporterGeneric( schema );
	}



//====================================================================================================================================================
// GClassifier capabilities
//====================================================================================================================================================



	/**
	 * Adds an TableColumn instance that participate as (part of) the key of this UniqueKey instance.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : TableColumn</li>
	 * <li><i>defined by</i> 	: UniqueKeyConstraint::feature</li>
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * </ul>
	 * 
	 * @param tableColumn The TableColumn that will be added to this UniqueCopsntraint as a feature.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addFeature( TableColumn tableColumn ) {
		return super.addFeatureGeneric( tableColumn );
	}



	/**
	 * Adds a ForeignKey instances that reference this UniqueConstraint instance.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : foreignKey</li>
	 * <li><i>defined by</i>    : UniqueContraintRelationship::keyRelationship</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : ForeignKey::uniqueKey</li>
	 * </ul>
	 * 
	 * @param foreignKey The ForeignKey that will be added to this UniqueConstraint.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addKeyRelationship( ForeignKey foreignKey ) {
		return super.addKeyRelationshipGeneric( foreignKey );
	}



//====================================================================================================================================================
// UniqueConstraint capabilities
//====================================================================================================================================================



	/**
	 * Indicates if the validity of the UniqueConstraint is to be tested at each statement or at the end of a transaction.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : DeferabilityType</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @return The type of the validity of the UniqueConstraint that is to be tested at each statement or at the end of a transaction.
	 * 
	 */
	public Deferability getDeferability() {
		return this.deferability;
	}



	/**
	 * Indicates if the validity of the UniqueConstraint is to be tested at each statement or at the end of a transaction.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : DeferabilityType</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @param deferrability The type of the validity of the UniqueConstraint that is to be tested at each statement or at the end of a transaction.
	 * Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>deferability</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>deferability</code> is <code>null</code>.
	 * 
	 */
	public Deferability setDeferability( Deferability deferability ) {
		if ( deferability == null ) {
			throw new NullPointerException( "The parameter: deferability must not be null." );
		}
		Deferability old = this.deferability;
		this.deferability = deferability;

		return old;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================
	
	
	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunUniqueConstraint" );
		if ( this.deferability != null ) {
			out.append( " | deferability=" + this.deferability );
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
	 * Indicates if the validity of the UniqueConstraint is to be tested at each statement or at the end of a transaction.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : DeferabilityType</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */
	protected Deferability deferability = null;
}