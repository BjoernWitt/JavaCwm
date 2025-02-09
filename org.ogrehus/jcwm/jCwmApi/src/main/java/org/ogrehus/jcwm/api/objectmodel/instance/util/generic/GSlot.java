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
package org.ogrehus.jcwm.api.objectmodel.instance.util.generic;

import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GStructuralFeature;


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
 * @param <NAMESPACE> The type of the Namespace that contains this Slot.
 * @param <PACKAGE> The type of the importer of this Slot.
 * @param <INSTANCE> Identifies the subtype that holds the current value represented by the slot
 * @param <FEATURE> Identifies the StructuralFeature instance for which the Slot instance contains the current value.
 * @param <OBJECT> The type of the Object that own the stlot instance.
 * 
 */
public interface GSlot<
	  NAMESPACE extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE   extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, INSTANCE  extends GInstance<?, ?, ?, ?>
	, FEATURE   extends GStructuralFeature<?, ?, ?, ?, ?, ?>
	, OBJECT    extends GCwmObject<?, ?, ?, ?>
> extends
	GModelElement<NAMESPACE, PACKAGE, Dependency, Constraint>
{


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
	 * @return The StructuralFeature instance that describes the value held by the Slot instance.
	 * 
	 */
	FEATURE getFeature();



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
	OBJECT getObject();



	/**
	 * Removes this Slot from the registered value.
	 * 
	 * @return <code>true</code> if removed.
	 * 
	 */
	Boolean removeObject();



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
	INSTANCE getValue();



	/**
	 * Removes this Slot from the registered value.
	 * 
	 * @return <code>true</code> if removed.
	 * 
	 */
	Boolean removeValue();



	/**
	 * References the DataValue or CwmObject instance that contains the current value held by this slot.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		   : generic INSTANCE</li>
	 * <li><i>defined by</i>   : SlotValue::value</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * @param value The new instance that contains the current value held by the slot.
	 * 
	 * @return The old Instance that has contained the current value held by the slot, before. 
	 * 
	 */
	INSTANCE setValue( INSTANCE value );



	/**
	 * Reference: feature
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		   : generic FEATURE</li>
	 * <li><i>defined by</i>   : FeatureSlot::feature</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @param feature The new feature
	 * 
	 * @return The StructuralFeature instance that describes the value held by the Slot instance. Must not be <code>null></code>.
	 * 
	 * @throws NullPointerException Is thrown if the parameter: <code>feature</code> is <code>null</code>
	 * 
	 */
	FEATURE setFeature( FEATURE feature );



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
	 * @param object The new instance of CwmObject, that own the slot.
	 * 
	 * @return The instance of CwmObject, that has owned the Slot before. Might be <code>null</code>.
	 * 
	 */
	OBJECT setObject( OBJECT object );
}