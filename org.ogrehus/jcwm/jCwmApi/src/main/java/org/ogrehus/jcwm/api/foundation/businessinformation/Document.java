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
package org.ogrehus.jcwm.api.foundation.businessinformation;

import java.util.Collection;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;

/**
 * The Document class represents externally stored descriptive information about some aspect of the modeled system. 
 * <p>
 * An instance of Document might be associated with one or more ModelElements. The name of a Document instance is derived from its super-classes.
 * </p>
 * <p>
 * Although the purposes of the Description and Document types may overlap somewhat, their chief distinction is that Description instances are stored
 * with the CWM meta-data whereas Documentation instances are stored externally to the CWM meta-data. Although there is an implication here that
 * Documentation instances might represent more voluminous information than Description instances, there is no particular requirement that this be so.
 * </p>
 * <p>
 * Because Documentation instances are themselves Namespace instances, hierarchical relationships between various externally stored documents can be 
 * represented.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-3-2]</b> A Document instance may not describe itself.</li>
 * </ul> 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Document
extends
	GNamespace<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, Document
	>
{

	/**
	 * Contains a textual representation of the identification, and perhaps the physical location, of externally maintained documentary information 
	 * about some aspect of the ModelElement(s) with which the Document instance is associated.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return A textual representation of the identification, and perhaps the physical location, of externally maintained documentary information 
	 * about some aspect of the ModelElement(s) with which the Document instance is associated. Can't be <code>null</code>.
	 * 
	 */
	String getReference();



	/**
	 * Contains a textual representation of the identification, and perhaps the physical location, of externally maintained documentary information 
	 * about some aspect of the ModelElement(s) with which the Document instance is associated.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param reference A textual representation of the identification, and perhaps the physical location, of externally maintained documentary 
	 * information about some aspect of the ModelElement(s) with which the Document instance is associated. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>reference</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>reference</code> is <code>null</code>.
	 * 
	 */
	String setReference( String reference );



	/**
	 * Contains a textual description of the type of information the Document represents.
	 * <p>
	 * Specific contents are usage defined.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return A textual description of the type of information the Document represents.
	 * 
	 */
	String getType();



	/**
	 * Contains a textual description of the type of information the Document represents.
	 * <p>
	 * Specific contents are usage defined.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @param type A textual description of the type of information the Document represents. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>type</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>type</code> is <code>null</code>.
	 * 
	 */
	String setType( String type );



	/**
	 * Identifies the ModelElement(s) for which this Document instance is relevant.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ModelElement</li>
	 * <li><i>defined by</i>    : DocumentDescribes::modelElement</li> 
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 * @return A Collection of ModelElement(s) for which this Document instance is relevant. Can't be <code>null</code>, but empty.
	 * 
	 */
	Collection<GModelElement<?, ?, ?, ?>> getModelElements();



	/**
	 * Adds a ModelElement to the reference of <code>modelElement</code>.
	 * <p>
	 * The reference of ModelElements is a set elements for which this Document instance is relevant.
	 * </p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ModelElement</li>
	 * <li><i>defined by</i>    : DocumentDescribes::modelElement</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-3-2]</b> A Document instance may not describe itself.</li>
	 * </ul> 
	 * 
	 * @param element A ModelElement for which this Document instance is relevant.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 * @throws ConstraintViolation If the Constraint [Foundation-C-3-2] is violated.
	 * 
	 */
	<E extends GModelElement<?, ?, ?, ?>> Boolean addModelElement( E element );



	/**
	 * Adds a ModelElement to the reference of <code>modelElement</code>.
	 * <p>
	 * The reference of ModelElements is a set elements for which this Document instance is relevant.
	 * </p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ModelElement</li>
	 * <li><i>defined by</i>    : DocumentDescribes::modelElement</li> 
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * @param element A ModelElement for which this Document instance is relevant.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 */
	<E extends GModelElement<?, ?, ?, ?>> Boolean removeModelElement( E element );
}