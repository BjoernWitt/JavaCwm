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
package org.ogrehus.jcwm.impl.foundation.typemapping.util.generic;

import org.ogrehus.jcwm.api.foundation.typemapping.util.generic.GTypeMapping;
import org.ogrehus.jcwm.api.objectmodel.core.util.ConstraintViolation;
import org.ogrehus.jcwm.api.objectmodel.core.util.CwmConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;

public abstract class GFunTypeMapping<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
> extends
	GFunModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT>
implements
	GTypeMapping<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT> 
{

	
	/**
	 * Factory method that creates a new instance of TypeMapping by specific parameters.
	 * 
	 * <p>
	 * TypeMapping instances permit the creation of mappings between data types defined within different environments and are used to indicate data
	 * type compatibilities that permit direct assignment of values from one environment (the source type) into equivalent values in another
	 * environment (the target type).
	 * </p>
	 * 
     * <p>
     * <b>Constraints</b>
     * </p>
     * <ul>
     * <li><b>[C-8-1]</b> The targetType and sourceType references may not refer to the same GClassifier instance.<pre>
     * <b>context</b> TypeMapping <b>inv</b>:
     * self.sourceType <> self.targetType
     * </pre>
     * </li>
     * </ul>
     *  
	 * @param name An identifier for the TypeMapping within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the TypeMapping within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param isBestMatch <code>true</code> if this TypeMapping instance represents the best available mapping between a pair of data types in
	 * different software systems. Must not be <code>null</code>.
	 * 
	 * @param isLossy <code>true</code> if this TypeMapping instance may result in a data conversion error if the source data is within certain
	 * ranges. For example, storing a 32-bit unsigned integer value into a 16-bit unsigned integer container will result in a data conversion error
	 * only when the source data has a value greater than 65535.  Must not be <code>null</code>.
	 * 
	 * @param sourceType Identifies the GClassifier instance that is the source of information exchange. Must not be <code>null</code>.
	 *
	 * @param targetType Identifies the GClassifier instance that is the target of information exchange. Must not be <code>null</code>.
	 * 
	 * @return A new instance of TypeMapping, by initial parameters.
	 * 
	 * @throws <b>NullPointerException</b> Is thrown, if one of the parameter: <code>name</code>, <code>visibility</code>, <code>isBestMatch</code>,
	 * <code>isLossy</code>, <code>sourceType</code> or <code>targetType</code> is <code>null</code>.
	 * 
	 * @throws <b>ConstraintViolation</b> Is thrown, if the Constraint <b>[C_8_1]</b> was violated.
	 * 
	 */
	public GFunTypeMapping(
		  String name
		, Visibility visibility
		, Boolean isBestMatch
		, Boolean isLossy
		, GClassifier<?, ?, ?, ?, ?, ?> sourceType
		, GClassifier<?, ?, ?, ?, ?, ?> targetType
	) {
		super( name, visibility );
		setBestMatch( isBestMatch );
		setLossy( isLossy );
		if ( sourceType == null ) {
			throw new NullPointerException( "Parameter: sourceType must not be null." );
		}
		if ( targetType == null ) {
			throw new NullPointerException( "Parameter: targetType must not be null." );
		}		
		if ( sourceType.equals( targetType ) ) {
			throw new ConstraintViolation( CwmConstraint.Foundation_C_8_1 );
		}
		this.sourceType = sourceType;
		this.targetType = targetType;
	}



	public Boolean isBestMatch() {
		return this.isBestMatch;
	}



	public Boolean isLossy() {
		return this.isLossy;
	}



	public Boolean setBestMatch( Boolean bestMatch ) {
		Boolean old = this.isBestMatch;
		this.isBestMatch = bestMatch;
		return old;
	}



	public Boolean setLossy( Boolean lossy ) {
		Boolean old = this.isLossy;
		this.isLossy = lossy;
		return old;
	}



	public GClassifier<?, ?, ?, ?, ?, ?> getSourceType() {
		return this.sourceType;
	}



	public GClassifier<?, ?, ?, ?, ?, ?> getTargetType() {
		return this.targetType;
	}



//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * True if this TypeMapping instance represents the best available mapping between a pair of data types in different software systems.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : Boolean</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Boolean isBestMatch = null;



	/**
	 * True if this TypeMapping instance may result in a data conversion error if the source data is within certain ranges. 
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
	 */
	protected Boolean isLossy = null;



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
	 */
	protected GClassifier<?, ?, ?, ?, ?, ?> sourceType = null;



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
	 */
	protected GClassifier<?, ?, ?, ?, ?, ?> targetType = null;
}