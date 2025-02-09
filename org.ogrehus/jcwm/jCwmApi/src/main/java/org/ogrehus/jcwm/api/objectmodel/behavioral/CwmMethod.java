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

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GCwmMethod;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

/**
 * CwmMethod is the implementation of an Operation. It specifies the algorithm or procedure that effects the results of an Operation.
 * <p>
 * <b>Constraints</b>:
 * </p>
 * <ul>
 * <li><b>[C-4-7]</b> If the realized Operation is a query, then so is the CwmMethod.</li>
 * <li><b>[C-4-8]</b> The signature of the CwmMethod should be the same as the signature of the realized Operation.</li>
 * <li><b>[C-4-9]</b> The visibility of the CwmMethod should be the same as for the realized Operation.</li>
 * <li><b>[C-4-10]</b> The realized Operation must be a feature (possibly inherited) of the same Classifier as the CwmMethod.</li>
 * <li><b>[C-4-11]</b> If the realized Operation has been overridden one or more times in the ancestors of the owner of the CwmMethod, then the
 * CwmMethod must realize the latest overriding (that is, all other Operations with the same signature must be owned by ancestors of the owner of the
 * realized Operation).</li>
 * <li><b>[C-4-12]</b> There may be at most one CwmMethod for a given Classifier (as owner of the CwmMethod) and Operation (as specification of the
 * CwmMethod) pair.</li>  
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface CwmMethod
extends
	GCwmMethod<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, GClassifier<?, ?, ?, ?, ?, ?>
		, Parameter
	> 
{


//====================================================================================================================================================
// CwmMethod capabilities
//====================================================================================================================================================



	/**
	 * A specification of the CwmMethod in some appropriate form (such as a programming language).
	 * <p>
	 * The exact form of a CwmMethod specification and knowledge of the language in which it is described is outside the scope of the CWM.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: ProcedureExpression</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return The specification of the method in some appropriate form.
	 * 
	 */
	ProcedureExpression getBody();



	/**
	 * Sets the value of body attribute.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: ProcedureExpression</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @see #getBody() For a descritpion on the property <code>type</code>.
	 * 
	 * @param body the new value of the property <code>type</code>. 
	 * 
	 * @return The old value of the property <code>type</code>. 
	 * 
	 */
	ProcedureExpression setBody( ProcedureExpression body );



	/**
	 * References the Operation that the CwmMethod implements.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Operation</li>
	 * <li><i>defined by</i>: OperationMethod::specification</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * <li><i>inverse</i>: Operation::method</li>
	 * </ul>
	 * 
	 * @return The specification of the operation the method implements. Cannot be <code>null</code>.
	 * 
	 */
	Operation getSpecification();



	/**
	 * Sets the value of reference specification.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Operation</li>
	 * <li><i>defined by</i>: OperationMethod::specification</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * <li><i>inverse</i>: Operation::method</li>
	 * </ul>
	 * 
	 * @see #getSpecification() for description on the reference <code>specification</code>.
	 * 
	 * @param specification New value to be set.
	 * 
	 * @return The old value set before.
	 */
	Operation setSpecification( Operation specification );



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains this CwmMethod. 
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
	 * Adds an importer (Package) to this CwmMethod, that will contain this CwmMethod as imported.
	 * 
	 * @param importer The specific package, that will contain this CwmMethod.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer );



//====================================================================================================================================================
// GBehavioral capabilities	
//====================================================================================================================================================



	/**
	 * Adds a parameter to the Set of ordered Parameter instances that comprise the signature of this Operation.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Parameter</li>
	 * <li><i>defined by</i>: BehavioralFeature::parameter</li>
	 * <li><i>multiplicity</i>: zero or more; ordered</li>
	 * <li><i>inverse</i>: Parameter::behavioralFeature</li>   
	 * </ul>
	 * 
	 * @param parameter Will be added to the set of ordered Parameter instances that comprise the signature of this Operation.
	 *  
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 *   
	 */
	Boolean addParameter( Parameter parameter );
}