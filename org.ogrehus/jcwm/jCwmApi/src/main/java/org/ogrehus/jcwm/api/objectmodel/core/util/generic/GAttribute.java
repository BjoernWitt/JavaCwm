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
package org.ogrehus.jcwm.api.objectmodel.core.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.Expression;


/**
 * An Attribute describes a named slot within a Classifier that may hold a value.
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GAttribute.
 * @param <PACKAGE> The type of the importer of this GAttribute.
 * @param <DEPENDENCY> The type of ClientDependency of this GAttribute.
 * @param <CONSTRAINT> The type of the Constraint under which this GAttribute is. 
 * @param <OWMER> The type of the Classifier that owns this GAttribute.
 * @param <TYPE> The type of a GAttribute.  
 *
 */
public interface GAttribute<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWMER      extends GClassifier< ?, ?, ?, ?, ?, ?>
	, TYPE       extends GClassifier< ?, ?, ?, ?, ?, ?>
> extends
	GStructuralFeature<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWMER, TYPE>
{


	/**
	 * Returns the value of the property <code>initialValue</code>.
	 * <p> 
	 * An Expression specifying the value of the attribute upon initialization. It is meant to be evaluated at the time the object is initialized. 
	 * (Note that an explicit constructor may supersede an initial value.)
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>: Expression</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @return An Expression specifying the value of the attribute upon initialization. Might be <code>null</code> if none exists.
	 *  
	 */
	Expression getInitialValue();



	/**
	 * Sets the initialValue for instantiation.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>   
	 * <ul>
	 * <li><i>type</i>: Expression</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @param initialValue The initial value to be used for instantiation. 
	 * 
	 * @see #getInitialValue() for description of the property <code>initialValue</code>. 
	 * 
	 * @return The old Expression specifying the value of the attribute upon initialization.
	 *  
	 */
	Expression setInitialValue( Expression initialValue );
}