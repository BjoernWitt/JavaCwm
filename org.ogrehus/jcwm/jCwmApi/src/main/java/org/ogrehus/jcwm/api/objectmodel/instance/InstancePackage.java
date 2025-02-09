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

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

public abstract class InstancePackage {
	
	
	/**
	 * Creates a new instance of the InstancePackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class
	 * <code>org.ogrehus.jcwm.api.objectmodel.instance.InstancePackage</code>.
	 * 
	 * @return A new instance of InstancePackage, determined by the specific classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends
	 * <code>org.ogrehus.jcwm.api.objectmodel.instance.InstancePackage</code> or if the class does not provide a simple public constructor without
	 * any parameters. 
	 * 
	 */
	public static final InstancePackage create( String classPath )
	throws
		ClassNotFoundException
	{
		try {
			Object instance = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( instance instanceof InstancePackage ) {
				return (InstancePackage)instance;
			}
			throw new ClassNotFoundException( 
				"Invalid Class, it must extend org.ogrehus.jcwm.api.objectmodel.instance.InstancePackage:" + classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid InstancePackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid InstancePackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid InstancePackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}



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
	abstract public Extent createExtent( String name, Visibility visibility );



	/**
	 * Factory method that creates a new instance of CwmObject by specific parameters.
	 * 
	 * @param name An identifier for the CwmObject within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmObject within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param cwmClass The Classifier that declares the structure of the Object. Must not be <code>null</code>.
	 * 
	 * @return A new instance of CwmObject, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>classifier</code>
	 * is <code>null</code>.
	 * 
	 */
	abstract public CwmObject createObject( String name, Visibility visibility, CwmClass cwmClass );



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
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>classifier</code>
	 * or <code>value</code> is <code>null</code>. 
	 * 
	 */
	abstract public DataValue createDataValue( String name, Visibility visibility, DataType dataType, String value );



//	/**
//	 * Factory method that creates a new instance of this DataSlot by specific parameters.
//	 * 
//	 * @param name An identifier for the DataSlot within its containing Namespace. Must not be <code>null</code>.
//	 * 
//	 * @param visibility Specifies extent of the visibility of the DataSlot within its owning Namespace. Must not be 
//     * <code>null</code>.
//	 * 
//	 * @param feature References the Attribute instance that describes the value held by the DataSlot instance. Must not
//     * be <code>null</code>.
//	 * 
//	 * @param dataValue The value for the slot expressed as a string. Must not be <code>null</code>.
//	 * 
//	 * @return A new instance of DataSlot, by initial parameters.
//	 * 
//	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, 
//     * <code>feature</code> or <code>dataValue</code> is <code>null</code>. 
//	 * 
//	 */	
//	abstract public DataSlot createDataSlot( 
//		  String name
//		, Visibility visibility
//		, Attribute feature
//		, String dataValue
//	);	

//	
//
//	/**
//	 * Factory method that creates a new instance of this Slot by specific 
//	 * parameters.
//	 * 
//	 * @param name An identifier for the ModelElement within its containing 
//	 * Namespace. Must not be <code>null</code>.
//	 * 
//	 * @param visibility Specifies extent of the visibility of the ModelElement 
//	 * within its owning Namespace. Must not be <code>null</code>.
//	 * 
//	 * @param feature References the StructuralFeature instance that describes 
//	 * the value held by the Slot instance. Must not be <code>null</code>.
//	 * 
//	 * @return A new instance of Slot, by initial parameters.
//	 * 
//	 * @throws NullPointerException Is thrown if one of the parameter: 
//	 * <code>name</code>, <code>visibility</code> or <code>feature</code> is 
//	 * <code>null</code>. 
//	 * 
//	 */
//	abstract public Slot createSlot( 
//		  String name
//		, VisibilityKind visibility
//    	, StructuralFeature feature 		
//	);
}