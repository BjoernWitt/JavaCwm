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

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;

/**
 * Instances of the IndexedFeature class map StructuralFeature instances of the spanned Class instance to the Index  instances that employ them as 
 * (part of) their key. 
 * <p>
 * Attributes of IndexedFeature instances indicate how specific StructuralFeature instances are used in Index keys.
 * </p> 
 *
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-6-1]</b> The isAscending attribute is valid only if the
 *  <code>isSorted</code> attribute is True.</li>
 *  </ul>
 *  
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
 * @param <INDEX> 
 * @param <STRUCTURAL> 
 * 
 */
public interface GIndexedFeature<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, INDEX      extends GIndex<?, ?, ?, ?>
	, STRUCTURAL extends GStructuralFeature<?, ?, ?, ?, ?, ?>
> extends 
	GModelElement<NAMESPACE, PACKAGE, Dependency, Constraint> 
{


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
	 * @return <code>true</code> if the feature is sorted in ascending order and <code>false</code>, if descending order and <code>null</code>, if 
	 * the Index is not sorted. 
	 * 
	 */
	Boolean isAscending();



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
	 * <li><b>[Foundation_C_6_1]</b> The isAscending attribute is valid only if the <code>isSorted</code> attribute is <code>true</code>.</li>
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
	Boolean setAscending( Boolean isAscending ); 



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
	INDEX getIndex();



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
	 * @return The old value of the property <code>index</code>. Cannot be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>index</code> was <code>null</code>. 
	 * 
	 */
	INDEX setIndex( INDEX index );



	/**
	 * Removes the Index instance for which this IndexedFeature instance is relevant.
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
	 * @return The old value of the property <code>index</code>. Cannot be <code>null</code>.
	 * 
	 */
	Boolean removeIndex();



	/**
	 * Identifies the StructuralFeature instance for which this IndexedFeature instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : StructuralFeature</li>
	 * <li><i>defined by</i> 	: IndexedFeatures::feature</li> 
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return The StructuralFeature instance for which this IndexedFeature instance is relevant. Can't be 
	 * <code>null</code>.
	 * 
	 */
	STRUCTURAL getFeature();



	/**
	 * Removes the StructuralFeature instance from this IndexedFeature.
	 * <p>
	 * Identifies the StructuralFeature instance for which this IndexedFeature instance is relevant.
	 * </p>
	 * 
	 * @return <code>true</code> if the Feature was removed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeFeature();
}