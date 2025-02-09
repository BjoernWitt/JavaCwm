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
package org.ogrehus.jcwm.api.objectmodel.core.util;

/**
 * In the meta-model OrderingKind defines an enumeration that specifies how the elements of a set are arranged. 
 * <p>
 * Used in conjunction with elements that have a multiplicity in cases when the multiplicity value is greater than one. The ordering must be 
 * determined and maintained by operations that modify the set.
 * </p>
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>ok_unordered</code> - The elements of the set have no inherent ordering.</li>
 * <li><code>ok_ordered</code> - The elements of the set have a sequential ordering.</li>
 * <li>The default value is <code>ok_unordered</code>.</li>
 * </ul>
 *  
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public enum Ordering {

	  unordered
	, ordered;
	
	public static Ordering getDefault() { return Ordering.unordered; }
}