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
package org.ogrehus.jcwm.api.objectmodel.behavioral;

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;

/**
 * BehavioralPackage provides hierarchical access to any factory method in this jCwmAPI in scope of the package core.
 * <p>
 * To work with the jCwm you need to choose an implementation of the API. For example its recommends to use the reference implementation provided by 
 * <a href="http://www.ogrehus.org">www.ogrehus.org</a> too. Otherwise you can implement your own if you need specific functionality like more 
 * immutable objects for more thread safety or final classes that can't be extended for security reasons. 
 * <p>
 * <b>Usage of BehavioralPackage:</b>
 * </p>
 * <pre>
 * ...
 * BehavioralPackage behavioral = BehavioralPackage.create( "org.ogrehus.jcwm.impl.objectmodel.behavioral.FunBehavioralPackage" );
 * CwmMethod         method     = behavioral.createMethod(...);
 * Parameter         parameter  = behavioral.createParameter(...); 
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
public abstract class BehavioralPackage {


	/**
	 * Creates a new instance of the BehavioralPackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class
	 * <code>org.ogrehus.jcwm.api.objectmodel.behavioral.BehavioralPackage</code>.
	 * 
	 * @return A new instance of BehavioralPackage, determined by the specific classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends
	 * <code>org.ogrehus.jcwm.api.objectmodel.behavioral.BehavioralPackage</code> or if the class does not provide a simple public constructor
	 * without any parameters. 
	 * 
	 */
	public static final BehavioralPackage create( String classPath )
	throws 
		ClassNotFoundException
	{
		try {
			Object behavioral = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( behavioral instanceof BehavioralPackage ) {
				return (BehavioralPackage)behavioral;
			}
			throw new ClassNotFoundException( "Invalid Class, it must extend " +
					"org.ogrehus.jcwm.api.objectmodel.behavioral.BehavioralPackage:" + classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid BehavioralPackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid BehavioralPackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid BehavioralPackage class ([security] unable to find in current class loader):" + classPath, e );
		}
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
	abstract public CallAction createCallAction( String name, Visibility visibility, Operation operation );



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
	abstract public Event createEvent( String name, Visibility visibility );



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
	abstract public Interface createInterface( String name, Visibility visibility );



	/**
	 * Factory method that creates a new instance of CwmMethod by specific parameters.
	 * <p>
	 * Method is the implementation of an Operation. It specifies the algorithm or procedure that effects the results of an Operation.
	 * </p>
	 * 
	 * @param specification The Operation which will be linked to the Method instance(s) that realize it. Must not be <code>null</code>.
	 * 
	 * @param body A specification of the CwmMethod in some appropriate form (such as a programming language). The exact form of a CwmMethod's
	 * specification and knowledge of the language in which it is described is outside the scope of the CWM. Must not be <code>null</code>.
	 * <p>
	 * <b>initial settings:</b>:
	 * </p>
	 * <ul>
	 * <li><code>name - </code> Taken from the Parameter <code>specification</code>.
	 * <li><code>visibility - </code> Taken from the Parameter <code>specification</code>.
	 * <li><code>isQuery - </code> Taken from the Parameter <code>specification</code>.
	 * </ul>
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
	abstract public CwmMethod createMethod( Operation specification, ProcedureExpression body );



	/**
	 * Factory method that creates a new instance of Operation by specific parameters.
	 * 
	 * @param name An identifier for the Operation within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Operation within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param isQuery Specifies whether an execution of the Operation leaves the state of the system unchanged. <code>true</code> indicates that the
	 * state is unchanged; <code>false</code> indicates that side-effects may occur. Must not be <code>null</code>.
	 * 
	 * @param isAbstract If <code>true</code>, then the Operation does not have an implementation, and one must be supplied by a descendant. In the 
	 * meta-model, an Operation is a BehavioralFeature that can be applied to instances of the Classifier that contains the Operation. Operation is 
	 * the specification, while CwmMethod is the implementation. Must not be <code>null</code>.
	 *  
	 * @return A new instance of Operation, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>,
	 * <code>isQuery</code> or <code>isAbstract</code> is <code>null</code>.
	 * 
	 */
	abstract public Operation createOperation( String name, Visibility visibility, Boolean isQuery, Boolean isAbstract );



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
	abstract public <T extends GClassifier<?, ?, ?, ?, ?, ?>> Parameter createParameter( 
		  String name
		, Visibility visibility
		, ParameterDirection kind
		, T type
	);
}