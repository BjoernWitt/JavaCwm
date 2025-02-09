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

import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;

/**
 * A PackageUsage represents a usage of a Package. It is particularly relevant in situations where a specific usage uses an alternative name for the 
 * Package, as this alternative name can be recorded using the packageAlias attribute.
 * <p>
 * For example, if a DataProvider representing an ODBC or JDBC client uses a name for a relational database that is different from the dataPackage 
 * name used by the RDBMS server, a PackageUsage that has the relevant ProviderConnection as client and the server data Package as supplier can be 
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
public interface PackageUsage
extends
	ModelElement
{


	/**
	 * If this attribute is present, it identifies the name by which the Package is known to the client.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * <p>
     * <b>Constraints</b>
     * </p>
     * <ul>
     * <li><b>[C-8-1]</b> A PackageUsage must have a single Package (or subtype of Package) as its supplier.</li>
     * </ul>
     * 
	 * @return A Collection of aliases that identifies the names by which the  Package is known to the client. Can't be <code>null</code>, but an 
	 * empty Set.
	 * 
	 */ 
	Set<String> getPackageAlias();



	Boolean addPackageAlias( String packageAlias );



	Boolean removePackageAlias( String packageAlias );
}