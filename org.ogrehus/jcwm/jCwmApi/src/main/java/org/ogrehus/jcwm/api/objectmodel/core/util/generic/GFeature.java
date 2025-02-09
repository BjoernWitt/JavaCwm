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

import org.ogrehus.jcwm.api.objectmodel.core.util.references.classifierfeature.Featured;


/**
 * A feature is a property, like attribute or operation that is encapsulated within a Classifier.
 * 
 * <p>
 * In the meta-model a GFeature declares a structural or behavioral characteristic of an instance of a Classifier or of the Classifier itself. 
 * GFeature is an abstract meta-class.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, August 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GFeature.
 * @param <PACKAGE> The type of the importer of this GFeature.
 * @param <DEPENDENCY> The type of ClientDependency of this GFeature.
 * @param <CONSTRAINT> The type of the Constraint under which this GFeature is. 
 * @param <CLASSIFIER> The type of the Classifier that owns this GFeature. 
 *
 */
public interface GFeature<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, CLASSIFIER extends GClassifier< ?, ?, ?, ?, ?, ?>
>extends
	  GModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT>
	, Featured<CLASSIFIER>
{
	// only configurational purpose in type definition in interface
}