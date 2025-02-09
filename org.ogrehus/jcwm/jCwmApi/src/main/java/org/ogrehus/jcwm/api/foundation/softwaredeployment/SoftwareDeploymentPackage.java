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
package org.ogrehus.jcwm.api.foundation.softwaredeployment;

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.resource.relational.Catalog;

/**
 * SoftwareDeploymentPackage provides hierarchical access to any factory method in this jCwmAPI in scope of the package 
 * <code>softwaredeployment</code>.
 * <p>
 * To work with the jCwm you need to choose an implementation of the API. For example its recommends to use the reference implementation provided by 
 * <a href="http://www.ogrehus.org">www.ogrehus.org</a> too. Otherwise you can implement your own if you need specific functionality like more 
 * immutable objects for more thread safety or final classes that can't be extended for security reasons. 
 * <p>
 * <b>Usage of SoftwareDeploymentPackage:</b>
 * </p>
 * <pre>
 * ...
 * SoftwareDeploymentPackage deploy   = SoftwareDeploymentPackage.create( "org.ogrehus.jcwm.impl.objectmodel.foundation.softwaredeployment.FunSoftwareDeploymentPackage" );
 * SoftwareSystem            software = softwaredeployment.createSoftwareSystem(...);
 * Machine                   machine  = softwaredeployment.createMaschine(...);
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
public abstract class SoftwareDeploymentPackage {


	/**
	 * Creates a new instance of the SoftwareDeploymentPackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * </p>
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class 
	 * <code>org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareDeploymentPackage</code>.
	 * 
	 * @return A new instance of FoundationPackage, determined by the specific  classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends
	 * <code>org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareDeploymentPackage</code> or if the class does not provide a simple public 
	 * constructor without any parameters. 
	 * 
	 */
	public static final SoftwareDeploymentPackage create( String classPath ) 
	throws 
		  ClassNotFoundException
	{
		try {
			Object softwareDeployment = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( softwareDeployment instanceof SoftwareDeploymentPackage ) {
				return (SoftwareDeploymentPackage)softwareDeployment;
			}
			throw new ClassNotFoundException( "Invalid Class, it must extend org.ogrehus.jcwm.api.foundation.softwaredeployment."
											+ "SoftwareDeploymentPackage:" + classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid SoftwareDeploymentPackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid SoftwareDeploymentPackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid SoftwareDeploymentPackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}



	/**
	 * Creates a new instance of Component by specific parameters.
	 * 
	 * @param name An identifier for the Component within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>notInstantiable</b> - <code>true</code>.
	 * </ul>
	 * 
	 * @return A new instance of Component by specific parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> is <code>null</code>. 
	 *
	 */	
	abstract public Component createComponent( String name );



	/**
	 * Factory method that creates a new instance of DeployedComponent by specific parameters.
	 * 
	 * <p>
	 * A DeployedComponent represents the deployment of a Component on a specific Machine.
	 * </p> 
	 * 
	 * @param name An identifier for the DeployedComponent within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param pathname A pathname for the DeployedComponent within the Machine file system.
	 *
	 * @param component identifies the Component that is deployed by this DeployedComponent. Must not be <code>null</code>.
	 * 
	 * @param machine identifies the Machine on which the DeployedComponent is deployed. Must not be <code>null</code>.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @return A new instance of DeployedComponent, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>pathname</code>, 
	 * <code>component</code> or <code>machine</code> is <code>null</code>. 
	 * 
	 */
	abstract public DeployedComponent createDeployedComponent(
		  String name
		, String pathname
		, Component component
		, Machine machine
	);



	/**
	 * Factory method that creates a new instance of DataManager by specific parameters.
	 * 
	 * <p>
	 * A DataManager represents a DeployedComponent that manages access to data. 
	 * </p>
	 * 
	 * @param name An identifier for the DataManager within its containing Namespace. Must not be <code>null</code>.
	 *
	 * @param pathname A pathname for the DataManager within the Machine's file system.
	 *
	 * @param component identifies the Component that is deployed by this DataManager. Must not be <code>null</code>.
	 * 
	 * @param machine identifies the Machine on which the DataManager is deployed. Must not be <code>null</code>.
	 * 
	 * @param isCaseSensitive Indicates whether or not the DataManager treats lower case letters within object names as being different from the 
	 * corresponding upper case letters. Must not be <code>null</code>.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @return A new instance of JDBCManager, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>pathname</code>, <code>component</code>
	 * , <code>machine</code> or <code>isCaseSensitive</code> is <code>null</code>. 
	 * 
	 */
	abstract public JDBCManager createJDBCManager(
		  String name
		, String pathname
		, Component component
		, Machine machine
		, Boolean isCaseSensitive
	);



	/**
	 * Factory method that creates a new instance of DataProvider by specific parameters.
	 * 
	 * <p>
	 * A DataProvider is a deployed software Component that acts as a client to provide access to data that is managed by another product.
	 * </p>
	 * 
	 * @param name An identifier for the DataProvider within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param pathname A pathname for the DataProvider within the Machine file system.
	 *
	 * @param component identifies the Component that is deployed by this DataProvider. Must not be <code>null</code>.
	 * 
	 * @param machine identifies the Machine on which the DataProvider is deployed. Must not be <code>null</code>.
	 * 
	 * @param isCaseSensitive Indicates whether or not the DataProvider treats lower case letters within object names as being different from the 
	 * corresponding upper case letters. Must not be <code>null</code>.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 *  
	 * @return A new instance of DataProvider, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>pathname</code>, 
	 * <code>component</code>, <code>machine</code> or <code>isCaseSensitive</code> is <code>null</code>. 
	 * 
	 */
	abstract public JDBCProvider createJDBCProvider(
		  String name
		, String pathname
		, Component component
		, Machine machine
		, Boolean isCaseSensitive
	);



	/**
	 * Factory method that creates a new instance of DeployedSoftwareSystem by specific parameters.
	 * 
	 * <p>
	 * A DeployedSoftwareSystem represents a deployment of a SoftwareSystem.
	 * </p>
	 * 
	 * @param name An identifier for the DeployedSoftwareSystem within its containing Namespace. Must not be 
	 * <code>null</code>.
	 * 
	 * @param fixLevel Describes the fix level of the DeployedSoftwareSystem instance. Must not be <code>null</code>.
	 * 
	 * @param softwareSystem Identifies the SoftwareSystem deployed. Must not be <code>null</code>. 
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @return A new instance of DeployedSoftwareSystem, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>fixLevel</code> or <code>softwareSystem</code> 
	 * is <code>null</code>. 
	 * 
	 */
	abstract public DeployedSoftwareSystem createDeployedSoftwareSystem(
		  String name
		, String fixLevel
		, SoftwareSystem softwareSystem
	);



	/**
	 * Factory method that creates a new instance of Machine by specific parameters.
	 * <p>
	 * A Machine represents a computer. The Site at which the Machine is located and the Components deployed on the Machine may be recorded.
	 * </p>
	 * 
	 * @param name An identifier for the Machine within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @return A new instance of Machine by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */	
	abstract public Machine createMachine( String name );



	/**
	 * Factory method that creates a new instance of PackageUsage by specific parameters.
	 * 
	 * @param name An identifier for the PackageUsage within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param client The element that is affected by the supplier element. In some cases the direction is unimportant and serves only to distinguish 
	 * the two elements. Must not be <code>null</code>.
	 * 
	 * @param supplier Inverse of client. Designates the element that is unaffected by a change. In a two-way relationship this would be the more 
	 * general element. In an undirected situation the choice of client and supplier may be irrelevant. Must not be <code>null</code>.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @return A new instance of PackageUsage, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters <code>name</code>, <code>client</code> or <code>supplier</code> 
	 * was <code>null</code>. 
	 * 
	 */
	abstract public JDBCPackageUsage createJDBCPackageUsage( String name, JDBCConnection client, Catalog supplier );



	/**
	 * Factory method that creates a new instance of ProviderConnection by specific parameters.
	 * 
	 * @param name An identifier for the ProviderConnection within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param isReadOnly Indicates whether the ProviderConnection only allows read access to the DataManager. Must not be <code>null</code>.
	 * 
	 * @param dataProvider Identifies the DataProvider that is the client of the ProviderConnection. Must not be <code>null</code>.
	 * 
	 * @param dataManager Identifies the DataManager that is accessed by the ProviderConnection.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @return A new instance of ProviderConnection, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>isReadOnly</code>, <code>dataProvider</code> 
	 * or <code>dataManager</code> is <code>null</code>. 
	 * 
	 */
	abstract public JDBCConnection createJDBCConnection(
		  String name
		, Boolean isReadOnly
		, JDBCProvider dataProvider
		, JDBCManager dataManager
	);



	/**
	 * Factory method that creates a new instance of Site by specific parameters.
	 * 
	 * <p>
	 * A Site represents a geographical location. It provides a grouping mechanism for a group of machines at the same location. Sites may be 
	 * documented at different levels of granularity; containment links may be used to record hierarchical relationships between Sites.
	 * </p>
	 * 
	 * @param name An identifier for the Site within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Site within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param locationType Descriptive information about the character or identity of the Site instance. Must not be <code>null</code>.
	 * 
	 * @param address The address of the Site instance. The precise content of this string is usage-defined. Must not be <code>null</code>.
	 * 
	 * @param city The name of the city in which the Site instance is found. The precise content of this string is usage-defined. Must not 
	 * be <code>null</code>.
	 * 
	 * @param postCode The postal code of the Site instance. The precise content of this string is usage-defined. Must not be <code>null</code>.
	 *
	 * @param area The area in which the Site instance is found. The precise content of this string is usage-defined, but a common usage would be to 
	 * refer to a geographical subdivision such as a state or province. Must not be <code>null</code>.
	 * 
	 * @param country The name of the country in which the Site instance is found. The precise content of this string is usage-defined. Must not 
	 * be <code>null</code>.
	 *  
	 * @return A new instance of Site, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, 
	 * <code>locationType</code>, <code>address</code>, <code>city</code>, <code>postCode</code>, <code>area</code> or 
	 * <code>country</code> is <code>null</code>. 
	 * 
	 */
//	abstract public Site createSite(
//		  String name
//		, Visibility visibility
//		, String locationType
//		, String address
//		, String city
//		, String postCode
//		, String area
//		, String country 
//	);	



	/**
	 * Factory method that creates a new instance of SoftwareSystem by specific parameters.
	 * 
	 * @param name An identifier for the SoftwareSystem within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>notInstantiable</b> - <code>true</code>.
	 * </ul>
	 * 
	 * @return A new instance of SoftwareSystem, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> is <code>null</code>.
	 * 
	 */
	abstract public SoftwareSystem createSoftwareSystem( String name );
}