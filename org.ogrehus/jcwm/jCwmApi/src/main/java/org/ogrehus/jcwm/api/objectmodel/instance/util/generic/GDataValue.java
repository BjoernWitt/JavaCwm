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

import java.util.Set;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * A data value is an instance with no identity. 
 * <p>
 * In the meta-model, DataValue is a child of Instance that cannot change its state; that is, all operations that are applicable to it are pure 
 * functions or queries that do not cause any side effects. DataValues are typically used as attribute values.
 * </p>
 * <p>
 * Since it is not possible to differentiate between two data values that appear to be the same, it becomes more of a philosophical issue whether 
 * there are several data values representing the same value or just one for each value. In addition, a data value cannot change its data type and it 
 * does not have contained instances.
 * </p>
 *
 * <p>
 * <b>Constraints:</b>
 * </p>
 * <ul>
 * <li><b>[C-6-1]</b> - A DataValue originates from a Classifier that is a DataType.</li>
 * <li><b>[C-6-2]</b> - A DataValue has no Slots.</li>
 * </ul>
 * 
 * Constraint <b>[C-6-1]</b> comes up to the generic upper bound of DatatType.
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
 * @param <DATA_TYPE> The data-type of this Instance. 
 * 
 */
public interface GDataValue<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DATA_TYPE  extends GDataType<?, ?>
> extends 
	GInstance<NAMESPACE, PACKAGE, DATA_TYPE, GSlot<?, ?, ?, ?, ?>>
{


	/**
	 * A string representation of the value.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>		   : String</li>	  
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-6-1]</b> - A DataValue originates from a Classifier that is a 
	 * DataType.</li>
	 * <li><b>[C-6-2]</b> - A DataValue has no Slots.</li>
	 * </ul>
	 * 
	 * @return A string representation of the value.
	 * 
	 */
	String getValue();



	/**
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>		   : String</li>
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-6-1]</b> - A DataValue originates from a Classifier that is a DataType.</li>
	 * <li><b>[C-6-2]</b> - A DataValue has no Slots.</li>
	 * </ul>
	 * 
	 * @see #getValue() For a descritpion on the property <code>value</code>.
	 * 
	 * @param value The string representation of the new value.
	 * 
	 * @return The old value, if set before.
	 * 
	 */
	String setValue( String value );



	/**
	 * Identifies the set of Slot instances for which the DataValue or Object instance contains the current value.
	 * <p>
	 * The SlotValue association connects slot instances with the DataValue or Object instance that contains the current value held by the slot.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : Slot</li>
	 * <li><i>multiplicity</i> : zero or more</li>
	 * </ul>
	 *
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-6-2]</b> - A DataValue has no Slots.</li>
	 * </ul> 
	 * 
	 * @return Always an empty Set. See constraint <b>[C-6-2]</b>.  
	 * 
	 */
	Set<GSlot<?, ?, ?, ?, ?>> getValueSlot();
}