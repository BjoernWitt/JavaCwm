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
package org.ogrehus.jcwm.api.objectmodel.core.util.references.elementconstraint;

import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;

public interface UnderConstraint<CONSTRAINT extends ConstraintedElement<? extends UnderConstraint<?>>>
extends
	ModelElement
{


	/**
	 * Removes a constraint that was affecting this element.
	 * 
	 * @param constraint A Constrain that musn't be satisfied by this ModelElement any more. 
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.   
	 * 
	 */
	Boolean removeConstraint( ConstraintedElement<?> constraint );



	/**
	 * A set of Constraints affecting the element.
	 * <p>
	 * A constraint that must be satisfied by the model element. A model element may have a set of constraints. The constraint is to be evaluated when
	 * the system is stable; that is, not in the middle of an atomic operation.
	 * </p>
	 * <p>
	 * The ElementConstraint association provides linkages between  ModelElements and the Constraint instances that constrain their state. Note that a
	 * Constraint instance may not simultaneously participate in both the ElementConstraint and the StereotypeConstraint associations.
	 * </p>
	 * <p>
	 * The StereotypeConstraints association links Stereotypes with Constraints that further restrict the states that a stereotyped ModelElement may 
	 * assume. A Constraint instance may not simultaneously participate in both the StereotypeContraints association and the ElementConstraint 
	 * association.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>   
	 * <ul>
	 * <li><i>class</i>			: Constraint</li>
	 * <li><i>defined by</i>	: ElementConstraint</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: Constraint::constrainedElement</li>
	 * </ul>
	 * 
	 * @return A Set of all Constrains that must be satisfied by this ModelElement. 
	 * 
	 */
	Set<CONSTRAINT> getConstraints();
}