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

import org.ogrehus.jcwm.api.resource.relational.*;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GAttribute;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


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
 * @param <NAMESPACE> The type of the Namespace that contains this Column.
 * @param <CONSTRAINT> The type of the Constraint under which this Column is. 
 * @param <OWNER> The type of the Classifier that owns this Column.
 */
public interface GColumn<
	  CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWNER      extends GCwmClass<?, ?, ?, ?, ?, ?, ?> // remind SQLStructuredType doesn't inherit from GColumnSet
>extends
	  GAttribute<GNamespace<?, ?, ?, ?, ?>, Catalog, Dependency, CONSTRAINT, OWNER, SQLDataType<?, ?>>
	, Column
{
	// only configurational purpose in type definition in interface
}
