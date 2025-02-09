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
 * String defines a piece of text. 
 * 
 * <p>
 * Strings do not normally have a defined length; rather, they are considered to be arbitrarily long (practical limits on the length of Strings exist,
 * but are implementation dependent). When String is used as the type of an Attribute, string length sometimes can be specified (see the Relational 
 * and Record packages for examples).
 * </p>
 * 
 * <p>
 * The default for the String data type is an empty string.
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
public interface CwmString
extends
	DataType
{



	/**
	 * The default for data type String is <code>""</code> (zerostring).
	 * 
	 * @return <code>""</code> - an empty String.
	 * 
	 */
	public String getDefaultValue();
}