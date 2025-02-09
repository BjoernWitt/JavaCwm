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
package org.ogrehus.jcwm.api.objectmodel.core;

import java.lang.reflect.InvocationTargetException;

import org.ogrehus.jcwm.api.objectmodel.behavioral.Interface;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.Changeable;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;

/**
 * CorePackage provides hierarchical access to any factory method in this jCwmAPI in scope of the package core.
 * <p>
 * To work with the jCwm you need to choose an implementation of the API. For example its recommends to use the reference implementation provided by
 * <a href="http://www.ogrehus.org">www.ogrehus.org</a> too. Otherwise you can implement your own if you need specific functionality like more 
 * immutable objects for more thread safety or final classes that can't be extended for security reasons. 
 * <p>
 * <b>Usage of CorePackage:</b>
 * </p>
 * <pre>
 * ...
 * CorePackage core     = CorePackage.create( "org.ogrehus.jcwm.impl.objectmodel.core.FunCorePackage" );
 * CwmPackage  aPackage = core.createCwmPackage(...);
 * DataType    aInt     = core.createDatatType(...); 
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
public abstract class CorePackage {


	/**
	 * Creates a new instance of the CorePackage.
	 * <p>
	 * The new instance depends on the classPath determined by the parameter <code>classPath</code>.
	 * 
	 * @param classPath ClassPath of the class that will be instantiated. The class must extend the abstract class 
	 * <code>org.ogrehus.jcwm.api.objectmodel.core.CorePackage</code>.
	 * 
	 * @return A new instance of CorePackage, determined by the specific classPath.
	 * 
	 * @throws ClassNotFoundException If the class can not be found, or if the class does not extends 
	 * <code>org.ogrehus.jcwm.api.objectmodel.core.CorePackage</code> or if the class does not provide a simple public constructor without any 
	 * parameters.
	 * 
	 */
	public static final CorePackage create( String classPath )
	throws 
		ClassNotFoundException
	{
		try {
			Object core = Class.forName( classPath ).getDeclaredConstructor().newInstance();
			if ( core instanceof CorePackage ) {
				return (CorePackage)core;
			}
			throw new ClassNotFoundException( "Invalid Class, it must extend org.ogrehus.jcwm.api.objectmodel.core.CorePackage:" + classPath );
		} catch (InstantiationException e) {
			throw new ClassNotFoundException( "Invalid CorePackage class:" + classPath, e );
		} catch (IllegalArgumentException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
			throw new ClassNotFoundException( "Invalid CorePackage class (default constructor is missing):" + classPath, e );
		} catch (SecurityException e) {
			throw new ClassNotFoundException( "Invalid CorePackage class ([security] unable to find in current class loader):" + classPath, e );	
		}
	}




	/**
	 * Factory method that creates a new instance of CwmAny, by predefined initial parameters.
	 * 
	 * <p>
	 * Predefined Parameters
	 * </p>
	 * <ul>
	 * <li><code>name</code> - Any</li>
	 * <li><code>visibility</code> - public</li>
	 * <li><code>notInstantiable</code> - true</li>
	 * </ul>
	 * 
	 * <p>
	 * The Any data type is used to indicate that an attribute or parameter may take values from any of the available data types.
	 * </p>
	 * 
	 * <p>
	 * In CWM, the set of data types an Any attribute or parameter may assume includes the data types and enumerations, described in this chapter
	 * plus any available instances of the Classifier class.
	 * </p>
	 * 
	 * <p>
	 * There is no default value for data type Any.
	 * </p>
	 * 
	 * @return A new instance of CwmAny, by predefined initial parameters.
	 * 
	 */
	abstract public CwmAny createAny();



	/**
	 * Factory method that creates a new instance of CwmBoolean, by predefined initial parameters.
	 * 
	 * <p>
	 * Predefined Parameters
	 * </p>
	 * <ul>
	 * <li><code>name</code> - Boolean</li>
	 * <li><code>visibility</code> - public</li>
	 * <li><code>notInstantiable</code> - true</li>
	 * </ul>
	 * 
	 * <p>
	 * Boolean defines an enumeration that denotes a logical condition.
	 * </p>
	 * 
	 * <p>
	 * Its enumeration literals are:
	 * </p>
	 * <ul>
	 * <li><code>true</code> - The Boolean condition is satisfied.</li>
	 * <li><code>false</code> - The Boolean condition is not satisfied.</li>
	 * </ul>
	 * 
	 * <p>
	 * The default for data type Boolean is false.
	 * </p>
	 * 
	 * <p>
	 * There is no default value for data type Any.
	 * </p>
	 * 
	 * @return A new instance of CwmBoolean, by predefined initial parameters.
	 * 
	 */
	abstract public CwmBoolean createBoolean();



	/**
	 * Factory method that creates a new instance of CwmFloat, by predefined initial parameters.
	 * 
	 * <p>
	 * Predefined Parameters
	 * </p>
	 * <ul>
	 * <li><code>name</code> - Float</li>
	 * <li><code>visibility</code> - public</li>
	 * <li><code>notInstantiable</code> - true</li>
	 * </ul>
	 * 
	 * <p>
	 * The Float data type is used to indicate that an attribute or parameter may take on floating point numeric values.
	 * </p>
	 * 
	 * <p>
	 * The number of significant digits and other representational details are implementation defined.
	 * </p>
	 * 
	 * <p>
	 * The default for the Float data type is the value 0.0.
	 * </p>
	 * 
	 * @return A new instance of CwmFloat, by predefined initial parameters.
	 * 
	 */
	abstract public CwmFloat createFloat();



	/**
	 * Factory method that creates a new instance of CwmInteger, by predefined initial parameters.
	 * 
	 * <p>
	 * Predefined Parameters
	 * </p>
	 * <ul>
	 * <li><code>name</code> - Integer</li>
	 * <li><code>visibility</code> - public</li>
	 * <li><code>notInstantiable</code> - true</li>
	 * </ul>
	 * 
	 * <p>
	 * Integer represents the predefined type of integers.
	 * </p>
	 * 
	 * <p>
	 * An instance of Integer is an element in the (infinite) set of integers (-2, -1, 0, 1, 2).
	 * </p>
	 * 
	 * <p>
	 * The default for Integer is 0.
	 * </p>
	 * 
	 * @return A new instance of CwmInteger, by predefined initial parameters.
	 * 
	 */
	abstract public CwmInteger createInteger();



	/**
	 * Factory method that creates a new instance of CwmString, by predefined initial parameters.
	 * 
	 * <p>
	 * Predefined Parameters
	 * </p>
	 * <ul>
	 * <li><code>name</code> - String</li>
	 * <li><code>visibility</code> - public</li>
	 * <li><code>notInstantiable</code> - true</li>
	 * </ul>
	 * 
	 * <p>
	 * String defines a piece of text.
	 * </p>
	 * 
	 * <p>
	 * Strings do not normally have a defined length; rather, they are considered to be arbitrarily long (practical limits on the length of Strings
	 * exist, but are implementation dependent). When String is used as the type of an Attribute, string length sometimes can be specified (see the
	 * Relational and Record packages for examples).
	 * </p>
	 * 
	 * <p>
	 * The default for the String data type is an empty string.
	 * </p>
	 * 
	 * @return A new instance of CwmString, by predefined initial parameters.
	 * 
	 */
	abstract public CwmString createString();



	/**
	 * Factory method that creates a new instance of CwmTime, by predefined initial parameters.
	 * 
	 * <p>
	 * Predefined Parameters
	 * </p>
	 * <ul>
	 * <li><code>name</code> - Time</li>
	 * <li><code>visibility</code> - public</li>
	 * <li><code>notInstantiable</code> - true</li>
	 * </ul>
	 * 
	 * <p>
	 * Time defines a statement that will define the time of occurrence of an event.
	 * </p>
	 * 
	 * <p>
	 * The specific format of time expressions is not specified here and is subject to implementation considerations.
	 * </p>
	 * 
	 * <p>
	 * There is no default for the Time data type.
	 * </p>
	 * 
	 * @return A new instance of CwmTime, by predefined initial parameters.
	 * 
	 */
	abstract public CwmTime createTime();



	/**
	 * Factory method that creates a new instance of CwmUnlimitedInteger, by predefined initial parameters.
	 * 
	 * <p>
	 * Predefined Parameters
	 * </p>
	 * <ul>
	 * <li><code>name</code> - UnlimitedInteger</li>
	 * <li><code>visibility</code> - public</li>
	 * <li><code>notInstantiable</code> - true</li>
	 * </ul>
	 * 
	 * <p>
	 * UnlimitedInteger defines a data type whose range is the nonnegative integers augmented by the special value unlimited.
	 * </p>
	 * 
	 * <p>
	 * It is used for the upper bound of multiplicities.
	 * </p>
	 * 
	 * <p>
	 * The default for an UnlimitedInteger is the special value unlimited.
	 * </p>
	 * 
	 * @return A new instance of CwmUnlimitedInteger, by predefined initial parameters.
	 * 
	 */
	abstract public CwmUnlimitedInteger createUnlimitedInteger();



	/**
	 * Factory method that creates a new instance of Attribute by specific parameters.
	 * 
	 * @param name An identifier for the Attribute within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Attribute within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param changeability Specifies whether the value may be modified after the object is created. Must not be <code>null</code>.
	 * 
	 * @param type The new GClassifier describing the type of this Attribute. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Attribute, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code> 
	 * or <code>type</code> is <code>null</code>.
	 * 
	 */
	abstract public Attribute createAttribute( String name, Visibility visibility, Changeable changeability, DataType type );



	/**
	 * Factory method that creates a new instance of Attribute by specific parameters.
	 * 
	 * @param name An identifier for the Attribute within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Attribute within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param changeability Specifies whether the value may be modified after the object is created. Must not be <code>null</code>.
	 * 
	 * @param type The new GClassifier describing the type of this Attribute. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Attribute, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code>
	 * or <code>type</code> is <code>null</code>.
	 * 
	 */
	abstract public Attribute createAttribute( String name, Visibility visibility, Changeable changeability, CwmClass type );



	/**
	 * Factory method that creates a new instance of Attribute by specific parameters.
	 * 
	 * @param name An identifier for the Attribute within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Attribute within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param changeability Specifies whether the value may be modified after the object is created. Must not be <code>null</code>.
	 * 
	 * @param type The new GClassifier describing the type of this Attribute. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Attribute, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code>
	 * or <code>type</code> is <code>null</code>. 
	 * 
	 */
	abstract public Attribute createAttribute( String name, Visibility visibility, Changeable changeability, Interface type );



	/**
	 * Factory method that creates a new instance of BooleanExpression by specific parameters.
	 * 
	 * @param body The text of the expression expressed in the given language.  Must not be <code>null</code>.
	 * 
	 * @return A new instance of BooleanExpression, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if the parameter <code>body</code> was <code>null</code>.
	 * 
	 */
	abstract public BooleanExpression createBooleanExpression( String body );



	/**
	 * Factory method that creates a new instance of Constraint by specific parameters.
	 * 
	 * @param name An identifier for the Constraint within its containing  Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Constraint within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param body A BooleanExpression that must be true when evaluated for an instance of a system to be well formed. A boolean expression defining
	 * the constraint. Expressions are written as strings in a designated language. For the model to be well formed, the expression must always yield
	 * a true value when evaluated for instances of the constrained elements at any time when the system is stable; that is, not during the execution
	 * of an atomic operation. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Constraint, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>body</code>
	 * is <code>null</code>.
	 * 
	 */
	abstract public Constraint createConstraint( String name, Visibility visibility, BooleanExpression body );



	/**
	 * Factory method that creates a new instance of CwmClass by specific parameters.
	 * 
	 * @param name An identifier for the CwmClass within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmClass within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this CwmClass is abstract and can not be instantiated, <code>false</code> otherwise.
	 *
	 * @return A new instance of CwmClass by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 * 
	 */
	abstract public CwmClass createClass( String name, Visibility visibility, Boolean notInstantiable );



	/**
	 * Factory method that creates a new instance of CwmPackage by specific parameters.
	 * 
	 * @param name An identifier for the CwmPackage within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmPackage within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @return A new instance of CwmPackage, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	abstract public CwmPackage createPackage( String name, Visibility visibility );



	/**
	 * Factory method that creates a new instance of GDataType by specific parameters.
	 * 
	 * @param name An identifier for the GDataType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the GDataType within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param notInstantiable <code>true</code> if this GDataType is abstract and can not be instantiated, <code>false</code> otherwise. Must not
	 * be <code>null</code>.
	 * 
	 * @return A new instance of GDataType, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or
	 * <code>notInstantiable</code> is <code>null</code>.
	 * 
	 */
	abstract public DataType createDataType( String name, Visibility visibility, Boolean notInstantiable );



	/**
	 * Factory method that creates a new instance of Dependency by specific parameters.
	 * 
	 * @param name An identifier for the Dependency within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Dependency within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param client The element that is affected by the supplier element. In some cases the direction is unimportant and serves only to distinguish
	 * the two elements. Must not be <code>null</code>.
	 * 
	 * @param supplier Inverse of client. Designates the element that is unaffected by a change. In a two-way relationship this would be the more
	 * general element. In an undirected situation the choice of client and supplier may be irrelevant. Must not be <code>null</code>.
	 *    
	 * @return A new instance of Dependency, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters <code>name</code>, <code>visibility</code>, <code>client</code> or
	 * <code>supplier</code> was <code>null</code>.
	 * 
	 */
	abstract public Dependency createDependency(
		  String name
		, Visibility visibility
		, GModelElement<?, ?, ?, ?> client
		, GModelElement<?, ?, ?, ?> supplier
	);



	/**
	 * Factory method that creates a new instance of this Expression by specific parameters.
	 * 
	 * @param body The text of the expression expressed in the given language. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Expression, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>body</code> is <code>null</code>.
	 * 
	 */
	abstract public Expression createExpression( String body );



	/**
	 * Factory method that creates a new instance of Model by specific parameters.
	 * 
	 * @param name An identifier for the Model within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Model within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Model, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	abstract public Model createModel( String name, Visibility visibility );



	/**
	 * Factory method that creates a new instance of Multiplicity by specific parameters.
	 * 
	 * @param lower Specifies the positive integer lower bound of the range. Must not be <code>null</code>.
	 * 
	 * @param upper Specifies the upper bound of the range, which is a positive integer or the special value <i>unlimited</i> indicating no upper
	 * bound is defined. <i>unlimited</i> is represented by value <code>null</code>.
	 *  
	 * @return A new instance of Multiplicity, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if the parameter: <code>lower</code> is <code>null</code>.
	 * 
	 */
	abstract public Multiplicity createMultiplicity( Integer lower, Integer upper );



	/**
	 * Factory method that creates a new instance of ProcedureExpression by 
	 * specific parameters.
	 * 
	 * @param body The text of the expression expressed in the given language. Must not be <code>null</code>.
	 * 
	 * @return A new instance of ProcedureExpression, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if the parameter: <code>body</code> is <code>null</code>.
	 * 
	 */
	abstract public ProcedureExpression createProcedureExpression( String body );



	/**
	 * Factory method that creates a new instance of Stereotype by specific parameters.
	 * 
	 * @param name An identifier for the Stereotype within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Stereotype within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param baseClass The property <code>baseClass</code> specifies the name of a modeling element to which the stereotype applies, such as Class,
	 * Association, Constraint, etc. This is the name of a meta-class; that is, a class from the meta-model itself rather than a user model class. 
	 * Must not be <code>null</code>.
	 * 
	 * @return A new instance of Stereotype, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>baseClass</code>
	 * is <code>null</code>.  
	 * 
	 */
	abstract public Stereotype createStereotype( String name, Visibility visibility, Name baseClass );



	/**
	 * Factory method that creates a new instance of Stereotype by specific parameters.
	 * 
	 * @param name An identifier for the Stereotype within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Stereotype within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param baseClass The property <code>baseClass</code> specifies the name of a modeling element to which the stereotype applies, such as Class,
	 * Association, Constraint, etc. This is the name of a meta-class; that is, a class from the meta-model itself rather than a user model class.
	 * Must not be <code>null</code>.
	 * 
	 * @return A new instance of Stereotype, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>baseClass</code>
	 * is <code>null</code>.
	 * 
	 */
	abstract public Stereotype createStereotype( String name, Visibility visibility, String baseClass );



	/**
	 * Factory method that creates a new instance of Subsystem by specific parameters.
	 * 
	 * @param name An identifier for the Subsystem within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Subsystem within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this Subsystem is abstract and can not be instantiated, <code>false</code> otherwise. Must not be
	 * <code>null</code>.
	 * 
	 * @return A new instance of Subsystem, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 * 
	 */
	abstract public Subsystem createSubsystem(
		  String name
		, Visibility visibility
		, Boolean notInstantiable
	);



	/**
	 * Factory method that creates a new instance of TaggedValue by specific parameters.
	 * 
	 * @param tag An identifier for the TaggedValue within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param value The current value of the TaggedValue.  Must not be <code>null</code>.
	 * 
	 * @return A new instance of TaggedValue, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter:  <code>tag</code> or <code>value</code> is <code>null</code>.
	 * 
	 */
	abstract public TaggedValue createTaggedValue( String tag, String value );
}