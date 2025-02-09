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

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.dependencyclient.ClientDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.elementconstraint.UnderConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.elementownership.OwnedElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.importedelements.Importer;


/**
 * A model element is an element that is an abstraction drawn from the system being modeled.
 * <p>
 * In the meta-model a ModelElement is a named entity in a Model. It is the base for all modeling meta-classes in the CWM. All other modeling 
 * meta-classes are either direct or indirect subclasses of ModelElement.
 * </p>
 * <p>
 * TODO: implement Constraints may be by Constructor or methods
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-3-3]</b> Tags associated with a model element (directly via a property list or indirectly via a stereotype) must not clash with any meta 
 * attributes associated with the model element.</li>
 * <li><b>[C-3-4]</b> A model element must have at most one tagged value with a given tag name.</li>
 * <li><b>[C-3-5]</b> A stereotype cannot extend itself.</li>
 * </ul>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this ModelElement.
 * @param <PACKAGE> The type of the importer of this ModelElement.
 * @param <DEPENDENCY> The type of ClientDependency of this ModelElement.
 * @param <CONSTRAINT> The type of the Constraint under which this ModelElement is. 
 * 
 */
public interface GModelElement<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
> extends 
	  ModelElement
	, OwnedElement<NAMESPACE>
	, Importer<PACKAGE>
	, ClientDependency<DEPENDENCY>
	, UnderConstraint<CONSTRAINT>
{
	// only configurational purpose in type definition in interface
}