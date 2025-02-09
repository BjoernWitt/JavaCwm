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

import java.util.Properties;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCConnection;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCManager;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCPackageUsage;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCProvider;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

import org.ogrehus.jcwm.impl.foundation.softwaredeployment.util.generic.GFunProviderConnection;

/**
 * A ProviderConnection represents a connection that allows a DataProvider acting as a client to access data from a specific DataManager.
 *  
 * <p>
 * For example a ProviderConnection could represent a connection from an ODBC or JDBC client to a DBMS.
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
public class FunJDBCConnection
extends
	GFunProviderConnection<JDBCManager, JDBCProvider, JDBCPackageUsage>
implements
	JDBCConnection
{



	/**
	 * Creates a new instance of ProviderConnection by specific parameters.
	 * 
	 * @param name An identifier for the ProviderConnection within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the ProviderConnection within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param isReadOnly Indicates whether the ProviderConnection only allows read access to the DataManager. Must not be <code>null</code>.
	 * 
	 * @param jdbcProvider Identifies the DataProvider that is the client of the ProviderConnection. Must not be <code>null</code>.
	 * 
	 * @param jdbcManager Identifies the DataManager that is accessed by the ProviderConnection.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>isReadOnly</code>,
	 * <code>dataProvider</code> or <code>dataManager</code> is <code>null</code>.
	 * 
	 */
	public FunJDBCConnection(
		  String name
		, Visibility visibility
		, Boolean isReadOnly
		, JDBCProvider jdbcProvider
		, JDBCManager jdbcManager
	) {
		super( name, visibility, isReadOnly, jdbcProvider, jdbcManager );
	}



	/**
	 * Identifies the DataProvider that is the client of the ProviderConnection.
	 *
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-8-2]</b> A ProviderConnection must not associate a DataProvider with itself.</li>
	 * </ul>
	 * 
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: DataProvider</li>
	 * <li><i>defined by</i>	: DataProviderConnections::dataProvider</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * <li><i>inverse</i>		: DataProvider::resourceConnection</li>
	 * </ul>
	 * 
	 * @param jdbcProvider The DataProvider that is the client of the ProviderConnection. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>dataProvider</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>dataProvider</code> is <code>null</code>.
	 * 
	 */
	public JDBCProvider setDataProvider( JDBCProvider jdbcProvider ) {
		if ( jdbcProvider == null ) {
			throw new NullPointerException( "The parmeter: jdbcProvider must not be null." );
		}

		if ( jdbcProvider.equals( this.dataProvider ) ) {
			return jdbcProvider; // no changes
		}

		JDBCProvider old = this.dataProvider;
		if ( old != null && old.getResourceConnections().contains( this ) ) {
			old.removeResourceConnection( this );
		}

		this.dataProvider = jdbcProvider;
		if ( !(this.dataProvider.getResourceConnections().contains( this )) ) {
			this.dataProvider.addResourceConnection( this );
		}

		return old;
	}



	/**
	 * Identifies the DataManager that is accessed by the ProviderConnection.
	 * 
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: DataManager</li>
	 * <li><i>defined by</i>	: DataManagerConnections::dataManager</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @param jdbcManager The DataManager that is accessed by the ProviderConnection. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>dataManager</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>dataManager</code> is <code>null</code>.
	 * 
	 */
	public JDBCManager setDataManager( JDBCManager jdbcManager ) {
		if ( jdbcManager == null ) {
			throw new NullPointerException( "The parameter: jdbcManager must not be null." );
		}

		if ( jdbcManager.equals( this.dataManager ) ) {
			return jdbcManager; // no changes
		}

		JDBCManager old = this.dataManager;
		if ( old != null && old.getClientConnections().contains( this ) ) {
			old.removeClientConnection( this );
		}

		this.dataManager = jdbcManager;
		if ( !(this.dataManager.getClientConnections().contains( this )) ) {
			this.dataManager.addClientConnection( this );
		}
		
		return old;
	}



	public Properties setProperties( Properties properties ) {
		Properties old = this.properties;
		this.properties = properties;
		
		return old;
	}



	public Properties getProperties() {
		return this.properties; // might be  null if none avail
	}



	public String getDriverClass() {
		return this.getDataManager().getPathname();
	}



	public String getUrl() {
		return this.getDataProvider().getPathname();
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	protected Properties properties = null;
}