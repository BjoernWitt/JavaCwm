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

import org.ogrehus.jcwm.api.objectmodel.core.util.Scope;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


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
 * @param <NAMESPACE> The type of the Namespace that contains this GFunFeature.
 * @param <PACKAGE> The type of the importer of this GFunFeature.
 * @param <DEPENDENCY> The type of ClientDependency of this GFunFeature.
 * @param <CONSTRAINT> The type of the Constraint under which this GFunFeature is. 
 * @param <CLASSIFIER> The type of the Classifier that owns this GFunFeature. 
 *
 */
public abstract class GFunFeature<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, CLASSIFIER extends GClassifier< ?, ?, ?, ?, ?, ?>
>extends
	GFunModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT>
implements
	GFeature<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, CLASSIFIER> 
{


	/**
	 * Creates a new instance of GFunFeature by specific parameters.
	 * 
	 * @param name An identifier for the GFunFeature within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the GFunFeature within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code> or <code>visibility</code> was <code>null</code>.
	 * 
	 */
	protected GFunFeature( String name, Visibility visibility ) {
		super( name, visibility );
	}



	public Boolean removeOwner() {
		if ( this.owner == null ) {
			return Boolean.TRUE;
		}
		CLASSIFIER old = this.owner;
		this.owner = null;

		return old.removeFeature( this );
	}



	protected CLASSIFIER setOwnerGeneric( CLASSIFIER owner ) {
		if ( owner != null && owner.equals( this.owner ) ) {
			return owner; // no changes, cause its the same namespace
		}
		CLASSIFIER  old = this.owner; // return value 
		if ( old != null  ) {
			old.removeFeature( this );
		}
		this.owner = owner;
		if ( this.owner != null ) {
			if ( !this.owner.getFeatures().contains( this ) ) {
				// organize the bidirectional reference of new owner by reflection
				//------------------------------------------------------------------------------------------------------
				invokeByReflection( this.owner, "addFeature", this );
			}
		}

		return old;
	}



	public CLASSIFIER getOwner() {
		return this.owner;
	}



	public Scope getOwnerScope() {
		return this.ownerScope;
	}



	public Scope setOwnerScope( Scope ownerScope ) {
		Scope old = this.ownerScope;
		this.ownerScope = ownerScope;
		return old;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunFeature" );
		if ( this.ownerScope != null ) {
			out.append( " | ownerScope=" + this.ownerScope );
		}

		if ( this.owner != null ) {
			out.append( " | owner=" + this.owner.getQualifiedName() );
			out.append( "<" + owner.getClass().getSimpleName() + ">" );
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
	 * Attribute: ownerScope
	 * 
	 * Specifies whether the Feature appears in every instance of the Classifier or whether it appears only once for the entire Classifier.
	 * <ul>
	 * <li><i>type</i>			: ScopeKind</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * </ul>
	 * 
	 */
	protected Scope ownerScope = null;



	/**
	 * Reference: owner
	 * 
	 * The Classifier declaring the Feature.
	 * 
	 * <ul>
	 * <li><i>class</i>		    : generic CLASSIFIER</li>
	 * <li><i>defined by</i>	: ClassifierFeature::owner</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Classifier::feature</li>
	 * </ul>
	 * 
	 */
	protected CLASSIFIER owner = null;
}