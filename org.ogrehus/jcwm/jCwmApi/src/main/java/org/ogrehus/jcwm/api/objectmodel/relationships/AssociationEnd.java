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
package org.ogrehus.jcwm.api.objectmodel.relationships;

import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.relationships.util.generic.GAssociationEnd;

/**
 * An association end is an end-point of an association that connects the association to a classifier.
 * <p>
 * Each association end is part of one association. The association ends of each association are ordered.
 * </p>
 * <p>
 * In the meta-model an AssociationEnd is part of an Association and specifies the connection of an Association to some other Classifier. Because
 * AssociationEnds are a kind of StructuralFeature, they are owned and ordered by Association* instances via the ClassifierFeature association. The
 * StructuralFeatureType association is used to identify the Classifier to which the AssociationEnd is attached. Each AssociationEnd has a name and
 * defines a set of properties of the connection.
 * </p>
 * <p>
 * The multiplicity property of an association end specifies how many instances of the classifier at a given end (the one bearing the multiplicity
 * value) may be associated with a single instance of the classifier at the other end. The association end also states whether or not the connection
 * may be traversed towards the instance playing that role in the connection (the isNavigable attribute); that is, if the instance is directly
 * reachable via the association.
 * </p>
 * <p>
 * <b>Constraints:</b>
 * </p>
 * <ul>
 * <li><b>[C-5-6]</b> - An AssociationEnd must have an owning Association.</li>
 * <li><b>[C-5-7]</b> - The Classifier of an AssociationEnd cannot be an Interface or a DataType if the association is navigable away from that
 * end.</li>
 * <li><b>[C-5-8]</b> - An Instance may not belong by composition to more than one composite Instance.</li>
 * <li><b>[C-5-9]</b> - An AssociationEnd with composite or shared aggregation semantics must be owned by an Association.</li>
 * </ul>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface AssociationEnd
extends
	GAssociationEnd<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Association
		, CwmClass
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
	 * Adds an importer (Package) to this Attribute, that will contain this Attribute as imported.
	 * 
	 * @param importer The specific package, that will contain this Attribute.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer );



//====================================================================================================================================================
// Feature capabilities
//====================================================================================================================================================



	/**
	 * The Classifier declaring the Feature.
	 * </p>
	 * <p>
	 * The ClassifierFeature association provides a composite aggregation containment relationship between Classifiers and the Features they own. The
	 * feature end of the association is ordered allowing preservation of the ordering of Features within their owning Classifier. A GFeature can be
	 * owned by at most one Classifier. The optional character of ownership is intended as a convenience to tools, allowing them to create Features
	 * prior to linking them to their owning Classifier.
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: Association</li>
	 * <li><i>defined by</i>	: Association::owner</li>
	 * <li><i>multiplicity</i>	: one</li>
	 * <li><i>inverse</i>		: AssociationEnd::feature</li>
	 * </ul>
	 *
     * <p>
     * <b>Constraints:</b>
     * </p>
     * <ul>
     * <li><b>[C-5-6]</b> - An AssociationEnd must have an owning Association.
     * <pre>
     * <b>context</b> AssociationEnd <b>inv</b>:
     * self.owner.oclIsKindOf( Association )
     * </pre>
     * </li>
     * </ul>
	 * 
	 * @param association The Association declaring the AssociationEnd.
	 * 
	 * @return The old value of owner, can't be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter type is <code>null</code>.
	 * 
	 */
	Association setOwner( Association association );



//====================================================================================================================================================
// StructuralFeature capabilities
//====================================================================================================================================================


	/**
	 * Sets a new reference to this <code>type</code>.
	 *  
	 * <ul>
	 * <li><i>class</i>: GClassifier</li>
	 * <li><i>defined by</i>: StructuralFeatureType::type</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param type The new GClassifier describing the type of this Attribute.
	 * 
	 * @return The old GClassifier describing the type of this Attribute. Can not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>type</code> is <code>null</code>. 
	 * 
	 */
	CwmClass setType( CwmClass type );
}