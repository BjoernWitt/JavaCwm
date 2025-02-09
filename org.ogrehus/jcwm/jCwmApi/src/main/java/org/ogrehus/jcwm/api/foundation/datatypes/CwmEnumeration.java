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
package org.ogrehus.jcwm.api.foundation.datatypes;

import java.util.Set;
import java.util.SortedSet;

import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;


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
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface CwmEnumeration
extends
	GDataType<TypeSystem, GFeature<?, ?, ?, ?, ?>>
{



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
	Boolean isOrdered();



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
	 * @param isOrdered <code>true</code>, if the ordered constraint on the EnumerationLiterals association is relevant, <code>false</code> 
	 * otherwise. Must not be <code>null</code>. 
	 * 
	 * @return old value of the property <code>isOrdered</code>. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isOrdered</code> was <code>null</code>.
	 *  
	 */
	Boolean setOrdered( Boolean isOrdered );



	/**
	 * Identifies the EnumerationLiteral instances relevant for a particular Enumeration instance. 
	 * <p>
	 * If the Enumeration isOrdered attribute is <code>true</code>, the ordering constraint on this reference end can be used to determine a 
	 * logical ordering for the EnumerationLiteral instances. Otherwise, ordering is ignored.
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
	SortedSet<EnumerationLiteral> getLiterals(); 



	Boolean addLiteral( String name, Visibility visibility, Expression... values );



	Boolean addLiteral( String name, Visibility visibility, Set<Expression> values );
}
