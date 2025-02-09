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
package org.ogrehus.jcwm.impl.objectmodel.behavioral.util.generic;


import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GCwmMethod;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.ConstraintViolation;
import org.ogrehus.jcwm.api.objectmodel.core.util.CwmConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * Method is the implementation of an Operation. It specifies the algorithm or procedure that effects the results of an 
 * Operation.
 * <p>
 * <b>Constraints</b>:
 * </p>
 * <ul>
 * <li><b>[C-4-7]</b> If the realized Operation is a query, then so is the Method.</li>
 * <li><b>[C-4-8]</b> The signature of the Method should be the same as the signature of the realized Operation.</li>
 * <li><b>[C-4-9]</b> The visibility of the Method should be the same as for the realized Operation.</li>
 * <li><b>[C-4-10]</b> The realized Operation must be a feature (possibly inherited) of the same Classifier as the 
 * Method.</li> 
 * <li><b>[C-4-11]</b> If the realized Operation has been overridden one or more times in the ancestors of the owner of 
 * the Method, then the Method must realize the latest overriding (that is, all other Operations with the same 
 * signature must be owned by ancestors of the owner of the realized Operation).</li>
 * <li><b>[C-4-12]</b> There may be at most one Method for a given Classifier (as owner of the Method) and Operation 
 * (as specification of the Method) pair.</li>  
 * </ul> 
 * <p>
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @version $Id: GFunCwmMethod.java 279 2008-06-06 15:53:32Z bjoern $
 * 
 */
public abstract class GFunCwmMethod<
      NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CLASSIFIER extends GClassifier< ?, ?, ?, ?, ?, ?>
	, PARAMETER  extends GParameter<?, ?, ?, ?>
> extends
	GFunBehavioralFeature<NAMESPACE, PACKAGE, CLASSIFIER, PARAMETER>
