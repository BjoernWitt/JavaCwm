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

import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GSlot;
import org.ogrehus.jcwm.api.resource.relational.Catalog;
import org.ogrehus.jcwm.api.resource.relational.ColumnValue;

/**
 * A slot is a named location in an Object instance that holds the current value of the StructuralFeature associated with the Slot instance.
 * <p>
 * Normally, the StructuralFeature associated with the slot will be either an Attribute instance or an AssociationEnd instance. Slots are owned by 
 * Objects; DataValues do not have slots.
 * </p>
 * 
 * <p>
 * <b>Constraints:</b>
 * </p>
 * <ul>
 * <li><b>[C-6-5]</b> - If the StructuralFeature describing a Slot is an AssociationEnd, the Classifier associated with the Object owning the Slot 
 * must be an Association.</li>
 * <li><b>[C-6-9]</b> - If the Slot instance is not also a DataSlot, the value reference must be present.</li>
 * </ul>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <ROW_SET> The type of the Namespace that contains this Slot.
 * @param <COLUMN> Identifies the StructuralFeature instance for which the Slot instance contains the current value.
 * @param <ROW> The type of the Namespace that contains this Slot.
 * 
 */
public interface GColumnSlot<
	  ROW_SET extends GRowSet<?, ?, ?>
	, COLUMN  extends GColumn<?, ?>
	, ROW     extends GRow<?, ?, ?>    
> extends 
	GSlot<ROW_SET, Catalog, ColumnValue, COLUMN, ROW>
{
	// only configurational purpose in type definition in interface
}
