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
package org.ogrehus.jcwm.impl.objectmodel.behavioral.util.generic;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GBehavioralFeature;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GEvent;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;


/**
 * Parameters are used in the specification of operations, methods, and events. 
 * <p>
 * A Parameter may include a name, type, and direction of communication.
 * </p>  
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GFunParameter.
 * @param <PACKAGE> The type of the importer of this GFunParameter.
 * @param <DEPENDENCY> The type of ClientDependency of this GFunParameter.
 * @param <CONSTRAINT> The type of the Constraint under which this GFunParameter is.
 * @param <EVENT> The type of the Event this GFunParameter acts in.
 * @param <PARAMETER> The type of Parameter that comprise the signature of the GFunParameter.
 * 
 */
public abstract class GFunParameter<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, EVENT      extends GEvent<?, ?, ?, ?, ?>
	, BEHAVIORAL extends GBehavioralFeature<?, ?, ?, ?>    
>extends
	GFunModelElement<NAMESPACE, PACKAGE, Dependency, Constraint>
implements
	GParameter<NAMESPACE, PACKAGE, EVENT, BEHAVIORAL>
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
	protected <T extends GClassifier<?, ?, ?, ?, ?, ?>> GFunParameter( 
		  String name
		, Visibility visibility
		, ParameterDirection kind
		, T type
	) {
		super( name, visibility ); // throws NullPointerException
		this.setKind( kind ); // throws NullPointerException
		this.setType( type ); // throws NullPointerException
	}



//====================================================================================================================================================
// GParameter capabilities
//====================================================================================================================================================



	public ParameterDirection getKind() {
		return this.kind;
	}



	public ParameterDirection setKind( ParameterDirection kind ) {
		if ( kind == null ) {
			throw new NullPointerException( "Parameter: kind must not be null!" );
		}
		
		ParameterDirection old = this.kind;
		this.kind = kind;

		return old; 
	}



	public GClassifier<?, ?, ?, ?, ?, ?> getType() {
		return this.type;
	}



	public <T extends GClassifier<?, ?, ?, ?, ?, ?>> GClassifier<?, ?, ?, ?, ?, ?> setType( T type ) {
		if ( type == null ) {
			throw new NullPointerException( "Parameter: type must not be null!" );
		}

		GClassifier<?, ?, ?, ?, ?, ?> old = this.type;
		this.type = type;

		return old;
	}



	public Expression getDefaultValue() {
		return this.defaultValue;
	}



	public Expression setDefaultValue( Expression defaultValue ) {
		Expression old = this.defaultValue;
		this.defaultValue = defaultValue;
		
		return old;
	}



	public EVENT getEvent() {
		return this.event;
	}



	public Boolean removeEvent() {
		if ( this.event == null ) {
			return Boolean.TRUE;
		}
		EVENT old = this.event;
		this.event = null;

		return old.removeParameter( this );
	}



	protected EVENT setEventGeneric( EVENT event ) {
		if ( event != null && event.equals( this.event ) ) {
			return event; // no changes, cause its the same namespace
		}
		EVENT old = this.event; // return value 
		if ( old != null  ) {
			old.removeParameter( this );
		}
		this.event = event;
		if ( this.event != null ) {
			if ( !this.event.getParameters().contains( this ) ) {
				// organize the bidirectional reference of new namespace by reflection
				//------------------------------------------------------------------------------------------------------------------------------------
				invokeByReflection( this.event , "addParameter", this );
			}
		}
		return old;
	}



