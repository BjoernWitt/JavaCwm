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
package org.ogrehus.jcwm.api.resource.relational.util.generic;

import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GCwmObject;
import org.ogrehus.jcwm.api.resource.relational.Catalog;

/**
 * An instance of a ColumnSet. 
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <ROW_SET> The type of the Namespace that contains this Row.
 * @param <COLUMN_SET> The type of this Row. 
 * @param <SLOT> The slot for this Row.
 * 
 */
public interface GRow<
	  ROW_SET    extends GRowSet<?, ?, ?>
	, COLUMN_SET extends GColumnSet<?, ?, ?, ?>
	, SLOT       extends GColumnSlot<?, ?, ?>
>extends
	GCwmObject<
		  ROW_SET
		, Catalog
		, COLUMN_SET
		, SLOT
	>
{
	// only configurational purpose in type definition in interface
}
