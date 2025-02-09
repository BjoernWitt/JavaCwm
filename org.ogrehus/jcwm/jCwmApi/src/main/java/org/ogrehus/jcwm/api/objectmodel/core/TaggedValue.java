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

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;


/**
 * A tagged value allows information to be attached to any model element in the form of a tagged value pair; that is, name = value.
 * <p>
 * The interpretation of tagged value semantics is intentionally beyond the scope of CWM. It must be determined by user or tool conventions. It is 
 * expected that tools will define tags to supply information needed for their operations beyond the basic semantics of CWM. Such information could 
 * include code generation options, model management information, or user-specified semantics.
 * </p>
 * <p>
 * Even though TaggedValues are a simple and straightforward extension technique, their use restricts semantic interchange of meta-data to only those 
 * tools that share a common understanding of the specific tagged value names.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by 
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, August 2008
 * 
 */
public interface TaggedValue 
extends 
	Element 
{


	/**
	 * Contains the name of the TaggedValue. 
	 * <p>
	 * This name determines the semantics that are applicable to the contents 
	 * of the value attribute.
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: Name</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @return The name of the TaggedValue.
	 * 
	 */
	Name getTag();



	/**
	 * Sets the value of tag attribute. 
	 * 
	 * <ul>
	 * <li><i>type</i>			: Name</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @param tag New name  of the tagged value.
	 * 
	 * @return The old name of the TaggedValue.
	 * 
	 */
	Name setTag( String tag );



	/**
	 * Sets the value of tag attribute. 
	 * 
	 * <ul>
	 * <li><i>type</i>			: Name</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @param tag New name  of the tagged value.
	 * 
	 * @return The old name of the TaggedValue.
	 * 
	 */
	Name setTag( Name tag );



	/**
	 * Contains the current value of the TaggedValue.
	 * 
	 * <ul>
	 * <li><i>type</i>			: String</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 */
	String getValue();



	/**
	 * Sets the value of the attribute <code>value</code>. 
	 * 
	 * <ul>
	 * <li><i>type</i>			: String</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @see #getValue()
	 *  
	 * @param value The new value of this tagged element.
	 * 
	 * @return The old value, assigned before.
	 * 
	 */
	String setValue( String value );



	/**
	 * References the ModelElement to which the TaggedValue pertains.
	 *
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: TaggedElement::modelElement</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse<i>		: ModelElement::taggedValue</li>
	 * </ul>
	 *
	 * @return The referenced ModelElement, if one exists otherwise <code>null</code>.
	 *   
	 */
	GModelElement<?, ?, ?, ?> getModelElement();



	/**
	 * Sets the value of reference modelElement.
	 *
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: TaggedElement::modelElement</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse<i>		: ModelElement::taggedValue</li>
	 * </ul>
	 *
	 * @param The new reference for the modelElement.
	 *
	 * @return The old referenced ModelElement, if one exists otherwise <code>null</code>.
	 *   
	 */
	GModelElement<?, ?, ?, ?> setModelElement( GModelElement<?, ?, ?, ?> modelElement );



	/**
	 * Returns the reference of the Stereotype that uses the TaggedValue.
	 * 
	 * <ul>
	 * <li><i>class</i>			: Stereotype</li>
	 * <li><i>defined by</i>	: StereotypeTaggedValues</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Stereotype::requiredTag</li>
	 * </ul>
	 * 
	 * @return The referenced Stereotype, if one exists otherwise <code>null</code>.
	 */
	Stereotype getStereotype();



	/**
	 * Returns the reference of the Stereotype that uses the TaggedValue.
	 * 
	 * <ul>
	 * <li><i>class</i>			: Stereotype</li>
	 * <li><i>defined by</i>	: StereotypeTaggedValues</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Stereotype::requiredTag</li>
	 * </ul>
	 * 
	 * @return The referenced Stereotype, if one exists otherwise <code>null</code>.
	 */
	Stereotype setStereotype( Stereotype stereotype );
}