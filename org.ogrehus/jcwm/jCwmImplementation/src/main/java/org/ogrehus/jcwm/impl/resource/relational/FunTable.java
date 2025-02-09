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

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.CheckConstraint;
import org.ogrehus.jcwm.api.resource.relational.ForeignKey;
import org.ogrehus.jcwm.api.resource.relational.PrimaryKey;
import org.ogrehus.jcwm.api.resource.relational.SQLDataType;
import org.ogrehus.jcwm.api.resource.relational.SQLIndex;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.Table;
import org.ogrehus.jcwm.api.resource.relational.TableColumn;
import org.ogrehus.jcwm.api.resource.relational.Trigger;
import org.ogrehus.jcwm.api.resource.relational.UniqueConstraint;

import org.ogrehus.jcwm.impl.resource.relational.util.generic.GFunNamedColumnSet;


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
public class FunTable
extends
	GFunNamedColumnSet<CheckConstraint, TableColumn>
implements
	Table
{


	/**
	 * Factory method that creates a new instance of Table by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>notInstantiable</b> - <code>false</code> (not abstract).
	 * </ul>
	 * 
	 * @param name An identifier for the Table within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param isSystem <code>true</code> if the table is a System Table, like a part of the system catalog), <code>false</code> otherwise. Must not
	 * be <code>null</code>.
	 * 
	 * @param isTemporary <code>true</code> if the table content is temporary. SQL92 standards provide two types of temporary tables (local Temporary
	 * and Global Temporary). A System Table, like a part of the system catalog), <code>false</code> otherwise. Must not be <code>null</code>.
	 *
	 * @return A new instance of Table by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>isSystem</code> or <code>isTemporary</code>
	 * is <code>null</code>.
	 * 
	 */
	public FunTable( String name, Boolean isSystem, Boolean isTemporary ) {
		super( name, Visibility._public, Boolean.FALSE );
		this.triggers= new TreeSet<Trigger>();
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( CheckConstraint checkConstraint ) {
		return super.addConstraintGeneric( checkConstraint );
	}



	public Boolean addImporter( Schema schema ) {
		return super.addImporterGeneric( schema );
	}



//====================================================================================================================================================
// GNamespace capabilities
//====================================================================================================================================================



	public Boolean addOwnedElement( ForeignKey foreignKey ) {
		return super.addOwnedElementGeneric( foreignKey );
	}



	public Boolean addOwnedElement( PrimaryKey primarykey ) {
		return super.addOwnedElementGeneric( primarykey );
	}



	public Boolean addOwnedElement( UniqueConstraint uniqueConstraint ) {
		return super.addOwnedElementGeneric( uniqueConstraint );
	}



//====================================================================================================================================================
// GCwmClass capabilities
//====================================================================================================================================================



	public Boolean addIndex( SQLIndex sqlIndex ) {
		return super.addIndexGeneric( sqlIndex );
	}



//====================================================================================================================================================
// GClassifier capabilities
//====================================================================================================================================================



	public Boolean addColumn( String columnName, SQLDataType<?, ?> type ) {
		return super.addFeatureGeneric( new FunTableColumn( columnName, type ) );
	}



	public Set<TableColumn> getColumns() {
		return super.getFeatures();
	}



	public Boolean addColumn( TableColumn tableColumn ) {
		return super.addFeatureGeneric( tableColumn );
	}



	public TableColumn getColumn( String simpleName ) {
		return super.getFeature( TableColumn.class, simpleName );
	}



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
	public Boolean isSystem() {
		return this.isSystem;
	}



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
	public Boolean setSystem( Boolean isSystem ) {
		Boolean old = this.isSystem;
		this.isSystem = isSystem;
		return old;
	}



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
	public Boolean isTemporary() {
		return this.isTemporary;
	}



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
	public Boolean setTemporary( Boolean isTemporary ) {
		Boolean old = this.isTemporary;
		this.isTemporary = isTemporary;
		return old;
	}



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
	 * <li><i>constraints</i> : May not be specified if isTemporary is set to false.</li>
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
	public String getTemporaryScope() {
		return this.temporaryScope;
	}



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
	 * <li><i>constraints</i> : May not be specified if isTemporary is set to false.</li>
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
	public String setTemporaryScope( String temporaryScope ) {
		String old = this.temporaryScope;
		this.temporaryScope = temporaryScope;
		return old;
	}



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
	public SortedSet<Trigger> getTriggers() {
		return this.triggers;
	}



	public Boolean addTrigger( Trigger trigger ) {
		if ( trigger != null && this.triggers.add( trigger ) ) {
			trigger.setTable( this ); // bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeTrigger( Trigger trigger ) {
		if ( trigger == null ) {
			return Boolean.TRUE;
		}
		
		if ( this.triggers.remove( trigger ) ) {
			trigger.removeTable();

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



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
	public Set<UniqueConstraint> getUniqueConstraints() {
		return getOwnedElementsByType( UniqueConstraint.class );
	}



	public Boolean addUniqueConstraint( UniqueConstraint uniqueConstraint ) {
		return addOwnedElementGeneric( uniqueConstraint );
	}



	public Boolean removeUniqueConstraint( UniqueConstraint uniqueConstraint ) {
		return removeOwnedElement( uniqueConstraint );
	}



	public UniqueConstraint getUniqueConstraint( String simpleName ) {
		return super.getOwnedElement( UniqueConstraint.class, simpleName );
	}



	public Boolean addCheckConstraint( CheckConstraint checkConstraint ) {
		return addConstraint( checkConstraint );
	}



	public Set<CheckConstraint> getCheckConstraints() {
		return super.getConstraints();
	}



	public CheckConstraint getCheckConstraint( String simpleName ) {
		return super.getOwnedElement( CheckConstraint.class, simpleName );
	}



	public Boolean removeCheckConstraint( CheckConstraint checkConstraint ) {
		return super.removeConstraint( checkConstraint );
	}



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
	public Set<ForeignKey> getForeignKeys() {
		return getOwnedElementsGeneric( ForeignKey.class );
	}



	public Boolean addForeignKey( ForeignKey foreignKey ) {
		return addOwnedElement( foreignKey );
	}



	public ForeignKey getForeignKey(String simpleName) {
		return super.getOwnedElement( ForeignKey.class, simpleName );
	}



	public Boolean removeForeignKey( ForeignKey foreignKey ) {
		return removeOwnedElement( foreignKey );
	}



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
	 * @param primaryKey A PrimaryKey. The value <code>null</code> removes the PrimaryKey.
	 * 
	 * @return Old value of the property <code>primaryKey</code>. Can't be <code>null</code>.
	 * 
	 */
	public PrimaryKey setPrimaryKey( PrimaryKey primaryKey ) {
		PrimaryKey old = getPrimaryKey(); // might be null
		if ( primaryKey != null && primaryKey.equals( old ) ) {
			return old; // no changes its the same primaryKey
		}
		removePrimaryKey(); // make sure only one primaryKey is assigned
		if ( primaryKey != null ) {
			addOwnedElementGeneric( primaryKey ); // assign new value
		}

		return old;
	}



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
	public PrimaryKey getPrimaryKey() {
		Set<PrimaryKey> aPrimaryKeyOrNot = getOwnedElementsGeneric( PrimaryKey.class );
		if ( aPrimaryKeyOrNot.isEmpty() ) {
			return null; // no PrimaryKey found...
		}

		return aPrimaryKeyOrNot.iterator().next(); // can only be one in the Set, so it must be the first!
	}



	public Boolean removePrimaryKey() {
		PrimaryKey removeMe = getPrimaryKey();
		if ( removeMe == null ) {
			return Boolean.TRUE;
		}
		return removeOwnedElement( removeMe );
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunTable" );
		if ( this.isSystem != null ) {
			out.append( " | isSystem=" + this.isSystem );
		}
		if ( this.isTemporary != null ) {
			out.append( " | isTemporary=" + this.isTemporary );
		}
		if ( this.temporaryScope != null ) {
			out.append( " | temporaryScope=" + this.temporaryScope );
		}
		out.append( collectionToString( "triggers", this.triggers, false ) );
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		return out.toString();
	}



//====================================================================================================================================================
// Properties
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
	 */ 
	protected Boolean isSystem = null;



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
	 */ 
	protected Boolean isTemporary = null;



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
	 * <li><i>constraints</i> : May not be specified if isTemporary is set to false.</li> 
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-1]</b> - Attribute temporaryScope is meaningful only when the isTemporary flag is True.</li>
	 * </ul> 
	 * 
	 */
	protected String temporaryScope = null;



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
	protected SortedSet<Trigger> triggers = null;
}