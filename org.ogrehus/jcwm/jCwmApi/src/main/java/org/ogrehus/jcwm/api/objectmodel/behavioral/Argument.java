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

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;

/**
 * Argument is an expression describing how to determine an actual value passed in a CallAction.
 * <p>
 * In the meta-model an Argument is a composite part of a CallAction and contains a meta-attribute, value, of type Expression. It states how the 
 * actual argument is determined when the owning CallAction is executed.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Argument
extends
	GModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	>
{


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
	Expression getValue();



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
	 * @see #getValue() For more Information about the property <code>value</code>.
	 * 
	 * @param value The new value for the property <code>value</code>.
	 * 
	 * @return The expression determining the actual Argument instance when executed. Cannot be <code>null</code>.
	 * 
	 */
	Expression setValue( Expression value );



	/**
	 * Returns the reference of <code>callAction</code>.
	 * <p>
	 * Identifies the CallAction that uses the Argument.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: CallAction</li>
	 * <li><i>defined by</i>: CallArguments::action</i></li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: CallAction::actualArgument</li> 
	 * </ul>
	 * 
	 * @return The CallAction from the actualArgument defined by the CallAction. May be <code>null</code> if none was set before.
	 * 
	 */
	CallAction getCallAction();



	/**
	 * Sets the reference of <code>callAction</code>.
	 * <p>
	 * Identifies the CallAction that uses the Argument.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: CallAction</li>
	 * <li><i>defined by</i>: CallArguments::action</i></li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * <li><i>inverse</i>: CallAction::actualArgument</li>
	 * </ul>
	 * 
	 * @param callAction The new value of this reference.
	 * 
	 * @return The CallAction from the actualArgument defined by the CallAction. May be <code>null</code> if none was set before.
	 * 
	 */
	CallAction setCallAction( CallAction callAction );



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains this Argument.
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a
	 * kind of virtual namespace).
	 * </p>
	 * <p>
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: GNamespace</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Namespace::ownedElement</li>
	 * </ul>
	 * 
	 * @param namespace The new namespace that contains this ModelElement.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */	
	GNamespace< ?, ?, ?, ?, ?> setNamespace( GNamespace< ?, ?, ?, ?, ?> namespace );



	/**
	 * Adds an importer (Package) to this Argument, that will contain this Argument as imported.
	 * 
	 * @param importer The specific package, that will contain this Argument.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer );



	/**
	 * Adds a dependency to this Argument.
	 * 
	 * @param dependency A Dependency in witch this Argument is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this Argument.
	 * 
	 * @param constraint A Constrain that must be satisfied by this Argument.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );
}