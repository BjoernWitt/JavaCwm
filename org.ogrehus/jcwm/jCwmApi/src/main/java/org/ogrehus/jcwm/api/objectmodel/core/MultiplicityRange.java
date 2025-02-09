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
package org.ogrehus.jcwm.api.objectmodel.core;


/**
 * In the meta-model a MultiplicityRange is immutable and defines a range of integers. 
 * 
 * <p>
 * The upper bound of the range cannot be below the lower bound. The lower bound must be a nonnegative integer. The upper bound must be a nonnegative 
 * integer or the special value unlimited, which indicates there is no upper bound on the range. 
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, August 2008
 * 
 */
public interface MultiplicityRange
extends
	Element
{


	/**
	 * Returns the lower Range of this MultiplicityRange.
	 * <p>
	 * Specifies the positive integer lower bound of the range.
	 * </p>
	 * <ul>
	 * <li><i>type</i>: Integer</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return The lower bound of this range.
	 * 
	 */
	Integer getLower();



	/**
	 * Returns the upper Range of this MultiplicityRange.
	 * <p>
	 * Specifies the upper bound of the range, which is a positive integer or the special value unlimited indicating no upper bound is defined.
	 * </p>
	 * <ul>
	 * <li><i>type</i>: UnlimitedInteger</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return The upper bound of this range. 
	 * 
	 */
	Integer getUpper();



	/**
	 * Returns a reference of the multiplicity of this Range.
	 * <p>
	 * References the Multiplicity instance that owns the MultiplicityRange.
	 * </p>
	 * <p>
	 * The RangeMultiplicity association identifies the set of MultiplicityRange instances that specify the lower and upper bounds of individual 
	 * cardinality ranges defined by a Multiplicity instance. A MultiplicityRange instance must be owned by a single Multiplicity instance.
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Multiplicity</li>
	 * <li><i>defined by</i>: RangeMultiplicity::multiplicity</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * <li><i>inverse</i>: Multiplicity::range</li>
	 * </ul>
	 * 
	 * @return The multiplicity that owns this range.
	 * 
	 */
	Multiplicity getMultiplicity();
}