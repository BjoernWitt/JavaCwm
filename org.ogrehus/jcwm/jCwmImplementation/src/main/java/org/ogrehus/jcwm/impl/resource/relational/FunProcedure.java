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

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.ParameterDirection;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.references.behavioralparameter.BehavioralParameter;
import org.ogrehus.jcwm.impl.objectmodel.behavioral.util.generic.GFunCwmMethod;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GParameter;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.Procedure;
import org.ogrehus.jcwm.api.resource.relational.SQLParameter;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.util.ProcedureType;
import org.ogrehus.jcwm.impl.objectmodel.behavioral.FunOperation;


/**
 * This class describes Relational DBMS Stored procedures and functions. 
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunProcedure
extends
	GFunCwmMethod<
		  Schema
		, Schema
		, GClassifier< ?, ?, ?, ?, ?, ?>
		, SQLParameter
	>
implements
Procedure
{


	/**
	 * Factory method that creates a new instance of Procedure by specific parameters.
	 * 
	 * <p>
	 * A Procedure describes Relational DBMS Stored procedures and functions.
	 * </p>
	 * 
	 * @param name An identifier for the Procedure within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Procedure within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param isQuery Specifies whether an execution of the Procedure leaves the state of the system unchanged.<code>true</code> indicates that the
	 * state is unchanged; <code>false</code> indicates that side-effects may occur. Must not be <code>null</code>.
	 * 
	 * @param body A specification of the Procedure in some appropriate form (such as a programming language). The exact form of a Method
	 * specification and knowledge of the language in which it is described is outside the scope of the CWM. Must not be <code>null</code>.
	 * 
	 * @param type Function or Procedure. This indicates whether this object returns a value or not. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Procedure, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, <code>isQuery</code>,
	 * <code>body</code> or <code>type</code> is <code>null</code>.
	 * 
	 */
	public FunProcedure( String name, ProcedureExpression body, ProcedureType type ) {
		this( name
			, new FunOperation( name, Visibility._public, ProcedureType.isQuery( type ), Boolean.FALSE )
			, body
			, type
		);
	}



	/**
	 * Factory method that creates a new instance of Procedure by specific parameters.
	 * 
	 * <p>
	 * A Procedure describes Relational DBMS Stored procedures and functions.
	 * </p>
	 * 
	 * @param name An identifier for the Procedure within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Procedure within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param isQuery Specifies whether an execution of the Procedure leaves the state of the system unchanged. <code>true</code> indicates that the
	 * state is unchanged; <code>false</code> indicates that side-effects may occur. Must not be <code>null</code>.
	 * 
	 * @param body A specification of the Procedure in some appropriate form (such as a programming language). The exact form of a Method
	 * specification and knowledge of the language in which it is described is outside the scope of the CWM. Must not be <code>null</code>.
	 * 
	 * @param type Function or Procedure. This indicates whether this object returns a value or not. Must not be <code>null</code>.
	 * 
	 * @return A new instance of Procedure, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code>, <code>visibility</code>, <code>isQuery</code>,
	 * <code>body</code> or <code>type</code> is <code>null</code>.
	 * 
	 */
	public FunProcedure( String name, Operation specification, ProcedureExpression body, ProcedureType type ) {
		super( name, Visibility._public, specification, body );
		this.setType( type );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Schema setNamespace( Schema schema ) {
		return super.setNamespaceGeneric( schema );
	}



	public Boolean addImporter( Schema schema ) {
		return super.addImporterGeneric( schema );
	}



//====================================================================================================================================================
// GBehavioralFeature capabilities
//====================================================================================================================================================



	public Boolean addParameter( SQLParameter sqlParameter ) {
		this.specification.addParameter( sqlParameter.getSimpleName(), sqlParameter.getKind(), sqlParameter.getType() );        
		return super.addParameterGeneric( sqlParameter );
	}



	@Override
	public Boolean removeParameter( BehavioralParameter<?> sqlParameter ) {
		for ( GParameter<?, ?, ?, ?> parameter : this.specification.getParameters() ) {
			if ( parameter.getSimpleName().equals( sqlParameter.getSimpleName() ) ) {
				this.specification.removeParameter( sqlParameter );
			}
		}

		return super.removeParameter( sqlParameter );
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
		return addParameter( new FunSQLParameter( name, visibility, kind, type ) );
	}



//====================================================================================================================================================
// Procedure capabilities
//====================================================================================================================================================



	/**
	 * A Procedure can be either a Function or a true Procedure. 
	 * <p>
	 * This indicates whether this object returns a value or not.
	 * </p>
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i> : ProcedureType</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @return Function or Procedure. This indicates whether this object returns a value or not.
	 * 
	 */
	public ProcedureType getType() {
		return this.type;
	}



	/**
	 * A Procedure can be either a Function or a true Procedure. 
	 * <p>
	 * This indicates whether this object returns a value or not.
	 * </p>
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i> : ProcedureType</li>
	 * <li><i>multiplicity</i> : exactly one</li> 
	 * </ul>
	 * 
	 * @param type Function or Procedure. This indicates whether this object returns a value or not. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>type</code>. Can't  be <code>null</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>type</code> is <code>null</code>.
	 * 
	 */
	public ProcedureType setType( ProcedureType type ) {
		if ( type == null ) {
			throw new NullPointerException( "The parameter: type must not be null." );
		}
		ProcedureType old = this.type;
		this.type = type;

		return old;
	}



	public String getSqlQualifier() {
		return this.sqlQualifier;
	}



	public String setSqlQualifier( String sqlQualifier ) {
		String old = this.sqlQualifier;
		this.sqlQualifier = sqlQualifier;
		return old;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunProcedure" );
		if ( this.type != null ) {
			out.append( " | type=" + this.type );
		}
		if ( this.sqlQualifier != null ) {
			out.append( " | sqlQualifier=" + this.sqlQualifier );
		}

		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}



//====================================================================================================================================================
// properties
//====================================================================================================================================================



	/**
	 * A Procedure can be either a Function or a true Procedure. 
	 * <p>
	 * This indicates whether this object returns a value or not.
	 * </p>
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : ProcedureType</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */
	protected ProcedureType type = null;



	protected String sqlQualifier = null;
}