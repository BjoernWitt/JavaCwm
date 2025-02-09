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

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.behavioral.CwmMethod;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Parameter;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GCwmMethod;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.resource.relational.Procedure;

import org.ogrehus.jcwm.impl.objectmodel.behavioral.util.generic.GFunBehavioralFeature;

/**
 * Operation is a service that can be requested from an object to effect behavior. 
 * <p>
 * An Operation has a signature, which describes the parameters that are possible (including possible return values).
 * </p>
 * <p>
 * In the metamodel, an Operation is a GFunBehavioralFeature that can be applied to instances of the Classifier that contains the Operation.
 * </p>
 * <p>
 * Operation is the specification, while Method is the implementation.
 * </p>   
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunOperation
extends
	GFunBehavioralFeature<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, GClassifier<?, ?, ?, ?, ?, ?>
		, GParameter<?, ?, ?, ?>
> implements
		Operation
{



	/**
	 * Creates a new instance of Operation by specific parameters.
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
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, <code>isQuery</code>
	 * or <code>isAbstract</code> is <code>null</code>.
	 *  
	 */
	public FunOperation( String name, Visibility visibility, Boolean isQuery, Boolean isAbstract ) {
		super( name, visibility, isQuery );
		this.setAbstract( isAbstract );
		this.methods = new HashSet<GCwmMethod<?, ?, ?, ?>>();
	}



//====================================================================================================================================================
// Operation capabilities
//====================================================================================================================================================



	/**
	 * Attribute: isAbstract
	 * <p>
	 * If true, then the Operation does not have an implementation, and one must be supplied by a descendant.
	 * </p>
	 * <p> 
	 * If false, the Operation must have an implementation in the class or inherited from an ancestor.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the operation doesn't have an implementation, and one must be supplied by a descendant, <code>false</code> means
	 * the implementation is in the class or was inherited from an ancestor.
	 * 
	 */
	public Boolean isAbstract() {
		return this.isAbstract;
	}



	/**
	 * 	Sets the value of the property <code>isAbstract</code>. 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 *
	 * @see #isAbstract() for description on the property <code>isAbstract</code>.
	 * 
	 * @param isAbstract The new Value of the property <code>isAbstract</code>. Must not be <code>null</code>.
	 * 
	 * @return The value set before. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if the parameter: <code>isAbstract</code> is <code>null</code>.
	 * 
	 */
	public Boolean setAbstract( Boolean isAbstract ) {
		if ( isAbstract == null ) {
			throw new NullPointerException( "The Parameter: isAbstract must not be null!" );
		}
		Boolean old = this.isAbstract;
		this.isAbstract = isAbstract;
		return old;
	}



	/**
	 * References the set of CwmMethod instances defined for the Operation.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: CwmMethod</li>
	 * <li><i>defined by</i>: OperationMethod::method</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * <li><i>inverse</i>: CwmMethod::specification</li>
	 * </ul>
	 * 
	 * @return Set of methods defined for this operations.
	 * 
	 */
	public Set<GCwmMethod<?, ?, ?, ?>> getMethods() {
		return this.methods;
	}



	public Boolean addMethod( CwmMethod method ) {
		return addMethodGeneric( method );
	}



	public Boolean addMethod( Procedure method ) {
		return addMethodGeneric( method );
	}



	protected Boolean addMethodGeneric( GCwmMethod<?, ?, ?, ?> method ) {
		if ( method != null && this.methods.add( method ) ) {
			method.setSpecification( this );
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeMethod( GCwmMethod<?, ?, ?, ?> method ) {
		if ( this.methods.remove( method ) ) {
			method.removeSpecification(); // remove bi-directional reference
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



//====================================================================================================================================================
// GBehavioral capabilities
//====================================================================================================================================================



	public Boolean addParameter( Parameter parameter ) {
		return super.addParameterGeneric( parameter );
	}



	public <T extends GClassifier<?, ?, ?, ?, ?, ?>> Boolean addParameter( String name, ParameterDirection kind, T type ) {
		return addParameter( name, Visibility._public, kind, type );
	}



	public <T extends GClassifier<?, ?, ?, ?, ?, ?>> Boolean addParameter( 
		  String name
		, Visibility visibility
		, ParameterDirection kind
		, T type
	) {
		Parameter parameter = new FunParameter( name, visibility, kind, type );
		
		return addParameter( parameter );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public GNamespace<?, ?, ?, ?, ?> setNamespace( GNamespace<?, ?, ?, ?, ?> namespace ) {
		return super.setNamespaceGeneric( namespace ); 
	}



	public Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer ) {
		return super.addImporterGeneric( importer );		
	}



//====================================================================================================================================================
// GFeature capabilities
//====================================================================================================================================================



	public GClassifier<?, ?, ?, ?, ?, ?> setOwner( GClassifier<?, ?, ?, ?, ?, ?> owner ) {
		return super.setOwnerGeneric( owner );
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Attribute: isAbstract
	 * <p>
	 * If true, then the Operation does not have an implementation, and one must be supplied by a descendant.
	 * </p>
	 * <p> 
	 * If false, the Operation must have an implementation in the class or inherited from an ancestor.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: Boolean</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Boolean isAbstract = null;



	/**
	 * References the set of Method instances defined for the Operation.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Method</li>
	 * <li><i>defined by</i>: OperationMethod::method</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * <li><i>inverse</i>: Method::specification</li>
	 * </ul>
	 * 
	 */
	protected Set<GCwmMethod<?, ?, ?, ?>> methods = null;
}