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
package org.ogrehus.jcwm.impl.foundation.keyindexes;

import org.ogrehus.jcwm.api.foundation.keyindexes.Index;
import org.ogrehus.jcwm.api.foundation.keyindexes.IndexedFeature;
import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;
import org.ogrehus.jcwm.api.objectmodel.relationships.AssociationEnd;
import org.ogrehus.jcwm.impl.foundation.keyindexes.util.generic.GFunIndexedFeature;


public class  FunIndexedFeature
extends 
	GFunIndexedFeature<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Index
		, GStructuralFeature<?, ?, ?, ?, ?, ?>
	>
implements
    IndexedFeature
{


	/**
	 * Creates a new instance of IndexedFeature by specific parameters.
	 * 
	 * @param name An identifier for the IndexedFeature within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the IndexedFeature within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param index The Index instance for which this IndexedFeature instance is relevant. Must not be <code>null</code>.
	 * 
	 * @param attribute Identifies the StructuralFeature instance for which this IndexedFeature instance is relevant.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>index</code>
	 * is <code>null</code>.
	 * 
	 */
	protected FunIndexedFeature( String name, Visibility visibility, Attribute attribute, Index index ) {
		super( name, visibility, index, attribute ); // may throw NullPointerException
	}



	/**
	 * Creates a new instance of IndexedFeature by specific parameters.
	 * 
	 * @param name An identifier for the IndexedFeature within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the IndexedFeature within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param index The Index instance for which this IndexedFeature instance is relevant. Must not be <code>null</code>.
	 * 
	 * @param associationEnd Identifies the StructuralFeature instance for which this IndexedFeature instance is relevant.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>index</code>
	 * is <code>null</code>.
	 * 
	 */
	protected FunIndexedFeature( String name, Visibility visibility, AssociationEnd associationEnd, Index index ) {
		super( name, visibility, index, associationEnd ); // may throw NullPointerException
	}



//====================================================================================================================================================
// IndexedFeature capabilities
//====================================================================================================================================================



	public GStructuralFeature<?, ?, ?, ?, ?, ?> setFeature( Attribute attribute ) {
		return super.setFeatureGeneric( attribute );
	}




	public GStructuralFeature<?, ?, ?, ?, ?, ?> setFeature( AssociationEnd associationEnd ) {
		return super.setFeatureGeneric( associationEnd );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public GNamespace<?, ?, ?, ?, ?> setNamespace( GNamespace<?, ?, ?, ?, ?> namespace ) {
		return super.setNamespaceGeneric( namespace );
	}



	public Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> cwmPackage ) {
		return super.addImporterGeneric( cwmPackage );
	}
}