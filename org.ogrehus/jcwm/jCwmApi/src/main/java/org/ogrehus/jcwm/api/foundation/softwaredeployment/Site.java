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
package org.ogrehus.jcwm.api.foundation.softwaredeployment;

import java.util.Collection;

import org.ogrehus.jcwm.api.foundation.businessinformation.Location;


/**
 * A Site represents a geographical location. It provides a grouping mechanism for a group of machines at the same location.
 * <p>
 * Sites may be documented at different levels of granularity; containment links may be used to record hierarchical relationships between Sites.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-8-3]</b> A Site must not have a containingSite reference that refers to itself.</li>
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
public interface Site
extends
	Location 
{


	/**
	 * Identifies a Site of which the current Site forms a part.
	 * 
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>			: Site</li>
	 * <li><i>defined by</i>	: RelatedSites::containingSite</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * </ul>
	 * 
	 * <p>
     * <b>Constraints</b>
     * </p>
     * <ul>
     * <li><b>[C-8-3]</b> A Site must not have a containingSite reference that refers to itself.</li>
     * </ul>  
     * 
     * @return The Sites of which the current Site forms a part. 
	 * 
	 */
	Collection<Site> getContainingSites();
}