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
package org.ogrehus.jcwm.impl.objectmodel.core.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Changeable;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GAttribute;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * An Attribute describes a named slot within a Classifier that may hold a value.
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GFunStructuralFeature.
 * @param <PACKAGE> The type of the importer of this GFunStructuralFeature.
 * @param <DEPENDENCY> The type of ClientDependency of this GFunStructuralFeature.
 * @param <CONSTRAINT> The type of the Constraint under which this GFunStructuralFeature is. 
 * @param <CLASSIFIER> The type of the Classifier that owns this GFunStructuralFeature. 
 * @param <TYPE> The type of a GFunStructuralFeature. 
 *
 */
public abstract class GFunAttribute<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, CLASSIFIER extends GClassifier< ?, ?, ?, ?, ?, ?>
	, TYPE       extends GClassifier< ?, ?, ?, ?, ?, ?>
>extends
	GFunStructuralFeature<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, CLASSIFIER, TYPE>
implements
	GAttribute<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, CLASSIFIER, TYPE> 
{


	/**
	 * Creates a new instance of FunBehavioralFeature by specific parameters.
	 * 
	 * @param name An identifier for the FunStructuralFeature within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunStructuralFeature within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param changeability The changeability of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @param type The new Classifier describing the type of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code> or
	 * <code>type</code> is <code>null</code>.
	 * 
	 */
	protected GFunAttribute( String name, Visibility visibility, Changeable changeability,  TYPE type ) {
		super( name, visibility, changeability, type ); // may throw NullPointerException
	}



//====================================================================================================================================================
// Attribute capabilities
//====================================================================================================================================================



	public Expression getInitialValue() {
		return this.initialValue;
	}



	public Expression setInitialValue( Expression initialValue ) {
		Expression old = this.initialValue;
		this.initialValue = initialValue;
		return old;
	}	



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunAttribute" );
		if ( this.initialValue != null ) {
			out.append( " | initialValue=" + this.initialValue.getBody() );
		}
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}



//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * Attribute: initialValue
	 * 
	 * An Expression specifying the value of the attribute upon initialization. It is meant to be evaluated at the time the object is initialized.
	 * (Note that an explicit constructor may supersede an initial value.)
	 * 
	 * <ul>
	 * <li><i>type</i>: Expression</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 */
	protected Expression initialValue = null;
}