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
package org.ogrehus.jcwm.api.objectmodel.core.util.references.importedelements;

import java.util.Set;

import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;

public interface Importer<
	PACKAGE extends ImportedElement<? extends Importer<?>>
> extends
	ModelElement
{


	/**
	 * Removes an importer (Package) from this ModelElement, to make this ModelElement no longer imported by the Package.
	 * 
	 * @param importer
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	Boolean removeImporter( ImportedElement<?> importer );



	/**
	 * Returns a set of Package instances that import the ModelElement.
	 * <p>
	 * The ImportedElements association identifies ModelElements that a Package instance imports from other Namespaces. Although any ModelElement may 
	 * be imported by a Package, imported ModelElements are typically other Packages or aggregate Classifiers, such as Class instances.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: Package</li>
	 * <li><i>defined by</i>	: ImportedElements::importer</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: Package::importedElement</li>
	 * </ul>
	 * 
	 * @return A Set of Package instances that import the ModelElement.. 
	 */
	Set<PACKAGE> getImporters();
}