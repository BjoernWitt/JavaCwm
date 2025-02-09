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
package org.ogrehus.jcwm.api.objectmodel.behavioral;

import java.util.SortedSet;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;


/**
 * A call action is an action resulting in an invocation of an operation.
 * <p>
 * The purpose of a CallAction is to identify the actual Arguments used in a specific invocation of an Operation.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface CallAction
extends
	GModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
    > 
{


	/**
	 * The Operation that will be invoked when the CallAction is executed.
	 * <p>
	 * <b>Constraints</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C-4-3]</b> The number of arguments must be the same as the number of the Operation.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Operation</li>
	 * <li><i>defined by</i>: CalledOperation::operation</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return The invoked operation, when CallAction is executed.
	 * 
	 */
	Operation getOperation();



	/**
	 * Sets the value of reference operation.
	 * <p>
	 * <b>Constraints</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C-4-3]</b> The number of arguments must be the same as the number of the Operation.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Operation</li>
	 * <li><i>defined by</i>: CalledOperation::operation</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @see #getOperation() For description of the reference <code>operation</code>.
	 * 
	 * @param operation The new operation, that will be invoked when this CallAction is executed.
	 * 
	 * @return The old operation, assigned before.
	 * 
	 */
	Operation setOperation( Operation operation );



	/**
	 * The Argument(s) supplied to the CallAction.
	 * <p> 
	 * <b>Constraints</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C-4-3]</b> The number of arguments must be the same as the number of the Operation.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Argument</li>
	 * <li><i>defined by</i>: CallArguments::actualArgument</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Argument::callAction</li>
	 * </ul>
	 * 
	 * @return The arguments supplied to this CallAction.
	 */
	SortedSet<Argument> getActualArguments();



	/**
	 * Adds an Argument that is supplied to the CallAction.
	 * <p> 
	 * <b>Constraints</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C-4-3]</b> The number of arguments must be the same as the number of the Operation.</li>
	 * </ul>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Argument</li>
	 * <li><i>defined by</i>: CallArguments::actualArgument</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Argument::callAction</li>
	 * </ul>
	 * 
	 * @param actualArgument The new argument(s) supplied to this CallAction. The arguments must fit to the constraint [C-4-3].
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addActualArgument( Expression actualArgument );



	/**
	 * Removes an Argument that is supplied to the CallAction.
	 * <p> 
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Argument</li>
	 * <li><i>defined by</i>: CallArguments::actualArgument</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Argument::callAction</li>  
	 * </ul>
     * 
     * @param actualArgument The argument to be removed from this CallAction.
     * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeActualArgument( Argument actualArgument );
}