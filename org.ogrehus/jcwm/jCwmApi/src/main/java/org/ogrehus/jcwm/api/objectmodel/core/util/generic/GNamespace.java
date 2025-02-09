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

import org.ogrehus.jcwm.api.objectmodel.core.util.references.elementownership.Namespace;

/**
 * A namespace is a part of a model that contains a set of ModelElements each of whose names designates a unique element within the namespace. 
 * <p>
 * In the meta-model, a Namespace is a ModelElement that can own other ModelElements, such as Classifiers. The name of each owned ModelElement must 
 * be unique within the Namespace. Moreover, each contained ModelElement is owned by at most one Namespace. The concrete subclasses of Namespace may 
 * have additional constraints on which kind of elements may be contained.
 * </p>
 * <p>
 * Namespace is an abstract metaclass.
 * </p>
 * <p>
 * Note that explicit parts of a model element, such as the features of a GClassifier, are not modeled as owned elements in a namespace. A namespace 
 * is used for unstructured contents such as the contents of a package, or a class declared inside the scope of another class. 
 * </p>
 * <p>
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by 
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 *  
 * @param <NAMESPACE> The type of the namespace that contains this namespace.
 * @param <PACKAGE> The type of the importer of this namespace.
 * @param <DEPENDENCY> The type of Dependency of this Namespace.
 * @param <CONSTRAINT> The type of the Constraint under which this namespace is. 
 * @param <OWNED> Type of the ownedElements by this namspace 
 *    
 */
public interface GNamespace<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWNED      extends GModelElement<?, ?, ?, ?>
> extends 
	  GModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT> 
	, Namespace<OWNED>
{
	// only configurational purpose in type definition in interface
}