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
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.CwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.resource.relational.Catalog;


/**
 * A DeployedComponent represents the deployment of a Component on a specific Machine.
 * 
 * <p>
 * It may represent the deployment of any type of Component. However, if the Component is part of a SoftwareSystem, the DeployedComponent should be 
 * part of a DeployedSoftwareSystem.
 * </p>
 * 
 * <p>
 * Usage dependencies may be used to document that one DeployedComponent uses another DeployedComponent.
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
public interface DeployedComponent
extends
	GCwmPackage<
		  GDataManager<?, ?>
		, SoftwareSystem
		, SoftwareSystem
		, Dependency
		, Constraint
		, GModelElement<?,?,?,?>
		, GModelElement<?,?,?,?>
	> 
{



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================

	

	/**
	 * Designates the Namespace that contains this DeployedComponent. Every Model Element except a root element must belong to exactly one Namespace 
	 * or else be a composite part of another ModelElement (which is a kind of virtual namespace).
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
	 * @param softwareSysteme The new namespace that contains this ModelElement.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before. 
	 * 
	 */	
	SoftwareSystem setNamespace( SoftwareSystem softwareSysteme );



	/**
	 * Adds a dependency to this DeployedComponent.
	 * 
	 * @param dependency A Dependency in witch this DeployedComponent is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds an importer (Package) to this DeployedComponent, that will contain this DeployedComponent as imported.
	 * 
	 * @param softwareSysteme The specific package, that will contain this DeployedComponent.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( SoftwareSystem softwareSysteme );



	/**
	 * Adds another Constraint to this DeployedComponent.
	 * 
	 * @param constraint A Constrain that must be satisfied by this DeployedComponent.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// Namespace capabilities	
//====================================================================================================================================================



	Boolean addOwnedElement( Catalog catalog );



	Boolean addOwnedElement( CwmPackage cwmPackage );



//====================================================================================================================================================
// Package capabilities	
//====================================================================================================================================================



	Boolean addDataManager( JDBCManager jdbcManager );



	Boolean addDataManager( CwmManager jdbcManager );



//====================================================================================================================================================
// DeployedComponent capabilities	
//====================================================================================================================================================



	/**
	 * @param typeSystemName
	 * 
	 * @param typeSystemVersion
	 */
	TypeSystem getTypeSystem( String typeSystemName, String typeSystemVersion );



	/**
	 * A pathname for the DeployedComponent within the Machine file system.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return A pathname for the DeployedComponent within the Machine file system. Can't be <code>null</code>.
	 * 
	 */
	String getPathname();



	/**
	 * A pathname for the DeployedComponent within the Machine file system.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param pathname A pathname for the DeployedComponent within the Machine file system. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>pathname</code>.
	 *  
	 * @throws NullPointerException If the parameter <code>pathname</code> is <code>null</code>. 
	 * 
	 */
	String setPathname( String pathname );



	/**
	 * Identifies all deployed Components in this Component.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Component</li>
	 * <li><i>defined by</i>    : ComponentDeployments::component</li> 
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return The Component deployed. Can't be <code>null</code>.
	 * 
	 */
	Component getComponent();



	/**
	 * Identifies the Component deployed.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Component</li>
	 * <li><i>defined by</i>    : ComponentDeployments::component</li> 
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param component The Component deployed. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>component</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>component</code> is 
	 * <code>null</code>. 
	 * 
	 */
	Component setComponent( Component component );



	Boolean removeComponent();



	/**
	 * Identifies the Machine on which the DeployedComponent is deployed.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Machine</li>
	 * <li><i>defined by</i>    : ComponentsOnMachine::machine</li> 
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * <li><i>inverse</i>       : Machine::deployedComponent</li> 
	 * </ul>
	 * 
	 * @return The Machine on which the DeployedComponent is deployed. Can't be <code>null</code>. 
	 * 
	 */
	Machine getMachine();



	/**
	 * Identifies the Machine on which the DeployedComponent is deployed.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Machine</li>
	 * <li><i>defined by</i>    : ComponentsOnMachine::machine</li> 
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * <li><i>inverse</i>       : Machine::deployedComponent</li> 
	 * </ul>
	 * 
	 * @param machine The Machine on which the DeployedComponent is deployed. 
	 * Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>machine</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>machine</code> is <code>null</code>. 
	 * 
	 */
	Machine setMachine( Machine machine );



	Boolean removeMachine();



	/**
	 * Identifies the DeployedComponent instances that use this DeployedComponent.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : DeployedComponent</li>
	 * <li><i>defined by</i>    : DeployedComponentUsage::usedComponents</li> 
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * <li><i>inverse</i>       : DeployedComponent::usingComponents</li> 
	 * </ul>
	 * 
	 * @return A Collection of the DeployedComponent instances that use this DeployedComponent. Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	Set<DeployedComponent> getUsedComponents();



//	<T extends DeployedComponent> boolean addUsedComponent( T addComponent );



//	<T extends DeployedComponent> boolean removeUsedComponent( T addComponent );



	/**
	 * Identifies the DeployedComponent instances that this DeployedComponent instance uses.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : DeployedComponent</li>
	 * <li><i>defined by</i>    : DeployedComponentUsage::usingComponents</li> 
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * <li><i>inverse</i>       : DeployedComponent::usedComponents</li> 
	 * </ul>
	 * 
	 * @return A Collection of the DeployedComponent instances that this DeployedComponent instance uses. Can't be <code>null</code>, but an empty 
	 * Collection.
	 * 
	 */
	Set<DeployedComponent> getUsingComponents();



//	<T extends DeployedComponent> boolean addUsingComponent( T addComponent );



//	<T extends DeployedComponent> boolean removeUsingComponent( T addComponent );
}