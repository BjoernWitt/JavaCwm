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

import org.ogrehus.jcwm.api.objectmodel.relationships.Association;
import org.ogrehus.jcwm.api.objectmodel.relationships.AssociationEnd;
import org.ogrehus.jcwm.api.foundation.keyindexes.KeyRelationship;
import org.ogrehus.jcwm.api.foundation.keyindexes.UniqueKey;
import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;

import org.ogrehus.jcwm.impl.foundation.keyindexes.util.generic.*;

/**
 * KeyRelationship instances represent relationships between UniqueKey instances and the Class(es) that reference them. 
 * 
 * <p>
 * This class is intended as a starting point for the creation of �foreign key� and other associative relationships.
 * </p>
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation_C_6_2]</b> A KeyRelationship instance must be owned by one and only one Class instance.</li>
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
public class FunKeyRelationship
extends 
	GFunKeyRelationship<
		  CwmClass
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, UniqueKey
		, GStructuralFeature<?, ?, ?, ?, ?, ?>
	>
implements
	KeyRelationship
{


	/**
	 * Factory method that creates a new instance of KeyRelationship by specific parameters.
	 * 
	 * <p>
	 * KeyRelationship instances represent relationships between UniqueKey instances and the Class(es) that reference them. This class is intended as
	 * a starting point for the creation of �foreign key� and other associative relationships.
	 * </p>
	 * 
	 * @param name An identifier for the KeyRelationship within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the KeyRelationship within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param uniqueKey Identifies the UniqueKey instance that serves as the �primary key� for this KeyRelationship instance.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>firstFeature</code> or
	 * <code>uniqueKey</code> is <code>null</code>.
	 * 
	 */
	protected FunKeyRelationship( String name, Visibility visibility, UniqueKey uniqueKey ) {
		super( name, visibility, uniqueKey ); // may throw NullPointerException
	}



//====================================================================================================================================================
// KeyRelationship capabilities
//====================================================================================================================================================



	public Boolean addFeature( Attribute attribute ) {
		return super.addFeatureGeneric( attribute );
	}



	public Boolean addFeature( AssociationEnd associationEnd ) {
		return super.addFeatureGeneric( associationEnd );
	}



	public UniqueKey setUniqueKey( UniqueKey uniqueKey ) {
		return super.setUniqueKeyGeneric( uniqueKey );
	}



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	public CwmClass setNamespace( CwmClass cwmClass ) {
		return super.setNamespaceGeneric( cwmClass );
	}



	public CwmClass setNamespace( Association association ) {
		return super.setNamespaceGeneric( association );
	}



	public Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> cwmPackage ) {
		return super.addImporterGeneric( cwmPackage );
	}
}