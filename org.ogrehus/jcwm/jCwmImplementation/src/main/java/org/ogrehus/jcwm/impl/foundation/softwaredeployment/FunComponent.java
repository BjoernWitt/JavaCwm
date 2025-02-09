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

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.Component;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.DeployedComponent;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunClassifier;


/**
 * A Component represents a physical piece of implementation of a system, including software code (source, binary or executable) or equivalents such
 * as scripts or command files. 
 * <p>
 * A Component is a subt-ype of Classifier, and so may have its own Features, such as Attributes and Operations. Deployment of a Component on a
 * specific Machine is represented as a DeployedComponent.
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
public class FunComponent
extends
	GFunClassifier<
		  SoftwareSystem
		, SoftwareSystem
		, Dependency
		, Constraint
		, GModelElement<?,?,?,?>
		, GFeature<?,?,?,?,?>
	>
implements
	Component
{


	/**
	 * Creates a new instance of Component by specific parameters.
	 * 
	 * @param name An identifier for the Component within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Component within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this Component is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 *
	 */
	public FunComponent( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility, notInstantiable ); // may throw NullPointerException
		this.deployments    = new HashSet<DeployedComponent>();
		this.designPackages = new HashSet<GCwmPackage<?, ?, ?, ?, ?, ?, ?>>();
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the Component. Every Model Element except a root element must belong to exactly one Namespace or else be
	 * a composite part of another ModelElement (which is a kind of virtual namespace).
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
	 * @param softwareSystem The new namespace that contains this Component.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */
	public SoftwareSystem setNamespace( SoftwareSystem softwareSystem ) {
		return super.setNamespaceGeneric( softwareSystem );
	}



	/**
	 * Adds an importer (Package) to this Component, that will contain this Component as imported.
	 * 
	 * @param softwareSystem The specific package, that will contain this Component.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addImporter( SoftwareSystem softwareSystem ) {
		return super.addImporterGeneric( softwareSystem );
	}



	/**
	 * Adds a dependency to this Component.
	 * 
	 * @param dependency A Dependency in witch this Component is a client.
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
	 * @param constraint A Constrain that must be satisfied by this Component.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



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
	 * @return A Collection of the the Package instance containing the Component's design. Can't be <code>null</code>, but empty.
	 * 
	 */
	public Set<GCwmPackage<?, ?, ?, ?, ?, ?, ?>> getDesignPackages() {
		return this.designPackages;
	}



	public Boolean addDesignPackage( GCwmPackage<?, ?, ?, ?, ?, ?, ?> designPackage ) {
		return ((designPackage != null) && (this.designPackages.add( designPackage )));
	}



	public Boolean removeDesignPackage(GCwmPackage<?, ?, ?, ?, ?, ?, ?> designPackage) {
		return ((designPackage != null) && (this.designPackages.remove( designPackage )));
	}



	public Set<DeployedComponent> getDeployments() {
		return this.deployments;
	}



	public <DEPLOYED extends DeployedComponent> DEPLOYED getDeployment( Class<DEPLOYED> typeof, String simpleName ) {
		if ( simpleName == null || typeof == null ) {
			return null;
		}
		
		for ( DeployedComponent deployment : this.deployments ) {
			if ( simpleName.equals( deployment.getSimpleName() ) && typeof.isInstance( deployment ) ) {
				return typeof.cast( deployment );
			}
		}
		
		return null; // found nothing
	}



	public Boolean removeDeployment( DeployedComponent deployedComponent ) {
		if ( deployedComponent != null && this.deployments.remove( deployedComponent ) ) {
			deployedComponent.removeComponent();
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean addDeployment( DeployedComponent deployedComponent ) {
		if ( deployedComponent != null && this.deployments.add( deployedComponent ) ) {
			if ( !this.equals( deployedComponent.getComponent() ) ) {
				deployedComponent.setComponent( this );
			}
			
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunComponent" );
		if ( !this.designPackages.isEmpty() ) {
			out.append( " | designPackages(" + this.designPackages.size() + ")={" );
			boolean comma = false;
			for ( GCwmPackage<?, ?, ?, ?, ?, ?, ?> designPackage : this.designPackages ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( designPackage.getSimpleName() );
				out.append( "<" + designPackage.getClass().getSimpleName() + ">" );
			}
			out.append( "}" );
		}

		if ( !this.deployments.isEmpty() ) {
			out.append( " | deployments(" + this.deployments.size() + ")={" );
			boolean comma = false;
			for ( GCwmPackage<?, ?, ?, ?, ?, ?, ?> deployment : this.deployments ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( deployment.getSimpleName() );
				out.append( "<" + deployment.getClass().getSimpleName() + ">" );
			}
			out.append( "}" );
		}
		
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		
		return out.toString();
	}



//====================================================================================================================================================
// Properties
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
	 */
	protected Set<GCwmPackage<?, ?, ?, ?, ?, ?, ?>> designPackages = null;



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
	 */
	protected Set<DeployedComponent> deployments = null;
}