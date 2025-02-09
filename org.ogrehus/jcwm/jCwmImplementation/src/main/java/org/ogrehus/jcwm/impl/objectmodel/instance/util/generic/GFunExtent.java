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
package org.ogrehus.jcwm.impl.objectmodel.instance.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GCwmObject;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GExtent;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;
import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunCwmPackage;

/**
 * Each instance of Extent owns a collection of instances and is used to link such collections to their structural and behavioral definitions in CWM 
 * Resource packages.
 * <p>
 * Because Extent is a subclass of package, it owns member instances via the ElementOwnership association.
 * </p>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2007
 * 
 * @param <MANAGER> The type of the holding Namespace that contains this Extent.
 * @param <CLASS> The type of the Namespace that contains this Extent.
 * @param <PACKAGE> The type of the importer of this Extent.
 * @param <OWNED> Type of objects owned by this Extent. 
 * @param <IMPORTED> Type of objects the imported by this Extent.
 * 
 */
public class GFunExtent<
	  MANAGER  extends GDataManager<?, ?>  
	, CLASS    extends GCwmClass<?, ?, ?, ?, ?, ?, ?>
	, PACKAGE  extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, OWNED    extends GCwmObject<?, ?, ?, ?>
	, IMPORTED extends GCwmObject<?, ?, ?, ?>
> extends
	GFunCwmPackage<MANAGER, CLASS, PACKAGE, Dependency, Constraint, OWNED, IMPORTED>
implements
	GExtent<MANAGER, CLASS, PACKAGE, OWNED, IMPORTED>
{


	protected GFunExtent( String name, Visibility visibility ) {
		super( name, visibility ); // throws NullPointerException
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}
}