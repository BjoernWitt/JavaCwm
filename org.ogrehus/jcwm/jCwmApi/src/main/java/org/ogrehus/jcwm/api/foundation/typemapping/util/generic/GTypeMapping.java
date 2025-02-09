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
package org.ogrehus.jcwm.api.foundation.typemapping.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * TypeMapping instances permit the creation of mappings between data types defined within different environments and 
 * are used to indicate data type compatibilities that permit direct assignment of values from one environment (the source type) into equivalent 
 * values in another environment (the target type).
 * <p>
 * For example, an integer field data type in a record-oriented DBMS (the source type) might be mapped to a compatible integer data type in a 
 * relational DBMS (the target type).
 * </p>
 * <p>
 * Whereas the actual transfer of data values between environments is accomplished using the CWM Transformation package, TypeMapping instances can 
 * be used to identify both the permissible and preferred mappings between data types. 
 * </p>
 * <p>
 * Value interchange between a pair of data types is considered permissible if a TypeMapping instance is defined for the pair. A TypeMapping instance 
 * is considered the preferred mapping if the instance isBestMatch attribute has the value true.
 * </p>
 * <p>
 * Typically, there will be one TypeMapping Instance between a pair of data types that is considered the preferred mapping. To promote flexible use 
 * of this feature, there is no requirement that a preferred TypeMapping instance must be identified between a pair of data types nor are multiple 
 * preferred instances prohibited. In these latter cases, however, the precise semantics are usage-defined.
 * </p>
 * <p>
 * Interchange between data types defined by non-preferred mappings may often function as intended. However, the isLossy boolean may be set to 
 * indicate that such interchanges may be subject to validity restrictions in certain circumstances. For example, it may be valid to move data values 
 * from a 32-bit integer data type to a 16-bit integer data type as long as the actual values in the 32-bit underlying data type do not exceed the 
 * range permitted for 16-bit integers. The CWM Foundation leaves the understanding and handling of such differences to individual tools. If such 
 * differences must be modeled, consider using the CWM Transformation package to filter data values during interchange. TypeMapping instances are 
 * unidirectional, so two instances are required to show that a data type pair can be mutually interchanged between environments.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-8-1]</b> The targetType and sourceType references may not refer to the same GClassifier instance.</li>
 * </ul> 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this GTypeMapping.
 * @param <PACKAGE> The type of the importer of this GTypeMapping.
 * @param <DEPENDENCY> The type of ClientDependency of this GTypeMapping.
 * @param <CONSTRAINT> The type of the Constraint under which this GTypeMapping is.  
 * 
 */
public interface GTypeMapping<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
>extends
	  GModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT>
{


	/**
	 * Returns <code>true</code> if this TypeMapping instance represents the best available mapping between a pair of data types in different 
	 * software systems.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this TypeMapping instance represents the best available mapping between a pair of 
	 * data types in different software systems, <code>false</code> otherwise. Cannot be <code>null</code>.
	 * 
	 */
	Boolean isBestMatch();



	/**
	 * Returns <code>true</code> if this TypeMapping instance represents the best available mapping between a pair of data types in different 
	 * software systems.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param bestMatch <code>true</code>, if if this TypeMapping instance represents the best available mapping between a pair of data types in 
	 * different software systems, <code>false</code> otherwise. Must not be <code>null</code>.
	 * 
	 * @return The old value of the property <code>bestMatch</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException if the parameter <code>bestMatch</code> is <code>null</code>. 
	 * 
	 */
	Boolean setBestMatch( Boolean bestMatch );	



	/**
	 * Returns <code>true</code> if this TypeMapping instance may result in a data conversion error if the source data is within certain ranges. 
	 * <p>
	 * For example, storing a 32-bit unsigned integer value into a 16-bit unsigned integer container will result in a data conversion error only 
	 * when the source data has a value greater than 65535.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if this TypeMapping instance may result in a data conversion error if the source data is within certain ranges, 
	 * <code>false</code> otherwise. Cannot be <code>null</code>.
	 * 
	 */
	Boolean isLossy();



	/**
	 * Returns <code>true</code> if this TypeMapping instance may result in a data conversion error if the source data 
	 * is within certain ranges. 
	 * <p>
	 * For example, storing a 32-bit unsigned integer value into a 16-bit unsigned integer container will result in a 
	 * data conversion error only when the source data has a value greater than 65535.
	 * </p>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 * @param lossy <code>true</code> if this TypeMapping instance may result in a data conversion error if the source data 
	 * is within certain ranges, <code>false</code> otherwise. Must not be <code>null</code>.
	 *
	 * @return The old value of the property <code>lossy</code>. Can't be <code>null</code>.
	 * 
	 * @throws NullPointerException if the parameter <code>lossy</code> is <code>null</code>. 
	 * 
	 */
	Boolean setLossy( Boolean lossy );



	/**
	 * Identifies the GClassifier instance that is the source of information exchange.
	 *
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : GClassifier</li>
	 * <li><i>defined by</i> 	: MappingSource::sourceType</li>
	 * <li><i>multiplicity</i> 	: exactly one</li> 
	 * </ul>
	 * 
	 * @return The GClassifier instance that is the source of information the exchange. Cannot be <code>null</code>.
	 * 
	 */
	GClassifier<?, ?, ?, ?, ?, ?> getSourceType();



	/**
	 * Identifies the GClassifier instance that is the target of information exchange.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		    : GClassifier</li>
	 * <li><i>defined by</i> 	: MappingTarget::targetType</li>
	 * <li><i>multiplicity</i> 	: exactly one</li> 
	 * </ul>
	 *
	 * @return The GClassifier instance that is the target of information exchange.
	 * 
	 */
	GClassifier<?, ?, ?, ?, ?, ?> getTargetType();
}