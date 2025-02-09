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

import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;


/**
 * A model captures a view of a physical system. 
 * 
 * <p>
 * It is an abstraction of the physical system, with a certain purpose. The model completely describes those aspects of the physical system that are 
 * relevant to the purpose of the model, at the appropriate level of detail.
 * </p>
 * <p>
 * In the meta-model Model is a subclass of Package. It contains a containment hierarchy of ModelElements that together describe the physical system. 
 * A Model also contains a set of ModelElements that represents the environment of the system.
 * </p>
 * <p>
 * Different Models can be defined for the same physical system, where each model represents a view of the physical system defined by its purpose 
 * and abstraction level; for example, an analysis model, a design model, an implementation model. Typically different models are complementary and 
 * defined from the perspectives (viewpoints) of different system stakeholders.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this Package.
 * @param <PACKAGE> The type of the importer of this Package.
 * @param <DEPENDENCY> The type of ClientDependency of this Package.
 * @param <CONSTRAINT> The type of the Constraint under which this Package is. 
 * @param <OWNED> Type of the ownedElements by this Package 
 * @param <IMPORTED> Type of the importedElements of this Package.
 *    
 */
public interface GModel<
	  MANAGER    extends GDataManager<?, ?>
	, NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWNED      extends GModelElement<?, ?, ?, ?>
	, IMPORTED   extends GModelElement<?, ?, ?, ?>
> extends
	  GCwmPackage<MANAGER, NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWNED, IMPORTED>
{
	// only configurational purpose in type definition in interface
}
