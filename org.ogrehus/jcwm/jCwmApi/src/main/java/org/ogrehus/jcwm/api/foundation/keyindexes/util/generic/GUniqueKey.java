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

import java.util.Set;
import java.util.SortedSet;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;


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
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
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
public interface GUniqueKey<
	  NAMESPACE    extends GCwmClass<?, ?, ?, ?, ?, ?, ?>
	, PACKAGE      extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, KEY_RELATION extends GKeyRelationship<?, ?, ?, ?>
	, STRUCTURAL   extends GStructuralFeature<?, ?, ?, ?, ?, ?>
> extends 
	GModelElement<NAMESPACE, PACKAGE, Dependency, Constraint>
{


	/**
	 * Identifies the StructuralFeature instances that make up the unique key. 
	 * 
	 * <p>
	 * The ordered constraint is used to represent the sequence of StructuralFeature instances that make up the UniqueKey instance's key.
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
	 * @return Set of generic STRUCTURAL instances that make up the unique key. 
	 * 
	 */
	SortedSet<STRUCTURAL> getFeatures();



	/**
	 * Removes a feature from this index.
	 * <p>
	 * An UniqueKey must have at least one StructuralFeature, so if the feature to be removed is the last found within the set of features it can't 
	 * be removed!
	 * </p>
	 * 
	 * @param feature The feature that will be removed from this KeyRelationship.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeFeature( GStructuralFeature<?, ?, ?, ?, ?, ?> feature );



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
	 * @return A set of the generic KEY_RELATION instances that reference this UniqueKey instance.
	 * 
	 */
	Set<KEY_RELATION> getKeyRelationships();



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
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this UniqueKey.
	 * 
	 * @param constraint A Constrain that must be satisfied by this UniqueKey.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );
}