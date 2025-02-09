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
package org.ogrehus.jcwm.impl.foundation.typemapping.util.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.ogrehus.jcwm.api.foundation.typemapping.TypeMapping;
import org.ogrehus.jcwm.api.foundation.typemapping.util.generic.GTypeSystem;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunCwmPackage;


/**
 * Instances of the TypeSystem class collect together the data types (subclasses of GClassifier) defined by a software system and the TypeMapping
 * instances defining how they are mapped to data types in other TypeSystems.
 * <p>
 * TypeMapping instances collected by a TypeSystem instance include both those in which the software system data types act as sources and as targets
 * of mappings. Classifiers and TapeMappings are maintained in a single collection via the ElementOwnership association but can be distinguished by
 * their respective types.
 * </p>
 * <p>
 * Because it is a Package, a TypeSystem can also serve to collect together the GClassifier instances for a particular software system.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-8-2]</b> A TypeSystem may own only Classifiers and TypeMappings.<pre>
 * <b>context</b> TypeSystem <b>inv</b>:
 * self.ownedElement -> forAll( e | e.oclIsKindOf(Classifier) or e.oclIsKindOf(TypeMapping))
 * </pre> 
 * </li>
 * </ul> 
 * 
 * The constraint [C_8_2] is satisfied by generic type-safe construction. The generic parameter <code>OWNED_ELEMENT</code> allows only to add types
 * of GTypeMapping. The GTypeMapping itself allows to contain GClassifiers only. The access to the containing GClassifier can be done by the
 * method {@link #getTypes()}.
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <MANAGER> The type of the holding Namespace that contains this TypeSystem.
 * @param <NAMESPACE> The type of the Namespace that contains this TypeSystem.
 * @param <PACKAGE> The type of the importer of this TypeSystem.
 * @param <OWNED> Type of the ownedElements by this TypeSystem. 
 * @param <IMPORTED> Type of the importedElements of this TypeSystem.
 * 
 */
public abstract class GFunTypeSystem<
	  MANAGER    extends GDataManager<?, ?>
	, NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, OWNED      extends GModelElement<?, ?, ?, ?>
	, IMPORTED   extends GClassifier<?, ?, ?, ?, ?, ?> 
>extends
	GFunCwmPackage<MANAGER, NAMESPACE, PACKAGE, Dependency, Constraint, OWNED, IMPORTED>
implements
	GTypeSystem<MANAGER, NAMESPACE, PACKAGE, OWNED, IMPORTED>
{


	/**
	 * Factory method that creates a new instance of TypeSystem by specific parameters.
	 * 
	 * @param name An identifier for the TypeSystem within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the TypeSystem within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param version A string describing the version of the TypeSystem represented. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>version</code>
	 * is <code>null</code>.
	 * 
	 */
	public GFunTypeSystem( String name, Visibility visibility, String version ) {
		super( name, visibility );
		setVersion( version );
	}



	public String getVersion() {
		return this.version;
	}



	public String setVersion( String version ) {
		String old = this.version;
		this.version = version;
		return old;
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



//====================================================================================================================================================
// TypeSystem methods
//====================================================================================================================================================



	public Collection<GClassifier<?, ?, ?, ?, ?, ?>> getOwnedClassifers() {
		List<GClassifier<?, ?, ?, ?, ?, ?>> classifiers = new ArrayList<GClassifier<?, ?, ?, ?, ?, ?>>();
		for ( OWNED owned  : this.getOwnedElements() ) {
			if ( owned instanceof GClassifier<?, ?, ?, ?, ?, ?> ) {
				classifiers.add( (GClassifier<?, ?, ?, ?, ?, ?>)owned );
			}
		}

		return classifiers;
	}



	public Collection<TypeMapping> getOwnedTypeMappings() {
		return super.getOwnedElementsGeneric( TypeMapping.class );
	}



	public GClassifier<?, ?, ?, ?, ?, ?> getTarget( GClassifier<?, ?, ?, ?, ?, ?> sourceType ) {
		Collection<TypeMapping> sourceMappings = getSourceMappings( sourceType );
		TypeMapping lastChance = null;
		for ( TypeMapping mapping : sourceMappings ) {
			if ( mapping.isBestMatch() ) {
				return mapping.getTargetType();
			}
			lastChance = mapping;
		}

		return lastChance.getTargetType(); // return the last mapping is better than no mapping, if no bestMatch avail.
	}



	protected Collection<TypeMapping> getSourceMappings( GClassifier<?, ?, ?, ?, ?, ?> sourceType ) {
		Collection<TypeMapping> sourceMappings = new ArrayList<TypeMapping>();
		for ( TypeMapping mapping : this.getOwnedTypeMappings() ) {
			if ( mapping.getSourceType().equals( sourceType ) ) {
				sourceMappings.add( mapping );
			}
		}

		return sourceMappings;
	}



	public Collection<GClassifier<?, ?, ?, ?, ?, ?>> getSourceTypes() {
		Integer countTypes = this.getOwnedElements().size();
		Collection<GClassifier<?, ?, ?, ?, ?, ?>> sourceTypes = new HashSet<GClassifier<?,?,?,?,?,?>>( countTypes );
		for ( OWNED owned : getOwnedElements() ) {
			if ( owned instanceof TypeMapping ) {
				TypeMapping mapping = (TypeMapping)owned;
				sourceTypes.add( mapping.getSourceType() );
			}
		}

		return sourceTypes;
	}



	public Collection<GClassifier<?, ?, ?, ?, ?, ?>> getTargetTypes() {
		Integer countTypes = this.getOwnedElements().size();		
		Collection<GClassifier<?, ?, ?, ?, ?, ?>> targetTypes = new HashSet<GClassifier<?,?,?,?,?,?>>( countTypes );
		for ( OWNED owned : this.getOwnedElements() ) {
			if ( owned instanceof TypeMapping ) {
				TypeMapping mapping = (TypeMapping)owned;
				targetTypes.add( mapping.getTargetType() );
			}			
		}

		return targetTypes;
	}


	public Collection<GClassifier<?, ?, ?, ?, ?, ?>> getTypes() {
		Integer countTypes = getOwnedElements().size() * 2; 
		Collection<GClassifier<?, ?, ?, ?, ?, ?>> types = new HashSet<GClassifier<?,?,?,?,?,?>>( countTypes );
		for ( OWNED owned : this.getOwnedElements() ) {
			if ( owned instanceof TypeMapping ) {
				TypeMapping mapping = (TypeMapping)owned;
				types.add( mapping.getSourceType() );				
				types.add( mapping.getTargetType() );
			}			
		}

		return types;
	}	



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunTypeSystem" );
		if ( this.version != null ) {
			out.append( " | version=" + this.version );
		}
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}



//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * A string describing the version of the TypeSystem represented.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>		    : String</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected String version = null;
}