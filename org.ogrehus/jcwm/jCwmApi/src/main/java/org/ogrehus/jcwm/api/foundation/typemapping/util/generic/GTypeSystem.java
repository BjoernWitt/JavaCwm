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
package org.ogrehus.jcwm.api.foundation.typemapping.util.generic;

import java.util.Collection;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;


/**
 * Instances of the TypeSystem class collect together the data types (subclasses of GClassifier) defined by a software system and the TypeMapping 
 * instances defining how they are mapped to data types in other TypeSystems. 
 * <p>
 * TypeMapping instances collected by a TypeSystem instance include both those in which the software system data types act as sources and as 
 * targets of mappings. Classifiers and TapeMappings are maintained in a single collection via the ElementOwnership association but can be 
 * distinguished by their respective types.
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
 * The constraint [C_8_2] is satisfied by generic type-safe construction. The generic parameter 
 * <code>OWNED_ELEMENT</code> allows only to add types of GTypeMapping. The GTypeMapping itself allows to contain 
 * GClassifiers only. The access to the containing GClassifier can be done by the method {@link #getTypes()}.     
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a> 
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this Package.
 * @param <PACKAGE> The type of the importer of this Package.
 * @param <OWNED> Type of the ownedElements by this Package 
 * @param <IMPORTED> Type of the importedElements of this Package.
 * 
 */
public interface GTypeSystem<
	  MANAGER   extends GDataManager<?, ?>
	, NAMESPACE extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE   extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, OWNED     extends GModelElement<?, ?, ?, ?>
	, IMPORTED  extends GClassifier<?, ?, ?, ?, ?, ?>
> extends
	  GCwmPackage<MANAGER, NAMESPACE, PACKAGE, Dependency, Constraint, OWNED, IMPORTED>
{


//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Adds a dependency to this ModelElement.
	 * 
	 * @param dependency A Dependency in witch this ModelELement is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */	
	Boolean addDependency( Dependency dependency );



	/**
	 * Adds a constraint to this element.
	 * 
	 * <p>
	 * <b>Constraints:</b>:
	 * </p>
	 * <ul>
	 * <li><b>[C 3-1]</b> A Constraint cannot be applied to itself.
	 * <pre>
	 * <b>context</b> Constraint <b>inv</b>:
	 *   <b>not</b> self.constrainedElement -> includes( self )
	 * </pre></li>
	 * </ul> 
	 * 
	 * @param constraint A Constrain that must be satisfied by this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws ConstraintViolation if the Constraint <b>[C 3-1]</b> was violated, caused by registering of bidirectional reference. 
	 * 
	 */
	Boolean addConstraint( Constraint constraint );



//====================================================================================================================================================
// TypeSystem capabilities
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
	 * @return The version of the TypeSystem represented.
	 * 
	 */
	String getVersion();



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
	 * @param version A string describing the version of the TypeSystem represented.
	 * 
	 * @return The old value of the property <code>version</code>.
	 * 
	 */
	String setVersion( String version );



	/**
	 * Returns a Collection of all source Classifiers mapped in this TypeSystem.
	 * 
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * sourceTypes : Set(GClassifier)
	 * sourceTypes = self.ownedElement.sourceType
	 * </pre>
	 * 
	 * @return A Collection of all source Classifiers owned in this TypeSystem.
	 * 
	 */	
	Collection<GClassifier<?, ?, ?, ?, ?, ?>> getSourceTypes();



	/**
	 * Returns a Collection of all target Classifiers mapped in this TypeSystem.
	 * 
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * targetTypes : Set(GClassifier)
	 * targetTypes = self.ownedElement.targetType
	 * </pre>
	 * 
	 * @return A Collection of all target Classifiers owned in this TypeSystem.
	 * 
	 */	
	Collection<GClassifier<?, ?, ?, ?, ?, ?>> getTargetTypes();	



	/**
	 * Returns a Collection of all Classifiers mapped in this TypeSystem.
	 * 
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allTypes : Set(GClassifier)
	 * allTypes = self.sourceTypes -> union( self.sourceTypes.targetTypes )
	 * </pre>
	 * 
	 * @return A Collection of all mapped Classifiers owned in this TypeSystem.
	 * 
	 */	
	Collection<GClassifier<?, ?, ?, ?, ?, ?>> getTypes();



	/**
	 * Returns the best match for the sourceType in this TypeSystem.
	 * 
	 * @param sourceType The Type that should be mapped
	 * 
	 * @return
	 */
	GClassifier<?, ?, ?, ?, ?, ?> getTarget( GClassifier<?, ?, ?, ?, ?, ?> sourceType );
}