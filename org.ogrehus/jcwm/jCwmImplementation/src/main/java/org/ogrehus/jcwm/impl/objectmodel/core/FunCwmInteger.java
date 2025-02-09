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

import org.ogrehus.jcwm.api.objectmodel.core.CwmInteger;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

/**
 * Integer represents the predefined type of integers. 
 * 
 * <p>
 * An instance of Integer is an element in the (infinite) set of integers (-2, -1, 0, 1, 2).
 * </p>
 * 
 * <p>
 * The default for Integer is 0.
 * </p>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunCwmInteger
extends
	FunDataType
implements
	CwmInteger
{


	/**
	 * Crates a new instance of FunCwmInteger by initial predefined parameters.
	 * 
	 * <p>
	 * Predefined Parameters
	 * </p>
	 * <ul>
	 * <li><code>name</code> - Integer</li>
	 * <li><code>visibility</code> - public</li>
	 * <li><code>notInstantiable</code> - true</li>
	 * </ul>
	 * 
	 */
	public FunCwmInteger() {
		super( "Integer", Visibility._public, Boolean.TRUE );
	}



	/**
	 * The default for data type Integer is <code>"0"</code>.
	 * 
	 * @return <code>"0"</code>
	 * 
	 */
	@Override
	public String getDefaultValue() {
		return "0";
	}
}