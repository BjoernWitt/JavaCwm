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

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

/**
 * BusinessInformationPackage provides hierarchical access to any factory method in this jCwmAPI in scope of the package 
 * <code>businessinformation</code>.
 * <p>
 * To work with the jCwm you need to choose an implementation of the API. For example its recommends to use the reference implementation provided by 
 * <a href="http://www.ogrehus.org">www.ogrehus.org</a> too. Otherwise you can implement your own if you need specific functionality like more 
 * immutable objects for more thread safety or final classes that can't be extended for security reasons.
 * <p>
 * <b>Usage of FoundationPackage:</b>
 * </p>
 * <pre>
 * ...
 * BusinessInformationPackage factory = BusinessInformationPackage.create( "org.ogrehus.jcwm.impl.foundation.businessinformation.FunBusinessInformationPackage" );
 * Contact                    contact  = factory.createContact(...);
 * Document                   document = factory.createDocument(...);
 * ... 
 * </pre>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public abstract class BusinessInformationPackage {



	/**
	 * Creates a new instance of the BusinessInformationPackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * </p>
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class 
	 * <code>org.ogrehus.jcwm.api.foundation.businessinformation.BusinessInformationPackage</code>.
	 * 
	 * @return A new instance of BusinessInformationPackage, determined by the specific classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends 
	 * <code>org.ogrehus.jcwm.api.foundation.businessinformation.BusinessInformationPackage</code> or if the class does not provide a simple public 
	 * constructor without any parameters. 
	 *   
	 */
	public static final BusinessInformationPackage create( String classPath ) 
	throws 
		  ClassNotFoundException
	{
		try {
			Object foundation = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( foundation instanceof BusinessInformationPackage ) {
				return (BusinessInformationPackage)foundation;
			}
			throw new ClassNotFoundException( "Invalid Class, it must extend org.ogrehus.jcwm.api.foundation." +
											  "businessinformation.BusinessInformationPackage:" + classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid BusinessInformationPackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid BusinessInformationPackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid BusinessInformationPackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}



	@Override
	public boolean equals( Object obj ) {
		return ( obj instanceof BusinessInformationPackage );
	}
	



	/**
	 * Factory method that creates a new instance of Contact by specific parameters.
	 * 
	 * <p>
	 * Each Contact instance collects together the various types of related contact information.
	 * </p>
	 * 
	 * 
	 * @param name An identifier for the Contact within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Contact within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Contact, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>. 
	 * 
	 */
	abstract public Contact createContact( String name, Visibility visibility );



	/**
	 * Factory method that creates a new instance of Description by specific parameters.
	 * 
	 * <p>
	 * Instances of the Description class contain arbitrary textual information relevant to a particular ModelElement.
	 * </p> 
	 * 
	 * @param name An identifier for the Description within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Description within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param body Contains a textual description of information pertaining to the owning ModelElement. Must not be <code>null</code>.
	 * 
	 * @param language Contains an identification of the language in which the content of the body attribute is specified. Must not 
	 * be <code>null</code>.
	 * 
	 * @param type Contains a textual description of the type of information the Description represents.Specific contents are usage defined. Must not 
	 * be <code>null</code>.
	 * 
	 * @return A new instance of Description, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, 
	 * <code>body</code>, <code>language</code> or <code>type</code> is <code>null</code>. 
	 * 
	 */
	abstract public Description createDescription(
		  String name
		, Visibility visibility
		, String body
		, String language
		, String type
	);



	/**
	 * Factory method that creates a new instance of Document by specific parameters.
	 * 
	 * <p>
	 * The Document class represents externally stored descriptive information about some aspect of the modeled system.
	 * </p>
	 * 
	 * @param name An identifier for the Document within its containing  Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Document within its owning Namespace. Must not be 
	 * <code>null</code>.
	 * 
	 * @param reference Contains a textual representation of the identification, and perhaps the physical location, of externally maintained 
	 * documentary information about some aspect of the ModelElement(s) with which the Document instance is associated. Must not be <code>null</code>.
	 * 
	 * @param type Contains a textual description of the type of information the Document represents. Specific contents are usage defined. Must not 
	 * be <code>null</code>.
	 * 
	 * @return A new instance of Document, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, 
	 * <code>reference</code> or <code>type</code> is <code>null</code>. 
	 * 
	 */
	abstract public Document createDocument(
		  String name
		, Visibility visibility
		, String reference
		, String type
	);	



	/**
	 * Factory method that creates a new instance of Email by specific parameters.
	 * 
	 * <p>
	 * An Email instance identifies a single email address. Via a Contact instance, an email address can be associated with one or more 
	 * ResponsibleParty instances.
	 * </p>
	 * 
	 * @param name An identifier for the Email within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Email within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param eMailAdress A textual representation of an email address. Must not be <code>null</code>.
	 *
	 * @param eMailType Contains a textual representation of the type of the email address. Interesting values might include location information 
	 * such as home or work, or perhaps an indication of the type of email system for which the eMailAddress is formatted, such as SMTP or X.400. 
	 * Must not be <code>null</code>.
	 * 
	 * @return A new instance of Email, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>eMailAdress</code> 
	 * or <code>eMailType</code> is <code>null</code>. 
	 * 
	 */
	abstract public Email createEmail(
		  String name
		, Visibility visibility
		, String eMailAdress
		, String eMailType
	);



	/**
	 * Factory method that creates a new instance of Location by specific parameters.
	 * 
	 * <p>
	 * Instances of the Location class represent physical locations. Note that the name of a Location is derived from its superclass, ModelElement.
	 * </p>
	 * 
	 * @param name An identifier for the Location within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Location within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param locationType Descriptive information about the character or identity of the Location instance. Must not be <code>null</code>.
	 * 
	 * @param address The address of the Location instance. The precise content of this string is usage-defined. Must not be <code>null</code>.
	 * 
	 * @param city The name of the city in which the Location instance is found. The precise content of this string is usage-defined. Must not 
	 * be <code>null</code>.
	 * 
	 * @param postCode The postal code of the Location instance. The precise content of this string is usage-defined. Must not be <code>null</code>.
	 *
	 * @param area The area in which the Location instance is found. The precise content of this string is usage-defined, but a common usage would be 
	 * to refer to a geographical subdivision such as a state or province. Must not be <code>null</code>.
	 * 
	 * @param country The name of the country in which the Location instance is found. The precise content of this string is usage-defined. Must not 
	 * be <code>null</code>.
	 *  
	 * @return A new instance of Location, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>locationType</code>, 
	 * <code>address</code>, <code>city</code>, <code>postCode</code>, <code>area</code> or <code>country</code> is <code>null</code>. 
	 * 
	 */
	abstract public Location createLocation(
		  String name
		, Visibility visibility
		, String locationType
		, String address
		, String city
		, String postCode
		, String area
		, String country 
	);



	/**
	 * Factory method that creates a new instance of ResourceLocator by specific parameters.
	 * 
	 * <p>
	 * Instances of the ResourceLocator class provide a general means for describing the resources whose location is not defined by a traditional 
	 * mailing address.
	 * </p>
	 * 
	 * @param name An identifier for the ResourceLocator within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the ResourceLocator within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param url Contains the text of the resource location. For Internet locations, this will be a web URL (Uniform Resource Locator) but there is 
	 * no requirement that this be so. Must not be <code>null</code>.
	 * 
	 * @return A new instance of ResourceLocator, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>url</code> 
	 * is <code>null</code>. 
	 * 
	 */
	abstract public ResourceLocator createResourceLocator( String name, Visibility visibility, String url );	



	/**
	 * Factory method that creates a new instance of ResponsibleParty by specific parameters.
	 * 
	 * <p>
	 * The ResponsibleParty class allows representation of entities within an information system that are in some way interested in receiving 
	 * information about, or are otherwise responsible for, particular ModelElements. Each ResponsibleParty may own multiple sets of contact 
	 * information, and a single ResponsibleParty may be associated with many ModelElements.
	 * </p>
	 * 
	 * @param name An identifier for the ResponsibleParty within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the ResponsibleParty within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param responsibility Textual identification or description of the ResponsibleParty in a usage-dependent format. Must not be <code>null</code>.
	 * 
	 * @return A new instance of ResponsibleParty, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>responsibility</code> 
	 * is <code>null</code>. 
	 * 
	 */
	abstract public ResponsibleParty createResponsibleParty( String name, Visibility visibility, String responsibility );



	/**
	 * Factory method that creates a new instance of Telephone by specific parameters.
	 * 
	 * <p>
	 * Instances of the Telephone class represent telephone contact information.
	 * </p>
	 * 
	 * @param name An identifier for the Telephone within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Telephone within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param phoneNumber A textual representation of the telephone's number. Must not be <code>null</code>.
	 * 
	 * @param phoneType A textual representation of the telephone's type, such as multi-line, or its usage, such as a fax or mobile. Must not 
	 * be <code>null</code>.
	 * 
	 * @return A new instance of Telephone, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>phoneNumber</code> 
	 * or <code>phoneType</code> is <code>null</code>. 
	 * 
	 */
	abstract public Telephone createTelephone(
		  String name
		, Visibility visibility
		, String phoneNumber
		, String phoneType

	);
}