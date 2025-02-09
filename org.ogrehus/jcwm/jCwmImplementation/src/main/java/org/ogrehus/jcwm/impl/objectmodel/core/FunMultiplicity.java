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

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.Cwm;
import org.ogrehus.jcwm.api.objectmodel.core.Multiplicity;
import org.ogrehus.jcwm.api.objectmodel.core.MultiplicityRange;
import org.ogrehus.jcwm.impl.FunCwm;


public class FunMultiplicity
implements
	Multiplicity 
{


	/**
	 * Creates a new instance of Multiplicity by specific parameters.
	 * 
	 * @param lower Specifies the positive integer lower bound of the range. Must not be <code>null</code>.
	 * 
	 * @param upper Specifies the upper bound of the range, which is a positive integer or the special value <i>unlimited</i> indicating no upper
	 * bound is defined. <i>unlimited</i> is represented by value <code>null</code>.
	 *  
	 * @return A new instance of Multiplicity, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if the parameter: <code>lower</code> is <code>null</code>.
	 * 
	 */
	public FunMultiplicity( Integer lower, Integer upper ) {
		this.ranges = new HashSet<MultiplicityRange>();
		addRange( lower, upper ); // throws NullPointerException
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



	public Boolean addRange( Integer lower, Integer upper ) {
		MultiplicityRange range = new FunMultiplicityRange( lower, upper, this ); // Throws NullPointerException 
		return this.ranges.add( range );
	}



	public Boolean removeRange( MultiplicityRange range ) {
		if ( range == null ) {
			return Boolean.TRUE;
		}
		
		if ( this.ranges.size() < 2 ) {
			return Boolean.FALSE; // does not allow to remove any range, must have at least one left!
		}
		if ( this.ranges.remove( range ) ) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



	public Boolean removeRange( Integer lower, Integer upper ) {
		if ( this.ranges.size() < 2 ) {
			return Boolean.FALSE; // does not allow to remove any range, must have at least one left!
		}
		return removeRange( new FunMultiplicityRange(lower, upper, this ) );
	}



	public Set<MultiplicityRange> getRanges() {
		return this.ranges;
	}



//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * Reference: range
	 * 
	 * References the set of MultiplicityRange instances that describe the cardinality of the Multiplicity instance.
	 * 
	 * <ul>
	 * <li><i>class</i>: MultiplicityRange</li>
	 * <li><i>defined by</i>: RangeMultiplicity</li>
	 * <li><i>multiplicity</i>: one or more</li>
	 * <li><i>inverse</i>: MultiplicityRange::multiplicity</li>
	 * 
	 */
	protected Set<MultiplicityRange> ranges = null;



	protected Cwm cwmFactory = null;
}