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
package org.ogrehus.jcwm.impl.objectmodel.behavioral;

import java.util.Collections;
import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.behavioral.Interface;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.ConstraintViolation;
import org.ogrehus.jcwm.api.objectmodel.core.util.CwmConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunClassifier;

/**
 * FunInterface is a named set of operations that specify the behavior of an element.
 * <p>
 * In the meta-model, an FunInterface contains a set of Operations that together define a service offered by a Classifier realizing the FunInterface.
 * A Classifier may offer several services, which means that it may realize several Interfaces, and several Classifiers may realize the same
 * FunInterface.
 * </p>
 * <p>
 * <b>Constraints</b>:
 * </p>
 * <ul>
 * <li><b>[C-4-4]</b> An FunInterface can only contain Operations.</li>
 * <li><b>[C-4-5]</b> An FunInterface cannot contain any ModelElements.</li>
 * <li><b>[C-4-6]</b> All Features defined in an FunInterface are public.</li>
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunInterface
extends
	GFunClassifier<
		  CwmClass
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
		, Operation
	>
implements
	Interface
{



	/**
	 * Constructor creates a new instance of this Interface by specific parameters.
	 * 
	 * @param name An identifier for the Interface within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Interface within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @return A new instance of Interface, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameters: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 * 
	 */
	public FunInterface( String name, Visibility visibility ) {
		super( name, visibility, Boolean.TRUE );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public CwmClass setNamespace( CwmClass namespace ) {
		return super.setNamespaceGeneric( namespace );
	}



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



	public Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer ) {
		return super.addImporterGeneric( importer );
	}



//====================================================================================================================================================
// GNamespace capabilities
//====================================================================================================================================================



	/**
	 * The operation contents results in an empty Set.
	 * <p>
	 * <b>Constraint:</b>:
	 * <ul>
	 * <li><b>[C-4-5]</b> An Interface cannot contain any ModelElements.<pre>
	 * <b>context</b> Interface <b>inv</b>:
	 * self.allContents -> isEmpty
	 * </pre>
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @return A Set of ModelElements contained by this namespace.
	 * 
	 */
	@Override
	public Set<ModelElement> getContents() {
		return Collections.emptySet();
	}



//====================================================================================================================================================
// Classifier capabilities
//====================================================================================================================================================



	/**
	 * Appends a Feature that will be owned by this Interface.
	 * 
	 * <p>
	 * <b>Constraints</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C-4-4]</b> An Interface can only contain Operations.<pre>
	 * <b>context</b> Interface <b>inv</b>:
	 * self.allFeatures -> forAll( f | f.oclIsKindOf( Operation ) ) 
	 * </pre> 
	 * </li>
	 * <li><b>[C-4-6]</b> All Features defined in an Interface are public.<pre>
	 * <b>context</b> Interface <b>inv</b>:
	 * self.allFeatures -> forAll( f | f.visibility = #public )
	 * </pre> 
	 * </li>
	 * </ul> 
	 * The constraint <b>[C-4-4]</b> is assured by only the method signature tha only allows to add Operation as a Feature to this Classifier.
	 * 
	 * @param operation The Operation that will be owned by this Interface. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addFeature( Operation operation ) {
		if ( operation.getVisibility() != Visibility._public ) {
			throw new ConstraintViolation( CwmConstraint.C_4_6, operation.getName() );
		}
		
		return this.features.add( operation );
	}
}