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
package org.ogrehus.jcwm.impl.resource.relational.util.generic;

import org.ogrehus.jcwm.api.resource.relational.Catalog;
import org.ogrehus.jcwm.api.resource.relational.NamedColumnSet;
import org.ogrehus.jcwm.api.resource.relational.SQLDataType;
import org.ogrehus.jcwm.api.resource.relational.SQLDistinctType;
import org.ogrehus.jcwm.api.resource.relational.SQLSimpleType;
import org.ogrehus.jcwm.api.resource.relational.SQLStructuredType;
import org.ogrehus.jcwm.api.resource.relational.util.Nullable;
import org.ogrehus.jcwm.api.resource.relational.util.generic.GColumn;
import org.ogrehus.jcwm.api.resource.relational.util.types.SqlTypeCharacters;
import org.ogrehus.jcwm.api.resource.relational.util.types.SqlTypeDateTime;
import org.ogrehus.jcwm.api.resource.relational.util.types.SqlTypeNumeric;
import org.ogrehus.jcwm.api.resource.relational.util.types.SqlTypeScaled;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Changeable;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunAttribute;


/**
 * A column in a result set, a view, a table, or an SQLStructuredType.
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation-C-3]</b> The scale attribute is valid only if the precision attribute is specified.</li>
 * </ul> 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <CONSTRAINT> The type of the Constraint under which this Column is.
 * @param <OWNER> The type of the Classifier that owns this Column.
 * 
 */
public class GFunColumn<
	  CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWNER      extends GCwmClass<?, ?, ?, ?, ?, ?, ?> // remind SQLStructuredType doesn't inherit from GColumnSet
>extends
	  GFunAttribute<GNamespace<?,?,?,?,?>, Catalog, Dependency, CONSTRAINT, OWNER, SQLDataType<?, ?>>
