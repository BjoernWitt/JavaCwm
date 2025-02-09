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
package org.ogrehus.jcwm.api.objectmodel.instance;

import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GDataSlot;


/**
 * A AssociationSlot that is used to hold a data value where there is no need to manage the value as an element in its own right (in which case a 
 * DataValue would be used) - for example it is a one-off string value or a number.
 * <p>
 * The dataValue (and dataType where set) must be consistent with the type of the DataSlot feature (Attribute) and must obey any constraints on the 
 * full descriptor of the Attribute DataType (including both explicit constraints and built-in constraints such as multiplicity).
 * </p>
 * 
 * <p>
 * <b>Constraints:</b>
 * </p>
 * <ul>
 * <li><b>[C-6-6]</b> - A DataType instance associated with a DataSlot instance must be compatible with the type of the DataSlot indicated by the 
 * feature::type inherited from AssociationSlot.</li>
 * <li><b>[C-6-7]</b> - The StructuralFeature instance obtained via the feature reference inherited from AssociationSlot must be an Attribute.</li>
 * <li><b>[C-6-8]</b> - The value reference inherited from AssociationSlot must be empty.</li>
 * </ul>
 * The Constraint <b>[C-6-7]</b> will be achieved by narrowing the generic type of FEATURE to GAttribute
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface DataSlot
 extends
	GDataSlot<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Attribute
		, CwmObject
		, DataType
	>
{
	// only configurational purpose in type definition in interface
}