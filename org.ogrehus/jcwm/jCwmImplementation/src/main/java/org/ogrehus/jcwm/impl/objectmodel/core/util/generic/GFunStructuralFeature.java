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

import org.ogrehus.jcwm.api.objectmodel.core.Multiplicity;
import org.ogrehus.jcwm.api.objectmodel.core.util.Changeable;
import org.ogrehus.jcwm.api.objectmodel.core.util.Ordering;
import org.ogrehus.jcwm.api.objectmodel.core.util.Scope;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;

/**
 * A feature is an abstract property, like attribute or operation that is encapsulated within a Classifier.
 * 
 * <p>
 * In the meta-model a Feature declares a structural or behavioral characteristic of an instance of a Classifier or of the Classifier itself. Feature
 * is an abstract meta-class.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, August 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GFunStructuralFeature.
 * @param <PACKAGE> The type of the importer of this GFunStructuralFeature.
 * @param <DEPENDENCY> The type of ClientDependency of this GFunStructuralFeature.
 * @param <CONSTRAINT> The type of the Constraint under which this GFunStructuralFeature is. 
 * @param <CLASSIFIER> The type of the Classifier that owns this GFunStructuralFeature. 
 * @param <TYPE> The type of a GFunStructuralFeature. 
 * 
 */
public abstract class GFunStructuralFeature<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, CLASSIFIER extends GClassifier< ?, ?, ?, ?, ?, ?>
	, TYPE       extends GClassifier< ?, ?, ?, ?, ?, ?>
>extends
	GFunFeature<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, CLASSIFIER>
implements
	GStructuralFeature<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, CLASSIFIER, TYPE> 
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
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code>
	 * or <code>type</code> was <code>null</code>.
	 * 
	 */
	protected GFunStructuralFeature( String name, Visibility visibility, Changeable changeability,  TYPE type ) {
		super( name, visibility ); // may throw NullPointerException
		setChangeability( changeability ); // may throw NullPointerException
		setTypeGeneric( type ); // may throw NullPointerException
	}	



//====================================================================================================================================================
// StructuralFeature capabilities
//====================================================================================================================================================



	protected TYPE setTypeGeneric( TYPE type ) {
		if ( type == null ) {
			throw new NullPointerException( "Parameter: type must not be null." );
		}
		TYPE old  = this.type;
		this.type = type;

		return old;
	}



	public TYPE getType() {
		return this.type;
	}	



	public Changeable setChangeability( Changeable changeability ) {
		if ( changeability == null ) {
			throw new NullPointerException( "Parameter: changeability must not be null." );
		}		
		Changeable old  = this.changeability;
		this.changeability = changeability;

		return old;
	}


	public Changeable getChangeability() {
		return this.changeability;
	}



	public Multiplicity setMultiplicity( Multiplicity multiplicity ) {
		Multiplicity old  = this.multiplicity;
		this.multiplicity = multiplicity;

		return old;
	}



	public Multiplicity getMultiplicity() {
		return this.multiplicity;
	}	



	public Ordering setOrdering( Ordering ordering ) {
		Ordering old  = this.ordering;
		this.ordering = ordering;

		return old;
	}



	public Ordering getOrdering() {
		return this.ordering;
	}



	public Scope setTargetScope( Scope targetScope ) {
		Scope old  = this.targetScope;
		this.targetScope = targetScope;

		return old;
	}



	public Scope getTargetScope() {
		return this.targetScope;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunStructuralFeature" );
		if ( this.changeability != null ) {
			out.append( " | changeability=" + this.changeability );
		}
		if ( this.multiplicity != null ) {
			out.append( " | multiplicity=" + this.multiplicity.getRanges() );
		}
		if ( this.ordering != null ) {
			out.append( " | ordering=" + this.ordering );
		}
		if ( this.targetScope != null ) {
			out.append( " | targetScope=" + this.targetScope );
		}
		if ( this.type != null ) {
			out.append( " | type=" + this.type.getQualifiedName() );
			out.append( "<" + this.type.getClass().getSimpleName() + ">" );
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
	 * Attribute: changeability
	 * 
	 * Specifies whether the value may be modified after the object is created.
	 * 
	 * type			: ChangeabilityKind
	 * multiplicity	: exactly one
	 * 
	 */
	protected Changeable changeability = null;
	
	
	/**
	 * Attribute: multiplicity
	 * 
	 * The possible number of data values for the feature that may be held by an instance. The cardinality of the set of values is an implicit part of
	 * the feature. In the common case in which the multiplicity is 1..1, then the feature is a scalar; that is, it holds exactly one value.
	 * <ul>
	 * <li><i>type</i>			: Multiplicity</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * </ul>
	 */
	protected Multiplicity multiplicity	= null;



	/**
	 * Attribute: 
	 * 
	 * Specifies whether the set of instances is ordered. The ordering must be determined and maintained by Operations that add values to the feature.
	 * This property is only relevant if the multiplicity is greater than one.
	 * <ul>
	 * <li><i>type</i>			: OrderingKind
	 * <li><i>multiplicity</i>	: zero or one
	 * </ul>
	 */
	protected Ordering ordering = null;



	/**
	 * Attribute: targetScope
	 * 
	 * Specifies whether the targets are ordinary Instances or are Classifiers.
	 * 
	 * <ul>
	 * <li><i>type</i>: ScopeKind</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 */
	protected Scope targetScope = null;



	/**
	 * Reference: 
	 * 
	 * Designates the Classifier whose instances are values of the feature. It must be a Class, DataType, or Interface.
	 * 
	 * <ul>
	 * <li><i>class</i>: generic TYPE</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 */
	protected TYPE type = null;
}