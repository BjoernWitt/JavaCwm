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
package org.ogrehus.jcwm.api.foundation.datatypes;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GAttribute;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * UnionMembers are described as features of a Union and each represents one of the members of a Union. 
 * <p>
 * Note, however, that multiple case values can map to a single UnionMember. If isDefault is true, the union member is the default member. UnionMember 
 * instances are allowed to have a memberCase and be the default case. UnionMember instances often represent structured storage areas. A particular 
 * UnionMember may be associated with a Classifier that describes its internal structure via the StructuralFeatureType association (defined in the 
 * ObjectModel::Core package). 
 * </p>
 * <p>
 * For example, the Record::Group class, itself a Classifier, can be used as the type of a UnionMember in a manner completely analogous to how it is 
 * used to describe the type of a structured field (see the instance diagrams in the Record meta-model chapter for details). 
 * </p>
 * <p>
 * This implementation was guided by the  
 * <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface UnionMember
extends
	GAttribute<
		  GNamespace<?, ?, ?, ?, ?>
		, GCwmPackage<?, ?, ?, ?, ?, ?, ?>
		, Dependency
		, Constraint
		, Union
		, GDataType< ?, ?>
	> 
{



	/**
	 * Contains the value of the Union's discriminator for this UnionMember.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>		    : Expression</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return The value of the Union's discriminator for this UnionMember. Can't be <code>null</code>.
	 * 
	 */
	Expression getMemberCase();



	/**
	 * Contains the value of the Union's discriminator for this UnionMember.
	 *  
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>		    : Expression</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param memberCase The value of the Union discriminator for this UnionMember. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>memberCase</code> was <code>null</code>.
	 * 
	 */
	Expression setMemberCase( Expression memberCase );



	/**
	 * Indicates if this UnionMember is the default member of the Union  (implying that when unstated, the Union's discriminator would assume this
	 * instance memberCase value).
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this UnionMember is the default member of the Union (implying that when unstated, the Union's discriminator would 
	 * assume this instance's memberCase value), <code>false</code> otherwise.
	 * 
	 */
	Boolean isDefault();



	/**
	 * Indicates if this UnionMember is the default member of the Union (implying that when unstated, the Union's discriminator would assume this
	 * instance's memberCase value).
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>        
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param isDefault <code>true</code> if this UnionMember is the default member of the Union (implying that when unstated, the Union's 
	 * discriminator would assume this instance memberCase value), <code>false</code> otherwise. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException is thrown if the parameter <code>isDefault</code> was <code>null</code>.
	 * 
	 */
	Boolean setDefault( Boolean isDefault );
}