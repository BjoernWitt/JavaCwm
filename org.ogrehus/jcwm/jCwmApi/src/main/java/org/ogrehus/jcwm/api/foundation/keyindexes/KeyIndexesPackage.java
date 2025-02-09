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
package org.ogrehus.jcwm.api.foundation.keyindexes;

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.relationships.AssociationEnd;


public abstract class KeyIndexesPackage {
	
	/**
	 * Creates a new instance of the KeyIndexesPackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * </p>
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class 
	 * <code>org.ogrehus.jcwm.api.foundation.keyindexes.KeyIndexesPackage</code>.
	 * 
	 * @return A new instance of KeyIndexesPackage, determined by the specific  classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends 
	 * <code>org.ogrehus.jcwm.api.foundation.keyindexes.KeyIndexesPackage</code> or if the class does not provide a simple public constructor without 
	 * any parameters. 
	 * 
	 */
	public static final KeyIndexesPackage create( String classPath ) 
	throws 
		  ClassNotFoundException
	{
		try {
			Object keyIndexesPackage = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( keyIndexesPackage instanceof KeyIndexesPackage ) {
				return (KeyIndexesPackage)keyIndexesPackage;
			}
			throw new ClassNotFoundException( 
				"Invalid Class, it must extend org.ogrehus.jcwm.api.foundation.keyindexes.KeyIndexesPackage:" + classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid KeyIndexesPackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid KeyIndexesPackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid KeyIndexesPackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}



	/**
	 * Factory method that creates a new instance of Index by specific parameters.
	 * 
	 * <p>
	 * Instances of the Index class represent the ordering of the instances of some other Class, and the Index is said to span the Class. Indexes 
	 * normally have an ordered set of attributes of the Class instance they span that make up the key of the index; this set of relationships is 
	 * represented by the IndexedFeature class that indicates how the attributes are used by the Index instance. 
	 * </p>
	 * 
	 * @param name An identifier for the Index within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Index within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param isUnique The isUnique attribute is <code>true</code> if the Index instance guarantees all of its instances have a unique key value. Must
	 * not be <code>null</code>.
	 * 
	 * @param isSorted If <code>true</code>, the Index instance is maintained in a sorted order. Must not be <code>null</code>.
	 * 
	 * @param isPartitioning If <code>true</code>, this Index instance is used as a partitioning index. Must not be <code>null</code>.
	 * 
	 * @param spannedClass Identifies the Class instance spanned by the Index instance. Must not be <code>null</code>.
	 * 
     * @param indexedName The name of the new IndexedFeature. Must not be <code>null</code>.
     * 
     * @param indexedVisibility The visibility of the new IndexedFeature. Must not be <code>null</code>.
     * 
     * @param indexedFeature The StructuralFeature that will be associated by this IndexedFeature. Must not be <code>null</code>.
     * 
	 * @return A new instance of Index, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, 
     * <code>isUnique</code>, <code>isSorted</code>, <code>isPartitioning</code>, <code>spannedClass</code>,
     * <code>indexedName</code>, <code>indexedVisibility</code> or <code>indexedFeature</code> is <code>null</code>. 
	 * 
	 */
	abstract public Index createIndex(
		  String name
		, Visibility visibility
		, Boolean isUnique
		, Boolean isSorted
		, Boolean isPartitioning
		, CwmClass spannedClass
		, String indexedName
		, Visibility indexedVisibility
		, Attribute indexedFeature
	);



	/**
	 * Factory method that creates a new instance of Index by specific parameters.
	 * 
	 * <p>
	 * Instances of the Index class represent the ordering of the instances of some other Class, and the Index is said to span the Class. Indexes 
	 * normally have an ordered set of attributes of the Class instance they span that make up the key of the index; this set of relationships is 
	 * represented by the IndexedFeature class that indicates how the attributes are used by the Index instance. 
	 * </p>
	 * 
	 * @param name An identifier for the Index within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Index within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param isUnique The isUnique attribute is <code>true</code> if the Index instance guarantees all of its instances have a unique key value. Must 
	 * not be <code>null</code>.
	 * 
	 * @param isSorted If <code>true</code>, the Index instance is maintained in a sorted order. Must not be <code>null</code>.
	 * 
	 * @param isPartitioning If <code>true</code>, this Index instance is used as a partitioning index. Must not be <code>null</code>.
	 * 
	 * @param spannedClass Identifies the Class instance spanned by the Index instance. Must not be <code>null</code>.
	 * 
     * @param indexedName The name of the new IndexedFeature. Must not be <code>null</code>.
     * 
     * @param indexedVisibility The visibility of the new IndexedFeature. Must not be <code>null</code>.
     * 
     * @param indexedFeature The StructuralFeature tha will be associated by this IndexedFeature. Must not be 
     * <code>null</code>.
     * 
	 * @return A new instance of Index, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, 
     * <code>isUnique</code>, <code>isSorted</code>, <code>isPartitioning</code>, <code>spannedClass</code>,
     * <code>indexedName</code>, <code>indexedVisibility</code> or <code>indexedFeature</code> is <code>null</code>. 
	 * 
	 */
	abstract public Index createIndex(
		  String name
		, Visibility visibility
		, Boolean isUnique
		, Boolean isSorted
		, Boolean isPartitioning
		, CwmClass spannedClass
		, String indexedName
		, Visibility indexedVisibility
		, AssociationEnd indexedFeature
	);



	/**
	 * Factory method that creates a new instance of UniqueKey by specific parameters.
	 * 
	 * <p>
	 * A UniqueKey represents a collection of features of some Class that, taken together, uniquely identify instances of the Class. Instances of 
	 * UniqueKey for which all features are required to have non-null values are candidates for use as primary keys such as those defined by 
	 * relational DBMSs.
	 * </p>
	 * 
	 * @param name An identifier for the UniqueKey within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the UniqueKey within its owning Namespace. Must not be <code>null</code>.
	 * 
     * @param keyRelationship Identifies the KeyRelationship instances that reference this UniqueKey instance.
     * 
	 * @return A new instance of UniqueKey, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>keyRelationship</code> 
	 * is <code>null</code>. 
	 * 
	 */
	abstract public UniqueKey createUniqueKey( String name, Visibility visibility, KeyRelationship keyRelationship );



	/**
	 * Factory method that creates a new instance of KeyRelationship by specific parameters.
	 * 
	 * <p>
	 * KeyRelationship instances represent relationships between UniqueKey instances and the Class(es) that reference them. This class is intended as 
	 * a starting point for the creation of foreign key and other associative relationships.
	 * </p>
	 * 
	 * @param name An identifier for the KeyRelationship within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the KeyRelationship within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param uniqueKey Identifies the UniqueKey instance that serves as the primary key for this KeyRelationship instance.
	 * 
	 * @return A new instance of KeyRelationship, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or 
	 * <code>uniqueKey</code> is <code>null</code>. 
	 * 
	 */
	abstract public KeyRelationship createKeyRelationship( String name, Visibility visibility, UniqueKey uniqueKey );
}