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
 * Instances of the Location class represent physical locations.
 * <p>
 * Note that the name of a Location is derived from its superclass, ModelElement.
 * </p>
 * <p>
 * Because Locations are first class objects within the CWM, they can be used for purposes beyond those associated with the CWM Foundation's Business
 * Information concepts. If additional attributes about Location instances are required, they should be added by creating sub-types of the Location
 * class and placing the additional attributes therein.
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Location
extends
	GModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	>
{


	/**
	 * Descriptive information about the character or identity of the Location instance.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return The descriptive information about the character or identity of the Location instance. Can't be <code>null</code>. 
	 * 
	 */
	String getLocationType();



	/**
	 * Descriptive information about the character or identity of the Location instance.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param locationType The descriptive information about the character or identity of the Location instance. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>locationType</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>locationType</code> is <code>null</code>. 
	 * 
	 */
	String setLocationType( String locationType );



	/**
	 * The address of the Location instance. The precise content of this string is usage-defined.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return The usage-defined address of the Location instance. Can't be <code>null</code>.
	 * 
	 */
	String getAddress();



	/**
	 * The address of the Location instance. The precise content of this string is usage-defined.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param address The usage-defined address of the Location instance. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>address</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>address</code> is <code>null</code>. 
	 * 
	 */
	String setAddress( String address );



	/**
	 * The name of the city in which the Location instance is found. The precise content of this string is usage-defined.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return The usage-defined name of the city in which the Location instance is found. Can't be <code>null</code>.
	 * 
	 */
	String getCity();



	/**
	 * The name of the city in which the Location instance is found. The precise content of this string is usage-defined.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param city The usage-defined name of the city in which the Location instance is found. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>city</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>city</code> is <code>null</code>.
	 * 
	 */
	String setCity( String city );



	/**
	 * The postal code of the Location instance. The precise content of this string is usage-defined.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @return The usage-defined postal code of the Location instance. Can't be <code>null</code>.
	 * 
	 */
	String getPostCode();



	/**
	 * The postal code of the Location instance. The precise content of this string is usage-defined.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param postCode The usage-defined postal code of the Location instance. Can't be <code>null</code>.
	 * 
	 * @return The old value of the property <code>postCode</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>postCode</code> is <code>null</code>.
	 * 
	 */
	String setPostCode( String postCode );



	/**
	 * The area in which the Location instance is found. 
	 * <p>
	 * The precise content of this string is usage-defined, but a common usage would be to refer to a geographical subdivision such as a state or 
	 * province.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @return The usage-defined area in which the Location instance is found, like a geographical subdivision such as a state or province. Can't 
	 * be <code>null</code>.
	 * 
	 */
	String getArea();



	/**
	 * The area in which the Location instance is found. 
	 * <p>
	 * The precise content of this string is usage-defined, but a common usage would be to refer to a geographical subdivision such as a state or 
	 * province.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param area The usage-defined area in which the Location instance is found, like a geographical subdivision such as a state or province. Must 
	 * not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>area</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>area</code> is <code>null</code>.
	 * 
	 */
	String setArea( String area );



	/**
	 * The name of the country in which the Location instance is found. 
	 * <p>
	 * The precise content of this string is usage-defined.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @return The usage-defined name of the country in which the Location instance is found. Can't be <code>null</code>. 
	 * 
	 */
	String getCountry();



	/**
	 * The name of the country in which the Location instance is found. The precise content of this string is usage-defined.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param country The usage-defined name of the country in which the Location instance is found. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>country</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException If the value for the parameter <code>country</code> is <code>null</code>.
	 * 
	 */
	String setCountry( String country );



	/**
	 * Identifies the Contact instance(s) with which this Location instance is associated.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Contact</li>
	 * <li><i>defined by</i>    : ContactLocation::contact</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : Contact::location</li>
	 * </ul>
	 * 
	 * @return A Collection if Contact instance(s) with which this Location instance is associated. Can't be <code>null</code>, but an empty 
	 * Collection.
	 * 
	 */
	Collection<Contact> getContact();
}