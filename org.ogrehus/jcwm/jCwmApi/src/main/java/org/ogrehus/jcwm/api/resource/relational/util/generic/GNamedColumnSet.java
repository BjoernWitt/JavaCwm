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
package org.ogrehus.jcwm.api.resource.relational.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.resource.relational.NamedColumnSet;
import org.ogrehus.jcwm.api.resource.relational.Schema;


/**
 * A catalogued set of columns, which may be Table or View.
 * <p>
 * Note for typed tables: It is assumed that the typed table will own a set of columns conforming to the type they are OF. This set of columns allows 
 * the manipulation of the table by products that ignore this [SQL] extension. It also allows the columns of type REF, to be copied to a column with 
 * a SCOPE reference.
 * </p>
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <CONSTRAINT> The type of the Constraint under which this Class is. 
 * @param <COLUMN> The type of Column this ColumnSet contains. 
 * 
 */
public interface GNamedColumnSet<
	  CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, COLUMN     extends GColumn<?, ?>
> extends
	  GColumnSet<Schema, Schema, CONSTRAINT, COLUMN>
	, NamedColumnSet<COLUMN>
{
	// only configurational purpose in type definition in interface
}