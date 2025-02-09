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

import org.ogrehus.jcwm.api.objectmodel.behavioral.CwmMethod;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Parameter;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.objectmodel.behavioral.util.generic.GFunCwmMethod;


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
 */
public class FunCwmMethod
extends
	GFunCwmMethod<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, GClassifier<?, ?, ?, ?, ? , ?>
		, Parameter
> implements
	CwmMethod
{


	/**
	 * Creates a new instance of FunCwmMethod by specific parameters.
	 * <p>
	 * Method is the implementation of an Operation. It specifies the algorithm or procedure that effects the results of an Operation.
	 * </p>
	 * 
	 * @param name An identifier for the CwmMethod within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmMethod within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param isQuery Specifies whether an execution of the CwmMethod leaves the state of the system unchanged. <code>true</code> indicates that the
	 * state is unchanged; <code>false</code> indicates that side-effects may occur. Must not be <code>null</code>.
	 * 
	 * @param specification The Operation which will be linked to the Method instance(s) that realize it. Must not be <code>null</code>.
	 * 
	 * @param body A specification of the CwmMethod in some appropriate form (such as a programming language). The exact form of a CwmMethod
	 * specification and knowledge of the language in which it is described is outside the scope of the CWM. Must not be <code>null</code>.
	 * 
	 * @return A new instance of FunCwmMethod, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, <code>isQuery</code>,
	 * <code>specification</code>  or <code>body</code> is <code>null</code>.
	 * 
	 * @throw ConstraintViolation Is thrown if one of the following CwmConstraints is violated: <code>C_4_7</code>, <code>C_4_8</code>,
	 * <code>C_4_9</code>, <code>C_4_10</code>, <code>C_4_11</code> or <code>C_4_12</code>.
	 * 
	 */
	public FunCwmMethod( 
		  String name
		, Visibility visibility
		, Operation specification
		, ProcedureExpression body
	) {
		super( name, visibility, specification, body ); // may throw NullPointerException + ConstraintViolation
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public GNamespace<?, ?, ?, ?, ?> setNamespace( GNamespace<?, ?, ?, ?, ?> namespace ) {
		return super.setNamespaceGeneric( namespace );
	}



	public Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer ) {
		return super.addImporterGeneric( importer );		
	}



//====================================================================================================================================================
// GFeature capabilities
//====================================================================================================================================================



	/**
	 * The Classifier declaring the Feature.
	 * </p>
	 * <p>
	 * The ClassifierFeature association provides a composite aggregation containment relationship between Classifiers and the Features they own. The
	 * feature end of the association is ordered allowing preservation of the ordering of Features within their owning Classifier. A GFeature can be
	 * owned by at most one Classifier. The optional character of ownership is intended as a convenience to tools, allowing them to create Features
	 * prior to linking them to their owning Classifier.
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: generic CLASSIFIER</li>
	 * <li><i>defined by</i>	: ClassifierFeature::owner</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Classifier::feature</li>
	 * </ul>
	 *
	 * @return The old value of  owner if one is available, <code>null</code> otherwise.
	 * 
	 */
	public GClassifier<?, ?, ?, ?, ?, ?> setOwner( GClassifier<?, ?, ?, ?, ?, ?> owner ) {
		return super.setOwnerGeneric( owner );
	}



//====================================================================================================================================================
// GBehavioral capabilities
//====================================================================================================================================================



	public Boolean addParameter( Parameter parameter ) {
		return super.addParameterGeneric( parameter );
	}



	public <T extends GClassifier<?, ?, ?, ?, ?, ?>> Boolean addParameter( String name, ParameterDirection kind, T type ) {
		return addParameter( name, Visibility._public, kind, type );
	}



	public <T extends GClassifier<?, ?, ?, ?, ?, ?>> Boolean addParameter(
		  String name
		, Visibility visibility
		, ParameterDirection kind
		, T type
	) {
		return addParameter( new FunParameter( name, visibility, kind, type ) );
	}
}