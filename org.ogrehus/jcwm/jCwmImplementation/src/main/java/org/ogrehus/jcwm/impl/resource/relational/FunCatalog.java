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
package org.ogrehus.jcwm.impl.resource.relational;

import java.util.Set;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCManager;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.Catalog;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunCwmPackage;

/**
 * A Catalog is the unit of login and identification. It also identifies the scope of SQL statements: the tables 
 * contained in a catalog can be used in a single SQL statement.
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunCatalog
extends
	GFunCwmPackage<
		  JDBCManager
		, JDBCManager
		, Catalog
		, Dependency
		, Constraint
		, Schema
		, Schema
	>
implements
	Catalog
{

	/**
	 * Creates a new instance of Catalog by specific parameters.
	 * <p>
	 * A Catalog is the unit of logon and identification. It also identifies the scope of SQL statements: the tables contained in a catalog can be
	 * used in a single SQL statement.
	 * </p>
	 * 
	 * @param name An identifier for the Catalog within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Catalog within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param defaultCharacterSetName The name of the default character set used for the values in the column. Must not be <code>null</code>.
	 * 
	 * @param defaultCollationName The name of the default collation sequence used to sort the data values in the column. This applies only to columns
	 * whose data-type is a form of character string. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>,
	 * <code>defaultCharacterSetName</code>, <code>defaultCollationName</code>  is <code>null</code>.
	 * 
	 */
	public FunCatalog( 
		  String name 
		, Visibility visibility
		, String defaultCharacterSetName
		, String defaultCollationName 
	) {
		super( name, visibility );
		this.setDefaultCharacterSetName( defaultCharacterSetName );
		this.setDefaultCollationName( defaultCollationName );
	}


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public JDBCManager setNamespace( JDBCManager jdbcManager ) {
		return super.setNamespaceGeneric( jdbcManager );
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



	public Boolean addImporter( Catalog catalog ) {
		return super.addImporterGeneric( catalog );
	}



//====================================================================================================================================================
// Namespace capabilities
//====================================================================================================================================================



	public Boolean addOwnedElement( Schema schma ) {
		return super.addOwnedElementGeneric( schma );
	}



	public Schema getSchema( String simpleName ) {
		// generic super method uses qualified name!
		for ( Schema schema : this.ownedElements ) {
			if ( schema.getSimpleName().equals( simpleName ) ) {
				return schema;
			}
		}
		return null;
	}



//====================================================================================================================================================
// Package capabilities
//====================================================================================================================================================



	public Boolean addDataManager( JDBCManager jdbcManager ) {
		return super.addDataManagerGeneric( jdbcManager );
	}



	/**
	 * Adds an importable Schema to this Package.
	 * 
	 * @param schema The new ModelElement that will enter the extended Namespace of a Package.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addImportedElement( Schema schema ) {
		return super.addImportedElementGeneric( schema );
	}



//====================================================================================================================================================
// Catalog capabilities
//====================================================================================================================================================



	public Set<Schema> getSchemas() {
		return getOwnedElements();
	}



	/**
	 * The name of the default character set used for the values in the column.
	 * 
	 * <p>
	 * This field applies only to columns whose data-type is a character string.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return The name of the default character set used for the values in the column. Can't be <code>null</code>.
	 *
	 */
	public String getDefaultCharacterSetName() {
		return this.defaultCharacterSetName;
	}



	/**
	 * The name of the default character set used for the values in the column.
	 * 
	 * <p>
	 * This field applies only to columns whose data-type is a character string.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param defaultCharacterSetName The name of the default character set used for the values in the column. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>defaultCharacterSetName</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if the parameter <code>defaultCharacterSetName</code> is <code>null</code>.
	 *
	 */
	public String setDefaultCharacterSetName( String defaultCharacterSetName ) {
		if ( defaultCharacterSetName == null ) {
			throw new NullPointerException( "The parameter: defaultCharacterSetName must not be null." );
		}
		String old = this.defaultCharacterSetName;
		this.defaultCharacterSetName = defaultCharacterSetName;

		return old;
	}



	/**
	 * The name of the default collation sequence used to sort the data values in the column.
	 * <p>
	 * This applies only to columns whose data-type is a form of character string.
	 * </p>
	 * 
	 * <p>
	 * A collation is a set of rules for comparing character-string (CHAR/VARCHAR/CLOB) values. Other terms for the same thing are sort order and
	 * linguistic comparison, but "collation" is the official SQL:1999 word. Collations are especially important in search conditions or sorts.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return The name of the default collation sequence used to sort the data values in the column. Can't be <code>null</code>.
	 *
	 */
	public String getDefaultCollationName() {
		return this.defaultCollationName;
	}



	/**
	 * The name of the default collation sequence used to sort the data values in the column. 
	 * <p>
	 * This applies only to columns whose data-type is a form of character string.
	 * </p>
	 * 
	 * <p>
	 * A collation is a set of rules for comparing character-string (CHAR/VARCHAR/CLOB) values. Other terms for the same thing are sort order and
	 * linguistic comparison, but "collation" is the official SQL:1999 word. Collations are especially important in search conditions or sorts.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param defaultCollationName The name of the default collation sequence used to sort the data values in the column. Can't be <code>null</code>.
	 * 
	 * @return The old value of the property <code>defaultCollationName</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if the parameter <code>defaultCollationName</code> is <code>null</code>. 
	 * 
	 */
	public String setDefaultCollationName( String defaultCollationName ) {
		if ( defaultCollationName == null ) {
			throw new NullPointerException( "The parameter: defaultCollationName must not be null." );
		}
		String old = this.defaultCollationName;
		this.defaultCollationName = defaultCollationName;

		return old;
	}



//======================================================================================================================
// Object capabilities
//======================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunCatalog" );
		if ( this.defaultCharacterSetName != null ) {
			out.append( " | defaultCharacterSetName=" + this.defaultCharacterSetName );
		}
		if ( this.defaultCollationName != null ) {
			out.append( " | defaultCollationName=" + this.defaultCollationName );
		}
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		return out.toString();
	}



//======================================================================================================================
// Properties
//======================================================================================================================


	/**
	 * The name of the default character set used for the values in the column.
	 * 
	 * <p>
	 * This field applies only to columns whose datatype is a character string.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 *
	 */
	protected String defaultCharacterSetName = null;



	/**
	 * The name of the default collation sequence used to sort the data values 
	 * in the column. 
	 * <p>
	 * This applies only to columns whose datatype is a form of character 
	 * string.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 *
	 */
	protected String defaultCollationName = null;
}