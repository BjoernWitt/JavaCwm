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

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.Cwm;
import org.ogrehus.jcwm.api.foundation.businessinformation.Description;
import org.ogrehus.jcwm.api.foundation.businessinformation.Document;
import org.ogrehus.jcwm.api.foundation.businessinformation.ResponsibleParty;
import org.ogrehus.jcwm.api.objectmodel.core.ModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.Stereotype;
import org.ogrehus.jcwm.api.objectmodel.core.TaggedValue;
import org.ogrehus.jcwm.api.objectmodel.core.Name;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GConstraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GCwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GDependency;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GNamespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.dependencyclient.Clientable;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.elementconstraint.ConstraintedElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.importedelements.ImportedElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.elementownership.Namespace;
import org.ogrehus.jcwm.api.objectmodel.core.util.references.elementownership.OwnedElement;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

import org.ogrehus.jcwm.api.objectmodel.core.util.references.dependencyclient.ClientDependency;
import org.ogrehus.jcwm.impl.FunCwm;
import org.ogrehus.jcwm.impl.objectmodel.core.FunName;


/**
 * A model element is an element that is an abstraction drawn from the system being modeled.
 * <p>
 * In the meta-model a ModelElement is a named entity in a Model. It is the base for all modeling meta-classes in the CWM. All other modeling
 * meta-classes are either direct or indirect subclasses of ModelElement.
 * </p>
 * <p>
 * TODO: implement Constraints may be by Constructor or methods
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-3-3]</b> - Tags associated with a model element (directly via a property list or indirectly via a stereotype) must not clash with any
 * meta attributes associated with the model element.</li>
 * <li><b>[C-3-4]</b> - A model element must have at most one tagged value with a given tag name.</li>
 * <li><b>[C-3-5]</b> - A stereotype cannot extend itself.<li>
 * </ul>
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <NAMESPACE> The type of the Namespace that contains this ModelElement.
 * @param <PACKAGE> The type of the importer of this ModelElement.
 * @param <DEPENDENCY> The type of ClientDependency of this ModelElement.
 * @param <CONSTRAINT> The type of the Constraint under which this ModelElement is.
 * 
 */
public abstract class GFunModelElement<
	  NAMESPACE  extends GNamespace<?, ?, ?, ?, ?>
	, PACKAGE    extends GCwmPackage<?, ?, ?, ?, ?, ?, ?>
	, DEPENDENCY extends GDependency<?, ?, ?, ?, ?>
	, CONSTRAINT extends GConstraint<?, ?, ?, ?, ?>
