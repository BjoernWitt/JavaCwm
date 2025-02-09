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
package org.ogrehus.jcwm.impl.foundation.softwaredeployment.util.generic;

import org.ogrehus.jcwm.api.foundation.softwaredeployment.SoftwareSystem;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataManager;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GDataProvider;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GPackageUsage;
import org.ogrehus.jcwm.api.foundation.softwaredeployment.util.generic.GProviderConnection;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;

import org.ogrehus.jcwm.impl.objectmodel.core.util.generic.GFunModelElement;


/**
 * A ProviderConnection represents a connection that allows a DataProvider acting as a client to access data from a specific DataManager.
 *  
 * <p>
 * For example a ProviderConnection could represent a connection from an ODBC or JDBC client to a DBMS.
 * </p>
 * 
 * <p>
 * <b>Constraints</b>
 * </p>
 * <ul>
 * <li><b>[C-8-2]</b> A ProviderConnection must not associate a DataProvider with itself.</li>
 * </ul>  
 * 
 * <p>
 * This implementation was guided by the <a href="http://www.omg.org/cgi-bin/doc?formal/03-03-02">CWM Specification V1.1</a>
 * by <a href="http://www.omg.org">OMG</a>
 * </p>
 * 
 * @author Bjoern Witt, ogrehus.org, copyright 2008
 * 
 * @param <MANAGER> Type of the corresponding DataManager.
 * @param <PROVIDER> Type of the provider, might be JDBC, ODBC, FileSystem any other suitable one.
 * @param <USAGE> Type of the packageUsage as the role of the a dependency of this ProviderConnecion.
 * 
 */
public abstract class GFunProviderConnection<
      MANAGER    extends GDataManager<?, ?>
    , PROVIDER   extends GDataProvider<?, ?>
    , USAGE      extends GPackageUsage<?>    
>extends
    GFunModelElement<SoftwareSystem, SoftwareSystem, USAGE, Constraint>
