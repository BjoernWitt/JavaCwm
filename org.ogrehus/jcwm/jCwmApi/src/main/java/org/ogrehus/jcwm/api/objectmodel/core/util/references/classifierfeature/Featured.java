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
package org.ogrehus.jcwm.api.objectmodel.core.util.references.classifierfeature;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;

public interface Featured<OWMER extends Classifier<?>>
extends
	ModelElement
{


	/**
	 * Abstract, cause can only be determined, when bounded generic type of CLASSIFIER is defined by a non-generic type to organize the bi-directional
	 * references.
	 * 
	 * @return <code>true</code> if the value for owner changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean removeOwner();



	/**
	 * The Classifier declaring the GFeature.
	 * <p>
	 * The ClassifierFeature association provides a composite aggregation containment relationship between Classifiers and the Features they own. The 
	 * feature end of the association is ordered allowing preservation of the ordering of Features within their owning Classifier. A GFeature can be 
	 * owned by at most one Classifier. The optional character of ownership is intended as a convenience to tools, allowing them to create Features 
	 * prior to linking them to their owning Classifier.
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: generic CLASSIFIER</li>
	 * <li><i>defined by</i>	: ClassifierFeature::owner</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Classifier::feature</li>
	 * </ul>
	 *
	 * @return A classifier declaring this type of Featured. Can be <code>null</code> if none is available.
	 * 
	 */
	OWMER getOwner();
}