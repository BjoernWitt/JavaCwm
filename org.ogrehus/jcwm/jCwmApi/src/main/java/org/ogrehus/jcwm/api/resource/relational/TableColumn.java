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

import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumn;

/**
 * A column in a result set, a view, a table, or an SQLStructuredType.
 *
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation-C-3]</b> The scale attribute is valid only if the precision attribute is specified.</li>
 * </ul> 
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface TableColumn
extends
	GColumn<CheckConstraint, Table>
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Adds another CheckConstraint to this Column.
	 * 
	 * @param checkConstraint A CheckConstraint that must be satisfied by this Column.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( CheckConstraint checkConstraint );



//====================================================================================================================================================
// Feature capabilities	
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
	 * <li><i>class</i>			: Association</li>
	 * <li><i>defined by</i>	: ClassifierFeature::owner</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Association::feature</li>
	 * </ul>
	 * 
	 * @param table The ColumnSet declaring this Column.
	 *
	 * @return The old value of owner if one is available, <code>null</code> otherwise.
	 * 
	 */
	Table setOwner( Table table );
}