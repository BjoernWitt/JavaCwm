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

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GCwmObject;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GInstance;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GSlot;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;

/**
 * A slot is a named location in an Object instance that holds the current value of the StructuralFeature associated with the Slot instance.
 * <p>
 * Normally, the StructuralFeature associated with the slot will be either an Attribute instance or an AssociationEnd instance. Slots are owned by
 * Objects; DataValues do not have slots.
 * </p>
 * 
 * <p>
 * <b>Constraints:</b>
 * </p>
 * <ul>
 * <li><b>[C-6-5]</b> - If the StructuralFeature describing a Slot is an AssociationEnd, the Classifier associated with the Object owning the Slot
 * must be an Association.</li>
 * <li><b>[C-6-9]</b> - If the Slot instance is not also a DataSlot, the value reference must be present.</li>
 * </ul>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this ModelElement.
 * @param <PACKAGE> The type of the importer of this ModelElement.
 * @param <INSTANCE> The type of ClientDependency of this ModelElement.
 * @param <FEATURE> Describing the structure of the value held by the slot. 
 * @param <OBJECT> The type of the instance(Object) this slot is contained by. 
 * 
 */
public class GFunSlot<
	  NAMESPACE extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE   extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, INSTANCE  extends GInstance<?, ?, ?, ?>
	, FEATURE   extends GStructuralFeature<?, ?, ?, ?, ?, ?>
	, OBJECT    extends GCwmObject<?, ?, ?, ?>
> extends
	GFunModelElement<NAMESPACE, PACKAGE, Dependency, Constraint>
implements
	GSlot<NAMESPACE, PACKAGE, INSTANCE, FEATURE, OBJECT>
{


	/**
	 * Creates a new instance of this Slot by specific parameters.
	 * 
	 * @param feature References the StructuralFeature instance that describes the value held by the Slot instance.
	 * 
	 * @return A new instance of Slot, by initial parameters.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>feature</code>
	 * is <code>null</code>.
	 * 
	 */
	protected GFunSlot( FEATURE feature ) {
		super( feature.getName().toString(), feature.getVisibility() ); // throws NullPointerException
		this.setFeature( feature );// throws NullPointerException
	}



//====================================================================================================================================================
// GSlot capabilities	
//====================================================================================================================================================



	/**
	 * Reference: feature
	 * <p>
	 * References the StructuralFeature instance that describes the value held by the Slot instance.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : generic <FEATURE></li>
	 * <li><i>defined by</i>   : FeatureSlot::feature</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @return The StructuralFeature instance that describes the value held by the Slot instance.
	 * 
	 */
	public FEATURE getFeature() {
		return this.feature;
	}



	/**
	 * Reference: feature
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : generic <FEATURE></li>	  
	 * <li><i>defined by</i>   : FeatureSlot::feature</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @see #getFeature() For a descritpion on the property <code>feature</code>.
	 * 
	 * @param The new feature 
	 * 
	 * @return The StructuralFeature instance that describes the value held by the Slot instance.
	 * 
	 */
	public FEATURE setFeature( FEATURE feature ) {
		if ( feature == null ) {
			throw new NullPointerException( "Parameter: feature must not be null!" );
		}
		FEATURE old = this.feature;
		this.feature = feature;

		return old;
	}



	/**
	 * Identifies the Object instance that owns the Slot instance.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : CwmObject</li>	  
	 * <li><i>defined by</i>   : ObjectSlot::object</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>inverse</i>      : Object::slot</li> 
	 * </ul>
	 * 
	 * @see #getObject() For more description on the property <code>object</code>.
	 * 
	 * @param object The new instance of CwmObject, that own the slot.
	 * 
	 * @return The instance of CwmObject, that has owned the Slot before. Might be <code>null</code>.
	 * 
	 */
	public OBJECT setObject( OBJECT object ) {
		OBJECT old = this.object;
		removeObject(); // organizes the bi-directional reference
		if ( object != null ) {
			this.object = object;
			invokeByReflection(this.object, "addSlot", this );
		}

		return old;
	}



	/**
	 * Identifies the Object instance that owns the Slot instance.
	 * <p>
	 * References the Object instance that owns the Slot. The ObjectSlot association connects Slot instances with their owning Object instances.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : CwmObject</li>
	 * <li><i>defined by</i>   : ObjectSlot::object</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>inverse</i>      : Object::slot</li>
	 * </ul>
	 * 
	 * @return The instance of CwmObject, that own the Slot.
	 * 
	 */
	public OBJECT getObject() {
		return this.object;
	}



	/**
	 * Removes this Slot from the registered value.
	 * 
	 * @return <code>true</code> if removed.
	 * 
	 */
	public Boolean removeObject() {
		if ( this.object == null ) {
			return Boolean.TRUE;
		}
		this.object.removeSlot( this );
		this.object = null;

		return Boolean.TRUE;
	}


	/**
	 * References the DataValue or CwmObject instance that contains the current value held by this slot.
	 * <p>
	 * The SlotValue association connects slot instances with the DataValue or CwmObject instance that contains the current value held by the slot.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : Instance</li>
	 * <li><i>defined by</i>   : SlotValue::value</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @return An Instance that contains the current value held by the Slot.
	 * 
	 */
	public INSTANCE getValue() {
		return this.value;
	}



	/**
	 * Removes this Slot from the registered value.
	 * 
	 * @return <code>true</code> if removed.
	 * 
	 */
	public Boolean removeValue() {
		if ( this.value == null ) {
			return Boolean.TRUE;
		}
		this.value.removeValueSlot( this );
		this.value = null;

		return Boolean.TRUE;
	}



	/**
	 * References the DataValue or CwmObject instance that contains the current value held by this slot.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : Instance</li>
	 * <li><i>defined by</i>   : SlotValue::value</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @see #getValue() For more description on the property <code>value</code>.
	 * 
	 * @param value The new instance that contains the current value held by the slot.
	 * 
	 * @return The old Instance that has contained the current value held by the slot, before.
	 * 
	 */
	public INSTANCE setValue( INSTANCE value ) {
		INSTANCE old = this.value;
		removeValue(); // organizes the bi-directional reference
		if ( value != null ) {
			this.value = value;
			if (  this.value instanceof GCwmObject<?, ?, ?, ?> ) {
				GCwmObject<?, ?, ?, ?> valueObject = (GCwmObject<?, ?, ?, ?>)this.value;
				invokeByReflection( valueObject, "addValueSlot", this ); // bi-directional reference
			}
		}

		return old;
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Reference: feature
	 * <p>
	 * References the StructuralFeature instance that describes the value held by the Slot instance.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		   : generic FEATURE</li>
	 * <li><i>defined by</i>   : FeatureSlot::feature</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */
	protected FEATURE feature = null;



	/**
	 * Reference: object
	 * <p>
	 * References the Object instance that owns the Slot.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : generic OBJECT</li>
	 * <li><i>defined by</i>   : ObjectSlot::object</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * <li><i>inverse</i>      : Object::slot</li>
	 * </ul>
	 * 
	 */
	protected OBJECT object = null;



	/**
	 * Reference: value
	 * <p>
	 * References the DataValue or Object instance that contains the current value held by the Slot.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : generic INSTANCE</li>
	 * <li><i>defined by</i>   : SlotValue::value</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 */
	protected INSTANCE value = null;
}