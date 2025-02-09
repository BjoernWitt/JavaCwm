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

import org.ogrehus.jcwm.api.foundation.keyindexes.Index;
import org.ogrehus.jcwm.api.foundation.keyindexes.KeyRelationship;
import org.ogrehus.jcwm.api.foundation.keyindexes.UniqueKey;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;

import org.ogrehus.jcwm.api.objectmodel.relationships.Association;

import org.ogrehus.jcwm.api.objectmodel.behavioral.Interface;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;


/**
 * A class is a description of a set of objects that share the same attributes, operations, methods, relationships, and semantics.
 *  
 * <p>
 * A class may use a set of interfaces to specify collections of operations it provides to its environment. In the meta-model, a Class describes a
 * set of objects sharing a collection of Features that are common to the set of objects.
 * </p>
 * <p>
 * The purpose of a Class is to declare a collection of Features that fully describe the structure and behavior of objects. Some Classes may not be
 * directly instantiated. These Classes are said to be abstract and exist only for other Classes to inherit and reuse the Features declared by them.
 * No object may be a direct instance of an abstract Class, although an object may be an indirect instance of one through a subclass that is none
 * abstract.
 * </p>
 * <p>
 * A Class acts as the namespace for various kinds of contained elements defined within its scope, including classes, interfaces, and associations
 * (note that this is purely a scoping construction and does not imply anything about aggregation). The contained classes can be used as ordinary
 * classes in the container class. If a class inherits another class, the contents of the ancestor are available to its descendants if the visibility
 * of an element is public or protected. If the visibility is private, then the element is not visible and therefore not available in the descendant.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface CwmClass
extends
	GCwmClass<
		  GNamespace<?, ?, ?, ?, ?>
		, CwmPackage
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
		, GFeature<?, ?, ?, ?, ?>
		, Index
	>
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the Class.
	 * 
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a
	 * kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: CwmClass</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: CwmClass::ownedElement</li>
	 * </ul>
	 * 
	 * @param cwmClass The new namespace that contains this Class.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */
	GNamespace<?, ?, ?, ?, ?> setNamespace( CwmClass cwmClass );



	/**
	 * Designates the Namespace that contains the Class.
	 * 
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a
	 * kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: CwmPackage</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: CwmPackage::ownedElement</li>
	 * </ul>
	 * 
	 * @param cwmPackage The new namespace that contains this Class.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */
	GNamespace<?, ?, ?, ?, ?> setNamespace( CwmPackage cwmPackage );



	/**
	 * Designates the Namespace that contains the Class.
	 * 
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a
	 * kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: Association</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Association::ownedElement</li>
	 * </ul>
	 * 
	 * @param association The new namespace that contains this Class.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */
	GNamespace<?, ?, ?, ?, ?> setNamespace( Association association );



	/**
	 * Adds an importer (Package) to this CwmClass, that will contain this CwmClass as imported.
	 * 
	 * @param cwmPackage The specific package, that will contain this CwmClass.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( CwmPackage cwmPackage );



	/**
	 * Adds a dependency to this CwmClass.
	 * 
	 * @param dependency A Dependency in witch this CwmClass is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );		



	/**
	 * Adds a constraint to this CwmClass.
	 * 
	 * @param constraint A Constrain that must be satisfied by this CwmClass.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// GNamespace capabilities
//====================================================================================================================================================



	/**
	 * Adds an Interface to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this CwmClass. The ModelElement visibility attribute states whether the element
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this CwmClass to the Interface for inverse traversal.
	 * </p> 
	 * 
	 * @param ownedInterface of this CwmClass.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( Interface ownedInterface );



	/**
	 * Adds a Class to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this CwmClass. The ModelElement visibility attribute states whether the element
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this CwmClass to the CwmClass for inverse traversal.
	 * </p>
	 * 
	 * @param ownedClass of this CwmClass.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( CwmClass ownedClass );



	/**
	 * Adds an Association to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this CwmClass. The ModelElement's visibility attribute states whether the element
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this CwmClass to the Association for inverse traversal.
	 * </p>
	 * 
	 * @param ownedAssociation of this CwmClass.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( Association ownedAssociation );



	/**
	 * Adds an Association to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this CwmClass. The ModelElement visibility attribute states whether the element
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this CwmClass to the Association for inverse traversal.
	 * </p> 
	 * 
	 * @param ownedKeyRelationship of this CwmClass.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( KeyRelationship ownedKeyRelationship );



	/**
	 * Adds an Association to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this CwmClass. The ModelElement's visibility attribute states whether the element
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this CwmClass to the Association for inverse traversal.
	 * </p>
	 * 
	 * @param ownedUniqueKey of this CwmClass.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addOwnedElement( UniqueKey ownedUniqueKey );



//====================================================================================================================================================
// Classifier capabilities
//====================================================================================================================================================



	/**
	 * Appends an Attribute that will be owned by this CwmClass as a feature.
	 * 
	 * @param attribute that will be owned by this CwmClass as a feature.
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addFeature( Attribute attribute );



	/**
	 * Appends an Operation that will be owned by this CwmClass as a feature.
	 * 
	 * @param operation that will be owned by this CwmClass as a feature.
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addFeature( Operation operation );



//====================================================================================================================================================
// CwmClass capabilities
//====================================================================================================================================================



	/**
	 * Adds an Index to the reference of <code>indexes</code> that span this Class instance.
	 * 
	 * @param index of this CwmClass.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addIndex( Index index );
}