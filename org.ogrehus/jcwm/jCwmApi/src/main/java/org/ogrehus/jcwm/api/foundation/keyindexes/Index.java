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
package org.ogrehus.jcwm.api.foundation.keyindexes;

import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GIndex;
import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.relationships.AssociationEnd;


/**
 * Instances of the Index class represent the ordering of the instances of some other Class, and the Index is said to span the Class. 
 * <p>
 * Indexes normally have an ordered set of attributes of the Class instance they span that make up the key of the index; this set of relationships is 
 * represented by the IndexedFeature class that indicates how the attributes are used by the Index instance.
 * </p>
 * <p>
 * The Index class is intended primarily as a starting point for tools that require the notion of an index.
 * </p>
 * <p>
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Index
extends 
	GIndex<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, CwmClass
		, IndexedFeature
	>
{


	/**
	 * Adds an IndexedFeature instance that associates this Index with one of the StructuralFeature elements of the Index's key. 
	 * 
	 * <p>
	 * The ordered constraint on this reference can be used to represent the sequential order of elements of the Index's key. 
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : IndexedFeature</li>
	 * <li><i>defined by</i> 	: IndexedFeatureInfo::indexedFeature</li> 
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * <li><i>inverse</i> 		: IndexedFeature::index</li> 
	 * </ul>
	 * 
	 * @param indexFeature The instance that associates this Index with one of the StructuralFeature elements of the Index's key
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addIndexedFeature( IndexedFeature indexFeature );



	/**
	 * Creates an IndexedFeature instance by specific parameters and adds it to this Index.
	 * <p>
	 * The IndexedFeature associates this Index with one of the StructuralFeature elements of the Index's key. 
	 * </p>
	 * <p>
	 * The ordered constraint on this reference can be used to represent the sequential order of elements of the Index's key. 
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : IndexedFeature</li>
	 * <li><i>defined by</i> 	: IndexedFeatureInfo::indexedFeature</li> 
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * <li><i>inverse</i> 		: IndexedFeature::index</li> 
	 * </ul>
	 * 
	 * @param indexedName The name of the new IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @param indexedVisibility The visibility of the new IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @param attribute The StructuralFeature that will be associated by this IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the following parameter is <code>null</code>: <code>indexedName</code>, 
	 * <code>indexedVisibility</code>, <code>attribute</code>.
	 * 
	 */
	Boolean addIndexedFeature( String indexedName, Visibility indexedVisibility, Attribute attribute );



	/**
	 * Creates an IndexedFeature instance by specific parameters and adds it to this Index.
	 * <p>
	 * The IndexedFeature associates this Index with one of the StructuralFeature elements of the Index key. 
	 * </p>
	 * <p>
	 * The ordered constraint on this reference can be used to represent the sequential order of elements of the Index's key. 
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : IndexedFeature</li>
	 * <li><i>defined by</i> 	: IndexedFeatureInfo::indexedFeature</li> 
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * <li><i>inverse</i> 		: IndexedFeature::index</li> 
	 * </ul>
	 * 
	 * @param indexedName The name of the new IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @param indexedVisibility The visibility of the new IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @param associationEnd The StructuralFeature that will be associated by this IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the following parameter is <code>null</code>: <code>indexedName</code>, 
	 * <code>indexedVisibility</code>, <code>attribute</code>.
	 * 
	 */
	Boolean addIndexedFeature( String indexedName, Visibility indexedVisibility, AssociationEnd associationEnd );



	/**
	 * Identifies the Class instance spanned by the Index instance.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : Class</li>
	 * <li><i>defined by</i> 	: IndexSpansClass::spannedClass</li> 
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param cwmClass Registers the Class instance spanned by the Index instance. Must not be <code>null</code>.
	 * 
	 * @return The Class instance spanned by the Index instance. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>cwmClass</code> was <code>null</code>. 
	 * 
	 */
	CwmClass setSpannedClass( CwmClass cwmClass );



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the Index. 
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
	 * @param namespace The new namespace that contains this Index.
	 * 
	 * @return The namespace that contains this Index before, or <code>null</code> none was assigned before. 
	 * 
	 */
	GNamespace<?, ?, ?, ?, ?> setNamespace( GNamespace<?, ?, ?, ?, ?> namespace  );



	/**
	 * Adds an importer (Package) to this Index, that will contain this Index as imported.
	 * 
	 * @param cwmPackage The specific package, that will contain this Index.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> cwmPackage );
}