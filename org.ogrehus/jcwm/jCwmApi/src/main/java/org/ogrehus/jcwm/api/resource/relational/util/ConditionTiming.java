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
package org.ogrehus.jcwm.api.resource.relational.util;

/**
 * Indicates if the trigger activity is run before or after the statement or row is modified.
 * 
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>before</code> - </li>
 * <li><code>after</code> - </li>
 * <li>The default value is <code>after</code>.</li>
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public enum ConditionTiming {
	  before
	, after
	;


	public static ConditionTiming getDefault() { 
		return ConditionTiming.after; 
	}



	public static ConditionTiming byJDBC( String triggerTime ) {
		if ( "B".equalsIgnoreCase( triggerTime ) ) {
			return before;
		}
		
		if ( "A".equalsIgnoreCase( triggerTime ) ) {
			return after;
		}
		
		return null;
	}
}