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
package org.ogrehus.jcwm.impl.foundation.datatypes;

import org.ogrehus.jcwm.api.foundation.datatypes.TypeAlias;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.impl.foundation.datatypes.util.generic.GFunTypeAlias;


/**
 * The TypeAlias class is intended to provide a renaming capability for Classifier instances.
 * <p>
 * This class is required to support situations in which creation of an alias for a class effectively creates a new class. For example, CORBA IDL
 * type aliases have different typeCodes than their base types and are therefore treated as distinct types.
 * </p>
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation_C-4-1]</b> A TypeAlias instance cannot alias itself.</li>
 * </ul> 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <TYPE> The type of the GClassifier for which this TypeAlias instance acts as an alias.
 * 
 */
public class FunTypeAlias<
	TYPE extends GClassifier<?, ?, ?, ?, ?, ?>
> extends
	GFunTypeAlias<TypeSystem, Attribute, TYPE> 
implements
	TypeAlias<TYPE>
{

	
	/**
	 * Factory method that creates a new instance of TypeAlias by specific parameters.
	 * 
	 * <p>
	 * The TypeAlias class is intended to provide a renaming capability for Classifier instances.This class is required to support situations in which
	 * creation of an alias for a class effectively creates a new class. For example, CORBA IDL type aliases have different typeCodes than their base
	 * types and are therefore treated as distinct types.
	 * </p>
	 * 
	 * @param name An identifier for the TypeAlias within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the TypeAlias within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param notInstantiable <code>true</code> if this TypeAlias is abstract and can not be instantiated, <code>false</code> otherwise. Must not 
	 * be <code>null</code>.
	 * 
	 * @param type Identifies the Classifier instance for which this TypeAlias instance acts as an alias. The ClassifierAlias association connects
	 *  TypeAlias instances with the Classifier instances that they rename.  Must not be <code>null</code>.
	 *   
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>notInstantiable</code> 
	 * or <code>type</code> is <code>null</code>.
	 * 
	 */
	public FunTypeAlias( String name, Visibility visibility, Boolean notInstantiable, TYPE type ) {
		super( name, visibility, notInstantiable, type ); // may throw NullPointerException
	}



//====================================================================================================================================================
// TypeAlias capabilities
//====================================================================================================================================================



	public TYPE setType(TYPE type) {
		return super.setTypeGeneric( type );
	}



 //===================================================================================================================================================
 // GModelElement capabilities
 //===================================================================================================================================================



 	public TypeSystem setNamespace( TypeSystem typeSystem ) {
 		return super.setNamespaceGeneric( typeSystem );
 	}



//====================================================================================================================================================
// Classifier capabilities
//====================================================================================================================================================



	/**
	 * Appends a Feature that will be owned by this TypeAlias.
	 * 
	 * @param attribute that will be owned by this TypeAlias. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addFeature( Attribute attribute ) {
		return super.addFeatureGeneric( attribute );
	}



	public Boolean addFeature(GFeature<?, ?, ?, ?, ?> feature) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}