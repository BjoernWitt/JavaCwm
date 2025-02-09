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
package org.ogrehus.jcwm.impl.objectmodel.core;

import org.ogrehus.jcwm.api.Cwm;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.impl.FunCwm;

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
public class FunExpression
implements
	Expression
{



	/**
	 * Creates a new instance of Expression by specific parameter.
	 * 
	 * @param body The text of the expression expressed in the given language. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>body</code> is <code>null</code>.
	 * 
	 */
	public FunExpression( String body ) {
		super();
		setBody( body ); // may throw NullPointerException
		try {
			this.cwmFactory = Cwm.create( FunCwm.class.getName() );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// can't occur, cause otherwise this class would be missing too! 
		}
	}



	public Cwm getCwmFactory() {
		return this.cwmFactory;
	}	



	public String getBody() {
		return this.body;
	}



	public String setBody( String body ) {
		if ( body == null ) {
			throw new NullPointerException( "Parameter: body may not be null." );
		}		
		String old = this.body;
		this.body = body;

		return old;
	}



	public String getLanguage() {
		return this.language;
	}



	public String setLanguage( String language ) {
		String old = this.language;
		this.language = language;

		return old;
	}



//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * Attribute: body
	 * 
	 * The text of the expression expressed in the given language.
	 * 
	 * <ul>
	 * <li><i>type</i>: String</li>
	 * <li><i>multiplicity</i>: exactly one</li>
	 * </ul>
	 * 
	 */
	protected String body    = null;



	/**
	 * Attribute: language
	 * 
	 * Names the language in which the expression body is represented. The interpretation of the expression depends on the language. If the language
	 * name is omitted, no interpretation for the expression can be assumed. In general, a language name should be spelled and capitalized exactly as
	 * it appears in the document defining the language. For example, use COBOL, not Cobol; use Ada, not ADA; use PostScript, not Postscript.
	 * 
	 * <ul>
	 * <li><i>type</i>: String</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 */
	protected String language = null;



	protected Cwm cwmFactory  = null;
}