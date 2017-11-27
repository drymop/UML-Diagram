package com.umldiagram.model;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.umldiagram.util.StringUtils;

public class Method extends Structure {
	public static class Builder
			extends Structure.Builder<Method, Builder> {

		@Override
		Method createStructure() { return new Method(); }

		@Override
		Builder getThis() { return this; }
		
		public Builder withReturnType(String type) {
			structure.returnType = type;
			return builder;
		}
		
		public Builder withVisibility(Visibility v) {
			structure.visibility = v;
			return builder;
		}
		
		public Builder isAbstract(boolean flag) {
			structure.isAbstract = flag;
			return builder;
		}
		
		public Builder isStatic(boolean flag) {
			structure.isStatic = flag;
			return builder;
		}
		
		public Builder withArgument(Argument arg) {
			structure.arguments.add(arg);
			return builder;
		}
		
		public Builder withArguments(List<Argument> args) {
			structure.arguments = new LinkedHashSet<>(args);
			return builder;
		}
		
		@Override
		public void validate() throws StructureException {
			super.validate();
			if (structure.isAbstract && structure.isStatic)
				throw new IllegalModifierException();
			if (!StringUtils.isJavaReturnType(structure.returnType))
				throw new IllegalNameException(structure.returnType);
			for (Argument arg : structure.arguments) {
				if (!StringUtils.isJavaType(arg.type))
					throw new IllegalNameException(arg.type);
				if (!StringUtils.isJavaName(arg.name))
					throw new IllegalNameException(arg.name);
			}
		}
	}
	
	/**
	 * Immutable object represent a method argument
	 */
	public static class Argument {
		private String type;
		
		private String name;
		
		public Argument(String name, String type) {
			this.name = name;
			this.type = type;
		}

		public String getType() { return type; }

		public String getName() { return name; }
		
		/**
		 * 2 argument is equal when they have same name
		 * in method context
		 */
		@Override
		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass())
				return false;
			return ((Argument)o).name.equals(this.name);
		}
		
		@Override
		public int hashCode() {
			return name.hashCode();
		}
	}
	
	private Visibility visibility = Visibility.PACKAGE;
	private boolean isAbstract;
	private boolean isStatic;
	private String returnType = "void";
	private LinkedHashSet<Argument> arguments = new LinkedHashSet<>();
	
	private Method() {}
	
	public Visibility getVisibility() { return visibility; }
	
	public boolean isAbstract() { return isAbstract; }

	public boolean isStatic() { return isStatic; }
	
	public String getReturnType() {
		return returnType;
	}
	
	public Set<Argument> getArguments() {
		return Collections.unmodifiableSet(arguments);
	}
	
	/**
	 * 2 methods are equal when they have same signature (name + argument type)
	 * in class context
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Method))
			return false;
		Method m = (Method)o;
		if (m.arguments.size() != this.arguments.size()) 
			return false;
		Iterator<Argument> it = m.arguments.iterator();
		for (Argument arg : this.arguments) {
			if (!it.next().type.equals(arg.type))
				return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = name.hashCode();
		for (Argument arg : arguments)
			hash = hash * 31 + arg.type.hashCode();
		return hash;
	}
}