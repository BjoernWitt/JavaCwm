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
package org.ogrehus.jcwm.impl.foundation.keyindexes.util.generic;

import java.util.SortedSet;
import java.util.TreeSet;

import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GIndex;
import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GIndexedFeature;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;


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
 * @param <NAMESPACE> The type of the Namespace that contains this ModelElement.
 * @param <PACKAGE> The type of the importer of this ModelElement.
 * @param <SPANNED_CLASS> 
 * @param <INDEXED_FEATURE> 
 * 
 */
public abstract class GFunIndex<
	  NAMESPACE       extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE         extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, SPANNED_CLASS   extends GCwmClass<?, ?, ?, ?, ?, ?, ?>
	, INDEXED_FEATURE extends GIndexedFeature<?, ?, ?, ?>
> extends 
	GFunModelElement<NAMESPACE, PACKAGE, Dependency, Constraint>
implements
	GIndex<NAMESPACE, PACKAGE, SPANNED_CLASS, INDEXED_FEATURE>
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
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>isUnique</code>,
	 * <code>isSorted</code>, <code>isPartitioning</code> or <code>spannedClass</code> is <code>null</code>.
	 * 
	 */
	public <STRUCTURAL extends INDEXED_FEATURE> GFunIndex(
		  String name
		, Visibility visibility
		, Boolean isUnique
		, Boolean isSorted
		, Boolean isPartitioning
		, SPANNED_CLASS spannedClass
	) {
		super( name, visibility );//may throw NullPointerException 
		this.indexedFeatures = new TreeSet<INDEXED_FEATURE>();
		setUnique( isUnique ); //may throw NullPointerException 
		setSorted( isSorted ); //may throw NullPointerException 
		setPartitioning( isPartitioning ); //may throw NullPointerException 
		setSpannedClassGeneric( spannedClass );  //may throw NullPointerException 
	}



