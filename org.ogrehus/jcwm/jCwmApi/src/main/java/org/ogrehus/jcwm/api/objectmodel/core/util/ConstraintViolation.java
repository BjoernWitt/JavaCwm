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
package org.ogrehus.jcwm.api.objectmodel.core.util;

import java.util.Locale;


/**
 * A ConstraintException is the an exception that will be thrown if a constraint was violated.
 * <p>
 * See the CwmConstraint for more details
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2007
 * 
 */
public class ConstraintViolation
extends
	CwmRuntimeException 
{


	/**
	 * A UID represents an identifier that is unique over time with respect to the host it is generated on, or one of 216 "well-known" identifiers.
	 * 
	 */
	private static final long serialVersionUID = 1L;



	/**
	 * Property: violated
	 * <p>
	 * Identifier of the violation of the CWM-Constraint
	 * </p>
	 * 
	 * @see CwmConstraint for suitable values.
	 * 
	 */
	protected CwmConstraint violated = null;



	/**
	 * Property: objects
	 * 
	 * An Array of Objects, used to create the MessageFormat for this Exception.
	 * 
	 */
	protected Object[] objects = null;



	/** 
	 * Constructs a new CwmRuntimeException with the specified detail message.
	 * <p>
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 * </p>
	 *
	 * @param	violated Identifier of the violation of the CWM-Constraint.
	 * 
	 * @param   message   the detail message. The detail message is saved for 
	 *          later retrieval by the {@link #getMessage()} method.
	 *          
	 * @see 	CwmConstraint for suitable values.
	 * 
	 */
	public ConstraintViolation( CwmConstraint violated ) {
		this( violated, new Object[] {} );
	}



	/** 
	 * Constructs a new runtime exception with the specified cause and a detail message of <tt>(cause==null ? null : cause.toString())</tt> (which 
	 * typically contains the class and detail message of <tt>cause</tt>).  
	 * <p>
	 * This constructor is useful for runtime exceptions that are little more than wrappers for other throwables.
	 * </p>
	 *
	 * @param	violated Identifier of the violation of the CWM-Constraint.
	 * 
	 * @param  cause the cause (which is saved for later retrieval by the {@link #getCause()} method).  (A <tt>null</tt> value is permitted, and 
	 * indicates that the cause is nonexistent or unknown.)
	 * 
	 * @see 	CwmConstraint for suitable values.
	 * 
	 */
	public ConstraintViolation( CwmConstraint violated, Object ...objects ) {
		super();
		this.violated = violated;
		this.objects  = objects;
	}



	@Override
	public String getLocalizedMessage( Locale locale ) {
//        if ( this.objects == null ) {
//            return CwmConstraint.getMessage( this.violated, locale );
//        }
		return CwmConstraint.getMessage( this.violated, locale, this.objects );
	}
}