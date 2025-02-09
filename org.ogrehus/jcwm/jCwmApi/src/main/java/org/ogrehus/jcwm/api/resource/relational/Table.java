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
import java.util.SortedSet;

import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GNamedColumnSet;


/**
 * A materialized NamedColumnSet.
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Table
extends
	GNamedColumnSet<CheckConstraint, TableColumn>
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	Boolean addDependency( Dependency dependency );



	Boolean addConstraint( CheckConstraint checkConstraint );



	Boolean addCheckConstraint( CheckConstraint checkConstraint );



	CheckConstraint getCheckConstraint( String simpleName );



	Boolean removeCheckConstraint( CheckConstraint checkConstraint );



	Set<CheckConstraint> getCheckConstraints();



//====================================================================================================================================================
// GNamespace capabilities	
//====================================================================================================================================================



	Set<TableColumn> getColumns();



	/**
	 * A Foreign Key associates columns from one table with columns of another table.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: ForeignKey</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: ForeignKey::namespace</li>
	 * </ul>
	 *  
	 * @return A Collection of ForeignKeys. Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	Set<ForeignKey> getForeignKeys();



	Boolean addOwnedElement( ForeignKey foreignKey );


	
	Boolean addForeignKey( ForeignKey foreignKey );



	ForeignKey getForeignKey( String simpleName );



	Boolean removeForeignKey( ForeignKey foreignKey );



	/**
	 * There is only one UniqueConstraint of type PrimaryKey per Table. It is implemented specifically by each RDBMS.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: PrimaryKey</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: PrimaryKey::namespace</li>
	 * </ul>
	 *  
	 * @param primaryKey A PrimaryKey. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>primaryKey</code>. Can't be <code>null</code>. 
	 *
	 * @throws NullPointerException is thrown if the parameter <code>primaryKey</code> is <code>null</code>. 
	 */
	PrimaryKey setPrimaryKey( PrimaryKey primaryKey );



	Boolean addOwnedElement( PrimaryKey primarykey );



	Boolean removePrimaryKey();



	/**
	 * There is only one UniqueConstraint of type PrimaryKey per Table. It is implemented specifically by each RDBMS.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: PrimaryKey</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: PrimaryKey::namespace</li>
	 * </ul>
	 *  
	 * @return A PrimaryKey. Can be <code>null</code>, if none exists.
	 *  
	 */
	PrimaryKey getPrimaryKey();



	/**
	 * A condition to define uniqueness of rows in a table. An example of UniqueConstraint is a primary key.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: UniqueConstraint</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: UniqueConstraint::namespace</li>
	 * </ul>
	 *  
	 * @return A Collection of UniqueConstraints. Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	Boolean addOwnedElement( UniqueConstraint uniqueConstraint );



	Set<UniqueConstraint> getUniqueConstraints();



	Boolean addUniqueConstraint( UniqueConstraint uniqueConstraint );



	UniqueConstraint getUniqueConstraint( String simpleName );



	Boolean removeUniqueConstraint( UniqueConstraint uniqueConstraint );



//====================================================================================================================================================
// CwmClass capabilities	
//====================================================================================================================================================



	/**
	 * Associates indexes with the classes they span. 
	 * <p>
	 * This relationship is separate from the ownership of indexes, to allow modeling of systems where an index is NOT 
	 * owned by the object it spans. In most situations, however, the spanned and owning Class instances will be the 
	 * same.
	 * </p>
	 * 
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: generic INDEX</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * </ul>
	 * 
	 */
	Boolean addIndex( SQLIndex sqlIndex );



//====================================================================================================================================================
// GClassifier capabilities
//====================================================================================================================================================


	
 	Boolean addColumn( String columnName, SQLDataType<?, ?> type );



 	Boolean addColumn( TableColumn tableColumn );



 	TableColumn getColumn( String simpleName );



