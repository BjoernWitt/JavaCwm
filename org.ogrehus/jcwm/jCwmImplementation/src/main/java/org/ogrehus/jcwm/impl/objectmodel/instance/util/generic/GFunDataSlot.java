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

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GAttribute;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GCwmObject;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GDataSlot;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GInstance;


public class GFunDataSlot<
	  NAMESPACE extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE   extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, ATTRIBUTE extends GAttribute<?, ?, ?, ?, ?, ?>
	, OBJECT    extends GCwmObject<?, ?, ?, ?>
	, DATA_TYPE extends GClassifier< ?, ?, ?, ?, ?, ?>
> extends
	GFunSlot<NAMESPACE, PACKAGE, GInstance<?, ?, ?, ?>, ATTRIBUTE, OBJECT>
implements
	GDataSlot<NAMESPACE, PACKAGE, ATTRIBUTE, OBJECT, DATA_TYPE>
{


	/**
	 * Creates a new instance of this DataSlot by specific parameters.
	 * 
	 * @param attribute References the Attribute instance that describes the value held by the DataSlot instance.
	 * 
	 * @param dataValue The value for the slot expressed as a string.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>attribute</code> or <code>dataValue </code> is <code>null</code>.
	 * 
	 */
	protected GFunDataSlot( ATTRIBUTE attribute, String dataValue ) {
		super( attribute ); // throws NullPointerException
		this.dataType = (DATA_TYPE)attribute.getType(); // ???dunnow why the cast is demanded???
		setDataValue( dataValue ); // throws NullPointerException
	}



	public DATA_TYPE getDataType() {
		return this.dataType;
	}



	public <T extends DATA_TYPE> DATA_TYPE setDataType( T dataType ) {
		DATA_TYPE old = this.dataType;
		this.dataType = dataType;

		return old;
	}



	public String setDataValue( String dataValue ) {
		if ( dataType == null ) {
			throw new NullPointerException( "Parameter: dataValue must not be null." );
		}		
		String old = this.dataValue;
		this.dataValue = dataValue;

		return old;
	}



	public String getDataValue() {
		return this.dataValue;
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================        



	/**
	 * Attribute: dataValue
	 * <p>
	 * The value for the slot expressed as a string.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>		   : String</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 */
	protected String dataValue = null;



	/**
	 * Reference: dataType
	 * <p>
	 * The type of the dataValue. If not set, the type is taken as the type of the Attribute (StructuralFeature) that is the feature for the DataSlot.
	 * </p>
	 * <p>
	 * The DataSlotType association connects DataSlot instances with the DataType instance that identifies the type of information stored in the
	 * DataSlot::dataValue attribute.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		   : generic DATA_TYPE</li>
	 * <li><i>defined by</i>   : DataSlotType::dataType</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 */
	protected DATA_TYPE dataType = null;
}