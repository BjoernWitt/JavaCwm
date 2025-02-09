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

import org.ogrehus.jcwm.api.foundation.softwaredeployment.ProviderConnection;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;


/**
 * A ProviderConnection represents a connection that allows a DataProvider acting as a client to access data from a specific DataManager.
 *  
 * <p>
 * For example a ProviderConnection could represent a connection from an ODBC or JDBC client to a DBMS.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-8-2]</b> A ProviderConnection must not associate a DataProvider with itself.</li>
 * </ul>  
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <MANAGER> Type of the corresponding DataManager.
 * @param <PROVIDER> Type of the provider, might be JDBC, ODBC, FileSystem any other suitable one.
 * @param <USAGE> Type of the packageUsage as the role of the a dependency of this ProviderConnecion
 * 
 */
public interface GProviderConnection<
	  MANAGER    extends GDataManager<?, ?>
	, PROVIDER   extends GDataProvider<?, ?>
	, USAGE      extends GPackageUsage<?>
>extends
	  GModelElement<SoftwareSystem, SoftwareSystem, USAGE, Constraint> 
	, ProviderConnection	
{


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
	 * @return The DataProvider that is the client of the ProviderConnection.
	 * 
	 */
	PROVIDER getDataProvider();



	/**
	 * Identifies the DataProvider that is the client of the ProviderConnection.
	 *
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-8-2]</b> A ProviderConnection must not associate a DataProvider
	 * with itself.</li>
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
	 * @param dataProvider The DataProvider that is the client of the ProviderConnection. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>dataProvider</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>dataProvider</code> is <code>null</code>. 
	 * 
	 */
	PROVIDER setDataProvider( PROVIDER dataProvider );



	Boolean removeDataProvider();



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
	 * @return The DataManager that is accessed by the ProviderConnection.
	 * 
	 */
	MANAGER getDataManager();



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
	 * @param dataManager The DataManager that is accessed by the ProviderConnection. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>dataManager</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>dataManager</code> is <code>null</code>. 
	 * 
	 */
	MANAGER setDataManager( MANAGER dataManager );



	Boolean removeDataManager();
}