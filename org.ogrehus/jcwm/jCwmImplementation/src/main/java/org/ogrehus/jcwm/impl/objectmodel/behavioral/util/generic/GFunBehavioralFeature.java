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

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GBehavioralFeature;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.references.behavioralparameter.BehavioralParameter;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunFeature;


/**
 * GFunBehavioralFeature is an abstract meta-class.
 * <p>
 * A behavioral feature refers to a dynamic feature of a model element, such as an operation or method. In the meta-model BehavioralFeature specifies
 * a behavioral aspect of a Classifier. All different kinds of behavioral aspects of a Classifier, such as Operation and Method, are subclasses of
 * BehavioralFeature.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GBehavioralFeature.
 * @param <PACKAGE> The type of the importer of this GBehavioralFeature.
 * @param <DEPENDENCY> The type of ClientDependency of this GBehavioralFeature.
 * @param <CONSTRAINT> The type of the Constraint under which this GBehavioralFeature is. 
 * @param <CLASSIFIER> The type of the Classifier that owns this GBehavioralFeature.
 * @param <PARAMETER> The caller must supply as a list of values compatible with the types of the Parameters
 *
 */
public abstract class GFunBehavioralFeature<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CLASSIFIER extends GClassifier< ?, ?, ?, ?, ?, ?>
	, PARAMETER  extends GParameter<?, ?, ?, ?>
>extends
	GFunFeature<NAMESPACE, PACKAGE, Dependency, Constraint, CLASSIFIER>
