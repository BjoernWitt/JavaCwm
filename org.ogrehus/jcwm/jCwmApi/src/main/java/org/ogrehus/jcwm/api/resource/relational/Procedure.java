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

import org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic.GCwmMethod;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.resource.relational.util.ProcedureType;


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
public interface Procedure
extends
	GCwmMethod<
		  Schema
		, Schema
		, GClassifier< ?, ?, ?, ?, ?, ?>
		, SQLParameter
	>
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	Schema setNamespace( Schema catalog );



	Boolean addImporter( Schema schema );



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
	ProcedureType getType();



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
	ProcedureType setType( ProcedureType type );



	String getSqlQualifier();



	String setSqlQualifier( String sqlQualifier );
}