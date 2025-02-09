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

public interface Clientable<CLIENT extends ClientDependency<? extends Clientable<?>>>
extends
	ModelElement
{


	/**
	 * Removes a modelElement that acts as a client in the dependency.
	 * 
	 * <p>
	 * <b>Remind:</b> If only one client is left in this dependency, it won't be removed, cause a dependency must have at least one client to be 
	 * valid!
	 * </p>
	 * 
	 * @param client that will no longer be affect by a supplier thru this Dependency.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeClient( ClientDependency<?> client );



	/**
	 * Returns the element that is affected by the supplier element. In some cases the direction is unimportant and serves only to distinguish the 
	 * two elements.
	 * <p>
	 * The DependencyClient association links Dependency instances with ModelElements that act as clients in the 
	 * represented dependency relationship.
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: DependencyClient::client</li>
	 * <li><i>multiplicity</i>	: one or more</li>
	 * <li><i>inverse</i>		: ModelElement::clientDependency</li>
	 * </ul>
	 * 
	 * @return A Collection of clients that are defined for this dependency. Can't be <code>null</code>.
	 * 
	 */	
	Set<CLIENT> getClients();



	/**
	 * Adds a supplier to the this Dependency.
	 * 
	 * @param supplier A supplier is the more general element in a directed dependency to a client and will be added.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addSupplier( ModelElement supplier );



	/**
	 * Removes a supplier from the this Dependency.
	 * 
	 * <p>
	 * <b>Remind:</b> If only one supplier is left in this dependency, it won't be removed, cause a dependency must have at least one supplier to be 
	 * valid!
	 * </p>
	 * 
	 * @param supplier A supplier is the more general element in a directed dependency to a client and will be removed.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean removeSupplier( ModelElement supplier );



	/**
	 * Returns the Collection of containing 
	 * <p>  
	 * Designates the element that is unaffected by a change. In a two-way relationship this would be the more general element. In an undirected 
	 * situation the choice of client and supplier may be irrelevant.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: DependencySupplier::supplier</li>
	 * <li><i>multiplicity</i>	: one or more</li>
	 * <li><i>inverse</i>		: ModelElement::supplierDependency</li>
	 * </ul>
	 * 
	 * @return A Set of all direct suppliers of this ModelElement.
	 * 
	 */
	Set<ModelElement> getSuppliers();
}