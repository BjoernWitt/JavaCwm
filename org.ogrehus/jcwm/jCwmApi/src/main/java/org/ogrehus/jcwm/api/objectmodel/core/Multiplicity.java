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

import java.util.Set;


/**
 * In the meta-model a Multiplicity defines a non-empty set of non-negative integers. 
 * <p>
 * A set that only contains zero ({0}) is not considered a valid Multiplicity. Every Multiplicity has at least one corresponding String 
 * representation.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 *
 */
public interface Multiplicity
extends
	Element 
{


	/**
	 * Adds a MultiplicityRange to this Multiplicity.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addRange( Integer upper, Integer lower );
	
	
	/**
	 * Removes a MultiplicityRange from the reference <code>ranges</code>.
	 * 
	 * <p>
	 * If the number of ranges can't be smaller than 1. If a range should be remove even if there is only 
	 * 1 range left, the removal won't proceed and <code>false</code> will be returned.
	 * </p>
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise. 
	 * <b>Remind:</b> the number of ranges must not be smaller than 1!
	 * 
	 */
	Boolean removeRange( Integer upper, Integer lower );



	/**
	 * Returns a Set of MultiplicityRanges defining the range of an Element.
	 * <p>
	 * References the set of MultiplicityRange instances that describe the cardinality of the Multiplicity instance.
	 * </p>
	 * <p>
	 * The RangeMultiplicity association identifies the set of MultiplicityRange instances that specify the lower and upper bounds of individual 
	 * cardinality ranges defined by a Multiplicity instance. A MultiplicityRange instance must be owned by a single Multiplicity instance.
	 * </p>
	 * <ul>
	 * <li><i>class</i>: MultiplicityRange</li>
	 * <li><i>defined by</i>: RangeMultiplicity</li>
	 * <li><i>multiplicity</i>: one or more</li>
	 * <li><i>inverse</i>: MultiplicityRange::multiplicity</li>
	 * </ul>
	 * 
	 * @return A Set of MultiplicityRanges describing  
	 * 
	 */
	Set<MultiplicityRange> getRanges();
}