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

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * A generalization is a taxonomic relationship between a more general element and a more specific element.
 * <p>
 * The more specific element is fully consistent with the more general element (it has all of its properties, members, and relationships) and may 
 * contain additional information.
 * </p>
 * <p>
 * In the meta-model a Generalization is a directed inheritance relationship, uniting a Classifier with a more general Classifier in a hierarchy. 
 * Generalization is a subtyping relationship; that is, an instance of the more general (parent) Classifier may be substituted by an instance of the 
 * more specific (child) Classifier.
 * </p>
 * <p>
 * To understand inheritance fully, it is necessary to understand the concept of a full descriptor and a segment descriptor. A full descriptor is the 
 * full description needed to describe an instance of a meta-model object. It contains a description of all of the attributes, associations, and 
 * operations that the object contains.
 * </p>
 * <p>
 * In a pre-object-oriented language, the full descriptor of a data structure was declared directly in its entirety. In an object-oriented language, 
 * the description of an object is built out of incremental segments that are combined using inheritance to produce a full descriptor for an object. 
 * The segments are the modeling elements that are actually declared in a model. Each classifier contains a list of features and other relationships 
 * that it adds to what it inherits from its ancestors. The mechanism of inheritance defines how full descriptors are produced from a set of segments 
 * connected by generalization. The full descriptors are implicit, but they define the structure of actual instances. Features of a classifier that 
 * have private visibility are not visible to descendants of the classifier.
 * </p>
 * <p>
 * If a classifier has no parent, then its full descriptor is the same as its segment descriptor. If a classifier has one or more parents, then its 
 * full descriptor contains the union of the features from its own segment descriptor and the segment descriptors of all of its ancestors. No 
 * attribute, operation, or association end with the same signature may be declared in more than one of the segments (in other words, they may not be 
 * redefined). A method may be declared in more than one segment. A method declared in any segment supersedes and replaces a method with the same 
 * signature declared in any ancestor. If two or more methods nevertheless remain, then they conflict and the model is ill formed.The constraints on 
 * the full descriptor are the union of the constraints on the segment itself and all of its ancestors. If any of them are inconsistent, then the 
 * model is ill formed.
 * </p>
 * <p>
 * In any full descriptor for a classifier, each method must have a corresponding operation. In a concrete classifier, each operation in its full 
 * descriptor must have a corresponding method in the full descriptor.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Generalization
extends
	GModelElement<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
	> 
{


	/**
	 * Designates a Classifier that occupies the child or specialization position of the Generalization relationship.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: GClassifier</li>
	 * <li><i>defined by</i>: ChildElement::child</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return The value of reference <code>child</code>.
	 * 
	 */
	GClassifier< ?, ?, ?, ?, ?, ?> getChild();



	/**
	 * Sets the new value of reference child. 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Classifier</li>
	 * <li><i>defined by</i>: ChildElement::child</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @see #getChild for description on the reference.
	 *  
	 * @param child Designates a Classifier that occupies the child or specialization position of the Generalization relationship. Must not 
	 * be <code>null</code>.
	 * 
	 * @return The old value of the reference <code>child</code>.
	 * 
	 * @throws NullPointerException Is thrown if one the parameter: <code>child</code> is <code>null</code>.    
	 * 
	 */
	GClassifier< ?, ?, ?, ?, ?, ?> setChild( GClassifier< ?, ?, ?, ?, ?, ?> child );



	Boolean removeChild();



	/**
	 * Designates a Classifier that occupies the parent or generalization 
	 * position of the Generalization relationship.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Classifier</li>
	 * <li><i>defined by</i>: ParentElement::parent</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 * @return The value of reference <code>parent</code>.  
	 *  
	 */
	GClassifier< ?, ?, ?, ?, ?, ?> getParent();



	Boolean removeParent();



	/**
	 * Sets the value of reference parent. 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: GClassifier</li>
	 * <li><i>defined by</i>: ParentElement::parent</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 *
	 * @see #getParent() for description on the reference.
	 * 
	 * @param parent Designates a Classifier that occupies the parent or generalization position of the Generalization relationship. Must not 
	 * be <code>null</code>.
	 * 
	 * @return The old value of the reference <code>parent</code>.
	 * 
	 * @throws NullPointerException Is thrown if one the parameter: <code>parent</code> is <code>null</code>.    
	 *  
	 */
	GClassifier< ?, ?, ?, ?, ?, ?> setParent( GClassifier< ?, ?, ?, ?, ?, ?> parent );
}