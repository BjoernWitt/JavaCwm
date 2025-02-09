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
package org.ogrehus.jcwm.api.objectmodel.instance;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;


/**
 * The instance construct defines an entity to which a set of operations can be applied and which has a state that stores the effects of the 
 * operations. 
 * <p>
 * In the meta-model Instance is connected to a Classifier that declares its structure and behavior. It has a set of attribute values matching the 
 * definition of its Classifier. The set of attribute values implements the current state of the Instance.
 * </p>
 * <p>
 * Because Instance is an abstract class, all Instances are either Object or DataValue instances. 
 * </p>
 * <p>
 * The data content of an Instance comprises one value for each attribute in its full descriptor (and nothing more). The value must be consistent with
 * the type of the attribute. An instance must obey any constraints on the full descriptor of the Classifier of which it is an instance (including 
 * both explicit constraints and built-in constraints such as multiplicity).
 * </p>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <VALUE_SLOT> The type of ClientDependency of this ModelElement.
 * @param <CLASSIFIER> The type of this Instance. 
 * 
 */
public interface Instance<
	CLASSIFIER extends GClassifier<?, ?, ?, ?, ?, ?>
> extends 
	ModelElement
{


	/**
	 * Reference: classifier
	 * <p>
	 * The Classifier that declares the structure of the Instance. The InstanceClassifier association links Instances with Classifiers that describe 
	 * them.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : Classifier</li>
	 * <li><i>defined by</i>   : InstanceClassifier::classifier</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>  
	 * 
	 * @return The Classifier that declares the structure of the Instance.
	 * 
	 */
	CLASSIFIER getClassifier();



	/**
	 * Reference: The Classifier that declares the structure of the Instance.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : Classifier</li>
	 * <li><i>defined by</i>   : InstanceClassifier::classifier</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @see #getClassifier() For a descritpion on the property <code>classifier</code>.
	 * 
	 * @param classifier The new value for this reference
	 * 
	 * @return The old Classifier that declares the structure of the Instance. May not be <code>null</null>. 
     * 
     * @throws NullPointerException Is thrown if the parameter <code>classifier</code> ist <code>null</code>.
	 * 
	 */
	CLASSIFIER setClassifier( CLASSIFIER classifier );
}