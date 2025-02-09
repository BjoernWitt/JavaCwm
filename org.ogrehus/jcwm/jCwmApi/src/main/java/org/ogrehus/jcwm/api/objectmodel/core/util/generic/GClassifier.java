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
package org.ogrehus.jcwm.api.objectmodel.core.util.generic;

import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.behavioral.CwmMethod;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.classifierfeature.Classifier;
import org.ogrehus.jcwm.api.objectmodel.relationships.Generalization;


/**
 * A classifier is an element that describes structural and behavioral features; it comes in several specific forms, including class, data type, 
 * interface, component, and others that are defined in other meta-model packages. 
 * 
 * <p>
 * GClassifier is often used as a type. In the meta-model a GClassifier may declare a collection of Features, such as Attributes, Operations, and 
 * Methods. It has a name, which is unique in the Namespace enclosing the GClassifier. GClassifier is an abstract meta-class.
 * </p>
 * <p>
 * GClassifier is a child of Namespace. As a Namespace, a GClassifier may declare other Classifiers nested in its scope. Nested Classifiers may be 
 * accessed by other Classifiers only if the nested Classifiers have adequate visibility. There are no data value or state consequences of nested 
 * Classifiers; that is, it is not an aggregation or composition.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the namespace that contains this Classifier.
 * @param <PACKAGE> The type of the importer of this Classifier.
 * @param <DEPENDENCY> The type of Dependency of this Classifier.
 * @param <CONSTRAINT> The type of the Constraint under which this Classifier is. 
 * @param <OWNED> Type of the ownedElements by this Classifier.  
 * @param <FEATURE> Type of the Feature of this Classifier. 
 * 
 */
public interface GClassifier<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWNED      extends GModelElement<?, ?, ?, ?>
	, FEATURE    extends GFeature<?, ?, ?, ?, ?>
>extends
	  GNamespace<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWNED>
	, Classifier<FEATURE>
{


	/**
	 * Returns whether this GClassifier is abstract or not.
	 * <p>
	 * An abstract GClassifier is not instantiable.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: Boolean</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 *  @return <code>true</code> if this GClassifier is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
	 */
	Boolean isAbstract();



	/**
	 * Sets the value of the property <code>isAbstract</code>.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: Boolean</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @see #isAbstract() for description of this property.
	 * 
	 * @param notInstantiable The new value of the property <code>abstract</code>.
	 * 
	 * @return The old value of the property <code>isAbstract</code>
	 * 
	 * @throws NullPointerException if the parameter <code>notInstantiable</code> is <code>null</code>.
	 *  
	 */
	Boolean setAbstract( Boolean notInstantiable );



	/**
	 * Identifies the set of Generalization instances in which the GClassifier acts as a child in the inheritance hierarchy.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Generalization</li> 
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 *  
	 * @return A set of Classifiers that act as a child in the inheritance hierarchy, or an empty Set if none exists.
	 * 
	 */
	Set<Generalization> getGeneralizations();



	Boolean addGeneralization( Generalization generalization );



	Boolean removeGeneralization( Generalization generalization );



	/**
	 * Identifies the set of Generalization instances in which the GClassifier acts as a child in the inheritance hierarchy.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Generalization</li> 
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 *  
	 * @return A set of Classifiers that act as a child in the inheritance hierarchy, or an empty Set if none exists.
	 * 
	 */
	Set<Generalization> getSpecializations();



	Boolean addSpecialization( Generalization specialization );



	Boolean removeSpecialization( Generalization specialization );



	/**
	 * The operation specification yields the set of Classifiers that the current GClassifier realizes.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * specification: Set(GClassifier)
	 * specification = self.clientDependency -> 
	 *     select(d | d.stereotype.name = "realization"
	 *     and 
	 *     d.supplier.oclIsKindOf(GClassifier)).supplier.oclAsType(GClassifier)
	 * </pre>
	 * 
	 * @return A set of Classifiers that the current GClassifier realizes, or an empty set if none exists.
	 * 
	 */
	Set<GClassifier<?, ?, ?, ?, ?, ?>> getSpecifications();



	/**
	 * The operation parent returns a Set containing all direct parents of a GClassifier.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * parent : Set(GClassifier);
	 * parent = self.generalization.parent
	 * </pre>
	 * 
	 * @return A HashSet containing all direct parents of this classifier, without this classifier itself, or an empty 
	 * one if none exists.
	 * 
	 */
	Set<GClassifier<?, ?, ?, ?, ?, ?>> getParents();



	/**
	 * The operation allParents returns a Set containing all the Classifiers inherited by this GClassifier (the transitive closure), excluding the 
	 * GClassifier itself.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allParents : Set(GClassifier);
	 * allParents = self.parent->union(self.parent.allParents)
	 * </pre>
	 * 
	 * @return A HashSet containing all direct parents of this classifier, or an empty set if none exists.
	 * 
	 */
	Set<GClassifier<?, ?, ?, ?, ?, ?>> getAllParents();



	/**
	 * The operation allContents returns a Set containing all ModelElements contained in the GClassifier together with the contents inherited from 
	 * its parents.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allContents : Set(ModelElement);
	 * allContents = self.contents -> union( self.parent.allContents 
	 *                                    -> select(e |  e.elementOwnership.visibility = #public 
	 *                                                or e.elementOwnership.visibility = #protected )
	 *                                )
	 * </pre>
	 * 
	 * @return A HashSet containing all direct parents of this classifier, or an empty set if none exists.
	 * 
	 */
	Set<ModelElement> getAllContents();



	/**
	 * The operation allFeatures results in a Set containing all Features of the GClassifier itself and all its inherited Features.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 *     allFeatures : Set(Featured);
	 *     allFeatures = self.feature->union(self.parent.oclAsType(GClassifier).allFeatures)
	 * </pre>
	 *  
	 * @return A set containing all {@link Featured}s accomplished to the OCL, or an empty set if none exists.
	 *  
	 */
	Set<GFeature<?, ?, ?, ?, ?>> getAllFeatures();



	/**
	 * The operation allAttributes results in a Set containing all Attributes of the GClassifier itself and all its inherited Attributes.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allAttributes : set(Attribute);
	 * allAttributes = self.allFeatures->select(f | f.oclIsKindOf(Attribute))
	 * </pre>
	 * 
	 *  
	 * @return A set containing all {@link Attribute}s accomplished to the OCL, or an empty set if none exists.
	 * 
	 */
	Set<Attribute> getAllAttributes();



	/**
	 * The operation allOperations results in a Set containing all Operations of the GClassifier itself and all its inherited Operations.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allOperations : set(Operation);
	 * allOperations = self.allFeatures->select( f | f.oclIsKindOf(Operation))
	 * </pre>
	 * 
	 *  
	 * @return A set containing all {@link Operation}s accomplished to the OCL, or an empty set if none exists.
	 * 
	 */
	Set<Operation> getAllOperations();



	/**
	 * The operation allMethods results in a Set containing all Methods of the GClassifier itself and all its inherited Methods.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allMethods : set(CwmMethod);
	 * allMethods = self.allFeatures->select( f | f.oclIsKindOf(CwmMethod))
	 * </pre>
	 * 
	 *  
	 * @return A set containing all {@link CwmMethod}s accomplished to the OCL, or an empty set if none exists.
	 * 
	 */
	Set<CwmMethod> getAllMethods();
}