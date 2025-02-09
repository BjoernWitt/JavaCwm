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
package org.ogrehus.jcwm.api.foundation.keyindexes.util.generic;

import java.util.SortedSet;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


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
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this ModelElement.
 * @param <PACKAGE> The type of the importer of this ModelElement.
 * @param <SPANNED_CLASS> 
 * @param <INDEXED_FEATURE> 
 * 
 */
public interface GIndex<
	  NAMESPACE       extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE         extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, SPANNED_CLASS   extends GCwmClass<?, ?, ?, ?, ?, ?, ?>
	, INDEXED_FEATURE extends GIndexedFeature<?, ?, ?, ?>
> extends 
	GModelElement<NAMESPACE, PACKAGE, Dependency, Constraint> 
{



//====================================================================================================================================================
// GIndex capabilities	
//====================================================================================================================================================



	/**
	 * The isUnique property is <code>true</code> if the Index instance guarantees all of its instances have a unique key value.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the Index instance guarantees all of its instances have a unique key value, <code>false</code> otherwise. Cannot 
	 * be <code>null</code>.
	 * 
	 */
	Boolean isUnique();



	/**
	 * The isUnique property is <code>true</code> if the Index instance guarantees all of its instances have a unique 
	 * key value.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param isUnique <code>true</code> if the Index instance guarantees all of its instances have a unique key value, <code>false</code> otherwise. 
	 * Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>isUnique</code>. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isUnique</code> was <code>null</code>.
	 * 
	 */
	Boolean setUnique( Boolean isUnique );



	/**
	 * If <code>true</code>, the Index instance is maintained in a sorted order.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the Index instance is maintained in a sorted order, <code>false</code> otherwise.
	 * 
	 */
	Boolean isSorted();



	/**
	 * If <code>true</code>, the Index instance is maintained in a sorted order.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param isSorted <code>true</code> if the Index instance is maintained in a sorted order, <code>false</code> otherwise. Must not be 
	 * <code>null</code>.
	 * 
	 * @return The old value of the property <code>isUnique</code>. Cannot be <code>null</code>. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isUnique</code> was <code>null</code>.
	 * 
	 */
	Boolean setSorted( Boolean isSorted );



	/**
	 * If <code>true</code>, this Index instance is used as a partitioning index.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this Index instance is used as a partitioning index, <code>false</code> otherwise. Can't be <code>null</code>.
	 * 
	 */
	Boolean isPartitioning();



	/**
	 * If <code>true</code>, this Index instance is used as a partitioning index.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param isPartitioning <code>true</code> if this Index instance is used as a partitioning index, <code>false</code> otherwise. Must not 
	 * be <code>null</code>.
	 * 
	 * @return The old value for the property <code>isPartitioning</code>. Can't be <code>null</code>
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isPartitioning</code> was <code>null</code>. 
	 * 
	 */
	Boolean setPartitioning( Boolean isPartitioning );



	/**
	 * Identifies the IndexedFeature instance that associates this Index with one of the StructuralFeature elements of the Index's key. 
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
	 * @return List of indexedFeatures that associates this Index with one of the StructuralFeature elements of the Index's key.
	 * 
	 */
	SortedSet<INDEXED_FEATURE> getIndexedFeatures();



	/**
	 * Removes a feature from this index.
	 * <p>
	 * An Index must have at least one IndexedFeature, so if the feature to be removed is the last found within the set 
	 * of features it can't be removed!
	 * </p>
	 * 
	 * @param indexedFeature The feature that will be removed from this Index.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeIndexedFeature( GIndexedFeature<?, ?, ?, ?> indexedFeature );



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
	 * @return The Class instance spanned by the Index instance. Cannot be <code>null</code>.
	 * 
	 */
	SPANNED_CLASS getSpannedClass();



	/**
	 * Removes the Class instance spanned by the Index instance.
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
	 * @return <code>true</code> if this the spannedClass is removed, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeSpannedClass();



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Adds a dependency to this DataType.
	 * 
	 * @param dependency A Dependency in witch this DataType is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this DataType.
	 * 
	 * @param constraint A Constrain that must be satisfied by this DataType.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );
}