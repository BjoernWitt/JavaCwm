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
package org.ogrehus.jcwm.api.foundation;

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.foundation.businessinformation.BusinessInformationPackage;
import org.ogrehus.jcwm.api.foundation.datatypes.DataTypePackage;
import org.ogrehus.jcwm.api.foundation.keyindexes.KeyIndexesPackage;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareDeploymentPackage;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeMappingPackage;

/**
 * FoundationPackage provides hierarchical access to any factory method in this jCwmAPI in scope of the package foundation.
 * <p>
 * To work with the jCwm you need to choose an implementation of the API. For example its recommends to use the reference implementation provided 
 * by <a href="http://www.ogrehus.org">www.ogrehus.org</a> too. Otherwise you can implement your own if you need specific functionality like more 
 * immutable objects for more thread safety or final classes that can't be extended for security reasons. 
 * <p>
 * <b>Usage of FoundationPackage:</b>
 * </p>
 * <pre>
 * ...
 * FoundationPackage foundation = FoundationPackage.create( "org.ogrehus.jcwm.impl.objectmodel.foundation.FunFoundationPackage" );
 * TypeSystem        typeSystem = foundation.typeMapping.createTypeSystem(...);
 * SoftwareSystem    software   = foundation.softwaredeployment.createSoftwareSystem(...);
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
public abstract class FoundationPackage {

	public BusinessInformationPackage businessinformation = null;
	public DataTypePackage            datatype            = null;
//	public ExpressionPackage          expression          = null;
	public KeyIndexesPackage          keyindexes          = null;
	public SoftwareDeploymentPackage  softwaredeployment  = null;
	public TypeMappingPackage         typemapping         = null;


	
	/**
	 * Creates a new instance of the FoundationPackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * </p>
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class
	 * <code>org.ogrehus.jcwm.api.foundation.FoundationPackage</code>.
	 * 
	 * @return A new instance of FoundationPackage, determined by the specific classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends 
	 * <code>org.ogrehus.jcwm.api.foundation.FoundationPackage</code> or if the class does not provide a simple public constructor without any 
	 * parameters. 
	 * 
	 */
	public static final FoundationPackage create( String classPath ) 
	throws 
		  ClassNotFoundException
	{
		try {
			Object foundation = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( foundation instanceof FoundationPackage ) {
				return (FoundationPackage)foundation;
			}
			throw new ClassNotFoundException( 
					   "Invalid Class, it must extend org.ogrehus.jcwm.api.foundation.FoundationPackage:" + classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid FoundationPackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid FoundationPackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid FoundationPackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}

}