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

import java.util.SortedSet;

import org.ogrehus.jcwm.api.objectmodel.behavioral.Argument;
import org.ogrehus.jcwm.api.objectmodel.behavioral.CallAction;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Parameter;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.api.objectmodel.core.util.ConstraintViolation;
import org.ogrehus.jcwm.api.objectmodel.core.util.CwmConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;

/**
 * A call action is an action resulting in an invocation of an operation.
 * <p>
 * The purpose of a FunCallAction is to identify the actual Arguments used in a specific invocation of an Operation.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunCallAction
extends
	GFunModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	>
implements
	CallAction
{


	/**
	 * Constructor creates a new instance of this CallAction by specific parameters.
	 * 
	 * @param name An identifier for the CallAction within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CallAction within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param operation The Operation that will be invoked when the CallAction is executed. Must not be <code>null</code>.
	 * 
	 * @return A new instance of CallAction, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>operation</code>
	 * is <code>null</code>.
	 * 
	 */
	public FunCallAction( String name, Visibility visibility, Operation operation ) {
		super( name, visibility ); // throws NullPointerException
		this.setOperation( operation ); // throws NullPointerException
	}



//====================================================================================================================================================
// CallAction capabilities
//====================================================================================================================================================



	/**
	 * The Operation that will be invoked when the CallAction is executed.
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
	public Operation getOperation() {
		return this.operation;
	}



	/**
	 * Sets the value of reference operation.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Operation</li>
	 * <li><i>defined by</i>: CalledOperation::operation</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param operation The new operation, that will be invoked when this CallAction is executed.
	 * 
	 * @return The old operation, assigned before.
	 * 
	 */
	public Operation setOperation( Operation operation ) {
		if ( operation == null ) {
			throw new NullPointerException( "Parameter: operation must not be null!" );
		}
		Operation old = this.operation;
		this.operation = operation;

		return old;
	}



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
	 * 
	 */
	public SortedSet<Argument> getActualArguments() {
		return this.actualArguments;
	}



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
	public Boolean addActualArgument( Expression actualArgument ) {
		if ( this.operation.getParameters().size() == this.actualArguments.size() ) {
			throw new ConstraintViolation( CwmConstraint.C_4_3 );
		}
		Parameter[] parameters = new Parameter[] {};  
		this.operation.getParameters().toArray( parameters );
		Parameter param    = parameters[ this.actualArguments.size() ];
		Argument  argument = new FunArgument( param.getName().toString(), param.getVisibility(), actualArgument );
		
		return this.actualArguments.add( argument  );
	}



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
	public Boolean removeActualArgument( Argument actualArgument ) {
		return this.actualArguments.remove( actualArgument );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public GNamespace<?, ?, ?, ?, ?> setNamespace( GNamespace<?, ?, ?, ?, ?> namespace ) {
		return super.setNamespaceGeneric( namespace );
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



	public Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer ) {
		return super.addImporterGeneric( importer );
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * The Operation that will be invoked when the FunCallAction is executed.
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
	 */
	protected Operation operation = null;



	/**
	 * The FunArgument(s) supplied to the FunCallAction.
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
	 * <li><i>class</i>: FunArgument</li>
	 * <li><i>defined by</i>: CallArguments::actualArgument</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: FunArgument::callAction</li>
	 * </ul>
	 * 
	 */
	protected SortedSet<Argument> actualArguments = null;
}