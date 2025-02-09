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

import org.ogrehus.jcwm.api.objectmodel.behavioral.Argument;
import org.ogrehus.jcwm.api.objectmodel.behavioral.CallAction;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;

/**
 * FunArgument is an expression describing how to determine an actual value passed in a FunCallAction.
 * <p>
 * In the metamodel an FunArgument is a composite part of a FunCallAction and contains a meta-attribute, value, of type Expression. It states how the
 * actual argument is determined when the owning FunCallAction is executed.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunArgument
extends
	GFunModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	>
implements
	Argument
{


	/**
	 * Constructor creates a new instance of this Argument by specific parameters.
	 * 
	 * @param name An identifier related to the parameter of this Argument. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies the visibility of the argument related to its parameter. Must not be <code>null</code>.
	 *
	 * @param value An expression determining the actual Argument instance when executed. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Argument, by initial parameters.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>value</code>
	 * is <code>null</code>.
	 * 
	 */
	public FunArgument( String name, Visibility visibility, Expression value ) {
		super( name, visibility );
		this.setValue( value );
	}



//====================================================================================================================================================
// Argument capabilities
//====================================================================================================================================================



	/**
	 * Returns the value of the property <code>value</code>.
	 * <p>
	 * An expression determining the actual Argument instance when executed.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>: Expression</li> 
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return The expression determining the actual Argument instance when executed. Cannot be <code>null</code>.
	 * 
	 */
	public Expression getValue() {
		return this.value;
	}



	/**
	 * Sets the value of the property <code>value</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>: Expression</li> 
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 *
	 * @see #getValue() For more Information about the property 
	 * <code>value</code>. 
	 * 
	 * @param value The new value for the property <code>value</code>.
	 *    
	 * @return The expression determining the actual Argument instance when executed. Cannot be <code>null</code>.
	 * 
	 *  
	 */
	public Expression setValue( Expression value ) {
		Expression old = this.value;
		this.value = value;
		return old;
	}



	/**
	 * Returns the reference of <code>callAction</code>.
	 * <p>
	 * Identifies the FunCallAction that uses the Argument.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: FunCallAction</li>
	 * <li><i>defined by</i>: CallArguments::action</i></li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: FunCallAction::actualArgument</li>
	 * </ul>
	 * 
	 * @return The FunCallAction from the actualArgument defined by the FunCallAction. May be <code>null</code> if none was set before.
	 * 
	 */
	public CallAction getCallAction() {
		return this.callAction;
	}



	/**
	 * Sets the reference of <code>callAction</code>.
	 * <p>
	 * Identifies the FunCallAction that uses the Argument.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: FunCallAction</li>
	 * <li><i>defined by</i>: CallArguments::action</i></li>  
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: FunCallAction::actualArgument</li> 
	 * </ul>
	 * 
	 *  @see #getCallAction() For more Inforamtion of this reference.
	 *  
	 *  @param callAction The new value of this reference.  
	 *  
	 *  @return The FunCallAction from the actualArgument defined by the 
	 *  FunCallAction. May be <code>null</code> if none was set before.
	 * 
	 */
	public CallAction setCallAction( CallAction callAction ) {
		CallAction old = this.callAction;
		this.callAction = callAction;
		return old;
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
	 * Attribute: value.
	 * <p>
	 * An expression determining the actual FunArgument instance when executed.
	 * </p>
	 * <ul>
	 * <li><i>type</i>: Expression</li> 
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Expression value	= null;



	/**
	 * Reference: callAction
	 * <p>
	 * Identifies the FunCallAction that uses the FunArgument.
	 * </p>
	 * <ul>
	 * <li><i>class</i>: FunCallAction</li>
	 * <li><i>defined by</i>: CallArguments::action</i></li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: FunCallAction::actualArgument</li>
	 * </ul>
	 * 
	 */
	protected CallAction callAction = null;
}