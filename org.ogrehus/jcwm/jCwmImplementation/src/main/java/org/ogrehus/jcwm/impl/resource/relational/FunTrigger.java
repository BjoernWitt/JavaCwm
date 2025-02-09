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

import org.ogrehus.jcwm.api.objectmodel.core.BooleanExpression;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.NamedColumnSet;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.Table;
import org.ogrehus.jcwm.api.resource.relational.Trigger;
import org.ogrehus.jcwm.api.resource.relational.util.ActionOrientation;
import org.ogrehus.jcwm.api.resource.relational.util.ConditionTiming;
import org.ogrehus.jcwm.api.resource.relational.util.EventManipulation;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;


/**
 * An action run by the DBMS when specified events occur on the table owning the Trigger.
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunTrigger
extends
	GFunModelElement<
		  Schema
		, Schema
		, Dependency
		, Constraint
	>
implements
	Trigger
{


	/**
	 * Factory method that creates a new instance of Trigger by specific parameters.
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
	 * @param conditionTiming Indicates if the trigger activity is run before or after the statement or row is modified.
	 * Must not be <code>null</code>.
	 * 
	 * @param conditionReferenceNewTable The alias for the owning table name, used in the actionStatement, to represent the state of the table after
	 * the insert/delete/update. Must not be <code>null</code>.
	 * 
	 * @param conditionReferenceOldTable The alias for the name of the owning table, used in the actionStatement, to represent the state of the table
	 * before the update/delete/insert. Must not be <code>null</code>.
	 * 
	 * @param table The table that owns this Trigger. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>eventManipulation</code>,
	 * <code>actionCondition</code>, <code>actionStatement</code>, <code>actionOrientation</code>, <code>conditionTiming</code>,
	 * <code>conditionReferenceNewTable</code>, <code>conditionReferenceOldTable</code> or <code>table</code> is <code>null</code>.
	 * 
	 */
	public FunTrigger ( 
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
		super( name, Visibility._public ); // throws NullPointerException
		this.usedColumnSets = new HashSet<NamedColumnSet<?>>();
		setEventManipulation( eventManipulation );
		setActionCondition( actionCondition );
		setActionStatement( actionStatement );
		setActionOrientation( actionOrientation );
		setConditionTiming( conditionTiming );
		setConditionReferenceNewTable( conditionReferenceNewTable );
		setConditionReferenceOldTable( conditionReferenceOldTable );
		setTable( table );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Schema setNamespace( Schema schema ) {
		return super.setNamespaceGeneric( schema );
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint) {
		return super.addConstraintGeneric( constraint );
	}



	public Boolean addImporter( Schema schema ) {
		return super.addImporterGeneric( schema );
	}



//====================================================================================================================================================
// Trigger capabilities
//====================================================================================================================================================



	public EventManipulation getEventManipulation() {
		return this.eventManipulation;
	}



	public EventManipulation setEventManipulation( EventManipulation eventManipulation ) {
		if ( eventManipulation == null ) {
			throw new NullPointerException( "The parameter: eventManipulation must not be null." );
		}
		EventManipulation old = this.eventManipulation;
		this.eventManipulation = eventManipulation;

		return old;
	}



	public BooleanExpression getActionCondition() {
		return this.actionCondition;
	}



	public BooleanExpression setActionCondition( BooleanExpression actionCondition ) {
		if ( actionCondition == null ) {
			throw new NullPointerException( "The parameter: actionCondition must not be null." );
		}
		BooleanExpression old = this.actionCondition;
		this.actionCondition = actionCondition;

		return old;
	}



	public ProcedureExpression getActionStatement() {
		return this.actionStatement;
	}



	public ProcedureExpression setActionStatement( ProcedureExpression actionStatement ) {
		if ( actionStatement == null ) {
			throw new NullPointerException( "The parameter: actionStatement must not be null." );
		}
		ProcedureExpression old = this.actionStatement;
		this.actionStatement = actionStatement;

		return old;
	}



	public ActionOrientation getActionOrientation() {
		return this.actionOrientation;
	}



	public ActionOrientation setActionOrientation( ActionOrientation actionOrientation ) {
		if ( actionOrientation == null ) {
			throw new NullPointerException( "The parameter: actionOrientation must not be null." );
		}
		ActionOrientation old = this.actionOrientation;
		this.actionOrientation = actionOrientation;

		return old;
	}



	public ConditionTiming getConditionTiming() {
		return this.conditionTiming;
	}



	public ConditionTiming setConditionTiming( ConditionTiming conditionTiming ) {
		if ( conditionTiming == null ) {
			throw new NullPointerException( "The parameter: conditionTiming must not be null." );
		}
		ConditionTiming old = this.conditionTiming;
		this.conditionTiming = conditionTiming;

		return old;
	}



	public String getConditionReferenceNewTable() {
		return this.conditionReferenceNewTable;
	}



	public String setConditionReferenceNewTable( String conditionReferenceNewTable ) {
		if ( conditionReferenceNewTable == null ) {
			throw new NullPointerException( "The parameter: conditionReferenceNewTable must not be null." );
		}
		String old = this.conditionReferenceNewTable;
		this.conditionReferenceNewTable = conditionReferenceNewTable;

		return old;
	}



	public String getConditionReferenceOldTable() {
		return this.conditionReferenceOldTable;
	}



	public String setConditionReferenceOldTable( String conditionReferenceOldTable ) {
		if ( conditionReferenceOldTable == null ) {
			throw new NullPointerException( "The parameter: conditionReferenceOldTable must not be null." );
		}
		String old = this.conditionReferenceOldTable;
		this.conditionReferenceOldTable = conditionReferenceOldTable;

		return old;
	}



	public Boolean addUsedColumnSet( NamedColumnSet<?> usedColumnSet ) {
		if ( usedColumnSet != null && this.usedColumnSets.add( usedColumnSet ) ) {
			if ( !usedColumnSet.getUsingTriggers().contains( this ) ) {
				usedColumnSet.addUsingTrigger( this );
			}

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeUsedColumnSet( NamedColumnSet<?> usedColumnSet ) {
		if ( usedColumnSet != null && this.usedColumnSets.remove( usedColumnSet ) ) {
			usedColumnSet.removeUsingTrigger( this );

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Set<NamedColumnSet<?>> getUsedColumnSets() {
		return this.usedColumnSets;
	}



	public Table getTable() {
		return this.table;
	}



	public Table setTable( Table table ) {
		if ( table == null ) {
			throw new NullPointerException( "The parameter: table must not be null." );
		}
		if ( table.equals( this.table ) ) {
			return table; // same table do nothing...
		}
		Table old = this.table;
		if ( old != null ) {
			old.removeTrigger( this );
		}
		this.table = table;
		if ( !this.table.getTriggers().contains( this ) ) {
			this.table.addTrigger( this );
		}

		return old;
	}

	

	public Boolean removeTable() {
		if ( this.table == null ) {
			return Boolean.TRUE;
		}
		Table old = this.table;
		this.table = null;
		if ( old != null && old.getTriggers().contains( this ) ) {
			return old.removeTrigger( this );
		}
		return Boolean.TRUE;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunTrigger" );
		if ( this.eventManipulation != null ) {
			out.append( " | eventManipulation=" + this.eventManipulation );
		}
		if ( this.actionCondition != null ) {
			out.append( " | actionCondition=" + this.actionCondition.getBody() );
		}
		if ( this.actionStatement != null ) {
			out.append( " | actionStatement=" + this.actionStatement.getLanguage() );
		}
		if ( this.actionOrientation != null ) {
			out.append( " | actionOrientation=" + this.actionOrientation );
		}
		if ( this.conditionTiming != null ) {
			out.append( " | conditionTiming=" + this.conditionTiming );
		}
		if ( this.conditionReferenceNewTable != null ) {
			out.append( " | conditionReferenceNewTable=" + this.conditionReferenceNewTable );
		}
		if ( this.conditionReferenceOldTable != null ) {
			out.append( " | conditionReferenceOldTable=" + this.conditionReferenceOldTable );
		}
		if ( this.table != null ) {
			out.append( " | table=" + this.table.getQualifiedName() );
		}
		out.append( collectionToString( "usedColumnSets", this.usedColumnSets, true ) );
		out.append( " | \nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Indicates what types of events are using the current Trigger.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : EventManipulationType</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */
	protected EventManipulation eventManipulation = null;



	/**
	 * A boolean expression that defines when the trigger has to be executed.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : BooleanExpression</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */
	protected BooleanExpression actionCondition = null;



	/**
	 * The Trigger action itself.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : ProcedureExpression</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */ 
	protected ProcedureExpression actionStatement = null;



	/**
	 * Indicates if the trigger is called once per statement execution or before or after each row of the table is modified.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>enum</i> : ActionOrientationType</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */
	protected ActionOrientation actionOrientation = null;



	/**
	 * Indicates if the trigger activity is run before or after the statement or row is modified.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>enum</i> : ConditionTimingType</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */
	protected ConditionTiming conditionTiming = null;



	/**
	 * The alias for the owning table name, used in the actionStatement, to represent the state of the table after the insert/delete/update.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : String</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */
	protected String conditionReferenceNewTable = null;



	/**
	 * The alias for the name of the owning table, used in the actionStatement, to represent the state of the table before the update/delete/insert.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : String</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */
	protected String conditionReferenceOldTable = null;



	/**
	 * Tables referenced by the actionStatement or the actionCondition.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : NamedColumnSet</li>
	 * <li><i>defined by</i> : TriggerUsingColumnSet::usedColumnSet</li>
	 * <li><i>multiplicity</i> : zero or more</li>
	 * <li><i>inverse</i> : NamedColumnSet::usingTrigger</li>
	 * </ul>
	 * 
	 */
	protected Set<NamedColumnSet<?>> usedColumnSets = null;



	/**
	 * The table that owns the Trigger.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : Table</li>
	 * <li><i>defined by</i> : TableOwningTrigger::table</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * <li><i>inverse</i> : Table::trigger</li>
	 * </ul>
	 * 
	 */
	protected Table table = null;
}