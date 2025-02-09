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
package org.ogrehus.jcwm.impl.objectmodel.core.util.generic;

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GIndex;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * A class is a description of a set of objects that share the same attributes, operations, methods, relationships, and semantics.
 *  
 * <p>
 * A class may use a set of interfaces to specify collections of operations it provides to its environment. In the meta-model, a Class describes a set
 * of objects sharing a collection of Features that are common to the set of objects.
 * </p>
 * <p>
 * The purpose of a Class is to declare a collection of Features that fully describe the structure and behavior of objects. Some Classes may not be
 * directly instantiated. These Classes are said to be abstract and exist only for other Classes to inherit and reuse the Features declared by them.
 * No object may be a direct instance of an abstract Class, although an object may be an indirect instance of one through a subclass that is none
 * abstract.
 * </p>
 * <p>
 * A Class acts as the namespace for various kinds of contained elements defined within its scope, including classes, interfaces, and associations
 * (note that this is purely a scoping construction and does not imply anything about aggregation). The contained classes can be used as ordinary
 * classes in the container class. If a class inherits another class, the contents of the ancestor are available to its descendants if the visibility
 * of an element is public or protected. If the visibility is private, then the element is not visible and therefore not available in the descendant.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the namespace that contains this Classifier.
 * @param <PACKAGE> The type of the importer of this Classifier.
 * @param <DEPENDENCY> The type of Dependency of this Classifier.
 * @param <CONSTRAINT> The type of the Constraint under which this Classifier is. 
 * @param <OWNED> Type of the ownedElements by this Classifier.  
 * @param <FEATURE> Type of the Feature of this Classifier. 
 * @param <INDEX> Identifies the type if Index instances that span this Class. 
 * 
 */
public abstract class GFunCwmClass<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWNED      extends GModelElement<?, ?, ?, ?>
	, FEATURE    extends GFeature<?, ?, ?, ?, ?>
	, INDEX      extends GIndex<?, ?, ?, ?>
>extends
	GFunClassifier<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWNED, FEATURE>
implements
	GCwmClass<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWNED, FEATURE, INDEX>
{


	/**
	 * Constructor that creates a new instance of CwmClass by specific parameters.
	 * 
	 * @param name An identifier for the CwmClass within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmClass within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this CwmClass is abstract and can not be instantiated, <code>false</code> otherwise.
	 *
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 * 
	 */
	protected GFunCwmClass( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility, notInstantiable ); // may throw NullPointerException
		this.indexes = new HashSet<INDEX>();
	}



//====================================================================================================================================================
// GCwmClass capabilities
//====================================================================================================================================================



	/**
	 * Associates indexes with the classes they span. 
	 * <p>
	 * This relationship is separate from the ownership of indexes, to allow modeling of systems where an index is NOT 
	 * owned by the object it spans. In most situations, however, the spanned and owning Class instances will be the 
	 * same.
	 * </p>
	 * 
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: generic INDEX</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * </ul>
	 * 
	 * @return A Set of Index instances that span this Class instance. Will be empty List, if none exists.
	 * 
	 */
	public Set<INDEX> getIndexes() {
		return this.indexes;
	}



	protected Boolean addIndexGeneric( INDEX index ) {
		if ( index != null && this.indexes.add( index ) ) {
			invokeByReflection( index, "setSpannedClass",  this ); // bi-directional reference
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	/**
	 * Removes an Index that spans this Class instance.
	 * 
	 * @param index that will no longer span this Class instance.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean removeIndex( GIndex<?, ?, ?, ?> index ) {
		if ( index == null ) {
			return Boolean.TRUE;
		}

		if ( this.indexes.remove( index ) ) {
			index.removeSpannedClass();
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunCwmClass" );
		out.append( collectionToString( "indexes", this.indexes, true ) );
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );
		return out.toString();
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Adds an Index to the reference of <code>indexes</code> that span this Class instance..
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: generic INDEX</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * </ul>
	 * 
	 */
	protected Set<INDEX> indexes = null;
}