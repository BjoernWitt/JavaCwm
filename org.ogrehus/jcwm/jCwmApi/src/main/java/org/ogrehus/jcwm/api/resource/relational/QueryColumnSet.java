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
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumnSet;

/**
 * The result set of a query.
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface QueryColumnSet
extends
	GColumnSet<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Constraint
		, QueryColumn
	>
{


	/**
	 * The query associated with the QueryColumnSet. 
	 * <p>
	 * The query result must match the set of Columns associated with the QueryColumnSet (in parent class ColumnSet).
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
	 * @return The query associated with the QueryColumnSet. 
	 * 
	 */
	QueryExpression getQueryExpression();



	/**
	 * The query associated with the QueryColumnSet. 
	 * <p>
	 * The query result must match the set of Columns associated with the QueryColumnSet (in parent class ColumnSet).
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
	 * @param queryExpression The query associated with the QueryColumnSet.
	 * 
	 * @return Old value of the property <code>queryExpression</code>. Can't be <code>null</code>. 
	 *
	 * @throws NullPointerException is thrown if the parameter <code>queryExpression</code> is <code>null</code>. 
	 * 
	 */
	QueryExpression setQueryExpression( QueryExpression queryExpression );
}