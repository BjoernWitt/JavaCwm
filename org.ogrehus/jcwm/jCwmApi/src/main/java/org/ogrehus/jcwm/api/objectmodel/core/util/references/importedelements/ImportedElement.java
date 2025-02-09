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
package org.ogrehus.jcwm.api.objectmodel.core.util.references.importedelements;

import java.util.Collection;
import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;

public interface ImportedElement<IMPORTED extends Importer<? extends ImportedElement<?>>>
extends
	ModelElement
{


	/**
	 * Removes an importable ModelElement from this Package.
	 * 
	 * @param importedElement The new ModelElement that will enter the extended Namespace of a Package.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeImportedElement( Importer<?> importedElement );



	/**
	 * Returns a reference to the importedElement.
	 * <p>
	 * The Namespace defined by the package is extended by ModelElements imported from other packages.
	 * </p>
	 * <p>
	 * The ImportedElements association identifies ModelElements that a Package instance imports from other Namespaces. Although any ModelElement may
	 * be imported by a Package, imported ModelElements are typically other Packages or aggregate Classifiers, such as Class instances.
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: ImportedElements::importedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: ModelElement::importer</li>
	 * </ul>
	 * 
	 * @param <MODEL_ELEMENT> Type of the imported elements in a grouped package.
	 * 
	 * @return A Collection of the elements enveloped by this Package. Will return an empty Collection if none was assigned.
	 * 
	 */
	Collection<IMPORTED> getImportedElements();



	/**
	 * Returns all ownedElements from this Namespace by a specific type.
	 * <p>
	 * A set of ModelElements owned by the namespace. The ModelElement visibility attribute states whether the element is visible outside the
	 * namespace.
	 * </p>
	 * 
	 * @param classOfElements All returned elements implement or extends this class.
	 * 
	 * @return A Collection of owned ModelElements from this namespace by a specific type. Can't be <code>null</code> but may be an empty collection,
	 * if none is available.
	 * 
	 */
	<E extends IMPORTED> Collection<E> getImportedElements( Class<E> classOfElements );



	/**
	 * The operation allImportedElements results in a Set containing the ModelElements imported by the Package.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allImportedElements : Set(ModelElement)
	 * allImportedElements = self.importedElement
	 * </pre>
	 * 
	 * @return A Set containing the ModelElements imported by the Package.
	 * 
	 */
	Set<GModelElement<?, ?, ?, ?>> getAllImportedElements();



	/**
	 * The operation contents results in a Set containing the ModelElements owned by or imported by the Package.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * contents : Set(ModelElement)
	 * contents = self.ownedElement->union(self.importedElement)
	 * </pre>
	 * 
	 * @return A Collection of the elements owned or imported by this package.
	 * 
	 */
	Set<ModelElement> getContents();



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
	 * The operation allContents results in a Set containing the ModelElements owned by or imported by the Package.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allContents : Set(ModelElement)
	 * allContents = self.contents
	 * </pre>
	 * 
	 * @see #getContents() For more detail on the content.
	 * 
	 * @return A Collection of the elements owned or imported by this package.
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
	 * allContentsGeneric( t ) = self.contents( t )
	 * </pre>
	 * 
	 * @return A Collection of the elements owned or imported by this package.
	 * 
	 */
	<TYPE extends ModelElement> Set<TYPE> getAllContentsGeneric( Class<TYPE> typeOf );
}