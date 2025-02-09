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
package org.ogrehus.jcwm.api.foundation.datatypes.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;


/**
 * The TypeAlias class is intended to provide a renaming capability for Classifier instances. 
 * <p>
 * This class is required to support situations in which creation of an alias for a class effectively creates a new  class. For example, CORBA IDL 
 * type aliases have different typeCodes than their base types and are therefore treated as distinct types.
 * </p>
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[Foundation_C-4-1]</b> A TypeAlias instance cannot alias itself.</li>
 * </ul> 
 *   
 * <p>
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * 
 * @param <FEATURE> Type of the Feature of this TypeAlias. 
 * @param <TYPE> The type of the GClassifier for which this TypeAlias instance acts as an alias.
 * 
 */
public interface GTypeAlias<
	  NAMESPACE extends GCwmPackage<?,?,?,?,?,?,?>
    , FEATURE   extends GFeature<?, ?, ?, ?, ?>
    , TYPE      extends GClassifier<?, ?, ?, ?, ?, ?>
> extends
	GDataType<NAMESPACE, FEATURE>
{


//====================================================================================================================================================
// TypeAlias capabilities	
//====================================================================================================================================================



	/**
	 * Identifies the Classifier instance for which this TypeAlias instance acts as an alias.
	 * <p>
	 * The ClassifierAlias association connects TypeAlias instances with the Classifier instances that they rename.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : generic GClassifier</li>
	 * <li><i>defined by</i> 	: ClassifierAlias::type</li> 
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return A Classifier instance for which this TypeAlias instance acts as an alias. Can't be <code>null</code>.
	 * 
	 */
	TYPE getType();
}