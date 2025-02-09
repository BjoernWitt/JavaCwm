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
package org.ogrehus.jcwm.api.foundation.typemapping;

import java.util.Set;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareSystem;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;
import org.ogrehus.jcwm.api.foundation.typemapping.util.generic.GTypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.resource.relational.SQLDistinctType;
import org.ogrehus.jcwm.api.resource.relational.SQLSimpleType;
import org.ogrehus.jcwm.api.resource.relational.SQLStructuredType;


/**
 * TypeSystem.java
 * <p>
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @see org.ogrehus.jcwm.api.foundation.typemapping.util.generic.GTypeSystem
 *
 */
public interface TypeSystem
extends
	GTypeSystem<
		  GDataManager<?, ?> // can be CwmManager, JDBCManager or any other
		, GNamespace< ?, ?, ?, ?, ?> // can be GClassifier and TypeSystem common super is GNamespace
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, GModelElement<? , ?, ?, ?>
		, GClassifier<?, ?, ?, ?, ?, ?>
	>
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the ModelElement. Every Model Element except a root element must belong to exactly one Namespace or
	 * else be a composite part of another ModelElement (which is a kind of virtual namespace).
	 * <p>
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: Namespace</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Namespace::ownedElement</li>
	 * </ul>
	 * 
	 * @param namespace The new namespace that contains this ModelElement.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */
	GNamespace< ?, ?, ?, ?, ?> setNamespace( GNamespace< ?, ?, ?, ?, ?> namespace );



	/**
	 * Adds an importer (Package) to this ModelElement, that will contain this ModelElement as imported.
	 * 
	 * @param importer The specific package, that will contain this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer );



//====================================================================================================================================================
// GNamespace capabilities	
//====================================================================================================================================================



	/**
	 * Adds a value to the reference <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this namespace to the new ownedElement for inverse traversal.
	 * </p> 
	 * 
	 * @param dataType Adds an owning element to the collection of <code>ownedElement</code>s.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( DataType dataType );



	/**
	 * Adds a value to the reference <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this namespace to the new ownedElement for inverse traversal.
	 * </p> 
	 * 
	 * @param sqlDistinctType Adds an owning element to the collection of <code>ownedElement</code>s.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( SQLDistinctType sqlDistinctType );



	/**
	 * Adds a value to the reference <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this namespace to the new ownedElement for inverse traversal.
	 * </p> 
	 * 
	 * @param sqlSimpleType Adds an owning element to the collection of <code>ownedElement</code>s.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( SQLSimpleType sqlSimpleType );



	/**
	 * Adds a value to the reference <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this namespace to the new ownedElement for inverse traversal.
	 * </p> 
	 * 
	 * @param sqlStructuredType Adds an owning element to the collection of <code>ownedElement</code>s.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( SQLStructuredType sqlStructuredType );



	/**
	 * Adds a value to the reference <code>ownedElement</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the element
	 * is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this namespace to the new ownedElement for inverse traversal.
	 * </p> 
	 * 
	 * @param ownedMapping Adds an owning element to the collection of <code>ownedElement</code>s.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */
	Boolean addOwnedElement( TypeMapping ownedMapping );



//====================================================================================================================================================
// GCwmPackage capabilities
//====================================================================================================================================================



	/**
	 * Adds a SQLStructuredType to this TypeSystem.
	 * 
	 * @param structuredType A SQLStructuredType that will be imported in this GCwmPackage.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addImportedElement( SQLStructuredType structuredType );	



	/**
	 * Adds a SQLDistinctType to this TypeSystem.
	 * 
	 * @param distinctType A SQLDistinctType that will be imported in this GCwmPackage.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addImportedElement( SQLDistinctType distinctType );	



//======================================================================================================================
// TypeSystem capabilities	
//======================================================================================================================	



	/**
	 * Returns the first matching SQLSimpleType in the Set of owned SQLSimpleTypes by specific <code>typeNumber</code>.
	 * 
	 * @param typeNumber The specific number to determine the SQLSimpleType.
	 * 
	 * @return The first found matching SQlSimpleType by typeNumber if one is avail, <code>null</code> otherwise. 
	 * 
	 */
	SQLSimpleType getSQLSimpleType( Integer typeNumber );



	Set<SQLSimpleType> getSQLSimpleTypes( Integer typeNumber );



	/**
	 * Returns an imported SqlStrucuredType of this Type System.
	 * 
	 * @param qualifiedName The qualified name of the specific SqlStrucuredType in this Type System.
	 * 
	 * @return The matching SqlStrucuredType by qualifiedName if one is avail, <code>null</code> otherwise. 
	 * 
	 */
//	SQLSimpleType getSQLStructuredType( String qualifiedName );



	Set<SoftwareSystem> getSupportingSystems();



	Boolean addSupportingSystem( SoftwareSystem softwareSystem );



	Boolean removeSupportingSystem( SoftwareSystem softwareSystem );
}
