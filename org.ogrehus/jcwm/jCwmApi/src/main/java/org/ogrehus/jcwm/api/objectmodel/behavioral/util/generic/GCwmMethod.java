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
package org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;

/**
 * Method is the implementation of an Operation. It specifies the algorithm or procedure that effects the results of an Operation.
 * <p>
 * <b>Constraints</b>:
 * </p>
 * <ul>
 * <li><b>[C-4-7]</b> If the realized Operation is a query, then so is the Method.</li>
 * <li><b>[C-4-8]</b> The signature of the Method should be the same as the signature of the realized Operation.</li>
 * <li><b>[C-4-9]</b> The visibility of the Method should be the same as for the realized Operation.</li>
 * <li><b>[C-4-10]</b> The realized Operation must be a feature (possibly inherited) of the same Classifier as the Method.</li> 
 * <li><b>[C-4-11]</b> If the realized Operation has been overridden one or more times in the ancestors of the owner of the Method, then the Method 
 * must realize the latest overriding (that is, all other Operations with the same signature must be owned by ancestors of the owner of the realized 
 * Operation).</li>
 * <li><b>[C-4-12]</b> There may be at most one Method for a given Classifier (as owner of the Method) and Operation (as specification of the Method) 
 * pair.</li>  
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GFeature.
 * @param <PACKAGE> The type of the importer of this GFeature.
 * @param <CLASSIFIER> The type of the Classifier that owns this GFeature.
 * @param <PARAMETER> The caller must supply as a list of values compatible with the types of the Parameters  
 *
 */
public interface GCwmMethod<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CLASSIFIER extends GClassifier< ?, ?, ?, ?, ?, ?>
	, PARAMETER  extends GParameter<?, ?, ?, ?>
> extends
	GBehavioralFeature<NAMESPACE, PACKAGE, CLASSIFIER, PARAMETER>
{


//======================================================================================================================
// CwmMethod capabilities
//======================================================================================================================



	/**
	 * A specification of the Method in some appropriate form (such as a programming language).
	 * <p>
	 * The exact form of a Method's specification and knowledge of the language in which it is described is outside the scope of the CWM.
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
	 * References the Operation that the Method implements.
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
	 * Links an Operation with the Method instance(s) that realize it.
	 * <p>
	 * The various Method instances represent alternative implementations (usually in different programming languages or environments) of the 
	 * Operation.
	 * </p>
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
	 * @param specification The Operation which will be linked to the Method instance(s) that realize it. Must not be <code>null</code>.
	 *  
	 * @return The old value set before.
	 * 
	 * @throws NullPointerException Is thrown if the parameter: <code>specification</code> is <code>null</code>.  
	 * 
	 */
	Operation setSpecification( Operation specification );



	Boolean removeSpecification();
}
