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

import org.ogrehus.jcwm.api.Cwm;

/**
 * An element is an atomic constituent of a model. 
 * <p>
 * In the metamodel, an Element is the top metaclass in the metaclass hierarchy. Element is an abstract metaclass.
 * </p>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface Element {
	
	/**
	 * Return the root of the factory tree, available in jCwm.
	 * 
	 * <p>
	 * Use this to get a reference to the top-level factory instance, to allow further instantiation related to the same implementation already used 
	 * in the specific Element.
	 * </p>
	 * <pre>
	 * ...
	 *     Cwm       jCwmFactoryImpl = (Element)anyElement.getCwmFactory();
	 *     CwmString string          = jCwmFactoryImpl.objectmodel.core.createString();
	 * ...    
	 * </pre>  
	 * @return
	 */
	Cwm getCwmFactory();
}