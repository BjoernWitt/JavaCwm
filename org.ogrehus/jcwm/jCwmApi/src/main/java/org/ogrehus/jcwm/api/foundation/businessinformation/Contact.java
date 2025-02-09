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
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;


/**
 * Each Contact instance collects together the various types of related contact information. 
 * <p>
 * Each Contact instance can be associated with multiple Email, Location, and Telephone instances. Conversely, each Email, Location, ResourceLocator,
 * and Telephone instance can be associated with many Contact instances. The ordering constraints on the associations between these classes and the 
 * Contact class can be used to represent a prioritized sequence in which the various types of contact information should be used.
 * </p>
 * <p>
 * A particular ResponsibleParty instance may have multiple instances of Contact associated with it via the ResponsiblePartyContact association. This 
 * association is ordered to support representation of the sequence in which Contact instances should be used. For example, a ResponsibleParty 
 * instance representing an employee might be associated with Contact instances representing their office, home, and mobile contact information with 
 * an indication that the employee should be contacted first at the office, then at home, and finally via their mobile phone.
 * </p>
 * <p>
 * To maximize flexibility of the meta-model, Contact instances may provide contact information for multiple ResponsibleParty instances.
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
public interface Contact
extends
	GModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	>
{


	/**
	 * Identifies the Email instances associated with this Contact instance. 
	 * <p>
	 * The ordered constraint may be used to identify the order in which Email instances should be contacted.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Email</li>
	 * <li><i>defined by</i>    : ContactEmail::email</li>
	 * <li><i>multiplicity</i>  : zero or more; ordered</li> 
	 * <li><i>inverse</i>       : Email::contact</li>
	 * </ul>
	 * 
	 * @return A List of eMail instances. Can't be <code>null</code>, but an empty List.
	 * 
	 */
	List<Email> getEMail();



	/**
	 * Identifies the Location instances associated with this Contact instance.
	 * <p>
	 * The ordered constraint may be used to identify the order in which Location instances should be contacted.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Location</li>
	 * <li><i>defined by</i>    : ContactLocation::location</li>
	 * <li><i>multiplicity</i>  : zero or more; ordered</li> 
	 * <li><i>inverse</i>       : Location::contact</li>
	 * </ul>
	 * 
	 * @return A List of Location instances. Can't be <code>null</code>, but an empty List.
	 * 
	 */
	List<Location> getLocation();



	/**
	 * Identifies the ResponsibleParty instances associated with this Contact instance.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ResponsibleParty</li>
	 * <li><i>defined by</i>    : ResponsiblePartyContact::responsibleParty</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * <li><i>inverse</i>       : ResponsibleParty::contact</li>
	 * </ul>
	 * 
	 * @return A Collection of ResponsibleParty instances. Can't be <code>null</code>, but an empty List.
	 * 
	 */
	Collection<ResponsibleParty> getResponsibleParty();



	/**
	 * Identifies the Telephone instance associated with this Contact instance.
	 * <p>
	 * The ordered constraint may be used to identify the order in which Telephone instances should be contacted.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Telephone</li>
	 * <li><i>defined by</i>    : ContactTelephone::telephone</li>
	 * <li><i>multiplicity</i>  : zero or more; ordered</li> 
	 * <li><i>inverse</i>       : Telephone::contact</li>
	 * </ul>
	 * 
	 * @return A List of Telephone instances associated with this Contact instance. Can't be <code>null</code>, but an empty List.
	 * 
	 */
	List<Telephone> getTelephones();



	/**
	 * Identifies the ResourceLocator instances associated with this Contact instance.  
	 * <p>
	 * The ordered constraint on the ResourceLocator association may be used to identify the order in which ResourceLocator instances should be 
	 * contacted.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ResourceLocator</li>
	 * <li><i>defined by</i>    : ContactResourceLocator::url</li>
	 * <li><i>multiplicity</i>  : zero or more; ordered</li> 
	 * <li><i>inverse</i>       : ResourceLocator::contact</li>
	 * </ul>
	 * 
	 * @return A List of ResourceLocator instances associated with this Contact instance. Can't be <code>null</code>, but an empty List.
	 * 
	 */
	List<ResourceLocator> getUrls();
}