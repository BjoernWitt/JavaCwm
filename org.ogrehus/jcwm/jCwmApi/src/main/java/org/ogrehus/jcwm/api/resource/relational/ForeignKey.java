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

import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GKeyRelationship;
import org.ogrehus.jcwm.api.resource.relational.util.Deferability;
import org.ogrehus.jcwm.api.resource.relational.util.ReferentialRule;

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
public interface ForeignKey
extends
	GKeyRelationship<
		  Table
		, Schema
		, UniqueConstraint
		, TableColumn
	>
{


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
	Table setNamespace( Table table );



	/**
	 * Adds an importer (Package) to this UniqueConstraint, that will contain this UniqueConstarint as imported.
	 * 
	 * @param Schema The specific package, that will contain this UniqueCosntraint.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( Schema schema );



//====================================================================================================================================================
// ForeignKey capabilities
//====================================================================================================================================================



	Boolean addFeature( TableColumn tableColumn );



	UniqueConstraint setUniqueConstraint( UniqueConstraint uniqueConstraint );



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
	ReferentialRule getDeleteRule();



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
	ReferentialRule setDeleteRule( ReferentialRule deleteRule );



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
	ReferentialRule getUpdateRule();



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
	ReferentialRule setUpdateRule( ReferentialRule updateRule );



	/**
	 * Indicates if the validity of the ForeignKey is to be tested at each statement or at the end of a transaction.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: DeferabilityType</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @return The validity of the ForeignKey is to be tested at each statement or at the end of a transaction.  Can't be <code>null</code>.
	 * 
	 */
	Deferability getDeferability();



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
	 * @param deferrability Indicates if the validity of the ForeignKey is to be tested at each statement or at the end of a transaction. Must not
	 * be <code>null</code>.
	 * 
	 * @return Old value of the property <code>deferability</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>deferability</code> is <code>null</code>.
	 * 
	 */
	Deferability setDeferability( Deferability deferability );
}