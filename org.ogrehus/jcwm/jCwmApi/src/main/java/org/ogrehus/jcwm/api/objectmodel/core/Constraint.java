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
package org.ogrehus.jcwm.api.objectmodel.core;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * A constraint is a semantic condition or restriction expressed in text.
 * </p>
 * <p>
 * In the meta-model a Constraint is a BooleanExpression on an associated ModelElement(s) that must be true for the model to be well formed. This
 * restriction can be stated in natural language, or in different kinds of languages with well defined semantics. Certain Constraints are predefined,
 * others may be user defined. Note that a Constraint is an assertion, not an executable mechanism.
 * </p>
 * <p>
 * The specification is written as an expression in a designated constraint language. The language can be specially designed for writing constraints
 * (such as OCL), a programming language, mathematical notation, or natural language. If constraints are to be enforced by a model editor tool, then
 * the tool must understand the syntax and semantics of the constraint language. Because the choice of language is arbitrary, constraints can be used
 * as an extension mechanism.
 * </p>
 * <p>
 * The constraint concept allows new semantics to be specified linguistically for a model element. In the meta-model a Constraint directly attached to
 * a ModelElement describes semantic restrictions that this ModelElement must obey.
 * </p>
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li>A Constraint cannot be applied to itself. [C-3-1]</li>
 * </ul>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Constraint
extends
	GConstraint<
		  GNamespace< ?, ?, ?, ?, ?>
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
	 * Designates the Namespace that contains this Constraint.
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
	 * <li><i>class</i>			: Namespace</li>
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
	 * <li><b>[C_3_1]</b> - A Constraint cannot be applied to itself.</li>
	 * </ul>
	 * 
	 * @param constraint A Constraint that satisfy the (CWM-)Constraint [C_3_1] and that must be satisfied by this Constraint.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws <b>ConstraintViolation</b> Is thrown if the constraint [C_3_1] is violated.
	 * 
	 */
	Boolean addConstraint( Constraint  constraint );



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
	 * <p>
	 * <b>Constraints:</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C 3-1]</b> A Constraint cannot be applied to itself.
	 * <pre>
	 * <b>context</b> Constraint <b>inv</b>:
	 *   <b>not</b> self.constrainedElement -> includes( self )
	 * </pre></li>
	 * </ul> 
	 * 
	 * @param constrainted The constarintedElement to  be added.
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws ConstraintViolation if the Constraint <b>[C 3-1]</b> was violated.
	 * 
	 */
	Boolean addConstraintedElement( GModelElement<?, ?, ?, ?> constrainted );
}