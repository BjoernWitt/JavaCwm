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

import java.util.SortedSet;
import java.util.TreeSet;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GEvent;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;


/**
 * Event is a specification of an observable occurrence. 
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
public /*abstract*/ class GFunEvent<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, PARAMETER  extends GParameter<?, ?, ?, ?>
> extends
	GFunModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT>
implements
	GEvent<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, PARAMETER>
{


	/**
	 * Creates a new instance of this Event by specific parameters.
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
	protected GFunEvent( String name, Visibility visibility ){
		super( name, visibility ); // may throw NullPointerException
		this.parameters = new TreeSet<PARAMETER>();
	}



//======================================================================================================================
// GFunEvent capabilities
//======================================================================================================================



	public Boolean removeParameter( GParameter<?, ?, ?, ?> parameter ) {
		if ( this.parameters.remove( parameter ) ) {
			parameter.removeEvent(); // remove bi-directional reference
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}



	public SortedSet<PARAMETER> getParameters() {
		return this.parameters;
	}



	protected <P extends PARAMETER> Boolean addParameterGeneric( P parameter ) {
		if ( parameter != null && this.parameters.add( parameter ) ) {
			invokeByReflection( parameter, "setEvent", this ); // bi-directional reference
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



//======================================================================================================================
// Properties
//======================================================================================================================



	/**
	 * References the set of ordered Parameter instances that comprise the signature of the Event.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: generic PARAMETER</li>
	 * <li><i>defined by</i>: EventParameter::parameter</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Parameter::event</li>   
	 * </ul>
	 * 
	 */
	protected SortedSet<PARAMETER> parameters = null;
}