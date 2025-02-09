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
package org.ogrehus.jcwm.api.objectmodel.core.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.BooleanExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.elementconstraint.ConstraintedElement;


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
 * The constraint concept allows new semantics to be specified linguistically for a model element. In the meta-model a Constraint directly attached 
 * to a ModelElement describes semantic restrictions that this ModelElement must obey.
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
 * @param <NAMESPACE> The type of the Namespace that contains this Constraint.
 * @param <PACKAGE> The type of the importer of this Constraint.
 * @param <DEPENDENCY> The type of ClientDependency of this Constraint.
 * @param <CONSTRAINT> The type of the Constraint under which this Constraint is. 
 * @param <CONSTRAINTED> The element that is under this constraint.
 * 
 */
public interface GConstraint<
	  NAMESPACE    extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE      extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY   extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT   extends GConstraint<?, ?, ?, ?, ?>
	, CONSTRAINTED extends GModelElement<?, ?, ?, ?>
>extends
	  GModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT>
	, ConstraintedElement<CONSTRAINTED>
{


	/**
	 * Returns the property <code>body</code>.
	 * <p>
	 * A BooleanExpression that must be true when evaluated for an instance of a system to be well formed. A boolean expression defining the 
	 * constraint. Expressions are written as strings in a designated language. For the model to be well formed, the expression must always yield a 
	 * true value when evaluated for instances of the constrained elements at any time when the system is stable; that is, not during the execution 
	 * of an atomic operation.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: BooleanExpression</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @return The body of this Constraint, to evaluate a statement. Can not be <code>null</code>.
	 *
	 */
	BooleanExpression getBody();



	/**
	 * Sets the value of the property <code>body</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>			: BooleanExpression</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @see #getBody() for description of the property <code>body</code>.
	 * 
	 * @param body A BooleanExpression that must be true when evaluated for an instance of a system to be well formed. A boolean expression defining 
	 * the constraint.
	 * 
	 * @return The old value of property <code>body</code> of this Constraint, to evaluate a statement. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>body</code> is <code>null</code>.
	 *
	 */
	BooleanExpression setBody( BooleanExpression body );
}