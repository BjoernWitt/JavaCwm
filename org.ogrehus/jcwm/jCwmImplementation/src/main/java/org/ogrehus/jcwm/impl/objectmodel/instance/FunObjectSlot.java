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
package org.ogrehus.jcwm.impl.objectmodel.instance;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;
import org.ogrehus.jcwm.api.objectmodel.instance.CwmObject;
import org.ogrehus.jcwm.api.objectmodel.instance.ObjectSlot;

import org.ogrehus.jcwm.impl.objectmodel.instance.util.generic.GFunSlot;


public class FunObjectSlot<
	FEATURE extends GStructuralFeature<?, ?, ?, ?, ?, ?>
>extends
	GFunSlot<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, CwmObject
		, FEATURE
		, CwmObject
> implements
	ObjectSlot<FEATURE> 
{


	/**
	 * Creates a new instance of this FunObjectSlot by specific parameters.
	 * 
	 * @param feature References the StructuralFeature instance that describes the value held by the Slot instance.
	 * 
	 * @return A new instance of Slot, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>feature</code>
	 * is <code>null</code>.
	 * 
	 */
	public FunObjectSlot( FEATURE feature ) {
		super( feature ); // throws NullPointerException
	}
}