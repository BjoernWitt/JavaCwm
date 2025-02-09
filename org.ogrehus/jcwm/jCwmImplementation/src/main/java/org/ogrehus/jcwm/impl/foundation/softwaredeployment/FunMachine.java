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
package org.ogrehus.jcwm.impl.foundation.softwaredeployment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.DeployedComponent;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.Machine;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.Site;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Model;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunNamespace;


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
public class FunMachine
extends
	GFunNamespace<
		  Model
		, Model
		, Dependency
		, Constraint
		, GModelElement<?,?,?,?>
	>
implements
	Machine
{


	/**
	 * Factory method that creates a new instance of Machine by specific parameters.
	 * <p>
	 * A Machine represents a computer. The Site at which the Machine is located and the Components deployed on the Machine may be recorded.
	 * </p>
	 * 
	 * @param name An identifier for the Machine within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Machine within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	public FunMachine( String name, Visibility visibility ) {
		super( name, visibility ); // may throw NullPointerException
		
		this.deployedComponents = new HashSet<DeployedComponent>();
		this.hostNames          = new ArrayList<String>();
		this.ipAddresses        = new ArrayList<String>();
		this.machineIDs         = new HashSet<String>();
		this.sites              = new HashSet<Site>();
	}



//====================================================================================================================================================
// GModelElement capabilities
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
	public Model setNamespace( Model model ) {
		return super.setNamespaceGeneric( model );
	}



	/**
	 * Adds an importer (Package) to this Machine, that will contain this Machine as imported.
	 * 
	 * @param model The specific package, that will contain this Machine.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addImporter( Model model ) {
		return super.addImporterGeneric( model );
	}



	/**
	 * Adds a dependency to this Machine.
	 * 
	 * @param dependency A Dependency in witch this Machine is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	/**
	 * Adds a constraint to this element.
	 * 
	 * @param constraint A Constrain that must be satisfied by this Machine.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



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
	public List<String> getIpAddresses() {
		return this.ipAddresses;
	}



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
	public List<String> getHostNames() {
		return this.hostNames;
	}



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
	public Set<String> getMachineIDs() {
		return this.machineIDs;
	}



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
	 * @return A Collection of the identification of the Sites at which the Machine is located. Can't be <code>null</code>, but an empty List.
	 * 
	 */
	public Set<Site> getSites() {
		return this.sites;
	}



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
	public Set<DeployedComponent> getDeployedComponents() {
		return this.deployedComponents;
	}



	public Boolean addDeployedComponent( DeployedComponent deployedComponent ) {
		if ( deployedComponent != null && this.deployedComponents.add( deployedComponent ) ) {
			deployedComponent.setMachine( this );
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeDeployedComponent( DeployedComponent deployedComponent ) {
		if ( deployedComponent != null && this.deployedComponents.remove( deployedComponent ) ) {
			deployedComponent.removeMachine();
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



//====================================================================================================================================================
// GModelElement capabilities
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
	 */
	protected List<String> ipAddresses = null;



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
	 */ 
	protected List<String> hostNames = null;



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
	 */
	protected Set<String> machineIDs = null;



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
	 */
	protected Set<Site> sites =null;



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
	 */
	protected Set<DeployedComponent> deployedComponents = null;
}