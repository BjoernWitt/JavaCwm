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
package org.ogrehus.jcwm.api.objectmodel.relationships;

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.Changeable;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.relationships.util.Aggregation;

public abstract class RelationshipPackage {
	
	
	/**
	 * Creates a new instance of the RelationshipPackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * </p>
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class
	 * <code>org.ogrehus.jcwm.api.objectmodel.relationships.RelationshipPackage</code>.
	 * 
	 * @return A new instance of RelationshipPackage, determined by the specific classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends
	 * <code>org.ogrehus.jcwm.api.objectmodel.relationships.RelationshipPackage</code> or if the class does not provide
	 * a simple public constructor without any parameters.
	 * 
	 */
	public static final RelationshipPackage create( String classPath )
	throws
		ClassNotFoundException
	{
		try {
			Object relationship = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( relationship instanceof RelationshipPackage ) {
				return (RelationshipPackage)relationship;
			}
			throw new ClassNotFoundException( "Invalid Class, it must extend org.ogrehus.jcwm.api.objectmodel. relationships.RelationshipPackage:"
											+ classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid RelationshipPackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid RelationshipPackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid RelationshipPackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}


	/**
	 * Factory method that creates a new instance of Association by specific parameters.
	 * 
	 * @param name An identifier for the FunAssociation within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunAssociation within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this FunAssociation is abstract and can not be instantiated,
	 * <code>false</code> otherwise.
	 *
	 * @return A new instance of FunAssociation by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 * 
	 */
	abstract public Association createAssociation( 
		  String name
		, Visibility visibility
		, Boolean notInstantiable
		, String sourceName
		, Visibility sourceVisibility
		, Changeable sourceChangeability
		, CwmClass sourceType
		, Aggregation sourceAggregation
		, Boolean sourceIsNavigateable
		, String targetName
		, Visibility targetVisibility
		, Changeable targetChangeability
		, CwmClass targetType
		, Aggregation targetAggregation
		, Boolean targetIsNavigateable
	);



	/**
	 * Factory method that creates a new instance of Generalization by specific parameters.
	 * 
	 * @param name An identifier for the Generalization within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Generalization within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>. 
	 *
	 */	
	abstract public <CHILD  extends GClassifier<?, ?, ?, ?, ?, ?>, PARENT extends GClassifier<?, ?, ?, ?, ?, ?>>



	Generalization createGeneralization( String name, Visibility visibility, CHILD  child, PARENT parent );
}