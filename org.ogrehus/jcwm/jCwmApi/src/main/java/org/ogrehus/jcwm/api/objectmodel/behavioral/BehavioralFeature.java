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

import java.util.SortedSet;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GBehavioralFeature;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.references.behavioralparameter.BehavioralParameter;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;


public interface BehavioralFeature<PARAMETER extends BehavioralParameter<?>> 
extends
	ModelElement
{


	/**
	 * Removes a parameter from this Behavioral instance.
	 * 
	 * <p>
	 * This method cancels the registration of this Behavioral from the removed parameter.
	 * </p>
	 * 
	 * @param parameter The parameter to be removed.  
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeParameter( BehavioralParameter<?> parameter );



	/**
	 * Reference: An ordered set of Parameters for the BehavioralFeature.
	 * <p>
	 * To call the BehavioralFeature, the caller must supply a list of values compatible with the types of the Parameters.
	 * </p>
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-4-1]</b> All Parameters should have a unique name.</li>
	 * <li><b>[C-4-2]</b> The type of the Parameters should be included in the namespace of the Classifier.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Parameter</li>
	 * <li><i>defined by</i>: BehavioralFeatureParameter::parameter</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Parameter::behavioralFeature</li>
	 * </ul>
	 * 
	 * @return A SortedSet of the parameters assigned to this behaviouralFeature, can't be <code>null</code> but an empty set.
	 * 
	 */
	SortedSet<PARAMETER> getParameters();



	/**
	 * Returns a parameter by simple name.
	 * 
	 * @param simpleName Name within this behavioralFeature.
	 * 
	 * @return The specific parameter if avail, <code>null</code> otherwise.
	 * 
	 */
	PARAMETER getParameter( String simpleName );



	/**
	 * Creates a new parameter and adds it to this behavioralFeature.
	 * <p>
	 * <b>Default initialization values:</b>
	 * </p> 
	 * <ul>
	 * <li><b>visibility</b> - for system without specific visibility the value is <code>public</code></li>
	 * </ul> 
	 * 
	 * @param <T> Wildcard capturing for generic use of any dataType that extends <code>GClassifier&lt;?, ?, ?, ?, ?, ?&gt;</code>
	 *  
	 * @param name SimpleName of the new parameter to be added
	 * 
	 * @param kind Determines the kind of the parameter, like IN/OUT//INOUT/...
	 * 
	 * @param type The dataType of this parameter, determines the generic type of the new parameter. 
	 * 
	 * @return <code>true</code> if the collection of parameters has changed as a result of this call, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>kind</code> or <code>type</code> is <code>null</code>.
	 * 
	 */
	<T extends GClassifier<?, ?, ?, ?, ?, ?>> Boolean addParameter( String name, ParameterDirection kind, T type );



	/**
	 * Creates a new parameter and adds it to this behavioralFeature.
	 * 
	 * @param <T> Wildcard capturing for generic use of any dataType that extends <code>GClassifier&lt;?, ?, ?, ?, ?, ?&gt;</code>
	 *  
	 * @param name SimpleName of the new parameter to be added
	 * 
	 * @param visibility Specifies extent of the visibility of the Constraint within its owning Namespace. Must not be <code>null</code>. 
	 * 
	 * @param kind Determines the kind of the parameter, like IN/OUT//INOUT/...
	 * 
	 * @param type The dataType of this parameter, determines the generic type of the new parameter. 
	 * 
	 * @return <code>true</code> if the collection of parameters has changed as a result of this call, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, 
	 * <code>kind</code> or <code>type</code> is <code>null</code>.  
	 *  
	 */
	<T extends GClassifier<?, ?, ?, ?, ?, ?>> Boolean addParameter( String name
																  , Visibility visibility
																  , ParameterDirection kind
																  , T type );



	/**
	 * Specifies whether an execution of the BehavioralFeature leaves the state of the system unchanged. 
	 * <ul>
	 * <li><code>true</code> indicates that the state is unchanged;</li> 
	 * <li><code>false</code> indicates that side-effects may occur.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the state of the system stays unchanged after execution, <code>false</code> otherwise.
	 * 
	 */
	Boolean isQuery();



	/**
	 * Changes this behavioral BehavioralFeature to cause side-effects on the related system or not.
	 * <ul>
	 * <li><code>true</code> indicates that the state is unchanged;</li> 
	 * <li><code>false</code> indicates that side-effects may occur.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @see #isQuery() For more Information of the property <code>isQuery</code>.
	 * 
	 * @param isQuery <code>true</code> indicates that the state is unchanged; <code>false</code> indicates that side-effects may occur. Must not 
	 * be <code>null</code>.
	 * 
	 * @return The old value of the property <code>isQuery</code>. May not be <code>null</code>. 
	 * 
	 * @throws NullPointerException Is thrown if the parameter <code>isQuery</code> was <code>null</code>.
	 * 
	 */
	Boolean setQuery( Boolean isQuery );



	/**
	 * The operation hasSameSignature checks if the argument has the same signature as the instance itself.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * hasSameSignature ( b : BehavioralFeature ) : Boolean;
	 * hasSameSignature (b) =
	 *     (self.name = b.name) 
	 *     and
	 *     (self.parameter->size = b.parameter->size) 
	 *     and
	 *     Sequence{ 1..(self.parameter->size) }
	 *         ->forAll( index : Integer | b.parameter->at(index).type = 
	 *             self.parameter->at(index).type 
	 *             and
	 *             b.parameter->at(index).kind = self.parameter->at(index).kind
	 *         )
	 * </pre>
	 * 
	 * @param behavioralFeature is the argument to be checked for same signature.
	 * 
	 * @return <code>true</code> if the OCL was accomplished by the <code>behavioralFeature</code>, <code>false</code> 
	 * otherwise.
	 *  
	 */
	Boolean hasSameSignature( GBehavioralFeature<?, ?, ?, ?> behavioralFeature );
}