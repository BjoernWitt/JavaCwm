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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GSubsystem;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.importedelements.Importer;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;

/**
 * A package is a grouping of model elements.
 * 
 * <p>
 * In the meta-model Package is a subclass of Namespace. A Package contains ModelElements such as Packages and Classifiers. A Package may also contain
 * Constraints and Dependencies between ModelElements of the Package.
 * </p>
 * <p>
 * The purpose of the package construct is to provide a general grouping mechanism. In fact, its only semantics is to define a Namespace for its
 * contents. The package construct can be used for organizing elements for any purpose; the criteria to use for grouping elements together into one
 * package are not defined.
 * </p>
 * <p>
 * A package owns a set of model elements, with the implication that if the package is removed from the model, so are the elements owned by the
 * package. Elements with names, such as classifiers, that are owned by the same package must have unique names within the package, although elements
 * in different packages may have the same name.
 * </p>
 * <p>
 * There may be relationships between elements contained in the same package, and between an element in one package and an element in a surrounding
 * package at any level. In other words, elements �see� all the way out through nested levels of packages. Elements in peer packages, however, are
 * encapsulated and are not a priori visible to each other. The same goes for elements in contained packages; that is, packages do not see inwards.
 * </p>
 * <p>
 * Elements owned by a Package can be made available to other Packages by importing them. Although any ModelElement may be imported by a Package,
 * imported ModelElements are typically other Packages. When an element is imported by a package it extends the Namespace of that package. Thus the
 * elements available in a Package consists of its owned and imported ModelElements.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 *  
 * @param <MANAGER> The Type of the dataManager administration this in the role of a dataPackage.
 * @param <NAMESPACE> The type of the Namespace that contains this GSubsystem.
 * @param <PACKAGE> The type of the importer of this GSubsystem.
 * @param <DEPENDENCY> The type of ClientDependency of this GSubsystem.
 * @param <CONSTRAINT> The type of the Constraint under which this GSubsystem is.
 * @param <OWNED> Type of the ownedElements by this GSubsystem.
 * @param <IMPORTED> Type of the importedElements of this GSubsystem.
 * @param <FEATURE> Type of the Feature of this GSubsystem.
 * 
 */
public abstract class GFunSubsystem<
	  MANAGER    extends GDataManager<?, ?>
	, NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWNED      extends GModelElement<?, ?, ?, ?>
	, IMPORTED   extends GModelElement<?, ?, ?, ?>
	, FEATURE    extends GFeature<?, ?, ?, ?, ?>
> extends
	GFunClassifier<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWNED, FEATURE>
