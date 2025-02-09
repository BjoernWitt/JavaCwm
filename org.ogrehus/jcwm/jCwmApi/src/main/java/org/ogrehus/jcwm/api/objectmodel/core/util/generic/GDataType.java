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
package org.ogrehus.jcwm.api.objectmodel.core.util.generic;

import java.util.Set;

import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;


/**
 * A data type is a type whose values have no identity; that is, they are pure values. 
 * 
 * <p>
 * Data types include primitive built-in types (such as integer and string) as well as definable enumeration types.
 * </p>
 * <b>DateType are immutable values:</b>
 * <p>
 * In the meta-model a GDataType defines a special kind of Classifier in which operations are all pure functions; that is, they can return data values
 * but they cannot change data values, because they have no identity. For example, an add operation on a number with another number as an argument 
 * yields a third number as a result; the target and argument are unchanged.
 * </p>
 * <p>
 * A GDataType is a special kind of Classifier whose instances are primitive values, not objects. For example, integers and strings are usually 
 * treated as primitive values. A primitive value does not have an identity, so two occurrences of the same value cannot be differentiated. Usually, 
 * DataTypes are used for specification of the type of an attribute or parameter.
 * </p>
 * <p>
 * The ObjectModel meta-model contains a number of data types. Each of these data types is an instance of the GDataType class. Some of these data 
 * types have default values. These default values only apply for mandatory attributes or parameters of the relevant data type where an explicit 
 * value is not supplied.
 * </p>
 * <p>
 * <b>Constraints:</b><br>
 * </p> 
 * <ul>
 * <li><b>[C_3_2]</b> - A GDataType cannot contain any other ModelElements.</li>
 * </ul>
 * <p>
 * The constraint [C_3_2] is satisfied cause there is no way (method) to add a ModelElement to this DataType. The protected generic method of 
 * addOwnedElement is final and throws a constraint violation exception if any method will allow adding an ownedElement by inherited classes.
 * </p>
 *  
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <FEATURE> Type of the ordered list of Features owned by this GDataType. 
 *
 */
public interface GDataType<
	  NAMESPACE extends GCwmPackage<?,?,?,?,?,?,?>
	, FEATURE   extends GFeature<?, ?, ?, ?, ?>
> extends
	GClassifier<NAMESPACE, TypeSystem, Dependency, Constraint, GModelElement<?, ?, ?, ?>, FEATURE> 
{


//======================================================================================================================
// GModelElement capabilities	
//======================================================================================================================



	/**
	 * Adds an importer (Package) to this DataType, that will contain this DataType as imported.
	 * 
	 * @param importer The specific package, that will contain this DataType.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( TypeSystem importer );



	/**
	 * Adds a dependency to this DataType.
	 * 
	 * @param dependency A Dependency in witch this DataType is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this DataType.
	 * 
	 * @param constraint A Constrain that must be satisfied by this DataType.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Returns the values of the reference <code>ownedElement</code>.
	 * <p>
	 * <b>Constraints:</b><br>
	 * </p> 
	 * <ul>
	 * <li><b>[C_3_2]</b> - A GDataType cannot contain any other ModelElements.</li>
	 * </ul>
	 * 
	 * @return Always an empty Set.
	 * 
	 */	
	Set<GModelElement<?, ?, ?, ?>> getOwnedElements();
}