//====================================================================================================================================================
// BehavioralParameter capabilities
//====================================================================================================================================================



	/**
	 * Reference: behavioralFeature
	 * <p>
	 * References the BehavioralFeature instance for which the Parameter instance describes a parameter.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: BehavioralFeature</li>
	 * <li><i>defined by</i>: BehavioralFeatureParameter::behavioralFeature</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: BehavioralFeature::parameter</li>
	 * </ul>
	 * 
	 * @return A BehavioralFeature for which this parameter is a parameter. Can be <code>null</code> if none is avail.
	 * 
	 */
	public BEHAVIORAL getBehavioralFeature() {
		return this.behavioralFeature;
	}



	protected BEHAVIORAL setBehavioralFeatureGeneric( BEHAVIORAL behavioralFeature ) {
		if ( behavioralFeature != null && behavioralFeature.equals( this.behavioralFeature ) ) {
			return behavioralFeature; // no changes, cause its the same behavioralFeature
		}
		BEHAVIORAL old = this.behavioralFeature; // return value 
		if ( old != null  ) {
			old.removeParameter( this );
		}
		this.behavioralFeature = behavioralFeature;
		if ( this.behavioralFeature != null ) {
			if ( !this.behavioralFeature.getParameters().contains( this ) ) {
				// organize the bidirectional reference of new behavioralFeature
				//------------------------------------------------------------------------------------------------------------------------------------
				invokeByReflection( this.behavioralFeature, "addParameter", this );
			}
		}
		return old;
	}



	/**
	 * Deregisters a behavioralFeature from this parameter.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: BehavioralFeature</li>
	 * <li><i>defined by</i>: BehavioralFeatureParameter::behavioralFeature</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: BehavioralFeature::parameter</li>
	 * </ul>
	 * 
	 * @return The old value of the property <code>behavioralFeature</code>. 
	 * 
	 */
	public Boolean removeBehavioralFeature() {
		if ( this.behavioralFeature == null ) {
			return Boolean.TRUE;
		}
		BEHAVIORAL old = this.behavioralFeature;
		this.behavioralFeature = null;

		return old.removeParameter( this );
	}



	public <T extends GParameter<?, ?, ?, ?>> Boolean hasSameSignature( T parameter ) {
		if ( parameter == null ) {
			return Boolean.FALSE;
		}
		return this.getType().equals( parameter.getType() ) && this.getKind().equals( parameter.getKind() );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunParameter" );
		if ( this.defaultValue != null ) {
			out.append( " | defaultValue=" + this.defaultValue.getBody() 
					  + "<" + this.defaultValue.getClass().getSimpleName() + ">" );
		}

		if ( this.kind != null ) {
			out.append( " | kind=" + this.kind );
		}

		if ( this.behavioralFeature != null ) {
			out.append( " | behavioralFeature=" + this.behavioralFeature.getSimpleName()
					  + "<" + this.behavioralFeature.getClass().getSimpleName() + ">" );
		}

		if ( this.event != null ) {
			out.append( " | event=" + this.event.getSimpleName()
					  + "<" + this.event.getClass().getSimpleName() + ">" );
		}
		
		if ( this.type != null ) {
			out.append( " | type=" + this.type.getSimpleName()
					  + "<" + this.type.getClass().getSimpleName() + ">" );
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
	 * Attribute: defaultValue
	 * <p>
	 * An Expression whose evaluation yields a value to be used when no argument is supplied for the Parameter.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: Expression</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 */
	protected Expression defaultValue = null;



	/**
	 * Attribute: kind
	 * <p>
	 * Specifies what kind of a Parameter is required.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>: ParameterDirectionKind</li>
	 * <li><i>multiplicity</i>: exactly one/li>
	 * </ul>
	 * 
	 */
	protected ParameterDirection kind = null;



	/**
	 * Reference: behavioralFeature
	 * <p>
	 * References the GFunBehavioralFeature instance for which the Parameter instance describes a parameter.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: generic BEHAVIORAL</li>
	 * <li><i>defined by</i>: BehavioralFeatureParameter::behavioralFeature</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: GFunBehavioralFeature::parameter</li>
	 * </ul>
	 * 
	 */
	protected BEHAVIORAL behavioralFeature = null;



	/**
	 * Reference: event
	 * <p>
	 * References the Event instance for which the Parameter instance describes a parameter.
	 * </p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: generic EVENT</li>
	 * <li><i>defined by</i>: EventParameter::event</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: Event::parameter</li>
	 * </ul>
	 * 
	 */
	protected EVENT event = null;



	/**
	 * Reference type
	 * <p>
	 * Designates a Classifier to which an argument value must conform.
	 * </p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Classifier</li>
	 * <li><i>defined by</i>: ParameterType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 */
	protected GClassifier<?, ?, ?, ?, ?, ?> type = null;
}