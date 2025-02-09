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
package org.ogrehus.jcwm.api.objectmodel.core.util.references.dependencyclient;

import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;

public interface ClientDependency<DEPENDENCY extends Clientable<? extends ClientDependency<?>>>
extends
	ModelElement
{


	/**
	 * Removes a dependency from this ModelElement.
	 * 
	 * @param dependency A Dependency that will be removed from this ModelELement.
	 * 
	 * @return <code>true</code> if the value for owner changed as a result of the call, <code>false</code> otherwise.
	 */
	Boolean removeDependency( Clientable<?> dependency ); 



	/**
	 * Inverse of client.
	 * <p> 
	 * Designates a set of Dependencies in which the ModelElement is a client.
	 * </p>
	 * <p>
	 * The DependencyClient and DependencySupplier association links Dependency instances with ModelElements that act as clients in the represented 
	 * dependency relationship.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>   
	 * <ul>
	 * <li><i>class</i>			: Dependency</li>
	 * <li><i>defined by</i>	: DependencyClient::clientDependency</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: Dependency::client</li>
	 * </ul>
	 * 
	 * @return A Set of all dependencies in which this ModelElement is a client.
	 *   
	 */	
	Set<DEPENDENCY> getDependencies();



	/**
	 * The operation supplier results in a Set containing all direct suppliers of the ModelElement.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * supplier : Set(ModelElement);
	 * supplier = self.clientDependency.supplier
	 * </pre>
	 * 
	 * @return A Set of all direct suppliers of this ModelElement.
	 * 
	 */
	Set<ModelElement> getSuppliers();



	/**
	 * The operation allSuppliers results in a Set containing all the ModelElements that are suppliers of this ModelElement, including the suppliers 
	 * of these Model Elements. 
	 * <p>
	 * This is the transitive closure.
	 * </p>
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allSuppliers : Set(ModelElement);
	 * allSuppliers = self.supplier->union(self.supplier.allSuppliers)
	 * </pre>
	 * 
	 * @return A Set containing all ModelElements that are suppliers of this ModelElement, including the suppliers of these ModelElements.
	 *  
	 */
	Set<ModelElement> getAllSuppliers();
}
