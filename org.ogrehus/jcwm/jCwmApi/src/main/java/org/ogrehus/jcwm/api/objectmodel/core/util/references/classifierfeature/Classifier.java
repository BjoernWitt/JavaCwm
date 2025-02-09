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

import java.util.SortedSet;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;


public interface Classifier<FEATURE extends Featured<?>>
extends
	ModelElement
{


	/**
	 * Removes a Featured owned by the Classifier.
	 * 
	 * @param feature that will no longer be owned by this Classifier
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeFeature( Featured<?> feature );



	/**
	 * Returns an ordered list of Features owned by the Classifier.
	 * 
	 * <p>
	 * The ClassifierFeature association provides a composite aggregation containment relationship between Classifiers and the Features they own. The 
	 * feature end of the association is ordered allowing preservation of the ordering of Features within their owning GClassifier. A GFeature can be 
	 * owned by at most one GClassifier. The optional character of ownership is intended as a convenience to tools, allowing them to create Features 
	 * prior to linking them to their owning GClassifier.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: generic FEATURE</li>
	 * <li><i>defined by</i>	: ClassifierFeature::feature</li>
	 * <li><i>multiplicity</i>	: zero or more; ordered</li>
	 * <li><i>inverse</i>		: GFeature::owner</li>
	 * </ul>
	 * 
	 * @return A List of features. Will be empty List, if none exists.
	 * 
	 */
	SortedSet<FEATURE> getFeatures();



	/**
	 * Returns a Features owned by the Classifier by type and name.
	 * 
	 * To find the feature, a lookup to all Features is done, so you can find even inherited features.
	 * 
	 * @param typeof The Class of the feature to get from this Classifier.
	 * 
	 * @param qualifiedName The full qualified name of the feature.
	 * 
	 * @return The matching feature or <code>null</code> if none exists.
	 * 
	 */
	<TYPE extends FEATURE> TYPE getFeature( Class<TYPE> typeof, String qualifiedName );
}