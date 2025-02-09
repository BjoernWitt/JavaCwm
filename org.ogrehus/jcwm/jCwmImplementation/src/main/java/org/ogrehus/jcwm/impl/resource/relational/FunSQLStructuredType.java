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

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.resource.relational.Column;
import org.ogrehus.jcwm.api.resource.relational.ColumnSet;
import org.ogrehus.jcwm.api.resource.relational.NamedColumnSet;
import org.ogrehus.jcwm.api.resource.relational.SQLDataType;
import org.ogrehus.jcwm.api.resource.relational.SQLIndex;
import org.ogrehus.jcwm.api.resource.relational.SQLStructuredType;
import org.ogrehus.jcwm.api.resource.relational.Schema;
import org.ogrehus.jcwm.api.resource.relational.SQLStructuredAttribute;
import org.ogrehus.jcwm.api.resource.relational.Table;
import org.ogrehus.jcwm.api.resource.relational.View;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunCwmClass;


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
 * is supported. Multiple inheritance of interfaces (as in Java) is not supported. Both distinct and structured types can be used as the data type for
 * columns, SQL variables, a field of a row type, or as attribute of another UDT.
 * </p> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunSQLStructuredType
extends
	GFunCwmClass<Schema, TypeSystem, Dependency, Constraint, GModelElement<?, ?, ?, ?>, SQLStructuredAttribute, SQLIndex>
implements
	SQLStructuredType
{


	/**
	 * Creates a new instance of SQLStructuredType by specific parameters.
	 * 
	 * @param name An identifier for the SQLStructuredType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the SQLStructuredType within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this SQLStructuredType is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 *
	 */
	public FunSQLStructuredType( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility, notInstantiable);
		this.columnSets         = new HashSet<NamedColumnSet<?>>();
		this.referencingColumns = new HashSet<Column>();
	}



	/**
	 * Creates a new instance of SQLStructuredType by specific parameters.
	 * 
	 * <b>Default values:</b>
	 * <ul>
	 * <li><b>visibility</b> - <code>Visibility._public</code>.
	 * <li><b>notInstantiable</b> - <code>false</code> (not abstract). 
	 * </ul>
	 * 
	 * @param name An identifier for the SQLStructuredType within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @return A new instance of SQLStructuredType by specific parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> is <code>null</code>.
	 *
	 */
	public FunSQLStructuredType( String name ) {
		this( name, Visibility._public, Boolean.FALSE );
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



	public Boolean addImporter( TypeSystem typeSystem ) {
		return super.addImporterGeneric( typeSystem );
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



//====================================================================================================================================================
// SQLDataType capabilities
//====================================================================================================================================================



	/**
	 * The number assigned to the data-type by the owning RDBMS.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 * @return The number assigned to the data-type by the owning RDBMS or <code>null</code> if none exists.
	 * 
	 */
	public Integer getTypeNumber() {
		return this.typeNumber;
	}



	/**
	 * The number assigned to the data-type by the owning RDBMS.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @param typeNumber The number assigned to the data-type by the owning RDBMS.
	 * 
	 * @return Old value of the property <code>typeNumber</code> or <code>null</code> if none exists.
	 * 
	 */
	public Integer setTypeNumber( Integer typeNumber ) {
		Integer old = this.typeNumber;
		this.typeNumber = typeNumber;
		return old;
	}



//====================================================================================================================================================
// SQLStructuredType capabilities
//====================================================================================================================================================



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
	public Set<NamedColumnSet<?>> getColumnSets() {
		return this.columnSets;
	}



	public Boolean addColumnSet( Table table ) {
		if ( table != null && this.columnSets.add( table ) ) {
			table.setType( this );

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean addColumnSet( View view ) {
		if ( view != null && this.columnSets.add( view ) ) {
			view.setType( this );

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeColumnSet( NamedColumnSet<?> namedColumnSet ) {
		if ( namedColumnSet == null ) {
			return Boolean.TRUE;
		}

		if ( this.columnSets.remove( namedColumnSet ) ) {
			namedColumnSet.removeType(); // remove bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public <TYPE extends SQLDataType<?, ?>> Boolean addAttribute( String name, TYPE type ) {
		return addFeature( new FunSQLStructuredAttribute( name, type ) );
	}



	public Boolean addFeature( SQLStructuredAttribute attribute ) {
		return super.addFeatureGeneric( attribute );
	}



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
	public Set<Column> getReferencingColumns() {
		return this.referencingColumns;
	}



	public Boolean addReferencingColumn( Column column ) {
		if ( column != null && this.referencingColumns.add( column ) ) {
			column.setType( this );

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeReferencingColumn( Column column ) {
		if ( column == null ) {
			return Boolean.TRUE;
		}

		if ( this.columnSets.remove( column ) ) {
			column.removeReferencedTableType(); // remove bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[FunSQLStructuredType" );
		if ( this.typeNumber != null ) {
			out.append( " | typeNumber=" + this.typeNumber );
		}
		if ( !this.columnSets.isEmpty() ) {
			out.append( " | columnSets(" + this.columnSets.size() + ")={" );
			boolean comma = false;
			for ( ColumnSet<?> columnSet : this.columnSets ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( columnSet.getSimpleName() );
				out.append( "<" + columnSet.getClass().getSimpleName() + ">" );
			}
			out.append( "}" );
		}

		if ( !this.referencingColumns.isEmpty() ) {
			out.append( " | referencingColumns(" + this.referencingColumns.size() + ")={" );
			boolean comma = false;
			for ( Column column : this.referencingColumns ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( column.getSimpleName() );
				out.append( "<" + column.getClass().getSimpleName() + ">" );
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



	/**
	 * A NamedColumnSet created as of this type.
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
	 */
	protected Set<NamedColumnSet<?>> columnSets = null;



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
	 */
	protected Set<Column> referencingColumns = null;



	/**
	 * The number assigned to the data-type by the owning RDBMS.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li> 
	 * </ul>
	 * 
	 * @param typeNumber The number assigned to the data-type by the owning RDBMS.
	 * 
	 * @return Old value of the property <code>typeNumber</code>.
	 * 
	 */
	protected Integer typeNumber = null;
}