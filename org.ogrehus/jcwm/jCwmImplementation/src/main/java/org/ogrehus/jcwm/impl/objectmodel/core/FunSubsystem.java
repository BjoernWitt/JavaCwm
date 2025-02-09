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
package org.ogrehus.jcwm.impl.objectmodel.core;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.CwmManager;
import org.ogrehus.jcwm.api.objectmodel.core.Subsystem;
import org.ogrehus.jcwm.api.objectmodel.core.CwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunSubsystem;


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
 * @param <NAMESPACE> The type of the Namespace that contains this FunSubsystem.
 * @param <PACKAGE> The type of the importer of this FunSubsystem.
 * @param <DEPENDENCY> The type of ClientDependency of this FunSubsystem.
 * @param <CONSTRAINT> The type of the Constraint under which this FunSubsystem is. 
 * @param <OWNED> Type of the ownedElements by this FunSubsystem. 
 * @param <IMPORTED> Type of the importedElements of this FunSubsystem.
 * @param <FEATURE> Type of the Feature of this FunSubsystem. 
 * 
 */
public class FunSubsystem
extends
	GFunSubsystem<
		  CwmManager
		, CwmPackage
		, CwmPackage
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
		, GModelElement<?, ?, ?, ?>
		, GFeature<?, ?, ?, ?, ?>
> implements
	Subsystem
{


	/**
	 * Creates a new instance of FunSubsystem by specific parameters.
	 * 
	 * @param name An identifier for the FunSubsystem within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunSubsystem within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this FunSubsystem is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 *
	 */
	public FunSubsystem( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility, notInstantiable ); // may throw NullPointerException
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public CwmPackage setNamespace( CwmPackage cwmPackage ) {
		return super.setNamespaceGeneric( cwmPackage );
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



	public Boolean addImporter( CwmPackage cwmPackage ) {
		return super.addImporterGeneric( cwmPackage );		
	}



//====================================================================================================================================================
// GNamspace capabilities (determined by Package)
//====================================================================================================================================================



	public Boolean addOwnedElement( GClassifier<?, ?, ?, ?, ?, ?> classifier ) {
		return super.addOwnedElementGeneric( classifier );
	}



	public Boolean addOwnedElement( GConstraint<?, ?, ?, ?, ?> constraint ) {
		return super.addOwnedElementGeneric( constraint );
	}



	public Boolean addOwnedElement( GCwmPackage<?, ?, ?, ?, ?, ?, ?> cwmPackage ) {
		return super.addOwnedElementGeneric( cwmPackage );
	}



	public Boolean addOwnedElement( GDependency<?, ?, ?, ?, ?> ownedDependency ) {
		return super.addOwnedElementGeneric( ownedDependency );
	}



//====================================================================================================================================================
// GCwmPacakge capabilities
//====================================================================================================================================================



	public Boolean addImportedElement( GModelElement<?, ?, ?, ?> importedElement ) {
		return super.addImportedElementGeneric( importedElement );
	}



	public Boolean addDataManager( CwmManager cwmManager ) {
		return super.addDataManagerGeneric( cwmManager );
	}



//====================================================================================================================================================
// GClassifier capabilities
//====================================================================================================================================================



	public Boolean addFeature( GFeature<?, ?, ?, ?, ?> feature ) {
		return super.addFeatureGeneric( feature );
	}
}