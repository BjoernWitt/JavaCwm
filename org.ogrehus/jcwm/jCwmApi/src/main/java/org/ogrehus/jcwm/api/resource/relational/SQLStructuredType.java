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

import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;


/**
 * A data-type defined as a Distinct Type, per [SQL] standard.
 * 
 * <p>
 * SQL:1999 specifies the SQL:1999 object model. It adds User Defined Types (UDTs) to SQL. There are two types of UDTs:
 * </p>
 * <ul> 
 * <li><b>distinct types</b> - A distinct type is based on a built-in data type, such as INTEGER, but whose values cannot be directly mixed in 
 * operation with that built-in type.</li>
 * <li><b>structured types</b> - An internal structure such as an address type that might have street, state, and postal code attributes as part of 
 * the structure.</li>
 * </ul>
 * <p>
 * Structured types can be given specific user-defined functions and participate in type hierarchies with other structured types. Single inheritance 
 * is supported. Multiple inheritance of interfaces (as in Java) is not supported. Both distinct and structured types can be used as the data type 
 * for columns, SQL variables, a field of a row type, or as attribute of another UDT.
 * </p> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface SQLStructuredType
extends
	  GCwmClass<Schema, TypeSystem, Dependency, Constraint, GModelElement<?, ?, ?, ?>, SQLStructuredAttribute, SQLIndex>
	, SQLDataType<Schema, SQLStructuredAttribute>
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	Schema setNamespace( Schema schema );



	Schema getSchema();



	Schema setSchema( Schema schema );



	Boolean addImporter( TypeSystem typeSystem );



	Boolean addDependency( Dependency dependency );



	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// SQLStructuredType capabilities	
//====================================================================================================================================================



	Boolean addFeature( SQLStructuredAttribute attribute );



	<TYPE extends SQLDataType<?, ?>> Boolean addAttribute( String name, TYPE type );



	/**
	 * NamedColumnSet created as of this type.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : NamedColumnSet</li>
	 * <li><i>defined by</i> : ColumnSetOfStructuredType::columnSet</li> 
	 * <li><i>multiplicity</i> : zero or more</li>
	 * <li><i>inverse</i> : NamedColumnSet::type</li>
	 * </ul>
	 *   
	 * @return Set of NamedColumnSet created as of this type. Can't be <code>null</code> but an empty Set.
	 * 
	 */
	Set<NamedColumnSet<?>> getColumnSets();



	Boolean addColumnSet( Table table );



	Boolean addColumnSet( View view );



	Boolean removeColumnSet( NamedColumnSet<?> namedColumnSet );


	
	/**
	 * Associates Columns of a StructuredType with the Type they reference in the REF clause.
	 * 
	 * <p>
	 * Reference a column of an SQLStructuredType (otherType) that is created with a REF clause referencing this SQLStructuredType (thisType).
	 * </p>
	 *  
	 * <p>
	 * Note that in general, otherType and thisType are two different instances of SQLStructuredType.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : Column</li>
	 * <li><i>defined by</i> : ColumnRefStructuredType::referencingColumn</li> 
	 * <li><i>multiplicity</i> : zero or more</li>
	 * <li><i>inverse</i> : Column::referencedTableType</li>
	 * </ul>
	 *   
	 * @return Set of associated Columns of a StructuredType with the Type they reference in the REF clause.
	 * 
	 */
	Set<Column> getReferencingColumns();



	Boolean addReferencingColumn( Column column );



	Boolean removeReferencingColumn( Column column );
}