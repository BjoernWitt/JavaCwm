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
import org.ogrehus.jcwm.api.foundation.softwaredeployment.Machine;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.Catalog;

import org.ogrehus.jcwm.impl.foundation.softwaredeployment.util.generic.GFunDataManager;

/**
 * A JDBCManager represents a DeployedComponent that manages access to JDBC data. 
 * 
 * <p>
 * For example, a deployed DBMS or File Management System would be represented as a DataManager.
 * </p>
 * <p>
 * The DataManager may be associated with one or more data Packages identifying the Schema, Relational Catalog, Files, or other data container that it
 * provides access to.
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
 */
public class FunJDBCManager
extends
	GFunDataManager<Catalog, JDBCConnection>
implements
	JDBCManager
{



	/**
	 * Creates a new instance of JDBCManager by specific parameters.
	 * 
	 * <p>
	 * A JDBCManager represents a DeployedComponent that manages access to JDBC data.
	 * </p>
	 * 
	 * @param name An identifier for the JDBCManager within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the JDBCManager within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param pathname A pathname for the JDBCManager within the Machine file system.
	 *
	 * @param component identifies the Component that is deployed by this JDBCManager. Must not be <code>null</code>.
	 * 
	 * @param machine identifies the Machine on which the JDBCManager is deployed. Must not be <code>null</code>.
	 * 
	 * @param isCaseSensitive Indicates whether or not the JDBCManager treats lower case letters within object names as being different from the
	 * corresponding upper case letters. Must not be <code>null</code>.
	 *  
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>pathname</code>,
	 * <code>component</code>, <code>machine</code> or <code>isCaseSensitive</code> is <code>null</code>.
	 * 
	 */
	public FunJDBCManager(
		  String name
		, Visibility visibility
		, String pathname
		, Component component
		, Machine machine
		, Boolean isCaseSensitive
	) {
		super( name, visibility, pathname, component, machine, isCaseSensitive );
	}



	public Boolean addDataPackage( Catalog catalog ) {
		return super.addDataPackageGeneric( catalog );
	}



	public Boolean addClientConnection( JDBCConnection jdbcConnection ) {
		return super.addClientConnectionGeneric( jdbcConnection );
	}
}