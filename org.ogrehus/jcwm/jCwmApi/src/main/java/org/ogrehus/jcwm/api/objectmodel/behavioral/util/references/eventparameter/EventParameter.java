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
package org.ogrehus.jcwm.api.objectmodel.behavioral.util.references.eventparameter;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;

import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;

public interface EventParameter<EVENT extends ParameterEvent<?>>
extends
	ModelElement
{


	/**
	 * Reference: event
	 * <p>
	 * References the Event instance for which the Parameter instance describes a parameter.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Event</li>
	 * <li><i>defined by</i>: EventParameter::event</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: Event::parameter</li>
	 * </ul>
	 * 
	 * @return An Event for which this parameter is a parameter.  
	 * 
	 */
	EVENT getEvent();



//	/**
//	 * Reference: event
//	 * <p>
//	 * <b>Characteristics</b>:
//	 * </p>
//	 * <ul>
//	 * <li><i>class</i>: Event</li>
//	 * <li><i>defined by</i>: EventParameter::event</li>
//	 * <li><i>multiplicity</i>: zero or one</li>
//	 * <li><i>inverse</i>: Event::parameter</li>
//	 * </ul>
//	 * 
//	 * @see #getEvent() for description on this reference.
//	 * 
//	 * @param event The new event assigned to this parameter.
//	 * 
//	 * @return The old value of the property <code>Event</code>.  
//	 * 
//	 */
//	EVENT setEvent( EVENT event );



	/**
	 * Abstract, cause can only be determined, when bounded generic type of EVENT is defined by a non-generic type.
	 * 
	 * @return <code>true</code> if the value for event changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeEvent();
	
	
	/**
	 * Attribute: defaultValue
	 * <p>
	 * An Expression whose evaluation yields a value to be used when no argument is supplied for the Parameter.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: Expression</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @return the defaultValue to be used, when no other value is available.
	 * 
	 */
	Expression getDefaultValue();



	/**
	 * Sets the value of the property <code>defaultValue</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: Expression</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @see #getDefaultValue()
	 * 
	 * @param defaultValue The new value of the property 
	 * <code>defaultValue</code>. 
	 * 
	 * @return the defaultValue to be used, when no other value is available.
	 * 
	 */
	Expression setDefaultValue( Expression defaultValue );



	/**
	 * Attribute: kind
	 * <p>
	 * Specifies what kind of a Parameter is required.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>: ParameterDirectionKind</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 */
	ParameterDirection getKind();



	/**
	 * Sets the new kind of the property <code>kind</kind>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>: ParameterDirectionKind</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @see #getKind() for description on the property <code>kind</code>.
	 * 
	 * @param kind The new kind of the property <code>kind</code>.Must not be <code>null</code>. 
	 * 
	 * @return The old value of the property <code>kind</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>type</code> is <code>null</code>.
	 * 
	 */
	ParameterDirection setKind( ParameterDirection kind );
}