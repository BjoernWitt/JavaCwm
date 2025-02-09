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
package org.ogrehus.jcwm.impl.objectmodel.core.util.generic;

import java.util.SortedSet;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.ogrehus.jcwm.api.objectmodel.behavioral.CwmMethod;
import org.ogrehus.jcwm.api.objectmodel.behavioral.Operation;
import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.Stereotype;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.classifierfeature.Featured;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GClassifier;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;

import org.ogrehus.jcwm.api.objectmodel.relationships.Generalization;


/**
 * A classifier is an element that describes structural and behavioral features; it comes in several specific forms, including class, data type,
 * interface, component, and others that are defined in other meta-model packages.
 * 
 * <p>
 * GClassifier is often used as a type. In the meta-model a GClassifier may declare a collection of Features, such as Attributes, Operations, and
 * Methods. It has a name, which is unique in the Namespace enclosing the GClassifier. GClassifier is an abstract meta-class.
 * </p>
 * <p>
 * GClassifier is a child of Namespace. As a Namespace, a GClassifier may declare other Classifiers nested in its scope. Nested Classifiers may be
 * accessed by other Classifiers only if the nested Classifiers have adequate visibility. There are no data value or state consequences of nested
 * Classifiers; that is, it is not an aggregation or composition.
 * </p>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the namespace that contains this Classifier.
 * @param <PACKAGE> The type of the importer of this Classifier.
 * @param <DEPENDENCY> The type of Dependency of this Classifier.
 * @param <CONSTRAINT> The type of the Constraint under which this Classifier is. 
 * @param <OWNED> Type of the ownedElements by this Classifier.  
 * @param <FEATURE> Type of the Featured of this Classifier. 
 * 
 */
public abstract class GFunClassifier<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
	, OWNED      extends GModelElement<?, ?, ?, ?>
	, FEATURE    extends GFeature<?, ?, ?, ?, ?>
>extends
	GFunNamespace<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWNED>
