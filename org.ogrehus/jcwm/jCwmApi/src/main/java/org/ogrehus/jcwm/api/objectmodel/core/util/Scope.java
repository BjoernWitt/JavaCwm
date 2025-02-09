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
 * In the meta-model Scope defines an enumeration that denotes whether a feature belongs to individual instances or an entire classifier.
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>instance</code> - The feature pertains to instances of a GClassifier. For example, it is a distinct attribute in each instance or an 
 * operation that works on an instance.</li>
 * <li><code>classifier</code> - The feature pertains to an entire GClassifier. For example, it is an attribute shared by the entire GClassifier or 
 * an operation that works on the GClassifier, such as a creation operation.</li>
 * <li>The default value is <code>instance</code>.</li>
 * </ul>
 *  
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, August 2007
 * 
 */
public enum Scope {
	
	  instance
	, classifier;

	public static Scope getDefault() {
		return Scope.instance; 
	}
}