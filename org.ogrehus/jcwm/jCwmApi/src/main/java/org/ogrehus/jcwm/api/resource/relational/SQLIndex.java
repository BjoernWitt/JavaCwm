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

import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GIndex;

/**
 * An Index on a table.
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface SQLIndex
extends
	GIndex<Schema, Schema, Table, SQLIndexColumn>
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	Schema setNamespace( Schema schema );



	Schema setSchema( Schema schema );



	Schema getSchema();



//====================================================================================================================================================
// Index capabilities
//====================================================================================================================================================



	Table setSpannedClass( Table table );



	/**
	 * Adds an IndexedFeature instance that associates this Index with one of the StructuralFeature elements of the Index's key.
	 * 
	 * <p>
	 * The ordered constraint on this reference can be used to represent the sequential order of elements of the Index's key.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : IndexedFeature</li>
	 * <li><i>defined by</i> 	: IndexedFeatureInfo::indexedFeature</li> 
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * <li><i>inverse</i> 		: IndexedFeature::index</li> 
	 * </ul>
	 * 
	 * @param sqlIndexColumn The instance that associates this Index with one of the StructuralFeature elements of the Index's key
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addIndexedFeature( SQLIndexColumn sqlIndexColumn );




	/**
	 * Creates an IndexedFeature instance by specific parameters and adds it to this Index.
	 * <p>
	 * The IndexedFeature associates this Index with one of the StructuralFeature elements of the Index's key.
	 * </p>
	 * <p>
	 * The ordered constraint on this reference can be used to represent the sequential order of elements of the Index's key.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : IndexedFeature</li>
	 * <li><i>defined by</i> 	: IndexedFeatureInfo::indexedFeature</li> 
	 * <li><i>multiplicity</i> 	: one or more; ordered</li>
	 * <li><i>inverse</i> 		: IndexedFeature::index</li> 
	 * </ul>
	 * 
	 * @param indexedName The name of the new IndexedFeature. Must not be <code>null</code>.
	 * 
	 * @param column The StructuralFeature that will be associated by this SQLIndex. Must not be <code>null</code>.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the following parameter is <code>null</code>: <code>indexedName</code>, 
	 * <code>attribute</code>.
	 * 
	 */
	Boolean addIndexedFeature( String indexedName, TableColumn column );



	Boolean addIndexedFeature( TableColumn column );



	SQLIndexColumn getIndexedFeature( String simpleName );



//====================================================================================================================================================
// SQLIndex capabilities
//====================================================================================================================================================



	/**
	 * Which subset of the table is indexed.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return Which subset of the table is indexed. Cannot be <code>null</code>.
	 * 
	 */
	String getFilterCondition();



	/**
	 * Which subset of the table is indexed.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param filterCondition Which subset of the table is indexed. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>filterCondition</code>. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>filterCondition</code> is <code>null</code>. 
	 * 
	 */
	String setFilterCondition( String filterCondition );



	/**
	 * Defines whether the entries in this index can be null, or not.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the entries in this index can be null, <code>false</code> otherwise. The return-value itself can't 
	 * be <code>null</code>.
	 * 
	 */
	Boolean isNullable();



	/**
	 * Defines whether the entries in this index can be null, or not.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param isNullable <code>true</code> if the entries in this Index can be a null-value, <code>false</code> otherwise. Must not 
	 * be <code>null</code>.
	 * 
	 * @return Old value of the property <code>isNullable</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isNullable</code> was <code>null</code>.
	 * 
	 */
	Boolean setNullable( Boolean isNullable );



	/**
	 * Defines whether the index is updated automatically, or not.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the index is updated automatically, <code>false</code> otherwise. Can't be <code>null</code>.
	 * 
	 */
	Boolean isAutoUpdate();



	/**
	 * Defines whether the index is updated automatically, or not.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param autoUpdate <code>true</code> if the index is updated automatically, <code>false</code> otherwise. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>autoUpdate</code>. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>autoUpdate</code> was <code>null</code>.
	 * 
	 */
	Boolean setAutoUpdate( Boolean autoUpdate );
}