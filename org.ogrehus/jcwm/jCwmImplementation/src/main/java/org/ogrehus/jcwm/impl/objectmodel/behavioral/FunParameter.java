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
package org.ogrehus.jcwm.impl.objectmodel.behavioral;

import org.ogrehus.jcwm.api.objectmodel.behavioral.CwmMethod;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Event;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Parameter;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GBehavioralFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.objectmodel.behavioral.util.generic.GFunParameter;


/**
 * Parameters are used in the specification of operations, methods, and events. 
 * <p>
 * A FunParameter may include a name, type, and direction of communication.
 * </p>  
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunParameter
extends
	GFunParameter<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Event
		, GBehavioralFeature<?, ?, ?, ?>
> implements
	Parameter
{


	/**
	 * Factory method that creates a new instance of Parameter by specific parameters.
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
	public <T extends GClassifier<?, ?, ?, ?, ?, ?>> FunParameter( 
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
	 * Registers a Operation to this parameter.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Operation</li>
	 * <li><i>defined by</i>: BehavioralFeatureParameter::behavioralFeature</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: BehavioralFeature::parameter</li>
	 * </ul>
	 * 
	 * @param Operation The new operation for the reference <code>behavioralFeature</code>.
	 * 
	 * @return The old value of the property <code>behavioralFeature</code>. Can be <code>null</code> if none was avail before.
	 * 
	 */
	public GBehavioralFeature<?, ?, ?, ?> setBehavioralFeature( Operation operation ) {
		return super.setBehavioralFeatureGeneric( operation );
	}



	/**
	 * Registers a CwmMethod to this parameter.
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
	 * @param cwmMethod The new mathod for the reference <code>behavioralFeature</code>.
	 * 
	 * @return The old value of the property <code>behavioralFeature</code>. Can be <code>null</code> if none was avail before.
	 * 
	 */
	public GBehavioralFeature<?, ?, ?, ?> setBehavioralFeature( CwmMethod cwmMethod ) {
		return super.setBehavioralFeatureGeneric( cwmMethod );
	}



	public Event setEvent( Event event ) {
		return super.setEventGeneric( event );
	}
}