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

/**
 * A structural feature refers to a static feature of a model element.
 * 
 * <p>
 * In the meta-model, a StructuralFeature declares a structural aspect of a Classifier that is typed, such as an attribute. For example, it specifies 
 * the multiplicity and changeability of the StructuralFeature. StructuralFeature is an abstract meta-class.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, August 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GStructuralFeature.
 * @param <PACKAGE> The type of the importer of this GStructuralFeature.
 * @param <DEPENDENCY> The type of ClientDependency of this GStructuralFeature.
 * @param <CONSTRAINT> The type of the Constraint under which this GStructuralFeature is. 
 * @param <OWNER> The type of the Classifier that owns this GStructuralFeature.
 * @param <TYPE> The type of a GStructuralFeature.  
 *
 */
public interface GStructuralFeature<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWNER      extends GClassifier< ?, ?, ?, ?, ?, ?>
	, TYPE       extends GClassifier< ?, ?, ?, ?, ?, ?>
> extends
	GFeature<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWNER> 
{


	/**
	 * Returns the reference of this <code>type</code>. 
	 * <p>
	 * Designates the Classifier whose instances are values of the feature. It must be a Class, DataType, or Interface.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Classifier</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return A Classifier describing the type of this structuralFeature. Can not be <code>null</code>.
	 * 
	 */
	TYPE getType();
}