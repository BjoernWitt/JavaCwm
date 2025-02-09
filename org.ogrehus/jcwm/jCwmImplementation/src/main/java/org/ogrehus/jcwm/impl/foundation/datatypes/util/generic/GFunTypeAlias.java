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
package org.ogrehus.jcwm.impl.foundation.datatypes.util.generic;

import org.ogrehus.jcwm.api.foundation.datatypes.util.generic.GTypeAlias;
import org.ogrehus.jcwm.api.objectmodel.core.util.ConstraintViolation;
import org.ogrehus.jcwm.api.objectmodel.core.util.CwmConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunDataType;


/**
 * The TypeAlias class is intended to provide a renaming capability for Classifier instances. 
 * <p>
 * This class is required to support situations in which creation of an alias for a class effectively creates a new class. For example, CORBA IDL type
 * aliases have different typeCodes than their base types and are therefore treated as distinct types.
 * </p>
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation_C-4-1]</b> A TypeAlias instance cannot alias itself.</li>
 * </ul> 
 *   
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <FEATURE> Type of the ordered list of Features owned by this  TypeAlias. 
 * @param <TYPE> The type of the GClassifier for which this TypeAlias instance acts as an alias.
 * 
 */
public abstract class GFunTypeAlias<
	  NAMESPACE extends GCwmPackage<?,?,?,?,?,?,?>
	, FEATURE   extends GFeature<?, ?, ?, ?, ?>
	, TYPE      extends GClassifier<?, ?, ?, ?, ?, ?>
> extends
	GFunDataType<NAMESPACE, FEATURE>
implements
	GTypeAlias<NAMESPACE, FEATURE, TYPE>
{


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
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>notInstantiable</code>
	 * or <code>type</code> is <code>null</code>.  
	 * 
	 */
	protected GFunTypeAlias( String name, Visibility visibility, Boolean notInstantiable, TYPE type	) {
		super( name, visibility, notInstantiable ); // may throw NullPointerException
		this.setTypeGeneric( type );
	}



//====================================================================================================================================================
// TypeAlias capabilities
//====================================================================================================================================================



	/**
	 * Identifies the Classifier instance for which this TypeAlias instance acts as an alias.
	 * <p>
	 * The ClassifierAlias association connects TypeAlias instances with the Classifier instances that they rename.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic GClassifier</li>
	 * <li><i>defined by</i> 	: ClassifierAlias::type</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return A Classifier instance for which this TypeAlias instance acts as an alias. Can't be <code>null</code>.
	 * 
	 */
	public TYPE getType() {
		return this.type;
	}



	/**
	 * Registers a Classifier instance for which this TypeAlias instance acts as an alias.
	 * <p>
	 * The ClassifierAlias association connects TypeAlias instances with the Classifier instances that they rename.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic GClassifier</li>
	 * <li><i>defined by</i> 	: ClassifierAlias::type</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[Foundation_C-4-1]</b> A TypeAlias instance cannot alias itself.
	 * <pre>
	 * <b>context</b> TypeAlias <b>inv</b>:
	 * self.type <> self
	 * </pre>
	 * </li>
	 * </ul>
	 * 
	 * @param type Classifier instance for which this TypeAlias instance acts as an alias. Can't be <code>null</code>.
	 * 
	 * @return Old value of the property <code>length</code>. Can be <code>null</code> if none exists.
	 * 
	 * @throws NullPointerException if the parameter <code>type</code> is <code>null</code>.
	 * 
	 * @throws ConstraintViolation if the constraint <b>[Foundation_C-4-1]</b> is violated. 
	 * 
	 */
	protected TYPE setTypeGeneric( TYPE type ) {
		if ( type == null ) {
			throw new NullPointerException( "The parameter: type must not be null!" );
		}
		
		if ( this.getClass().equals( type.getClass() ) ) {
			throw new ConstraintViolation( CwmConstraint.Foundation_C_4_1, this.getQualifiedName() );
		}
		
		TYPE old = this.type;
		this.type = type;
		
		return old;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunTypeAlias" );
		if ( this.type != null ) {
			out.append( " | type=" + this.type.getSimpleName() + "<" + this.type.getClass().getSimpleName() + ">" );
		}
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		
		return out.toString();
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Identifies the Classifier instance for which this TypeAlias instance acts as an alias.
	 * <p>
	 * The ClassifierAlias association connects TypeAlias instances with the Classifier instances that they rename.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic TYPE</li>
	 * <li><i>defined by</i> 	: ClassifierAlias::type</li> 
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected TYPE type = null;
}