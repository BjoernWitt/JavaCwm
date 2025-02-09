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

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;


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
public interface DeployedSoftwareSystem
extends
	GCwmPackage<
		  CwmManager
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



	public SoftwareSystem setNamespace( SoftwareSystem softwareSystem );



	public Boolean addDependency( Dependency dependency );



	public Boolean addConstraint( Constraint constraint );



	public Boolean addImporter( SoftwareSystem softwareSystem );



//====================================================================================================================================================
// GModelElement capabilities	
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
	String getFixLevel();



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
	 * @param fixLevel Describes the fix level of the DeployedSoftwareSystem instance.
	 * 
	 * @return The description of the fix level of the DeployedSoftwareSystem instance. Can't be <code>null</code>.
	 * 
	 */ 
	String setFixLevel( String fixLevel );



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
	SoftwareSystem getSoftwareSystem();



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
	 * @param softwareSystem The SoftwareSystem deployed. Must not be 
	 * <code>null</code>.
	 * 
	 * @return Old value of the property <code>softwareSystem</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>softwareSystem</code>
	 * is <code>null</code>. 
	 * 
	 */
	SoftwareSystem setSoftwareSystem( SoftwareSystem softwareSystem );



	Boolean removeSoftwareSystem();
}