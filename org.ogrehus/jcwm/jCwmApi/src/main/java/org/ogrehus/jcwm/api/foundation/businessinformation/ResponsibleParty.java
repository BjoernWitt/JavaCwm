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
package org.ogrehus.jcwm.api.foundation.businessinformation;

import java.util.Collection;
import java.util.List;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;


/**
 * The ResponsibleParty class allows representation of entities within an information system that are in some way interested in receiving information
 * about, or are otherwise responsible for, particular ModelElements. Each ResponsibleParty may own multiple sets of contact information, and a single
 * ResponsibleParty may be associated with many ModelElements.
 * <p>
 * ResponsibleParty instances may represent any entity appropriate to the system being modeled and need not be limited to people. For example, a 
 * ResponsibleParty instance might represent an individual such as George Washington, a role (the President), or an organization (Congress), 
 * depending upon the needs of the system being modeled. Similarly, the precise semantics of the <i>responsibility</i> attribute are open to 
 * interpretation and may be adapted on a system-by-system basis.
 * </p>
 * <p>
 * Because ResponsibleParty instances are Namespaces, they can be organized into hierarchies of ResponsibleParty instances, capturing organizational 
 * structures or similar relationships.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-3-3]</b> A ResponsibleParty may not describe itself.</li>
 * </ul> 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface ResponsibleParty
extends
	GNamespace<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, ResponsibleParty
	>
{


	/**
	 * Textual identification or description of the ResponsibleParty in a usage-dependent format.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return The textual identification or description of the ResponsibleParty in a usage-dependent format. Can't be <code>null</code>.
	 * 
	 */
	String getResponsibility();



	/**
	 * Textual identification or description of the ResponsibleParty in a usage-dependent format.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[Foundation-C-3-3]</b> A ResponsibleParty may not describe itself.</li>
	 * </ul> 
	 * 
	 * @param responsibility The textual identification or description of the ResponsibleParty in a usage-dependent format. Must not 
	 * be <code>null</code>.
	 * 
	 * @return The old value of the property <code>responsibility</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>responsibility</code> is <code>null</code>.
	 * 
	 */
	String setResponsibility( String responsibility );



	/**
	 * Identifies the Contact instance(s) associated with a ResponsibleParty instance. 
	 * <p>
	 * The ordered constraint on this reference allows retention of the sequence in which multiple Contact should be employed.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ResponsiblePartyContact::contact</li>
	 * <li><i>multiplicity</i>  : zero or more; ordered</li>
	 * <li><i>inverse</i>       : Contact::responsibleParty</li>
	 * </ul>
	 * 
	 * @return A List of the Contact instance(s) associated with a ResponsibleParty instance. CAn't be <code>null</code>, but an empty Collection.
	 * 
	 */
	List<Contact> getContacts();



	/**
	 * Identifies the model elements for which this ResponsibleParty instance has some interest or responsibility.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ModelElement</li>
	 * <li><i>defined by</i>    : ModelElementResponsibility::modelElement</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 * @return A Collection of the model elements for which this ResponsibleParty instance has some interest or responsibility. Can't 
	 * be <code>null</code>, but an empty Collection.
	 * 
	 */
	Collection<GModelElement<?, ?, ?, ?>> getModelElements();



	/**
	 * Adds a ModelElement to the reference of <code>modelElement</code>.
	 * <p>
	 * The reference of ModelElements is a set elements for which this Document instance is relevant.
	 * </p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ModelElement</li>
	 * <li><i>defined by</i>    : ModelElementResponsibility::modelElement</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-3-3]</b> A ResponsibleParty may not describe itself.</li>
	 * </ul> 
	 * 
	 * @param element A ModelElement for which this Document instance is relevant.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 * @throws ConstraintViolation If the Constraint [Foundation-C-3-3] is violated.
	 * 
	 */
	<E extends GModelElement<?, ?, ?, ?>> Boolean addModelElement( E element );



	/**
	 * Adds a ModelElement to the reference of <code>modelElement</code>.
	 * <p>
	 * The reference of ModelElements is a set elements for which this Document instance is relevant.
	 * </p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ModelElement</li>
	 * <li><i>defined by</i>    : ModelElementResponsibility::modelElement</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 * @param element A ModelElement for which this Document instance is relevant.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 */
	<E extends GModelElement<?, ?, ?, ?>> Boolean removeModelElement( E element );
}