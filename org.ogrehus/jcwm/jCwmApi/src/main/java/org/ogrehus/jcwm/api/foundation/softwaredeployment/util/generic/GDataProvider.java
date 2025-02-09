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
package org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic;

import java.util.Set;

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
public interface GDataProvider<
	  DATA_PACKAGE extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CONNECTION   extends GProviderConnection<?, ?, ?>
> extends
	  GDataManager<DATA_PACKAGE, CONNECTION>
{


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
	Set<CONNECTION> getResourceConnections();



	CONNECTION getResourceConnection( String simpleName );



	Boolean addResourceConnection( CONNECTION resourceConnection );



	Boolean removeResourceConnection( GProviderConnection<?, ?, ?> resourceConnection );
}