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
 * Instances of the ResourceLocator class provide a general means for describing the resources whose location is not defined by a traditional mailing 
 * address.
 * <p>
 * For example, a ResourceLocator instance could refer to anything from a location within a building (Room 317, third file cabinet, 2nd drawer) to a 
 * web location (www.omg.org). Because they are first class objects in the CWM, ResourceLocator instances may also be used for purposes beyond those 
 * associated with the CWM Foundation's Business Information concepts.
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
public interface ResourceLocator
extends
	GModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	>
{


	/**
	 * Contains the text of the resource location. For Internet locations, this will be a web URL (Uniform Resource Locator) but there is no 
	 * requirement that this be so.
	 * <p>
	 * In fact, the string can contain any text meaningful to its intended use in a particular environment.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return The text of the resource location. In fact, the string can contain any text meaningful to its intended use in a particular environment.
	 * 
	 */
	String getUrl();



	/**
	 * Contains the text of the resource location. For Internet locations, this will be a web URL (Uniform Resource Locator) but there is no 
	 * requirement that this be so.
	 * <p>
	 * In fact, the string can contain any text meaningful to its intended use in a particular environment.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param url The text of the resource location. In fact, the string can contain any text meaningful to its intended use in a particular 
	 * environment. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>url</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>url</code> is <code>null</code>.
	 * 
	 */
	String setUrl( String url );



	/**
	 * Identifies the Contact instance(s) for which the ResourceLocator instance is relevant.
	 * 
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ContactResourceLocator::contact</li> 
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : Contact::url</li>  
	 * </ul>
	 * 
	 * @return A Collection of the Contact instance(s) for which the ResourceLocator instance is relevant. Can't be <code>null</code>, but an empty 
	 * Collection. 
	 * 
	 */
	Collection<Contact> getContacts();



	/**
	 * Adds a new Contact instance for which the ResourceLocator instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ContactResourceLocator::contact</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : Contact::email</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.  
	 * 
	 */
	Boolean addContact( Contact contact );



	/**
	 * Removes a Contact instance from this ResourceLocator.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ContactResourceLocator::contact</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : Contact::email</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 */
	Boolean removeContact( Contact contact );
}