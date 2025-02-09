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

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.resource.relational.SQLIndex;
import org.ogrehus.jcwm.api.resource.relational.SQLIndexColumn;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.Table;
import org.ogrehus.jcwm.api.resource.relational.TableColumn;
import org.ogrehus.jcwm.impl.foundation.keyindexes.util.generic.GFunIndex;

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
public class FunSQLIndex
extends
	GFunIndex<Schema, Schema, Table, SQLIndexColumn>
implements
	SQLIndex
{


	/**
	 * Factory method that creates a new instance of SQLIndex by specific parameters.
	 * 
	 * <p>
	 * An Index on a table. 
	 * </p>
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * </ul> 
	 * 
	 * @param name An identifier for the SQLIndex within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param isUnique The isUnique attribute is <code>true</code> if the SQLIndex instance guarantees all of its instances have a unique key value.
	 * Must not be <code>null</code>.
	 * 
	 * @param isSorted If <code>true</code>, the SQLIndex instance is maintained in a sorted order. Must not be <code>null</code>.
	 * 
	 * @param isPartitioning If <code>true</code>, this SQLIndex instance is used as a partitioning index. Must not be <code>null</code>.
	 * 
	 * @param spannedClass Identifies the Class instance spanned by the SQLIndex instance. Must not be <code>null</code>.
	 * 
	 * @param filterCondition Which subset of the table is indexed. Must not be <code>null</code>.
	 *
	 * @param isNullable <code>true</code> if the entries in this Index can be a null-value, <code>false</code> otherwise. Must not
	 * be <code>null</code>.
	 * 
	 * @param autoUpdate <code>true</code> if the index is updated automatically, <code>false</code> otherwise. Must not be <code>null</code>.
	 * 
	 * @return A new instance of SQLIndex, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>isUnique</code>, <code>isSorted</code>,
	 * <code>isPartitioning</code>, <code>spannedClass</code>, <code>firstIndexedFeature</code>, <code>filterCondition</code>, <code>isNullable</code>
	 * or <code>autoUpdate</code> is <code>null</code>.
	 * 
	 */
	public FunSQLIndex(
		  String name
		, Boolean isUnique
		, Boolean isSorted
		, Boolean isPartitioning
		, Table table
		, String filterCondition
		, Boolean isNullable
		, Boolean autoUpdate
	) {
		super( name, Visibility._public, isUnique, isSorted, isPartitioning, table );
		setFilterCondition( filterCondition );
		setNullable( isNullable );
		setAutoUpdate( autoUpdate );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Schema setNamespace( Schema schema ) {
		return super.setNamespaceGeneric( schema );
	}



	public Schema setSchema( Schema schema ) {
		return setNamespace( schema );
	}



	public Schema getSchema() {
		return getNamespace();
	}



//====================================================================================================================================================
// Index capabilities
//====================================================================================================================================================



	public Table setSpannedClass( Table table ) {
		return super.setSpannedClassGeneric( table );
	}



	/**
	 * Adds an IndexedFeature instance that associates this Index with one of the StructuralFeature elements of the Index key.
	 * 
	 * <p>
	 * The ordered constraint on this reference can be used to represent the sequential order of elements of the Index key. 
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
	 * @param sqlIndexColumn The instance that associates this Index with one of the StructuralFeature elements of the Index key
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addIndexedFeature( TableColumn column ) {
		return addIndexedFeature( new FunSQLIndexColumn( this, column ) );
	}



	public Boolean addIndexedFeature( String featureName, TableColumn column ) {
		return addIndexedFeature( new FunSQLIndexColumn( featureName, this, column ) );
	}



	public Boolean addIndexedFeature( SQLIndexColumn sqlIndexColumn ) {
		return super.addIndexFeatureGeneric( sqlIndexColumn );
	}



	public SQLIndexColumn getIndexedFeature( String simpleName ) {
		return super.getIndexedFeatureGeneric( SQLIndexColumn.class, simpleName );
	}



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
	public String getFilterCondition() {
		return this.filterCondition;
	}



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
	public String setFilterCondition( String filterCondition ) {
		String old = this.filterCondition;
		this.filterCondition = filterCondition;
		return old;
	}



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
	 * @return <code>true</code> if the entries in this index can be null, <code>false</code> otherwise. The return-value itself can't be
	 * <code>null</code>.
	 * 
	 */
	public Boolean isNullable() {
		return this.isNullable;
	}



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
	public Boolean setNullable( Boolean isNullable ) {
		Boolean old = this.isNullable;
		this.isNullable = isNullable;
		return old;
	}



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
	public Boolean isAutoUpdate() {
		return this.autoUpdate;
	}



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
	public Boolean setAutoUpdate( Boolean autoUpdate ) {
		Boolean old = this.autoUpdate;
		this.autoUpdate = autoUpdate;
		return old; 
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunSQLIndex" );
		if ( this.filterCondition != null ) {
			out.append( " | filterCondition=" + this.filterCondition );  
		}
		if ( this.isNullable != null ) {
			out.append( " | isNullable=" + this.isNullable );  
		}
		if ( this.autoUpdate != null ) {
			out.append( " | autoUpdate=" + this.autoUpdate );  
		}
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}



//====================================================================================================================================================
// Properties
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
	 * @param filterCondition Which subset of the table is indexed. Must not be <code>null</code>.
	 * 
	 * @return Which subset of the table is indexed. Cannot be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>filterCondition</code> was <code>null</code>.
	 * 
	 */
	protected String filterCondition = null;



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
	 * @return Whether the entries in this index can be null, or not. The return-value itself can't be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isNullable</code> was <code>null</code>.
	 * 
	 */
	protected Boolean isNullable = null;



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
	 * @param isNullable <code>true</code> if the index is updated automatically, <code>false</code> otherwise. Must not be <code>null</code>.
	 * 
	 * @return Whether the index is updated automatically, or not. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>autoUpdate</code> was <code>null</code>.
	 * 
	 */
	protected Boolean autoUpdate = null;
}