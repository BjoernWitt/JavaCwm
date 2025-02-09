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

import java.util.HashSet;
import java.util.Set;
import org.ogrehus.jcwm.api.foundation.datatypes.CwmEnumeration;
import org.ogrehus.jcwm.api.foundation.datatypes.EnumerationLiteral;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;


/**
 * EnumerationLiteral instances describe the enumeration identifiers, and possibly the values, associated with an enumerated data type. Enumeration
 * identifiers are contained in the name attribute derived from the EnumerationLiteral instance ModelElement superclass.
 * <p>
 * EnumerationLiteral instances may also be used to define expression-based values such as ranges. To do so, simply state the membership expression
 * in the instance value. For example, a range literal can be created by setting the value attribute to m..n, where m represents the lower bound of
 * the range, and n, the upper bound. In this way, ranges and other more complicated expressions can be intermixed with simple enumeration literals.
 * For example, an enumeration might contain the literals 1, 2, 4..7, and > 10.
 * </p>
 * <p>
 * Consequently, a simple range data type can be created with an Enumeration instance that owns a single EnumerationLiteral instance. A model 
 * attribute of this data type might then be declared as posInt : PositiveInteger.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunEnumerationLiteral
extends
	GFunModelElement<
		  CwmEnumeration
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	>
implements
	EnumerationLiteral
{


	/**
	 * Factory method that creates a new instance of EnumerationLiteral by specific parameters.
	 * 
	 * <p>
	 * EnumerationLiteral instances describe the enumeration identifiers, and possibly the values, associated with an enumerated data type.
	 * Enumeration identifiers are contained in the name attribute derived from the EnumerationLiteral instance ModelElement superclass.
	 * </p>
	 * 
	 * @param name An identifier for the EnumerationLiteral within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the EnumerationLiteral within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param enumeration 
	 * 
	 * @param initialValues The optional initial values of this literal.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.  
	 * 
	 */
	public FunEnumerationLiteral( 
		  String name
		, Visibility visibility
		, CwmEnumeration enumeration
		, Expression... initialValues 
	) {
		super( name, visibility );
		setEnumeration( enumeration );
		this.values = new HashSet<Expression>();
		if ( initialValues != null && initialValues.length > 0 ) {
			for ( Expression expression : initialValues ) {
				this.addValue( expression );
			}
		}
	}



//====================================================================================================================================================
// EnumerationLiteral capabilities	
//====================================================================================================================================================



	/**
	 * The value associated with an enumeration identifier can be stored here.
	 * <p> 
	 * The attribute is optional because enumeration literals are not required to have a specific, displayable value. This is indicated by either an
	 * empty value attribute or a value attribute value whose expression body attribute is a zero-length string.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>: Expression</li> 
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 * 
	 * @return A Collection of values associated with an enumeration identifier that can be stored here. Can not be <code>null</code>.
	 * 
	 */
	public Set<Expression> getValues() {
		return this.values;
	}



	public Boolean addValue( Expression expression ) {
		return this.values.add( expression );
	}



	public Boolean removeValue( Expression expression ) {
		return this.values.remove( expression );
	}



	/**
	 * Identifies the Enumeration instance for which this enumeration literal is relevant.
	 * <p>
	 * The EnumerationLiterals association links enumeration literals to the Enumeration instances that contain them. 
	 * </p>
	 * <p>
	 * If the Enumeration's isOrdered attribute is True, the ordering constraint on the association is relevant. Otherwise, it is ignored.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Enumeration</li>
	 * <li><i>defined by</i>: EnumerationLiterals::enumeration</li>
	 * <li><i>multiplicity</i>: exactly one</li>  
	 * <li><i>inverse</i>: Enumeration::literal</li>
	 * </ul>
	 * 
	 * @return the Enumeration instance for which this enumeration literal is relevant. Can't be <code>null</code>.
	 * 
	 */
	public CwmEnumeration getEnumeration() {
		return this.enumeration;
	}



	/**
	 * Identifies the Enumeration instance for which this enumeration literal is relevant.
	 * <p>
	 * The EnumerationLiterals association links enumeration literals to the Enumeration instances that contain them. 
	 * </p>
	 * <p>
	 * If the Enumeration isOrdered attribute is True, the ordering constraint on the association is relevant. Otherwise, it is ignored.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>: Enumeration</li>
	 * <li><i>defined by</i>: EnumerationLiterals::enumeration</li>
	 * <li><i>multiplicity</i>: exactly one</li>  
	 * <li><i>inverse</i>: Enumeration::literal</li>
	 * </ul>
	 *
	 * @param enumeration The Enumeration instance for which this enumeration literal is relevant.
	 * 
	 * @return Old value of the property <code>enumeration</code>.
	 * 
	 * @throws NullPointerException Is thrown if the parameter: <code>enumeration</code> is <code>null</code>.
	 * 
	 */
	public CwmEnumeration setEnumeration( CwmEnumeration enumeration ) {
		if ( enumeration == null ) {
			throw new NullPointerException( "The parmeter: enumeration must not be null." );
		}
		CwmEnumeration old = this.enumeration;
		this.enumeration = enumeration;
		
		return old;
	}



//====================================================================================================================================================
// EnumerationLiteral capabilities	
//====================================================================================================================================================



	/**
	 * The value associated with an enumeration identifier can be stored here.
	 * <p>
	 * The attribute is optional because enumeration literals are not required to have a specific, displayable value. This is indicated by either an
	 * empty value attribute or a value attribute value whose expression body attribute is a zero-length string.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>: Expression</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 * 
	 */
	protected Set<Expression> values = null;



	/**
	 * Identifies the Enumeration instance for which this enumeration literal is relevant.
	 * <p>
	 * The EnumerationLiterals association links enumeration literals to the Enumeration instances that contain them. 
	 * </p>
	 * <p>
	 * If the Enumeration isOrdered attribute is True, the ordering constraint on the association is relevant. Otherwise, it is ignored.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Enumeration</li>
	 * <li><i>defined by</i>: EnumerationLiterals::enumeration</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * <li><i>inverse</i>: Enumeration::literal</li>
	 * </ul>
	 * 
	 */
	protected CwmEnumeration enumeration = null;
}