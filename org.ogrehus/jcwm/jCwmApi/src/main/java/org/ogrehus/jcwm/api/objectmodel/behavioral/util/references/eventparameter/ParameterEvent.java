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
package org.ogrehus.jcwm.api.objectmodel.behavioral.util.references.eventparameter;

import java.util.SortedSet;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;

public interface ParameterEvent<PARAMETER extends EventParameter<?>>
extends
	ModelElement
{


	/**
	 * Removes a Parameter from the set of ordered Parameter instances.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Parameter</li>
	 * <li><i>defined by</i>: EventParameter::parameter</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Parameter::event</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeParameter( GParameter<?, ?, ?, ?> parameter );



	/**
	 * Returns the set of ordered Parameter instances that comprise the signature of this Event.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Parameter</li>
	 * <li><i>defined by</i>: EventParameter::parameter</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Parameter::event</li>   
	 * </ul>
	 * 
	 *  @return A Sorted Set of parameters comprising the signature of this event.
	 * 
	 */
	SortedSet<PARAMETER> getParameters();
}