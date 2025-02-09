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
package org.ogrehus.jcwm.impl.objectmodel.instance;

import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.CwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.instance.CwmObject;
import org.ogrehus.jcwm.api.objectmodel.instance.DataSlot;
import org.ogrehus.jcwm.api.objectmodel.instance.DataValueSlot;
import org.ogrehus.jcwm.api.objectmodel.instance.Extent;
import org.ogrehus.jcwm.api.objectmodel.instance.ObjectSlot;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GSlot;
import org.ogrehus.jcwm.api.objectmodel.relationships.AssociationEnd;

import org.ogrehus.jcwm.impl.objectmodel.instance.util.generic.GFunCwmObject;


public class FunCwmObject
extends
	GFunCwmObject<
		  Extent
		, CwmPackage
		, CwmClass
		, GSlot<?, ?, ?, ?, ?>
> implements
	CwmObject
{


	/**
	 * Creates a new instance of FunCwmObject by specific parameters.
	 * 
	 * @param name An identifier for the Object within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the Object within its owning Namespace. Must not be <code>null</code>.
	 *
	 * @param cwmClass The Classifier that declares the structure of the Object. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>classifier</code>
	 * is <code>null</code>.
	 * 
	 */
	public FunCwmObject( String name, Visibility visibility, CwmClass cwmClass ) {
		super( name, visibility, cwmClass ); // throws NullPointerException
	}



	@Override
	protected void initSlots() {
		this.classifier.getAllFeatures();
		for ( GFeature<?, ?, ?, ?, ?> feature : this.classifier.getAllFeatures() ) {
			if ( feature instanceof Attribute ) {
				Attribute attribute = (Attribute)feature;
				GClassifier<?, ?, ?, ?, ?, ?> type = attribute.getType();
				if ( type instanceof CwmClass ) {
					addSlot( new FunObjectSlot<Attribute>( attribute ) );// type-save
				} else if ( type instanceof DataType ) {
					addSlot( new FunDataValueSlot<Attribute>( (Attribute)attribute ) );// type-save
				}
			} else if ( feature instanceof AssociationEnd ) {
				AssociationEnd end = (AssociationEnd)feature;
				GClassifier<?, ?, ?, ?, ?, ?> type = end.getType();
				if ( type instanceof CwmObject ) {
					addSlot( new FunObjectSlot<AssociationEnd>( end ) );
				} else if ( type instanceof DataType ) {
					addSlot( new FunDataValueSlot<AssociationEnd>( end ) );
				}
			}
		}
	}



	public Boolean addSlot( ObjectSlot<?> objectSlot ) {
		return super.addSlotGeneric( objectSlot );
	}



	public Boolean addSlot( DataSlot dataSlot ) {
		return super.addSlotGeneric( dataSlot );
	}



	public Boolean addSlot( DataValueSlot<?> dataValueSlot ) {
		return super.addSlotGeneric( dataValueSlot );
	}



	/**
	 * Adds a valueSlot instance for which the DataValue or CwmObject instance contains the current value.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		   : ObjectSlot</li>
	 * <li><i>defined by</i>   : ObjectSlot::slot</li>
	 * <li><i>multiplicity</i> : zero or more</li>
	 * <li><i>inverse</i>      : Slot::object</li> 
	 * </ul>
	 * 
	 * @param valueSlot New valueSlot instance for which the DataValue or CwmObject instance contains the current value.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addValueSlot( ObjectSlot<?> valueSlot ) {
		return super.addValueSlotGeneric( valueSlot );
	}
}