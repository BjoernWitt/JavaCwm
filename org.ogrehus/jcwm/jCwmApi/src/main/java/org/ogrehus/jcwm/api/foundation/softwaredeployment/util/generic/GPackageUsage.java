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

import org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;

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
 * @param CLIENT Type of the specific client in the clientDependency relation.
 * 
 */
public interface GPackageUsage<
	CLIENT extends GProviderConnection<?, ?, ?>
> extends
	GDependency<
		  SoftwareSystem
		, SoftwareSystem
		, Dependency
		, Constraint
		, CLIENT
	> 
{


	//================================================================================================================================================
	// GModelElement capabilities	
	//================================================================================================================================================



	/**
	 * Designates the Namespace that contains the ModelElement. Every Model Element except a root element must belong to exactly one Namespace or 
	 * else be a composite part of another ModelElement (which is a kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>  
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: Namespace</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Namespace::ownedElement</li>
	 * </ul>
	 * 
	 * @param softwareSystem The new namespace that contains this ModelElement.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before. 
	 * 
	 */	
	SoftwareSystem setNamespace( SoftwareSystem softwareSystem );	



	/**
	 * Adds a dependency to this Dependency.
	 * 
	 * @param dependency A Dependency in witch this Dependency is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );	



	/**
	 * Adds an importer (Package) to this Dependency, that will contain this Dependency as imported.
	 * 
	 * @param softwareSystem The specific package, that will contain this Dependency.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( SoftwareSystem softwareSystem );



	/**
	 * Adds another Constraint to this Dependency.
	 * 
	 * @param constraint A Constrain that must be satisfied by this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );
}