implements
    GCwmMethod<NAMESPACE, PACKAGE, CLASSIFIER, PARAMETER>
{


	/**
	 * Creates a new instance of CwmMethod by specific parameters.
     * <p>
     * Method is the implementation of an Operation. It specifies the algorithm or procedure that effects the results of
     * an Operation.
     * </p>
	 * 
	 * @param name An identifier for the CwmMethod within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmMethod within its owning Namespace. Must not be 
	 * <code>null</code>.
	 *
	 * @param isQuery Specifies whether an execution of the CwmMethod leaves the state of the system unchanged. 
	 * <code>true</code> indicates that the state is unchanged; <code>false</code> indicates that side-effects may 
	 * occur. Must not be <code>null</code>.
	 * 
	 * @param specification The Operation which will be linked to the Method instance(s) that realize it. Must not be 
     * <code>null</code>.
     * 
	 * @param body A specification of the CwmMethod in some appropriate form (such as a programming language). The exact 
	 * form of a CwmMethod�s specification and knowledge of the language in which it is described is outside the scope of 
	 * the CWM. Must not be <code>null</code>.
	 *  
	 * @return A new instance of CwmMethod, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, 
	 * <code>isQuery</code>, <code>specification</code>  or <code>body</code> is <code>null</code>. 
     * 
     * @throw ConstraintViolation Is thrown if one of the following CwmConstraints is violated: <code>C_4_7</code>, 
     * <code>C_4_8</code>, <code>C_4_9</code>, <code>C_4_10</code>, <code>C_4_11</code> or <code>C_4_12</code>. 
	 * 
	 */
	protected GFunCwmMethod( 
          String name
        , Visibility visibility
        , Operation specification        
        , ProcedureExpression body
    ) {
        super( name, visibility, specification.isQuery() ); // may throw NullPointerException
        for ( GParameter<?, ?, ?, ?> param : specification.getParameters() ) {
            addParameter( param.getSimpleName(), param.getVisibility(), param.getKind(), param.getType() );                    
        }
        this.setSpecification( specification );  // may throw NullPointerException + ConstraintViolation
        this.setBody( body ); // may throw NullPointerException
    }        
    

//======================================================================================================================
// CwmMethod capabilities
//======================================================================================================================

    
	/**
	 * A specification of the CwmMethod in some appropriate form (such as a 
	 * programming language).
	 * <p>
	 * The exact form of a CwmMethod�s specification and knowledge of the language 
	 * in which it is described is outside the scope of the CWM.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: ProcedureExpression</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 *  @return The specification of the method in some appropriate form. 
	 *  
	 */
	public ProcedureExpression getBody() {
        return this.body;
    }

    
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
	public ProcedureExpression setBody( ProcedureExpression body ) {
        if ( body == null ) {
            throw new NullPointerException( "Parameter: body must not be null!" );
        }
        ProcedureExpression old = this.body;
        this.body = body;
        return old;
    }
    
    
    @Override
    protected <P extends PARAMETER> Boolean addParameterGeneric( P parameter ) {
		if ( parameter != null && this.parameters.add( parameter ) ) {
            invokeByReflection( parameter, "setBehavioralFeature", this );
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
    }
    
    
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
	 * @return The specification of the operation the method implements. Cannot 
	 * be <code>null</code>.
	 * 
	 */
	public Operation getSpecification() {
        return this.specification;
    }
	
	
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
     * <p>
     * <b>Constraints</b>:
     * </p>
     * <ul>
     * <li><b>[C-4-7]</b> If the realized Operation is a query, then so is the Method.
     * <pre>
     * <b>context</b> Method <b>inv</b>:
     * self.specification->isQuery <b>implies</b> self.isQuery
     * </pre>
     * </li>
     * <li><b>[C-4-8]</b> The signature of the Method should be the same as the signature of the realized Operation.
     * <pre>
     * <b>context</b> Method <b>inv</b>:
     * self.hasSameSignature( self.specification )
     * </pre>
     * </li>
     * <li><b>[C-4-9]</b> The visibility of the Method should be the same as for the realized Operation.
     * <pre>
     * <b>context</b> Method <b>inv</b>:
     * self.visibility = self.specification.visibility
     * </pre>
     * </li>
     * <li><b>[C-4-10]</b> The realized Operation must be a feature (possibly inherited) of the same Classifier as the 
     * Method.
     * <pre>
     * <b>context</b> Method <b>inv</b>:
     * self.owner.allOperations->includes( self.specification )
     * </pre>
     * </li>
     * <li><b>[C-4-11]</b> If the realized Operation has been overridden one or more times in the ancestors of the owner
     * of the Method, then the Method must realize the latest overriding (that is, all other Operations with the same 
     * signature must be owned by ancestors of the owner of the realized Operation).
     * <pre>
     * <b>context</b> Method <b>inv</b>:
     * self.specification.owner.allOperations -> includesAll( 
     *                                             self.owner.allOperations -> select( op | self.hasSameSignature( op ) )
     *                                           )
     * </pre>
     * </li>
     * <li><b>[C-4-12]</b> There may be at most one Method for a given Classifier (as owner of the Method) and Operation 
     * (as specification of the Method) pair.
     * <pre>
     * <b>context</b> Method <b>inv</b>:
     * self.owner.allMethods -> select( operation = self.operation ) -> size = 1
     * </pre>
     * </li>
     * </ul> 
     * <p>
	 * 
	 * @see #getSpecification() for description on the reference <code>specification</code>.
	 * 
	 * @param specification New value to be set.  
	 *  
	 * @return The old value assigned before.
     * 
     * @throw NullPoinerException Is thrown if the parameter <code>specification</code> is <code>null</code>.
     * 
     * @throw ConstraintViolation Is thrown if one of the following CwmConstraints is violated: <code>C_4_7</code>, 
     * <code>C_4_8</code>, <code>C_4_9</code>, <code>C_4_10</code>, <code>C_4_11</code> or <code>C_4_12</code>. 
     * 
	 */
	public Operation setSpecification( Operation specification ) {
        if ( specification == null ) {
            throw new NullPointerException( "The parameter: specification must not be null!" );
        }
        if ( specification.equals( this.specification ) ) {
        	return specification; // no changes, cause its the same specification
        }
        if ( specification.isQuery() && !this.isQuery ) {
            throw new ConstraintViolation( CwmConstraint.C_4_7 );
        }
        if ( !this.hasSameSignature( specification ) ) {
            throw new ConstraintViolation( CwmConstraint.C_4_8 );
        }
        if ( this.visibility != specification.getVisibility() ) {
            throw new ConstraintViolation( CwmConstraint.C_4_9 );
        }
//        if ( this.owner.getAllOperations().contains( specification ) ) {
//            throw new ConstraintViolation( CwmConstraint.C_4_10 );
//        }
//        
//        Set<Operation> sameSignatures = new HashSet<Operation>();
//        for ( Operation operation : this.getOwner().allOperations() ) {
//           if ( hasSameSignature( operation ) )  {
//               sameSignatures.add( operation );
//           }
//        }
//        if ( !specification.getOwner().allOperations().containsAll( sameSignatures ) {
//            throw new ConstraintViolation( CwmConstraint.C_4_11 );
//        }
//        
// 
//        Boolean foundMatchingOperation = Boolean.FALSE;
//        for ( CwmMethod method : this.getOwner().getAllMethods() ) {
//            if ( method.getSpecification().equals( specification ) ) {
//                if ( !foundMatchingOperation ) {
//                    foundMatchingOperation = Boolean.TRUE;
//                } else {
//                    // this means a second was found for owner...
//                    throw new ConstraintViolation( CwmConstraint.C_4_12 );
//                }
//             }
//         }
         
         
        Operation old = this.specification;
        if ( old != null ) {
            old.removeMethod( this ); // bi-directional reference
        }
        this.specification = specification;
		if ( !this.specification.getMethods().contains( this ) ) {
			invokeByReflection( this.specification, "addMethod", this ); // bi-directional reference 
		}
        return old;
    }  

	
	public Boolean removeSpecification() {
		if ( this.specification == null ) {
			return Boolean.TRUE;
		}
        Operation old = this.specification;
        this.specification = null;
        return old.removeMethod( this );
	}		

	
//======================================================================================================================
// Object capabilities
//======================================================================================================================

    @Override
    public String toString() {
        StringBuffer out = new StringBuffer( "[GFunCwmMethod" );
        if ( this.body != null ) {
            out.append( " | body=" + this.body.getBody() );
        }
        if ( this.specification != null ) {
            out.append( " | specification=" + this.specification.getSimpleName() );                
        }
        out.append( " |\nextends: " );
        out.append( super.toString() );
        out.append( "]" );        
        return out.toString();
    }
	    
	
//======================================================================================================================
// Properties
//======================================================================================================================

    
	/**
	 * A specification of the Method in some appropriate form (such as a programming language).
	 * <p>
	 * The exact form of a Method�s specification and knowledge of the language in which it is described is outside the 
     * scope of the CWM.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: ProcedureExpression</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>  
	 *  
	 */
	protected ProcedureExpression body = null;
	
	
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
	 */
	protected Operation specification = null;

}





