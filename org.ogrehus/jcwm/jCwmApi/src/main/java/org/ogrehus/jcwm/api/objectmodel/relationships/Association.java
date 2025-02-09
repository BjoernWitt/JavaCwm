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


import org.ogrehus.jcwm.api.foundation.keyindexes.Index;

import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.CwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.Changeable;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.classifierfeature.Featured;
import org.ogrehus.jcwm.api.objectmodel.relationships.util.Aggregation;
import org.ogrehus.jcwm.api.objectmodel.relationships.util.generic.GAssociation;


/**
 * An association defines a semantic relationship between classifiers. 
 * <p>
 * Associations have two or more named ends. Associations with two or more ends are called n-ary whereas associations with exactly two ends are 
 * called binary. Each end, depending upon its multiplicity, connects to zero or more instances of some classifier.
 * </p>
 * <p>
 * In the meta-model, an Association is a declaration of a semantic relationship between Classifiers, such as Classes. Associations must have two, and
 * may have more, association ends. Each end is connected to a Classifier; the same Classifier may be connected to more than one association end in 
 * the same association. (Refer to the ObjectModel's Instance package, below, for a description of how Associations are instantiated.)
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
 * <li><b>[C-5-1]</b> - An Association must have at least two AssociationEnds.</li>
 * <li><b>[C-5-2]</b> - The AssociationEnds must have a unique name within the association.</li>
 * <li><b>[C-5-3]</b> - At most one AssociationEnd may be an aggregation or composition.</li>
 * <li><b>[C-5-4]</b> - If an Association has three or more AssociationEnds, then no AssociationEnd may be an aggregation or composition.
 * <li><b>[C-5-5]</b> - The connected Classifiers of the AssociationEnds should be included in the Namespace of the Association, or be Classifiers 
 * with public visibility in other Namespaces to which the Association has access.</li> 
 * </ul>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Association
extends
	GAssociation<
		  GNamespace<?, ?, ?, ?, ?>
		, CwmPackage
		, GModelElement<?, ?, ?, ?>
		, GFeature<?, ?, ?, ?, ?> 
		, Index
	>
	, CwmClass
{
	
	
//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================
	
	
	/**
	 * Designates the Namespace that contains the Association. 
	 * 
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a 
	 * kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: CwmClass</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: CwmClass::ownedElement</li>
	 * </ul>
	 * 
	 * @param cwmClass The new namespace that contains this Association.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before. 
	 * 
	 */	
	GNamespace<?, ?, ?, ?, ?> setNamespace( CwmClass cwmClass );



	GNamespace<?, ?, ?, ?, ?> setNamespace( CwmPackage cwmPackage );



	GNamespace<?, ?, ?, ?, ?> setNamespace( Association association );



