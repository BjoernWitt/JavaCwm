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

import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GIndexedFeature;
import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;
import org.ogrehus.jcwm.api.objectmodel.relationships.AssociationEnd;


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
 */
public interface IndexedFeature
extends
	GIndexedFeature<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Index
		, GStructuralFeature<?, ?, ?, ?, ?, ?>
	>
{



	/**
	 * Identifies the Attribute instance for which this IndexedFeature instance is relevant.
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
	 * @param attribute The StructuralFeature instance for which this IndexedFeature instance is relevant. May not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>feature</code>. Cannot be <code>null</code>. 
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>index</code> was <code>null</code>.
	 * 
	 */
	GStructuralFeature<?, ?, ?, ?, ?, ?> setFeature( Attribute attribute );



	/**
	 * Identifies the AssociationEnd instance for which this IndexedFeature instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : AssociationEnd</li>
	 * <li><i>defined by</i> 	: IndexedFeatures::feature</li> 
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 *
	 * @param associationEnd The AssociationEnd instance for which this IndexedFeature instance is relevant. May not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>feature</code>. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>index</code> was <code>null</code>.
	 * 
	 */
	GStructuralFeature<?, ?, ?, ?, ?, ?> setFeature( AssociationEnd associationEnd );
}