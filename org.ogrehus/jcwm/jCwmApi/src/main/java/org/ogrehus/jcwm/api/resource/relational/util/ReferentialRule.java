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
 * In the meta-model ReferentialRule defines an enumeration that indicates the disposition of the data records containing the foreign key value when 
 * the record of the matching primary key is deleted.
 * 
 * <p>
 * Values and meanings:
 * </p>
 * <ul>
 * <li><code>importedKeyNoAction</code> - </li>
 * <li><code>importedKeyCascade</code> - </li>
 * <li><code>importedKeySetNull</code> - </li>
 * <li><code>importedKeyRestrict</code> - </li>
 * <li><code>importedKeySetDefault</code> - </li>  
 * <li>The default value is <code>importedKeySetDefault</code>.</li>
 * </ul> 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public enum ReferentialRule {
	  importedKeyNoAction
	, importedKeyCascade
	, importedKeySetNull
	, importedKeyRestrict
	, importedKeySetDefault
	;


	public static ReferentialRule getDefault() { 
		return ReferentialRule.importedKeySetDefault; 
	}	



	public static ReferentialRule byJDBC( Short referentialRule ) {
		if ( referentialRule == null ) {
			return importedKeyNoAction;
		}
		
		switch ( referentialRule.shortValue() ) {
		case DatabaseMetaData.importedKeyNoAction:   return importedKeyNoAction;
		case DatabaseMetaData.importedKeyCascade:    return importedKeyCascade;
		case DatabaseMetaData.importedKeySetNull:    return importedKeySetNull;
		case DatabaseMetaData.importedKeyRestrict:   return importedKeyRestrict;
		case DatabaseMetaData.importedKeySetDefault: return importedKeySetDefault;
		default: return importedKeyNoAction;
		}
	}	
}