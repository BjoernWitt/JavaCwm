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
package org.ogrehus.jcwm.impl.objectmodel.core;

import java.util.Collection;
import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Name;
import org.ogrehus.jcwm.api.objectmodel.core.Stereotype;
import org.ogrehus.jcwm.api.objectmodel.core.TaggedValue;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;


public class FunStereotype
extends
	GFunModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint	
	>
implements
	Stereotype
{


	/**
	 * Creates a new instance of FunModelElement by specific parameters.
	 *
	 * <b>Constraints:</b>
	 * <ul>
	 * <li>The base class name must be provided. (see CWM spec:[C-3-6])</li>
	 * </ul> 
	 * 
	 * @param name An identifier for the FunModelElement within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunModelElement within its owning Namespace. Must not
	 * be <code>null</code>.
	 * 
	 * @param baseClass The new value for the property baseClass. Must not be <code>null</code> or a zero-string. 
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is 
	 * <code>null</code>. 
	 *
	 * @throws ConstraintViolation Is thrown if one of the parameter: <code>baseClass</code> is <code>null</code> or a 
	 * zero-string. 
	 *
	 */	
	public FunStereotype( String name, Visibility visibility, Name baseClass ) {
		super( name, visibility ); // throws NullPointerException
		this.setBaseClass( baseClass ); // throws ConstraintViolation
	}



	public Name getBaseClass() {
		return this.name;
	}



	public Collection<GModelElement<?, ?, ?, ?>> getExtendedElements() {
		return this.extendedElements;
	}



	public Set<TaggedValue> getRequiredTags() {
		return this.requiredTags;
	}



	public Collection<Constraint> getStereotypeConstraints() {
		return this.stereotypeConstraints;
	}



	public Name setBaseClass( Name baseClass ) {
		if ( baseClass == null ) {
			throw new NullPointerException( "Parameter: baseClass must not be null." );
		}
		Name old = this.baseClass;
		this.baseClass = baseClass;
		return old;		
	}


	/**
	 * Attribute: baseClass
	 * 
	 * Specifies the name of a modeling element to which the stereotype applies, such as Class, Association, Constraint, etc. This is the name of a
	 * meta-class; that is, a class from the meta-model itself rather than a user model class.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: Name</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Name baseClass = null;



	/**
	 * Reference: extendedElement
	 * <p>
	 * Designates the model elements affected by the stereotype. Each one must be a model element of the kind specified by the baseClass attribute.
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
	 */
	protected Set<GModelElement<?, ?, ?, ?>> extendedElements = null;



	/**
	 * Reference: requiredTag 
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
	 */
	protected Set<TaggedValue> requiredTags = null;



	/**
	 * Designates constraints that apply to all model elements branded by this stereotype. These constraints are defined in the scope of the full 
	 * meta-model. 
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
	 */
	protected Set<Constraint> stereotypeConstraints	= null;
}