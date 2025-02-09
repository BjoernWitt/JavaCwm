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

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GKeyRelationship;
import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GUniqueKey;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;


/**
 * A UniqueKey represents a collection of features of some Class that, taken together, uniquely identify instances of the Class.
 *  
 * <p>
 * Instances of UniqueKey for which all features are required to have non-null values are candidates for use as primary keys such as those defined by
 * relational DBMSs.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation_C_6_3]</b> An UniqueKey instance must be owned by one and only one Class instance.</li>
 * </ul> 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this UniqueKey.
 * @param <PACKAGE> The type of the importer of this UniqueKey.
 * @param <KEY_RELATION> Identifies the KeyRelationship instances that reference this UniqueKey instance.
 * @param <STRUCTURAL> Identifies the StructuralFeature instances that make up the unique key. 
 * 
 */
public abstract class GFunUniqueKey<
	  NAMESPACE    extends GCwmClass<?, ?, ?, ?, ?, ?, ?>
	, PACKAGE      extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, KEY_RELATION extends GKeyRelationship<?, ?, ?, ?>
	, STRUCTURAL   extends GStructuralFeature<?, ?, ?, ?, ?, ?>
> extends
	GFunModelElement<NAMESPACE, PACKAGE, Dependency, Constraint>
implements
	GUniqueKey<NAMESPACE, PACKAGE, KEY_RELATION, STRUCTURAL>
{


	/**
	 * Factory method that creates a new instance of UniqueKey by specific parameters.
	 * 
	 * <p>
	 * A UniqueKey represents a collection of features of some Class that, taken together, uniquely identify instances of the Class. Instances of
	 * UniqueKey for which all features are required to have non-null values are candidates for use as primary keys such as those defined by
	 * relational DBMSs.
	 * </p>
	 * 
	 * @param name An identifier for the UniqueKey within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the UniqueKey within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>firstFeature</code> or
	 * <code>keyRelationship</code> is <code>null</code>.
	 * 
	 */
	protected GFunUniqueKey( String name, Visibility visibility ) {
		super( name, visibility );
		
		this.features         = new TreeSet<STRUCTURAL>();
		this.keyRelationships = new TreeSet<KEY_RELATION>();
	}



//====================================================================================================================================================
// UniqueKey capabilities
//====================================================================================================================================================



	/**
	 * Identifies the StructuralFeature instances that make up the unique key.
	 * 
	 * <p>
	 * The ordered constraint is used to represent the sequence of StructuralFeature instances that make up the UniqueKey instance key.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic FEATURE</li>
	 * <li><i>defined by</i> 	: UniqueFeature::feature</li>
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * </ul>
	 * 
	 * @return Set StructuralFeature instances that make up the unique key.
	 * 
	 */
	public SortedSet<STRUCTURAL> getFeatures() {
		return this.features;
	}



	/**
	 * Removes a feature from this index.
	 * <p>
	 * An UniqueKey must have at least one StructuralFeature, so if the feature to be removed is the last found within the set of features it can't be
	 * removed!
	 * </p>
	 * 
	 * @param feature The feature that will be removed from this KeyRelationship.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean removeFeature( GStructuralFeature<?, ?, ?, ?, ?, ?> feature ) {
		if ( feature == null ) {
			return Boolean.TRUE;
		}
		
		return ( this.features.size() >= 1 && this.features.remove( feature ) );
	}



	protected Boolean addFeatureGeneric( STRUCTURAL feature ) {
		return ( feature != null && this.features.add( feature ) );
	}



	/**
	 * Identifies the KeyRelationship instances that reference this UniqueKey instance.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : KeyRelationship</li>
	 * <li><i>defined by</i>    : UniqueKeyRelationship::keyRelationship</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : KeyRelationship::uniqueKey</li>
	 * </ul>
	 * 
	 * @return Set of KeyRelationship instances that reference this UniqueKey instance.
	 * 
	 */
	public Set<KEY_RELATION> getKeyRelationships() {
		return this.keyRelationships;
	}



	public Boolean addKeyRelationshipGeneric( KEY_RELATION keyRelationship ) {
		if ( keyRelationship != null && this.keyRelationships.add( keyRelationship ) ) {
			invokeByReflection( keyRelationship, "addUniqueKey", this ); // bi-directional reference
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Adds a dependency to this UniqueKey.
	 * 
	 * @param dependency A Dependency in witch this UniqueKey is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	/**
	 * Adds a constraint to this UniqueKey.
	 * 
	 * @param constraint A Constrain that must be satisfied by this UniqueKey.
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
		StringBuffer out = new StringBuffer( "[GFunUniqueKey" );
		out.append( collectionToString( "features", this.features, true ) );
		out.append( collectionToString( "keyRelationships", this.keyRelationships, true ) );        
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		
		return out.toString();
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Identifies the StructuralFeature instances that make up the unique key. 
	 * 
	 * <p>
	 * The ordered constraint is used to represent the sequence of StructuralFeature instances that make up the UniqueKey instance key.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic STRUCTURAL</li>
	 * <li><i>defined by</i> 	: UniqueFeature::feature</li>
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * </ul>
	 * 
	 */
	protected SortedSet<STRUCTURAL> features = null;



	/**
	 * Identifies the KeyRelationship instances that reference this UniqueKey instance.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : generic KEY_RELATION</li>
	 * <li><i>defined by</i>    : UniqueKeyRelationship::keyRelationship</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * <li><i>inverse</i>       : KeyRelationship::uniqueKey</li>
	 * </ul>
	 * 
	 */
	protected Set<KEY_RELATION> keyRelationships = null;
}