> implements
	GModelElement<NAMESPACE, PACKAGE, DEPENDENCY, CONSTRAINT>
{


	/**
	 * Creates a new instance of GFunModelElement by specific parameters.
	 * 
	 * @param name An identifier for the GFunModelElement within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the GFunModelElement within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code> or <code>visibility</code> is <code>null</code>.
	 *
	 */	
	protected GFunModelElement( String name, Visibility visibility ) {
		try {
			this.setName( name ); // throws NullPointerException
		} catch ( UnsupportedOperationException unsupported ) {
			if ( this instanceof FunName ) {
				// do nothin' cause FunName ha a constant for return value!
			} else {
				throw unsupported;
			}
		}
		this.setVisibility( visibility ); // throws NullPointerException
		this.constraints        = new HashSet<CONSTRAINT>();
		this.packages           = new HashSet<PACKAGE>();
		this.dependencies       = new HashSet<DEPENDENCY>();
		this.taggedValues       = new HashSet<TaggedValue>();  
		this.descriptions       = new HashSet<Description>();
		this.responsibleParties = new HashSet<ResponsibleParty>();
		this.documents          = new HashSet<Document>();
		
		try {
			this.cwmFactory = Cwm.create( FunCwm.class.getName() );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// can't occur, cause otherwise this class would be missing too! 
		}
	}



	public Cwm getCwmFactory() {
		return this.cwmFactory;
	}



	public Name getName() {
		if ( this.name == null ) {
			return FunName.DEFAULT;
		}

		return this.name;
	}



	/**
	 * There are two forms of names: simple names and qualified names:
	 * <ul>
	 * <li>A <b>simple name</b> is a single identifier. </li>
	 * <li>A <b>qualified name</b> consists of a name, a "." token, and an identifier.</li>
	 * <ul>
	 * 
	 * @return simple name
	 * 
	 */
	public String getSimpleName() {
		return getName().toString();
	}



	/**
	 * There are two forms of names: simple names and qualified names:
	 * <ul>
	 * <li>A <b>simple name</b> is a single identifier. </li>
	 * <li>A <b>qualified name</b> consists of a name, a "." token, and an identifier.</li>
	 * <ul>
	 * 
	 * @return qualifiedName
	 * 
	 */
	public String getQualifiedName() {
		return getQualifiedName( ".", "" );
	}



	/**
	 * There are two forms of name: simple and qualified name:
	 * <ul>
	 * <li>A <b>simple name</b> is a single identifier of the element within its namespace.</li>
	 * <li>A <b>qualified name</b> consists of a name, a "." token, and an identifier building the hierarchical step of the element within its
	 * namespaces.</li>
	 * </ul>
	 * 
	 * The use of the following parameters depend on the related System, for example JDBC determines the <code>separatorSymbol</code> by requesting:
	 * separatorSymbol</code>
	 * 
	 * @param separator This symbol separates each token that represents a hierarchical step in namespaces.
	 * 
	 * @param surrounding This symbol delimiter will embed each token that represents a hierarchical step in namespaces. Use <code>null</code> or a
	 * zero-string if no surroundingSymbol should be used.
	 * 
	 * @return qualifiedName
	 * 
	 */
	public String getQualifiedName( String separator, String surrounding ) {
		if ( surrounding == null ) {
			surrounding = "";
		}

		String myName = getSimpleName();
		if ( myName.length() > 0 ) {
			myName = surrounding + myName + surrounding; // surround only if name is avail
		}

		if ( this.namespace != null ) {
			String qualifiedName = this.namespace.getQualifiedName( separator, surrounding ); // recursive name construction
			if ( qualifiedName.length() > 0 ) {
				return qualifiedName + separator + myName;
			}
		}

		return myName;
	}



	public int compareTo( ModelElement other ) {
		if ( other == null ) {
			return 1;
		}
		
		int compareTo = this.getSimpleName().compareTo( other.getSimpleName() );
		if ( compareTo == 0 ) {
			return this.getQualifiedName().compareTo( other.getQualifiedName() );
		}

		return compareTo;
	}



	public Name setName( String name ) {
		Name old = this.name;
		this.name = new FunName( name );
		return old;
	}



	public Visibility getVisibility() {
		return this.visibility;
	}



	public Visibility setVisibility(Visibility visibility) {
		if ( visibility == null ) {
			throw new NullPointerException( "Parameter: visibility must not be null." );
		}		
		Visibility old = this.visibility;
		this.visibility = visibility;

		return old;
	}



	public Stereotype getStereotype() {
		return this.stereotype;
	}



	public Stereotype setStereotype(Stereotype stereotype) {
		Stereotype old = this.stereotype;
		this.stereotype = stereotype;

		return old;
	}



	public Collection<TaggedValue> getTaggedValues() {
		return this.taggedValues;
	}



//====================================================================================================================================================
// OwnedElement<NAMESPACE>.java
//====================================================================================================================================================



	protected NAMESPACE setNamespaceGeneric( NAMESPACE namespace ) {
		if ( namespace != null && namespace.equals( this.namespace ) ) {
			return namespace; // no changes, cause its the same namespace
		}

		NAMESPACE  old = this.namespace; // return value 
		if ( old != null  ) {
			old.removeOwnedElement( this );
		}

		this.namespace = namespace;
		if ( this.namespace != null ) {
			if ( !this.namespace.getOwnedElements().contains( this ) ) {
				invokeByReflection( this.namespace, "addOwnedElement", this ); // organize the bidirectional reference 
			}
		}

		return old;
	}	



	public NAMESPACE getNamespace() {
		return this.namespace;
	}



	public Boolean removeNamespace() {
		if ( this.namespace == null ) {
			return Boolean.TRUE;
		}

		NAMESPACE old = this.namespace;
		this.namespace = null;

		return old.removeOwnedElement( this );
	}



	public Set<Namespace<?>> getAllSurroundingNamespaces() {
		Set<Namespace<?>> all = new HashSet<Namespace<?>>();
		all.add( this.namespace );

		for ( Namespace<?> surrounding : ((OwnedElement<?>)this.namespace).getAllSurroundingNamespaces() ) {
			all.add( surrounding );
		}

		return all;
	}



	protected static final Method findMethod( Object invoker, String name, Object parameter ) {
		try {
			Method[] methods = invoker.getClass().getMethods( );
			Set<Method> byName  = new HashSet<Method>();
			for ( Method method : methods ) {
				if (   name.equals( method.getName() ) && method.getParameterTypes().length == 1 ) {
					byName.add( method );
				}
			}

			for ( Method checkParam : byName ) {
				for ( Class<?> paramType : checkParam.getParameterTypes() ) {
					if ( paramType.isInstance( parameter ) ) {
						return checkParam; // suitable found
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return null; // found nothing
	}



	protected static final <INVOKE, PARAM> Boolean invokeByReflection( INVOKE invoker, String methodName, PARAM param ) {
		Method call = findMethod( invoker, methodName, param );
		if ( call == null ) {
			throw new IllegalArgumentException( "The class " + invoker.getClass().getName()
					+ " does not provide a suitable Method named: \"" + methodName + "\" to set the object type : "
					+ param.getClass().getName() );
		}
		
		try {
			call.invoke( invoker.getClass().cast( invoker ), param );
			return Boolean.TRUE;
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return Boolean.FALSE;
	}



//====================================================================================================================================================
// Importer<PACKAGE>.java
//====================================================================================================================================================



	protected Boolean addImporterGeneric( PACKAGE importer ) {
		if ( importer != null && this.packages.add( importer) ) {
			invokeByReflection( importer, "addImportedElement", this ); // bi-directional reference
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeImporter( ImportedElement<?> importer ) {
		if ( importer == null ) {
			return Boolean.TRUE;
		}
		importer.removeImportedElement( this );

		return this.packages.remove( importer );
	}



	public Set<PACKAGE> getImporters() {
		if ( this.packages == null ) {
			this.packages = new HashSet<PACKAGE>();
		}

		return this.packages;
	}



//====================================================================================================================================================
// ClientDependency<DEPENDENCY>.java
//====================================================================================================================================================



	protected Boolean addDependencyGeneric( DEPENDENCY dependency ) {
		if ( dependency != null && this.dependencies.add( dependency ) ) {
			invokeByReflection( dependency, "addClient", this ); // bi-directional reference
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeDependency( Clientable<?> dependency ) {
		if ( dependency == null ) {
			return Boolean.TRUE;
		}
		
		if ( this.dependencies.remove( dependency ) ) {
			dependency.removeClient( this );
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Set<DEPENDENCY> getDependencies() {
		return this.dependencies;
	}



	public Set<ModelElement> getSuppliers() {
		Set<ModelElement> suppliers = new HashSet<ModelElement>();
		for ( DEPENDENCY dependency :  this.dependencies ) {
			suppliers.addAll( dependency.getSuppliers() );
		}
		return suppliers;
	}



	public Set<ModelElement> getAllSuppliers() {
		Set<ModelElement> allSuppliers = new HashSet<ModelElement>();
		Set<ModelElement> mySuppliers  = getSuppliers();
		allSuppliers.addAll( mySuppliers );
		for ( ModelElement aSupplier : mySuppliers ) {
			new HashSet<ModelElement>();
			if ( aSupplier instanceof ClientDependency<?> ) {
				allSuppliers.addAll( ((ClientDependency<?>)aSupplier).getAllSuppliers() );
			}
		}

		return allSuppliers;
	}



//====================================================================================================================================================
// UnderConstraint<CONSTRAINT>.java
//====================================================================================================================================================



	protected Boolean addConstraintGeneric( CONSTRAINT constraint ) {
		if ( constraint != null && this.constraints.add( constraint ) ) {
			invokeByReflection( constraint, "addConstraintedElement", this ); // bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeConstraint( ConstraintedElement<?> constraint ) {
		if ( constraint == null ) {
			return Boolean.TRUE;
		}

		if ( this.constraints.remove( constraint ) ) {
			constraint.removeConstraintedElement( this ); // remove bi-directional reference

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Set<CONSTRAINT> getConstraints() {
		return this.constraints;
	}



	public String setRemarks( String remarks ) {
		String old = this.remarks;
		this.remarks = remarks;
		return old;
	}



	public String getRemarks() {
		return this.remarks;
	}



//====================================================================================================================================================
// BusinessInformationen
//====================================================================================================================================================



	public Boolean addDocument( Document document ) {
		if ( document == null ) {
			return Boolean.FALSE;
		}

		if ( this.documents.add( document ) ) {
			document.addModelElement( this );
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeDocument( Document document ) {
		if ( document == null ) {
			return Boolean.TRUE;
		}

		if ( this.documents.remove( document ) ) {
			document.removeModelElement( this );

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Collection<Document> getDocuments() {
		return this.documents;	
	}



	public Boolean addDescription( Description description ) {
		if ( description == null ) {
			return Boolean.FALSE;
		}

		if ( this.descriptions.add( description ) ) {
			description.addModelElement( this );

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeDescription( Description description ) {
		if ( description == null ) {
			return Boolean.TRUE;
		}

		if ( this.descriptions.remove( description ) ) {
			description.removeModelElement( this );
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Collection<Description> getDescriptions() {
		return this.descriptions;
	}



	public Boolean addResponsibleParty( ResponsibleParty responsibleParty ) {
		if ( responsibleParty == null ) {
			return Boolean.FALSE;
		}

		if ( this.responsibleParties.add( responsibleParty ) ) {
			responsibleParty.addModelElement( this );
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeResponsibleParty( ResponsibleParty responsibleParty ) {
		if ( responsibleParty == null ) {
			return Boolean.TRUE;
		}

		if ( this.responsibleParties.remove( responsibleParty ) ) {
			responsibleParty.removeModelElement( this );

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Collection<ResponsibleParty> getResponsibleParties() {
		return this.responsibleParties;	
	}



//====================================================================================================================================================
// Object capabilities
//====================================================================================================================================================


	@Override
	public String toString() {
		StringBuffer out = new StringBuffer( "[GFunModelElement" );
		out.append( " | name=" + this.name );
		out.append( " | visibility=" + this.visibility );
		if ( this.namespace != null ) {
			out.append( " | namespace=" + this.namespace.getQualifiedName() );
			out.append( "<" + this.namespace.getClass().getSimpleName() + ">" );
		}

		if ( this.stereotype != null ) {
			out.append( " | stereotype=" + this.stereotype.getQualifiedName() );
		}

		if ( this.remarks != null ) {
			out.append( " | remarks=" + this.remarks );
		}

		out.append( collectionToString( "packages", this.packages, true ) );
		out.append( collectionToString( "dependencies", this.dependencies, true ) );
		out.append( collectionToString( "constraints", this.constraints, true ) );

		if ( !this.taggedValues.isEmpty() ) {
			out.append( " | dependencies(" + this.taggedValues.size() + ")={" );
			boolean comma = false;
			for ( TaggedValue taggedValue : this.taggedValues ) {
				if ( comma ) {
					out.append( " , " );
				} else {
					comma = true;
				}
				out.append( taggedValue.getTag() );
			}
			out.append( "}" );
		}
		
		out.append( collectionToString( "descriptions"      , this.descriptions ) );        
		out.append( collectionToString( "documents"         , this.documents ) );        
		out.append( collectionToString( "responsibleParties", this.responsibleParties ) );
		out.append( "]" );
		
		return out.toString();
	}



	static final protected String collectionToString( String name, Collection<? extends ModelElement> collection ) {
		return collectionToString(name, collection, false );
	}



	static final protected String collectionToString( String name, Collection<? extends ModelElement> collection, boolean typed ) {
		StringBuffer out = new StringBuffer();
		if ( collection != null && !collection.isEmpty() ) {
			out.append( " | " );
			out.append( name );
			out.append( "(" );
			out.append( collection.size() );
			out.append( ")={" );
			boolean comma = false;
			for ( ModelElement element : collection ) {
				if ( element != null ) {
					if ( comma ) {
						out.append( " , " );
					} else {
						comma = true;
					}
					out.append( element.getQualifiedName() );
					if ( typed ) {
						out.append( "<" + element.getClass().getSimpleName() + ">" );
					}
				}
			}
			out.append( "}" );
		}

		return out.toString();
	}




//====================================================================================================================================================
//	Properties
//====================================================================================================================================================



	/**
	 * An identifier for the ModelElement within its containing Namespace.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: Name</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected Name name = null;



	/**
	 * Specifies extent of the visibility of the ModelElement within its owning Namespace.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>type</i>			: Visibility</li>
	 * <li><i>multiplicity</i> 	: exactly one</li>
	 * </ul>
	 *  
	 */
	protected Visibility visibility = null;	



	/**
	 * References the set of Package instances that import the ModelElement.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>			: generic PACKAGE</li>
	 * <li><i>defined by</i>	: ImportedElements::importer</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: Package::importedElement</li>
	 * </ul>
	 * 
	 */
	protected Set<PACKAGE> packages = null;



	/**
	 * Designates the Namespace that contains the ModelElement. Every Model Element except a root element must belong to exactly one Namespace or
	 * else be a composite part of another ModelElement (which is a kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: generic NAMESPACE</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Namespace::ownedElement</li>
	 * </ul>
	 * 
	 */
	protected NAMESPACE namespace = null;



	/**
	 * Inverse of client. Designates a set of Dependency in which the ModelElement is a client.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>  
	 * <ul>
	 * <li><i>class</i>			: generic DEPENDENCY</li>
	 * <li><i>defined by</i>	: DependencyClient::clientDependency</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: Dependency::client</li>
	 * </ul>
	 * 
	 */
	protected Set<DEPENDENCY> dependencies = null;



	/**
	 * A set of Constraints affecting the element. A constraint that must be satisfied by the model element. A model element may have a set of
	 * constraints. The constraint is to be evaluated when the system is stable; that is, not in the middle of an atomic operation.
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>			: generic CONSTRAINT</li>
	 * <li><i>defined by</i>	: ElementConstraint</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: Constraint::constrainedElement</li>
	 * </ul>
	 * 
	 */
	protected Set<CONSTRAINT> constraints = null;



	/**
	 * References the set of TaggedValue instances that extend the ModelElement. (comes new in Version 1.1)
	 * 
	 * <ul>
	 * <li><i>class</i>			: TaggedValue</li>
	 * <li><i>defined by</i>	: TaggedElement::taggedValue</li>
	 * <li><i>multiplicity</i>	: zero or more</li>
	 * <li><i>inverse</i>		: TaggedValue::modelElement</li>
	 * </ul>
	 * 
	 */
	protected Set<TaggedValue> taggedValues = null;



	/**
	 * Inverse of <code>extendetElement</code>.
	 * <p> 
	 * Identifies the Stereotype instance that further defines the semantics of the ModelElement.
	 * </p>
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Stereotype</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 * 
	 */
	protected Stereotype stereotype = null;



	protected String remarks = null;
	
	/**
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Description</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 *  
	 */
	protected Set<Description> descriptions = null;



	/**
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: Document</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 *  
	 */
	protected Set<Document> documents = null;



	/**
	 * <p>
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>: ResponsibleParty</li>
	 * <li><i>multiplicity</i>: zero or one</li>
	 * </ul>
	 *  
	 */
	protected Set<ResponsibleParty> responsibleParties = null;



	protected Cwm cwmFactory = null;
}