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

import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GInstance;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GCwmObject;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GExtent;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GSlot;


public abstract class GFunCwmObject<
	  EXTENT  extends GExtent<?, ?, ?, ?, ?>
	, PACKAGE extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, CLASS   extends GCwmClass<?, ?, ?, ?, ?, ?, ?>
	, SLOT    extends GSlot<?, ?, ?, ?, ?>
>extends
	GFunInstance<EXTENT, PACKAGE, CLASS, SLOT>
implements
	GCwmObject<EXTENT, PACKAGE, CLASS, SLOT>
{


	/**
	 * Creates a new instance of any FunCwmObject.
	 * 
	 * @param name An identifier for the Object within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Object within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param classifier The Classifier that declares the structure of the Object. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>classifier</code>
	 * is <code>null</code>.
	 * 
	 */
	public GFunCwmObject( String name, Visibility visibility, CLASS classifier ) {
		super( name, visibility, classifier ); // throws NullPointerException
		this.slots = new HashSet<SLOT>();
		initSlots();
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public EXTENT setNamespace( EXTENT namespace ) {
		return super.setNamespaceGeneric( namespace );
	}



//====================================================================================================================================================
// CwmObject capabilities
//====================================================================================================================================================



	/**
	 * The Instances (hence MultiDimensional) metamodel is very wasteful in requiring a separate DataValue object for each simple string slot value:
	 * this in effect doubles the number of objects for value-oriented schemas such as Dimension definitions (in MultiDimensional where DataValue is
	 * inherited into MemberValue).
	 * 
	 * <p>
	 * This is a problem for XMI files and their processing, but even more so for repository implementations which tend to have management overheads
	 * associated with each object. Moreover these DataValue objects end up being directly contained in the MemberSet for the Dimension, which surely
	 * was not the intention. This means that when navigating from the Dimension to process its Members one also has to filter out a large number of
	 * these unwanted Data/MemberValue objects.
	 * </p>
	 * 
	 * <ol>
	 * <li>
	 * Add new subclass of Slot called 'DataSlot' with description: "A Slot which is used to hold a data value where there is no need to manage the
	 * value as an element in its own right (in which case a DataValue would be used) - for example it is a one-off string value or a number. The
	 * dataValue (and dataType where set) must be consistent with the type of the DataSlot's feature (Attribute) and must obey any constraints on the
	 * full descriptor of the Attribute's DataType (including both explicit constraints and built-in constraints such as multiplicity)."
	 * </li>
	 * <li>
	 * Add String attribute 'dataValue', description: "The value for the slot expressed as a string."
	 * </li>
	 * <li>
	 * Add reference 'dataType'[0..1] to DataType, description: "The type of the dataValue. If not set the type is taken as the type of the Attribute
	 * (StructuralFeature) which is the feature for the DataSlot.
	 * </li>
	 * <li>
	 * Add constraint that the feature of a DataSlot must be an Attribute. In OCL: self.feature.oclIsTypeOf(Attribute).
	 * </li>
	 * <li>
	 * Add constraint that a DataType associated with a DataSlot must be type compatible with that associated with the Attribute which is its feature.
	 * In OCL: self.dataType.oclIsKindOf(self.feature.type).
	 * </li>
	 * <li>
	 * Change multiplicity of reference Slot.value from 1 to 0..1 and add constraints that it must not be empty for direct instances of Slot and must
	 * be empty for instances of DataSlot. In OCL: [on Slot] self.oclIsTypeOf(Slot) implies self.value->notEmpty()
	 * and [on DataSlot] self.value->isEmpty().
	 * </li>
	 * <li>
	 * Update Figure 7-6-3 to change the 2 instances of Slot associated with the 'name' Attribute to be instances of DataSlot, delete the attached
	 * instances of DataValue, and attach the 'value=' strings directly to the DataSlots making them 'dataValue='.
	 * </li>
	 * </ol>
	 * 
	 */
	abstract protected void initSlots();



	public Set<SLOT> getSlots() {
		return this.slots;
	}



	public Boolean removeSlot( GSlot<?, ?, ?, ?, ?> slot ) {
		if ( slot == null ) {
			return Boolean.TRUE;
		}

		if ( this.slots.remove( slot ) ) {
			slot.removeObject();

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	protected <S extends SLOT> Boolean addValueSlotGeneric( S valueSlot ) {
		if ( valueSlot != null && this.valueSlots.add( valueSlot ) ) {
			invokeByReflection( valueSlot, "setValue", this ); // bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	protected <S extends SLOT> Boolean addSlotGeneric( S slot ) {
		if ( slot != null && this.slots.add( slot ) ) {
			invokeByReflection( slot, "setObject", this ); // bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	/**
	 * Return a Collection of all containing Instances by type.
	 * <p>
	 * The containing instances are defined as follows:
	 * </p>
	 * <pre>
	 * <b>context</b> Object:
	 *     contents : Set( Instance )
	 *     contents = self.slot.instance 
	 * </pre>
	 * This OCL can't be found in the CWM Specification.
	 * 
	 * @param instanceType the type of the containing Instances.
	 * 
	 * @return All Objects and DataValues held by this Object.
	 * 
	 */
	public <INSTANCE extends GInstance<?, ?, ?, ?>> Set<INSTANCE> getContents( Class<INSTANCE> instanceType ) {
		Set<INSTANCE> contents = new HashSet<INSTANCE>();
		for ( SLOT slot: this.slots ) {
			GInstance<?, ?, ?, ?> value = slot.getValue();
			if ( instanceType.isInstance( value ) ) {
				contents.add( instanceType.cast( value ) );
			}
		}

		return contents;
	}



	/**
	 * Return a Collection of all containing Instances.
	 * <p>
	 * The containing instances are defined as follows:
	 * </p>
	 * <pre>
	 * <b>context</b> Object:
	 *     contents : Set( Instance )
	 *     contents = self.slot.instance 
	 * </pre>
	 * This OCL can't be found in the CWM Specification.
	 * 
	 * @return All Objects and DataValues held by this Object.
	 * 
	 */
	public Set<GInstance<?, ?, ?, ?>> getContents() {
		Set<GInstance<?, ?, ?, ?>> contents = new HashSet<GInstance<?, ?, ?, ?>>();
		for ( SLOT slot: this.slots ) {
			GInstance<?, ?, ?, ?> value = slot.getValue();
			if ( value != null ) {
				contents.add( value );
			}
		}

		return contents;
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Reference: slot 
	 * <p>
	 * The set of Slot instances owned by the Object. The ObjectSlot association connects Slot instances with their owning Object instances.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : Slot</li>
	 * <li><i>defined by</i>   : ObjectSlot::slot</li>
	 * <li><i>multiplicity</i> : zero or more</li>
	 * <li><i>inverse</i>      : Slot::object</li>
	 * </ul>
	 * 
	 */
	protected Set<SLOT> slots = null;
}