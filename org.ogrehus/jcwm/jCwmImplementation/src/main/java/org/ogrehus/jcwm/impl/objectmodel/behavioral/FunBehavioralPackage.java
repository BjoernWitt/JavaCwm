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

import org.ogrehus.jcwm.api.objectmodel.behavioral.Argument;
import org.ogrehus.jcwm.api.objectmodel.behavioral.BehavioralPackage;
import org.ogrehus.jcwm.api.objectmodel.behavioral.CallAction;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Event;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Interface;
import org.ogrehus.jcwm.api.objectmodel.behavioral.CwmMethod;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Parameter;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;

/**
 * FunBehavioralPackage provides hierarchical access to any factory method in this jCwmAPI in scope of the package core.
 * <p>
 * To work with the jCwm you need to choose an implementation of the API. For example its recommends to use the reference implementation provided by
 * <a href="http://www.ogrehus.org">www.ogrehus.org</a> too. Otherwise you can implement your own if you need specific functionality like more 
 * immutable objects for more thread safety or final classes that can't be extended for security reasons.
 * <p>
 * <b>Usage of FunBehavioralPackage:</b>
 * </p>
 * <pre>
 * ...
 * FunBehavioralPackage behavioral = FunBehavioralPackage.create( "org.ogrehus.jcwm.impl.objectmodel.behavioral.FunBehavioralPackage" );
 * CwmMethod            method     = behavioral.createMethod(...);
 * Parameter            parameter  = behavioral.createParameter(...);
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
public class FunBehavioralPackage
extends
	BehavioralPackage
{


	/**
	 * Factory method that creates a new instance of Argument by specific parameters.
	 * 
	 * @param name An identifier for the Argument within its containing  Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Argument within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param value An expression determining the actual Argument instance when executed. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Argument, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>value</code>
	 * is <code>null</code>.
	 * 
	 */
	public Argument createArgument( String name, Visibility visibility, Expression value ) {
		return new FunArgument( name, visibility, value );
	}



	/**
	 * Factory method that creates a new instance of CallAction by specific parameters.
	 * 
	 * @param name An identifier for the CallAction within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CallAction within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param operation The Operation that will be invoked when the CallAction is executed. Must not be <code>null</code>.
	 * 
	 * @return A new instance of CallAction, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>operation</code>
	 * is <code>null</code>.
	 * 
	 */
	public CallAction createCallAction( String name, Visibility visibility, Operation operation ) {
		return new FunCallAction( name, visibility, operation );
	}



	/**
	 * Factory method that creates a new instance of this Event by specific parameters.
	 * 
	 * @param name An identifier for the Event within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Event within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @return A new instance of Event, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	public Event createEvent( String name, Visibility visibility ) {
		return new FunEvent( name, visibility );
	}



	/**
	 * Factory method that creates a new instance of this Interface by specific parameters.
	 * 
	 * @param name An identifier for the Interface within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Interface within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @return A new instance of Interface, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	public Interface createInterface( String name, Visibility visibility ) {
		return new FunInterface( name, visibility );
	}



	/**
	 * Factory method that creates a new instance of CwmMethod by specific parameters.
	 * <p>
	 * Method is the implementation of an Operation. It specifies the algorithm or procedure that effects the results of an Operation.
	 * </p>
	 * 
	 * @param name An identifier for the CwmMethod within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmMethod within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param isQuery Specifies whether an execution of the CwmMethod leaves the state of the system unchanged. <code>true</code> indicates that the
	 * state is unchanged; <code>false</code> indicates that side-effects may occur. Must not be <code>null</code>.
	 * 
	 * @param specification The Operation which will be linked to the Method instance(s) that realize it. Must not be <code>null</code>.
	 * 
	 * @param body A specification of the CwmMethod in some appropriate form (such as a programming language). The exact form of a CwmMethod
	 * specification and knowledge of the language in which it is described is outside the scope of the CWM. Must not be <code>null</code>.
	 *  
	 * @return A new instance of CwmMethod, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, <code>isQuery</code>,
	 * <code>specification</code>  or <code>body</code> is <code>null</code>.
	 * 
	 * @throw ConstraintViolation Is thrown if one of the following CwmConstraints is violated: <code>C_4_7</code>, <code>C_4_8</code>,
	 * <code>C_4_9</code>, <code>C_4_10</code>, <code>C_4_11</code> or <code>C_4_12</code>.
	 * 
	 */
	public CwmMethod createMethod( Operation specification, ProcedureExpression body ) {
		return new FunCwmMethod( specification.getSimpleName()
							   , specification.getVisibility()
							   , specification
							   , body );
	}



	/**
	 * Factory method that creates a new instance of Operation by specific parameters.
	 * 
	 * @param name An identifier for the Operation within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Operation within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param isQuery Specifies whether an execution of the Operation leaves the state of the system unchanged.<code>true</code> indicates that the
	 * state is unchanged; <code>false</code> indicates that side-effects may occur. Must not be <code>null</code>.
	 * 
	 * @param isAbstract If <code>true</code>, then the Operation does not have an implementation, and one must be supplied by a descendant. In the
	 * meta-model, an Operation is a BehavioralFeature that can be applied to instances of the Classifier that contains the Operation. Operation is
	 * the specification, while CwmMethod is the implementation. Must not be <code>null</code>.
	 *  
	 * @return A new instance of Operation, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, <code>isQuery</code>
	 * or <code>isAbstract</code> is <code>null</code>.
	 * 
	 */
	public Operation createOperation( String name, Visibility visibility, Boolean isQuery, Boolean isAbstract ) {
		return new FunOperation( name, visibility, isQuery, isAbstract );
	}



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
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, <code>kind</code>
	 * or <code>type</code> is <code>null</code>.
	 * 
	 */
	public <T extends GClassifier<?, ?, ?, ?, ?, ?>> Parameter createParameter( 
		  String name
		, Visibility visibility
		, ParameterDirection kind
		, T type
	) { 
		return new FunParameter( name, visibility, kind, type );
	}
}