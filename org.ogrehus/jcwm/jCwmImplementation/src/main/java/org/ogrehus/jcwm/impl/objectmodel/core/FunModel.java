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

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Model;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.CwmManager;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunCwmPackage;


/**
 * A model captures a view of a physical system. 
 * 
 * <p>
 * It is an abstraction of the physical system, with a certain purpose. The model completely describes those aspects of the physical system that are
 * relevant to the purpose of the model, at the appropriate level of detail.
 * </p>
 * <p>
 * In the meta-model Model is a subclass of Package. It contains a containment hierarchy of ModelElements that together describe the physical system.
 * A Model also contains a set of ModelElements that represents the environment of the system.
 * </p>
 * <p>
 * Different Models can be defined for the same physical system, where each model represents a view of the physical system defined by its purpose and
 * abstraction level; for example, an analysis model, a design model, an implementation model. Typically different models are complementary and
 * defined from the perspectives (viewpoints) of different system stakeholders.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunModel
extends 
	GFunCwmPackage<
		  CwmManager
		, Model
		, Model
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
		, GModelElement<?, ?, ?, ?>
	>
implements
	Model
{


	/**
	 * Creates a new instance of FunPackage by specific parameters.
	 * 
	 * @param name An identifier for the FunPackage within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunPackage within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 *
	 */	
	public FunModel( String name, Visibility visibility ) {
		super( name, visibility ); // throws NullPointerException
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Model setNamespace( Model namespace ) {
		return super.setNamespaceGeneric( namespace );
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



	public Boolean addImporter( Model model ) {
		return super.addImporterGeneric( model );
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
}