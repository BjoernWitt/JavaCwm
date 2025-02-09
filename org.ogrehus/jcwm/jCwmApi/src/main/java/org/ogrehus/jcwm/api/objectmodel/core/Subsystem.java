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
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GSubsystem;


/**
 * A subsystem is a grouping of model elements that represents a behavioral unit in a physical system. 
 * <p>
 * A subsystem offers interfaces and has operations. In the meta-model, Subsystem is a subclass of both Package and Classifier. As such it may have a 
 * set of Features. 
 * </p>
 * <p>
 * The purpose of the subsystem construct is to provide a grouping mechanism for specifying a behavioral unit of a physical system. Apart from 
 * defining a namespace for its contents, a subsystem serves as a specification unit for the behavior of its contained model elements. 
 * </p>
 * <p>
 * The contents of a subsystem is defined in the same way as for a package, thus it consists of owned elements and imported elements, with unique 
 * names within the subsystem.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Subsystem
extends
    GSubsystem<
          CwmManager
		, CwmPackage
	  	, CwmPackage
	  	, Dependency
	  	, Constraint
        , GModelElement<?, ?, ?, ?>
        , GModelElement<?, ?, ?, ?>
        , GFeature<?, ?, ?, ?, ?>
    >
	, CwmPackage 
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



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
	 * @param cwmPackage The new namespace that contains this ModelElement.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before. 
	 * 
	 */	
	CwmPackage setNamespace( CwmPackage cwmPackage );



	/**
	 * Adds an importer (Package) to this ModelElement, that will contain this ModelElement as imported.
	 * 
	 * @param cwmPackage The specific package, that will contain this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( CwmPackage cwmPackage );



	/**
	 * Adds a dependency to this ModelElement.
	 * 
	 * @param dependency A Dependency in witch this ModelELement is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this element.
	 * 
	 * <p>
	 * <b>Constraints:</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C 3-1]</b> A Constraint cannot be applied to itself.
	 * <pre>
	 * <b>context</b> Constraint <b>inv</b>:
	 *   <b>not</b> self.constrainedElement -> includes( self )
	 * </pre></li>
	 * </ul> 
	 * 
	 * @param constraint A Constrain that must be satisfied by this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws ConstraintViolation if the Constraint <b>[C 3-1]</b> was violated, caused by registering of bidirectional reference. 
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
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the element
	 * is visible outside the namespace.
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
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the element
	 * is visible outside the namespace.
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
	 * Adds an importable ModelElement to this Subsystem.
	 * 
	 * @param importedElement The new ModelElement that will enter the extended Namespace of this Subsystem.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImportedElement( GModelElement<?, ?, ?, ?> importedElement );



//====================================================================================================================================================
// Classifier capabilities	
//====================================================================================================================================================



	/**
	 * Appends a Feature that will be owned by this Subsystem.
	 * 
	 * @param feature that will be owned by this Subsystem. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addFeature( GFeature<?, ?, ?, ?, ?> feature );
}