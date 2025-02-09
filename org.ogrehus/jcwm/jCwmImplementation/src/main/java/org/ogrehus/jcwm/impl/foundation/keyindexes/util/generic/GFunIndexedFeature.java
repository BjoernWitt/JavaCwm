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

import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GIndex;
import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GIndexedFeature;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;

/**
 * Instances of the IndexedFeature class map StructuralFeature instances of the spanned Class instance to the Index instances that employ them as
 * (part of) their key.
 * <p>
 * Attributes of IndexedFeature instances indicate how specific StructuralFeature instances are used in Index keys.
 * </p> 
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-6-1]</b> The isAscending attribute is valid only if the <code>isSorted</code> attribute is True.</li>
 *  </ul>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this ModelElement.
 * @param <PACKAGE> The type of the importer of this ModelElement.
 * @param <INDEX> 
 * @param <STRUCTURAL> 
 * 
 */
public abstract class  GFunIndexedFeature<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, INDEX      extends GIndex<?, ?, ?, ?>
	, STRUCTURAL extends GStructuralFeature<?, ?, ?, ?, ?, ?>
> extends
	GFunModelElement<NAMESPACE, PACKAGE, Dependency, Constraint>
implements
	GIndexedFeature<NAMESPACE, PACKAGE, INDEX, STRUCTURAL>
{


	/**
	 * Creates a new instance of IndexedFeature by specific parameters.
	 * 
	 * @param name An identifier for the IndexedFeature within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the IndexedFeature within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param index The Index instance for which this IndexedFeature instance is relevant. Must not be <code>null</code>.
	 * 
	 * @param feature Identifies the StructuralFeature instance for which this IndexedFeature instance is relevant.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>index</code>
	 * is <code>null</code>.
	 * 
	 */
	protected GFunIndexedFeature( String name, Visibility visibility, INDEX index, STRUCTURAL feature ) {
		super( name, visibility ); // may throw NullPointerException
		setIndex( index ); // may throw NullPointerException
		setFeatureGeneric( feature ); // may throw NullPointerException
	}



//====================================================================================================================================================
// IndexedFeature capabilities
//====================================================================================================================================================



	/**
	 * The isAscending attribute is <code>true</code> if the feature is sorted in ascending order and <code>false</code>, if descending order.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: Zero or one</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-6-1]</b> The isAscending attribute is valid only if the <code>isSorted</code> attribute is True.</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the feature is sorted in ascending order and <code>false</code>, if descending order and <code>null</code>, if the
	 * Index is not sorted.
	 * 
	 */
	public Boolean isAscending() {
		return this.isAscending;
	}



	/**
	 * The isAscending attribute is <code>true</code> if the feature is sorted in ascending order and <code>false</code>, if descending order.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: Zero or one</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[Foundation_C_6_1]</b> The isAscending attribute is valid only if the <code>isSorted</code> attribute is 
     * <code>true</code>.</li>
	 * </ul>
	 * 
	 * 
	 * @param isAscending <code>true</code> if the feature is sorted in ascending order and <code>false</code>, if descending order.
	 * 
	 * @return The old value of the property <code>isAscending</code>.
	 * 
	 * @throws ConstraintViolation If the constraint <b>[Foundation_C_6_1]</b> is violated. 
	 * 
	 */
	public Boolean setAscending( Boolean isAscending ) {
		Boolean old = this.isAscending;
		this.isAscending = isAscending;
		
		return old;
	}



	/**
	 * Identifies the Index instance for which this IndexedFeature instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : Index</li>
	 * <li><i>defined by</i> 	: IndexedFeatureInfo::index</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * <li><i>inverse</i> 		: Index::indexedFeature</li>
	 * </ul>
	 * 
	 * @return The Index instance for which this IndexedFeature instance is relevant.
	 * 
	 */
	public INDEX getIndex() {
		return this.index;
	}



	/**
	 * Identifies the Index instance for which this IndexedFeature instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : Index</li>
	 * <li><i>defined by</i> 	: IndexedFeatureInfo::index</li> 
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * <li><i>inverse</i> 		: Index::indexedFeature</li> 
	 * </ul>
	 * 
	 * @param index The Index instance for which this IndexedFeature instance is relevant. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>index</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>index</code> was <code>null</code>.
	 * 
	 */
	public INDEX setIndex( INDEX index ) {
		if ( index == null ) {
			throw new NullPointerException( "The parameter: index must not be null!" );
		}
		
		INDEX old = this.index;
		this.index = index;
		invokeByReflection( this.index, "addIndexedFeature", this );
		
		if ( old != null ) {
			old.removeIndexedFeature( this );
		}
		
		return old;
	}



	public Boolean removeIndex() {
		if ( this.index == null ) {
			return Boolean.TRUE;
		}
		
		this.index.removeIndexedFeature( this );
		this.index = null;
		
		return Boolean.TRUE;
	}



	/**
	 * Identifies the StructuralFeature instance for which this IndexedFeature instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic STRUCTURAL</li>
	 * <li><i>defined by</i> 	: IndexedFeatures::feature</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return The StructuralFeature instance for which this IndexedFeature instance is relevant. Cannot be <code>null</code>.
	 * 
	 */
	public STRUCTURAL getFeature() {
		return this.feature;
	}



	public Boolean removeFeature() {
		this.feature = null;
		
		return Boolean.TRUE;
	}



	protected STRUCTURAL setFeatureGeneric( STRUCTURAL feature ) {
		if ( feature == null ) {
			throw new NullPointerException( "The parameter: feature must not be null!" );
		}
		
		STRUCTURAL old = this.feature;
		this.feature = feature;
		
		return old;
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Adds a dependency to this IndexedFeature.
	 * 
	 * @param dependency A Dependency in witch this IndexedFeature is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	/**
	 * Adds a constraint to this IndexedFeature.
	 * 
	 * @param constraint A Constrain that must be satisfied by this IndexedFeature.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );        
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * The isAscending attribute is true if the feature is sorted in ascending order and false, if descending order.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: Zero or one</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-6-1]</b> The isAscending attribute is valid only if the <code>isSorted</code> attribute is True.</li>
	 * </ul>
	 * 
	 */
	protected Boolean isAscending = null;



	/**
	 * Identifies the Index instance for which this IndexedFeature instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic INDEX</li>
	 * <li><i>defined by</i> 	: IndexedFeatureInfo::index</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * <li><i>inverse</i> 		: Index::indexedFeature</li>
	 * </ul>
	 * 
	 */
	protected INDEX index = null;



	/**
	 * Identifies the StructuralFeature instance for which this IndexedFeature 
	 * instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic STRUCTURAL</li>
	 * <li><i>defined by</i> 	: IndexedFeatures::feature</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected STRUCTURAL feature = null;
}