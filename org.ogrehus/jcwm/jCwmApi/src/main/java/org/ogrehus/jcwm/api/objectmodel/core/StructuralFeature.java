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
package org.ogrehus.jcwm.api.objectmodel.core;

import org.ogrehus.jcwm.api.objectmodel.core.util.Changeable;
import org.ogrehus.jcwm.api.objectmodel.core.util.Ordering;
import org.ogrehus.jcwm.api.objectmodel.core.util.Scope;;


/**
 * A structural feature refers to a static feature of a model element.
 * 
 * <p>
 * In the meta-model, a StructuralFeature declares a structural aspect of a Classifier that is typed, such as an attribute. For example, it specifies 
 * the multiplicity and changeability of the StructuralFeature. StructuralFeature is an abstract meta-class.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, August 2008
 * 
 */
public interface StructuralFeature
extends
	Feature
{


	/**
	 * Return the value of the property <code>changeability</code>.
	 * <p>
	 * Specifies whether the value may be modified after the object is created.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>			: ChangeableKind</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @return The value of the property <code>changeability</code>. Can not be <code>null</code>.
	 * 
	 */
	Changeable getChangeability();



	/**
	 * Sets a new value for the property <code>changeability</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>			: ChangeableKind</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @param changeability The new value for the changeability of this 
	 * structuralFeature.
	 * 
	 * @return The value of the property <code>changeability</code>. Can not be <code>null</code>.
	 * 
	 * @see #getChangeability() for description of the property <code>changeability</code>.	 
	 * 
	 * @throws NullPointerException is thrown if the parameter type is <code>null</code>. 
	 * 
	 */
	Changeable setChangeability( Changeable changeability );



	/**
	 * Returns the value of the property <code>multiplicity</code>.
	 * <p>
	 * The possible number of data values for the feature that may be held by an instance. The cardinality of the set of values is an implicit part of 
	 * the feature. In the common case in which the multiplicity is 1..1, then the feature is a scalar; that is, it holds exactly one value.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>			: Multiplicity</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * </ul>
	 * 
	 * @return The Multiplicity of this structuralFeature. May be <code>null</code> if none exists.
	 *  
	 */
	Multiplicity getMultiplicity();



	/**
	 * Sets the new value of the property <code>multiplicity</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>			: Multiplicity</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * </ul>
	 * 
	 * @param multiplicity The new value of the property <code>multiplicity</code>. 
	 * 
	 * @return The old value of the  property <code>multiplicity</code>. May be <code>null</code> if none exists. 
	 * 
	 * @see #getMultiplicity for description of the property <code>multiplicity</code>.
	 * 
	 */
	Multiplicity setMultiplicity( Multiplicity multiplicity );



	/**
	 * Returns the value of the property <code>ordering</code>. 
	 * <p>
	 * Specifies whether the set of instances is ordered. The ordering must be determined and maintained by Operations that add values to the feature.
	 * This property is only relevant if the multiplicity is greater than one.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>			: OrderingKind
	 * <li><i>multiplicity</i>	: zero or one
	 * </ul>
	 * 
	 * @return The kind of ordering of this structuralFeature. May be <code>null</code> if none exists.
	 * 
	 */
	Ordering getOrdering();



	/**
	 * Returns the value of the property <code>ordering</code>. 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>			: OrderingKind
	 * <li><i>multiplicity</i>	: zero or one
	 * </ul>
	 * 
	 * @see #getOrdering() for description of the property <code>ordering</code>. 
	 *  
	 * @param ordering The new value of the property <code>ordering</code>. 
	 * 
	 * @return The old value of ordering of this structuralFeature. May be <code>null</code> if none exists.
	 * 
	 */
	Ordering setOrdering( Ordering ordering );



	/**
	 * Return the value of the property <code>targetScope</code>
	 * <p>
	 * Specifies whether the targets are ordinary Instances or are Classifiers.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: ScopeKind</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @return The targetScope of this structuralFeature, or <code>null</code> if none exists.
	 * 
	 */
	Scope getTargetScope();



	/**
	 * Return the value of the property <code>targetScope</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: ScopeKind</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @param targetScope The new value for the property <code>targetScope</code>.
	 * 
	 * @return The old value of targetScope of this structuralFeature, or <code>null</code> if none exists.
	 * 
	 * @see #getTargetScope() for description of the property <code>targetScope</code>.
	 * 
	 */
	Scope setTargetScope( Scope targetScope );
}