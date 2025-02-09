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
package org.ogrehus.jcwm.impl.objectmodel.instance;


import org.ogrehus.jcwm.api.foundation.softwaredeployment.CwmManager;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.CwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.instance.CwmObject;
import org.ogrehus.jcwm.api.objectmodel.instance.Extent;

import org.ogrehus.jcwm.impl.objectmodel.instance.util.generic.GFunExtent;


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
public class FunExtent
extends
	GFunExtent<
		  CwmManager
		, CwmClass
		, CwmPackage
		, CwmObject
		, CwmObject
	>
implements
	Extent
{


	/**
	 * Creates a new instance of this FunExtent by specific parameters.
	 * 
	 * @param name An identifier for the ModelElement within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the ModelElement within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Extent, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	public FunExtent( String name, Visibility visibility ) {
		super( name, visibility );
	}



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
	public CwmClass setNamespace( CwmClass cwmClass ) {
		return super.setNamespaceGeneric( cwmClass );
	}



	/**
	 * Adds an importer (Package) to this Extent, that will contain this Extent as imported.
	 * 
	 * @param cwmPackage The specific package, that will contain this Extent.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addImporter( CwmPackage cwmPackage ) {
		return super.addImporterGeneric( cwmPackage );
	}



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
	public Boolean addOwnedElement( CwmObject cwmObject ) {
		return super.addOwnedElementGeneric( cwmObject );
	}



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
	public Boolean addImportedElement( CwmObject cwmObject ) {
		return super.addImportedElementGeneric( cwmObject );
	}
}