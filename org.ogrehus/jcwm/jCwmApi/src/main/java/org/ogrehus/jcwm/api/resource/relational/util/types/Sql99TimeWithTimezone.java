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
package org.ogrehus.jcwm.api.resource.relational.util.types;

import org.ogrehus.jcwm.api.resource.relational.SQLSimpleType;

/**
 * DataType: <code>TIME WITH TIME ZONE</code> defined in SQL-99
 * 
 * <ul>
 * <li>Mandatory values:<ul>
 *   <li><code>NUMERIC_PRECISION</code> - <b>not</b> <code>null</code></li>
 *   <li><code>NUMERIC_PRECISION_RADIX</code> - <b>not</b> <code>null</code></li>
 *   <li><code>DATETIME_PRECISION</code> - <b>not</b> <code>null</code></li>   
 *   </ul>
 * </li>
 * <li>Optional or no suitable values:<ul>
 *   <li><code>NUMERIC_SCALE</code></li> 
 *   <li><code>CHARACTER_MAXIMUM_LENGTH</code></li> 
 *   <li><code>CHARACTER_OCTET_LENGTH</code></li>
 *   <li><code>COLLATION_CATALOG</code></li>
 *   <li><code>COLLATION_SCHEMA</code></li>
 *   <li><code>COLLATION_NAME</code></li>
 *   <li><code>USER_DEFINED_TYPE_CATALOG</code></li>
 *   <li><code>USER_DEFINED_TYPE_SCHEMA</code></li>
 *   <li><code>USER_DEFINED_TYPE_NAME</code></li>
 *   <li><code>INTERVAL_TYPE</code></li>
 *   <li><code>INTERVAL_PRECISION</code></li>
 *   <li><code>SCOPE_CATALOG</code></li>
 *   <li><code>SCOPE_SCHEMA</code></li>
 *   <li><code>SCOPE_NAME</code></li>
 *   <li><code>MAXIMUM_CARDINALITY</code></li>
 *   </ul>
 * </li>
 * </ul>
 *  
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Sql99TimeWithTimezone 
extends 
	SQLSimpleType 
{
	// only configurational purpose in type definition in interface
}