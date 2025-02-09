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
 * A CwmException is the a general exception that can be thrown while working with the CWM.
 * <p>
 * Any other Exception inherited from the CwnmException will have its own context within this CWM, like a violated constraint, not implemented methods 
 * or any more cases.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2007
 * 
 */
public abstract class CwmRuntimeException
extends
	RuntimeException
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/** 
	 * Constructs a CwmRuntimeException with <code>null</code> as its detail message.  The cause is not initialized, and may subsequently be 
	 * initialized by a call to {@link #initCause}.
	 * 
	 */
	public CwmRuntimeException() {
		super();
	}	



	/** 
	 * Constructs a new CwmRuntimeException with the specified detail message.
	 * <p>
	 * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
	 * </p>
	 *
	 * @param   message   the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
	 */
	public CwmRuntimeException( String message ) {
		super( message );
	}



	/**
	 * Constructs a new runtime exception with the specified detail message and cause.  
	 * <p>
	 * Note that the detail message associated with <code>cause</code> is <i>not</i> automatically incorporated in this runtime exception's 
	 * detail message.
	 * </p>
	 *
	 * @param  message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
	 * 
	 * @param  cause the cause (which is saved for later retrieval by the {@link #getCause()} method).  (A <tt>null</tt> value is permitted, and 
	 * indicates that the cause is nonexistent or unknown.)
	 * 
	 */
	public CwmRuntimeException( String message, Throwable cause ) {
		super( message, cause );
	}



	/** 
	 * Constructs a new runtime exception with the specified cause and a detail message of <tt>(cause==null ? null : cause.toString())</tt>
	 * (which typically contains the class and detail message of <tt>cause</tt>).  
	 * <p>
	 * This constructor is useful for runtime exceptions that are little more than wrappers for other throwables.
	 * </p>
	 *
	 * @param  cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt> value is permitted, and 
	 * indicates that the cause is nonexistent or unknown.)
	 * 
	 */
	public CwmRuntimeException( Throwable cause ) {
		super( cause );
	}



	@Override
	public String getLocalizedMessage() {
		return getLocalizedMessage( Locale.getDefault() );
	}



	/**
	 * Localizes the Message of this Exception.
	 * 
	 * @param locale The Locale to be used to perform i18n.
	 * 
	 * @return A localized Message, describing this Exception.
	 * 
	 */
	public String getLocalizedMessage( Locale locale ) {
		return super.getLocalizedMessage();
	}
}
