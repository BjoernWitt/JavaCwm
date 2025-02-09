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

import org.ogrehus.jcwm.api.objectmodel.behavioral.Event;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Parameter;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.api.resource.relational.SQLParameter;

import org.ogrehus.jcwm.impl.objectmodel.behavioral.util.generic.GFunEvent;


/**
 * FunEvent is a specification of an observable occurrence. 
 * <p>
 * The occurrence that generates an event instance is assumed to take place at an instant in time.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunEvent
extends
	GFunEvent<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, GParameter<?, ?, ?, ?>
> implements
	Event
{


	/**
	 * Creates a new instance of this Event by specific parameters.
	 * 
	 * @param name An identifier for the Event within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Event within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	public FunEvent( String name, Visibility visibility ){
		super( name, visibility ); // may throw NullPointerException
	}



//====================================================================================================================================================
// FunEvent capabilities
//====================================================================================================================================================



	public Boolean addParameter( Parameter parameter ) {
		return super.addParameterGeneric( parameter );
	}



	public Boolean addParameter( SQLParameter parameter ) {
		return super.addParameterGeneric( parameter );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the CwmClass (Namespace) that contains this FunEvent. 
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a
	 * kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>  
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: CwmClass</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: CwmClass::ownedElement</li>
	 * </ul>
	 * 
	 * @param cwmClass The new namespace that contains this ModelElement.
	 * 
	 * @return The CwmClass that contains this Interface before, or <code>null</code> none was assigned before. 
	 * 
	 */
	public GNamespace<?, ?, ?, ?, ?> setNamespace( GNamespace<?, ?, ?, ?, ?> namespace ) {
		return super.setNamespaceGeneric( namespace );
	}



	/**
	 * Adds an importer (Package) to this FunEvent, that will contain this Interface as imported.
	 * 
	 * @param importer The specific package, that will contain this FunEvent.
	 * 
	 * @return <code>true</code> if this Set changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer ) {
		return super.addImporterGeneric( importer );
	}



	/**
	 * Adds a dependency to this FunEvent.
	 * 
	 * @param dependency A Dependency in witch this FunEvent is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	/**
	 * Adds a constraint to this FunEvent.
	 * 
	 * @param constraint A Constrain that must be satisfied by this FunEvent.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}
}