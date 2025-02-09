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


import java.util.Set;

import org.ogrehus.jcwm.api.foundation.keyindexes.util.generic.GIndex;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;


/**
 * An association defines a semantic relationship between classifiers. 
 * <p>
 * Associations have two or more named ends. Associations with two or more ends are called n-ary whereas associations with exactly two ends are 
 * called binary. Each end, depending upon its multiplicity, connects to zero or more instances of some classifier.
 * </p>
 * <p>
 * In the meta-model, an GAssociation is a declaration of a semantic relationship between Classifiers, such as Classes. Associations must have two, 
 * and may have more, association ends. Each end is connected to a Classifier; the same Classifier may be connected to more than one association end 
 * in the same association. (Refer to the ObjectModel's Instance package, below, for a description of how Associations are instantiated.)
 * </p>
 * <p>
 * Because Associations are classifiers, they own and order their association ends (which are Attributes) via the ClassifierFeature association. In 
 * addition, because Associations are Classes, they can also own more traditional StructuralFeatures such as Attributes. Consequently, they may act in
 * a manner similar to association classes described by some other object models.
 * </p>
 * <p>
 * An association may represent an aggregation; that is, a whole/part relationship. In this case, the association end attached to the whole element is
 * designated, and the other association end represents the parts of the aggregation.
 * </p>
 * <p>
 * Associations can be of three different kinds:
 * </p> 
 * <ol>
 * <li>ordinary association</li>
 * <li>composite aggregate</li>
 * <li>and shareable aggregate</li>
 * </ol>
 * <p>
 * Since the aggregate construct can have several different meanings depending on the application area, CWM gives a more precise meaning to two of 
 * these constructs; that is, association and composite aggregate and leaves the shareable aggregate more loosely defined in between. Only binary 
 * Associations can have composite or sharable aggregation semantics.
 * </p>
 * <p>
 * Composite aggregation is a strong form of aggregation, which requires that a part instance be included in at most one composite at a time and that 
 * the composite object has sole responsibility for the disposition of its parts. This means that the composite object is responsible for the creation
 * and destruction of the parts. In implementation terms, it is responsible for their memory allocation. If a composite object is destroyed, it must 
 * destroy all of its parts. It may remove a part and give it to another composite object, which then assumes responsibility for it. If the 
 * multiplicity from a part to composite is zero-to-one, the composite may remove the part and the part may assume responsibility for itself, 
 * otherwise it may not live apart from a composite.
 * </p>
 * <p>
 * A consequence of these rules is that a composite aggregation implies propagation semantics; that is, some of the dynamic semantics of the whole is 
 * propagated to its parts. For example, if the whole is copied or destroyed, then so are the parts as well (because a part may belong to at most one 
 * composite). 
 * </p>
 * <p>
 * A classifier on the composite end of an association may have parts that are classifiers and associations. At the instance level, an instance of a 
 * part element is considered part of the instance of a composite element. If an association is part of a composite and it connects two classes that
 * are also part of the same composite, then an instance of the association will connect objects that are part of the same composite object of which 
 * the instance is 
 * part.
 * </p>
 * <p>
 * A shareable aggregation denotes weak ownership; that is, the part may be included in several aggregates and its owner may also change over time. 
 * However, the semantics of a shareable aggregation does not imply deletion of the parts when an aggregate referencing it is deleted. Both kinds of 
 * aggregations define a transitive, antisymmetric relationship; that is, the instances form a directed, non-cyclic graph. Composition instances form 
 * a strict tree (or rather a forest).
 * </p>
 * <p>
 * <b>Constraints:</b>
 * </p>
 * <ul>
 * <li><b>[C-5-1]</b> - An GAssociation must have at least two AssociationEnds.</li>
 * <li><b>[C-5-2]</b> - The AssociationEnds must have a unique name within the association.</li>
 * <li><b>[C-5-3]</b> - At most one AssociationEnd may be an aggregation or composition.</li>
 * <li><b>[C-5-4]</b> - If an GAssociation has three or more AssociationEnds, then no AssociationEnd may be an aggregation or composition.
 * <li><b>[C-5-5]</b> - The connected Classifiers of the AssociationEnds should be included in the Namespace of the GAssociation, or be Classifiers 
 * with public visibility in other Namespaces to which the GAssociation has access.</li> 
 * </ul>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the namespace that contains this Association.
 * @param <PACKAGE> The type of the importer of this GAssociation.
 * @param <OWNED> Type of the ownedElements owned by this Association.  
 * @param <FEATURE> Type of the Feature of this Association. 
* @param <INDEX> Identifies the type if Index instances that span this Association. 
 */
public interface GAssociation<
	  NAMESPACE extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE   extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, OWNED     extends GModelElement<?, ?, ?, ?>
	, FEATURE   extends GFeature<?, ?, ?, ?, ?> 
	, INDEX     extends GIndex<?, ?, ?, ?>
> extends
	GCwmClass<NAMESPACE, PACKAGE, Dependency, Constraint, OWNED, FEATURE, INDEX>
{


//====================================================================================================================================================
// GAssociation capabilities	
//====================================================================================================================================================



	/**
	 * The operation allConnections results in the set of all AssociationEnds of the GAssociation.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allConnections : Set(ASSOCIATION_END);
	 * allConnections = self.feature.oclIsKindOf(ASSOCIATION_END)
	 * </pre>
	 * 
	 * @param typeof The type of the conneciton that will be collected.
	 * 
	 * @return A HashSet containing all set of all AssociationEnds of the GAssociation, or an empty set if none exists.
	 * 
	 * @param <END> Type of AssociationEnds that must be collected in the set of connections.
	 * 
	 */
	public <END extends GAssociationEnd<?, ?, ?, ?>> Set<END> allConnections( Class<END> typeof );



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Adds a dependency to this CwmClass.
	 * 
	 * @param dependency A Dependency in witch this CwmClass is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this CwmClass.
	 * 
	 * @param constraint A Constrain that must be satisfied by this CwmClass.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );	



//====================================================================================================================================================
// Classifier capabilities	
//====================================================================================================================================================



	/**
	 * Removes a Feature owned by the Classifier.
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-5-1]</b> - An GAssociation must have at least two AssociationEnds.<pre>
	 * <b>context</b> GAssociation <b>inv</b>:
	 * self.allConnections -> size > 1
	 * </pre>
	 * </li>
	 * </ul>
	 * 
	 * @param associationEnd The feature that will no longer be owned by this Classifier
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws ConstraintViolation Is thrown in case of the Constratint <b>[C-5-1]</b> is violated while removing an associationEnd.
	 * 
	 */
	Boolean removeFeature( GAssociationEnd<?, ?, ?, ?> associationEnd );
}