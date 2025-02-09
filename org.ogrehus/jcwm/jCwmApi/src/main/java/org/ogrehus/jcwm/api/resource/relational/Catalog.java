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
package org.ogrehus.jcwm.api.resource.relational;

import java.util.Set;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.JDBCManager;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;

/**
 * A Catalog is the unit of login and identification. It also identifies the scope of SQL statements: the tables contained in a catalog can be used in
 * a single SQL statement.
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Catalog
extends
	GCwmPackage<
          JDBCManager
        , JDBCManager
        , Catalog
        , Dependency
        , Constraint
        , Schema
        , Schema
    >
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	JDBCManager setNamespace( JDBCManager jdbcManager );



	Boolean addDependency( Dependency dependency );



	Boolean addConstraint( Constraint constraint );



	Boolean addImporter( Catalog catalog );



//====================================================================================================================================================
// Namespace capabilities	
//====================================================================================================================================================



	Boolean addOwnedElement( Schema schma );



	Schema getSchema( String simpleName );



//====================================================================================================================================================
// Package capabilities	
//====================================================================================================================================================



	Boolean addDataManager( JDBCManager jdbcManager );



	/**
	 * Adds an importable Schema to this Package.
	 * 
	 * @param schema The new ModelElement that will enter the extended Namespace of a Package.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImportedElement( Schema schema );



//====================================================================================================================================================
// Catalog capabilities	
//====================================================================================================================================================



	/**
	 * Returns the ownedElements type of Schema.
	 * 
	 * @return All ownedElements that are type of Schema.
	 * 
	 */
	Set<Schema> getSchemas();



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
	String getDefaultCharacterSetName();



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
	String setDefaultCharacterSetName( String defaultCharacterSetName );	



	/**
	 * The name of the default collation sequence used to sort the data values in the column. 
	 * <p>
	 * This applies only to columns whose data-type is a form of character string. 
	 * </p>
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
	 * @return The name of the default collation sequence used to sort the data  values in the column. Can't be <code>null</code>. 
	 *
	 */ 
	String getDefaultCollationName();



	/**
	 * The name of the default collation sequence used to sort the data values in the column. 
	 * <p>
	 * This applies only to columns whose data-type is a form of character string.
	 * </p>
     * <p>
     * A collation is a set of rules for comparing character-string (CHAR/VARCHAR/CLOB) values. Other terms for the same thing are sort order and 
     * linguistic comparison, but "collation" is the official SQL:1999 word. Collations are especially important in search conditions or sorts.
     * </p>
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
	String setDefaultCollationName( String defaultCollationName );
}