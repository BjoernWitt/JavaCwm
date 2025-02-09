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
 * Instances of the Description class contain arbitrary textual information relevant to a particular ModelElement. 
 * <p>
 * While Description instances may contain any desired textual information, they will typically contain documentation or references to external 
 * reference information about the owning ModelElement.
 * </p>
 * <p>
 * Any ModelElement may have multiple Description instances associated with it. Indeed, a ModelElement instance that is a Description instance may 
 * itself have multiple Description instances linked to it. Also, a hierarchies of Description instances can be constructed.
 * </p>
 * <p>
 * Description instances are meant to hold descriptive textual information that will be stored in the meta-model itself. In contrast, Document 
 * instances are meant to describe the location documentary information stored outside the meta-model.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-3-1]</b> A Description instance may not describe itself.</li>
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
public interface Description
extends
	GNamespace<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, GModelElement<?, ?, ?, ?>
	>
{


	/**
	 * Contains a textual description of information pertaining to the owning ModelElement.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li> 
	 * </ul>
	 * 
	 * @return A textual description of information pertaining to the owning ModelElement. Can't be <code>null</code>.
	 * 
	 */
	String getBody();



	/**
	 * Contains a textual description of information pertaining to the owning ModelElement.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param body A textual description of information pertaining to the owning ModelElement. Must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>body</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>body</code> is <code>null</code>.
	 * 
	 */
	String setBody( String body );



	/**
	 * Contains an identification of the language in which the content of the body attribute is specified.
	 * <p>
	 * If desired, the language specification may be applied to the name attribute derived from ModelElement as well.
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
	 * @return An identification of the language in which the content of the body attribute is specified. Can't be <code>null</code>.
	 * 
	 */
	String getLanguage();



	/**
	 * Contains an identification of the language in which the content of the body attribute is specified.
	 * <p>
	 * If desired, the language specification may be applied to the name attribute derived from ModelElement as well.
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
	 * @param language An identification of the language in which the content of the body attribute is specified.
	 * 
	 * @return Old value of the property <code>language</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>language</code> is <code>null</code>.
	 * 
	 */
	String setLanguage( String language );



	/**
	 * Contains a textual description of the type of information the Description represents.
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
	 * @return A textual description of the type of information this Description represents. Can't be <code>null</code>.
	 * 
	 */
	String getType();



	/**
	 * Contains a textual description of the type of information the Description represents.
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
	 * @param type A textual description of the type of information this Description represents. must not be <code>null</code>.
	 * 
	 * @return Old value of the property <code>language</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>language</code> is <code>null</code>.
	 * 
	 */
	String setType( String type );



	/**
	 * Identifies the ModelElement for which this Description instance is relevant.
	 * <p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ModelElement</li>
	 * <li><i>defined by</i>    : ModelElementDescription::modelElement</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * @return A Collection of the ModelElements for which this Description instance is relevant. Can't be <code>null</code>.
	 * 
	 */
	Collection<GModelElement<?, ?, ?, ?>> getModelElements();



	/**
	 * Adds a ModelElement to the reference of <code>modelElement</code>.
	 * <p>
	 * The reference of ModelElements is a set elements for which this Description instance is relevant.
	 * </p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ModelElement</li>
	 * <li><i>defined by</i>    : ModelElementDescription::modelElement</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-3-1]</b> A Description instance may not describe itself.</li>
	 * </ul> 
	 * 
	 * @param element A ModelElement for which this Description instance is relevant.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 * @throws ConstraintViolation If the Constraint [Foundation-C-3-1] is violated.
	 * 
	 */
	<E extends GModelElement<?, ?, ?, ?>> Boolean addModelElement( E element );



	/**
	 * Adds a ModelElement to the reference of <code>modelElement</code>.
	 * <p>
	 * The reference of ModelElements is a set elements for which this Description instance is relevant.
	 * </p>
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : ModelElement</li>
	 * <li><i>defined by</i>    : ModelElementDescription::modelElement</li> 
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * @param element A ModelElement for which this Description instance is relevant.
	 * 
	 * @return <code>true</code> if this collection changed as a result of the call, otherwise <code>false</code>.
	 * 
	 */
	<E extends GModelElement<?, ?, ?, ?>> Boolean removeModelElement( E element );
}