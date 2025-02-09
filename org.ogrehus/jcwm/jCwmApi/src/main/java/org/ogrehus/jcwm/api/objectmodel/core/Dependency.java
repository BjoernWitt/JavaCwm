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

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

/**
 * A dependency states that the implementation or functioning of one or more elements requires the presence of one or more other elements.
 * <p>
 * In the meta-model a Dependency is a directed relationship from a client (or clients) to a supplier (or suppliers) stating that the client is
 * dependent on the supplier; that is, the client element requires the presence and knowledge of the supplier element.
 * </p>
 * <p>
 * A dependency specifies that the semantics of a set of model elements requires the presence of another set of model elements. This implies that if
 * the source is somehow modified, the dependents probably must be modified. The reason for the dependency can be specified in several different ways
 * (for example, using natural language or an algorithm) but is often implicit.
 * </p>
 * <p>
 * Whenever the supplier element of a dependency changes, the client element is potentially invalidated. After such invalidation, a check should be
 * performed followed by possible changes to the derived client element. Such a check should be performed after which action can be taken to change
 * the derived element to validate it again.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @see org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency
 * 
 */
public interface Dependency
extends
	GDependency<
		  GNamespace< ?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
	>
{


	//================================================================================================================================================
	// GModelElement capabilities
	//================================================================================================================================================



	/**
	 * Designates the Namespace that contains the ModelElement. Every Model Element except a root element must belong to exactly one Namespace or else
	 * be a composite part of another ModelElement (which is a kind of virtual namespace).
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
	 * @param namespace The new namespace that contains this ModelElement.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */
	GNamespace< ?, ?, ?, ?, ?> setNamespace( GNamespace< ?, ?, ?, ?, ?> namespace );



	/**
	 * Adds a dependency to this Dependency.
	 * 
	 * @param dependency A Dependency in witch this Dependency is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds an importer (Package) to this Dependency, that will contain this Dependency as imported.
	 * 
	 * @param importer The specific package, that will contain this Dependency.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer );



	/**
	 * Adds another Constraint to this Dependency.
	 * 
	 * @param constraint A Constrain that must be satisfied by this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// Dependency capabilities
//====================================================================================================================================================



	/**
	 * Adds a ClientDependency to the list of Dependencies affected by this Dependency.
	 * </p>
	 * <p>
	 * Adds the element that is affected by the supplier element. In some cases the direction is unimportant and serves only to distinguish the two
	 * elements.
	 * </p>
	 * 
	 * @param client The modelElement that will be affect by a supplier by this Dependency.
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 *
	 * @throws ConstraintViolation if the Constraint <b>[C 3-1]</b> was violated.
	 * 
	 */
	Boolean addClient( GModelElement<?, ?, ?, ?> client );
}