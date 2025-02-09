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
package org.ogrehus.jcwm.impl.foundation.typemapping;

import org.ogrehus.jcwm.api.foundation.typemapping.TypeMapping;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.impl.foundation.typemapping.util.generic.GFunTypeMapping;

public class FunTypeMapping
extends
	GFunTypeMapping<
		  TypeSystem
		, GCwmPackage<?, ?, ?, ?, ?, ?, TypeMapping>
		, GDependency<?, ?, ?, ?, TypeMapping>
		, GConstraint<?, ?, ?, ?, TypeMapping>
	>
implements
	TypeMapping
{


	/**
	 * Factory method that creates a new instance of TypeMapping by specific parameters.
	 * 
	 * <p>
	 * TypeMapping instances permit the creation of mappings between data types defined within different environments and are used to indicate data
	 * type compatibilities that permit direct assignment of values from one environment (the source type) into equivalent values in another
	 * environment (the target type).
	 * </p>
	 * 
	 * @param name An identifier for the TypeMapping within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the TypeMapping within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param isBestMatch <code>true</code> if this TypeMapping instance represents the best available mapping between a pair of data types in
	 * different software systems. Must not be <code>null</code>.
	 * 
	 * @param isLossy <code>true</code> if this TypeMapping instance may result in a data conversion error if the source data is within certain
	 * ranges. For example, storing a 32-bit unsigned integer value into a 16-bit unsigned integer container will result in a data conversion error
	 * only when the source data has a value greater than 65535. Must not be <code>null</code>.
	 * 
	 * @param sourceType Identifies the GClassifier instance that is the source of information exchange. Must not be <code>null</code>.
	 *
	 * @param targetType Identifies the GClassifier instance that is the target of information exchange. Must not be <code>null</code>.
	 * 
	 * @return A new instance of TypeMapping, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>isBestMatch</code>,
	 * <code>isLossy</code>, <code>sourceType</code> or <code>targetType</code> is <code>null</code>.
	 * 
	 */
	public FunTypeMapping(
		  String name
		, Visibility visibility
		, Boolean isBestMatch
		, Boolean isLossy
		, GClassifier<?, ?, ?, ?, ?, ?> sourceType
		, GClassifier<?, ?, ?, ?, ?, ?> targetType
	) {
		super( name, visibility, isBestMatch, isLossy, sourceType, targetType );
	}



	public TypeSystem setNamespace( TypeSystem typeSystem ) {
		if ( typeSystem != null && typeSystem.equals( this.namespace ) ) {
			return typeSystem; // no changes, cause its the same namespace
		}

		TypeSystem old = this.namespace; // return value 
		if ( old != null  ) {
			old.removeOwnedElement( this );
		}

		this.namespace = typeSystem;
		if ( this.namespace != null ) {
			if ( !this.namespace.getOwnedElements().contains( this ) ) {
				// organize the bidirectional reference of new namespace 
				//------------------------------------------------------------------------------------------------------
				this.namespace.addOwnedElement( this );
			}
		}

		return old;
	}
}