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

public interface Namespace< OWNED extends OwnedElement<? extends Namespace<?>> >
extends
	ModelElement
{


	/**
	 * Removes a value from the reference <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement's visibility attribute states whether the 
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method cancels the registration of this namespace to the removed ownedElement.
	 * </p> 
	 * 
	 * @param ownedElement The element to be removed.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean removeOwnedElement( OwnedElement<?> ownedElement );



	/**
	 * Returns the values of the reference <code>ownedElement</code>.
	 * <p>
	 * A set of ModelElements owned by the namespace. The ModelElement's visibility attribute states whether the element is visible outside the 
	 * namespace.
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
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: ModelElement::namespace</li>
	 * </ul>
	 * 
	 * @return A Collection of owned ModelElements from this namespace. Cannot be <code>null</code> but may be an empty collection.
	 * 
	 */
	Set<OWNED> getOwnedElements();



	/**
	 * Returns the values of the reference <code>ownedElement</code> by Type.
	 * 
	 * @param typeof The type of the owned Elements containt in this namespace.
	 * 
	 * @return A Collection of owned ModelElements from this namespace. Cannot be <code>null</code> but may be an empty 
	 * collection.
	 * 
	 */
	<TYPE extends OWNED> Set<TYPE> getOwnedElementsByType( Class<TYPE> typeof );



	/**
	 * Returns the values of the reference <code>ownedElement</code> by Type.
	 * 
	 * @param typeof The type of the owned Element contained in this namespace.
	 * 
	 * @param name The name of the Element in this namespace.
	 * 
	 * @return A TYPE that matches the name of the param <code>name</code>. Returns <code>null</code> if no matching element by type and name can be 
	 * found.
	 * 
	 */
	<TYPE extends OWNED> TYPE getOwnedElement( Class<TYPE> typeof, String name );



	/**
	 * Returns an ownedElement that ist owned by this namespace, or is owned by any owned namespace that is namespace.
	 * 
	 * @param typeof The type of the owned Element contained in this namespace.
	 * 
	 * @param name The name of the Element in this namespace.
	 * 
	 * @return A TYPE that matches the name of the param <code>name</code>. Returns <code>null</code> if no matching element by type and name can be 
	 * found.
	 * 
	 */	
	<TYPE extends ModelElement> TYPE getOwnedElementDeep( Class<TYPE> typeOf, String qualifiedName );



	/**
	 * Returns an ownedElement that ist owned by this namespace, or is owned by any owned namespace that is namespace.
	 * 
	 * @param typeof The type of the owned Element contained in this namespace.
	 * 
	 * @param name The name of the Element in this namespace.
	 * 
	 * @return A TYPE that matches the name of the param <code>name</code>. Returns <code>null</code> if no matching element by type and name can be 
	 * found.
	 * 
	 */	
	public <TYPE extends ModelElement> TYPE getOwnedElementDeep( 
			  Class<TYPE> typeOf
			, String qualifiedName
			, String separator
			, String surrounding 
	);



	/**
	 * The operation contents results in a Set containing all ModelElements contained by the Namespace.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * contents : Set(ModelElement)
	 * contents = self.ownedElement -> union(self.namespace.contents)
	 * </pre>
	 * 
	 * @return A Set of ModelElements contained by this namespace.
	 * 
	 */
	Set<ModelElement> getContents();



	/**
	 * The operation allContents results in a Set containing all ModelElements contained by the Namespace.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allContents : Set(ModelElement);
	 * allContents = self.contents
	 * </pre>
	 * 
	 * @return A Set of ModelElements contained by this namespace.
	 * 
	 */	
	Set<ModelElement> getAllContents();



	/**
	 * The operation contents results in a Set containing the ModelElements owned by or imported by the Package.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allContentsGeneric( t : oclType ) : Set( oclType )
	 * TODO:
	 * allContentsGeneric( t ) = self.contents( t )-> union( self.ownedElement->select( a | a.ockIsKindOf( t ) )....
	 * </pre>
	 * 
	 * @return A Collection of the elements owned or imported by this package.
	 *  
	 */
	<TYPE extends ModelElement> Set<TYPE> getAllContentsGeneric( Class<TYPE> typeOf );



	/**
	 * The operation contents results in a Set containing the ModelElements owned by or imported by the Package.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * contentsGeneric( t : oclType ) : Set( oclType )
	 * contentsGeneric( t ) = self.ownedElement.oclAsType( t )->union( self.importedElement.oclAsType( t ) )
	 * </pre>
	 * 
	 * @return A Collection of the elements owned or imported by this package.
	 *  
	 */
	<TYPE extends ModelElement> Set<TYPE> getContentsGeneric( Class<TYPE> typeOf );



	/**
	 * The operation allVisibleElements results in a Set containing all ModelElements visible outside of the Namespace.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allVisibleElements : Set(ModelElement)
	 * allVisibleElements = self.allContents -> select(e | e.elementOwnership.visibility = #public)
	 * </pre>
	 *                      
	 * @return A Set of containing ModelElements that were visible outside of this Namespace.
	 * 
	 */	
	Set<ModelElement> getAllVisibleElements();

}