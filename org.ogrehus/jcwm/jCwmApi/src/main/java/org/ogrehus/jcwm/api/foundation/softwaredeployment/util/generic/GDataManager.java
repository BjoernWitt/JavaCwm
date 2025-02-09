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

import org.ogrehus.jcwm.api.foundation.softwaredeployment.*;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;


/**
 * A DataManager represents a DeployedComponent that manages access to data. 
 * 
 * <p>
 * For example, a deployed DBMS or File Management System would be represented as a DataManager.
 * </p>
 * <p>
 * The DataManager may be associated with one or more data Packages identifying the Schema, Relational Catalog, Files, or other data container that 
 * it provides access to.
 * </p>
 * 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <DATA_PACKAGE> Type of the Package that will contain the definition of the data made avail by the DataManager.
 * @param <CONNECTION> Type of the corrsponding ProviderConnection of this DataManager.
 * 
 */
public interface GDataManager<
	  DATA_PACKAGE extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CONNECTION   extends GProviderConnection<?, ?, ?>
> extends
	DataManager
{


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
	Set<DATA_PACKAGE> getDataPackages();



	DATA_PACKAGE getDataPackage( String simpleName );



	Boolean addDataPackage( DATA_PACKAGE dataPackage );



	Boolean removeDataPackage( GCwmPackage<?, ?, ?, ?, ?, ?, ?> dataPackage );



	Set<CONNECTION> getClientConnections();



	Boolean addClientConnection( CONNECTION connection );



	Boolean removeClientConnection( GProviderConnection<?, ?, ?> connection );
}