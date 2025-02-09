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
package org.ogrehus.jcwm.api.objectmodel.instance;

import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GDataValue;


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
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public interface DataValue
extends
	GDataValue<GNamespace<?, ?, ?, ?, ?>, GCwmPackage<?, ?, ?, ?, ?, ?, ?>, DataType>
{


	/**
	 * Reference: The Classifier that declares the structure of the Instance.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>		   : DataType</li>
	 * <li><i>defined by</i>   : InstanceClassifier::classifier</li>	  
	 * <li><i>multiplicity</i> : exactly one</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Constraints:</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-6-1]</b> - A DataValue originates from a Classifier that is a DataType.</li>
	 * </u>
	 *
	 * @param dataValue The new value for this reference, type of DataType.
	 * 
	 * @return The old DataType that declares the structure of the Instance. May not be <code>null</null>. 
	 * 
	 */
	DataType setClassifier( DataType dataValue );



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
	 * 
	 * @param value The string representation of the new value.
	 * 
	 * @return The old value, if set before.
	 * 
	 */
	String setValue( String value );
}