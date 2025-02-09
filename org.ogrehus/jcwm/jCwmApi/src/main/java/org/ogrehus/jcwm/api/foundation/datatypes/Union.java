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
package org.ogrehus.jcwm.api.foundation.datatypes;

import java.util.Collection;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;


/**
 * The Union class represents programming language unions and similarly structured data types. 
 * 
 * <p>
 * Because of the diversity of union semantics found across software systems, the Union and UnionMember classes are likely candidates for 
 * specialization to better capture union semantics in specific language extension packages.
 * <p>
 * A discriminated Union has a collection of UnionMembers that determine the sets of contents that the Union may contain. Such Unions have an 
 * attribute called the discriminator that identifies the memberCase value of the UnionMember that the Union currently contains. The discriminator is 
 * found via the UnionDiscriminator association to StructuralFeature. The discriminator may be embedded within UnionMembers or may be located outside 
 * the discriminator. If it is located within UnionMembers, the discriminator should occur in every UnionMember at the same location (often, the 
 * first).
 * </p>
 * <p>
 * Undiscriminated unions (for example, a C language union) are also supported, but have an empty discriminator reference, and the memberCase 
 * attribute of the UnionMembers it contains is ignored.
 * </p>
 * <p>
 * Undiscriminated Unions are often used to represent alternate views of a single physical storage area. A fine degree of control over this aspect of 
 * Unions may be obtained by creating a class that derives from both UnionMember and FixedOffsetField (in the CWM Record package) and setting the 
 * offset attribute instances of that class accordingly.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation_C-4-2]</b> A Union can have at most one default UnionMember instance.</li>
 * </ul>
 *  
 * <p>
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Union
extends
	GClassifier<
		  GNamespace< ?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, UnionMember
		, UnionMember	
	>
{



//====================================================================================================================================================
// Union capabilities	
//====================================================================================================================================================



	/**
	 * Identifies the StructuralFeature instance that serves as the discriminator for the Union.
	 * <p>
	 * The UnionDiscriminator association connects a Union instance with the StructuralFeature instance that can be used to determine which 
	 * UnionMember instance is currently present in the Union instance. This discriminating attribute may be a feature of the UnionMembers themselves 
	 * or may be a feature of some Classifier that contains the Union instance as one of its Features. In the former case, the discriminating feature 
	 * will usually be present at the same offset in each UnionMember instance. If the discriminator reference is empty for a particular Union 
	 * instance, it is considered to be an undiscriminated Union and determination of the current UnionMember residing in the Union is usage-defined.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : StructuralFeature</li>
	 * <li><i>defined by</i> 	: UnionDiscriminator::discriminator</li> 
	 * <li><i>multiplicity</i> 	: zero or more</li>
	 * </ul>
	 *
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[Foundation_C_4_2]</b> A Union can have at most one default UnionMember instance.</li>
	 * </ul>
	 * 
	 * @return A Collection of StructuralFeature instances, that serves as the discriminator for this Union. Can't be <code>null</code>, but can 
	 * return an empty Collection.
	 * 
	 */
	Collection<GStructuralFeature<?, ?, ?, ?, ?, ?>> getDiscriminator(); 



	/**
	 * Adds a StructuralFeature instance that serves as the discriminator for the Union.
	 * <p>
	 * The UnionDiscriminator association connects a Union instance with the StructuralFeature instance that can be used to determine which 
	 * UnionMember instance is currently present in the Union instance. This discriminating attribute may be a feature of the UnionMembers themselves 
	 * or may be a feature of some Classifier that contains the Union instance as one of its Features. In the former case, the discriminating feature 
	 * will usually be present at the same offset in each UnionMember instance. If the discriminator reference is empty for a particular Union 
	 * instance, it is considered to be an undiscriminated Union and determination of the current UnionMember residing in the Union is usage-defined.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : GStructuralFeature</li>
	 * <li><i>defined by</i> 	: UnionDiscriminator::discriminator</li> 
	 * <li><i>multiplicity</i> 	: zero or more</li>
	 * </ul>
	 * 
	 * @param structuralFeature
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise. 
	 * 
	 */
	Boolean addDiscriminator( GStructuralFeature<?, ?, ?, ?, ?, ?> structuralFeature );



	/**
	 * Removes a StructuralFeature instance from this Union.
	 * 
	 * @param structuralFeature The StructuralFeature to be removed.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeDiscriminator( GStructuralFeature<?, ?, ?, ?, ?, ?> structuralFeature );	



//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the Union. 
	 * <p>
	 * Every Model Element except a root element must belong to exactly one Namespace or else be a composite part of another ModelElement (which is a 
	 * kind of virtual namespace).
	 * </p>
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>  
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: GNamespace</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Namespace::ownedElement</li>
	 * </ul>
	 * 
	 * @param namespace The new namespace that contains this Union.
	 * 
	 * @return The namespace that contains this Union before, or <code>null</code> none was assigned before. 
	 * 
	 */	
	GNamespace< ?, ?, ?, ?, ?> setNamespace( GNamespace< ?, ?, ?, ?, ?> namespace );	



	/**
	 * Adds an importer (Package) to this Union, that will contain this Union as imported.
	 * 
	 * @param importer The specific package, that will contain this Union.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addImporter( GCwmPackage<?, ?, ?, ?, ?, ?, ?> importer );



	/**
	 * Adds a dependency to this Union.
	 * 
	 * @param dependency A Dependency in witch this Union is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( GDependency<?, ?, ?, ?, ?> dependency );		



	/**
	 * Adds a constraint to this Union.
	 * 
	 * @param constraint A Constrain that must be satisfied by this Union.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( GConstraint<?, ?, ?, ?, ?> constraint );		



//====================================================================================================================================================
// GNamespace capabilities	
//====================================================================================================================================================



	/**
	 * Adds a UnionMember to the reference of <code>ownedElements</code>.
	 * <p>
	 * The reference of ModelElements is a set owned elements by the this namespace. The ModelElement visibility attribute states whether the 
	 * element is visible outside the namespace.
	 * </p>
	 * 
	 * <p>
	 * This method registers this type of namespace to the new ownedElement for inverse traversal.
	 * </p>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[Foundation_C_4_2]</b> A Union can have at most one default UnionMember instance.
	 * <pre>
	 * <b>context</b> Union <b>inv</b>:
	 * self.allFeatures -> select( isDefault ) -> size <= 1
	 * </pre>
	 * </li>
	 * </ul>
	 *  
	 * @param unionMember A new ownedElement from this Union. It must nut violate the Constraint  
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call.
	 * 
	 */	
	Boolean addOwnedElement( UnionMember unionMember );



//====================================================================================================================================================
// Classifier capabilities	
//====================================================================================================================================================



	/**
	 * Appends a UnionMember that will be owned by this Union.
	 * 
	 * @param unionMember that will be owned by this Union. 
	 *
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addFeature( UnionMember unionMember );
}