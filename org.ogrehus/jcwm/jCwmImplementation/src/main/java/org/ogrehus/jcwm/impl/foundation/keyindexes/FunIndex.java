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
package org.ogrehus.jcwm.impl.foundation.keyindexes;

import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.relationships.AssociationEnd;
import org.ogrehus.jcwm.api.foundation.keyindexes.Index;
import org.ogrehus.jcwm.api.foundation.keyindexes.IndexedFeature;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.foundation.keyindexes.util.generic.*;

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
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunIndex
extends 
	GFunIndex<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, CwmClass
		, IndexedFeature
	>
implements
	Index
{


	/**
	 * Factory method that creates a new instance of Index by specific parameters.
	 * 
	 * <p>
	 * Instances of the Index class represent the ordering of the instances of some other Class, and the Index is said to span the Class. Indexes
	 * normally have an ordered set of attributes of the Class instance they span that make up the key of the index; this set of relationships is
	 * represented by the IndexedFeature class that indicates how the attributes are used by the Index instance.
	 * </p>
	 * 
	 * @param name An identifier for the Index within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Index within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param isUnique The isUnique attribute is <code>true</code> if the Index instance guarantees all of its instances have a unique key value. Must
	 * not be <code>null</code>.
	 * 
	 * @param isSorted If <code>true</code>, the Index instance is maintained in a sorted order. Must not be <code>null</code>.
	 * 
	 * @param isPartitioning If <code>true</code>, this Index instance is used as a partitioning index. Must not be <code>null</code>.
	 * 
	 * @param spannedClass Identifies the Class instance spanned by the Index instance. Must not be <code>null</code>.
	 * 
	 * @param indexedName The name of the new IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @param indexedVisibility The visibility of the new IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @param indexedAttribute The StructuralFeature tha will be associated by this IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>isUnique</code>,
	 * <code>isSorted</code>, <code>isPartitioning</code> or <code>spannedClass</code> is <code>null</code>.
	 * 
	 */
	public FunIndex(
		  String name
		, Visibility visibility
		, Boolean isUnique
		, Boolean isSorted
		, Boolean isPartitioning
		, CwmClass spannedClass
		, String indexedName
		, Visibility indexedVisibility
		, Attribute indexedAttribute
	) {
		super( name, visibility, isUnique, isSorted, isPartitioning, spannedClass );  //may throw NullPointerException 
		addIndexedFeature( indexedName, indexedVisibility, indexedAttribute );
	}



	/**
	 * Factory method that creates a new instance of Index by specific parameters.
	 * 
	 * <p>
	 * Instances of the Index class represent the ordering of the instances of some other Class, and the Index is said to span the Class. Indexes
	 * normally have an ordered set of attributes of the Class instance they span that make up the key of the index; this set of relationships is
	 * represented by the IndexedFeature class that indicates how the attributes are used by the Index instance.
	 * </p>
	 * 
	 * @param name An identifier for the Index within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Index within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param isUnique The isUnique attribute is <code>true</code> if the Index instance guarantees all of its instances have a unique key value. Must
	 * not be <code>null</code>.
	 * 
	 * @param isSorted If <code>true</code>, the Index instance is maintained in a sorted order. Must not be <code>null</code>.
	 * 
	 * @param isPartitioning If <code>true</code>, this Index instance is used as a partitioning index. Must not be <code>null</code>.
	 * 
	 * @param spannedClass Identifies the Class instance spanned by the Index instance. Must not be <code>null</code>.
	 * 
	 * @param indexedName The name of the new IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @param indexedVisibility The visibility of the new IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @param indexedAssociationEnd The StructuralFeature tha will be associated by this IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>isUnique</code>,
	 * <code>isSorted</code>, <code>isPartitioning</code> or <code>spannedClass</code> is <code>null</code>.
	 * 
	 */
	public FunIndex(
		  String name
		, Visibility visibility
		, Boolean isUnique
		, Boolean isSorted
		, Boolean isPartitioning
		, CwmClass spannedClass
		, String indexedName
		, Visibility indexedVisibility
		, AssociationEnd indexedAssociationEnd
	) {
		super( name, visibility, isUnique, isSorted, isPartitioning, spannedClass );  //may throw NullPointerException 
		addIndexedFeature( indexedName, indexedVisibility, indexedAssociationEnd );
	}



//====================================================================================================================================================
// GFunIndex capabilities	
//====================================================================================================================================================



	public Boolean addIndexedFeature( String indexedName, Visibility indexedVisibility, Attribute attribute ) {
		return addIndexedFeature( new FunIndexedFeature( indexedName, indexedVisibility, attribute, this ) );
	}



	public Boolean addIndexedFeature(
		  String indexedName
		, Visibility indexedVisibility
		, AssociationEnd associationEnd
	) {
		return addIndexedFeature( new FunIndexedFeature( indexedName, indexedVisibility, associationEnd, this ) );
	}



	public Boolean addIndexedFeature( IndexedFeature indexFeature ) {
		if ( indexFeature == null ) {
			throw new NullPointerException( "The parameter: indexFeature must not be null!" );
		}
		if ( this.indexedFeatures.add( indexFeature ) ) {
			indexFeature.setIndex( this );
			
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



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
	public CwmClass setSpannedClass( CwmClass cwmClass ) {
		return super.setSpannedClassGeneric( cwmClass );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public GNamespace<?, ?, ?, ?, ?> setNamespace( GNamespace<?, ?, ?, ?, ?> naemspace ) {
		return super.setNamespaceGeneric( naemspace );
	}



	public Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer ) {
		return super.addImporterGeneric( importer );
	}
}