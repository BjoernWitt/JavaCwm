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
package org.ogrehus.jcwm.api.objectmodel.core.util.references.elementownership;

import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;


public interface OwnedElement<NAMESPACE extends Namespace<? extends OwnedElement<?>>>
extends
	ModelElement
{


	/**
	 * Abstract, cause can only be determined, when bounded generic type of NAMESPACE is defined by a non-generic type.
	 * 
	 * @return <code>true</code> if the value for namespace changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean removeNamespace();	



	/**
	 * Designates the Namespace that contains the ModelElement. 
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a 
	 * kind of virtual namespace).
	 * </p>
	 * <p>
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>  
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * </p>
	 * <p>
	 * The ElementOwnership association identifies ModelElements owned by Namespaces. ModelElements may be owned by at most one Namespace. Refer to 
	 * the above discussion of the Namespace class for more information on the nature of the ownership relationship between Namespaces and 
	 * ModelElements.
	 * </p>
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
	 * @param <NAMESPACE> TODO:...
	 * 
	 * @return The designated Namespace of this ModelElement, or <code>null</code>, if none was designated.
	 * 
	 */	
	NAMESPACE getNamespace();



	/**
	 * The operation allSurroundingNamespaces results in a Set containing all surrounding Namespaces.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allSurroundingNamespaces : Set(Namespace)
	 * allSurroundingNamespaces = self.namespace 
	 *                         -> union(self.namespace.allSurroundingNamesapces)
	 * </pre>
	 *                      
	 * @return A Set of containing ModelElements that were visible outside of this Namespace.
	 * 
	 */
	Set<Namespace<?>> getAllSurroundingNamespaces();
}