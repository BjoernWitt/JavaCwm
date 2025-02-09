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
package org.ogrehus.jcwm.api.objectmodel;

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.objectmodel.behavioral.BehavioralPackage;
import org.ogrehus.jcwm.api.objectmodel.core.CorePackage;
import org.ogrehus.jcwm.api.objectmodel.instance.InstancePackage;
import org.ogrehus.jcwm.api.resource.relational.RelationalPackage;

/**
 * ObjectModelPackage provides hierarchical access to any factory method in this jCwmAPI in scope of the package objectmodel.
 * <p>
 * To work with the jCwm you need to choose an implementation of the API. For example its recommends to use the reference implementation provided by
 * <a href="http://www.ogrehus.org">www.ogrehus.org</a> too. Otherwise you can implement your own if you need specific functionality like more
 * immutable objects for more thread safety or final classes that can't be extended for security reasons.
 * <p>
 * <b>Usage of ObjectModelPackage:</b>
 * </p>
 * <pre>
 * ...
 * ObjectModelPackage modelPack = ObjectModelPackage.create( "org.ogrehus.jcwm.impl.objectmodel.FunObjectModelPackage" );
 * CwmPackage         aPackage  = modelPack.core.createCwmPackage(...);
 * ...
 * // you can find more packages in the ObjectModelPackage than only the core package, e. g. you can do like:
 * Parameter          aParam    = modelPack.behavioral.createParameter(...);
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
public abstract class ObjectModelPackage {

	public BehavioralPackage   behavioral   = null;
	public CorePackage         core         = null;
	public InstancePackage     instance     = null;
	public RelationalPackage   relationship = null;
	
	
	/**
	 * Creates a new instance of the package ObjectModelPackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class 
	 * <code>org.ogrehus.jcwm.api.objectmodel.ObjectModelPackage</code>.
	 * 
	 * @return A new instance of ObjectModel, determined by the specific classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends
	 * <code>org.ogrehus.jcwm.api.objectmodel.ObjectModelPackage</code> or if the class does not provide a simple public constructor without any 
	 * parameters. 
	 * 
	 */
	public static final ObjectModelPackage create( String classPath )
	throws 
		ClassNotFoundException
	{
		try {
			Object objectModel = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( objectModel instanceof ObjectModelPackage ) {
				return (ObjectModelPackage)objectModel;
			}
			throw new ClassNotFoundException( "Invalid Class, it must extend org.ogrehus.jcwm.api.objectmodel.ObjectModelPackage:" + classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid ObjectModelPackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid ObjectModelPackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid ObjectModelPackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}
}