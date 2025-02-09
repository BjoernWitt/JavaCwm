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
package org.ogrehus.jcwm.api.objectmodel.behavioral;

import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;

import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;


/**
 * Interface is a named set of operations that specify the behavior of an element.
 * <p>
 * In the meta-model, an Interface contains a set of Operations that together define a service offered by a Classifier realizing the Interface. A 
 * Classifier may offer several services, which means that it may realize several Interfaces, and several Classifiers may realize the same Interface.
 * </p>
 * <p>
 * <b>Constraints</b>:
 * </p>
 * <ul>
 * <li><b>[C-4-4]</b> An Interface can only contain Operations.</li>
 * <li><b>[C-4-5]</b> An Interface cannot contain any ModelElements.</li>
 * <li><b>[C-4-6]</b> All Features defined in an Interface are public.</li>
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Interface
extends
	GClassifier<
		  CwmClass
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
		, Operation
	> 
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the CwmClass (Namespace) that contains this Interface. 
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
	 * @param cwmClass The new namespace that contains this ModelElement.
	 * 
	 * @return The CwmClass that contains this Interface before, or <code>null</code> none was assigned before.
	 * 
	 */
	CwmClass setNamespace( CwmClass cwmClass );



	/**
	 * Adds an importer (Package) to this Interface, that will contain this Interface as imported.
	 * 
	 * @param importer The specific package, that will contain this Interface.
	 * 
	 * @return <code>true</code> if this Set changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer );



	/**
	 * Adds a dependency to this Interface.
	 * 
	 * @param dependency A Dependency in witch this Interface is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this Interface.
	 * 
	 * @param constraint A Constrain that must be satisfied by this Interface.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// GNamespace capabilities
//====================================================================================================================================================



	/**
	 * The operation contents results in an empty Set.
	 * <p>
	 * <b>Constraint:</b>:
	 * <ul>
	 * <li><b>[C-4-5]</b> An Interface cannot contain any ModelElements.<pre>
	 * <b>context</b> Interface <b>inv</b>:
	 * self.allContents -> isEmpty
	 * </pre>
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @return A Set of ModelElements contained by this namespace.
	 * 
	 */
	Set<ModelElement> getContents();



//====================================================================================================================================================
// Classifier capabilities
//====================================================================================================================================================



	/**
	 * Appends a Feature that will be owned by this Interface.
	 * 
	 * <p>
	 * <b>Constraints</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C-4-4]</b> An Interface can only contain Operations.<pre>
	 * <b>context</b> Interface <b>inv</b>:
	 * self.allFeatures -> forAll( f | f.oclIsKindOf( Operation ) ) 
	 * </pre>
	 * </li>
	 * <li><b>[C-4-6]</b> All Features defined in an Interface are public.<pre>
	 * <b>context</b> Interface <b>inv</b>:
	 * self.allFeatures -> forAll( f | f.visibility = #public )
	 * </pre> 
	 * </li>
	 * </ul> 
	 * 
	 * @param operation The Operation that will be owned by this Interface.
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addFeature( Operation operation );
}