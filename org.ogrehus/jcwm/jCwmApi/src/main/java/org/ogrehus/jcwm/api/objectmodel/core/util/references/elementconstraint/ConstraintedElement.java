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

import java.util.SortedSet;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;

public interface ConstraintedElement<
	CONSTRAINTED extends UnderConstraint<? extends ConstraintedElement<?>>
> extends
	ModelElement
{
	
	
	/**
	 * Removes an element from the the list of ModelElements affected by the Constraint.
	 * 
	 * @param constrainted The constarintedElement to be removed.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeConstraintedElement( UnderConstraint<?> constrainted );



	/**
	 * Returns the referenced constrainedElement.
	 * <p> 
	 * A list of ModelElements affected by the Constraint.
	 * </p>
	 * <p>
	 * The ElementConstraint association provides linkages between ModelElements and the Constraint instances that constrain their state. Note that a
	 * Constraint instance may not simultaneously participate in both the ElementConstraint and the StereotypeConstraint associations.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: ElementConstraint::constrainedElement</li>
	 * <li><i>multiplicity</i>	: zero or more, ordered</li>
	 * <li><i>inverse</i>		: ModelElement::constraint</li>
	 * </ul>
	 * 
	 * 
	 * @return A Collection of the ModelElements affected by this constraint. Can not be <code>null</code>, but may be an empty Collection.
	 * 
	 */
	SortedSet<CONSTRAINTED> getConstraintedElements();



	/**
	 * Returns all elements under this Constraint by a specific type.
	 * 
	 * @param classOfElements All returned elements implement or extends this class.
	 * 
	 * @return A Collection elements under this Constraint by a specific type. Can't be <code>null</code> but may be an empty collection, if none is
	 * available.
	 * 
	 */
	<E extends CONSTRAINTED> SortedSet<E> getConstraintedElements( Class<E> classOfElements );
}