//====================================================================================================================================================
// Table capabilities	
//====================================================================================================================================================



	/**
	 * Indicates that the Table is a System Table (generally part of or view on the system catalog).
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Boolean</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the table is a System Table, like a part of the system catalog), <code>false</code> otherwise. Can't 
	 * be <code>null</code>. 
	 * 
	 */ 
	Boolean isSystem();



	/**
	 * Indicates that the Table is a System Table (generally part of or view on the system catalog).
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Boolean</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @param isSystem <code>true</code> if the table is a System Table, like a part of the system catalog), <code>false</code> otherwise. Must not 
	 * be <code>null</code>.
	 * 
	 * @return Old value of the property <code>isSystem</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>isSystem</code> is <code>null</code>.
	 * 
	 */ 
	Boolean setSystem( Boolean isSystem );



	/**
	 * Indicates that the table content is temporary. SQL92 standards provide two types of temporary tables (local Temporary and Global Temporary).
	 * <p>
	 * However, RDBMS products have implemented variations on this theme. It is recommended that the product manufacturers provide specific temporary
	 * information (besides the temporaryScope attribute) in their extensions.
	 * </p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Boolean</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the table content is temporary. SQL92 standards provide two types of temporary tables (local Temporary and Global 
	 * Temporary).a System Table, like a part of the system catalog), <code>false</code> otherwise. Can't be <code>null</code>.
	 * 
	 */ 
	Boolean isTemporary();



	/**
	 * Indicates that the table content is temporary. SQL92 standards provide two types of temporary tables (local Temporary and Global Temporary). 
	 * <p>
	 * However, RDBMS products have implemented variations on this theme. It is recommended that the product manufacturers provide specific temporary 
	 * information (besides the temporaryScope attribute) in their extensions.
	 * </p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Boolean</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @param  isTemporary <code>true</code> if the table content is temporary. SQL92 standards provide two types of temporary tables (local Temporary
	 * and Global Temporary).a System Table, like a part of the system catalog), <code>false</code> otherwise. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>isTemporary</code>. Can't be <code>null</code>. 
	 *
	 * @throws NullPointerException is thrown if the parameter <code>isTemporary</code> is <code>null</code>. 
	 * 
	 */ 
	Boolean setTemporary( Boolean isTemporary );



	/**
	 * This attribute is meaningful only when the isTemporary flag is <code>true</code> [C-1]. 
	 * <p>
	 * The scope indicates when the data of this table are available. SESSION, APPLICATION are examples of possible values. Look at the Scope 
	 * attribute for Global Temporary tables in the SQL standards for more details.
	 * </p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : String</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>constraints</i> : May not be specified if isTemporary is set to 
	 * false.</li> 
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-1]</b> - Attribute temporaryScope is meaningful only when the isTemporary flag is True.</li>
	 * </ul>
	 * 
	 * @return The scope indicates when the data of this table are available. SESSION, APPLICATION are examples of possible values. Look at the Scope 
	 * attribute for Global Temporary tables in the SQL standards for more details.
	 * 
	 */ 
	String getTemporaryScope();



	/**
	 * This attribute is meaningful only when the isTemporary flag is <code>true</code> [C-1]. 
	 * <p>
	 * The scope indicates when the data of this table are available. SESSION, APPLICATION are examples of possible values. Look at the Scope 
	 * attribute for Global Temporary tables in the SQL standards for more details.
	 * </p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : String</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>constraints</i> : May not be specified if isTemporary is set to 
	 * false.</li> 
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-1]</b> - Attribute temporaryScope is meaningful only when the isTemporary flag is True.</li>
	 * </ul>
	 * 
	 * @param temporaryScope The scope indicates when the data of this table are available. SESSION, APPLICATION are examples of possible values. Look
	 * at the Scope attribute for Global Temporary tables in the SQL standards for more details.
	 * 
	 * @return Old value of the property <code>temporaryScope</code>. Can't be <code>null</code>.
	 * 
	 */ 
	String setTemporaryScope( String temporaryScope );



	/**
	 * Associates triggers executed during changes to the table.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: Trigger</li>
	 * <li><i>defined by</i>	: TableOwningTrigger::trigger</li>
	 * <li><i>multiplicity</i>	: zero or more; ordered</li>
	 * <li><i>inverse</i>		: Trigger::table</li>
	 * </ul>
	 *  
	 * @return A List of associates triggers executed during changes to the table. Can't be <code>null</code>, but an empty Collection.
	 *  
	 */
	SortedSet<Trigger> getTriggers();



	Boolean addTrigger( Trigger trigger );



	Boolean removeTrigger( Trigger trigger );
}