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
package org.ogrehus.jcwm.api.foundation.softwaredeployment;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;


/**
 * A ProviderConnection represents a connection that allows a DataProvider acting as a client to access data from a specific DataManager.
 *  
 * <p>
 * For example a ProviderConnection could represent a connection from an ODBC or JDBC client to a DBMS.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-8-2]</b> A ProviderConnection must not associate a DataProvider with itself.</li>
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
public interface ProviderConnection
extends
	ModelElement
{


//====================================================================================================================================================
// ProviderConnection capabilities	
//====================================================================================================================================================



	/**
	 * Indicates whether the ProviderConnection only allows read access to the DataManager.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>          : Boolean</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 * 
	 * @return <code>true</code> if the ProviderConnection only allows read access to the DataManager, <code>false</code> otherwise. Can't be 
	 * <code>null</code>.
	 * 
	 */ 
	Boolean isReadOnly();



	/**
	 * Indicates whether the ProviderConnection only allows read access to the 
	 * DataManager.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Boolean</li>
	 * <li><i>multiplicity</i>  : zero or more</li> 
	 * </ul>
	 *  
	 * @param readOnly <code>true</code> if the ProviderConnection only allows read access to the DataManager, <code>false</code> otherwise. Must not 
	 * be <code>null</code>.
	 * 
	 * @return Old value of the property <code>readOnly</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>readOnly</code> is <code>null</code>. 
	 * 
	 */
	Boolean setReadOnly( Boolean readOnly );
}