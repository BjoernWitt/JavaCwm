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
package org.ogrehus.jcwm.api.resource.relational;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.resource.relational.util.Deferability;


/**
 * A rule that specifies the values allowed in one or more columns of every row of a table.
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface CheckConstraint
extends
	GConstraint<
		  Schema
	  	, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	  	, Dependency
	  	, Constraint
		, GModelElement<?, ?, ?, ?>
	>
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Adds a dependency to this Constraint.
	 * 
	 * @param dependency A Dependency in witch this Constraint is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDependency( Dependency dependency );	



	/**
	 * Adds an importer (Package) to this Constraint, that will contain this Constraint as imported.
	 * 
	 * @param importer The specific package, that will contain this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer );



	/**
	 * Adds another Constraint to this Constraint.
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C_3_1]</b> - A Constraint cannot be applied to itself. </li>
	 * </ul>
	 * 
	 * @param constraint A Constraint that satisfy the (CWM-)Constraint [C_3_1] and that must be satisfied by this Constraint.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws ConstraintViolation Is thrown if the constraint [C_3_1] is violated.   
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// GConstraint capabilities	
//====================================================================================================================================================



	/**
	 * Adds a constrainedElement to the list of ModelElements affected by the Constraint.
	 * </p>
	 * <p>
	 * The ElementConstraint association provides linkages between ModelElements and the Constraint instances that constrain their state. Note that a 
	 * Constraint instance may not simultaneously participate in both the ElementConstraint and the StereotypeConstraint associations.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: ElementConstraint::constrainedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: ModelElement::constraint</li>
	 * </ul>
	 * 
	 * @param column The constarinted Column to be added. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 *
	 */
	Boolean addConstraintedElement( TableColumn column );



	/**
	 * Adds a constrainedElement to the list of ModelElements affected by the Constraint.
	 * </p>
	 * <p>
	 * The ElementConstraint association provides linkages between ModelElements and the Constraint instances that constrain their state. Note that a 
	 * Constraint instance may not simultaneously participate in both the ElementConstraint and the StereotypeConstraint associations.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>			: ModelElement</li>
	 * <li><i>defined by</i>	: ElementConstraint::constrainedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: ModelElement::constraint</li>
	 * </ul>
	 * 
	 * @param table The constarinted Table to be added. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 *
	 */
	Boolean addConstraintedElement( Table table );



	/**
	 * Indicates the timing of the constraint enforcement during multiple-user updates. 
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : DeferabilityType</li>
	 * <li><i>multiplicity</i> : exactly one</li> 
	 * </ul>
	 * 
	 * @return A textual description of the type of information this Description represents. Can't be <code>null</code>.
	 * 
	 */
	Deferability getDeferability();



	/**
	 * Indicates the timing of the constraint enforcement during multiple-user updates. 
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : DeferrabilityType</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param deferability A textual description of the type of information this Description represents. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>deferability</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>deferability</code> is <code>null</code>. 
	 * 
	 */
	Deferability setDeferability( Deferability deferability );
}