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

import java.util.Set;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Model;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GSubsystem;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;


/**
 * A SoftwareSystem represents a specific release of a software product. It consists of a set of software Components. 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface SoftwareSystem
extends
	GSubsystem<
		  CwmManager
		, Model
		, Model
		, Dependency
		, Constraint
		, Component
		, Component
		, GFeature<?,?,?,?,?>
	> 
{


//====================================================================================================================================================	
// ModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the ModelElement. Every Model Element except a root element must belong to exactly one Namespace or else 
	 * be a composite part of another ModelElement (which is a kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: Namespace</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Namespace::ownedElement</li>
	 * </ul>
	 * 
	 * @param model The new namespace that contains this ModelElement.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before. 
	 * 
	 */	
	Model setNamespace( Model model );	



	/**
	 * Adds an importer (Package) to this ModelElement, that will contain this ModelElement as imported.
	 * 
	 * @param model The specific package, that will contain this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( Model model );



	/**
	 * Adds a value to the reference <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the 
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this namespace to the new ownedElement for inverse traversal.
	 * </p> 
     * 
     * @param component Adds an owning element to the contained set of <code>ownedElement</code>s.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */	
	Boolean addOwnedElement( Component component );



	/**
	 * Adds a dependency to this ModelElement.
	 * 
	 * @param dependency A Dependency in witch this ModelELement is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this element.
	 * 
	 * @param constraint A Constrain that must be satisfied by this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );	



//====================================================================================================================================================
// SoftwareSystem capabilities	
//====================================================================================================================================================



	/**
	 * Identifies the type of the software product. 
	 * 
	 * <p>
	 * One of the following predefined values should be used if appropriate:
	 * </p>
	 * <ul>
	 * <li><b>OS</b> - Operating System product</li>
	 * <lI><b>DBMS</b> - Database Management System product</li>
	 * <li><b>MDDB</b> - Multidimensional Database product</li>
	 * <li><b>FileSystem</b> - Any</li>
	 * <li><b>ODBC</b> - Open Database Connectivity</li>
	 * <li><b>JDBC</b> - Java Database Connectivity (JDBC)</li>
	 * <li>or <b>Application</b> - may be any</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * @return The types of the software product. Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	String getType();



	/**
	 * Identifies the type of the software product. 
	 * 
	 * <p>
	 * One of the following predefined values should be used if appropriate:
	 * </p>
	 * <ul>
	 * <li><b>OS</b> - Operating System product</li>
	 * <lI><b>DBMS</b> - Database Management System product</li>
	 * <li><b>MDDB</b> - Multidimensional Database product</li>
	 * <li><b>FileSystem</b> - Any</li>
	 * <li><b>ODBC</b> - Open Database Connectivity</li>
	 * <li><b>JDBC</b> - Java Database Connectivity (JDBC)</li>
	 * <li>or <b>Application</b> - may be any</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * @param type The type of the software product. Can be <code>null</code>.
	 * 
	 * @return Old value of the property <code>type</code>, or <code>null</code> if none exists.
	 * 
	 */	
	String setType( String type );



	/**
	 * This is used in conjunction with the type attribute to provide additional information about the type of the software product.
	 * <p>
	 * For some of the predefined types, suggested subtype values are listed below:
	 * </p>
	 * <ul>
	 * <li>For an Operating System product (type OS):
	 *   <ul>
	 *   <li>AIX</li>
	 *   <li>Linux</li>
	 *   <li>MVS</li>
	 *   <li>NT</li>
	 *   <li>Solaris</il>
	 *   <li>SunOS</li>
	 *   <li>VMS</li>
	 *   <li>or Windows</li>
	 *   </ul>
	 * </li>
	 * <li>For a Database Management System product (type DBMS): 
	 *   <ul>
	 *   <li>DB2</li>
	 *   <li>DMS II</li>
	 *   <li>IMS</li>
	 *   <li>Informix</li>
	 *   <li>Oracle</li>
	 *   <li>SQLServer</li>
	 *   <li>Sybase</li>   
	 *   </ul>
	 * </li>
	 * <li>For a Multidimensional Database product (type MDDB): 
	 *   <ul>
	 *   <li>Essbase</li>
	 *   <li>Express</li>
	 *   </ul>
	 * </li>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 *
	 * @return Additional information about the type of the software product. Can't be <code>null</code>, but an empty Set.
	 *  
	 */
	String getSubtype();



	/**
	 * This is used in conjunction with the type attribute to provide additional information about the type of the software product.
	 * <p>
	 * For some of the predefined types, suggested subtype values are listed below:
	 * </p>
	 * <ul>
	 * <li>For an Operating System product (type OS):
	 *   <ul>
	 *   <li>AIX</li>
	 *   <li>Linux</li>
	 *   <li>MVS</li>
	 *   <li>NT</li>
	 *   <li>Solaris</il>
	 *   <li>SunOS</li>
	 *   <li>VMS</li>
	 *   <li>or Windows</li>
	 *   </ul>
	 * </li>
	 * <li>For a Database Management System product (type DBMS): 
	 *   <ul>
	 *   <li>DB2</li>
	 *   <li>DMS II</li>   
	 *   <li>IMS</li>
	 *   <li>Informix</li>   
	 *   <li>Oracle</li>
	 *   <li>SQLServer</li>   
	 *   <li>Sybase</li>   
	 *   </ul>
	 * </li>
	 * <li>For a Multidimensional Database product (type MDDB): 
	 *   <ul>
	 *   <li>Essbase</li>
	 *   <li>Express</li>
	 *   </ul>
	 * </li>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 *
	 * @return subType Additional information about the type of the software product. Can be <code>null</code>.
	 *
	 * @return Old value of the property <code>subType</code>, or <code>null</code> if none exists.
	 *  
	 */
	String setSubType( String subType );



	/**
	 * The suppliers of the software product.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * 
	 * @return The suppliers of the software product.
	 * 
	 */
	String getSupplier();



	String setSupplier( String softwareSupplier );



	/**
	 * The version identification for the software product.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @return The version identification for the software product. Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	String getVersion();



	String setVersion( String softwareVersion );



	/**
	 * Identifies the TypeSystem(s) containing the datatypes supported by the SoftwareSystem.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : TypeSystem</li>
	 * <li><i>defined by</i>    : SystemTypespace::typespace</li> 
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * @return the TypeSystem(s) containing the datatypes supported by the SoftwareSystem. Can't be <code>null</code>, 
     * but an empty Collection.
	 * 
	 */
	Set<TypeSystem> getTypespaces();



	Boolean addTypespace( TypeSystem typeSystem );



	Boolean removeTypeSystem( TypeSystem typeSystem );



	TypeSystem getTypespace( String typeSystemName, String typeSystemVersion );



	Set<DeployedSoftwareSystem> getDeployedSoftwareSystems();



	Boolean addDeployedSoftwareSystem( DeployedSoftwareSystem deployedSoftwareSystem );



	Boolean removeDeployedSoftwareSystem( DeployedSoftwareSystem deployedSoftwareSystem );
}