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
 * In the meta-model Changeable defines an enumeration that denotes how an attribute link may be modified.
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>changeable</code> - No restrictions on modification.</li>
 * <li><code>frozen</code> - The value may not be changed from the source end after the creation and initialization of the source object. Operations 
 * on the other end may change a value.</li>
 * <li><code>addOnly</code> - If the multiplicity is not fixed, values may be added at any time from the source object, but once created a value may 
 * not be removed from the source end. Operations on the other end may change a value.</li>
 * <li>The default value is <code>changeable</code>.</li>
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public enum Changeable {
	
	  changeable
	, frozen
	, addOnly;
	
	public static Changeable getDefault() { return Changeable.changeable; } 
}