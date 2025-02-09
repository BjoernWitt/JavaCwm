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
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModel;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;


/**
 * A model captures a view of a physical system. 
 * 
 * <p>
 * It is an abstraction of the physical system, with a certain purpose. The model completely describes those aspects of the physical system that are 
 * relevant to the purpose of the model, at the appropriate level of detail.
 * </p>
 * <p>
 * In the meta-model Model is a subclass of Package. It contains a containment hierarchy of ModelElements that together describe the physical system. 
 * A Model also contains a set of ModelElements that represents the environment of the system.
 * </p>
 * <p>
 * Different Models can be defined for the same physical system, where each model represents a view of the physical system defined by its purpose and 
 * abstraction level; for example, an analysis model, a design model, an implementation model. Typically different models are complementary and 
 * defined from the perspectives (viewpoints) of different system stakeholders.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Model
extends
	GModel<
		  CwmManager
		, Model
		, Model
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
		, GModelElement<?, ?, ?, ?>
	>
{


//======================================================================================================================
// GModelElement capabilities	
//======================================================================================================================



	/**
	 * Designates the Namespace that contains the Model.
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
	 * @param model The new namespace that contains this Model.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */	
	Model setNamespace( Model model );



	/**
	 * Adds an importer (Package) to this Model, that will contain this Model as imported.
	 * 
	 * @param model The specific package, that will contain this Model.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( Model model );



	/**
	 * Adds a dependency to this Model.
	 * 
	 * @param dependency A Dependency in witch this Model is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this Model.
	 * 
	 * @param constraint A Constrain that must be satisfied by this Model.
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
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */	
	Boolean addOwnedElement( GCwmPackage<?, ?, ?, ?, ?, ?, ?> ownedPackage );



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
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( GClassifier<?, ?, ?, ?, ?, ?> ownedClassifier );



	/**
	 * Adds a Dependency to the reference of <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement's visibility attribute states whether the 
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this type of namespace to the new ownedElement for inverse traversal.
	 * </p> 
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */	
	Boolean addOwnedElement( GDependency<?, ?, ?, ?, ?> ownedDependency );



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
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */	
	Boolean addOwnedElement( GConstraint<?, ?, ?, ?, ?> ownedConstraint );



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