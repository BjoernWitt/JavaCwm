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

import java.util.List;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;


/**
 * Instances of the Telephone class represent telephone contact information.
 * <p>
 * Because telephones are first class objects within the CWM, they can be used for purposes beyond those associated with the CWM Foundation's Business
 * Information concepts.
 * 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Telephone
extends
	GModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	>
{


	/**
	 * A textual representation of the telephone's number.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @return A textual representation of the telephone number.
	 * 
	 */
	String getPhoneNumber();



	/**
	 * A textual representation of the telephone's number.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param phoneNumber A textual representation of the telephone number. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>phoneNumber</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>phoneNumber</code> is <code>null</code>.
	 * 
	 */
	String setPhoneNumber( String phoneNumber );



	/**
	 * A textual representation of the telephone's type, such as multi-line, or its usage, such as home, work, fax or mobile.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @return A textual representation of the telephone type. Can't be <code>null</code>.
	 * 
	 */
	String getPhoneType();



	/**
	 * A textual representation of the telephone type, such as multi-line, or its usage, such as home, work, fax or mobile.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param phoneType A textual representation of the telephone type. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>phoneType</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>phoneType</code> is <code>null</code>.
	 * 
	 */
	String setPhoneType( String phoneType );



	/**
	 * Identifies the Contact instance(s) for which the Telephone instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ContactTelephone::contact</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : Contact::telephone</li>
	 * </ul>
	 * 
	 * @return A Collection of the Contact instance(s) for which the Telephone instance is relevant. Can't be <code>null</code>, but may be an empty 
	 * Collection.
	 * 
	 */
	List<Contact> getContacts();



	/**
	 * Adds a new Contact instance(s) for which this Telephone instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ContactTelephone::contact</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : Contact::telephone</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 */
	Boolean addContact( Contact contact );



	/**
	 * Removes a Contact instance(s) from this Telephone.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ContactTelephone::contact</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : Contact::telephone</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 */
	Boolean removeContact( Contact contact );
}