implements
	GBehavioralFeature<NAMESPACE, PACKAGE, CLASSIFIER, PARAMETER>
{


	/**
	 * Creates a new instance of FunBehavioralFeature by specific parameters.
	 * 
	 * @param name An identifier for the FunStructuralFeature within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunStructuralFeature within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param isQuery Specifies whether an execution of the FunStructuralFeature leaves the state of the system unchanged. <code>true</code> indicates
	 * that the state is unchanged; <code>false</code> indicates that side-effects may occur. Must not be <code>null</code>.
	 * 
	 * @param isQuery The changeability of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @param type The new Classifier describing the type of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code>
	 * or <code>type</code> was <code>null</code>.
	 * 
	 */
	protected GFunBehavioralFeature( String name, Visibility visibility, Boolean isQuery ) {
		super( name, visibility ); // may throw NullPointerException
		this.setQuery( isQuery );
		this.parameters = new TreeSet<PARAMETER>();
	}



//====================================================================================================================================================
// GFunBehavioralFeature capabilities
//====================================================================================================================================================



	/**
	 * Removes a parameter from this Behavioral instance.
	 * 
	 * <p>
	 * This method cancels the registration of this Behavioral from the removed parameter.
	 * </p>
	 * 
	 * @param parameter The parameter to be removed.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean removeParameter( BehavioralParameter<?> parameter ) {
		if ( parameter == null) {
			return Boolean.FALSE;
		}
		
		if ( this.parameters.remove( parameter ) ) {
			parameter.removeBehavioralFeature();
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	/**
	 * Reference: An ordered set of Parameters for the BehavioralFeature.
	 * <p>
	 * To call the BehavioralFeature, the caller must supply a list of values compatible with the types of the Parameters.
	 * </p>
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-4-1]</b> All Parameters should have a unique name.</li>
	 * <li><b>[C-4-2]</b> The type of the Parameters should be included in the namespace of the Classifier.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Parameter</li>
	 * <li><i>defined by</i>: BehavioralFeatureParameter::parameter</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Parameter::behavioralFeature</li>
	 * </ul>
	 * 
	 * @return A SortedSet of the parameters assigned to this behaviouralFeature, can't be <code>null</code> but an empty set.
	 * 
	 */
	public SortedSet<PARAMETER> getParameters() {
		return this.parameters;
	}



	public PARAMETER getParameter( String simpleName ) {
		if ( simpleName != null ) {
			for ( PARAMETER parameter : this.parameters ) {
				if ( simpleName.equals( parameter.getSimpleName() ) ) {
					return parameter;
				}
			}
		}
		return null;
	}



	protected <P extends PARAMETER> Boolean addParameterGeneric( P parameter ) {
		if ( parameter != null && this.parameters.add( parameter ) ) {
			invokeByReflection( parameter, "setBehavioralFeature", this );
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	/**
	 * Specifies whether an execution of the BehavioralFeature leaves the state of the system unchanged. 
	 * <ul>
	 * <li><code>true</code> indicates that the state is unchanged;</li> 
	 * <li><code>false</code> indicates that side-effects may occur.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the state of the system stays unchanged after execution, <code>false</code> otherwise.
	 *  
	 */
	public Boolean isQuery() {
		return this.isQuery;
	}



	/**
	 * Changes this behavioral BehavioralFeature to cause side-effects on the related system or not.
	 * <ul>
	 * <li><code>true</code> indicates that the state is unchanged;</li> 
	 * <li><code>false</code> indicates that side-effects may occur.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @see #isQuery() For more Information of the property <code>isQuery</code>.
	 * 
	 * @param isQuery <code>true</code> indicates that the state is unchanged; <code>false</code> indicates that side-effects may occur. Must not be
	 * <code>null</code>.
	 * 
	 * @return The old value of the property <code>isQuery</code>. May not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if the parameter <code>isQuery</code> was <code>null</code>.
	 * 
	 */
	public Boolean setQuery( Boolean isQuery ) {
		if ( isQuery == null ) {
			throw new NullPointerException( "Parameter: isQuery must not be null!" );
		}
		Boolean old = this.isQuery;
		this.isQuery = isQuery;

		return old;
	}



	/**
	 * The operation hasSameSignature checks if the argument has the same signature as the instance itself.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * hasSameSignature ( b : BehavioralFeature ) : Boolean;
	 * hasSameSignature (b) =
	 *     (self.name = b.name) 
	 *     and
	 *     (self.parameter->size = b.parameter->size) 
	 *     and
	 *     Sequence{ 1..(self.parameter->size) }
	 *         ->forAll( index : Integer | b.parameter->at(index).type = 
	 *             self.parameter->at(index).type 
	 *             and
	 *             b.parameter->at(index).kind = self.parameter->at(index).kind
	 *         )
	 * </pre>
	 * 
	 * @param behavioralFeature is the argument to be checked for same signature.
	 * 
	 * @return <code>true</code> if the OCL was accomplished by the <code>behavioralFeature</code>, <code>false</code> otherwise.
	 * 
	 */
	public Boolean hasSameSignature( GBehavioralFeature<?, ?, ?, ?> behavioralFeature ) {

		if ( behavioralFeature == null ) {
			return Boolean.FALSE;
		}

		if ( !this.getName().toString().equals( behavioralFeature.getName().toString() ) ) {
			return Boolean.FALSE;
		}

		if ( this.getParameters().size() != behavioralFeature.getParameters().size() ) {
			return Boolean.FALSE;
		}

		List<GParameter<?, ?, ?, ?>> others = null;
		others = new ArrayList<GParameter<?, ?, ?, ?>>( behavioralFeature.getParameters() );
		int index = 0;
		for ( GParameter<?, ?, ?, ?> parameter : this.parameters ) {
			if ( !(parameter.hasSameSignature( others.get( index++ ) )) ) {
				return Boolean.FALSE;
			}
		}

		return Boolean.TRUE;
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
		StringBuffer out = new StringBuffer( "[GFunBehavioralFeature" );
		if ( this.isQuery != null ) {
			out.append( " | isQuery=" + this.isQuery );
		}
		if ( !this.parameters.isEmpty() ) {
			out.append( " | ownedElements(" + this.parameters.size() + ")={" );
			boolean comma = false;
			for ( PARAMETER parameter : this.parameters ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( parameter.getSimpleName() );
				out.append( "<" + parameter.getClass().getSimpleName() + ">" );
			}
			out.append( "}" );
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
	 * Specifies whether an execution of the GFunBehavioralFeature leaves the state of the system unchanged. 
	 * <p>
	 * True indicates that the state is unchanged; false indicates that side-effects may occur.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Boolean isQuery = null;



	/**
	 * Reference: An ordered list of Parameters for the GFunBehavioralFeature.
	 * <p>
	 * To call the GFunBehavioralFeature, the caller must supply a list of values compatible with the types of the Parameters.
	 * </p>
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-4-1]</b> All Parameters should have a unique name.</li>
	 * <li><b>[C-4-2]</b> The type of the Parameters should be included in the Namespace of the Classifier.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: generic PARAMETER</li>
	 * <li><i>defined by</i>: BehavioralFeatureParameter::parameter</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Parameter::behavioralFeature</li>
	 * </ul>
	 * 
	 */
	protected SortedSet<PARAMETER> parameters = null;
}