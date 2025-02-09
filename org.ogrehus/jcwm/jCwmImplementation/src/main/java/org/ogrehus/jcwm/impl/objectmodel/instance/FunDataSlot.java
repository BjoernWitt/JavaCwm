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
package org.ogrehus.jcwm.impl.objectmodel.instance;

import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.instance.CwmObject;
import org.ogrehus.jcwm.api.objectmodel.instance.DataSlot;

import org.ogrehus.jcwm.impl.objectmodel.instance.util.generic.GFunDataSlot;


public class FunDataSlot
extends
	GFunDataSlot<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Attribute
		, CwmObject
		, DataType
	>
implements
	DataSlot
{


	/**
	 * Creates a new instance of this DataSlot by specific parameters.
	 * 
	 * @param attribute References the Attribute instance that describes the value held by the DataSlot instance.
	 * 
	 * @param dataValue The value for the slot expressed as a string.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>attribute</code> or <code>dataValue </code> is <code>null</code>.
	 * 
	 */
	public FunDataSlot( Attribute attribute, String dataValue ) {
		super( attribute, dataValue ); // throws NullPointerException
	}
}