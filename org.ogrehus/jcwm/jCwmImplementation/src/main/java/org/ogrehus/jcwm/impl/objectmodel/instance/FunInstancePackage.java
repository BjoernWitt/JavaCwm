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

import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.instance.CwmObject;
import org.ogrehus.jcwm.api.objectmodel.instance.DataValue;
import org.ogrehus.jcwm.api.objectmodel.instance.Extent;
import org.ogrehus.jcwm.api.objectmodel.instance.InstancePackage;


public class FunInstancePackage
extends
	InstancePackage 
{


	/**
	 * Factory method that creates a new instance of this Extent by specific parameters.
	 * 
	 * @param name An identifier for the ModelElement within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the ModelElement within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Extent, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	public Extent createExtent( String name, Visibility visibility ) {
		return new FunExtent( name, visibility );
	}



	/**
	 * Factory method that creates a new instance of CwmObject by specific parameters.
	 * 
	 * @param name An identifier for the CwmObject within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmObject within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param classifier The Classifier that declares the structure of the Object. Must not be <code>null</code>.
	 * 
	 * @return A new instance of CwmObject, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>classifier</code>
	 * is <code>null</code>.
	 * 
	 */
	public CwmObject createObject( String name, Visibility visibility, CwmClass classifier ) {
		return new FunCwmObject( name, visibility, classifier );
	}



	/**
	 * Factory method that creates a new instance of this DataValue by specific parameters.
	 * 
	 * @param name An identifier for the ModelElement within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the ModelElement within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param dataType The DataType that declares the structure of the Instance. Must not be <code>null</code>.
	 * 
	 * @param value A string representation of the value. Must not be <code>null</code>.
	 * 
	 * @return A new instance of DataValue, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>classifier</code> or
	 * <code>value</code> is <code>null</code>.
	 * 
	 */
	public DataValue createDataValue( String name, Visibility visibility, DataType dataType, String value ) {
		return new FunDataValue( name, visibility, dataType, value );
	}
}