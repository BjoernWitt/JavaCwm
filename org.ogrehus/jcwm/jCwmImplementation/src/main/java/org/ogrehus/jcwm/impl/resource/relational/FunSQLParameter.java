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
package org.ogrehus.jcwm.impl.resource.relational;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GEvent;
import org.ogrehus.jcwm.impl.objectmodel.behavioral.util.generic.GFunParameter;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.Procedure;
import org.ogrehus.jcwm.api.resource.relational.SQLParameter;
import org.ogrehus.jcwm.api.resource.relational.util.Nullable;


/**
 * Parameters of stored procedures.
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunSQLParameter
extends
	GFunParameter<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, GEvent< ?, ?, ?, ?, ?>
		, Procedure
	>
implements
	SQLParameter
{


	/**
	 * Factory method that creates a new instance of FunSQLParameter by specific parameters.
	 * 
	 * @param name An identifier for the Parameter within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Parameter within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param kind Specifies what kind of a Parameter is required. Must not be <code>null</code>. 
	 * 
	 * @param type Designates a Classifier to which an argument value must conform. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Parameter, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, <code>kind</code> or
	 * <code>type</code> is <code>null</code>.
	 * 
	 */
	public <T extends GClassifier<?, ?, ?, ?, ?, ?>> FunSQLParameter( 
		  String name
		, Visibility visibility
		, ParameterDirection kind
		, T type
	) {
		super( name, visibility, kind, type );
	}



//====================================================================================================================================================
// Parameter capabilities
//====================================================================================================================================================



	/**
	 * Registers a Procedure/Function to this parameter.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Procedure</li>
	 * <li><i>defined by</i>: BehavioralFeatureParameter::behavioralFeature</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: BehavioralFeature::parameter</li>
	 * </ul>
	 * 
	 * @param procedure The new procedure for the reference <code>behavioralFeature</code>.
	 * 
	 * @return The old value of the property <code>behavioralFeature</code>. Can be <code>null</code> if none was avail before.
	 * 
	 */
	public Procedure setBehavioralFeature( Procedure procedure ) {
		return super.setBehavioralFeatureGeneric( procedure );
	}



	public Nullable getNullable() {
		return this.nullable;
	}



	public Nullable setNullable( Nullable nullable ) {
		Nullable old = this.nullable;
		this.nullable = nullable;

		return old;
	}



	/**
	 * Default is <code>false</code>
	 * 
	 */
	public Boolean isReturnValue() {
		return this.returnValue==null?Boolean.FALSE:this.returnValue;
	}



	public Boolean setReturnValue( Boolean isReturnValue ) {
		Boolean old = isReturnValue();
		this.returnValue = isReturnValue;

		return old;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunSQLParameter" );
		if ( this.nullable != null ) {
			out.append( " | nullable=" + this.nullable );
		}
		if ( this.returnValue != null ) {
			out.append( " | returnValue=" + this.returnValue );
		}
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	protected Nullable nullable = null;



	protected Boolean  returnValue = null;
}