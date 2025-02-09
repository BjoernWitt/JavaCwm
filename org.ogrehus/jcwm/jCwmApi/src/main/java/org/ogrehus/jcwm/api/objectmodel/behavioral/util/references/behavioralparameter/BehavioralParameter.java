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
package org.ogrehus.jcwm.api.objectmodel.behavioral.util.references.behavioralparameter;

import org.ogrehus.jcwm.api.objectmodel.behavioral.BehavioralFeature;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;

public interface BehavioralParameter<BEHAVIORAL extends BehavioralFeature<?>>
extends
	ModelElement 
{


	/**
	 * Reference: behavioralFeature
	 * <p>
	 * References the BehavioralFeature instance for which the Parameter instance describes a parameter.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: BehavioralFeature</li>
	 * <li><i>defined by</i>: BehavioralFeatureParameter::behavioralFeature</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: BehavioralFeature::parameter</li>
	 * </ul>
	 * 
	 * @return A BehavioralFeature for which this parameter is a parameter.  
	 * 
	 */
	BEHAVIORAL getBehavioralFeature();



	/**
	 * Deregisters a behavioralFeature from this parameter.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: BehavioralFeature</li>
	 * <li><i>defined by</i>: BehavioralFeatureParameter::behavioralFeature</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: BehavioralFeature::parameter</li>
	 * </ul>
	 * 
	 * @return The old value of the property <code>behavioralFeature</code>. 
	 * 
	 */
	Boolean removeBehavioralFeature();



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
	 * @param defaultValue The new value of the property <code>defaultValue</code>. 
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
	 * @throws NullPointerException is thrown if the parameter <code>kind</code> is <code>null</code>.
	 * 
	 */
	ParameterDirection setKind( ParameterDirection kind );



	/**
	 * The operation hasSameSignature checks if the parameter has the same signature as the instance itself.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * hasSameSignature ( p : Parameter ) : Boolean;
	 * hasSameSignature (p) = (self.type = p.type) and (self.kind = p.kind)
	 * </pre>
	 * 
	 * @param behavioralFeature is the argument to be checked for same signature.
	 * 
	 * @return <code>true</code> if the OCL was accomplished by the <code>parameter</code>, <code>false</code> 
	 * otherwise.
	 *  
	 */
	<T extends GParameter<?, ?, ?, ?>> Boolean hasSameSignature( T parameter );
}