implements
	GSubsystem<MANAGER, NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWNED, IMPORTED, FEATURE>
{


	/**
	 * Creates a new instance of GFunSubsystem by specific parameters.
	 * 
	 * @param name An identifier for the FunSubsystem within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunSubsystem within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this FunSubsystem is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or  <code>notInstantiable</code>
	 * is <code>null</code>.
	 *
	 */
	protected GFunSubsystem( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility, notInstantiable ); // may throw NullPointerException
		this.importedElements = new HashSet<IMPORTED>();
		this.dataManagers     = new HashSet<MANAGER>();
	}



	public Set<MANAGER> getDataManagers() {
		return this.dataManagers;
	}



	public <GENERIC_MANAGER extends MANAGER> Set<GENERIC_MANAGER> getGenericManagers( Class<GENERIC_MANAGER> typeOf ) {
		Set<GENERIC_MANAGER> genericManagers = new HashSet<GENERIC_MANAGER>();
		for ( MANAGER dataManager : this.dataManagers ) {
			if ( typeOf.isInstance( dataManager ) ) {
				genericManagers.add( typeOf.cast( dataManager ) );
			}
		}

		return genericManagers;
	}



	public <GENERIC_MANAGER extends MANAGER> GENERIC_MANAGER getDataManager(
		  Class<GENERIC_MANAGER> typeOf
		, String simpleName 
	) {
		for ( MANAGER dataManager : this.dataManagers ) {
			if ( typeOf.isInstance( dataManager ) && dataManager.getSimpleName().equals( simpleName ) ) {
				return typeOf.cast( dataManager );
			}
		}

		return null;
	}



	protected <M extends MANAGER> Boolean addDataManagerGeneric( M dataManager ) {
		if ( dataManager != null && this.dataManagers.add( dataManager ) ) {
			invokeByReflection( dataManager, "addDataPackage", this ); // bi-directional reference
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}



	public Boolean removeDataManager( GDataManager<?, ?> dataManager ) {
		if ( dataManager == null ) {
			return Boolean.TRUE;
		}

		if ( this.dataManagers.remove( dataManager ) ) {
			dataManager.removeDataPackage( this );

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



//====================================================================================================================================================
//	ImportedElement<MODEL_ELEMENT>.java
//====================================================================================================================================================



	protected <E extends IMPORTED> Boolean addImportedElementGeneric( E importedElement ) {
		if ( importedElement != null && this.importedElements.add( importedElement ) ) {
			invokeByReflection( importedElement, "addImporter", this ); // bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeImportedElement( Importer<?> importedElement ) {
		if ( importedElement == null ) {
			return Boolean.TRUE;
		}
		importedElement.removeImporter( this );

		return this.importedElements.remove( importedElement );
	}



	public Set<IMPORTED> getImportedElements() {
		return this.importedElements;
	}	



	public <E extends IMPORTED> Collection<E> getImportedElements( Class<E> classOfElements ) {
		Collection<E> specific = new HashSet<E>();
		for ( IMPORTED imported : this.importedElements ) {
			if ( classOfElements.isInstance( imported ) ) {
				specific.add( classOfElements.cast( imported ) );
			}
		}

		return specific;
	}



	public Set<GModelElement<?, ?, ?, ?>> getAllImportedElements() {
		Set<GModelElement<?, ?, ?, ?>> allImported = new HashSet<GModelElement<?,?,?,?>>();
		for ( IMPORTED imported : this.importedElements ) {
			if ( imported instanceof GModelElement<?, ?, ?, ?> ) {
				allImported.add( (GModelElement<?, ?, ?, ?>)imported );
			}
		}

		return allImported;
	}



//====================================================================================================================================================
// Namespace<OWNED>.java
//====================================================================================================================================================



	@Override
	public Set<ModelElement> getContents() {
		Set<ModelElement> contens = super.getContents();// = this.getOwnedElements()
		for ( IMPORTED imported : this.getImportedElements() ) {
			contens.add( (ModelElement)imported );
		}

		return contens;
	}



	public <TYPE extends ModelElement> Set<TYPE> getContentsGeneric( Class<TYPE> typeOf ) {
		Set<TYPE> contents = new HashSet<TYPE>();
		for ( ModelElement element : this.importedElements ) {
			if ( typeOf.isInstance( element ) ) {
				contents.add( typeOf.cast( element ) );
			}
		}
		for ( ModelElement element : this.ownedElements ) {
			if ( typeOf.isInstance( element ) ) {
				contents.add( typeOf.cast( element ) );
			}
		}

		return contents;
	}



	public <TYPE extends ModelElement> Set<TYPE> getAllContentsGeneric( Class<TYPE> typeOf ) {
		return getContentsGeneric( typeOf );
	}



//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * Reference: importedElement
	 * 
	 * The namespace defined by the package is extended by model elements imported from other packages.
	 * 
	 * <ul>
	 * <li><i>class</i>			: generic IMPORTED</li>
	 * <li><i>defined by</i>	: ImportedElements::importedElement</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: ModelElement::importer</li>
	 * </ul>
	 * 
	 */
	protected Set<IMPORTED> importedElements	= null;



	/**
	 * Reference: dataManagers
	 * 
	 * <ul>
	 * <li><i>class</i>			: generic MANAGER</li>
	 * <li><i>defined by</i>	: CwmPackage::dataManager</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: DataManager::dataPackage</li>
	 * </ul>
	 * 
	 */
	protected Set<MANAGER> dataManagers = null;
}