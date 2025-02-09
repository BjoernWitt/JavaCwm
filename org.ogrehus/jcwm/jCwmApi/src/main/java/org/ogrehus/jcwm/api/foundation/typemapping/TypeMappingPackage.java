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
package org.ogrehus.jcwm.api.foundation.typemapping;

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;

/**
 * TypeMappingPackage provides hierarchical access to any factory method in this jCwmAPI in scope of the package typeMapping.
 * <p>
 * To work with the jCwm you need to choose an implementation of the API. For example its recommends to use the reference implementation provided 
 * by <a href="http://www.ogrehus.org">www.ogrehus.org</a> too. Otherwise you can implement your own if you need specific functionality like more 
 * immutable objects for more thread safety or final classes that can't be extended for security reasons. 
 * <p>
 * <b>Usage of TypeMappingPackage:</b>
 * </p>
 * <pre>
 * ...
 * TypeMappingPackage types   = TypeMappingPackage.create( "org.ogrehus.jcwm.impl.objectmodel.foundation.typemapping.FunTypeMappingPackage" );
 * TypeSystem         system  = types.createTypeSystem(...);
 * TypeMapping        mapping = types.createTypeMapping(...); 
 * ... 
 * </pre>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public abstract class TypeMappingPackage {
	
	
	/**
	 * Creates a new instance of the TypeMappingPackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * </p>
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class 
	 * <code>org.ogrehus.api.cwm.foundation.typemapping.TypeMappingPackage</code>.
	 * 
	 * @return A new instance of TypeMappingPackage, determined by the specific classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends 
	 * <code>org.ogrehus.api.cwm.foundation.typemapping.TypeMappingPackage</code> or if the class does not provide a simple public constructor without 
	 * any parameters. 
	 *   
	 */
	public static final TypeMappingPackage create( String classPath ) 
	throws 
		  ClassNotFoundException
	{
		try {
			Object foundation = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( foundation instanceof TypeMappingPackage ) {
				return (TypeMappingPackage)foundation;
			}
			throw new ClassNotFoundException( "Invalid Class, it must extend org.ogrehus.api.cwm.foundation.typemapping.TypeMappingPackage:" 
											+ classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid TypeMappingPackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid TypeMappingPackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid TypeMappingPackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}



	@Override
	public boolean equals( Object obj ) {
		return ( obj instanceof TypeMappingPackage );
	}



	/**
	 * Factory method that creates a new instance of TypeMapping by specific parameters.
	 * 
	 * <p>
	 * TypeMapping instances permit the creation of mappings between data types defined within different environments and are used to indicate data 
	 * type compatibilities that permit direct assignment of values from one environment (the source type) into equivalent values in another 
	 * environment (the target type).
	 * </p>
	 * 
	 * @param name An identifier for the TypeMapping within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the TypeMapping within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param isBestMatch <code>true</code> if this TypeMapping instance represents the best available mapping between a pair of data types in 
	 * different software systems. Must not be <code>null</code>.
	 * 
	 * @param isLossy <code>true</code> if this TypeMapping instance may result in a data conversion error if the source data is within certain 
	 * ranges. For example, storing a 32-bit unsigned integer value into a 16-bit unsigned integer container will result in a data conversion error 
	 * only when the source data has a value greater than 65535.  Must not be <code>null</code>.
	 * 
	 * @param sourceType Identifies the GClassifier instance that is the source of information exchange. Must not be <code>null</code>.
	 * 
	 * @param targetType Identifies the GClassifier instance that is the target of information exchange. Must not be <code>null</code>.
	 * 
	 * @return A new instance of TypeMapping, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, 
	 * <code>isBestMatch</code>, <code>isLossy</code>, <code>sourceType</code> or <code>targetType</code> is <code>null</code>. 
	 * 
	 */
	abstract public TypeMapping createTypeMapping(
		  String name
		, Visibility visibility
		, Boolean isBestMatch
		, Boolean isLossy
		, GClassifier<?, ?, ?, ?, ?, ?> sourceType
		, GClassifier<?, ?, ?, ?, ?, ?> targetType
	);



	/**
	 * Factory method that creates a new instance of TypeSystem by specific parameters.
	 * 
	 * @param name An identifier for the TypeSystem within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the TypeSystem within its owning Namespace. Must not be 
	 * <code>null</code>.
	 * 
	 * @param version A string describing the version of the TypeSystem represented. Must not be <code>null</code>.
	 * 
	 * @return A new instance of TypeSystem, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>version</code> 
	 * is <code>null</code>. 
	 * 
	 */
	abstract public TypeSystem createTypeSystem( String name, Visibility visibility, String version	);
}