//====================================================================================================================================================
// GNamespace capabilities	
//====================================================================================================================================================

    
// none..

    
//====================================================================================================================================================
// Classifier capabilities	
//====================================================================================================================================================



	/**
	 * Creates a new instance of AssociationEnd by specific parameters and adds it to this Association.
	 * 
	 * @param name An identifier for the AssociationEnd  within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the AssociationEnd within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param changeability The changeability of this structuralFeature. Must not be <code>null</code>.
	 * 
	 * @param type The new Classifier describing the type of this AssociationEnd. Must not be <code>null</code>.
	 * 
	 * @param aggregation When placed on one end (the target end), specifies whether the class on the target end is an aggregation with respect to 
	 * the class on the other end (the source end). Only one end of an association can be an aggregation. May not be <code>null</code>.
	 * 
	 * @param isNavigateable When placed on a target end, specifies whether traversal from a source instance to its associated target instances is 
	 * possible. A value of <code>true</code> means that the association can be navigated by the source class and the target role-name can be used in 
	 * navigation expressions. Specification of navigability for each direction is defined independently. May not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter <code>name</code>, <code>visibility</code>, <code>changeability</code>, 
	 * <code>type</code>, <code>aggregation</code> or <code>isNavigateable</code> is <code>null</code>.
	 * 
	 */
	Boolean addAssociationEnd( 
		  String name
		, Visibility visibility
		, Changeable changeability
		, CwmClass type
		, Aggregation aggregation
		, Boolean isNavigateable
	);



	/**
	 * Adds an AssociationEnd that will be owned by this Association.
	 * 
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-5-2]</b> - The AssociationEnds must have a unique name within the association.<pre>
	 * <b>context</b> Association <b>inv</b>:
	 * self.allConnections -> forAll( r1, r2 | r1.name = r2.name implies r1 = r2 )
	 * </pre>
	 * </li>
	 * <li><b>[C-5-3]</b> - At most one AssociationEnd may be an aggregation or composition.<pre>
	 * <b>context</b> Association <b>inv</b>:
	 * self.allConnections -> select( aggregation <> Aggregation#none ) -> size <= 1
	 * </pre>
	 * </li>
	 * <li><b>[C-5-4]</b> - If an Association has three or more AssociationEnds, then no AssociationEnd may be an 
	 * aggregation or composition.<pre>
	 * <b>context</b> Association <b>inv</b>:
	 * self.allConnections -> size > 3 <b>implies</b>
	 *     self.allConnections -> forAll(  aggregation = Aggregation#none )
	 * </pre>
	 * </li>
	 * <li><b>[C-5-5]</b> - The connected Classifiers of the AssociationEnds should be included in the Namespace of the 
	 * Association, or be Classifiers with public visibility in other Namespaces to which the Association has access.
	 * <pre>
	 * <b>context</b> Association <b>inv</b>:
	 * self.allConnections -> forAll( r | self.namespace.allContents->includes ( r.type) ) <b>or</b>
	 * self.allConnections -> forAll( r | self.namespace.allContents->excludes ( r.type))
	 * <b>implies</b>
	 *     self.namespace.clientDependency->exists ( d |
	 *         d.supplier.oclAsType(Namespace).ownedElement->select ( e | 
	 *             e.elementOwnership.visibility = Visibility#_public )->includes ( r.type )
	 *         <b>or</b>
	 *         d.supplier.oclAsType( Classifier ).allParents.oclAsType( Namespace ).ownedElement -> select ( e |
	 *             e.elementOwnership.visibility = Visibility#_public )->includes ( r.type ) 
	 *         <b>or</b>
	 *         d.supplier.oclAsType( Package ).allImportedElements -> select ( e |
	 *             e.elementImport.visibility = Visibility#_public ) ->includes ( r.type ) ) )
	 * </pre>
	 * </li> 
	 * </ul>
	 * <p>
	 * 
	 * @param associationEnd An associationEnd that will be owned by this Association. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>associationEnd</code> is <code>null</code>.
	 * 
	 * @throws ConstratinViolation Is thrown if one of the Constraints  <b>[C-5-1]</b>, <b>[C-5-2]</b>, <b>[C-5-3]</b>, <b>[C-5-4]</b> or 
	 * <b>[C-5-5]</b> was violated.
	 * 
	 */	
	Boolean addFeature( AssociationEnd associationEnd );



	/**
	 * 
	 * 	
	 * @param Atttibute An Atttibute that will be owned by this Association. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>Atttibute</code> is <code>null</code>.
	 * 
	 */
	Boolean addFeature( Attribute Atttibute );	



	/**
	 * Removes a Featured owned by the Classifier.
	 *     
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-5-1]</b> - An Association must have at least two AssociationEnds.<pre>
	 * <b>context</b> Association <b>inv</b>:
	 * self.allConnections -> size > 1
	 * </pre>
	 * </li>
	 * </ul>
	 * 
	 * @param associationEnd The feature that will no longer be owned by this Classifier
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeFeature( Featured<?> associationEnd );
}