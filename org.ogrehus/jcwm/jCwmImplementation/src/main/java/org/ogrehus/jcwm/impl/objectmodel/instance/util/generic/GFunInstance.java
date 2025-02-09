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
package org.ogrehus.jcwm.impl.objectmodel.instance.util.generic;

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GInstance;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GSlot;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;


/**
 * The instance construct defines an entity to which a set of operations can be applied and which has a state that stores the effects of the
 * operations.
 * <p>
 * In the meta-model Instance is connected to a Classifier that declares its structure and behavior. It has a set of attribute values matching the
 * definition of its Classifier. The set of attribute values implements the current state of the Instance.
 * </p>
 * <p>
 * Because Instance is an abstract class, all Instances are either Object or DataValue instances.
 * </p>
 * <p>
 * The data content of an Instance comprises one value for each attribute in its full descriptor (and nothing more). The value must be consistent with
 * the type of the attribute. An instance must obey any constraints on the full descriptor of the Classifier of which it is an instance (including
 * both explicit constraints and built-in constraints such as multiplicity).
 * </p>
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this ModelElement.
 * @param <PACKAGE> The type of the importer of this ModelElement.
 * @param <CLASSIFIER> The type of this Instance. 
 * 
 */
public abstract class GFunInstance<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CLASSIFIER extends GClassifier<?, ?, ?, ?, ?, ?>
	, SLOT       extends GSlot<?, ?, ?, ?, ?>
> extends
	GFunModelElement<NAMESPACE, PACKAGE, Dependency, Constraint> 
implements
	GInstance<NAMESPACE, PACKAGE, CLASSIFIER, SLOT>
{


	/**
	 * Creates a new instance of GFunInstance by specific parameters.
	 * 
	 * @param name An identifier for the GFunInstance within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the CwmObject within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param classifier The Classifier that declares the structure of the Object. Must not be <code>null</code>.
	 * 
	 * @return A new instance of CwmObject, by initial parameters. 
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>classifier</code>
	 * is <code>null</code>.
	 * 
	 */
	protected GFunInstance( String name, Visibility visibility, CLASSIFIER classifier ) {
		super( name, visibility );
		setClassifier( classifier ); // throws NullPointerException
		this.valueSlots = new HashSet<SLOT>();
	}



//====================================================================================================================================================
// Instance capabilities
//====================================================================================================================================================



	public CLASSIFIER getClassifier() {
		return this.classifier;
	}



	public CLASSIFIER setClassifier( CLASSIFIER classifier ) {
		if ( classifier == null ) {
			throw new NullPointerException( "The parameter: classifier must not be null!" );
		}
		
		CLASSIFIER old = this.classifier;
		this.classifier = classifier;

		return old;
	}



	/**
	 * Identifies the set of AttributeSlot instances for which the DataValue or Object instance contains the current value.
	 * <p>
	 * The SlotValue association connects slot instances with the DataValue or Object instance that contains the current value held by the slot.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		   : AttributeSlot</li>
	 * <li><i>multiplicity</i> : zero or more</li>
	 * </ul>
	 * 
	 * @return The Set of AttributeSlot instances for which the DataValue or Object instance contains the current value. May not be <code>null</null>,
	 * but may return an empty Set.
	 * 
	 */
	public Set<SLOT> getValueSlots() {
		return this.valueSlots;
	}



	/**
	 * Removes a valueSlot instance from this Instance.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		   : generic SLOT</li>
	 * <li><i>multiplicity</i> : zero or more</li>
	 * </ul>
	 * 
	 * @param valueSlot The instance that will be remove, if avail in the set.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean removeValueSlot( GSlot<?, ?, ?, ?, ?> valueSlot ) {
		if ( valueSlot == null ) {
			return Boolean.TRUE;
		}

		if ( this.valueSlots.remove( valueSlot ) ) {
			valueSlot.removeValue();

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Reference: classifier
	 * <p>
	 * The Classifier that declares the structure of the Instance. The InstanceClassifier association links Instances with Classifiers that describe
	 * them.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		   : geberic CLASSIFIER</li>
	 * <li><i>defined by</i>   : InstanceClassifier::classifier</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @return The Classifier that declares the structure of the Instance.
	 * 
	 */
	protected CLASSIFIER classifier = null;



	protected Set<SLOT> valueSlots = null;
}