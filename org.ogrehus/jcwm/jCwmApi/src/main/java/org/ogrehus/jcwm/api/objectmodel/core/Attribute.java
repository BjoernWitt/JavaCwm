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
package org.ogrehus.jcwm.api.objectmodel.core;

import org.ogrehus.jcwm.api.objectmodel.behavioral.Interface;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GAttribute;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.relationships.Association;


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
public interface Attribute
extends
	GAttribute<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, GClassifier< ?, ?, ?, ?, ?, ?>
		, GClassifier< ?, ?, ?, ?, ?, ?>
	> 
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains this Attribute. Every Model Element except a root element must belong to exactly one Namespace or else 
	 * be a composite part of another ModelElement (which is a kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>  
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: Namespace</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Namespace::ownedElement</li>
	 * </ul>
	 * 
	 * @param namespace The new namespace that contains this ModelElement.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before. 
	 * 
	 */	
	GNamespace< ?, ?, ?, ?, ?> setNamespace( GNamespace< ?, ?, ?, ?, ?> namespace );



	/**
	 * Adds a dependency to this Attribute.
	 * 
	 * @param dependency A Dependency in witch this Attribute is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );	



	/**
	 * Adds an importer (Package) to this Attribute, that will contain this Attribute as imported.
	 * 
	 * @param importer The specific package, that will contain this Attribute.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer );



	/**
	 * Adds another Constraint to this Attribute.
	 * 
	 * @param constraint A Constrain that must be satisfied by this Attribute.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );	



//====================================================================================================================================================
// Feature capabilities	
//====================================================================================================================================================



	/**
	 * The Classifier declaring the Feature.
	 * </p>
	 * <p>
	 * The ClassifierFeature association provides a composite aggregation containment relationship between Classifiers and the Features they own. 
	 * The feature end of the association is ordered allowing preservation of the ordering of Features within their owning Classifier. A GFeature can 
	 * be owned by at most one Classifier. The optional character of ownership is intended as a convenience to tools, allowing them to create Features 
	 * prior to linking them to their owning Classifier.
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: Association</li>
	 * <li><i>defined by</i>	: ClassifierFeature::owner</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Association::feature</li>
	 * </ul>
	 * 
	 * @param association The Classifier declaring this Feature.
	 *
	 * @return The old value of  owner if one is available, <code>null</code> otherwise.
	 * 
	 */
	GClassifier< ?, ?, ?, ?, ?, ?> setOwner( Association association );



	/**
	 * The Classifier declaring the Feature.
	 * </p>
	 * <p>
	 * The ClassifierFeature association provides a composite aggregation containment relationship between Classifiers 
	 * and the Features they own. The feature end of the association is ordered allowing preservation of the ordering of
	 * Features within their owning Classifier. A GFeature can be owned by at most one Classifier. The optional 
	 * character of ownership is intended as a convenience to tools, allowing them to create Features prior to linking 
	 * them to their owning Classifier.
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: CwmClass</li>
	 * <li><i>defined by</i>	: ClassifierFeature::owner</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: CwmClass::feature</li>
	 * </ul>
	 * 
	 * @param cwmClass The Classifier declaring this Feature.
	 *
	 * @return The old value of  owner if one is available, <code>null</code> otherwise.
	 * 
	 */
	GClassifier< ?, ?, ?, ?, ?, ?> setOwner( CwmClass cwmClass );



//======================================================================================================================	
// StructuralFeature capabilities	
//======================================================================================================================	



	/**
	 * Sets a new reference to this <code>type</code>.
	 *  
	 * <ul>
	 * <li><i>class</i>: GClassifier</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param dataType The new GClassifier describing the type of this Attribute.
	 * 
	 * @return The old GClassifier describing the type of this Attribute. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>dataType</code> is <code>null</code>. 
	 * 
	 */	
	GClassifier< ?, ?, ?, ?, ?, ?> setType( DataType dataType );



	/**
	 * Sets a new reference to this <code>type</code>.
	 *  
	 * <ul>
	 * <li><i>class</i>: CwmClass</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param cwmClass The new GClassifier describing the type of this Attribute.   
	 * 
	 * @return The old GClassifier describing the type of this Attribute. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>cwmClass</code> is <code>null</code>. 
	 * 
	 */	
	GClassifier< ?, ?, ?, ?, ?, ?> setType( CwmClass cwmClass );//// conflict in classifier and datatype, solve it first



	/**
	 * Sets a new reference to this <code>type</code>.
	 *  
	 * <ul>
	 * <li><i>class</i>: Interface</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param cwmInterface The new GClassifier describing the type of this Attribute.   
	 * 
	 * @return The old GClassifier describing the type of this Attribute. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>cwmInterface</code> is <code>null</code>. 
	 * 
	 */	
	GClassifier< ?, ?, ?, ?, ?, ?> setType( Interface cwmInterface );// conflict in classifier and datatype, solve it first
}