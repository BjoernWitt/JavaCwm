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

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;

/**
 * An Email instance identifies a single email address. Via a Contact instance, an email address can be associated with one or more ResponsibleParty 
 * instances. 
 * <p>
 * Email instances might, for example, be used by an automated tool to send an automatically generated email message to a ResponsibleParty instance 
 * responsible about some change of state for a particular ModelElement. Multiple Email instances may be associated with a single Contact instance and
 * the ordering of the association between them may be used to represent the sequence in which the Email instances should be contacted.
 * </p>
 * <p>
 * Because email addresses are first class objects within the CWM, they can be used for purposes beyond those associated with the CWM Foundation's 
 * Business Information concepts.
 * </p> 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Email
extends
	GModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	>
{


	/**
	 * A textual representation of an email address.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return A textual representation of an email address. Can't be <code>null</code>.
	 * 
	 */
	String getEMailAdress();



	/**
	 * A textual representation of an email address.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 *
	 * @param eMailAdress A textual representation of an email address. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>eMailAdress</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>eMailAdress</code> is <code>null</code>.
	 * 
	 */
	String setEMailAdress( String eMailAdress );



	/**
	 * Contains a textual representation of the type of the email address. 
	 * <p>
	 * Interesting values might include location information such as home or work, or perhaps an indication of the type of email system for which the
	 * eMailAddress is formatted, such as SMTP or X.400.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return A textual representation of the type of the email address. Can't not be <code>null</code>.
	 *
	 */
	String getEMailType();



	/**
	 * Contains a textual representation of the type of the email address. 
	 * <p>
	 * Interesting values might include location information such as home or work, or perhaps an indication of the type of email system for which the 
	 * eMailAddress is formatted, such as SMTP or X.400.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 *
	 * @param eMailAdress A textual representation of the type of the email address. Must not be <code>null</code>.
	 *  
	 * @return The old value of the property <code>eMailType</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>eMailType</code> is <code>null</code>.
	 * 
	 */
	String setEMailType( String eMailType );



	/**
	 * Identifies the Contact instance(s) for which this Email instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ContactEmail::contact</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : Contact::email</li>
	 * </ul>
	 * 
	 * @return A Collection of the Contact instance(s) for which this Email instance is relevant. Can't be <code>null</code>, but empty.
	 * 
	 */
	Collection<Contact> getContacts();



	/**
	 * Adds a new Contact instance(s) for which this Email instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ContactEmail::contact</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : Contact::email</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 */
	Boolean addContact( Contact contact );



	/**
	 * Removes a Contact instance(s) from this Email.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ContactEmail::contact</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : Contact::email</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 */
	Boolean removeContact( Contact contact );
}