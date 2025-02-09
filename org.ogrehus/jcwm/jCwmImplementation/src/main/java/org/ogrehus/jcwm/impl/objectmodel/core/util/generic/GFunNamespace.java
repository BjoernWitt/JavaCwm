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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.elementownership.Namespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.elementownership.OwnedElement;


/**
 * A Namespace is a part of a model that contains a set of ModelElements each of whose names designates a unique element within the Namespace.
 * <p>
 * In the meta-model, a Namespace is a ModelElement that can own other ModelElements, such as Classifiers. The name of each owned ModelElement must be
 * unique within the Namespace. Moreover, each contained ModelElement is owned by at most one Namespace. The concrete subclasses of Namespace may have
 * additional constraints on which kind of elements may be contained.
 * </p>
 * <p>
 * Namespace is an abstract meta-class.
 * </p>
 * <p>
 * Note that explicit parts of a model element, such as the features of a GClassifier, are not modeled as owned elements in a namespace. A namespace
 * is used for unstructured contents such as the contents of a package, or a class declared inside the scope of another class.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the namespace that contains this namespace.
 * @param <PACKAGE> The type of the importer of this namespace.
 * @param <DEPENDENCY> The type of Dependency of this Namespace.
 * @param <CONSTRAINT> The type of the Constraint under which this namespace is.
 * @param <OWNED> Type of the ownedElements by this namspace 
 * 
 */
public abstract class GFunNamespace<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWNED      extends GModelElement<?, ?, ?, ?>
> extends
	GFunModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT>
implements
	GNamespace<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWNED>
{


	/**
	 * Helps to creates new instances of GFunNamespace by specific parameters.
	 * 
	 * @param name An identifier for the GFunNamespace within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the GFunNamespace within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	protected GFunNamespace( String name, Visibility visibility ) {
		super( name, visibility ); // throws NullPointerException
		this.ownedElements = new HashSet<OWNED>();
	}



//====================================================================================================================================================
// Namespace<OWNED>.java
//====================================================================================================================================================



	protected <E extends OWNED> Boolean addOwnedElementGeneric( E ownedElement ) {
		if ( ownedElement != null && this.ownedElements.add( ownedElement ) ) {
			invokeByReflection( ownedElement, "setNamespace", this ); // bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeOwnedElement( OwnedElement<?> ownedElement ) {
		if ( ownedElement == null ) {
			return Boolean.TRUE;
		}

		if ( this.ownedElements.remove( ownedElement ) ) {
			ownedElement.removeNamespace(); // remove bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Set<OWNED> getOwnedElements() {
		return this.ownedElements;
	}



	public <TYPE extends OWNED> Set<TYPE> getOwnedElementsByType( Class<TYPE> typeof ) {
		return getOwnedElementsGeneric( typeof );
	}



	public <TYPE extends OWNED> TYPE getOwnedElement( Class<TYPE> typeof, String simpleName ) {
		if ( simpleName == null ) {
			return null;
		}
		for ( TYPE byType : getOwnedElementsGeneric( typeof ) ) {
			if ( simpleName.equals( byType.getSimpleName() ) ) {
				return byType;
			}
		}

		return null;
	}



	/**
	 * Returns an ownedElement that ist owned by this namespace, or is owned by any owned namespace that is namespace.
	 * 
	 * @param typeof The type of the owned Element contained in this namespace.
	 * 
	 * @param name The name of the Element in this namespace.
	 * 
	 * @return A TYPE that matches the name of the param <code>name</code>. Returns <code>null</code> if no matching element by type and name can be
	 * found.
	 * 
	 */
	public <TYPE extends ModelElement> TYPE getOwnedElementDeep( Class<TYPE> typeOf, String qualifiedName ) {
		return getOwnedElementDeep( typeOf, qualifiedName, ".", "" );
	}



	/**
	 * Returns an ownedElement that ist owned by this namespace, or is owned by any owned namespace that is namespace.
	 * 
	 * @param typeof The type of the owned Element contained in this namespace.
	 * 
	 * @param name The name of the Element in this namespace.
	 * 
	 * @return A TYPE that matches the name of the param <code>name</code>. Returns <code>null</code> if no matching element by type and name can be
	 * found.
	 * 
	 */
	public <TYPE extends ModelElement> TYPE getOwnedElementDeep( 
		  Class<TYPE> typeOf
		, String qualifiedName
		, String separator
		, String surrounding 
	) {
		if ( qualifiedName != null ) {
			for ( TYPE byType : getAllContentsGeneric( typeOf ) ) {
				String qualifiedNameByType = byType.getQualifiedName( separator, surrounding );
				if ( qualifiedName.equals( qualifiedNameByType ) ) {
					return byType;
				}
			}
		}

		return null;
	}



	protected <E extends GModelElement<?, ?, ?, ?>> Set<E> getOwnedElementsGeneric( Class<E> classOfElements ) {
		if ( classOfElements == null ) {
			return Collections.emptySet();
		}

		Set<E> specific = new HashSet<E>();
		for ( OWNED owned : this.ownedElements ) {
			if ( classOfElements.isInstance( owned ) ) {
				specific.add( classOfElements.cast( owned ) );
			}
		}

		return specific;
	}



	public Set<ModelElement> getAllContents() {
		return getContents();
	}



	public Set<ModelElement> getContents() {
		Set<ModelElement> all = new HashSet<ModelElement>();
//		if ( this.namespace != null ) {
//			all.addAll( this.namespace.getContents() );
//		}
		all.addAll( this.ownedElements );

		return all;
	}



	public <TYPE extends ModelElement> Set<TYPE> getContentsGeneric( Class<TYPE> typeOf ) {
		Set<TYPE> contents = new HashSet<TYPE>();
		for ( ModelElement element : this.ownedElements ) {
//System.out.println( "ownedElement:" + element.getSimpleName() + "<" + element.getClass().getSimpleName() + ">");
			if ( typeOf.isInstance( element ) ) {
				contents.add( typeOf.cast( element ) );
			}
		}

		return contents;
	}



	public <TYPE extends ModelElement> Set<TYPE> getAllContentsGeneric( Class<TYPE> typeOf ) {
		Set<TYPE> allGeneric = new HashSet<TYPE>();
		for ( ModelElement content : getContents() ) { // do not call generic content here for recursive lookahead
			// 	do recursive lookahead for all typeOf instances
			if ( content instanceof Namespace<?> ) {
				Namespace<?> namespace = (Namespace<?>)content;
				allGeneric.addAll( namespace.getAllContentsGeneric( typeOf ) );
			}
			// general add for all typeOf instances
			if ( typeOf.isInstance( content ) ) {
				allGeneric.add( typeOf.cast( content ) );
			}
		}

		return allGeneric;
	}



	public Set<ModelElement> getAllVisibleElements() {
		Set<ModelElement> allVisible = new HashSet<ModelElement>();
		for ( ModelElement content : this.getAllContents() ) {
			switch ( content.getVisibility() ) {
			case _public:
				allVisible.add( content );
			default: // do nothing
			}
		}
		return allVisible;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunNamespace" );
		out.append( collectionToString( "ownedElements", this.ownedElements, true ) );
		out.append( " | \nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}



//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * Reference: ownedElement
	 * 
	 * A set of ModelElements owned by the Namespace. The ModelElement�s visibility attribute states whether the element
	 * is visible outside the namespace.
	 * 
	 * <ul>
	 * <li><i>class</i>			: generic OWNED</li>
	 * <li><i>defined by</i>	: ElementOwnership::ownedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: ModelElement::namespace</li>
	 * </ul>
	 * 
	 */
	protected Set<OWNED> ownedElements = null;
}