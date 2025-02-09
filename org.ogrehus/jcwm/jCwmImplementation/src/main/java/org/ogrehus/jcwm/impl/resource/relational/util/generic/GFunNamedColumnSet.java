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
package org.ogrehus.jcwm.impl.resource.relational.util.generic;

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.resource.relational.Column;
import org.ogrehus.jcwm.api.resource.relational.NamedColumnSet;
import org.ogrehus.jcwm.api.resource.relational.SQLStructuredType;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.Trigger;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumn;


/**
 * A cataloged set of columns, which may be Table or View.
 * <p>
 * Note for typed tables: It is assumed that the typed table will own a set of columns conforming to the type they are OF. This set of columns allows
 * the manipulation of the table by products that ignore this [SQL] extension. It also allows the columns of type REF, to be copied to a column with a
 * SCOPE reference.
 * </p>
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <CONSTRAINT> The type of the Constraint under which this Class is.
 * @param <COLUMN> The type of Column this ColumnSet contains.
 * 
 */
public class GFunNamedColumnSet<
	  CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, COLUMN     extends GColumn<?, ?>
> extends
	GFunColumnSet<Schema, Schema, CONSTRAINT, COLUMN>
implements
	NamedColumnSet<COLUMN>
{


	/**
	 * Creates a new instance of NamedColumnSet by specific parameters.
	 * <p>
	 * A NamedColumnSet is a cataloged set of columns, which may be Table or View.
	 * </p>
	 * <p>
	 * Note for typed tables: It is assumed that the typed table will own a set of columns conforming to the type they are OF. This set of columns
	 * allows the manipulation of the table by products that ignore this [SQL] extension. It also allows the columns of type REF, to be copied to a
	 * column with a SCOPE reference.
	 * </p> 
	 * 
	 * @param name An identifier for the NamedColumnSet within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the NamedColumnSet within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this NamedColumnSet is abstract and can not be instantiated, <code>false</code> otherwise.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 * 
	 */
	protected GFunNamedColumnSet( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility, notInstantiable );
		this.optionScopeColumns = new HashSet<Column>();
		this.usingTriggers      = new HashSet<Trigger>();
	}



//====================================================================================================================================================
// OwnedElement<Schema> capabilities
//====================================================================================================================================================



	public Schema getSchema() {
		return getNamespace();
	}



	public Schema setNamespace( Schema schema ) {
		return super.setNamespaceGeneric( schema );
	}



	public Schema setSchema( Schema schema ) {
		return setNamespace( schema );
	}



	public Boolean addImporter( Schema schema ) {
		return super.addImporterGeneric( schema );
	}
	
	
	
//====================================================================================================================================================
// GNamedColumnSet capabilities
//====================================================================================================================================================



	public Boolean addOptionScopeColumn( Column column ) {
		if ( column != null && this.optionScopeColumns.add( column ) ) {
			column.setOptionScopeColumnSet( this ); // bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeOptionScopeColumn( Column column ) {
		if ( column == null ) {
			return Boolean.TRUE;
		}
		if ( this.optionScopeColumns.remove( column ) ) {
			column.removeOptionScopeColumnSet(); // remove bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Set<Column> getOptionScopeColumns() {
		return this.optionScopeColumns;
	}



	public SQLStructuredType getType() {
		return this.type;
	}



	public Boolean removeType() {
		if ( this.type == null ) {
			return Boolean.TRUE;
		}
		SQLStructuredType old = this.type;
		this.type = null;

		return old.removeColumnSet( this );
	}



	public SQLStructuredType setType( SQLStructuredType type ) {
		if ( type != null && type.equals( this.type ) ) {
			return type; // no changes, cause its the same namespace
		}
		SQLStructuredType old = this.type; // return value 
		if ( old != null  ) {
			old.removeColumnSet( this );
		}
		this.type = type;
		if ( this.type != null ) {
			if ( !this.type.getColumnSets().contains( this ) ) {
				invokeByReflection( this.type, "addColumnSet", this ); // organize the bidirectional reference 
			}
		}

		return old;
	}



	public Boolean addUsingTrigger( Trigger usingTrigger ) {
		if ( usingTrigger != null && this.usingTriggers.add( usingTrigger ) ) {
			usingTrigger.addUsedColumnSet( this ); // bi-directional reference
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Set<Trigger> getUsingTriggers() {
		return this.usingTriggers;
	}



	public Boolean removeUsingTrigger( Trigger usingTrigger ) {
		if ( usingTrigger == null ) {
			return Boolean.TRUE;
		}
		if ( this.usingTriggers.remove( usingTrigger ) ) {
			usingTrigger.removeUsedColumnSet( this );

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunNamedColumnSet" );
		if ( this.type != null ) {
			out.append( " | type=" + this.type.getQualifiedName() );
		}
		out.append( collectionToString( "usingTriggers", this.usingTriggers, true ) );        
		out.append( collectionToString( "optionScopeColumns", this.optionScopeColumns, true ) );
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * A Trigger that references this NamedColumnSet in its expression.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : Trigger</li>
	 * <li><i>defined by</i> : TriggerUsingColumnSet::usingTrigger</li>
	 * <li><i>multiplicity</i> : zero or more</li>
	 * <li><i>inverse</i> : Trigger::usedColumnSet</li>
	 * </ul>
	 * 
	 */
	protected Set<Trigger> usingTriggers = null;



	/**
	 * For typed Tables and Views, reference the base SQLStructuredType.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : SQLStructuredType</li>
	 * <li><i>defined by</i> : ColumnSetOfStructuredType::type</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>inverse</i> : SQLStructuredType::columnSet</li>
	 * </ul>
	 * 
	 */
	protected SQLStructuredType type = null;



	/**
	 * Associates Columns with NamedColumnSets they reference in their OPTIONS clause.
	 * <p>
	 * This NamedColumnSet is referenced in a SCOPE clause of the referenced Column.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : Column</li>
	 * <li><i>defined by</i> : ColumnOptionsColumnSet::optionScopeColumn</li>
	 * <li><i>multiplicity</i> : zero or more</li>
	 * <li><i>inverse</i> : Column::optionScopeColumnSet</li>
	 * </ul>
	 * 
	 */
	protected Set<Column> optionScopeColumns = null;
}