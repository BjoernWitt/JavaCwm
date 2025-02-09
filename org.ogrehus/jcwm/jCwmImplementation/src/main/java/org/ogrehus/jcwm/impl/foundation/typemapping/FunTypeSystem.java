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

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareSystem;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeMapping;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.resource.relational.SQLDistinctType;
import org.ogrehus.jcwm.api.resource.relational.SQLSimpleType;
import org.ogrehus.jcwm.api.resource.relational.SQLStructuredType;

import org.ogrehus.jcwm.impl.foundation.typemapping.util.generic.GFunTypeSystem;


public class FunTypeSystem
extends
	GFunTypeSystem<
		  GDataManager<?, ?> // can be CwmManager, JDBCManager or any other
		, GNamespace< ?, ?, ?, ?, ?> // can be GClassifier and TypeSystem common super is GNamespace
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, GModelElement<?, ?, ?, ?>
		, GClassifier<?, ?, ?, ?, ?, ?>
	>
implements
	TypeSystem 
{


	/**
	 * Factory method that creates a new instance of TypeSystem by specific parameters.
	 * 
	 * @param name An identifier for the TypeSystem within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the TypeSystem within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param version A string describing the version of the TypeSystem represented. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>version</code>
	 * is <code>null</code>.
	 * 
	 */
	public FunTypeSystem( String name, Visibility visibility, String version ) {
		super( name, visibility, version );

		this.softwareSystems = new HashSet<SoftwareSystem>();
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public GNamespace<?, ?, ?, ?, ?> setNamespace( GNamespace<?, ?, ?, ?, ?> namespace ) {
		return super.setNamespaceGeneric( namespace );
	}

    
	public Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer ) {
		return super.addImporterGeneric( importer );		
	}



//====================================================================================================================================================
// GNamspace capabilities (determined by Package)
//====================================================================================================================================================



	public Boolean addOwnedElement( TypeMapping ownedMapping ) {
		return super.addOwnedElementGeneric( ownedMapping );
	}



	public Boolean addOwnedElement( DataType dataType ) {
		return super.addOwnedElementGeneric( dataType );
	}



	public Boolean addOwnedElement( SQLDistinctType sqlDistinctType ) {
		return super.addOwnedElementGeneric( sqlDistinctType );
	}



	public Boolean addOwnedElement( SQLSimpleType sqlSimpleType ) {
		return super.addOwnedElementGeneric( sqlSimpleType );
	}



	public Boolean addOwnedElement( SQLStructuredType sqlStructuredType ) {
		return super.addOwnedElementGeneric( sqlStructuredType );
	}



//====================================================================================================================================================
// GCwmPacakge capabilities
//====================================================================================================================================================



	public Boolean addImportedElement( GClassifier<?, ?, ?, ?, ?, ?> importedElement ) {
		if ( importedElement != null && this.importedElements.add( importedElement ) ) {
			invokeByReflection( importedElement, "addImporter", this );
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean addImportedElement( SQLStructuredType structuredType ) {
		return super.addImportedElementGeneric( structuredType );
	}



	public Boolean addImportedElement( SQLDistinctType distinctType ) {
		return super.addImportedElementGeneric( distinctType );
	}



//====================================================================================================================================================
// TypeSystem capabilities
//====================================================================================================================================================



	public Set<SoftwareSystem> getSupportingSystems() {
		return this.softwareSystems;
	}



	public Boolean addSupportingSystem( SoftwareSystem softwareSystem ) {
		if ( softwareSystem != null && this.softwareSystems.add( softwareSystem ) ) {
			if ( !softwareSystem.getTypespaces().contains( this ) ) {
				softwareSystem.addTypespace( this );
			}

			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



	public Boolean removeSupportingSystem( SoftwareSystem softwareSystem ) {
		if ( softwareSystem != null && this.softwareSystems.remove( softwareSystem ) ) {
			softwareSystem.removeTypeSystem( this );
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Set<SQLSimpleType> getSQLSimpleTypes( Integer typeNumber ) {
		Set<SQLSimpleType> simpleTypes = new HashSet<SQLSimpleType>();
		if ( typeNumber == null ) {
			return simpleTypes;
		}
		for ( SQLSimpleType simpleType : getOwnedElementsByType( SQLSimpleType.class ) ) {
			if ( typeNumber == simpleType.getTypeNumber() ) {
				simpleTypes.add( simpleType );
			}
		}
		
		return simpleTypes;
	}



	public SQLSimpleType getSQLSimpleType( Integer typeNumber ) {
		Set<SQLSimpleType> simpleTypes = getSQLSimpleTypes( typeNumber );
		if ( simpleTypes.size() == 0 ) {
			return null; // no specific simpleType avail
		}
		return simpleTypes.iterator().next();// return first found in set.
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunTypeSystem" );
		if ( !this.softwareSystems.isEmpty() ) {
			out.append( " | softwareSystems(" + this.softwareSystems.size() + ")={" );
			boolean comma = false;
			for ( SoftwareSystem system : this.softwareSystems ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( system.getSimpleName() );
				out.append( "<" + system.getClass().getSimpleName() + ">" );
			}
			out.append( "}" );
		}
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		return out.toString();
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	protected Set<SoftwareSystem> softwareSystems = null;
}