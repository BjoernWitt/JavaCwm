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
package org.ogrehus.jcwm.api.objectmodel.core;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.CwmManager;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;


/**
 * A package is a grouping of model elements.
 * 
 * <p>
 * In the meta-model Package is a subclass of Namespace. A Package contains ModelElements such as Packages and Classifiers. A Package may also contain
 * Constraints and Dependencies between ModelElements of the Package.
 * </p>
 * <p>
 * The purpose of the package construct is to provide a general grouping mechanism. In fact, its only semantics is to define a namespace for its 
 * contents. The package construct can be used for organizing elements for any purpose; the criteria to use for grouping elements together into one
 * package are not defined.
 * </p>
 * <p>
 * A package owns a set of model elements, with the implication that if the package is removed from the model, so are the elements owned by the
 * package. Elements with names, such as classifiers, that are owned by the same package must have unique names within the package, although elements
 * in different packages may have the same name.
 * </p>
 * <p>
 * There may be relationships between elements contained in the same package, and between an element in one package and an element in a surrounding
 * package at any level. In other words, elements see all the way out through nested levels of packages. Elements in peer packages, however, are
 * encapsulated and are not a priori visible to each other. The same goes for elements in contained packages; that is, packages do not see inwards.
 * </p>
 * <p>
 * Elements owned by a Package can be made available to other Packages by importing them. Although any ModelElement may be imported by a Package,
 * imported ModelElements are typically other Packages. When an element is imported by a package it extends the namespace of that package. Thus the
 * elements available in a Package consists of its owned and imported ModelElements.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * 
 * @see org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage
 * 
 */
public interface CwmPackage
extends
	GCwmPackage<
		  CwmManager
		, CwmPackage
		, CwmPackage
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
		, GModelElement<?, ?, ?, ?>
	>
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the CwmPackage.
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a
	 * kind of virtual namespace).
	 * </p>
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
	 * @param cwmPackage The new namespace that contains this CwmPackage.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */
	CwmPackage setNamespace( CwmPackage cwmPackage );



	/**
	 * Adds an importer (Package) to this CwmPackage, that will contain this CwmPackage as imported.
	 * 
	 * @param cwmPackage The specific package, that will contain this CwmPackage.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( CwmPackage cwmPackage );



	/**
	 * Adds a dependency to this CwmPackage.
	 * 
	 * @param dependency A Dependency in witch this CwmPackage is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this CwmPackage.
	 * 
	 * @param constraint A Constrain that must be satisfied by this CwmPackage.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// GNamespace capabilities
//====================================================================================================================================================



	/**
	 * Adds a Package to the reference of <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement's visibility attribute states whether the 
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this type of namespace to the new ownedElement for inverse traversal.
	 * </p> 
	 *
	 * @param cwmPackage The instance that will be assigned as an ownedElememnt to this type of namespace.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( GCwmPackage<?, ?, ?, ?, ?, ?, ?> cwmPackage );



	/**
	 * Adds a Classifier to the reference of <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement's visibility attribute states whether the 
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this type of namespace to the new ownedElement for inverse traversal.
	 * </p>
	 * 
	 * @param classifier The instance that will be assigned as an ownedElememnt to this type of namespace.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( GClassifier<?, ?, ?, ?, ?, ?> classifier );



	/**
	 * Adds a Dependency to the reference of <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this type of namespace to the new ownedElement for inverse traversal.
	 * </p>
	 * 
	 * @param dependency The instance that will be assigned as an ownedElememnt to this type of namespace.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( GDependency<?, ?, ?, ?, ?> dependency );



	/**
	 * Adds a Constraint to the reference of <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the 
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this type of namespace to the new ownedElement for inverse traversal.
	 * </p> 
	 * 
	 * @param constraint The instance that will be assigned as an ownedElememnt to this type of namespace.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( GConstraint<?, ?, ?, ?, ?> constraint );



//====================================================================================================================================================
// Package capabilities
//====================================================================================================================================================



	Boolean addDataManager( CwmManager cwmManager );



	/**
	 * Adds an importable ModelElement to this Package.
	 * 
	 * @param importedElement The new ModelElement that will enter the extended Namespace of a Package.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImportedElement( GModelElement<?, ?, ?, ?> importedElement );
}