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
package org.ogrehus.jcwm.impl.foundation.typemapping;

import org.ogrehus.jcwm.api.foundation.typemapping.TypeMapping;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeMappingPackage;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;

public class FunTypeMappingPackage
extends
	TypeMappingPackage
{


	
	@Override
	public TypeMapping createTypeMapping(
		  String name
		, Visibility visibility
		, Boolean isBestMatch
		, Boolean isLossy
		, GClassifier<?, ?, ?, ?, ?, ?> sourceType 
		, GClassifier<?, ?, ?, ?, ?, ?> targetType
	) {
		return new FunTypeMapping( name, visibility, isBestMatch, isLossy, sourceType, targetType );
	}



	@Override
	public TypeSystem createTypeSystem( String name, Visibility visibility, String version ) {
		return new FunTypeSystem( name, visibility, version );
	}
}