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
package org.ogrehus.jcwm.api.resource.relational.util;

import java.sql.DatabaseMetaData;

/**
 * In the meta-model ReferentialRuleType defines an enumeration that indicates if the validity of the ForeignKey is to be tested at each statement or 
 * at the end of a transaction.
 * 
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>initiallyDeferred</code> - see SQL92 for definition</li>
 * <li><code>initiallyImmediate</code> - see SQL92 for definition</li>
 * <li><code>notDeferrable</code> - </li>
 * <li>The default value is <code>notDeferrable</code>.</li>
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public enum Deferability {
	  initiallyDeferred
	, initiallyImmediate
	, notDeferrable
	;



	public static Deferability getDefault() { 
		return Deferability.notDeferrable; 
	}



	public static Deferability byJDBC( Short deferability ) {
		if ( deferability == null ) {
			return notDeferrable;
		}
		switch ( deferability.shortValue() ) {
		case DatabaseMetaData.importedKeyInitiallyDeferred: return initiallyDeferred;
		case DatabaseMetaData.importedKeyInitiallyImmediate: return initiallyImmediate;
		default: return notDeferrable;
		}
	}
}