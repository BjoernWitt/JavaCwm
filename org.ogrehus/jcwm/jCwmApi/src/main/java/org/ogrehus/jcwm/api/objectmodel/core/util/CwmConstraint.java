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
 *//**
 * 
 */
package org.ogrehus.jcwm.api.objectmodel.core.util;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * Constraints are statements of facts assumed to be true always and are core parts of any expressive meta-model.
 * <p>
 * Constraints are statements of facts assumed to be true always and are core parts of any expressive meta-model. CWM constraints are expressed in
 * two ways. Some constraints are represented in the structure of the model itself. These constraints take the form of multiplicity statements for
 * attributes and association ends, positioning of containment and inheritance relationships, and the abstractness of some meta-classes. Other
 * constraints are, following OMG requirements, expressed as OCL statements. Within the body of a chapter, these constraints are described in text,
 * and corresponding OCL statements are referenced by number and enclosed in square brackets. For example, a reference to the third OCL statement in
 * a chapter would appear as [C-3]. OCL statements within a chapter are numbered sequentially from C-1 and collected together in a section at the
 * end of chapter. Because the Foundation chapter contains an additional layer of subsections, constraint numbers in it include the subsection number;
 *  for example, [C-2-1] is the first constraint in the second subsection of the chapter.
 * </p>
 * <p>
 * A complete description of CWM includes both structural constraints and their accompanying OCL statements. Structural constraints are used to
 * capture general features of CWM and are generally preferred over OCL statements. OCL statements are used when capturing a constraint structurally
 * would overly complicate or otherwise obscure the basic intent and understanding of the meta-model. OCL statements are written to capture specific
 * situational constraints (such as uniqueness, filters for derived associations, and dependencies between attribute values) or to express
 * relationships between instances that cannot be inferred from the meta-model itself (such as or-ed or xor-ed associations and attributes, references
 * to super-classes, or other related instances, subsets, and implied transitivity).
 * </p>
 * <p>
 * Following the ground rules of OCL, CWM does not specify the nature of actions taken when constraints fail. Rather, specification of failure
 * actions and recognition of failure modes are left to individual implementations of CWM.
 * </p>
 * <p>
 * Unless otherwise stated for a particular OCL constraint, the evaluation policy for all CWM constraints is deferred meaning that constraint checking
 * should occur at the end of bulk operations, such as a load, or as part of a model validation operation.
 * </p>
 * <p> 
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> by
 * <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public enum CwmConstraint {

	// general
	  readOnly
	, multiplicity
	, methodeNoMoreSuitable
	// objectmodel::core
	, C_3_1
	, C_3_2
	, C_3_3
	, C_3_4
	, C_3_5
	, C_3_6
	// objectmodel::behavioral
	, C_4_3
//  , C_4_4 satisfied by method signature for adding a features to an interface
	, C_4_6
	, C_4_7
	, C_4_8
	, C_4_9
	, C_4_10
	// objectmodel::relationships
	, C_5_1
	, C_5_2
	, C_5_3
	, C_5_4
	, C_5_6
	// objectmodel::instance
	, C_6_Extent
	, C_6_1
	, C_6_2
	, C_6_7
	, callAction_OperationMissing
	// foundation::datatypes
	, Foundation_C_4_1
    // foundation::keyindexes
	, Foundation_C_6_1
	// foundation::keyindexes
	, Foundation_C_8_1
	;


	/**
	 * Returns a MessageFormat supported by i18n for each constraint.
	 * 
	 * @param constraint The violated constraint.
	 * 
	 * @param locale The Locale to be used for any further formatting.
	 * 
	 * @param objects The objects used to populate details to the messages.
	 * 
	 * @return A MessageFormat suitable to the parameter <code>constraint</code>.
	 * 
	 */
	public static String getMessage( CwmConstraint constraint, Locale locale, Object ...objects ) {
		// TODO: May be better to use a property-file later here!		
		switch ( constraint ) {
		// general constraint
		case readOnly:	return getMessage_ReadOnly( locale ).format( objects );
		case multiplicity: return getMessage_Multiplicity( locale ).format( objects );
		case methodeNoMoreSuitable: return getMessage_MethodNoMoreSuitable(locale).format( objects );
		// objectmodel::core		
		case C_3_1: 	
			return getMessage_C_3_1( locale ).format( objects );
		case C_3_2: 	
			return getMessage_C_3_2( locale ).format( objects );
		case C_3_6:		
			return getMessage_C_3_6( locale ).format( objects );
		// objectmodel::behavioral		
		case C_4_3:		
			return getMessage_C_4_3( locale ).format( objects );
		case C_4_6:		
			return getMessage_C_4_6( locale ).format( objects );
		case C_4_7:		
			return getMessage_C_4_7( locale ).format( objects );
		case C_4_8:		
			return getMessage_C_4_8( locale ).format( objects );
		case C_4_9:		
			return getMessage_C_4_9( locale ).format( objects );
		case C_4_10:		
            return getMessage_C_4_10( locale ).format( objects );
		// objectmodel::relationships
		case C_5_1:     
			return getMessage_C_5_1( locale ).format( objects );
		case C_5_2:     
			return getMessage_C_5_2( locale ).format( objects );
		case C_5_3:     
			return getMessage_C_5_3( locale ).format( objects );
		case C_5_4:     
			return getMessage_C_5_4( locale ).format( objects );
		case C_5_6:     
			return getMessage_C_5_6( locale ).format( objects );
		// objectmodel::instance		
		case C_6_1:     
			return getMessage_C_6_1( locale ).format( objects );
		case C_6_2:     
			return getMessage_C_6_2( locale ).format( objects );
		case C_6_7:     
			return getMessage_C_6_7( locale ).format( objects );
		case Foundation_C_4_1:
			return getMessage_Foundation_C_4_1( locale ).format( objects );
		case Foundation_C_6_1: 
			return getMessage_Foundation_C_6_1( locale ).format( objects );
		case Foundation_C_8_1: 
			return getMessage_Foundation_C_8_1( locale ).format( objects );
			
		default: 
			return null;
		}
	}



	protected static MessageFormat getMessage_ReadOnly( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[general] The object to be modified is readOnly to protect a Constraint. Use legal access methods to the object.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[general] Das zu modifizierte Objekt kann nur gelesen werden, da es durch einen Constraint überwacht wird. "
					+ "Bitte verwenden sie die zulässigen Zugriffsmethoden in den verwaltenden Klassen."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_Multiplicity( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[general] The multiplicity {0} was violated in {1} ";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern  = "[general] Die Multiplizität {0} wurde verletzt in {1} ";
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_MethodNoMoreSuitable( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[general] The method {0} is no longer suitable, use the method {1} instead!";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern  = "[general] Die Methode {0} ist nicht länger passend, bitte die Methode {1} verwenden!"; 
		}
		return new MessageFormat( pattern, locale );
	}



	protected static MessageFormat getMessage_C_3_1( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-3-1] A Constraint cannot be applied to itself. Affected Element is {0}.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-3-1] Ein Constraint kann nicht zu sich selbst hinzugefügt werden. Betroffenes Element ist: {0}."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_3_2( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-3-2] A DataType cannot contain any other ModelElements. Affected DataType is {0}.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-3-2] Einem DataType kann kein ModelElemente hinzugefügt werden. Betroffenes DataType ist: {0}."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_3_6( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-3-6] The baseClass of a stereotype name must be provided.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-3-6] Es muss in einem Stereotype immer ein Wert existieren für die Eigenschaft: baseClass"; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_4_3( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-4-3] The number of arguments must be the same as the number of the Operation.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-4-3] Die Anzahl der Argumente muss mit denen der Operation übereinstimmen."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_4_6( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-4-6] All Features defined in an Interface are public.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-4-6] Alle Features, die in einem Interface definiert werden müssen public sein."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_4_7( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-4-7] If the realized Operation is a query, then so is the Method.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-4-7] Wenn die realisierende Operation eine Query ist, dann muss es die Methode ebenfalls sein."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_4_8( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-4-8] The signature of the Method should be the same as the signature of the realized Operation.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-4-8] Die Signatur der Methode sollte die gleiche sein, wie die der zu realisierenden Operation."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_4_9( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-4-9] The visibility of the Method should be the same as for the realized Operation.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-4-9] Die Sichtbarkeit der Methode sollte die gleiche sein, wie die der zu realisierenden Operation."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_4_10( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-4-10] The realized Operation must be a feature (possibly inherited) of the same Classifier as the Method.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-4-10] Die zu realisierende Operation muss zuvor als ein Feature (möglicherweise auch vererbt) in dem gleichem Classifier "
					+ "bekannt sein."; 
		}
		return new MessageFormat( pattern, locale );
	}



	protected static MessageFormat getMessage_C_5_1( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-5-1] An Association must have at least 2 AssociationEnds.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-5-1] Eine Association besteht aus mindestens 2 AssociationEnds."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_5_2( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-5-2] The AssociationEnds must have a unique name within the association. The name \"{0}\" is already available within "
						+ "the Association \"{1}\".";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-5-2] Der Name eines AssociationEnds muss innerhalb einer Association eindeutig sein. Der Name \"{0}\" wurde innerhalb "
					+ "der Association \"{1}\" bereits vergeben."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_5_3( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-5-3] At most one AssociationEnd may be an aggregation or composition. The AssociationEnd \"{0}\" is already assigned "
						+ "to the Association \"{1}\" and is typeof aggregation or composition.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-5-3] Nur ein AssociationEnd darf maximal eine Aggregation oder Composition sein. Das AssociationEnd \"{0}\" ist bereits "
					+ "der Association \"{1}\" zugewiesen und vom Typ Aggregation oder Composition."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_5_4( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-5-4] If an Association has three or more AssociationEnds, then no AssociationEnd may be an aggregation or composition. The AssociationEnd \"{0}\" can't be typeof aggregation or composition in the Association \"{1}\".";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-5-4] Wenn eine Association 3 oder mehr AssociationEnds haben soll, dann darf keine der AssociationEnds vom Typ Aggregation oder Composition sein. Das AssociationEnd \"{0}\" in derAssociation \"{1}\" kann nicht vom Typ Aggregation oder Composition sein."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_5_6( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-5-6] An AssociationEnd must have an owning Association.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-5-6] Ein AssociationEnd muss eine besitzende Association haben."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_6_1( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-6-1] - A DataValue originates from a Classifier that is a GDataType";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-6-1] Ein DataValue entsteht aus einem Classifier, der ein Datentyp sein muss."; 
		}
		return new MessageFormat( pattern, locale ); 
	}


	
	protected static MessageFormat getMessage_C_6_2( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-6-2] - A DataValue has no Slots.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-6-2] - Ein DataValue besitzt keine Slots."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_C_6_7( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[C-6-7] The StructuralFeature instance obtained via the feature reference inherited from Slot must be an Attribute.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[C-6-7] Die Instanz StructuralFeature aus der Referenz feature durch die Vererbung von Slot muss in DataSlot ein Attribut sein."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_Foundation_C_4_1( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[Foundation_C_4_1] The TypeAlias instance \"{0}\" cannot alias itself.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[Foundation_C_4_1] Der TypeAlias \"{0}\" kann sich nicht selber als Pseudonym (Alias) benennen."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_Foundation_C_6_1( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found		
		String pattern  = "[Foundation_C_6_1] The isAscending attribute is valid only if the isSorted attribute is true.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[Foundation_C_6_1] Das Attribut: isAscending is nur gültig, wenn das Attribut: isSorted true ist."; 
		}
		return new MessageFormat( pattern, locale ); 
	}



	protected static MessageFormat getMessage_Foundation_C_8_1( Locale locale ) {
		String language = locale.getLanguage();
		// English language is default if no matching language was found
		String pattern  = "[Foundation_C_8_1] The sourceType and targetType references may not refer to the same Classifier instance.";
		if ( Locale.GERMAN.getDisplayLanguage().equals( language ) ) {
			pattern = "[Foundation_C_8_1] Der Quelltyp und der Zieltyp dürfen nicht den gleichen Classifier referenzieren."; 
		}
		return new MessageFormat( pattern, locale ); 
	}
}