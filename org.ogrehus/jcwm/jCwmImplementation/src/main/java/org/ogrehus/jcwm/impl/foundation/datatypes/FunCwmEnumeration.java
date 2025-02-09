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

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.ogrehus.jcwm.api.foundation.datatypes.CwmEnumeration;
import org.ogrehus.jcwm.api.foundation.datatypes.EnumerationLiteral;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunDataType;


/**
 * The Enumeration class is intended as a starting point from which enumerated data types can be created. 
 * <p>
 * An enumerated data type is a collection of identifiers often used as the permitted states that some other attribute or property of the enumerated
 * type may take.
 * </p>
 * <p>
 * The isOrdered attribute of an Enumeration instance is used to determine if the ordered constraint on the EnumerationLiterals association is
 * relevant for the enumeration. The particular ordering of EnumerationLiteral instances is obtained from the ordered constraint on the association
 * even if the value attributes of the EnumerationLiteral instances contain non-null values that might be used to determine ordering. This is done to
 * provide more flexible ordering semantics.
 * </p>
 * <p>
 * An instance of Enumeration is also required to create a range data type. Refer to the EnumerationLiteral class for details.
 * </p>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunCwmEnumeration
extends
	GFunDataType<TypeSystem, GFeature<?, ?, ?, ?, ?>>
implements
	CwmEnumeration
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
	 * @param firstLiteralName Name of the the first EnumerationLiteral instance relevant for this Enumeration instance.Must not be <code>null</code>.
	 * 
	 * @param firstLiteralVisibility Visibility of the the first EnumerationLiteral instance relevant for this Enumeration instance. Must not 
	 * be <code>null</code>.
	 * 
	 * @param firstLiteralValues optional values for the firstLiteral.
	 * 
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>,
	 * <code>notInstantiable</code>, <code>isOrdered</code>, <code>firstLiteralName</code> or 
	 * <code>firstLiteralName</code> is <code>null</code>.  
	 * 
	 */
	public FunCwmEnumeration( 
		  String name
		, Visibility visibility
		, Boolean notInstantiable
		, Boolean isOrdered 
		, String firstLiteralName
		, Visibility firstLiteralVisibility
		, Expression... firstLiteralValues
	) {
		super( name, visibility, notInstantiable ); 
		this.setOrdered( isOrdered );
		this.literals = new TreeSet<EnumerationLiteral>();
		this.addLiteral( firstLiteralName, firstLiteralVisibility, firstLiteralValues );
	}



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	public TypeSystem setNamespace( TypeSystem typeSystem ) {
		return super.setNamespaceGeneric( typeSystem );
	}



	/**
	 * If <code>true</code>, the ordered constraint on the EnumerationLiterals association is relevant. Otherwise, the ordering of EnumerationLiteral 
	 * instances is considered unspecified.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code>, if the ordered constraint on the EnumerationLiterals association is relevant, <code>false</code> otherwise.
	 * 
	 */
	public Boolean isOrdered() {
		return this.isOrdered;
	}



	/**
	 * If <code>true</code>, the ordered constraint on the EnumerationLiterals association is relevant. Otherwise, the 
	 * ordering of EnumerationLiteral instances is considered unspecified.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>: Boolean</li> 
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param isOrdered <code>true</code>, if the ordered constraint on the EnumerationLiterals association is relevant, <code>false</code> otherwise.
	 * Must not be <code>null</code>. 
	 * 
	 * @return old value of the property <code>isOrdered</code>. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isOrdered</code> was <code>null</code>.
	 * 
	 */
	public Boolean setOrdered( Boolean isOrdered ) {
		if ( isOrdered == null ) {
			throw new NullPointerException( "The parameter: isOrdered must not be null." );
		}
		Boolean old = this.isOrdered;
		this.isOrdered = isOrdered;
		return old;
	}



	/**
	 * Identifies the EnumerationLiteral instances relevant for a particular Enumeration instance.
	 * <p>
	 * If the Enumeration isOrdered attribute is <code>true</code>, the ordering constraint on this reference end can be used to determine a logical
	 * ordering for the EnumerationLiteral instances. Otherwise, ordering is ignored.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: EnumerationLiteral</li>
	 * <li><i>defined by</i>: EnumerationLiterals::literal</li>
	 * <li><i>multiplicity</i>: one or more; ordered</li>
	 * <li><i>inverse</i>: EnumerationLiteral::enumeration</li>
	 * </ul>
	 * 
	 * @return A List of EnumerationLiteral instances relevant for a particular Enumeration instance.
	 * 
	 */
	public SortedSet<EnumerationLiteral> getLiterals() {
		return this.literals;
	}




	public Boolean addLiteral( String name, Visibility visibility, Expression... values ) {
		EnumerationLiteral literal = new FunEnumerationLiteral( name, visibility, this, values );
		
		return this.literals.add( literal );
	}



	public Boolean addLiteral( String name, Visibility visibility, Set<Expression> values ) {
		return addLiteral( name, visibility, values.toArray( new Expression[0] ) );
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================        



	/**
	 * If True, the ordered constraint on the EnumerationLiterals association is relevant. Otherwise, the ordering of EnumerationLiteral instances is 
	 * considered unspecified.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Boolean isOrdered = null;



	/**
	 * Identifies the EnumerationLiteral instances relevant for a particular Enumeration instance.
	 * <p>
	 * If the Enumeration's isOrdered attribute is <code>true</code>, the ordering constraint on this reference end can be used to determine a logical
	 * ordering for the EnumerationLiteral instances. Otherwise, ordering is ignored.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>: EnumerationLiteral</li>
	 * <li><i>defined by</i>: EnumerationLiterals::literal</li>
	 * <li><i>multiplicity</i>: one or more; ordered</li>
	 * <li><i>inverse</i>: EnumerationLiteral::enumeration</li>
	 * </ul>
	 * 
	 */
	protected SortedSet<EnumerationLiteral> literals = null;
}