implements
	GClassifier<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT, OWNED, FEATURE> 
{


	/**
	 * Creates a new instance of FunClassifier by specific parameters.
	 * 
	 * @param name An identifier for the FunClassifier within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunClassifier within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this Classifier is abstract and can not be instantiated, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>. 
	 *
	 */
	protected GFunClassifier( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility ); // may throw NullPointerException
		this.setAbstract( notInstantiable ); // may throw NullPointerException
		this.features = new TreeSet<FEATURE>();
		this.generalizations = new HashSet<Generalization>();
		this.specializations = new HashSet<Generalization>();
	}



	public Boolean isAbstract() {
		return this.isAbstract;
	}



	public Boolean setAbstract( Boolean notInstantiable ) {
		Boolean old = this.isAbstract;
		this.isAbstract = notInstantiable;
		return old;
	}



	protected <E extends FEATURE> Boolean addFeatureGeneric( E feature ) {
		if ( feature != null && this.features.add( feature ) ) {
//System.out.println( "addFeatureGeneric( " + feature.getQualifiedName() + " ) -> size=" + this.features.size() );
			invokeByReflection( feature, "setOwner", this ); // bi-directional reference
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}



	public Boolean removeFeature( Featured<?> feature ) {
		if ( this.features.remove( feature ) ) {
			feature.removeOwner(); // remove bi-directional reference
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}	



	public SortedSet<FEATURE> getFeatures() {
		return this.features;
	}



	/**
	 * Returns a Features owned by the Classifier by type and name.
	 * 
	 * To find the feature, a lookup to all Features is done, so you can find even inherited features.
	 * 
	 * @param typeof The Class of the feature to get from this Classifier.
	 * 
	 * @param simpleName The simpleName name of the feature within its classifier.
	 * 
	 * 
	 * @return The matching feature or <code>null</code> if none exists.
	 * 
	 */
	public <TYPE extends FEATURE> TYPE getFeature( Class<TYPE> typeof, String simpleName ) {
		if ( simpleName == null ) {
			return null; // no name nothing to do...
		}

		for ( TYPE byType : getAllFeaturesGeneric( typeof ) ) {
			if ( simpleName.equals( byType.getSimpleName() ) ) {
				return byType;
			}
		}

		return null;
	}



	/**
	 * The operation specification yields the set of Classifiers that the current GClassifier realizes.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * specification: Set(GClassifier)
	 * specification = self.clientDependency -> 
	 *     select(d | d.stereotype.name = "realization"
	 *     and 
	 *     d.supplier.oclIsKindOf(GClassifier)).supplier.oclAsType(GClassifier)
	 * </pre>
	 * 
	 * @return A set of Classifiers that the current GClassifier realizes, or an empty set if none exists.
	 * 
	 */
	public Set<GClassifier<?, ?, ?, ?, ?, ?>> getSpecifications() {
		Set<GClassifier<?, ?, ?, ?, ?, ?>> specifications = new HashSet<GClassifier<?, ?, ?, ?, ?, ?>>();
		if ( this.dependencies != null ) {
			for ( DEPENDENCY dependency : this.dependencies ) {
				Stereotype d = dependency.getStereotype();
				if ( d != null && d.getName().toString().equalsIgnoreCase( "realization" ) ) { 
					// done: d.stereotype.name = "realization"
					for ( ModelElement supplier : dependency.getSuppliers() ) {
						if ( supplier instanceof GClassifier<?, ?, ?, ?, ?, ?> ) {
							specifications.add( (GClassifier<?, ?, ?, ?, ?, ?>)supplier );
						}
					}
				}
			}
		}
		return specifications;
	}



	/**
	 * The operation allContents returns a Set containing all ModelElements contained in the GClassifier together with the contents inherited from its
	 * parents.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allContents : Set(ModelElement);
	 * allContents = self.contents -> union( self.parent.allContents 
	 *                                    -> select(e |  e.elementOwnership.visibility = #public 
	 *                                                or e.elementOwnership.visibility = #protected )
	 *                                )
	 * </pre>
	 * 
	 * @return A HashSet containing all direct parents of this classifier, or an empty set if none exists.
	 * 
	 */
	@Override
	public Set<ModelElement> getAllContents() {
		Set<ModelElement> allContents = new HashSet<ModelElement>();
		allContents.addAll( this.getContents() );  
		for ( GClassifier<?, ?, ?, ?, ?, ?> parent : this.getParents() ) {
			for ( ModelElement content : parent.getAllContents() ) {
				if ( content instanceof GNamespace<?, ?, ?, ?, ?> ) {
					GNamespace<?, ?, ?, ?, ?> contentNamespace = (GNamespace<?, ?, ?, ?, ?>)content; 
					for ( GModelElement<?, ?, ?, ?> owned : contentNamespace.getOwnedElements() ) {
						switch ( owned.getVisibility() ) {
						case _public:
						case _protected:
							allContents.add( owned );
							break;
						default: // do nothing
						}
					}
				}
			}
		}
		return allContents;
	}



	/**
	 * Identifies the set of Generalization instances in which the GClassifier acts as a child in the inheritance  hierarchy.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Generalization</li> 
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 *  
	 * @return A set of Classifiers that act as a child in the inheritance hierarchy, or an empty Set if none exists.
	 * 
	 */
	public Set<Generalization> getGeneralizations() {
		return this.generalizations;
	}



	public Boolean addGeneralization( Generalization generalization ) {
		if ( generalization != null && this.generalizations.add( generalization ) ) {
			generalization.setChild( this ); // bi-directional reference
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeGeneralization( Generalization generalization ) {
		if ( generalization == null ) {
			return Boolean.TRUE;
		}

		if ( this.generalizations.remove( generalization ) ) {
			generalization.removeChild(); // remove bi-directional reference
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	/**
	 * Identifies the set of Generalization instances in which the GClassifier acts as a parent in the inheritance hierarchy.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Generalization</li> 
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 *  
	 * @return A set of Classifiers that act as a parent in the inheritance hierarchy, or an empty Set if none exists.
	 * 
	 */
	public Set<Generalization> getSpecializations() {
		return this.generalizations;
	}



	public Boolean addSpecialization( Generalization specialization ) {
		if ( specialization != null && this.specializations.add( specialization ) ) {
			specialization.setParent( this ); // bi-directional reference
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}



	public Boolean removeSpecialization( Generalization specialization ) {
		if ( specialization == null ) {
			return Boolean.TRUE;
		}

		if ( this.specializations.remove( specialization ) ) {
			specialization.removeParent(); // remove bi-directional reference
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	/**
	 * The operation parent returns a Set containing all direct parents of a GClassifier.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * parent : Set(GClassifier);
	 * parent = self.generalization.parent
	 * </pre>
	 * 
	 * @return A HashSet containing all direct parents of this classifier, without this classifier itself, or an empty one if none exists.
	 * 
	 */
	public Set<GClassifier<?, ?, ?, ?, ?, ?>> getParents() {
		int maxParents = this.generalizations.size(); // each has only one this saves memory...
		Set<GClassifier<?, ?, ?, ?, ?, ?>> parents = new HashSet<GClassifier<?, ?, ?, ?, ?, ?>>( maxParents );
		for ( Generalization generalization : this.generalizations ) {
			parents.add( generalization.getParent() );
		}

		return parents;
	}



	/**
	 * The operation allParents returns a Set containing all the Classifiers inherited by this GClassifier (the transitive closure), excluding the
	 * GClassifier itself.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allParents : Set(GClassifier);
	 * allParents = self.parent->union(self.parent.allParents)
	 * </pre>
	 * 
	 * @return A HashSet containing all direct parents of this classifier, or 
	 * an empty set if none exists.
	 * 
	 */
	public Set<GClassifier<?, ?, ?, ?, ?, ?>> getAllParents() {
		Set<GClassifier<?, ?, ?, ?, ?, ?>> parents    = getParents();
		Set<GClassifier<?, ?, ?, ?, ?, ?>> allParents = new HashSet<GClassifier<?, ?, ?, ?, ?, ?>>();
		allParents.addAll( getParents() );
		for ( GClassifier<?, ?, ?, ?, ?, ?> parent : parents ) {
			allParents.addAll( parent.getAllParents() );
		}

		return parents;
	}



	/**
	 * The operation allFeatures results in a Set containing all Features of the GClassifier itself and all its inherited Features.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 *     allFeatures : Set(Featured);
	 *     allFeatures = self.feature->union(self.parent.oclAsType(GClassifier).allFeatures)
	 * </pre>
	 *  
	 * @return A set containing all {@link Featured}s accomplished to the OCL, or an empty set if none exists.
	 * 
	 */
	public Set<GFeature<?, ?, ?, ?, ?>> getAllFeatures() {
		Set<GFeature<?, ?, ?, ?, ?>> allFeatures = new HashSet<GFeature<?, ?, ?, ?, ?>>();
		allFeatures.addAll( this.features ); 
		for ( GClassifier<?, ?, ?, ?, ?, ?> parent : getParents() ) {
			allFeatures.addAll( parent.getAllFeatures() );
		}

		return allFeatures;
	}



	/**
	 * The operation allAttributes results in a Set containing all Attributes of the GClassifier itself and all its inherited Attributes.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allAttributes : set(Attribute);
	 * allAttributes = self.allFeatures->select(f | f.oclIsKindOf(Attribute))
	 * </pre>
	 * 
	 *  
	 * @return A set containing all {@link Attribute}s accomplished to the OCL, or an empty set if none exists.
	 * 
	 */
	public Set<Attribute> getAllAttributes() {
		Set<Attribute> allAttributes = new HashSet<Attribute>();    	
		for ( GFeature<?, ?, ?, ?, ?> feature: getAllFeatures() ) {
			if ( feature instanceof Attribute ) {
				allAttributes.add( (Attribute)feature );
			}
		}

		return allAttributes;
	}



	/**
	 * The operation allOperations results in a Set containing all Operations of the GClassifier itself and all its inherited Operations.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allOperations : set(Operation);
	 * allOperations = self.allFeatures->select( f | f.oclIsKindOf(Operation))
	 * </pre>
	 * 
	 *  
	 * @return A set containing all {@link Operation}s accomplished to the OCL, or an empty set if none exists.
	 * 
	 */
	public Set<Operation> getAllOperations() {
		return getAllFeaturesGeneric( Operation.class );
	}



	/**
	 * The operation allMethods results in a Set containing all Methods of the GClassifier itself and all its inherited Methods.
	 * <p>
	 * <b>OCL of this method:</b>:
	 * </p>
	 * <pre>
	 * allMethods : set(CwmMethod);
	 * allMethods = self.allFeatures->select( f | f.oclIsKindOf(CwmMethod))
	 * </pre>
	 * 
	 *  
	 * @return A set containing all {@link CwmMethod}s accomplished to the OCL, or an empty set if none exists.
	 * 
	 */
	public Set<CwmMethod> getAllMethods() {
		return getAllFeaturesGeneric( CwmMethod.class );
	}



	public <F extends GFeature<?, ?, ?, ?, ?>> Set<F> getAllFeaturesGeneric( Class<F> featureType ) {
		Set<F> allFeatures = new HashSet<F>();
		for ( GFeature<?, ?, ?, ?, ?> feature : getFeatures() ) {
			if ( featureType.isInstance( feature ) ) {
				allFeatures.add( featureType.cast( feature ) );
			}
		}

		return allFeatures;
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================



	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunClassifier" );
		if ( this.isAbstract != null ) {
			out.append( " | isAbstract=" + this.isAbstract );
		}
		out.append( collectionToString( "features", this.features, true ) );        
		out.append( collectionToString( "generalizations", this.generalizations ) );
		out.append( collectionToString( "specializations", this.specializations ) );		
		out.append( " |\nextends: " );
		out.append( super.toString() );
		out.append( "]" );

		return out.toString();
	}



//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * Element: isAbstract
	 *  
	 * An abstract Classifier is not instantiable.
	 * <ul>
	 * <li><i>type</i>			: Boolean</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 *  
	 */
	protected Boolean isAbstract = null;



	/**
	 * Reference: feature
	 * 
	 * An ordered list of Features owned by the Classifier.
	 * 
	 * <ul>
	 * <li><i>class</i>			: Featured</li>
	 * <li><i>defined by</i>	: ClassifierFeature::feature</li>
	 * <li><i>multiplicity</i>	: zero or more; ordered</li>
	 * <li><i>inverse</i>		: Featured::owner</li>
	 * </ul>
	 */
	protected SortedSet<FEATURE> features = null;



	/**
	 * Reference: generalization
	 * <p>
	 * Identifies the set of Generalization instances in which the Classifier acts as a child in the inheritance hierarchy.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Generalization</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 * 
	 */
	protected Set<Generalization> generalizations = null;



	/**
	 * Reference: specializations
	 * <p>
	 * Identifies the set of Generalization instances in which the Classifier acts as a parent in the inheritance hierarchy.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Generalization</li>
	 * <li><i>multiplicity</i>: zero or more</li>
	 * </ul>
	 * 
	 */
	protected Set<Generalization> specializations = null;
}