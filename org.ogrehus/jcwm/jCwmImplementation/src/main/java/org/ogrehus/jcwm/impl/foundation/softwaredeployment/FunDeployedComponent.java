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
import org.ogrehus.jcwm.api.foundation.softwaredeployment.CwmManager;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCManager;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.DeployedComponent;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.Machine;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareSystem;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.CwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.resource.relational.Catalog;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunCwmPackage;


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
public class FunDeployedComponent
extends
	GFunCwmPackage<
		  GDataManager<?, ?> // can be JDBCManager, CwmManager or any other
		, SoftwareSystem
		, SoftwareSystem
		, Dependency
		, Constraint
		, GModelElement<?,?,?,?>
		, GModelElement<?,?,?,?>
	> 
implements
	DeployedComponent
{


	/**
	 * Factory method that creates a new instance of DeployedComponent by specific parameters.
	 * 
	 * <p>
	 * A DeployedComponent represents the deployment of a Component on a specific Machine.
	 * </p> 
	 * 
	 * @param name An identifier for the DeployedComponent within its containing Namespace. Must not be 
	 * <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the DeployedComponent within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param pathname A pathname for the DeployedComponent within the Machine file system.
	 *
	 * @param component identifies the Component that is deployed by this DeployedComponent. Must not be <code>null</code>.
	 * 
	 * @param machine identifies the Machine on which the DeployedComponent is deployed. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>pathname</code>,
	 * <code>component</code> or <code>machine</code> is <code>null</code>.
	 * 
	 */
	public FunDeployedComponent(
		  String name
		, Visibility visibility
		, String pathname
		, Component component
		, Machine machine
	) {
		super( name, visibility );      // may throw NullPointerException
		this.usedComponents  = new HashSet<DeployedComponent>();
		this.usingComponents = new HashSet<DeployedComponent>();
		this.setPathname( pathname );   // may throw NullPointerException 
		this.setComponent( component ); // may throw NullPointerException 
		this.setMachine( machine );     // may throw NullPointerException 
	}



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
	 * @param softwareSystem The new namespace that contains this ModelElement.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */
	public SoftwareSystem setNamespace( SoftwareSystem softwareSystem ) {
		return super.setNamespaceGeneric( softwareSystem );
	}



	/**
	 * Adds an importer (Package) to this DeployedComponent, that will contain this ModelElement as imported.
	 * 
	 * @param softwareSystem The specific package, that will contain this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addImporter( SoftwareSystem softwareSystem ) {
		return super.addImporterGeneric( softwareSystem );
	}



	/**
	 * Adds a dependency to this ModelElement.
	 * 
	 * @param dependency A Dependency in witch this ModelELement is a client.
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
	 * @param constraint A Constrain that must be satisfied by this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



//====================================================================================================================================================
// Namespace capabilities
//====================================================================================================================================================



	public Boolean addOwnedElement( Catalog catalog ) {
		return super.addOwnedElementGeneric( catalog );
	}



	public Boolean addOwnedElement( CwmPackage cwmPackage ) {
		return super.addOwnedElementGeneric( cwmPackage );
	}



//====================================================================================================================================================
// DeployedComponent capabilities
//====================================================================================================================================================



	public TypeSystem getTypeSystem( String typeSystemName, String typeSystemVersion ) {
		Component component = getComponent();
		if ( component != null ) {
			SoftwareSystem system = component.getNamespace();
			if ( system != null ) {
				return system.getTypespace( typeSystemName, typeSystemVersion );
			}
		}
		
		return null;
	}



	public Boolean addDataManager( JDBCManager jdbcManager ) {
		return super.addDataManagerGeneric( jdbcManager );
	}



	public Boolean addDataManager( CwmManager cwmManager ) {
		return super.addDataManagerGeneric( cwmManager );
	}



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
	 * @return A pathname for the DeployedComponent within the Machine�s file system. Can't be <code>null</code>.
	 * 
	 */
	public String getPathname() {
		return this.pathname;
	}



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
	 * @param pathname A pathname for the DeployedComponent within the Machine�s file system. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>pathname</code>.
	 *  
	 * @throws NullPointerException If the parameter <code>pathname</code> is <code>null</code>.
	 * 
	 */
	public String setPathname( String pathname ) {
		if ( pathname == null ) {
			throw new NullPointerException( "The parameter: pathname must not be null." );
		}
		
		String old = this.pathname;
		this.pathname = pathname;
		
		return old;
	}



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
	public Component getComponent() {
		return this.component;
	}



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
	 * @throws NullPointerException If the parameter <code>component</code> is <code>null</code>.
	 * 
	 */
	public Component setComponent( Component component ) {
		if ( component == null ) {
			throw new NullPointerException( "The parameter: component must not be null." );
		}
		
		if ( component.equals( this.component ) ) {
			return component; // no changes, cause its the same namespace
		}
		
		Component old = this.component;
		if ( old != null  ) {
			old.removeDeployment( this );
		}
		
		this.component = component;
		if ( !this.component.getDeployments().contains( this ) ) {
			this.component.addDeployment( this ); // organize the bidirectional reference 
		}
		
		return old;
	}



	public Boolean removeComponent() {
		if ( this.component == null ) {
			return Boolean.TRUE;
		}
		
		Component old = this.component;
		this.component = null;
		
		return old.removeDeployment( this );
	}



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
	public Machine getMachine() {
		return this.machine;
	}



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
	 * @param machine The Machine on which the DeployedComponent is deployed. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>machine</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>machine</code> is <code>null</code>.
	 * 
	 */
	public Machine setMachine( Machine machine ) {
		if ( machine == null ) {
			throw new NullPointerException( "The parameter: machine must not be null." );
		}
		
		if ( machine != null && machine.equals( this.machine ) ) {
			return machine; // no changes, cause its the same namespace
		}
		
		Machine old = this.machine;
		if ( old != null  ) {
			old.removeDeployedComponent( this );
		}
		
		this.machine = machine;
		if ( this.machine != null ) {
			if ( !this.machine.getDeployedComponents().contains( this ) ) {
				this.machine.addDeployedComponent( this ); // organize the bidirectional reference 
			}
		}
		
		return old;
	}



	public Boolean removeMachine() {
		if ( this.machine == null ) {
			return Boolean.TRUE;
		}
		
		Machine old = this.machine;
		this.machine = null;
		
		return old.removeDeployedComponent( this );
	}



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
	 * @return A Collection of the DeployedComponent instances that use this DeployedComponent. Can't be <code>null</code>, but an empty Set.
	 * 
	 */
	public Set<DeployedComponent> getUsedComponents() {
		return this.usedComponents;
	}



//	<T extends DeployedComponent> boolean addUsedComponent( T usedComponent ) {
//        
//    }
//	<T extends DeployedComponent> boolean removeUsedComponent( T usedComponent );



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
	public Set<DeployedComponent> getUsingComponents() {
		return this.usingComponents;
	}



//	<T extends DeployedComponent> boolean addUsingComponent( T addComponent );
//	<T extends DeployedComponent> boolean removeUsingComponent( T addComponent );



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunDeployedComponent" );
		out.append( " | pathname=" + this.pathname );
		out.append( " | component=" + this.component.getQualifiedName() );
		out.append( " | machine=" + this.machine.getQualifiedName() );
		
		if ( !this.usedComponents.isEmpty() ) {
			out.append( " | usedComponents(" + this.usedComponents.size() + ")={" );
			boolean comma = false;
			for ( DeployedComponent usedComponent : this.usedComponents ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( usedComponent.getSimpleName() );
				out.append( "<" + usedComponent.getClass().getSimpleName() + ">" );
			}
			out.append( "}" );
		}
		
		if ( !this.usingComponents.isEmpty() ) {
			out.append( " | usingComponents(" + this.usingComponents.size() + ")={" );
			boolean comma = false;
			for ( DeployedComponent usingComponent : this.usingComponents ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( usingComponent.getSimpleName() );
				out.append( "<" + usingComponent.getClass().getSimpleName() + ">" );
			}
			out.append( "}" );
		}
		
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		
		return out.toString();
	}



//====================================================================================================================================================
// DeployedComponent capabilities
//====================================================================================================================================================



	/**
	 * A pathname for the DeployedComponent within the Machine�s file system.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 */
	protected String pathname = null;



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
	 */
	protected Component component = null;



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
	 */
	protected Machine machine = null;



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
	 */
	protected Set<DeployedComponent> usedComponents = null;



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
	 */
	protected Set<DeployedComponent> usingComponents = null;
}