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

import java.util.Collection;

import org.ogrehus.jcwm.api.foundation.businessinformation.Description;
import org.ogrehus.jcwm.api.foundation.businessinformation.Document;
import org.ogrehus.jcwm.api.foundation.businessinformation.ResponsibleParty;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;


/**
 * A model element is an element that is an abstraction drawn from the system being modeled.
 * <p>
 * In the meta-model a ModelElement is a named entity in a Model. It is the base for all modeling meta-classes in the CWM. All other modeling
 * meta-classes are either direct or indirect subclasses of ModelElement.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface ModelElement
extends
	 Element
	, Comparable<ModelElement>
{


	/**
	 * An identifier for the ModelElement within its containing Namespace.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		: Name</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return A Name that defines a token that is used for named ModelElements.
	 * 
	 */
	Name getName();



	/**
	 * Sets the value of name attribute. See {@link #getName} for description on the attribute.
	 * <p>
	 * A Name defines a token that is used for naming ModelElements.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>         : Name</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @param name The name is the identifier for the ModelElement within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @return The old name.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>name</code> was <code>null</code>.
	 * 
	 */
	Name setName( String name );



	/**
	 * There are two forms of names: simple names and qualified names:
	 * <ul>
	 * <li>A <b>simple name</b> is a single identifier.</li>
	 * <li>A <b>qualified name</b> consists of a name, a "." token, and an identifier.</li>
	 * <ul>
	 * 
	 * @return simple name
	 * 
	 */
	String getSimpleName();



	/**
	 * There are two forms of names: simple names and qualified names:
	 * <ul>
	 * <li>A <b>simple name</b> is a single identifier.</li>
	 * <li>A <b>qualified name</b> consists of a name, a "." token, and an identifier.</li>
	 * </ul>
	 * 
	 * @return qualifiedName of this ModelElement without use of a surroundingSymbol and default separatorSymbol "."
	 * 
	 */
	String getQualifiedName();



	/**
	 * There are two forms of name: simple and qualified name:
	 * <ul>
	 * <li>A <b>simple name</b> is a single identifier of the element within its namespace.</li>
	 * <li>A <b>qualified name</b> consists of a name, a "." token, and an identifier building the hierarchical step of the element within its 
	 * namespaces.</li>
	 * </ul>
	 * 
	 * The use of the following parameters depend on the related System, for example JDBC determines the <code>separatorSymbol</code> by requesting:
	 * <code>separatorSymbol</code>
	 * 
	 * @param separatorSymbol This symbol separates each token that represents a hierarchical step in namespaces.
	 * 
	 * @param surroundingSymbol This symbol delimiter will embed each token that represents a hierarchical step in namespaces. Use <code>null</code>
	 * or a zero-string if no surroundingSymbol should be used.
	 * 
	 * @return simple name
	 * 
	 */
	String getQualifiedName( String separatorSymbol, String surroundingSymbol );



	/**
	 * Specifies extent of the visibility of the ModelElement within its owning Namespace.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>         : Visibility</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @return The Visibility assigned to the ModelElement. Can not be null.
	 * 
	 */
	Visibility getVisibility();



	/**
	 * Sets the value of visibility attribute. See {@link #getVisibility} for description on the attribute.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: Visibility</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param visibility The kind of visibility provides to this ModelElement. Must not be <code>null</code>.
	 * 
	 * @return The Visibility assigned previously, or null if none was assigned before.
	 * 
	 * @throws NullPointerException is thrown if the parameter visibility was <code>null</code>.
	 * 
	 */
	Visibility setVisibility( Visibility visibility );



	/**
	 * References the set of TaggedValue instances that extend the ModelElement. (comes new in Version 1.1)
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: TaggedValue</li>
	 * <li><i>defined by</i>	: TaggedElement::taggedValue</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: TaggedValue::modelElement</li>
	 * </ul>
	 * 
	 * @return A Set of TaggedValue instances that extend the ModelElement
	 * 
	 */
	Collection<TaggedValue> getTaggedValues();



	/**
	 * Inverse of <code>extendetElement</code>.
	 * <p>
	 * Identifies the Stereotype instance that further defines the semantics of the ModelElement.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Stereotype</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @param stereotype The stereotype, that defines the semantic of the Model Element.
	 * 
	 * @return The old value of the property <code>stereotype</code>.
	 * 
	 */
	Stereotype setStereotype( Stereotype stereotype );



	/**
	 * Inverse of <code>extendetElement</code>.
	 * <p>
	 * Identifies the Stereotype instance that further defines the semantics of the ModelElement.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Stereotype</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @return The stereotype, that defines the semantic of the Model Element or <code>null</code> if none exists.
	 * 
	 */
	Stereotype getStereotype();



	String setRemarks( String remarks );



	String getRemarks();



//====================================================================================================================================================
// BusinessInformationen
//====================================================================================================================================================



	/**
	 * Adds a Document to the instances that documents this element.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Document</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 * 
	 * @param document The related document to this element.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDocument( Document document );



	/**
	 * Removes a Document from the instances that documents this element.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Document</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 * 
	 * @param document The related document to be removed.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeDocument( Document document );



	/**
	 * Returns the Document instance that documents this element.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Document</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @return The related documents of this element or <code>null</code> if none exists.
	 * 
	 */
	Collection<Document> getDocuments();



	/**
	 * Adds a Description to the instances that describe this element.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Description</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 * 
	 * @param description The related description for this element.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addDescription( Description description );



	/**
	 * Removes a Description from the instances that describe this element.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: Description</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 * 
	 * @param description The related description to be removed.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeDescription( Description description );



	/**
	 * Returns the Description instance that describes this element.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Description</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @return The related documents of this element or <code>null</code> if none exists.
	 * 
	 */
	Collection<Description> getDescriptions();



	/**
	 * Adds a ResponsibleParty instance that is responsible for this element.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: ResponsibleParty</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 * 
	 * @param responsibleParty An instance that is responsible for this element.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addResponsibleParty( ResponsibleParty responsibleParty );



	/**
	 * Removes a ResponsibleParty from the instances that describe this element.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: ResponsibleParty</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 * 
	 * @param responsibleParty The related responsibleParty to be removed.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeResponsibleParty( ResponsibleParty responsibleParty );



	/**
	 * Returns the ResponsibleParty instance that is responsible for this element.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>: ResponsibleParty</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @return The related ResponsibleParty for this element or <code>null</code> if none exists.
	 * 
	 */
	Collection<ResponsibleParty> getResponsibleParties();
}