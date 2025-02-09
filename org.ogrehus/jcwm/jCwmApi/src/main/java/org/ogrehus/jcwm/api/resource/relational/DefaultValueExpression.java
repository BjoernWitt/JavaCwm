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
package org.ogrehus.jcwm.api.resource.relational;

import org.ogrehus.jcwm.api.objectmodel.core.Expression;

/**
 * Default value expression for a column, which should be interpreted as a string when the value is enclosed in single quotes.
 *  
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface DefaultValueExpression
extends
	Expression
{


	/**
	 * Returns whether the default value is value <code>null</code>.
	 * 
	 * @return <code>true</code> if NULL was specified as the default value, <code>false</code> otherwise.
	 * 
	 */
	Boolean isNull();



	/**
	 * Returns whether the specified default value cannot be represented without truncation.
	 * 
	 * @return <code>true</code> if the value needs truncation, <code>false</code> otherwise.
	 * 
	 */
	Boolean needsTruncation();
}