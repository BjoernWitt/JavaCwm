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

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.ForeignKey;
import org.ogrehus.jcwm.api.resource.relational.PrimaryKey;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.Table;
import org.ogrehus.jcwm.api.resource.relational.TableColumn;
import org.ogrehus.jcwm.api.resource.relational.UniqueConstraint;
import org.ogrehus.jcwm.api.resource.relational.util.Deferability;
import org.ogrehus.jcwm.api.resource.relational.util.ReferentialRule;
import org.ogrehus.jcwm.impl.foundation.keyindexes.util.generic.GFunKeyRelationship;

/**
 * A Foreign Key associates columns from one table with columns of another table.
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunForeignKey
extends
	GFunKeyRelationship<
		  Table
		, Schema
		, UniqueConstraint
		, TableColumn
	>
implements
	ForeignKey
{

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
	 * @param uniqueKey Identifies the UniqueKey instance that serves as the �primary key� for this Foreign Key instance. Can also be a 
	 * uniqueConstraint as well.
	 * 
	 * @param deleteRule The disposition of the data records containing the foreign key value when the record of the 
	 * matching primary key is deleted. Must not be <code>null</code>.
	 * 
	 * @param updateRule The disposition of the data records containing the foreign key value when the record of the matching primary key is updated.
	 * Must not be <code>null</code>.
	 * 
	 * @param deferability Indicates if the validity of the ForeignKey is to be tested at each statement or at the end of a transaction. Must not
	 * be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>uniqueKey</code>, <code>deleteRule</code>,
	 * <code>updateRule</code> or <code>deferability</code> is <code>null</code>.
	 * 
	 */
	public <UNIQUE_KEY extends UniqueConstraint> FunForeignKey( 
		  String name
		, UNIQUE_KEY uniqueKey
		, ReferentialRule deleteRule
		, ReferentialRule updateRule
		, Deferability deferability
	) {
		super( name, Visibility._public, uniqueKey );
		setDeleteRule( deleteRule );
		setUpdateRule( updateRule );
		setDeferability( deferability );
	}


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



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
// ForeignKey capabilities
//====================================================================================================================================================



	public Boolean addFeature( TableColumn tableColumn ) {
		return super.addFeatureGeneric( tableColumn );
	}



	public UniqueConstraint setUniqueConstraint( UniqueConstraint uniqueConstraint ) {
		return super.setUniqueKeyGeneric( uniqueConstraint );
	}



	public UniqueConstraint setUniqueConstraint( PrimaryKey primaryKey ) {
		return super.setUniqueKeyGeneric( primaryKey );
	}



	/**
	 * Indicates the disposition of the data records containing the foreign key value when the record of the matching primary key is deleted.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: ReferentialRuleType</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @return The disposition of the data records containing the foreign key value when the record of the matching primary key is deleted. Can't
	 * be <code>null</code>.
	 * 
	 */
	public ReferentialRule getDeleteRule() {
		return this.deleteRule;
	}



	/**
	 * Indicates the disposition of the data records containing the foreign key value when the record of the matching primary key is deleted.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: ReferentialRuleType</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @param deleteRule The disposition of the data records containing the foreign key value when the record of the matching primary key is deleted.
	 * Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>deleteRule</code>. Can't be <code>null</code>. 
	 * 
	 * @throws NullPointerException If the parameter <code>deleteRule</code> is <code>null</code>.
	 * 
	 */
	public ReferentialRule setDeleteRule( ReferentialRule deleteRule ) {
		if ( deleteRule == null ) {
			throw new NullPointerException( "The parameter: deleteRule must not be null.");
		}
		ReferentialRule old = this.deleteRule;
		this.deleteRule = deleteRule;
		return old;
	}



	/**
	 * Same as deleteRule for updates of the primary key data record.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: ReferentialRuleType</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @return The disposition of the data records containing the foreign key value when the record of the matching primary key is updated. Can't
	 * be <code>null</code>.
	 * 
	 */
	public ReferentialRule getUpdateRule() {
		return this.updateRule;
	}



	/**
	 * Same as deleteRule for updates of the primary key data record.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: ReferentialRuleType</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @param updateRule The disposition of the data records containing the foreign key value when the record of the matching primary key is updated.
	 * Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>updateRule</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>updateRule</code> is <code>null</code>.
	 * 
	 */
	public ReferentialRule setUpdateRule( ReferentialRule updateRule ) {
		if ( updateRule == null ) {
			throw new NullPointerException( "The parameter: updateRule must not be null.");
		}
		ReferentialRule old = this.updateRule;
		this.updateRule = updateRule;

		return old;
	}



	/**
	 * Indicates if the validity of the ForeignKey is to be tested at each statement or at the end of a transaction.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: DeferrabilityType</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @return The validity of the ForeignKey is to be tested at each statement or at the end of a transaction.  Can't be <code>null</code>.
	 * 
	 */
	public Deferability getDeferability() {
		return this.deferability;
	}



	/**
	 * Indicates if the validity of the ForeignKey is to be tested at each statement or at the end of a transaction.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: DeferrabilityType</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @param deferrability Indicates if the validity of the ForeignKey is to be tested at each statement or at the end 
	 * of a transaction. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>deferability</code>. Can't be <code>null</code>. 
	 * 
     * @throws NullPointerException If the parameter <code>deferability</code> is <code>null</code>.
	 *  
	 */
	public Deferability setDeferability( Deferability deferability ) {
		if ( deferability == null ) {
			throw new NullPointerException( "The paremter: deferability must not be null." );
		}
		Deferability old = this.deferability;
		this.deferability = deferability;
		return old;
	}
	
	
//======================================================================================================================
// Object capabilities
//======================================================================================================================
	
	
    @Override
    public String toString() {
        StringBuffer out = new StringBuffer( "[FunForeignKey" );
        if ( this.deleteRule != null ) {
            out.append( " | deleteRule=" + this.deleteRule );
        }
        if ( this.updateRule != null ) {
            out.append( " | updateRule=" + this.updateRule );
        }
        if ( this.deferability != null ) {
            out.append( " | deferability=" + this.deferability );
        }
		out.append( " |\nextends: " );
		out.append( super.toString() );
        out.append( "]" );
        return out.toString();
    } 	
	
	
//======================================================================================================================
// Properties
//======================================================================================================================
	
	
	/**
	 * Indicates the disposition of the data records containing the foreign key value when the record of the matching 
	 * primary key is deleted.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: ReferentialRuleType</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected ReferentialRule deleteRule = null;
	

	/**
	 * Same as deleteRule for updates of the primary key data record.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: ReferentialRuleType</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected ReferentialRule updateRule = null;
	
	
	/**
	 * Indicates if the validity of the ForeignKey is to be tested at each statement or at the end of a transaction.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: Deferability</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Deferability deferability = null;
	
}
