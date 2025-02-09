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

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.dependencyclient.ClientDependency;

/**
 * A dependency states that the implementation or functioning of one or more elements requires the presence of one or more other elements.
 * <p>
 * In the meta-model a Dependency is a directed relationship from a client (or clients) to a supplier (or suppliers) stating that the client is
 * dependent on the supplier; that is, the client element requires the presence and knowledge of the supplier element.
 * </p>
 * <p>
 * A dependency specifies that the semantics of a set of model elements requires the presence of another set of model elements. This implies that if
 * the source is somehow modified, the dependents probably must be modified. The reason for the dependency can be specified in several different ways
 * (for example, using natural language or an algorithm) but is often implicit.
 * </p>
 * <p>
 * Whenever the supplier element of a dependency changes, the client element is potentially invalidated. After such invalidation, a check should be
 * performed followed by possible changes to the derived client element. Such a check should be performed after which action can be taken to change
 * the derived element to validate it again.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this Dependency.
 * @param <PACKAGE> The type of the importer of this Dependency.
 * @param <DEPENDENCY> The type of ClientDependency of this Dependency.
 * @param <CONSTRAINT> The type of the Constraint under which this Dependency is. 
 * @param <CLIENT> Type of the client under this Dependency
 * 
 */
public abstract class GFunDependency<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, CLIENT     extends GModelElement<?, ?, ?, ?>
>extends
	GFunModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT>
implements
	GDependency<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, CLIENT>
{


	/**
	 * Creates a new instance of Dependency by specific parameters.
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
	 * @throws NullPointerException Is thrown if one of the parameters <code>name</code>, <code>visibility</code>, <code>client</code> or
	 * <code>supplier</code> was <code>null</code>.
	 * 
	 */
	public GFunDependency( String name, Visibility visibility, CLIENT client, ModelElement supplier ) {
		super( name, visibility );
		this.clients = new HashSet<CLIENT>();
		this.clients.add( client );
		this.suppliers = new HashSet<ModelElement>();
		this.suppliers.add( supplier );
	}



	public String getKind() {
		return this.kind;
	}



	public String setKind( String kind ) {
		String old = this.kind;
		this.kind  = kind; 
		return old;
	}



//====================================================================================================================================================
//	Clientable<CLIENT>.java
//====================================================================================================================================================



	public Set<CLIENT> getClients() {
		return this.clients;
	}



	public Set<ModelElement> getSupplier() {
		return this.suppliers;
	}



	public Boolean addSupplier( ModelElement supplier ) {
		return this.suppliers.add( supplier );
	}



	public Boolean removeClient( ClientDependency<?> client ) {
		if ( client == null ) {
			return Boolean.TRUE;
		}
		
		if ( this.clients.remove( client ) ) {
			client.removeDependency( this );
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeSupplier( ModelElement supplier ) {
		if ( supplier == null ) {
			return Boolean.TRUE;
		}

		return this.suppliers.remove( supplier );
	}



//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * Attribute: kind
	 * 
	 * Contains a description of the nature of the dependency relationship between the client and supplier. The list of possible values is open-ended.
	 * However, CWM predefines the values Abstraction and Usage.
	 * 
	 * <ul>
	 * <li><i>type</i>			: String</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * </ul>
	 * 
	 */
	protected String kind = null;



	/**
	 * Reference: client
	 * 
	 * The element that is affected by the supplier element. In some cases the direction is unimportant and serves only to distinguish the two
	 * elements.
	 * 
	 * <ul>
	 * <li><i>class</i>			: generic CLIENT</li>
	 * <li><i>defined by</i>	: DependencyClient::client</li>
	 * <li><i>multiplicity</i>	: one or more</li>
	 * <li><i>inverse</i>		: ModelElement::clientDependency</li>
	 * </ul>
	 * 
	 */
	protected Set<CLIENT> clients	= null;



	/**
	 * Reference: supplier
	 * 
	 * Inverse of client. Designates the element that is unaffected by a change. In a two-way relationship this would be the more general element. In
	 * an undirected situation the choice of client and supplier may be irrelevant.
	 * 
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: DependencySupplier::supplier</li>
	 * <li><i>multiplicity</i>	: one or more</li>
	 * <li><i>inverse</i>		: ModelElement::supplierDependency</li>
	 * </ul>
	 */
	protected Set<ModelElement>	suppliers	= null;
}