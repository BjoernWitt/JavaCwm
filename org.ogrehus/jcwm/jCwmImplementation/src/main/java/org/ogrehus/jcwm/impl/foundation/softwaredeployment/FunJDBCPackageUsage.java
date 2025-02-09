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

import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCConnection;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCPackageUsage;
import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

import org.ogrehus.jcwm.impl.foundation.softwaredeployment.util.generic.GFunPackageUsage;


/**
 * A PackageUsage represents a usage of a Package. It is particularly relevant in situations where a specific usage uses an alternative name for the
 * Package, as this alternative name can be recorded using the packageAlias attribute.
 * <p>
 * For example, if a DataProvider representing an ODBC or JDBC client uses a name for a relational database that is different from the dataPackage
 * name used by the RDBMS server, a PackageUsage that has the relevant ProviderConnection as client and the server�s data Package as supplier can be
 * added. Its packageAlias attribute can be used to record the name by which the data Package is known to the DataProvider.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-8-1]</b> A PackageUsage must have a single Package (or subtype of Package) as its supplier.</li>
 * </ul>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunJDBCPackageUsage
extends
	GFunPackageUsage<JDBCConnection>
implements 
	JDBCPackageUsage
{


	/**
	 * Factory method that creates a new instance of PackageUsage by specific parameters.
	 * 
	 * @param name An identifier for the PackageUsage within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the PackageUsage within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param client The element that is affected by the supplier element. In some cases the direction is unimportant and serves only to distinguish
	 * the two elements. Must not be <code>null</code>.
	 * 
	 * @param supplier Inverse of client. Designates the element that is unaffected by a change. In a two-way relationship this would be the more
	 * general element. In an undirected situation the choice of client and supplier may be irrelevant. Must not be <code>null</code>.
	 *   
	 * @throws NullPointerException Is thrown if one of the parameters <code>name</code>, <code>visibility</code>, <code>client</code> or
	 * <code>supplier</code> was <code>null</code>.
	 * 
	 */
	protected FunJDBCPackageUsage( String name, Visibility visibility, JDBCConnection client, ModelElement supplier ) {
		super( name, visibility, client, supplier );
	}



	public Boolean addClient(JDBCConnection jdbcConnection) {
		throw new UnsupportedOperationException("Not supported yet.");
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================
	
}