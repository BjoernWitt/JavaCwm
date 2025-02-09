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
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataProvider;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GProviderConnection;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;

/**
 * A DataProvider is a deployed software Component that acts as a client to provide access to data that is managed by another product.
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
 * @param DATA_PACKAGE 
 * @param CONNECTION 
 * 
 */
public abstract class GFunDataProvider<
	  DATA_PACKAGE extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CONNECTION   extends GProviderConnection<?, ?, ?>
> extends
	GFunDataManager<DATA_PACKAGE, CONNECTION>
implements
	GDataProvider<DATA_PACKAGE, CONNECTION>
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
	public GFunDataProvider(
		  String name
		, Visibility visibility
		, String pathname
		, Component component
		, Machine machine
		, Boolean isCaseSensitive
	) {
		super( name, visibility, pathname, component, machine, isCaseSensitive );
		this.resourceConnections = new HashSet<CONNECTION>();
	}



	/**
	 * Identifies the ProviderConnections that the DataProvider may use to access data resources.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>       : ProviderConnection</li>
	 * <li><i>defined by</i>  : DataProviderConnections::resourceConnection</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * <li><i>inverse</i>     : ProviderConnection::dataProvider</li>
	 * </ul>
	 *
	 * @return A Collection of the the the ProviderConnections that the DataProvider may use to access data resources. Can't be <code>null</code>,
	 * but empty.
	 * 
	 */
	public Set<CONNECTION> getResourceConnections() {
		return this.resourceConnections;
	}



	public CONNECTION getResourceConnection( String simpleName ) {
		if ( simpleName == null ) {
			return null;
		}
		
		for ( CONNECTION connection : this.resourceConnections ) {
			if ( simpleName.equals( connection.getSimpleName() ) ) {
				return connection;
			}
		}

		return null;
	}



	protected Boolean addResourceConnectionGeneric( CONNECTION resourceConnection ) {
		if ( resourceConnection != null && !this.resourceConnections.contains( resourceConnection ) ) {
			this.resourceConnections.add( resourceConnection );
			invokeByReflection( resourceConnection, "setDataProvider", this ); // bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeResourceConnection( GProviderConnection<?, ?, ?> resourceConnection ) {
		if ( resourceConnection == null ) {
			return Boolean.TRUE;
		}

		if ( this.resourceConnections.remove( resourceConnection ) ) {
			resourceConnection.removeDataProvider(); // remove bi-directional reference
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunDataProvider" );
		if ( !this.resourceConnections.isEmpty() ) {
			out.append( " | resourceConnections(" + this.resourceConnections.size() + ")={" );
			boolean comma = false;
			for ( CONNECTION resourceConnection : this.resourceConnections ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( resourceConnection.getSimpleName() );
				out.append( "<" + resourceConnection.getClass().getSimpleName() + ">" );
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
	 * Identifies the ProviderConnections that the DataProvider may use to access data resources.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>       : ProviderConnection</li>
	 * <li><i>defined by</i>  : DataProviderConnections::resourceConnection</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * <li><i>inverse</i>     : ProviderConnection::dataProvider</li>
	 * </ul>
	 * 
	 */
	protected Set<CONNECTION> resourceConnections = null;
}