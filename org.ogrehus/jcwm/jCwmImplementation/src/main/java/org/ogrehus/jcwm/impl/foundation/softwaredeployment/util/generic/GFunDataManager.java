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
package org.ogrehus.jcwm.impl.foundation.softwaredeployment.util.generic;

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.Component;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.Machine;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GProviderConnection;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;

import org.ogrehus.jcwm.impl.foundation.softwaredeployment.FunDeployedComponent;


/**
 * A DataManager represents a DeployedComponent that manages access to data. 
 * 
 * <p>
 * For example, a deployed DBMS or File Management System would be represented as a DataManager.
 * </p>
 * <p>
 * The DataManager may be associated with one or more data Packages identifying the Schema, Relational Catalog, Files, or other data container that it
 * provides access to.
 * </p>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param DATA_PACKAGE
 * @param CONNECTION 
 * 
 */
public abstract class GFunDataManager<
	  DATA_PACKAGE extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CONNECTION   extends GProviderConnection<?, ?, ?>
>extends
	FunDeployedComponent 
implements
	GDataManager<DATA_PACKAGE, CONNECTION>
{


	/**
	 * Factory method that creates a new instance of DataManager by specific parameters.
	 * 
	 * <p>
	 * A DataManager represents a DeployedComponent that manages access to data. 
	 * </p>
	 * 
	 * @param name An identifier for the DataManager within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the DataManager within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param pathname A pathname for the DataManager within the Machine file system.
	 *
	 * @param component identifies the Component that is deployed by this DataManager. Must not be <code>null</code>.
	 * 
	 * @param machine identifies the Machine on which the DataManager is deployed. Must not be <code>null</code>.
	 * 
	 * @param isCaseSensitive Indicates whether or not the DataManager treats lower case letters within object names as being different from the
	 * corresponding upper case letters. Must not be <code>null</code>.
	 *  
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>pathname</code>,
	 * <code>component</code>, <code>machine</code> or <code>isCaseSensitive</code> is <code>null</code>.
	 * 
	 */
	protected GFunDataManager(
		  String name
		, Visibility visibility
		, String pathname
		, Component component
		, Machine machine
		, Boolean isCaseSensitive
	) {
		super( name, visibility, pathname, component, machine );
		this.dataPackages      = new HashSet<DATA_PACKAGE>();
		this.clientConnections = new HashSet<CONNECTION>();
		this.setCaseSensitive( isCaseSensitive );
	}



//====================================================================================================================================================
// DataManager capabilities
//====================================================================================================================================================



	/**
	 * Indicates whether or not the DataManager treats lower case letters within object names as being different from the corresponding upper case
	 * letters.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Boolean</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the DataManager treats lower case letters within object names as being different from the corresponding upper case
	 * letters, <code>false/code> otherwise. Can't be <code>null</code>.
	 * 
	 */
	public Boolean isCaseSensitive() {
		return this.caseSensitive;
	}



	/**
	 * Indicates whether or not the DataManager treats lower case letters within object names as being different from the corresponding upper case
	 * letters.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Boolean</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param caseSensitive <code>true</code> if the DataManager treats lower case letters within object names as being different from the
	 * corresponding upper case letters, <code>false/code> otherwise. Can't be <code>null</code>.
	 * 
	 * @return The old value for the property <code>caseSensitive</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>CaseSensitive</code> is <code>null</code>.
	 * 
	 */
	public Boolean setCaseSensitive( Boolean caseSensitive ) {
		if ( caseSensitive == null ) {
			throw new NullPointerException( "The parameter: caseSensitive must not be null." );
		}
		Boolean old = this.caseSensitive;
		this.caseSensitive = caseSensitive;
		
		return old;
	}



	/**
	 * Identifies the Package(s) containing the definition of the data made available by the DataManager.
	 * 
	 * <p>
	 * Think of a catalog instance from the relational model as a valid value.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>class</i>         : CwmPackage</li>
	 * <li><i>defined by</i>    : DataManagerDataPackage::dataPackage</li> 
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * @return A Collection of the packages containing the definition of the data made available by the DataManager. 
	 * Can't be <code>null</code>, but an empty Collection.
	 * 
	 */
	public Set<DATA_PACKAGE> getDataPackages() {
		return this.dataPackages;
	}



	public DATA_PACKAGE getDataPackage( String simpleName ) {
		if ( simpleName != null ) {
			for ( DATA_PACKAGE dataPackage : this.dataPackages ) {
				if ( simpleName.equals( dataPackage.getSimpleName() ) ) {
					return dataPackage;
				}
			}
		}

		return null;
	}



	protected Boolean addDataPackageGeneric( DATA_PACKAGE dataPackage ) {
		if ( dataPackage != null && this.dataPackages.add( dataPackage ) ) {
			invokeByReflection( dataPackage, "addDataManager", this ); // bi-directional reference
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeDataPackage( GCwmPackage<?, ?, ?, ?, ?, ?, ?> dataPackage ) {
		if ( dataPackage == null ) {
			return Boolean.TRUE;
		}
		if ( this.ownedElements.remove( dataPackage ) ) {
			dataPackage.removeDataManager( this ); // remove bi-directional reference
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



	/**
	 * Identifies the ProviderConnections that may be used by clients to access the data provided by this DataManager.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ProviderConnection</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 * @return A Collection of ProviderConnections provided by this DataManager
	 * 
	 */
	public Set<CONNECTION> getClientConnections() {
		return this.clientConnections;
	}



	protected Boolean addClientConnectionGeneric( CONNECTION connection ) {
		if ( connection != null && this.clientConnections.add( connection ) ) {
			invokeByReflection( connection, "setDataManager", this ); // bi-directional reference
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}



	public Boolean removeClientConnection( GProviderConnection<?, ?, ?> connection ) {
		return this.clientConnections.remove( connection );
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunDataProvider" );
		if ( this.caseSensitive != null ) {
			out.append( " | caseSensitive=" + this.caseSensitive );
		}

		if ( !this.dataPackages.isEmpty() ) {
			out.append( " | dataPackages(" + this.dataPackages.size() + ")={" );
			boolean comma = false;
			for ( DATA_PACKAGE dataPackage : this.dataPackages ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( dataPackage.getSimpleName() );
				out.append( "<" + dataPackage.getClass().getSimpleName() + ">" );
			}
			out.append( "}" );
		}
		
		if ( !this.clientConnections.isEmpty() ) {
			out.append( " | clientConnections(" + this.clientConnections.size() + ")={" );
			boolean comma = false;
			for ( CONNECTION clientConnection : this.clientConnections ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( clientConnection.getSimpleName() );
				out.append( "<" + clientConnection.getClass().getSimpleName() + ">" );
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
	 * Indicates whether or not the DataManager treats lower case letters within object names as being different from the corresponding upper case
	 * letters.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Boolean</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 */
	protected Boolean caseSensitive = null;



	/**
	 * Identifies the Package(s) containing the definition of the data made available by the DataManager.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : CwmPackage</li>
	 * <li><i>defined by</i>    : DataManagerDataPackage::dataPackage</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 */
	protected Set<DATA_PACKAGE> dataPackages = null;



	protected Set<CONNECTION> clientConnections = null;
}