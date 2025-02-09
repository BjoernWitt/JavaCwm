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

import org.ogrehus.jcwm.api.objectmodel.core.util.references.elementownership.OwnedElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.importedelements.Importer;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumn;


/**
 * A cataloged set of columns, which may be Table or View.
 * <p>
 * Note for typed tables: It is assumed that the typed table will own a set of columns conforming to the type they are OF. This set of columns allows 
 * the manipulation of the table by products that ignore this [SQL] extension. It also allows the columns of type REF, to be copied to a column with 
 * a SCOPE reference.
 * </p>
 *
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <COLUMN> The type of Column this ColumnSet contains. 
 * 
 */
public interface NamedColumnSet<
	COLUMN extends GColumn<?, ?>
> extends
	  ColumnSet<COLUMN>
	, OwnedElement<Schema>
	, Importer<Schema>
{

//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	Schema setNamespace( Schema schema );



	Schema getSchema();



	Schema setSchema( Schema schema );



	Boolean addImporter( Schema schema );



//====================================================================================================================================================
// NamedColumnSet capabilities	
//====================================================================================================================================================



	/**
	 * A Trigger that references this NamedColumnSet in its expression.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i> : Trigger</li>
	 * <li><i>defined by</i> : TriggerUsingColumnSet::usingTrigger</li>
	 * <li><i>multiplicity</i> : zero or more</li> 
	 * <li><i>inverse</i> : Trigger::usedColumnSet</li> 
	 * </ul>
	 * 
	 * @return A Collection of Trigger that references this NamedColumnSet in its expression. Can't be <code>null</code> but an empty Collection.
	 * 
	 */
	Set<Trigger> getUsingTriggers();



	Boolean addUsingTrigger( Trigger usingTrigger );



	Boolean removeUsingTrigger( Trigger usingTrigger );



	/**
	 * For typed Tables and Views, reference the base SQLStructuredType.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i> : SQLStructuredType</li>
	 * <li><i>defined by</i> : ColumnSetOfStructuredType::type</li>
	 * <li><i>multiplicity</i> : zero or one</li> 
	 * <li><i>inverse</i> : SQLStructuredType::columnSet</li> 
	 * </ul>
	 * 
	 * @param type The reference of the base SQLStructuredType, for typed Tables and Views.
	 * 
	 * @return Old value of the property <code>type</code>. Can be <code>null</code> if none exists.
	 */
	SQLStructuredType setType( SQLStructuredType type );



	Boolean removeType();



	/**
	 * For typed Tables and Views, reference the base SQLStructuredType.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i> : SQLStructuredType</li>
	 * <li><i>defined by</i> : ColumnSetOfStructuredType::type</li>
	 * <li><i>multiplicity</i> : zero or one</li> 
	 * <li><i>inverse</i> : SQLStructuredType::columnSet</li> 
	 * </ul>
	 * 
	 * @return The reference of the base SQLStructuredType, for typed Tables and Views. Can be <code>null</code> if none exists. 
	 * 
	 */
	SQLStructuredType getType();



	/**
	 * Associates Columns with NamedColumnSets they reference in their OPTIONS clause.
	 * <p>
	 * This NamedColumnSet is referenced in a SCOPE clause of the referenced Column.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i> : Column</li>
	 * <li><i>defined by</i> : ColumnOptionsColumnSet::optionScopeColumn</li>
	 * <li><i>multiplicity</i> : zero or more</li> 
	 * <li><i>inverse</i> : Column::optionScopeColumnSet</li> 
	 * </ul>
	 * 
	 * @return A Collection of Columns that references this NamedColumnSet in the SCOPE clause. Can't be <code>null</code> but an empty Set.
	 * 
	 */
	Set<Column> getOptionScopeColumns();



	Boolean addOptionScopeColumn( Column column );



	Boolean removeOptionScopeColumn( Column column );
}