//====================================================================================================================================================
// GFunIndex capabilities	
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
	public Boolean isUnique() {
		return this.isUnique;
	}



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
	 * @param isUnique <code>true</code> if the Index instance guarantees all of its instances have a unique key value, <code>false</code> otherwise.
	 * Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>isUnique</code>. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isUnique</code> was <code>null</code>.
	 * 
	 */
	public Boolean setUnique( Boolean isUnique ) {
		if ( isUnique == null ) {
			throw new NullPointerException( "The parameter: isUnique must not be null!" );
		}
		Boolean old = isUnique;
		this.isUnique = isUnique;
		
		return old;
	}



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
	public Boolean isSorted() {
		return this.isSorted;
	}



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
	 * @param isSorted <code>true</code> if the Index instance is maintained in a sorted order, <code>false</code> otherwise. Must not
	 * be <code>null</code>.
	 * 
	 * @return The old value of the property <code>isUnique</code>. Cannot be <code>null</code>. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isUnique</code> was <code>null</code>.
	 * 
	 */
	public Boolean setSorted( Boolean isSorted ) {
		if ( isSorted == null ) {
			throw new NullPointerException( "The parameter: isSorted must not be null!" );
		}
		
		Boolean old = isSorted;
		this.isSorted = isSorted;
		
		return old;
	}



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
	public Boolean isPartitioning() {
		return this.isPartitioning;
	}



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
	public Boolean setPartitioning( Boolean isPartitioning ) {
		if ( isPartitioning == null ) {
			throw new NullPointerException( "The parameter: isPartitioning must not be null!" );
		}

		Boolean old = isPartitioning;
		this.isPartitioning = isPartitioning;
		
		return old;
	}



	/**
	 * Identifies the IndexedFeature instance that associates this Index with one of the StructuralFeature elements of the Index key.
	 * 
	 * <p>
	 * The ordered constraint on this reference can be used to represent the sequential order of elements of the Index key. 
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
	 * @return List of indexedFeatures that associates this Index with one of the StructuralFeature elements of the Index key.
	 * 
	 */
	public SortedSet<INDEXED_FEATURE> getIndexedFeatures() {
		return this.indexedFeatures;
	}



	/**
	 * Removes a feature from this index.
	 * <p>
	 * An Index must have at least one IndexedFeature, so if the feature to be removed is the last found within the set of features it can't be
	 * removed!
	 * </p>
	 * 
	 * @param indexedFeature The feature that will be removed from this Index.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean removeIndexedFeature( GIndexedFeature<?, ?, ?, ?> indexedFeature ) {
		if ( indexedFeatures == null ) {
			return Boolean.TRUE;
		}
		
		if ( this.indexedFeatures.size() > 1 && this.indexedFeatures.remove( indexedFeature ) ) {
			indexedFeature.removeIndex();
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



	protected <I extends INDEXED_FEATURE> Boolean addIndexFeatureGeneric( I indexedFeature ) {
		if ( indexedFeature != null && this.indexedFeatures.add( indexedFeature ) ) {
			invokeByReflection( indexedFeature, "setIndex", this ); // bi-directional reference
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}



	protected <I extends INDEXED_FEATURE> I getIndexedFeatureGeneric( Class<I> typeOf, String simpleName ) {
		if ( typeOf != null && simpleName != null ) {
			for ( INDEXED_FEATURE indexedFeature : this.indexedFeatures ) {
				if ( typeOf.isInstance( indexedFeature ) && simpleName.equals( indexedFeature.getSimpleName() ) ) {
					return typeOf.cast(indexedFeature);
				}
			}
		}
		return null;
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
	 * @return The Class instance spanned by the Index instance. Cannot be <code>null</code>.
	 * 
	 */
	public SPANNED_CLASS getSpannedClass() {
		return this.spannedClass;
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
	 * @param spannedClass Registers the Class instance spanned by the Index instance. Must not be <code>null</code>.
	 * 
	 * @return The Class instance spanned by the Index instance. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isPartitioning</code> was <code>null</code>. 
	 * 
	 */
	protected SPANNED_CLASS setSpannedClassGeneric( SPANNED_CLASS spannedClass ) {
		if ( spannedClass == null ) {
			throw new NullPointerException( "The parameter: spannedClass must not be null!" );
		}
		
		if ( spannedClass != null && spannedClass.equals( this.spannedClass ) ) {
			return spannedClass; // no changes, cause its the same namespace
		}
		
		SPANNED_CLASS old = this.spannedClass; // return value 
		if ( old != null ) {
			old.removeIndex( this );
		}
		
		this.spannedClass = spannedClass;
		if ( !this.spannedClass.getIndexes().contains( this ) ) {
			invokeByReflection( this.spannedClass, "addIndex", this ); // organize the bidirectional reference 
		}
		
		return old;
	}



	public Boolean removeSpannedClass() {
		if ( this.spannedClass == null ) {
			return Boolean.TRUE;
		}
		
		SPANNED_CLASS old = this.spannedClass;
		this.spannedClass = null;
		old.removeIndex( this );
		
		return Boolean.TRUE;
	}



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
	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	/**
	 * Adds a constraint to this DataType.
	 * 
	 * @param constraint A Constrain that must be satisfied by this DataType.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunIndex" );
		if ( this.isUnique != null ) {
			out.append( " | isUnique=" + this.isUnique );
		}
		
		if ( this.isSorted != null ) {
			out.append( " | isSorted=" + this.isSorted );
		}
		if ( this.isPartitioning != null ) {
			out.append( " | isPartitioning=" + this.isPartitioning );
		}
		
		if ( this.spannedClass != null ) {
			out.append( " | spannedClass=" + this.spannedClass.getQualifiedName() );
			out.append( "<" + spannedClass.getClass().getSimpleName() + ">" );
		}
		
		out.append( collectionToString( "indexedFeatures", this.indexedFeatures, true ) );
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		
		return out.toString();
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * The isUnique attribute is True if the Index instance guarantees all of its instances have a unique key value.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Boolean isUnique = null;



	/**
	 * If True, the Index instance is maintained in a sorted order.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Boolean isSorted = null;



	/**
	 * If True, this Index instance is used as a partitioning index.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Boolean isPartitioning = null;



	/**
	 * Identifies the IndexedFeature instance that associates this Index with one of the StructuralFeature elements of the Index key.
	 * 
	 * <p>
	 * The ordered constraint on this reference can be used to represent the sequential order of elements of the Index key.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic INDEXED_FEATURE</li>
	 * <li><i>defined by</i> 	: IndexedFeatureInfo::indexedFeature</li>
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * <li><i>inverse</i> 		: IndexedFeature::index</li>
	 * </ul>
	 * 
	 */
	protected SortedSet<INDEXED_FEATURE> indexedFeatures = null;



	/**
	 * Identifies the Class instance spanned by the Index instance.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic SPANNED_CLASS</li>
	 * <li><i>defined by</i> 	: IndexSpansClass::spannedClass</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected SPANNED_CLASS spannedClass = null;
}