implements
	GColumn<CONSTRAINT, OWNER>
{


	/**
	 * Creates a new instance of FunBehavioralFeature by specific parameters.
	 * 
	 * @param name An identifier for the FunStructuralFeature within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunStructuralFeature within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param changeability The changeability of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @param type The new Classifier describing the type of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code> or
	 * <code>type</code> is <code>null</code>.
	 * 
	 */
	protected <TYPE extends SQLDataType<?, ?>> GFunColumn( String name, TYPE type ) {
		super( name, Visibility._public, Changeable.getDefault(), type ); // may throw NullPointerException
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addImporter( Catalog catalog ) {
		return super.addImporterGeneric( catalog );
	}



//====================================================================================================================================================
// Column capabilities
//====================================================================================================================================================



	public String getCharacterSetName() {
		return this.characterSetName;
	}



	public String getCollationName() {
		return this.collationName;
	}



	public Nullable getNullable() {
		return this.isNullable;
	}



	public NamedColumnSet<?> getOptionScopeColumnSet() {
		return this.optionScopeColumnSet;
	}



	public Integer getLength() {
		if ( this.length == null ) {
			if ( this.type instanceof SqlTypeCharacters ) {
				SqlTypeCharacters characterType = (SqlTypeCharacters)this.type;
				if ( characterType.getCharacterOctetLength() != null ) {
					return characterType.getCharacterOctetLength();
				}

				return characterType.getCharacterMaximumLength();
			}
		}

		return this.length;		
	}



	public Integer getPrecision() {
		if ( this.precision == null ) {
			if ( this.type instanceof SqlTypeNumeric ) {
				return ((SqlTypeNumeric)this.type).getNumericPrecision();
			}
			if ( this.type instanceof SqlTypeDateTime ) {
				SqlTypeDateTime dateType = (SqlTypeDateTime)this.type;

				return dateType.getDateTimePrecision();
			}
		}

		return this.precision;
	}



	public Integer getScale() {
		if ( this.scale == null ) {
			if ( this.type instanceof SqlTypeScaled ) {
				return Integer.valueOf( ((SqlTypeScaled)this.type).getNumericScale() );
			}
		}
		return this.scale;
	}



	public SQLStructuredType getReferencedTableType() {
		return this.referencedTableType;
	}



	public String setCharacterSetName( String characterSetName ) {
		String old = this.characterSetName;
		this.characterSetName = characterSetName;

		return old;
	}



	public String setCollationName( String collationName ) {
		String old = this.collationName;
		this.collationName = collationName;

		return old;
	}



	public Integer setLength( Integer length ) {
		Integer old = this.length;
		this.length = length;

		return old;
	}



	public Nullable setNullable( Nullable nullable ) {
		Nullable old = this.isNullable;
		this.isNullable = nullable;

		return old;
	}



	public NamedColumnSet<?> setOptionScopeColumnSet( NamedColumnSet<?> namedColumnSet ) {
		if ( namedColumnSet != null && namedColumnSet.equals( this.optionScopeColumnSet ) ) {
			return namedColumnSet; // no changes, cause its the same namedColumnSet
		}
		NamedColumnSet<?>  old = this.optionScopeColumnSet; // return value 
		if ( old != null  ) {
			old.removeOptionScopeColumn( this );
		}

		this.optionScopeColumnSet = namedColumnSet;
		if ( this.optionScopeColumnSet != null ) {
			if ( !this.optionScopeColumnSet.getOptionScopeColumns().contains( this ) ) {
				this.optionScopeColumnSet.addOptionScopeColumn( this );// organize the bidirectional reference
			}
		}

		return old;
	}



	public Boolean removeOptionScopeColumnSet() {
		if ( this.optionScopeColumnSet == null ) {
			return Boolean.TRUE;
		}
		NamedColumnSet<?> old = this.optionScopeColumnSet;
		this.optionScopeColumnSet = null;

		return old.removeOptionScopeColumn( this );
	}



	public SQLStructuredType setReferencedTableType( SQLStructuredType sqlStructuredType ) {
		if ( sqlStructuredType != null && sqlStructuredType.equals( this.referencedTableType ) ) {
			return sqlStructuredType; // no changes, cause its the same sqlStructuredType
		}

		SQLStructuredType  old = this.referencedTableType; // return value 
		if ( old != null  ) {
			old.removeReferencingColumn( this );
		}

		this.referencedTableType = sqlStructuredType;
		if ( this.referencedTableType != null ) {
			if ( !this.referencedTableType.getReferencingColumns().contains( this ) ) {
				this.referencedTableType.addReferencingColumn( this );// organize the bidirectional reference
			}
		}

		return old;
	}



	public Boolean removeReferencedTableType() {
		if ( this.referencedTableType == null ) {
			return Boolean.TRUE;
		}
		SQLStructuredType old = this.referencedTableType;
		this.referencedTableType = null;

		return old.removeReferencingColumn( this );
	}



	public String getDefaultValue() {
		return this.defaultValue;
	}



	public Boolean isAutoIncrement() {
		return this.autoIncrement;
	}



	public Boolean setAutoIncrement( String isAutoIncrement ) {
		if ( isAutoIncrement == null ) {
			return setAutoIncrement( (Boolean)null );
		}
		return this.autoIncrement = "YES".equals( isAutoIncrement );
	}



	public Boolean setAutoIncrement( Boolean isAutoIncrement ) {
		Boolean old = this.autoIncrement;
		this.autoIncrement = isAutoIncrement;

		return old;
	}



	public String setDefaultValue( String defaultValue ) {
		String old = this.defaultValue;
		this.defaultValue = defaultValue;

		return old;
	}



	public Integer setPrecision( Integer precision ) {
		Integer old = this.precision;
		this.precision = precision;

		return old;
	}



	public Integer setScale( Integer scale ) {
		Integer old = this.scale;
		this.scale = scale;

		return old;
	}



	public SQLDataType<?, ?> setType( SQLDistinctType sqlDistinctType ) {
		SQLDataType<?, ?> old = this.type;
		this.type = sqlDistinctType;

		return old;
	}



	public SQLDataType<?, ?> setType( SQLSimpleType sqlSimpleType ) {
		SQLDataType<?, ?> old = this.type;
		this.type = sqlSimpleType;

		return old;
	}



	public SQLDataType<?, ?> setType( SQLStructuredType sqlStructuredType ) {
		SQLDataType<?, ?> old = this.type;
		this.type = sqlStructuredType;

		return old;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunColumn" );
		if ( this.characterSetName != null ) {
			out.append( " | characterSetName=" + this.characterSetName );
		}
		if ( this.collationName != null ) {
			out.append( " | collationName=" + this.collationName );  
		}
		if ( this.isNullable != null ) {
			out.append( " | isNullable=" + this.isNullable );  
		}
		if ( getLength() != null ) {
			out.append( " | length=" + getLength() );  
		}
		if ( getPrecision() != null ) {
			out.append( " | precision=" + getPrecision() );  
		}
		if ( getScale() != null ) {
			out.append( " | scale=" + getScale() );  
		}
		if ( this.autoIncrement != null ) {
			out.append( " | autoIncrement=" + this.autoIncrement );  
		}
		if ( this.defaultValue != null ) {
			out.append( " | defaultValue=" + this.defaultValue );  
		}
		if ( this.referencedTableType != null ) {
			out.append( " | referencedTableType=" + this.referencedTableType );  
		}
		if ( this.referencedTableType != null ) {
			out.append( " | optionScopeColumnSet=" + this.optionScopeColumnSet );
			out.append( "<" + optionScopeColumnSet.getClass().getSimpleName() + ">" );            
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
	 * The name of the character set used for the values in the column.
	 * <p>
	 * This field applies only to columns whose data-type is a character string.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 */
	protected String characterSetName = null;



	/**
	 * The name of the collation sequence used to sort the data values in the column.
	 * <p>
	 * This applies only to columns whose data-type is a form of character string.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 */
	protected String collationName = null;



	/**
	 * Indicates if null values are valid in this column.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : NullableType ( columnNoNulls | columnNullable | columnNullableUnknown )</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 */
	protected Nullable isNullable = null;



	/**
	 * The length of fixed length character or byte strings. 
	 * <p>
	 * Maximum length if length is variable. 
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 */
	protected Integer length = null;



	/**
	 * The total number of digits in the field. 
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i> : Integer</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>constraints</i> : Scale must be specified when precision is specified</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[Foundation-C-3]</b> The scale attribute is valid only if the precision attribute is specified.</li>
	 * </ul> 
	 * 
	 */
	protected Integer precision = null;



	/**
	 * The number of digits on the right of the decimal separator.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Integer</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 */
	protected Integer scale = null;



	protected Boolean autoIncrement = null;



	protected String defaultValue = null;



	/**
	 * Associates Columns of a StructuredType with the Type they reference in the REF clause.
	 * 
	 * <p>
	 * The column, used in an SQLStructuredType is a REF to a type. This references the REF SQLStructuredType.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : SQLStructuredType</li>
	 * <li><i>defined by</i> : ColumnRefStructuredType::referencedTableType</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>inverse</i> : SQLStructuredType::referencingColumn</li>
	 * </ul>
	 * 
	 */
	protected SQLStructuredType referencedTableType = null;



	/**
	 * Associates Columns with NamedColumnSets they reference in their OPTIONS clause.
	 * <p>
	 * Reference to the NamedColumnSet (Table or View) indicated in the SCOPE clause of the Column definition.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i> : NamedColumnSet</li>
	 * <li><i>defined by</i> : ColumnOptionsColumnSet::optionScopeColumnSet</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>inverse</i> : NamedColumnSet::optionScopeColumn</li>
	 * </ul>
	 * 
	 */
	protected NamedColumnSet<?> optionScopeColumnSet = null;
}