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
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;


/**
 * A Component represents a physical piece of implementation of a system, including software code (source, binary or executable) or equivalents such 
 * as scripts or command files. 
 * <p>
 * A Component is a sub-type of Classifier, and so may have its own Features, such as Attributes and Operations. Deployment of a Component on a 
 * specific Machine is represented as a DeployedComponent.
 * </p> 
 * 
 * <p>
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * 
 */
public interface Component
extends
	GClassifier<
		  SoftwareSystem
		, SoftwareSystem
		, Dependency
		, Constraint
		, GModelElement<?,?,?,?>
		, GFeature<?,?,?,?,?>
	> 
{



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the Component. Every Model Element except a root element must belong to exactly one Namespace or else 
	 * be a composite part of another ModelElement (which is a kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * </p>
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
	 * @param softwareSystem The new namespace that contains this Component.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before. 
	 * 
	 */	
	SoftwareSystem setNamespace( SoftwareSystem softwareSystem );



	/**
	 * Adds an importer (Package) to this Component, that will contain this Component as imported.
	 * 
	 * @param softwareSystem The specific package, that will contain this Component.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( SoftwareSystem softwareSystem );



	/**
	 * Adds a dependency to this Component.
	 * 
	 * @param dependency A Dependency in witch this Component is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this element.
	 * 
	 * @param constraint A Constrain that must be satisfied by this Component.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// Component capabilities	
//====================================================================================================================================================



	/**
	 * Identifies the Package instance containing the Component's design.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : Package</li>
	 * <li><i>defined by</i>    : ComponentDesign::designPackage</li> 
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * @return A Collection of the the Package instance containing the Component's design. Can't be <code>null</code>, 
	 * but empty.
	 * 
	 */
	Set<GCwmPackage<?, ?, ?, ?, ?, ?, ?>> getDesignPackages();



	Boolean addDesignPackage( GCwmPackage<?, ?, ?, ?, ?, ?, ?> designPackage );



	Boolean removeDesignPackage( GCwmPackage<?, ?, ?, ?, ?, ?, ?> designPackage );



	Set<DeployedComponent> getDeployments();



	Boolean removeDeployment( DeployedComponent deployedComponent );



	Boolean addDeployment( DeployedComponent deployedComponent );



	<DEPLOYED extends DeployedComponent> DEPLOYED getDeployment( Class<DEPLOYED> typeof, String simpleName );
}