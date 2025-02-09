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
package org.ogrehus.jcwm.impl.resource.relational;

import org.ogrehus.jcwm.api.objectmodel.core.BooleanExpression;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.ConstraintViolation;
import org.ogrehus.jcwm.api.objectmodel.core.util.CwmConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.resource.relational.CheckConstraint;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.Table;
import org.ogrehus.jcwm.api.resource.relational.TableColumn;
import org.ogrehus.jcwm.api.resource.relational.util.Deferability;
import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunConstraint;

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
public class FunCheckConstraint
extends
	GFunConstraint<
		  Schema
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
	>
implements
	CheckConstraint
{


	/**
	 * Creates a new instance of CheckConstraint by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul>
	 * 
	 * @param name An identifier for the CheckConstraint within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param body A BooleanExpression that must be true when evaluated for an instance of a system to be well formed. A boolean expression defining
	 * the CheckConstraint. Expressions are written as strings in a designated language. For the model to be well formed, the expression must always
	 * yield a true value when evaluated for instances of the constrained elements at any time when the system is stable; that is, not during the
	 * execution of an atomic operation. Must not be <code>null</code>.
	 * 
	 * @param deferability A textual description of the type of information this Description represents. Must not be <code>null</code>.
	 * 
	 * @return A new instance of CheckConstraint, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>body</code>
	 * is <code>null</code>.
	 * 
	 */
	public FunCheckConstraint( String name, BooleanExpression body, Deferability deferability ) {
		super( name, Visibility._public, body );
		setDeferability( deferability );
	}



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
	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	/**
	 * Adds an importer (Package) to this Constraint, that will contain this Constraint as imported.
	 * 
	 * @param importer The specific package, that will contain this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer ) {
		return super.addImporterGeneric( importer );
	}



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
	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



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
	 * @param column The constrainted Column to be added.
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addConstraintedElement( TableColumn column ) {
		if ( this.constrainedElements.add( column ) ) {
			column.addConstraint( this );
			return Boolean.TRUE; 
		}
		return Boolean.FALSE;
	}



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
	 * @param table The constrainted Table to be added. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addConstraintedElement( Table table ) {
		if ( this.equals( table ) ) {
			// [C-3-1] A Constraint cannot be applied to itself.
			throw new ConstraintViolation( CwmConstraint.C_3_1, this.getQualifiedName() );
		}
		
		if ( this.constrainedElements.add( table ) ) {
			table.addCheckConstraint( this ); // bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



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
	 * @return Indicates the timing of the constraint enforcement during multiple-user updates. Can't be <code>null</code>.
	 * 
	 */
	public Deferability getDeferability() {
		return this.deferability;
	}



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
	 * @param deferability The timing of the constraint enforcement during multiple-user updates. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>deferability</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>deferability</code> is <code>null</code>.
	 * 
	 */
	public Deferability setDeferability( Deferability deferability ) {
		if ( deferability == null ) {
			throw new NullPointerException( "The parameter: deferability must not be null." );
		}
		Deferability old = this.deferability;
		this.deferability = deferability;
		return old;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunCheckConstraint" );
		if ( this.deferability != null ) {
			out.append( " | deferability=" + this.deferability );
		}
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		return out.toString();
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Indicates the timing of the constraint enforcement during multiple-user updates. 
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Deferability</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param type A textual description of the type of information this Description represents. must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>language</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>language</code> is <code>null</code>.
	 * 
	 */
	protected Deferability deferability = null;
}