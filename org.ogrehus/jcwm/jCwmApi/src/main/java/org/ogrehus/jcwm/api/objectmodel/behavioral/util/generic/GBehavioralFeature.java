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
package org.ogrehus.jcwm.api.objectmodel.behavioral.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.behavioral.BehavioralFeature;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;


/**
 * BehavioralFeature is an abstract meta-class.
 * <p>
 * A behavioral feature refers to a dynamic feature of a model element, such as an operation or method. In the meta-model BehavioralFeature specifies 
 * a behavioral aspect of a Classifier. All different kinds of behavioral aspects of a Classifier, such as Operation and Method, are subclasses of 
 * BehavioralFeature.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GBehavioralFeature.
 * @param <PACKAGE> The type of the importer of this GBehavioralFeature.
 * @param <CLASSIFIER> The type of the Classifier that owns this GBehavioralFeature.
 * @param <PARAMETER> The caller must supply as a list of values compatible with the types of the Parameters  
 *
 */
public interface GBehavioralFeature<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CLASSIFIER extends GClassifier< ?, ?, ?, ?, ?, ?>
	, PARAMETER  extends GParameter< ?, ?, ?, ?>
>extends
	  GFeature<NAMESPACE, PACKAGE, Dependency, Constraint, CLASSIFIER>
	, BehavioralFeature<PARAMETER>
{


//====================================================================================================================================================
// GModelElement capabilities	
//====================================================================================================================================================



	/**
	 * Adds a dependency to this CwmMethod.
	 * 
	 * @param dependency A Dependency in witch this CwmMethod is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds another Constraint to this CwmMethod.
	 * 
	 * @param constraint A Constrain that must be satisfied by this CwmMethod.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean addConstraint( Constraint constraint );
}