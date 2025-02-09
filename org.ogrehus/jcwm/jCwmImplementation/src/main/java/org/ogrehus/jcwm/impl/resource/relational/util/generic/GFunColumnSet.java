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
package org.ogrehus.jcwm.impl.resource.relational.util.generic;

import org.ogrehus.jcwm.api.resource.relational.ColumnSet;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.resource.relational.SQLIndex;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumn;
import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunCwmClass;


/**
 * A set of columns, representing either the result of a query, a view, or a physical table.
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the namespace that contains this ColumnSet.
 * @param <PACKAGE> The type of the importer of this ColumnSet.
 * @param <CONSTRAINT> The type of the Constraint under which this Class is.
 * @param <COLUMN> The type of Column this ColumnSet contains.
 * 
 */
public abstract class GFunColumnSet<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>    
	, COLUMN     extends GColumn<?, ?>
> extends
	GFunCwmClass<NAMESPACE, PACKAGE, Dependency, CONSTRAINT, GModelElement<?, ?, ?, ?>, COLUMN, SQLIndex>
implements
	ColumnSet<COLUMN>
{


	/**
	 * Factory method that creates a new instance of ColumnSet by specific parameters.
	 * <p>
	 * A set of columns, representing either the result of a query, a view, or a physical table.
	 * </p>
	 * 
	 * 
	 * @param name An identifier for the ColumnSet within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the ColumnSet within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this ColumnSet is abstract and can not be instantiated, <code>false</code> otherwise.
	 *
	 * @return A new instance of ColumnSet by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 * 
	 */
	protected GFunColumnSet( String name, Visibility visibility, Boolean notInstantiable ) {
		super(name, visibility, notInstantiable);
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunColumnSet" );
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}
}