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
 * In the meta-model Visibility defines an enumeration that denotes how the element to which it refers is seen outside the enclosing name space. 
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>vk_public</code> - Other elements may see and use the target element.</li>
 * <li><code>vk_protected</code> - Descendants of the source element may see and use the target element.</li>
 * <li><code>vk_private</code> - Only the source element may see and use the target element.</li>
 * <li><code>vk_package</code> - Elements declared in the same package as the target element may see and use the target element.</li>
 * <li><code>vk_notapplicable</code> - May be used where namespaces do not support the concept of visibility.</li>
 * <li>The default value is <code>vk_public</code>.</li>
 * </ul>
 *  
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, August 2008
 * 
 */
public enum Visibility {
	  _public
	, _protected
	, _private
	, _package
	, notapplicable
	;

	public static Visibility getDefault() {
		return Visibility._public; 
	}
}