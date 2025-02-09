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

import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.instance.DataValue;

import org.ogrehus.jcwm.impl.objectmodel.instance.util.generic.GFunDataValue;


public class FunDataValue
extends
	GFunDataValue<GNamespace<?, ?, ?, ?, ?>, GCwmPackage<?, ?, ?, ?, ?, ?, ?>, DataType>
implements
	DataValue 
{


	/**
	 * Creates a new instance of this DataValue by specific parameters.
	 * 
	 * @param name An identifier for the ModelElement within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the ModelElement within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param classifier The Classifier that declares the structure of the Instance. Must not be <code>null</code>.
	 * 
	 * @param value A string representation of the value. Must not be <code>null</code>.
	 * 
	 * @return A new instance of DataValue, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>classifier</code> or
	 * <code>value</code> is <code>null</code>.
	 * 
	 */
	public FunDataValue( String name, Visibility visibility, DataType dataType, String value ) {
		super( name, visibility, dataType, value ); // may throw NullPointerException
		this.setValue( value ); // may throw NullPointerException
	}
}