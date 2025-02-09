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

import org.ogrehus.jcwm.api.foundation.softwaredeployment.CwmManager;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.DeployedSoftwareSystem;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunCwmPackage;


/**
 * A DeployedSoftwareSystem represents a deployment of a SoftwareSystem.
 * 
 * <p>
 * Its associated DeployedComponents identify the individual Component deployments that constitute the DeployedSoftwareSystem. These
 * DeployedComponents are not necessarily all deployed on the same Machine.
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
public class FunDeployedSoftwareSystem
extends
	GFunCwmPackage<
		  CwmManager
		, SoftwareSystem
		, SoftwareSystem
		, Dependency
		, Constraint
		, GModelElement<?,?,?,?>
		, GModelElement<?,?,?,?>
	>
implements
	DeployedSoftwareSystem
{


	/**
	 * Creates a new instance of DeployedSoftwareSystem by specific parameters.
	 * 
	 * <p>
	 * A DeployedSoftwareSystem represents a deployment of a FunSoftwareSystem.
	 * </p>
	 * 
	 * @param name An identifier for the DeployedSoftwareSystem within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the DeployedSoftwareSystem within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param fixLevel Describes the fix level of the DeployedSoftwareSystem instance. Must not be <code>null</code>.
	 * 
	 * @param softwareSystem Identifies the FunSoftwareSystem deployed. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>fixLevel</code> or
	 * <code>softwareSystem</code> is <code>null</code>.
	 * 
	 */
	public FunDeployedSoftwareSystem(
		  String name
		, Visibility visibility
		, String fixLevel
		, SoftwareSystem softwareSystem
	) {
		super( name, visibility );
		this.setFixLevel( fixLevel );
		this.setSoftwareSystem( softwareSystem );
	}



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	public SoftwareSystem setNamespace( SoftwareSystem softwareSystem ) {
		return super.setNamespaceGeneric( softwareSystem ); 
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



	public Boolean addImporter( SoftwareSystem softwareSystem ) {
		return super.addImporterGeneric( softwareSystem );
	}



//====================================================================================================================================================
// DeployedSoftwareSystem capabilities
//====================================================================================================================================================



	/**
	 * Describes the fix level of the DeployedSoftwareSystem instance.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @return The description of the fix level of the DeployedSoftwareSystem instance. Can't be <code>null</code>.
	 * 
	 */ 
	public String getFixLevel() {
		return this.fixLevel;
	}



	/**
	 * Describes the fix level of the DeployedSoftwareSystem instance.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param fixLevel The description of the fix level of the DeployedSoftwareSystem instance. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>fixLevel</code>. Can't be <code>null</code>.
	 * 
	 */ 
	public String setFixLevel( String fixLevel ) {
		if ( fixLevel == null ) {
			throw new NullPointerException( "The parmeter: fixLevel must not be null." );
		}
		
		String old = this.fixLevel;
		this.fixLevel = fixLevel;

		return old;
	}



	/**
	 * Identifies the SoftwareSystem deployed.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : SoftwareSystem</li>
	 * <li><i>defined by</i>    : SoftwareSystemDeployments::softwareSystem</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @return The SoftwareSystem deployed. Can't be <code>null</code>.
	 * 
	 */ 
	public SoftwareSystem getSoftwareSystem() {
		return this.softwareSystem;
	}



	/**
	 * Identifies the SoftwareSystem deployed.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : SoftwareSystem</li>
	 * <li><i>defined by</i>    : SoftwareSystemDeployments::softwareSystem</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param softwareSystem The SoftwareSystem deployed. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>softwareSystem</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>softwareSystem</code> is <code>null</code>.
	 * 
	 */
	public SoftwareSystem setSoftwareSystem( SoftwareSystem softwareSystem ) {
		if ( softwareSystem == null ) {
			throw new NullPointerException( "The parmeter: softwareSystem must not be null." );
		}

		SoftwareSystem old = this.softwareSystem;
		this.softwareSystem = softwareSystem;

		if ( old.getDeployedSoftwareSystems().contains( this ) ) {
			old.removeDeployedSoftwareSystem( this );
		}

		if ( !this.softwareSystem.getDeployedSoftwareSystems().contains( this ) ) {
			this.softwareSystem.addDeployedSoftwareSystem( this );
		}
		
		return old;
	}



	public Boolean removeSoftwareSystem() {
		if ( this.softwareSystem == null ) {
			return Boolean.TRUE;
		}

		SoftwareSystem old = this.softwareSystem;
		this.softwareSystem = null;

		return old.removeDeployedSoftwareSystem( this );
	}



//====================================================================================================================================================
// Properites
//====================================================================================================================================================



	/**
	 * Describes the fix level of the DeployedSoftwareSystem instance.
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
	protected String fixLevel = null;



	/**
	 * Identifies the SoftwareSystem deployed.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : SoftwareSystem</li>
	 * <li><i>defined by</i>    : SoftwareSystemDeployments::softwareSystem</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 */ 
	protected SoftwareSystem softwareSystem = null;
}