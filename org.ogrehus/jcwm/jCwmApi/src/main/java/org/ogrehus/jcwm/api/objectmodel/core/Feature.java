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

import org.ogrehus.jcwm.api.objectmodel.core.util.Scope;

public interface Feature
extends
	ModelElement
{


	/**
	 * Returns the value of the property <code>ownerScope</code>.
	 * <p>
	 * Specifies whether the GFeature appears in every instance of the Classifier or whether it appears only once for the entire Classifier.
	 * </p>
	 * <ul>
	 * <li><i>type</i>			: ScopeKind</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * </ul>
	 *
	 * @return The scope of the ownership. Might be <code>null</code>, if none exists. 
	 *  
	 */
	Scope getOwnerScope();



	/**
	 * Sets a new value of the property <code>ownerScope</code>.
	 * <ul>
	 * <li><i>type</i>			: ScopeKind</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * </ul>
	 *
	 * @see #getOwnerScope() for description of the property <code>ownerScope</code>.
	 * 
	 * @param ownerScope The value of the property <code>ownerScope</code>. 
	 * 
	 * @return The scope old value of the ownership if one exists before. 
	 *  
	 */
	Scope setOwnerScope( Scope ownerScope );
}