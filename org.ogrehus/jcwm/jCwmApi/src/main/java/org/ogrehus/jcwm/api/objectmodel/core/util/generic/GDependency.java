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

import org.ogrehus.jcwm.api.objectmodel.core.util.references.dependencyclient.Clientable;

/**
 * A dependency states that the implementation or functioning of one or more elements requires the presence of one or more other elements.
 * <p>
 * In the meta-model a Dependency is a directed relationship from a client (or clients) to a supplier (or suppliers) stating that the client is 
 * dependent on the supplier; that is, the client element requires the presence and knowledge of the supplier element.
 * </p>
 * <p>
 * A dependency specifies that the semantics of a set of model elements requires the presence of another set of model elements. This implies that if 
 * the source is somehow modified, the dependents probably must be modified. The reason for the dependency can be specified in several different ways 
 * (for example, using natural language or an algorithm) but is often implicit.
 * </p>
 * <p>
 * Whenever the supplier element of a dependency changes, the client element is potentially invalidated. After such invalidation, a check should be 
 * performed followed by possible changes to the derived client element. Such a check should be performed after which action can be taken to change 
 * the derived element to validate it again. 
 * </p>
 * <p>
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by 
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this Dependency.
 * @param <PACKAGE> The type of the importer of this Dependency.
 * @param <DEPENDENCY> The type of ClientDependency of this Dependency.
 * @param <CONSTRAINT> The type of the Constraint under which this Dependency is. 
 * @param <CLIENT> Type of the client under this Dependency
 * 
 */
public interface GDependency<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, CLIENT     extends GModelElement<?, ?, ?, ?>
> extends
	  GModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT>
	, Clientable<CLIENT>
{


	/**
	 * Returns the value of the property <code>kind</code>.
	 * 
	 * Contains a description of the nature of the dependency relationship between the client and supplier. The list of possible values is open-ended. 
	 * However, CWM predefines the values Abstraction and Usage.
	 *  
	 * <ul>
	 * <li><i>type</i>			: String</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * </ul>
	 * 
	 * @return The kind of this dependency if one exists, otherwise <code>null</code>.
	 * 
	 */
	String getKind();



	/**
	 * Sets the value of the property <code>kind</code>.
	 * <ul>
	 * <li><i>type</i>			: String</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * </ul>
	 * 
	 * @param kind The new value of the property <code>kind</code>. 
	 * 
	 * @return The old value of the property <code>kind</code> if one exists, otherwise <code>null</code>.
	 *
	 *  @see #getKind() for Information about this property.
	 */
	String setKind( String kind );
}