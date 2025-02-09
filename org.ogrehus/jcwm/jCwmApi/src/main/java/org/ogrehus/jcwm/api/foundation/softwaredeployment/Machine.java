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

import java.util.List;

import java.util.Set;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Model;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;


/**
 * A Machine represents a computer. The Site at which the Machine is located and the Components deployed on the Machine may be recorded.
 *  
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Machine
extends
	GNamespace<
		  Model
		, Model
		, Dependency
		, Constraint
		, GModelElement<?,?,?,?>
	> 
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the ModelElement. Every Model Element except a root element must belong to exactly one Namespace or 
	 * else be a composite part of another ModelElement (which is a kind of virtual namespace).
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
	 * Adds an importer (Package) to this Machine, that will contain this Machine as imported.
	 * 
	 * @param model The specific package, that will contain this Machine.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( Model model );



	/**
	 * Adds a dependency to this Machine.
	 * 
	 * @param dependency A Dependency in witch this Machine is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this element.
	 * 
	 * @param constraint A Constrain that must be satisfied by this Machine.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// Machine capabilities	
//====================================================================================================================================================



	/**
	 * A fixed IP address for the Machine.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or more; ordered</li> 
	 * </ul>
	 * 
	 * @return A List of the fixed IP address for the Machine. Can't be <code>null</code>, but an empty List.
	 * 
	 */ 
	List<String> getIpAddresses();



	/**
	 * A Host Name for the Machine. This may be used to identify the Machine on the network when IP addresses are dynamically allocated.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or more; ordered</li> 
	 * </ul>
	 *
	 * @return A List of the Host Names for the Machine. This may be used to identify the Machine on the network when IP addresses are dynamically 
	 * allocated. Can't be <code>null</code>, but an empty List.
	 * 
	 */
	List<String> getHostNames();



	/**
	 * An identification code for the Machine.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * @return A List of the identification code for the Machine. Can't be <code>null</code>, but an empty List.
	 * 
	 */ 
	Set<String> getMachineIDs();



	/**
	 * Identifies the Site at which the Machine is located.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Site</li>
	 * <li><i>defined by</i>    : SiteMachines::site</li> 
	 * <li><i>multiplicity</i>  : zero or one </li> 
	 * </ul>
	 * 
	 * @return A Collection of the identification of the Sites at which the Machine is located. Can't be 
	 * <code>null</code>, but an empty List.
	 * 
	 */ 
	Set<Site> getSites();



	/**
	 * Identifies the DeployedComponents on the Machine.
	 * 
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : DeployedComponent</li>
	 * <li><i>defined by</i>    : ComponentsOnMachine::deployedComponent</li> 
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * <li><i>inverse</i>       : DeployedComponent::machine</li> 
	 * </ul>
	 *
	 * @return A Setof the identification of the DeployedComponents on the Machine. Can't be <code>null</code>, but an empty Set.
	 * 
	 */ 
	Set<DeployedComponent> getDeployedComponents();



	Boolean addDeployedComponent( DeployedComponent deployedComponent );



	Boolean removeDeployedComponent( DeployedComponent deployedComponent );
}