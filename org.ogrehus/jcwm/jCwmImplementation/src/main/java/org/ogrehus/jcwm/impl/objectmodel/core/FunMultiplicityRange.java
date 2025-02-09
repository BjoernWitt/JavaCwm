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
package org.ogrehus.jcwm.impl.objectmodel.core;

import org.ogrehus.jcwm.api.Cwm;
import org.ogrehus.jcwm.api.objectmodel.core.Multiplicity;
import org.ogrehus.jcwm.api.objectmodel.core.MultiplicityRange;

import org.ogrehus.jcwm.impl.FunCwm;


public class FunMultiplicityRange
implements
	MultiplicityRange 
{


	/**
	 * Creates a new instance of MultiplicityRange by specific parameters.
	 * 
	 * @param lower Specifies the positive integer lower bound of the range. Must not be <code>null</code>.
	 * 
	 * @param upper Specifies the upper bound of the range, which is a positive integer or the special value unlimited indicating no upper bound is
	 * defined. unlimited is represented by value <code>null</code>.
	 * 
	 * @param multiplicity The Multiplicity instance that owns the MultiplicityRange. Must not be <code>null</code>.
	 * 
	 * @return A new instance of MultiplicityRange, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>lower</code> or <code>multiplicity</code> is <code>null</code>.
	 * 
	 */	
	protected FunMultiplicityRange( Integer lower, Integer upper, Multiplicity multiplicity ) {
		if ( lower == null ) {
			throw new NullPointerException("Parameter: lower must not be null.");
		}
		if ( upper == null ) {
			throw new NullPointerException("Parameter: multiplicity must not be null.");
		}
		this.lower = lower;
		this.upper = upper;
		this.multiplicity = multiplicity;
		try {
			this.cwmFactory = Cwm.create( FunCwm.class.getName() );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// can't occur, cause otherwise this class would be missing too! 
		}
	}



	public Cwm getCwmFactory() {
		return this.cwmFactory;
	}



	public Integer getLower() {
		return this.lower;
	}



	public Multiplicity getMultiplicity() {
		return this.multiplicity;
	}



	public Integer getUpper() {
		return this.upper;
	}



//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * Attribute: lower
	 * 
	 * Specifies the positive integer lower bound of the range.
	 * <ul>
	 * <li><i>type</i>: Integer</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Integer lower = null;



	/**
	 * Attribute: upper
	 * 
	 * Specifies the upper bound of the range, which is a positive integer or the special value �unlimited� indicating no upper bound is defined.
	 * 
	 * <ul>
	 * <li><i>type</i>: UnlimitedInteger</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Integer upper = null;



	/**
	 * Reference: multiplicity
	 * 
	 * References the Multiplicity instance that owns the MultiplicityRange.
	 * 
	 * <ul>
	 * <li><i>class</i>: Multiplicity</li>
	 * <li><i>defined by</i>: RangeMultiplicity::multiplicity</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * <li><i<>inverse</i>: Multiplicity::range</li>
	 * </ul>
	 * 
	 */
	protected Multiplicity multiplicity = null;



	protected Cwm cwmFactory = null;
}