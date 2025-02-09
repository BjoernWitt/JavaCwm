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
package org.ogrehus.jcwm.api.objectmodel.instance;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.CwmManager;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.CwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GExtent;


/**
 * Each instance of Extent owns a collection of instances and is used to link such collections to their structural and behavioral definitions in CWM 
 * Resource packages.
 * <p>
 * Because Extent is a subclass of package, it owns member instances via the ElementOwnership association.
 * </p>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Extent
extends
	GExtent<
		  CwmManager 
		, CwmClass
		, CwmPackage
		, CwmObject
		, CwmObject
	>
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================


	/**
	 * Designates the Namespace that contains the Extent.
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
	 * <li><i>class</i>			: CwmClass</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Namespace::ownedElement</li>
	 * </ul>
	 * 
	 * @param namespace The new namespace that contains this Extent.
	 * 
	 * @return The namespace that contains this Extent before, or <code>null</code> none was assigned before.
	 * 
	 */
	CwmClass setNamespace( CwmClass namespace );



	/**
	 * Adds an importer (Package) to this Extent, that will contain this Extent as imported.
	 * 
	 * @param cwmPackage The specific package, that will contain this Extent.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( CwmPackage cwmPackage );



	/**
	 * Adds a dependency to this Extent.
	 * 
	 * @param dependency A Dependency in witch this Extent is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this Extent.
	 * 
	 * @param constraint A Constrain that must be satisfied by this Extent.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// GNamespace capabilities
//====================================================================================================================================================



	/**
	 * Adds a generic CwmObject to the reference of <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this Extent. The object visibility attribute states whether the element is
	 * visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this type of namespace to the new ownedElement for inverse traversal.
	 * </p>
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( CwmObject cwmObject );



//====================================================================================================================================================
// Package capabilities
//====================================================================================================================================================



	/**
	 * Adds an importable CwmObject to this Extent.
	 * 
	 * @param cwmObject The new generic CwmObject that will enter the extended Namespace of an Extent.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImportedElement( CwmObject cwmObject );
}