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
package org.ogrehus.jcwm.api.resource.relational;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GEvent;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
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
public interface SQLParameter
extends
	GParameter<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, GEvent< ?, ?, ?, ?, ?>
		, Procedure
	> 
{


//====================================================================================================================================================
// Parameter capabilities	
//====================================================================================================================================================



	/**
	 * Registers a Procedure/Function to this parameter.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: CwmMethod</li>
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
	public Procedure setBehavioralFeature( Procedure procedure );



 //===================================================================================================================================================
 // GBehavioral capabilities	
 //===================================================================================================================================================



	/**
	 * Indicates if null values are valid in this parameter.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : NullableType</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @return Indicates if null values are valid in this parameter.
	 * 
	 */
	Nullable getNullable();



	/**
	 * Indicates if null values are valid in this parameter.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : NullableType</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @param nullable Indicates if null values are valid in this parameter.
	 * 
	 * @return Old value of the property <code>nullable</code>. Can't be <code>null</code>. 
	 * 
	 * @throws NullPointerException If the parameter <code>nullable</code> is <code>null</code>.
	 * 
	 */
	Nullable setNullable( Nullable nullable );



	Boolean isReturnValue();



	Boolean setReturnValue( Boolean isReturnValue );
}