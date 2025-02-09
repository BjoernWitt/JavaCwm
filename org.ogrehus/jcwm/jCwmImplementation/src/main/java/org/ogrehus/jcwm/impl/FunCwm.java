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
package org.ogrehus.jcwm.impl;

import org.ogrehus.jcwm.api.Cwm;
import org.ogrehus.jcwm.impl.foundation.FunFoundationPackage;
import org.ogrehus.jcwm.impl.objectmodel.FunObjectModel;
import org.ogrehus.jcwm.impl.resource.FunResourcePackage;


public class FunCwm
extends
	Cwm
{


	/**
	 * Creates a new Instance of FunCwm.
	 * 
	 * This Package allows navigation to underlying packages and their factory methods to create instances of the CWM.
	 * 
	 */
	public FunCwm() {
		this.objectmodel = new FunObjectModel();
		this.foundation  = new FunFoundationPackage();
		this.resource    = new FunResourcePackage();
	}
}