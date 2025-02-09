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

import java.util.List;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;


/**
 * KeyRelationship instances represent relationships between UniqueKey instances and the Class(es) that reference them. This class is intended as a 
 * starting point for the creation of foreign key and other associative relationships.
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation_C_6_2]</b> A KeyRelationship instance must be owned by one and only one Class instance.</li>
 * </ul> 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this KeyRelationship.
 * @param <PACKAGE> The type of the importer of this KeyRelationship.
 * @param <UNIQUE_KEY> Identifies the type of UniqueKey that serves as the primary key for this KeyRelationship.
 * @param <STRUCTURAL> Identifies the type of StructuralFeature that participate as (part of) the key of this KeyRelationship.
 * 
 */
public interface GKeyRelationship<
	  NAMESPACE  extends GCwmClass<?, ?, ?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, UNIQUE_KEY extends GUniqueKey<?, ?, ?, ?>
	, STRUCTURAL extends GStructuralFeature<?, ?, ?, ?, ?, ?>
> extends 
	GModelElement<NAMESPACE, PACKAGE, Dependency, Constraint> 
{


	/**
	 * Identifies StructuralFeature instances that participate as (part of) the key of this KeyRelationship instance.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : StructuralFeature</li>
	 * <li><i>defined by</i> 	: KeyRelationshipFeatures::feature</li> 
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * </ul>
	 * 
	 * @return A List of StructuralFeature instances that participate as (part of) the key of this KeyRelationship instance. Cannot 
	 * be <code>null</code>.
	 * 
	 */
	List<STRUCTURAL> getFeatures();



	/**
	 * Removes a feature from this index.
	 * <p>
	 * An KeyRelationship must have at least one StructuralFeature, so if the feature to be removed is the last found 
	 * within the set of features it can't be removed!
	 * </p>
	 * 
	 * @param feature The feature that will be removed from this KeyRelationship.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeFeature( GStructuralFeature<?, ?, ?, ?, ?, ?> feature ); 



	/**
	 * Identifies the UniqueKey instance that serves as the primary key for this KeyRelationship instance.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : UniqueKey</li>
	 * <li><i>defined by</i>    : UniqueKeyRelationship::uniqueKey</li> 
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * <li><i>inverse</i>       : UniqueKey::keyRelationship</li> 
	 * </ul>
	 *
	 * <p>
	 * <b>Constraint</b>:
	 * </p>
	 * <ul>
	 * <li><b>[Foundation_C_6_2]</b> A KeyRelationship instance must be owned by one and only one Class instance.</li>
	 * </ul>
	 * 
	 * @return The UniqueKey instance that serves as the primary key for  this KeyRelationship instance.
	 * 
	 */
	UNIQUE_KEY getUniqueKey();



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Adds a dependency to this KeyRelationship.
	 * 
	 * @param dependency A Dependency in witch this KeyRelationship is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this KeyRelationship.
	 * 
	 * @param constraint A Constrain that must be satisfied by this KeyRelationship.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );
}