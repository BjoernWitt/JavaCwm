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
package org.ogrehus.jcwm.impl.objectmodel.core;

import org.ogrehus.jcwm.api.foundation.keyindexes.Index;
import org.ogrehus.jcwm.api.foundation.keyindexes.KeyRelationship;
import org.ogrehus.jcwm.api.foundation.keyindexes.UniqueKey;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Interface;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.CwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.relationships.Association;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunCwmClass;

/**
 * A class is a description of a set of objects that share the same attributes, operations, methods, relationships, and semantics.
 *  
 * <p>
 * A class may use a set of interfaces to specify collections of operations it provides to its environment. In the meta-model, a Class describes a set
 * of objects sharing a collection of Features that are common to the set of objects.
 * </p>
 * <p>
 * The purpose of a Class is to declare a collection of Features that fully describe the structure and behavior of objects. Some Classes may not be
 * directly instantiated. These Classes are said to be abstract and exist only for other Classes to inherit and reuse the Features declared by them.
 * No object may be a direct instance of an abstract Class, although an object may be an indirect instance of one through a subclass that is none
 * abstract.
 * </p>
 * <p>
 * A Class acts as the namespace for various kinds of contained elements defined within its scope, including classes, interfaces, and associations
 * (note that this is purely a scoping construction and does not imply anything about aggregation). The contained classes can be used as ordinary
 * classes in the container class. If a class inherits another class, the contents of the ancestor are available to its descendants if the visibility
 * of an element is public or protected. If the visibility is private, then the element is not visible and therefore not available in the descendant.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunCwmClass
extends
	GFunCwmClass<
		  GNamespace<?, ?, ?, ?, ?>
		, CwmPackage
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
		, GFeature<?, ?, ?, ?, ?>
		, Index
	>
implements
	CwmClass
{


	/**
	 * Constructor that creates a new instance of CwmClass by specific parameters.
	 * 
	 * @param name An identifier for the CwmClass within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmClass within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this CwmClass is abstract and can not be instantiated, <code>false</code> otherwise.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 * 
	 */
	protected FunCwmClass( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility, notInstantiable ); // may throw NullPointerException
	}	



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public GNamespace<?, ?, ?, ?, ?> setNamespace( CwmClass cwmClass ) {
		return super.setNamespaceGeneric( cwmClass );
	}



	public GNamespace<?, ?, ?, ?, ?> setNamespace( CwmPackage cwmPackage ) {
		return super.setNamespaceGeneric( cwmPackage );
	}



	public GNamespace<?, ?, ?, ?, ?> setNamespace( Association association ) {
		return super.setNamespaceGeneric( association );
	}



	public Boolean addImporter( CwmPackage cwmPackage ) {
		return super.addImporterGeneric( cwmPackage );
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



//====================================================================================================================================================
// GNamespace capabilities
//====================================================================================================================================================



	public Boolean addOwnedElement( Association ownedAssociation ) {
		return super.addOwnedElementGeneric( ownedAssociation );
	}



	public Boolean addOwnedElement( CwmClass ownedClass ) {
		return super.addOwnedElementGeneric( ownedClass );
	}



	public Boolean addOwnedElement( Interface ownedInterface ) {
		return super.addOwnedElementGeneric( ownedInterface );
	}



//====================================================================================================================================================
// CwmClass capabilities	
//====================================================================================================================================================



	public Boolean addFeature( Attribute feature ) {
		return super.addFeatureGeneric( feature );
	}



	public Boolean addFeature( Operation operation ) {
		return super.addFeatureGeneric( operation );
	}



	public Boolean addOwnedElement( KeyRelationship ownedKeyRelationship ) {
		return super.addOwnedElementGeneric( ownedKeyRelationship );
	}



	public Boolean addOwnedElement( UniqueKey ownedUniqueKey ) {
		return super.addOwnedElementGeneric( ownedUniqueKey );
	}



	public Boolean addIndex( Index index ) {
		return super.addIndexGeneric( index );
	}



 //===================================================================================================================================================
 // Object capabilities
 //===================================================================================================================================================



 	@Override
 	public String toString() {
 		StringBuffer out = new StringBuffer( "[FunCwmClass" );
 		out.append( " |\nextends: " );
 		out.append( super.toString() );
 		out.append( "]" );
 
 		return out.toString();
 	}
}