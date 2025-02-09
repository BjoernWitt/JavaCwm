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

import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GAttribute;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * A AssociationSlot that is used to hold a data value where there is no need to manage the value as an element in its own right (in which case a
 * DataValue would be used) - for example it is a one-off string value or a number.
 * <p>
 * The dataValue (and dataType where set) must be consistent with the type of the DataSlot feature (Attribute) and must obey any constraints on the
 * full descriptor of the Attribute DataType (including both explicit constraints and built-in constraints such as multiplicity).
 * </p>
 * 
 * <p>
 * <b>Constraints:</b>
 * </p>
 * <ul>
 * <li><b>[C-6-6]</b> - A DataType instance associated with a DataSlot instance must be compatible with the type of the DataSlot indicated by the
 * feature::type inherited from AssociationSlot.</li>
 * <li><b>[C-6-7]</b> - The StructuralFeature instance obtained via the feature reference inherited from AssociationSlot must be an Attribute.</li>
 * <li><b>[C-6-8]</b> - The value reference inherited from GSlot must be empty.</li>
 * </ul>
 * The Constraint <b>[C-6-7]</b> will be achieved by narrowing the generic type of FEATURE to GAttribute
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this Slot.
 * @param <PACKAGE> The type of the importer of this Slot.
 * @param <ATTRIBUTE> Identifies the StructuralFeature instance for which the Slot instance contains the current value.
 * @param <OBJECT> The type of the Object that own the stlot instance.
 * @param <DATA_TYPE> Identifies the DataType instance representing the type of the information stored in the dataValue attribute.
 * 
 */
public interface GDataSlot<
	  NAMESPACE extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE   extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, ATTRIBUTE extends GAttribute<?, ?, ?, ?, ?, ?>
	, OBJECT    extends GCwmObject<?, ?, ?, ?>
	, DATA_TYPE extends GClassifier< ?, ?, ?, ?, ?, ?>
>extends
	GSlot<
		  NAMESPACE
		, PACKAGE
		, GInstance<?, ?, ?, ?>
		, ATTRIBUTE
		, OBJECT
	>
{


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
	 * @return The value for the slot expressed as a string.
	 * 
	 */
	String getDataValue();



	/**
	 * Attribute: dataValue
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>		   : String</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * @param dataValue The new dataValue for the slot expressed as a String.
	 * 
	 * @return The old value for the slot expressed as a string. Can not be <code>null</code>.
	 * 
	 */
	String setDataValue( String dataValue );



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
	 * <li><i>class</i>		   : DataType</li>
	 * <li><i>defined by</i>   : DataSlotType::dataType</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 *  @return The type of the dataValue. If not set, the type is taken as the type of the Attribute (StructuralFeature) that is the feature for the
	 *  DataSlot. 
	 * 
	 */
	DATA_TYPE getDataType();



	/**
	 * Reference: dataType
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>		   : generic DATA_TYPE</li>
	 * <li><i>defined by</i>   : DataSlotType::dataType</li>
	 * <li><i>multiplicity</i> : zero or one</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-6-6]</b> - A DataType instance associated with a DataSlot instance must be compatible with the type of the DataSlot indicated by the
	 * feature::type inherited from AssociationSlot.</li>
	 * <li><b>[C-6-8]</b> - The value reference inherited from AssociationSlot must be empty.</li>
	 * </ul> 
	 * 
	 * @see #getDataType() For more description on the property <code>dataType</code>.
	 * 
	 * @param dataType Identifies the type of information stored in the property <code>dataValue</code>.
	 * 
	 * @return The type of the dataValue. If not set, the type is taken as the type of the Attribute (StructuralFeature) that is the feature for the
	 * DataSlot.
	 * 
	 */
	<T extends DATA_TYPE> DATA_TYPE setDataType( T dataType );



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
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-6-8]</b> - The value reference inherited from DataSlot must be empty.</li>
	 * </ul>
	 * 
	 * @return Always <code>null</code>, to satisfy constraint <b>[C-6-8]</b>.
	 * 
	 */
	GInstance<?, ?, ?, ?> getValue();
}