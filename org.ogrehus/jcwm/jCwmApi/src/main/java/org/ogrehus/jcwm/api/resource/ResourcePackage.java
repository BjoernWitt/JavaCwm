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
package org.ogrehus.jcwm.api.resource;

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.resource.relational.RelationalPackage;

public abstract class ResourcePackage {
	
	
	public RelationalPackage relational = null;

	
	/**
	 * Creates a new instance of the ResourcePackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * </p>
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class 
	 * <code>org.ogrehus.jcwm.api.resource.ResourcePackage</code>.
	 * 
	 * @return A new instance of ResourcePackage, determined by the specific classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends 
	 * <code>org.ogrehus.jcwm.api.resource.ResourcePackage</code> or if the class does not provide a simple public constructor without any parameters.
	 * 
	 */
	public static final ResourcePackage create( String classPath ) 
	throws 
		ClassNotFoundException
	{
		try {
			Object resourcePackage = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( resourcePackage instanceof ResourcePackage ) {
				return (ResourcePackage)resourcePackage;
			}
			throw new ClassNotFoundException( "Invalid Class, it must extend org.ogrehus.jcwm.api.resource.ResourcePackage:" + classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid ResourcePackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid ResourcePackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid ResourcePackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}
}