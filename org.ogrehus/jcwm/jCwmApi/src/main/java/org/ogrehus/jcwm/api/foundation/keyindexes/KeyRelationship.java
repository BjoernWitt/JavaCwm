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
package org.ogrehus.jcwm.api.foundation.keyindexes;

import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GKeyRelationship;
import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;
import org.ogrehus.jcwm.api.objectmodel.relationships.Association;
import org.ogrehus.jcwm.api.objectmodel.relationships.AssociationEnd;

/**
 * KeyRelationship instances represent relationships between UniqueKey instances and the Class(es) that reference them. This class is intended as a 
 * starting point for the creation of foreign key and other associative relationships.
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation_C_6_2]</b> A KeyRelationship instance must be owned by one and only one Class instance.</li>
 * </ul> 
 * 
 * <p>
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface KeyRelationship
extends
	GKeyRelationship<
		  CwmClass
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, UniqueKey
		, GStructuralFeature<?, ?, ?, ?, ?, ?>
	>
{


	/**
	 * Adds an Attribute instance that participate as (part of) the key of this KeyRelationship instance.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>class</i>		    : Attribute</li>
	 * <li><i>defined by</i> 	: KeyRelationshipFeatures::feature</li> 
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * </ul>
	 * 
	 * @param attribute The new Attribute instance that participate as (part of) the key of this KeyRelationship instance.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addFeature( Attribute attribute );



	/**
	 * Adds an AssociationEnd instance that participate as (part of) the key of this KeyRelationship instance.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : AssociationEnd</li>
	 * <li><i>defined by</i> 	: KeyRelationshipFeatures::feature</li> 
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * </ul>
	 * 
	 * @param associationEnd The new associationEnd instance that participate as (part of) the key of this KeyRelationship. 
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addFeature( AssociationEnd associationEnd );



	/**
	 * Identifies the UniqueKey instance that serves as the primary key for this KeyRelationship instance.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : UniqueKey</li>
	 * <li><i>defined by</i>    : UniqueKeyRelationship::uniqueKey</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * <li><i>inverse</i>       : UniqueKey::keyRelationship</li>
	 * </ul>
	 *
	 * <p>
	 * <b>Constraint</b>:
	 * </p>
	 * <ul>
	 * <li><b>[Foundation_C_6_2]</b> A KeyRelationship instance must be owned by one and only one Class instance.</li>
	 * </ul>
	 * 
	 * @param uniqueKey The new UniqueKey instance that serves as the primary key for this KeyRelationship instance.
	 * 
	 * @return The UniqueKey instance that serves as the primary key for this KeyRelationship instance.
	 * 
	 */
	UniqueKey setUniqueKey( UniqueKey uniqueKey );



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Designates the CwmClass that contains this KeyRelationship. 
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a 
	 * kind of virtual namespace).
	 * </p>
	 * <p>  
	 * The pathname of CwmClass or KeyRelationship names starting from the root package provides a unique designation for every KeyRelationship.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: CwmClass</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: CwmClass::ownedElement</li>
	 * </ul>
	 * 
	 * @param cwmClass The new cwmClass that contains this KeyRelationship.
	 * 
	 * @return The old cwmClass that contains this KeyRelationship before, or <code>null</code> none was assigned.
	 * 
	 */
	CwmClass setNamespace( CwmClass cwmClass );



	/**
	 * Designates the Namespace that contains the KeyRelationship. 
	 * 
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a 
	 * kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>  
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: Association</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Association::ownedElement</li>
	 * </ul>
	 * 
	 * @param association The new namespace that contains this KeyRelationship.
	 * 
	 * @return The namespace that contains this KeyRelationship before, or <code>null</code> none was assigned before. 
	 * 
	 */	    
	CwmClass setNamespace( Association association );



	/**
	 * Adds an importer (Package) to this CwmClass, that will contain this KeyRelationship as imported.
	 * 
	 * @param cwmPackage The specific package, that will contain this KeyRelationship.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> cwmPackage );
}