implements 
    GProviderConnection<MANAGER, PROVIDER, USAGE>
{


	/**
	 * Creates a new instance of ProviderConnection by specific parameters.
	 * 
	 * @param name An identifier for the ProviderConnection within its containing Namespace. Must not be <code>null</code>.
	 * 
	 * @param visibility Specifies extent of the visibility of the ProviderConnection within its owning Namespace. Must not be <code>null</code>.
	 * 
	 * @param isReadOnly Indicates whether the ProviderConnection only allows read access to the DataManager. Must not be <code>null</code>.
	 * 
	 * @param dataProvider Identifies the DataProvider that is the client of the ProviderConnection. Must not be <code>null</code>.
	 * 
	 * @param dataManager Identifies the DataManager that is accessed by the ProviderConnection.
	 * 
	 * @throws NullPointerException Is thrown if one of the parameter: <code>name</code>, <code>visibility</code>, <code>isReadOnly</code>,
	 * <code>dataProvider</code> or <code>dataManager</code> is <code>null</code>.
	 * 
	 */
	protected GFunProviderConnection(
		  String name
		, Visibility visibility
		, Boolean isReadOnly
		, PROVIDER dataProvider
		, MANAGER dataManager
	) {
		super( name, visibility );
		this.setReadOnly( isReadOnly );
		this.setDataProvider( dataProvider );
		this.setDataManager( dataManager );
	}



//====================================================================================================================================================
// GModelElement capabilities
//====================================================================================================================================================



	/**
	 * Designates the Namespace that contains the Component. Every Model Element except a root element must belong to exactly one Namespace or else be
	 * a composite part of another ModelElement (which is a kind of virtual namespace).
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
	 * @param softwareSystem The new namespace that contains this Component.
	 * 
	 * @return The namespace that contains this ModelElement before, or <code>null</code> none was assigned before.
	 * 
	 */	
	public SoftwareSystem setNamespace( SoftwareSystem softwareSystem ) {
		return super.setNamespaceGeneric( softwareSystem );
	}



	/**
	 * Adds an importer (Package) to this ProviderConnection, that will contain this ProviderConnection as imported.
	 * 
	 * @param softwareSystem The specific package, that will contain this ProviderConnection.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addImporter(SoftwareSystem softwareSystem) {
		return super.addImporterGeneric( softwareSystem );
	}



	/**
	 * Adds a constraint to this ProviderConnection.
	 * 
	 * @param constraint A Constrain that must be satisfied by this ProviderConnection.
	 * 
	 * @return <code>true</code> if this Collection changed as a result of the call, <code>false</code> otherwise.
	 * 
	 */
	public Boolean addConstraint(Constraint constraint) {
		return super.addConstraintGeneric( constraint );
	}



//====================================================================================================================================================
// ProviderConnection capabilities
//====================================================================================================================================================



	/**
	 * Indicates whether the ProviderConnection only allows read access to the DataManager.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Boolean</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if the ProviderConnection only allows read access to the DataManager,<code>false</code> otherwise. Can't
	 * be <code>null</code>.
	 * 
	 */
	public Boolean isReadOnly() {
		return this.isReadOnly;
	}



	/**
	 * Indicates whether the ProviderConnection only allows read access to the DataManager.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Boolean</li>
	 * <li><i>multiplicity</i>  : exactly one</li>
	 * </ul>
	 * 
	 * @param readOnly <code>true</code> if the ProviderConnection only allows read access to the DataManager, <code>false</code> otherwise. Must not
	 * be <code>null</code>.
	 * 
	 * @return Old value of the property <code>readOnly</code>.
	 * 
	 * @throws NullPointerException If the parameter <code>readOnly</code> is <code>null</code>.
	 * 
	 */
	public Boolean setReadOnly( Boolean readOnly ) {
		if ( readOnly == null ) {
			throw new NullPointerException( "The parameter: readOnly must not be null." );
		}
		Boolean old = this.isReadOnly;
		this.isReadOnly = readOnly;
		
		return old;
	}



	/**
	 * Identifies the DataProvider that is the client of the ProviderConnection.
	 *
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-8-2]</b> A ProviderConnection must not associate a DataProvider with itself.</li>
	 * </ul>
	 * 
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>			: DataProvider</li>
	 * <li><i>defined by</i>	: DataProviderConnections::dataProvider</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * <li><i>inverse</i>		: DataProvider::resourceConnection</li>
	 * </ul>
	 * 
	 * @return The DataProvider that is the client of the ProviderConnection.
	 * 
	 */
	public PROVIDER getDataProvider() {
		return this.dataProvider;
	}



	/**
	 * Removes the DataProvider that is no longer the client of the ProviderConnection.
	 *
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>class</i>			: DataProvider</li>
	 * <li><i>defined by</i>	: DataProviderConnections::dataProvider</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * <li><i>inverse</i>		: DataProvider::resourceConnection</li>
	 * </ul>
	 * 
	 * 
	 * @return <code>true</code> if the value for owner changed as a result of the call, <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException If the parameter <code>dataProvider</code> is <code>null</code>.
	 * 
	 */
	public Boolean removeDataProvider() {
		if ( this.dataProvider == null ) {
			return Boolean.TRUE;
		}
		PROVIDER old = this.dataProvider;
		this.dataProvider = null;
		if ( old != null && old.getResourceConnections().contains( this ) ) {
			// must request for existing reference first to avoid endless cyclic removal call!
			old.removeResourceConnection( this );
		}
		
		return Boolean.TRUE;
	}



	/**
	 * Identifies the DataManager that is accessed by the ProviderConnection.
	 * 
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>			: DataManager</li>
	 * <li><i>defined by</i>	: DataManagerConnections::dataManager</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 * @return The DataManager that is accessed by the ProviderConnection.
	 * 
	 */
	public MANAGER getDataManager() {
		return this.dataManager;
	}



	public Boolean removeDataManager() {
		if ( this.dataManager == null ) {
			return Boolean.TRUE;
		}
		this.dataManager.removeClientConnection( this );
		this.dataManager = null;

		return Boolean.TRUE;
	}



//====================================================================================================================================================
// Properties
//====================================================================================================================================================



	/**
	 * Indicates whether the ProviderConnection only allows read access to the DataManager.
	 * 
	 * <p>
	 * <b>Characteristics</b>:
	 * </p>
	 * <ul>
	 * <li><i>type</i>          : Boolean</li>
	 * <li><i>multiplicity</i>  : zero or more</li>
	 * </ul>
	 * 
	 */
	protected Boolean isReadOnly = null;



	/**
	 * Identifies the DataProvider that is the client of the ProviderConnection.
	 *
	 * <p>
	 * <b>Constraints</b>
	 * </p>
	 * <ul>
	 * <li><b>[C-8-2]</b> A ProviderConnection must not associate a DataProvider
	 * with itself.</li>
	 * </ul>
	 *  
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>			: DataProvider</li>
	 * <li><i>defined by</i>	: DataProviderConnections::dataProvider</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * <li><i>inverse</i>		: DataProvider::resourceConnection</li>
	 * </ul>
	 * 
	 */
	protected PROVIDER dataProvider = null;



	/**
	 * Identifies the DataManager that is accessed by the ProviderConnection.
	 * 
	 * <b>Characteristics</b>:
	 * </p> 
	 * <ul>
	 * <li><i>class</i>			: DataManager</li>
	 * <li><i>defined by</i>	: DataManagerConnections::dataManager</li>
	 * <li><i>multiplicity</i>	: exactly one</li>
	 * </ul>
	 * 
	 */
	protected MANAGER dataManager = null;
}