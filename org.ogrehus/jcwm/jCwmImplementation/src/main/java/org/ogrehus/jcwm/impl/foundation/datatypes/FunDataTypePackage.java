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
package org.ogrehus.jcwm.impl.foundation.datatypes;

import org.ogrehus.jcwm.api.foundation.datatypes.CwmEnumeration;
import org.ogrehus.jcwm.api.foundation.datatypes.DataTypePackage;
import org.ogrehus.jcwm.api.foundation.datatypes.QueryExpression;
import org.ogrehus.jcwm.api.foundation.datatypes.TypeAlias;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;


/**
 * DataTypePackage provides hierarchical access to any factory method in this jCwmAPI in scope of the package core.
 * <p>
 * The CWM DataTypes meta-model supports definition of meta-model constructs that modelers can use to create the specific data types they need.
 * Although the CWM Foundation itself does not contain specific data type definitions, a number of data type definitions for widely used environments
 * are provided (in the CWM Data Types chapter) as examples of the appropriate usage of CWM Foundation classes for creating data type definitions.
 * </p>
 * <p>
 * To work with the jCwm you need to choose an implementation of the API. For example its recommends to use the reference implementation provided by
 * <a href="http://www.ogrehus.org">www.ogrehus.org</a> too. Otherwise you can implement your own if you need specific functionality like more
 * immutable objects for more thread safety or final classes that can't be extended for security reasons.
 * <p>
 * <b>Usage of DataTypePackage:</b>
 * </p>
 * <pre>
 * ...
 * DataTypePackage dataType = DataTypePackage.create( "org.ogrehus.jcwm.api.foundation.datatypes.DataTypePackage" );
 * Union           union    = dataType.createUnion(...);
 * UnionMember     member   = dataType.createUnionMember(...); 
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
public class FunDataTypePackage
extends
    DataTypePackage
{
	

	/**
	 * Factory method that creates a new instance of CwmEnumeration by specific parameters.
	 * 
	 * <p>
	 * The Enumeration class is intended as a starting point from which enumerated data types can be created. An enumerated data type is a collection 
	 * of identifiers often used as the permitted states that some other attribute or property of the enumerated type may take.
	 * </p>
	 * 
	 * @param name An identifier for the CwmEnumeration within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmEnumeration within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param notInstantiable <code>true</code> if this CwmEnumeration is abstract and can not be instantiated, <code>false</code> otherwise. Must not
	 * be <code>null</code>.
	 * 
	 * @param isOrdered If <code>true</code>, the ordered constraint on the EnumerationLiterals association is relevant. Otherwise, the ordering of 
	 * EnumerationLiteral instances is considered unspecified. Must not be <code>null</code>.
	 * 
	 * @param firstLiteralName Name of the the first EnumerationLiteral instance relevant for this Enumeration instance. Must not 
	 * be <code>null</code>.
	 * 
	 * @param firstLiteralVisibility Visibility of the the first EnumerationLiteral instance relevant for this Enumeration instance. Must not 
	 * be <code>null</code>.
	 * 
	 * @param firstLiteralValues optional values for the firstLiteral. Might be <code>null</code> if none is avail.
	 * 
	 * @return A new instance of CwmEnumeration, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>,
	 * <code>notInstantiable</code>, <code>isOrdered</code>, <code>firstLiteralName</code> or 
	 * <code>firstLiteralName</code> is <code>null</code>.  
	 * 
	 */
	public CwmEnumeration createCwmEnumeration( 
		  String     name
		, Visibility visibility
		, Boolean    notInstantiable
		, Boolean    isOrdered 
		, String     firstLiteralName
		, Visibility firstLiteralVisibility
		, Expression... firstLiteralValues
	) {
	return new FunCwmEnumeration( 
			  name
			, visibility
			, notInstantiable
			, isOrdered
			, firstLiteralName
			, firstLiteralVisibility
			, firstLiteralValues 
		);
	}



	/**
	 * Factory method that creates a new instance of QueryExpression by specific parameters.
	 * 
	 * <p>
	 * QueryExpression instances contain query statements in language-dependent form.
	 * </p>
	 * 
	 * @param body The text of the expression expressed in the given language. Must not be <code>null</code>.
	 * 
	 * @return A new instance of QueryExpression, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if the parameter: <code>body</code> is <code>null</code>.
	 * 
	 */
	public QueryExpression createQueryExpression( String body ) {
		return new FunQueryExpression( body );
	}



	/**
	 * Factory method that creates a new instance of TypeAlias by specific parameters.
	 * 
	 * <p>
	 * The TypeAlias class is intended to provide a renaming capability for Classifier instances.This class is required to support situations in which
	 * creation of an alias for a class effectively creates a new class. For example, CORBA IDL type aliases have different typeCodes than their base
	 * types and are therefore treated as distinct types.
	 * </p>
	 * 
	 * @param name An identifier for the TypeAlias within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the TypeAlias within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param notInstantiable <code>true</code> if this TypeAlias is abstract and can not be instantiated, <code>false</code> otherwise. Must not 
	 * be <code>null</code>.
	 * 
	 * @param type Identifies the Classifier instance for which this TypeAlias instance acts as an alias. The ClassifierAlias association connects 
	 * TypeAlias instances with the Classifier instances that they rename.  Must not be <code>null</code>.
	 * 
	 * @return A new instance of TypeAlias, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>notInstantiable</code> 
	 * or <code>type</code> is <code>null</code>.  
	 * 
	 */
	public <TYPE extends GClassifier<?, ?, ?, ?, ?, ?>> TypeAlias<TYPE> createTypeAlias( 
		  String name
		, Visibility visibility
		, Boolean notInstantiable
		, TYPE type
	) {
		return new FunTypeAlias<TYPE>( name, visibility, notInstantiable, type );
	}



