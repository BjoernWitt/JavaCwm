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
package org.ogrehus.jcwm.impl.foundation.softwaredeployment;

import java.util.HashSet;
import java.util.Set;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.Component;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.DeployedSoftwareSystem;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareSystem;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.CwmManager;
import org.ogrehus.jcwm.api.foundation.typemapping.TypeSystem;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Model;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GFeature;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunSubsystem;


/**
 * A FunSoftwareSystem represents a specific release of a software product. It consists of a set of software Components. 
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 */
public class FunSoftwareSystem
extends
	GFunSubsystem<
		  CwmManager
		, Model
		, Model
		, Dependency
		, Constraint
		, Component
		, Component
		, GFeature<?,?,?,?,?>
	>
implements
	SoftwareSystem
{


	/**
	 * Creates a new instance of FunSoftwareSystem by specific parameters.
	 * 
	 * @param name An identifier for the FunSoftwareSystem within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the FunSoftwareSystem within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param notInstantiable <code>true</code> if this FunSoftwareSystem is abstract and can not be instantiated, <code>false</code> otherwise. Must
	 * not be <code>null</code>.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code> or <code>notInstantiable</code>
	 * is <code>null</code>.
	 * 
	 */
	public FunSoftwareSystem( String name, Visibility visibility, Boolean notInstantiable ) {
		super( name, visibility, notInstantiable ); // may throw NullPointerException
		this.typespaces = new HashSet<TypeSystem>();
		this.deployedSoftwareSystems = new HashSet<DeployedSoftwareSystem>();
	}



//====================================================================================================================================================
// ModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the ModelElement. Every Model Element except a root element must belong to exactly one Namespace or else
	 * be a composite part of another ModelElement (which is a kind of virtual namespace).
	 * <p> 
	 * The pathname of Namespace or ModelElement names starting from the root package provides a unique designation for every ModelElement.
	 * </p>
	 * <p>
	 * The association attribute visibility specifies the visibility of the element outside its namespace (see ElementOwnership).
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: Namespace</li>
	 * <li><i>defined by</i>	: ElementOwnership::namespace</li>
	 * <li><i>multiplicity</i>	: zero or one</li>
	 * <li><i>inverse</i>		: Namespace::ownedElement</li>
	 * </ul>
	 * 
	 * @param model The new namespace that contains this ModelElement.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */
	public Model setNamespace( Model model ) {
		return super.setNamespaceGeneric( model );
	}



	/**
	 * Adds an importer (Package) to this ModelElement, that will contain this ModelElement as imported.
	 * 
	 * @param model The specific package, that will contain this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addImporter( Model model ) {
		return super.addImporterGeneric( model );
	}



	public Boolean addOwnedElement( Component component ) {
		return super.addOwnedElementGeneric( component );
	}



	/**
	 * Adds a dependency to this ModelElement.
	 * 
	 * @param dependency A Dependency in witch this ModelELement is a client.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addDependency( Dependency dependency ) {
		return super.addDependencyGeneric( dependency );
	}



	/**
	 * Adds a constraint to this element.
	 * 
	 * @param constraint A Constrain that must be satisfied by this ModelElement.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addConstraint( Constraint constraint ) {
		return super.addConstraintGeneric( constraint );
	}



//====================================================================================================================================================
// SoftwareSystem capabilities
//====================================================================================================================================================



	public Set<DeployedSoftwareSystem> getDeployedSoftwareSystems() {
		return this.deployedSoftwareSystems;
	}



	public Boolean addDeployedSoftwareSystem( DeployedSoftwareSystem deployedSystem ) {
		if ( deployedSystem != null && this.deployedSoftwareSystems.add( deployedSystem ) ) {
			if ( !(this.equals(  deployedSystem.getSoftwareSystem()) ) ) {
				deployedSystem.setSoftwareSystem( this );
			}

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	public Boolean removeDeployedSoftwareSystem( DeployedSoftwareSystem deployedSystem ) {
		if ( deployedSystem != null && this.deployedSoftwareSystems.remove( deployedSystem ) ) {
			deployedSystem.removeSoftwareSystem();
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}



	/**
	 * Identifies the type of the software product. 
	 * 
	 * <p>
	 * One of the following predefined values should be used if appropriate:
	 * </p>
	 * <ul>
	 * <li><b>OS</b> - Operating System product</li>
	 * <lI><b>DBMS</b> - Database Management System product</li>
	 * <li><b>MDDB</b> - Multidimensional Database product</li>
	 * <li><b>FileSystem</b> - Any</li>
	 * <li><b>ODBC</b> - Open Database Connectivity</li>
	 * <li><b>JDBC</b> - Java Database Connectivity (JDBC)</li>
	 * <li>or <b>Application</b> - may be any</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 * @return The types of the software product. Can't be <code>null</code>, but an empty Set.
	 * 
	 */
	public String getType() {
		return this.type;
	}



	public String setType( String type ) {
		String old = this.type;
		this.type = type;

		return old;
	}



	/**
	 * This is used in conjunction with the type attribute to provide additional information about the type of the software product.
	 * <p>
	 * For some of the predefined types, suggested subtype values are listed below:
	 * </p>
	 * <ul>
	 * <li>For an Operating System product (type OS):
	 *   <ul>
	 *   <li>AIX</li>
	 *   <li>Linux</li>
	 *   <li>MVS</li>
	 *   <li>NT</li>
	 *   <li>Solaris</il>
	 *   <li>SunOS</li>
	 *   <li>VMS</li>
	 *   <li>or Windows</li>
	 *   </ul>
	 * </li>
	 * <li>For a Database Management System product (type DBMS):
	 *   <ul>
	 *   <li>DB2</li>
	 *   <li>DMS II</li>
	 *   <li>IMS</li>
	 *   <li>Informix</li>
	 *   <li>Oracle</li>
	 *   <li>SQLServer</li>
	 *   <li>Sybase</li>
	 * </ul>
	 * </li>
	 * <li>For a Multidimensional Database product (type MDDB):
	 *   <ul>
	 *   <li>Essbase</li>
	 *   <li>Express</li>
	 *   </ul>
	 * </li>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 *
	 * @return Additional information about the type of the software product. Can't be <code>null</code>, but an empty public Set.
	 * 
	 */
	public String getSubtype() {
		return this.subtype;
	}



	public String setSubType( String subType ) {
		String old = this.subtype;
		this.subtype = subType;

		return old;
	}



	/**
	 * The supplier of the software product.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 * 
	 * @return The supplier of the software product.
	 * 
	 */
	public String getSupplier() {
		return this.supplier;
	}



	public String setSupplier( String softwareSupplier ) {
		String old = this.supplier;
		this.supplier = softwareSupplier;

		return old;
	}



	/**
	 * The version identification for the software product.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 * @return The version identification for the software product. Can be <code>null</code>, if none exists.
	 * 
	 */
	public String getVersion() {
		return this.version;
	}



	public String setVersion( String softwareVersion ) {
		String old = this.version;
		this.version = softwareVersion;

		return old;
	}



	/**
	 * Identifies the TypeSystem(s) containing the datatypes supported by the FunSoftwareSystem.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : TypeSystem</li>
	 * <li><i>defined by</i>    : SystemTypespace::typespace</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 * @return the TypeSystem(s) containing the datatypes supported by the FunSoftwareSystem. Can't be <code>null</code>, but an empty public Set.
	 * 
	 */
	public Set<TypeSystem> getTypespaces() {
		return this.typespaces;
	}



	public TypeSystem getTypespace( String typeSystemName, String typeSystemVersion ) {
		for ( TypeSystem system : this.typespaces ) {
			if ( system.getSimpleName().equals( typeSystemName ) ) {
				if ( typeSystemVersion == null ) {
					return system; // ignore version return any by name
				} else {
					if ( typeSystemVersion.equals( system.getVersion() ) ) {
						return system;
					}
				}
			}
		}

		return null;
	}



	public Boolean addTypespace( TypeSystem typeSystem ) {
		if ( typeSystem != null && this.typespaces.add( typeSystem ) ) {
			if ( !typeSystem.getSupportingSystems().contains( this ) ) {
				typeSystem.addSupportingSystem( this );
			}
			
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



	public Boolean removeTypeSystem( TypeSystem typeSystem ) {
		if ( typeSystem != null && this.typespaces.remove( typeSystem ) ) {
			typeSystem.removeSupportingSystem( this );
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Identifies the type of the software product. 
	 * 
	 * <p>
	 * One of the following predefined values should be used if appropriate:
	 * </p>
	 * <ul>
	 * <li><b>OS</b> - Operating System product</li>
	 * <lI><b>DBMS</b> - Database Management System product</li>
	 * <li><b>MDDB</b> - Multidimensional Database product</li>
	 * <li><b>FileSystem</b> - Any</li>
	 * <li><b>ODBC</b> - Open Database Connectivity</li>
	 * <li><b>JDBC</b> - Java Database Connectivity (JDBC)</li>
	 * <li>or <b>Application</b> - may be any</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 */
	protected String type = null;



	/**
	 * This is used in conjunction with the type attribute to provide additional information about the type of the software product.
	 * <p>
	 * For some of the predefined types, suggested subtype values are listed below:
	 * </p>
	 * <ul>
	 * <li>For an Operating System product (type OS):
	 *   <ul>
	 *   <li>AIX</li>
	 *   <li>Linux</li>
	 *   <li>MVS</li>
	 *   <li>NT</li>
	 *   <li>Solaris</il>
	 *   <li>SunOS</li>
	 *   <li>VMS</li>
	 *   <li>or Windows</li>
	 *   </ul>
	 * </li>
	 * <li>For a Database Management System product (type DBMS):
	 *   <ul>
	 *   <li>DB2</li>
	 *   <li>DMS II</li>
	 *   <li>IMS</li>
	 *   <li>Informix</li>
	 *   <li>Oracle</li>
	 *   <li>SQLServer</li>
	 *   <li>Sybase</li>
	 *   </ul>
	 * </li>
	 * <li>For a Multidimensional Database product (type MDDB):
	 *   <ul>
	 *   <li>Essbase</li>
	 *   <li>Express</li>
	 *   </ul>
	 * </li>
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 */
	protected String subtype = null;



	/**
	 * The supplier of the software product.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 */
	protected String supplier = null;



	/**
	 * The version identification for the software product.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : String</li>
	 * <li><i>multiplicity</i>  : zero or one</li>
	 * </ul>
	 * 
	 */
	protected String version = null;



	/**
	 * Identifies the TypeSystem(s) containing the datatypes supported by the SoftwareSystem.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>         : TypeSystem</li>
	 * <li><i>defined by</i>    : SystemTypespace::typespace</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 */
	protected Set<TypeSystem> typespaces = null;



	protected Set<DeployedSoftwareSystem> deployedSoftwareSystems = null;
}