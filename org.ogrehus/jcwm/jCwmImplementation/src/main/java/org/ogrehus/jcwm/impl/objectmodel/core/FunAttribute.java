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

import org.ogrehus.jcwm.api.objectmodel.behavioral.Interface;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.relationships.Association;
import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunAttribute;

import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;

import org.ogrehus.jcwm.api.objectmodel.core.util.Changeable;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

/**
 * An Attribute describes a named slot within a Classifier that may hold a value.
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunAttribute
extends 
	GFunAttribute<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, GClassifier< ?, ?, ?, ?, ?, ?>
		, GClassifier< ?, ?, ?, ?, ?, ?>
	>
implements
	Attribute
{


	/**
	 * Creates a new instance of FunAttribute by specific parameters.
	 * 
	 * @param name An identifier for the FunAttribute within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunAttribute within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param changeability The changeability of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @param type The new Classifier describing the type of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code>
	 * or <code>type</code> was <code>null</code>.
	 * 
	 */
	protected FunAttribute( String name, Visibility visibility, Changeable changeability, DataType type ) {
		super( name, visibility, changeability, type ); // may throw NullPointerException
	}



	/**
	 * Creates a new instance of FunAttribute by specific parameters.
	 * 
	 * @param name An identifier for the FunAttribute within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunAttribute within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param changeability The changeability of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @param type The new Classifier describing the type of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code>
	 * or <code>type</code> was <code>null</code>.
	 * 
	 */
	protected FunAttribute( String name, Visibility visibility, Changeable changeability, CwmClass type ) {
		super( name, visibility, changeability, type ); // may throw NullPointerException
	}



	/**
	 * Creates a new instance of FunAttribute by specific parameters.
	 * 
	 * @param name An identifier for the FunAttribute within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunAttribute within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param changeability The changeability of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @param type The new Classifier describing the type of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code>
	 * or <code>type</code> was <code>null</code>.
	 * 
	 */
	protected FunAttribute( String name, Visibility visibility, Changeable changeability, Interface type ) {
		super( name, visibility, changeability, type ); // may throw NullPointerException
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public GNamespace<?, ?, ?, ?, ?> setNamespace( GNamespace<?, ?, ?, ?, ?> namespace ) {
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
// Feature capabilities
//====================================================================================================================================================



	/**
	 * The Association declaring the Feature.
	 * </p>
	 * <p>
	 * The ClassifierFeature association provides a composite aggregation containment relationship between Classifiers and the Features they own. The
	 * feature end of the association is ordered allowing preservation of the ordering of Features within their owning Classifier. A GFeature can be
	 * owned by at most one Classifier. The optional character of ownership is intended as a convenience to tools, allowing them to create Features
	 * prior to linking them to their owning Classifier.
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: Association</li>
	 * <li><i>defined by</i>	: ClassifierFeature::owner</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Association::feature</li>
	 * </ul>
	 *
	 * @return The old value of  owner if one is available, <code>null</code> otherwise.
	 * 
	 */
	public GClassifier<?, ?, ?, ?, ?, ?> setOwner( Association association ) {
		return super.setOwnerGeneric( association );
	}



	/**
	 * The CwmClass declaring the Feature.
	 * </p>
	 * <p>
	 * The ClassifierFeature association provides a composite aggregation containment relationship between Classifiers and the Features they own. The
	 * feature end of the association is ordered allowing preservation of the ordering of Features within their owning Classifier. A GFeature can be
	 * owned by at most one Classifier. The optional character of ownership is intended as a convenience to tools, allowing them to create Features
	 * prior to linking them to their owning Classifier.
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: Association</li>
	 * <li><i>defined by</i>	: ClassifierFeature::owner</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Association::feature</li>
	 * </ul>
	 *
	 * @return The old value of owner if one is available, <code>null</code> otherwise.
	 * 
	 */
	public GClassifier<?, ?, ?, ?, ?, ?> setOwner( CwmClass cwmClass ) {
		return super.setOwnerGeneric( cwmClass );
	}



//====================================================================================================================================================
// StructuralFeature capabilities
//====================================================================================================================================================



	/**
	 * Sets a new reference to this <code>type</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Classifier</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param dataType The new Classifier describing the type of this structuralFeature.
	 * 
	 * @return The old Classifier describing the type of this structuralFeature. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter type is <code>null</code>.
	 * 
	 */
	public GClassifier<?, ?, ?, ?, ?, ?> setType( DataType dataType ) {
		return super.setTypeGeneric( dataType );
	}



	/**
	 * Sets a new reference to this <code>type</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Classifier</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param cwmClass The new Classifier describing the type of this structuralFeature.
	 * 
	 * @return The old Classifier describing the type of this structuralFeature. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter type is <code>null</code>.
	 * 
	 */
	public GClassifier<?, ?, ?, ?, ?, ?> setType( CwmClass cwmClass ) {
		return super.setTypeGeneric( cwmClass );
	}



	/**
	 * Sets a new reference to this <code>type</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Classifier</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param cwmInterface The new Classifier describing the type of this structuralFeature.
	 * 
	 * @return The old Classifier describing the type of this structuralFeature. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter type is <code>null</code>.
	 * 
	 */
	public GClassifier<?, ?, ?, ?, ?, ?> setType( Interface cwmInterface ) {
		return super.setTypeGeneric( cwmInterface );
	}
}