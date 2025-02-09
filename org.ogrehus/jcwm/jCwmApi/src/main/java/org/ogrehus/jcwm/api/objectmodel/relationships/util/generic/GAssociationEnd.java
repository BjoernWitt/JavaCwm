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
package org.ogrehus.jcwm.api.objectmodel.relationships.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.relationships.util.Aggregation;

/**
 * An association end is an end-point of an association that connects the association to a classifier. 
 * <p>
 * Each association end is part of one association. The association ends of each association are ordered.
 * </p>
 * <p>
 * In the meta-model an AssociationEnd is part of an GAssociation and specifies the connection of an GAssociation to some other Classifier. Because 
 * AssociationEnds are a kind of StructuralFeature, they are owned and ordered by GAssociation instances via the ClassifierFeature association. The 
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
 * <li><b>[C-5-6]</b> - An AssociationEnd must have an owning GAssociation.</li>
 * <li><b>[C-5-7]</b> - The Classifier of an AssociationEnd cannot be an Interface or a DataType if the association is navigable away from that 
 * end.</li>
 * <li><b>[C-5-8]</b> - An Instance may not belong by composition to more than one composite Instance.</li>
 * <li><b>[C-5-9]</b> - An AssociationEnd with composite or shared aggregation semantics must be owned by an GAssociation.</li>
 * </ul>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GAssociationEnd.
 * @param <PACKAGE> The type of the importer of this GAssociationEnd.
 * @param <ASSOCIATION> The type of the Classifier that owns this GAssociationEnd.
 * @param <TYPE> The type of a GAssociationEnd.  
 *
 */
public interface GAssociationEnd<
	  NAMESPACE   extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE     extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, ASSOCIATION extends GAssociation< ?, ?, ?, ?, ?>
	, TYPE        extends GClassifier< ?, ?, ?, ?, ?, ?>
> extends
	GStructuralFeature<NAMESPACE, PACKAGE, Dependency, Constraint, ASSOCIATION, TYPE>
{


	/**
	 * Attribute: aggregation
	 * <p> 
	 * When placed on one end (the target end), specifies whether the class on the target end is an aggregation with respect to the class on the other
	 * end (the source end). Only one end of an association can be an aggregation.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: Aggregation</li> 
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * 
	 * @return The type of the aggregation.
	 * 
	 */
	Aggregation getAggregation();



	/**
	 * Attribute: aggregation
	 * <p>
	 * When placed on one end (the target end), specifies whether the class on the target end is an aggregation with 
	 * respect to the class on the other end (the source end). Only one end of an association can be an aggregation.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: AggregationKind</li> 
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @param aggregation New value for the property <code>aggregation</code>. May not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>aggregation</code>, before the new value will be set. May not be <code>null</code> 
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>aggregation</code> is <code>null</code>.
	 * 
	 */
	Aggregation setAggregation( Aggregation aggregation );



	/**
	 * Attribute: isNavigateable
	 * <p>
	 * When placed on a target end, specifies whether traversal from a source instance to its associated target instances is possible. A value of true
	 * means that the association can be navigated by the source class and the target role-name can be used in navigation expressions. Specification 
	 * of navigability for each direction is defined independently.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: Boolean</li> 
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the association can be navigated by the source class and the target role-name can be used in navigation 
	 * expressions, <code>false</code> otherwise.
	 * 
	 */
	Boolean isNavigateable();



	/**
	 * Attribute: isNavigateable
	 * <p>
	 * When placed on a target end, specifies whether traversal from a source instance to its associated target instances is possible. A value of true
	 * means that the association can be navigated by the source class and the target role-name can be used in navigation expressions. Specification 
	 * of navigability for each direction is defined independently.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>type</i>: Boolean</li> 
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @see #isNavigateable() for description on the property <code>isNavigateable</code>.
	 * 
	 * @param navigatable The new value for the property <code>isNavigateable</code>. May not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>isNavigateable</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>aggregation</code> is <code>null</code>.  
	 * 
	 */
	Boolean setNavigateable( Boolean navigatable );



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



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
	 * Adds another Constraint to this Attribute.
	 * 
	 * @param constraint A Constrain that must be satisfied by this Attribute.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );
}