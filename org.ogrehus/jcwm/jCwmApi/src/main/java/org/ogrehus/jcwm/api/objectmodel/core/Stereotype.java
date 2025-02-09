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

import java.util.Collection;
import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.util.ConstraintViolation;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * The stereotype concept provides a way of branding (classifying) model elements so that they behave as if they were instances of new virtual
 * meta-model constructs. 
 * </p>
 * <p>
 * These model elements have the same structure (attributes, associations, operations) as similar non-stereotyped modelelements of the same kind.
 * The stereotype may specify additional constraints and required tagged values that apply to model elements. In addition, a stereotype may be used
 * to indicate a difference in meaning or usage between two model elements with identical structure.
 * </p>
 * <p>
 * In the meta-model the Stereotype meta-class is a subclass of ModelElement. Tagged Values and Constraints attached to a Stereotype apply to all
 * ModelElements branded by that Stereotype.
 * </p>
 * <p>
 * A stereotype keeps track of the base class to which it may be applied. The base class is a class in the meta-model (not a user-level modeling
 * element) such as Class, Association, etc. If a model element is branded by an attached stereotype, then the CWM base class of the model element
 * must be the base class specified by the stereotype or one of the subclasses of that base class.
 * </p>
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li>A Constraint cannot be applied to itself. [C-3-1]</li>
 * </ul>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Stereotype
extends
	GModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	>
{


	/**
	 * Returns the property <code>baseClass</code>.
	 * <p>
	 * The property <code>baseClass</code> specifies the name of a modeling element to which the stereotype applies, such as Class, Association,
	 * Constraint, etc. This is the name of a meta-class; that is, a class from the meta-model itself rather than a user model class.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: Name</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 *
	 * <p>
	 * <b>Constraints:</b>:
	 * <ul>
	 * <li><b> [C 3-6]</b> The baseClass name must be provided
	 * <pre>
	 * <b>context</b> Stereotype <b>inv</b>:
	 *   Set {self.baseClass} -> notEmpty
	 * </pre></li>
	 * </ul> 
	 * 
	 * @return The name of a modeling element to which the stereotype applies.
	 * 
	 */
	Name getBaseClass();



	/**
	 * Sets the value of the property <code>baseClass</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: Name</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li>A Constraint cannot be applied to itself. [C-3-1]</li>
	 * </ul>
	 * 
	 * @param baseClass The new value for the property <code>baseClass</code>.
	 * 
	 * @return The old value of the property <code>baseClass</code>.
	 * 
	 * @see #getBaseClass() for description of the property  <code>baseClass</code>.
	 * 
	 * @throws ConstraintViolation Is thrown if one of the parameter: <code>baseClass</code> is <code>null</code> or a zero-string.
	 * 
	 */
	Name setBaseClass( Name baseClass );



	/**
	 * Returns a Collection of referenced <code>extendedElement</code>s.
	 * <p>
	 * This reference (<code>extendedElement</code>) designates the modelElements affected by the stereotype. Each one must be a modelElement of the 
	 * kind specified by the baseClass attribute.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: StereotypedElement::extendedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: ModelElement::stereotype</li>
	 * </ul>
	 * 
	 * @return A Collection of referenced modelElements that extends this 
	 * stereotype. If none exists, an empty Collection will be returned.
	 * 
	 */
	Collection<GModelElement<?, ?, ?, ?>> getExtendedElements();



	/**
	 * Returns a Set of referenced <code>requiredTag</code>s.
	 * <p>
	 * Specifies a set of TaggedValues, each of which specifies a tag that an element classified by the Stereotype is required to have. The value part
	 * indicates the default value for the tagged value, that is, the tagged value that an element will be presumed to have if it is not overridden
	 * by an explicit tagged value on the element bearing the stereotype. If the value is unspecified, then the element must explicitly specify a
	 * tagged value with the given tag.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: TaggedValue</li>
	 * <li><i>defined by</i>	: StereotypeTaggedValues::requiredTag</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: TaggedValue::stereotype</li>
	 * </ul>
	 * 
	 * @return A Set of referenced <code>requiredTag</code>s that extends this stereotype. If none exists, an empty Set will be returned.
	 * 
	 */
	Set<TaggedValue> getRequiredTags();



	/**
	 * Returns all constraints that apply to all model elements branded by this stereotype.
	 * <p>
	 * These constraints are defined in the scope of the full meta-model.
	 * </p>
	 * <p>
	 * The StereotypeConstraints association links Stereotypes with Constraints that further restrict the states that a stereotyped ModelElement may 
	 * assume. A Constraint instance may not simultaneously participate in both the StereotypeContraints association and the ElementConstraint 
	 * association.
	 * </p> 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: Constraint</li>
	 * <li><i>defined by</i>	: StereotypeConstraints::stereotypeConstraint</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: Constraint::constrainedStereotype</li>
	 * </ul>
	 * 
	 * @return A Collection of the Constrains, applied to modelElements branded by this Stereotype. Cannot be <code>null</code>.
	 * 
	 */
	Collection<Constraint>	getStereotypeConstraints();
}