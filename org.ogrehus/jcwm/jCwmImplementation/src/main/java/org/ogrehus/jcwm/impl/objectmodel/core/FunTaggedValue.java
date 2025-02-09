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
package org.ogrehus.jcwm.impl.objectmodel.core;

import org.ogrehus.jcwm.api.Cwm;
import org.ogrehus.jcwm.api.objectmodel.core.Name;
import org.ogrehus.jcwm.api.objectmodel.core.Stereotype;
import org.ogrehus.jcwm.api.objectmodel.core.TaggedValue;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;

import org.ogrehus.jcwm.impl.FunCwm;


/**
 * A tagged value allows information to be attached to any model element in the form of a �tagged value� pair; that is, name = value.
 * <p>
 * The interpretation of tagged value semantics is intentionally beyond the scope of CWM. It must be determined by user or tool conventions. It is
 * expected that tools will define tags to supply information needed for their operations beyond the basic semantics of CWM. Such information could
 * include code generation options, model management information, or user-specified semantics.
 * </p>
 * <p>
 * Even though TaggedValues are a simple and straightforward extension technique, their use restricts semantic interchange of meta-data to only those
 * tools that share a common understanding of the specific tagged value names.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, August 2008
 * 
 */
public class FunTaggedValue
implements
	TaggedValue
{


//====================================================================================================================================================
// EnumerationLiteral capabilities
//====================================================================================================================================================



	/**
	 * Creates a new instance of TaggedValue by specific parameters.
	 * 
	 * @param tag An identifier for the TaggedValue within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param value The current value of the TaggedValue. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>tag</code> or <code>value</code> is <code>null</code>.
	 * 
	 */
	public FunTaggedValue( String tag, String value ) {
		this.setTag( tag );
		this.setValue( value );
		try {
			this.cwmFactory = Cwm.create( FunCwm.class.getName() );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// can't occur, cause otherwise this class would be missing too! 
		}
	}



	public Cwm getCwmFactory() {
		return this.cwmFactory;
	}



	public String setValue( String value ) {
		if ( value == null ) {
			throw new NullPointerException("Parameter: value may not be null!");
		}
		String old = this.value;
		this.value = value;

		return old;
	}



	public GModelElement<?, ?, ?, ?> setModelElement( GModelElement<?, ?, ?, ?> modelElement ) {
		GModelElement<?, ?, ?, ?> old = this.modelElement;
		this.modelElement = modelElement;

		return old;
	}



	public GModelElement<?, ?, ?, ?> getModelElement() {
		return this.modelElement;
	}



	public Name getTag() {
		return this.tag;
	}



	public String getValue() {
		return this.value;
	}



	public Name setTag( String tag ) {
		return setTag( new FunName( tag ) );
	}



	public Name setTag( Name tag ) {
		if ( tag == null ) {
			throw new NullPointerException( "Parameter: tag may not be null!" );
		}
		Name old = this.tag;
		this.tag = tag;

		return old;
	}



	public Stereotype getStereotype() {
		return this.stereotype;
	}



	public Stereotype setStereotype( Stereotype stereotype ) {
		Stereotype old = this.stereotype;
		this.stereotype = stereotype;
		return old;
	}



//	@Override
//	public boolean equals( Object anObject ) {
//		if (this == anObject ) {
//		    return true;
//		}
//		if ( !(anObject instanceof TaggedValue) ) {
//			return false;
//		}
//
//		return this.getTag().equals( ((TaggedValue)anObject).getTag() );
//		    
//	}



//====================================================================================================================================================
// properties
//====================================================================================================================================================



	/**
	 * Contains the name of the TaggedValue. This name determines the semantics that are applicable to the contents of the value attribute.
	 * <ul>
	 * <li><i>type</i>			: Name</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Name tag = null;



	/**
	 * Contains the current value of the TaggedValue.
	 * 
	 * <ul>
	 * <li><i>type</i>			: String</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected String value = null;



	/**
	 * References the ModelElement to which the TaggedValue pertains.
	 *
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: TaggedElement::modelElement</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse<i>		: ModelElement::taggedValue</li>
	 * </ul>
	 * 
	 */
	protected GModelElement<?, ?, ?, ?> modelElement = null;



	/**
	 * References a Stereotype that uses the TaggedValue.
	 * 
	 * <ul>
	 * <li><i>class</i>			: Stereotype</li>
	 * <li><i>defined by</i>	: StereotypeTaggedValues</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Stereotype::requiredTag</li>
	 * </ul>
	 * 
	 */
	protected Stereotype stereotype = null;



	protected Cwm cwmFactory = null;
}