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
package org.ogrehus.jcwm.impl.objectmodel.core;

import org.ogrehus.jcwm.api.objectmodel.behavioral.Interface;
import org.ogrehus.jcwm.api.objectmodel.core.Attribute;
import org.ogrehus.jcwm.api.objectmodel.core.BooleanExpression;
import org.ogrehus.jcwm.api.objectmodel.core.CorePackage;
import org.ogrehus.jcwm.api.objectmodel.core.Constraint;
import org.ogrehus.jcwm.api.objectmodel.core.CwmAny;
import org.ogrehus.jcwm.api.objectmodel.core.CwmBoolean;
import org.ogrehus.jcwm.api.objectmodel.core.CwmClass;
import org.ogrehus.jcwm.api.objectmodel.core.CwmFloat;
import org.ogrehus.jcwm.api.objectmodel.core.CwmInteger;
import org.ogrehus.jcwm.api.objectmodel.core.CwmPackage;
import org.ogrehus.jcwm.api.objectmodel.core.CwmString;
import org.ogrehus.jcwm.api.objectmodel.core.CwmTime;
import org.ogrehus.jcwm.api.objectmodel.core.CwmUnlimitedInteger;
import org.ogrehus.jcwm.api.objectmodel.core.DataType;
import org.ogrehus.jcwm.api.objectmodel.core.Dependency;
import org.ogrehus.jcwm.api.objectmodel.core.Stereotype;
import org.ogrehus.jcwm.api.objectmodel.core.Subsystem;
import org.ogrehus.jcwm.api.objectmodel.core.TaggedValue;
import org.ogrehus.jcwm.api.objectmodel.core.Multiplicity;
import org.ogrehus.jcwm.api.objectmodel.core.Model;
import org.ogrehus.jcwm.api.objectmodel.core.Name;
import org.ogrehus.jcwm.api.objectmodel.core.Expression;
import org.ogrehus.jcwm.api.objectmodel.core.ProcedureExpression;
import org.ogrehus.jcwm.api.objectmodel.core.util.Visibility;
import org.ogrehus.jcwm.api.objectmodel.core.util.Changeable;
import org.ogrehus.jcwm.api.objectmodel.core.util.generic.GModelElement;


public class FunCorePackage
extends
	CorePackage 
{


	public Attribute createAttribute( String name, Visibility visibility, Changeable changeability, DataType type ) {
		return new FunAttribute( name, visibility, changeability, type );
	}



	public Attribute createAttribute( String name, Visibility visibility, Changeable changeability, CwmClass type ) {
		return new FunAttribute( name, visibility, changeability, type );
	}



	public Attribute createAttribute( String name, Visibility visibility, Changeable changeability, Interface type ) {
		return new FunAttribute( name, visibility, changeability, type );
	}



	public BooleanExpression createBooleanExpression( String body ) {
		return new FunBooleanExpression( body );
	}



	public CwmClass createClass( String name, Visibility visibility, Boolean notInstantiable ) {
		return new FunCwmClass( name, visibility, notInstantiable );
	}



	public Constraint createConstraint( String name, Visibility visibility, BooleanExpression body ) {
		return new FunConstraint( name, visibility, body );
	}



	public CwmAny createAny() {
		return new FunCwmAny();
	}



	public CwmBoolean createBoolean() {
		return new FunCwmBoolean();
	}



	@Override
	public CwmFloat createFloat() {
		return new FunCwmFloat();
	}



	@Override
	public CwmInteger createInteger() {
		return new FunCwmInteger();
	}



	@Override
	public CwmString createString() {
		return new FunCwmString();
	}



	@Override
	public CwmTime createTime() {
		return new FunCwmTime();
	}



	@Override
	public CwmUnlimitedInteger createUnlimitedInteger() {
		return new FunCwmUnlimitedInteger();
	}    



	@Override
	public DataType createDataType( String name, Visibility visibility, Boolean notInstantiable ) {
		return new FunDataType(name, visibility, notInstantiable );
	}



	@Override
	public Dependency createDependency(
		  String name
		, Visibility visibility
		, GModelElement< ?, ?, ?, ?> client
		, GModelElement< ?, ?, ?, ?> supplier
	) {
		return new FunDependency( name, visibility, client, supplier );
	}



	@Override
	public Expression createExpression( String body ) {
		return new FunExpression( body );
	}



	@Override
	public Model createModel( String name, Visibility visibility ) {
		return new FunModel( name, visibility );
	}



	@Override
	public Multiplicity createMultiplicity(  Integer lower, Integer upper ) {
		return new FunMultiplicity( lower, upper );
	}



	@Override
	public CwmPackage createPackage( String name, Visibility visibility ) {
		return new FunCwmPackage( name, visibility );
	}



	@Override
	public ProcedureExpression createProcedureExpression( String body ) {
		return new FunProcedureExpression( body );
	}



	@Override
	public Stereotype createStereotype( String name, Visibility visibility, Name baseClass ) {
		return new FunStereotype( name, visibility, baseClass );
	}



	@Override
	public Stereotype createStereotype( String name, Visibility visibility, String baseClass ) {
		return new FunStereotype( name, visibility, new FunName( baseClass ) );
	}



	@Override
	public Subsystem createSubsystem( String name, Visibility visibility, Boolean notInstantiable ) {
		return new FunSubsystem( name, visibility, notInstantiable );
	}



	@Override
	public TaggedValue createTaggedValue( String tag, String value ) {
		return new FunTaggedValue( tag, value );
	}
}