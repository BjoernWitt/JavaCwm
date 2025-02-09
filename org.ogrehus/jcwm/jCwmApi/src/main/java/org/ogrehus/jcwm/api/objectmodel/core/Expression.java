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


/**
 * In the meta-model an Expression defines a statement that will evaluate to a (possibly empty) set of instances when executed in a context. 
 * 
 * <p>
 * An Expression does not modify the environment in which it is evaluated. An expression contains an expression string and the name of an 
 * interpretation language with which to evaluate the string.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by 
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org,  August 2008
 *
 */
public interface Expression
extends
	Element 
{


	/**
	 * Returns the value of property <code>body</code>.
	 * <p>
	 * The text of the expression expressed in the given language.
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: String</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @return The text of the expression expressed in the given language. May not be <code>null</code>
	 * 
	 */
	String getBody();



	/**
	 * Sets the value of property <code>body</code>.
	 * <ul>
	 * <li><i>type</i>			: String</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 *
	 * @see #getBody() for description of the property <code>body</code>.
	 * 
	 * @param body The new value of the property <code>body</code>.
	 * 
	 * @return The old value of the property <code>body</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>body</code> s <code>null</code>.
	 *  
	 */
	String setBody( String body );



	/**
	 * Returns the value of the property <code>language</code>.
	 * <p>
	 * Names the language in which the expression body is represented. The interpretation of the expression depends on the language. If the language 
	 * name is omitted, no interpretation for the expression can be assumed. In general, a language name should be spelled and capitalized exactly as 
	 * it appears in the document defining the language. For example, use COBOL, not Cobol; use Ada, not ADA; use PostScript, not Postscript.
	 * </p>
	 * <ul>
	 * <li><i>type</i>: String</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 * @return The value of the property <code>language</code>, or <code>null</code> if none exists.
	 *  
	 */
	String getLanguage();



	/**
	 * Sets the value of the property <code>language</code>.
	 *  
	 * <ul>
	 * <li><i>type</i>: String</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 *
	 * @param language The new value of the property <code>language</code>.
	 * 
	 * @return The old value of the property <code>language</code>, or <code>null</code> if none exists. 
	 * 
	 * @see #getLanguage() for description of the property <code>body</code>.
	 * 
	 */
	String setLanguage( String language );
}