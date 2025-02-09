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
package org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.references.behavioralparameter.BehavioralParameter;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.references.eventparameter.EventParameter;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;


/**
 * Parameters are used in the specification of operations, methods, and events. 
 * <p>
 * A Parameter may include a name, type, and direction of communication.
 * </p>  
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GEvent.
 * @param <PACKAGE> The type of the importer of this GEvent.
 * @param <DEPENDENCY> The type of ClientDependency of this GEvent.
 * @param <CONSTRAINT> The type of the Constraint under which this GEvent is.
 * @param <PARAMETER> The type of Parameter that comprise the signature of the Event.
 * 
 */
public interface GParameter<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, EVENT      extends GEvent<?, ?, ?, ?, ?>
	, BEHAVIORAL extends GBehavioralFeature<?, ?, ?, ?>
> extends
	  GModelElement<NAMESPACE, PACKAGE, Dependency, Constraint>
	, EventParameter<EVENT>
	, BehavioralParameter<BEHAVIORAL>
{


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



	/**
	 * Reference: type
	 * <p>
	 * Designates a Classifier to which an argument value must conform.
	 * </p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Classifier</li>
	 * <li><i>defined by</i>: ParameterType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return The classifier describing the type of this Parameter.  
	 * 
	 */
	GClassifier<?, ?, ?, ?, ?, ?> getType();



	/**
	 * Reference: type
	 * <p>
	 * Designates a Classifier to which an argument value must conform.
	 * </p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Classifier</li>
	 * <li><i>defined by</i>: ParameterType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @see #getType() for description on this reference.
	 * 
	 * @param type the new value of the property <code>type</code>. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>type</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>type</code> is <code>null</code>.
	 * 
	 */
	<T extends GClassifier<?, ?, ?, ?, ?, ?>> GClassifier<?, ?, ?, ?, ?, ?> setType( T type );



//======================================================================================================================
// GModelElement capabilities	
//======================================================================================================================



	/**
	 * Adds a dependency to this CwmMethod.
	 * 
	 * @param dependency A Dependency in witch this CwmMethod is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds another Constraint to this CwmMethod.
	 * 
	 * @param constraint A Constrain that must be satisfied by this CwmMethod.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );
}