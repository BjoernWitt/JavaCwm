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

import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.CwmPackage;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GCwmObject;
import org.ogrehus.jcwm.api.objectmodel.instance.util.generic.GSlot;


/**
 * An object is an instance that originates from a class.
 * <p>
 * In the meta-model Object is a subclass of Instance originating from a Class. The Class may be modified dynamically, which means that the set of
 * features of the Object may change during its lifetime.
 * </p>
 * <p>
 * An object is an instance that originates from a class; it is structured and behaves according to its class. All objects originating from the same
 * class are structured in the same way, although each of them has its own set of attribute slots. Each attribute slot references an instance, usually
 * a data value or possibly another object. The number of attribute slots with the same name fulfills the multiplicity of the corresponding attribute
 * in the class. The set may be modified according to the specification in the corresponding attribute. For example, each referenced instance must
 * originate from (a specialization of) the type of the attribute, and attribute slots may be added or removed according to the changeable property of
 * the attribute.
 * </p>
 * <p>
 * An Object instance slots may contain either DataValue instances or other Object instances. Owned Object instances occur as side-effects of either
 * of two meta-model situations:
 * </p>
 * <ul>
 * <li>First, the Classifier of the owning instance contains features (via the ClassifierFeature association) whose types are non-DataType
 * Classifiers.</li> 
 * <li>Second, the StructuralFeature describing the attribute slot is an AssociationEnd.</li>
 * </ul>
 * <p>
 * An Object instance may own other Object instances. This occurs when the Classifier describing the owning Object contains the Classifier(s)
 * describing the owned object through namespace containment via the ElementOwnership association. Namespace rules imply that an Object instance
 * contained in another Object instance has access to all names that are accessible to its container instance.
 * </p>
 * <p>
 * <b>Constraints:</b>
 * </p>
 * <ul>
 * <li><b>[C-6-3]</b> - An CwmObject may only own CwmObjects and DataValues.</li>
 * <li><b>[C-6-4]</b> - If an CwmObject represents an association, at least two of its ends must not be empty.</li>
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
public interface CwmObject
extends
	GCwmObject<
		  Extent
		, CwmPackage
		, CwmClass
		, GSlot<?, ?, ?, ?, ?>
	>
{


	/**
	 * Designates the Extent that contains this CwmObject.
	 * <p>
	 * Every CwmObject except a root element must belong to exactly one Extent or else be a composite part of another CwmObject (which is a kind of
	 * virtual namespace).
	 * </p>
	 * <p>
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * </p>
	 * <p>
	 * The ElementOwnership association identifies ModelElements owned by Namespaces. ModelElements may be owned by at most one Namespace. Refer to
	 * the above discussion of the Namespace class for more information on the nature of the ownership relationship between Namespaces and
	 * ModelElements.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: Extent</li>
	 * <li><i>defined by</i>	: ObjectExtent::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Extent::ownedElement</li>
	 * </ul>
	 * 
	 * @param namespace The new designated Extent of this CwmObject.
	 * 
	 * @return The old designated Extent of this CwmObject, or <code>null</code>, if none was designated before.
	 * 
	 */
	Extent setNamespace( Extent extent );



	/**
	 * Adds an ObjectSlot to the set of Slots instances owned by this CwmObject.
	 * 
	 * @param objectSlot New slot to be added.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addSlot( ObjectSlot<?> objectSlot );



	/**
	 * Adds an DataSlot to the set of Slots instances owned by this CwmObject.
	 * 
	 * @param dataSlot New slot to be added.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addSlot( DataSlot dataSlot );



	/**
	 * Adds an DataValueSlot to the set of Slots instances owned by this CwmObject.
	 * 
	 * @param dataValueSlot New slot to be added.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addSlot( DataValueSlot<?> dataValueSlot );
}