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

import org.ogrehus.jcwm.api.foundation.softwaredeployment.Component;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCConnection;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCManager;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCProvider;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.Machine;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.Catalog;

import org.ogrehus.jcwm.impl.foundation.softwaredeployment.util.generic.GFunDataProvider;


/**
 * A JDBCProvider is a deployed software Component that acts as a client to provide access to JDBC data that is managed by another product.
 * <p>
 * For instance, a DataProvider might represent a deployed ODBC or JDBC product. The DataProvider may have resourceConnection references to
 * ProviderConnections identifying the DataManagers to which it provides access.
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
public class FunJDBCProvider
extends
	GFunDataProvider<Catalog, JDBCConnection>
implements
	  JDBCProvider
	, JDBCManager
{



	/**
	 * Creates a new instance of GDataProvider by specific parameters.
	 * 
	 * <p>
	 * A DataProvider is a deployed software Component that acts as a client to provide access to data that is managed by another product.
	 * </p>
	 * 
	 * @param name An identifier for the DataProvider within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the DataProvider within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param pathname A pathname for the DataProvider within the Machine�s file system.
	 *
	 * @param component identifies the Component that is deployed by this DataProvider. Must not be <code>null</code>.
	 * 
	 * @param machine identifies the Machine on which the DataProvider is deployed. Must not be <code>null</code>.
	 * 
	 * @param isCaseSensitive Indicates whether or not the DataProvider treats lower case letters within object names as being different from the
	 * corresponding upper case letters. Must not be <code>null</code>.
	 *  
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>pathname</code>,
	 * <code>component</code>, <code>machine</code> or <code>isCaseSensitive</code> is <code>null</code>.
	 * 
	 */
	public FunJDBCProvider(
		  String name
		, Visibility visibility
		, String pathname
		, Component component
		, Machine machine
		, Boolean isCaseSensitive
	) {
		super( name, visibility, pathname, component, machine, isCaseSensitive );
	}



	public Boolean addResourceConnection( JDBCConnection jdbcResourceConnection ) {
		return super.addResourceConnectionGeneric( jdbcResourceConnection );
	}



	public Boolean addDataPackage( Catalog catalog ) {
		return super.addDataPackageGeneric( catalog );
	}



	public Boolean addClientConnection( JDBCConnection jdbcConnection ) {
		return addClientConnectionGeneric( jdbcConnection );
	}



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunJDBCProvider" );
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		
		return out.toString();
	}
}