//----------------------------------------------------------------------------------------------------------------------    
// TOBEDONE when more time is available    

    
//	/**
//	 * Factory method that creates a new instance of Union by specific parameters.
//	 * 
//	 * @param name An identifier for the Union within its containing Namespace. Must not be <code>null</code>.
//	 * 
//	 * @param visibility Specifies extent of the visibility of the Union within its owning Namespace. Must not be 
//	 * <code>null</code>.
//	 * 
//	 * @param notInstantiable <code>true</code> if this Union is abstract and can not be instantiated, 
//	 * <code>false</code> otherwise.
//	 *
//	 * @return A new instance of Union by initial parameters.
//	 *  
//	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or 
//	 * <code>notInstantiable</code> is <code>null</code>.
//	 * 
//	 */	
//	abstract public Union createUnion( String name, Visibility visibility, Boolean notInstantiable	);	
//	
//	
//	/**
//	 * Factory method that creates a new instance of UnionMember by specific parameters.
//	 * 
//	 * <p>
//	 * UnionMembers are described as features of a Union and each represents one of the members of a Union.
//	 * </p> 
//	 * 
//	 * @param name An identifier for the UnionMember within its containing Namespace. Must not be <code>null</code>.
//	 * 
//	 * @param visibility Specifies extent of the visibility of the UnionMember within its owning Namespace. Must not be 
//	 * <code>null</code>.
//	 *
//	 * @param changeability Specifies whether the value may be modified after the object is created. Must not be 
//	 * <code>null</code>.
//	 * 
//	 * @param type The new Classifier describing the type of this UnionMember. Must not be <code>null</code>.
//	 * 
//	 * @parma memberCase Contains the value of the Union discriminator for this UnionMember. Must not be 
//	 * <code>null</code>.
//	 * 
//     * @param memberCase 
//     * 
//     * @param isDefault Indicates if this UnionMember is the default member of the Union (implying that when unstated,
//	 * the Union's discriminator would assume this instance <i>memberCase value</i>). Must not be <code>null</code>.
//	 * 
//	 * @return A new instance of UnionMember, by initial parameters.
//	 * 
//	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, 
//	 * <code>changeability</code>, <code>type</code>, <code>memberCase</code> or <code>isDefault</code> is 
//	 * <code>null</code>. 
//	 * 
//	 */
//	abstract public UnionMember createUnionMember( 
//		  String name
//		, Visibility visibility
//		, Changeable changeability
//		, GClassifier<?, ?, ?, ?, ?, ?> type
//		, Expression memberCase
//		, Boolean isDefault
//	);	
}