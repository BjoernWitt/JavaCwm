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
package org.ogrehus.jcwm.api.objectmodel.behavioral;

import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GBehavioralFeature;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GCwmMethod;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.resource.relational.Procedure;


/**
 * Operation is a service that can be requested from an object to effect behavior.
 * <p>
 * An Operation has a signature, which describes the parameters that are possible (including possible return values).
 * </p>
 * <p>
 * In the meta-model, an Operation is a BehavioralFeature that can be applied to instances of the Classifier that contains the Operation.
 * </p>
 * <p>
 * Operation is the specification, while CwmMethod is the implementation.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Operation
extends
	GBehavioralFeature<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ? ,? ,? ,? ,?, ?>
		, GClassifier<?, ?, ?, ?, ?, ?>
		, GParameter<?, ?, ?, ?>
	>
{


//====================================================================================================================================================
// Operation capabilities
//====================================================================================================================================================



	/**
	 * Attribute: isAbstract
	 * <p>
	 * If true, then the Operation does not have an implementation, and one must be supplied by a descendant.
	 * </p>
	 * <p> 
	 * If false, the Operation must have an implementation in the class or inherited from an ancestor.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the operation doesn't have an implementation, and one must be supplied by a descendant, <code>false</code> means 
	 * the implementation is in the class or was inherited from an ancestor.
	 * 
	 */
	Boolean isAbstract();



	/**
	 * Sets the value of the property <code>isAbstract</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 *
	 * @see #isAbstract() for description on the property <code>isAbstract</code>.
	 * 
	 * @param isAbstract The new Value of the property <code>isAbstract</code>.
	 * 
	 * @return The value set before. Cannot be <code>null</code>.
	 * 
	 */
	Boolean setAbstract( Boolean isAbstract );



	/**
	 * Adds a parameter to the Set of ordered Parameter instances that comprise the signature of this Operation.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Parameter</li>
	 * <li><i>defined by</i>: BehavioralFeature::parameter</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Parameter::behavioralFeature</li>
	 * </ul>
	 * 
	 * @param parameter Will be added to the set of ordered Parameter instances that comprise the signature of this Operation.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addParameter( Parameter parameter );



	/**
	 * References the set of CwmMethod instances defined for the Operation.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: CwmMethod</li>
	 * <li><i>defined by</i>: OperationMethod::method</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * <li><i>inverse</i>: CwmMethod::specification</li>
	 * </ul>
	 * 
	 * @return Set of methods defined for this operations.
	 * 
	 */
	Set<GCwmMethod< ?, ?, ?, ?>> getMethods();



	/**
	 * Adds a method defined for this Operation.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: CwmMethod</li>
	 * <li><i>defined by</i>: OperationMethod::method</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * <li><i>inverse</i>: CwmMethod::specification</li>
	 * </ul>
	 * 
	 * @param method Adds a method defined for this Operation.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addMethod( CwmMethod method );



	/**
	 * Adds a procedure defined for this Operation.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Procedure</li>
	 * <li><i>defined by</i>: OperationMethod::method</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * <li><i>inverse</i>: CwmMethod::specification</li>
	 * </ul>
	 * 
	 * @param procedure Adds a method defined for this Operation.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addMethod( Procedure procedure );



	Boolean removeMethod( GCwmMethod<?, ?, ?, ?> method );
}