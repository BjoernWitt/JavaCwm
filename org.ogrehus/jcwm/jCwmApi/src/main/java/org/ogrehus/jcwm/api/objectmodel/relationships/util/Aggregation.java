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
package org.ogrehus.jcwm.api.objectmodel.relationships.util;

/**
 * An enumeration that denotes what kind of aggregation an Association defines.
 * <p>
 * When placed on a target end, specifies the relationship of the target end to the source end.
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>none</code> - The end is not an aggregate.</li>
 * <li><code>aggregate</code> - The end is an aggregate; therefore, the other end is a part and must have the aggregation value of none. The part may 
 * be contained in other aggregates.</li>
 * <li><code>composite</code> - The end is a composite; therefore, the other end is a part and must have the aggregation value of none. The part is 
 * strongly owned by the composite and may not be part of any other composite.</li>
 * <li>The default value is <code>none</code>.</li>
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public enum Aggregation {

	  none
	, aggregate
	, composite;


	public static Aggregation getDefault() { return Aggregation.none; } 

}
