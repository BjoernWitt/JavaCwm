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

import org.ogrehus.jcwm.api.foundation.datatypes.QueryExpression;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GNamedColumnSet;


/**
 * An action run by the DBMS when specified events occur on the table owning the Trigger.
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface View
extends
	GNamedColumnSet<Constraint, ViewColumn>
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	Boolean addColumn( ViewColumn tableColumn );



	Boolean addColumn( String columnName, SQLDataType<?, ?> type );



	ViewColumn getColumn( String simpleName );



	/**
	 * Indicates whether the underlying tables can be updated through an update 
	 * to this View.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Boolean</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @return <code>false</code> if the underlying tables can be updated through an update to this View, <code>true</code> otherwise. Can't 
	 * be <code>null</code>. 
	 * 
	 */
	Boolean isReadOnly();



	/**
	 * Indicates whether the underlying tables can be updated through an update to this View.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Boolean</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @param readOnly <code>false</code> if the underlying tables can be updated through an update to this View, <code>true</code> otherwise. Must 
	 * not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>readOnly</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>readOnly</code> is <code>null</code>.
	 * 
	 */
	Boolean setReadOnly( Boolean readOnly );



	/**
	 * The query associated with the View.
	 * <p>
	 * The query result must match the set of Columns associated with the View (in parent class ColumnSet).
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : QueryExpression</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @return The query associated with the View.
	 * 
	 */
	QueryExpression getQueryExpression();



	/**
	 * The query associated with the View. 
	 * <p>
	 * The query result must match the set of Columns associated with the View (in parent class ColumnSet).
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : QueryExpression</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @param queryExpression The query associated with the View.
	 * 
	 * @return Old value of the property <code>queryExpression</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>queryExpression</code> is <code>null</code>.
	 * 
	 */
	QueryExpression setQueryExpression( QueryExpression queryExpression );



	/**
	 * This field is meaningful only if the view is not ReadOnly.
	 *  
	 * <p>
	 * CheckOption indicates that the RDBMS will validate that changes made to 
	 * the data verify the view filtering condition and belong to the view 
	 * result set.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Boolean</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * <li><i>constraints</i> : only used when isReadOnly=false</li> 
	 * </ul>
	 *
	 * <p>
	 * <b>Constraints</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C-2]</b> checkOption is valid only if isReadOnly is False.</li>
	 * </ul>
	 *
	 * @return <code>true</code> if the RDBMS will validate that changes made to the data verify the view filtering condition and belong to the view 
	 * result set, <code>false</code> otherwise. can't be <code>null</code>.
	 * 
	 */
	Boolean getCheckOption();



	/**
	 * This field is meaningful only if the view is not ReadOnly.
	 *  
	 * <p>
	 * CheckOption indicates that the RDBMS will validate that changes made to the data verify the view filtering condition and belong to the view 
	 * result set.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Boolean</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * <li><i>constraints</i> : only used when isReadOnly=false</li> 
	 * </ul>
	 *
	 * <p>
	 * <b>Constraints</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C-2]</b> checkOption is valid only if isReadOnly is False.</li>
	 * </ul>
	 * 
	 * @param checkOption <code>true</code> if the RDBMS will validate that changes made to the data verify the view filtering condition and belong 
	 * to the view result set, <code>false</code> otherwise. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>checkOption</code>. Can't be <code>null</code>.
	 *
	 * @throws NullPointerException is thrown if the parameter <code>checkOption</code> is <code>null</code>.
	 * 
	 */
	Boolean setCheckOption( Boolean checkOption );
}