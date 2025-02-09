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

import org.ogrehus.jcwm.api.objectmodel.core.BooleanExpression;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.resource.relational.util.ActionOrientation;
import org.ogrehus.jcwm.api.resource.relational.util.ConditionTiming;
import org.ogrehus.jcwm.api.resource.relational.util.EventManipulation;


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
public interface Trigger
extends
	GModelElement<
		  Schema
		, Schema
		, Dependency
		, Constraint
	>
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	Schema setNamespace( Schema schema );



	Boolean addDependency( Dependency dependency );



	Boolean addConstraint( Constraint constraint );

	
	Boolean addImporter( Schema catalog );



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
	 * @return Type of event that are using the current Trigger. Can't be <code>null</code>.
	 * 
	 */ 
	EventManipulation getEventManipulation();



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
	 * @param eventManipulation of event that are using the current Trigger. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>eventManipulation</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>eventManipulation</code> is <code>null</code>.
	 * 
	 */ 
	EventManipulation setEventManipulation( EventManipulation eventManipulation );



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
	 * @return A boolean expression that defines when the trigger has to be executed. Can't be <code>null</code>.
	 * 
	 */ 
	BooleanExpression getActionCondition();



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
	 * @param actionCondition A boolean expression that defines when the trigger has to be executed. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>actionCondition</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>actionCondition</code> is <code>null</code>.
	 *  
	 */ 
	BooleanExpression setActionCondition( BooleanExpression actionCondition );



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
	 * @return The Trigger action itself. Can't be <code>null</code>.
	 * 
	 */ 
	ProcedureExpression getActionStatement();



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
	 * @param actionStatement The Trigger action itself. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>actionStatement</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>actionStatement</code> is <code>null</code>.
	 * 
	 */
	ProcedureExpression setActionStatement( ProcedureExpression actionStatement );



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
	 * @return Indicates if the trigger is called once per statement execution or before or after each row of the table is modified. Can't
	 * be <code>null</code>.
	 * 
	 */
	ActionOrientation getActionOrientation();



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
	 * @param actionOrientation Indicates if the trigger is called once per statement execution or before or after each row of the table is modified. 
	 * Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>actionOrientation</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>actionOrientation</code> is <code>null</code>.
	 * 
	 */
	ActionOrientation setActionOrientation( ActionOrientation actionOrientation );



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
	 * @return Indicates if the trigger activity is run before or after the statement or row is modified. Must not be <code>null</code>. Must not
	 * be <code>null</code>.
	 * 
	 */ 
	ConditionTiming getConditionTiming();



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
	 * @param conditionTiming Indicates if the trigger activity is run before or after the statement or row is modified. Must not
	 * be <code>null</code>.
	 * 
	 * @return Old value of the property <code>conditionTiming</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>conditionTiming</code> is <code>null</code>.
	 * 
	 */
	ConditionTiming setConditionTiming( ConditionTiming conditionTiming );



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
	 * @return The alias for the owning table name, used in the actionStatement, to represent the state of the table after the insert/delete/update.
	 * Can't be <code>null</code>.
	 * 
	 */
	String getConditionReferenceNewTable();



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
	 * @param conditionReferenceNewTable The alias for the owning table name, used in the actionStatement, to represent the state of the table after
	 * the insert/delete/update. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>conditionReferenceNewTable</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>conditionReferenceNewTable</code> is <code>null</code>.
	 * 
	 */
	String setConditionReferenceNewTable( String conditionReferenceNewTable );



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
	 * @return The alias for the name of the owning table, used in the actionStatement, to represent the state of the table before the
	 * update/delete/insert. Can't be <code>null</code>.
	 * 
	 */
	String getConditionReferenceOldTable();



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
	 * @param conditionReferenceOldTable The alias for the name of the owning table, used in the actionStatement, to represent the state of the table
	 * before the update/delete/insert. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>conditionReferenceOldTable</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>conditionReferenceOldTable</code> is <code>null</code>.
	 * 
	 */
	String setConditionReferenceOldTable( String conditionReferenceOldTable );



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
	 * @return A Collection of Tables that are referenced by the actionStatement or the actionCondition. Can't be <code>null</code>, but an empty 
	 * Collection.
	 * 
	 */
	Set<NamedColumnSet<?>> getUsedColumnSets();



	Boolean addUsedColumnSet( NamedColumnSet<?> usedColumnSet );



	Boolean removeUsedColumnSet( NamedColumnSet<?> usedColumnSet );



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
	 * @return The table that owns this Trigger. Can't be <code>null</code>.
	 * 
	 */
	Table getTable();



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
	 * @param table The table that owns this Trigger. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>conditionReferenceOldTable</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>table</code> is <code>null</code>.
	 * 
	 */
	Table setTable( Table table );



	Boolean removeTable();
}