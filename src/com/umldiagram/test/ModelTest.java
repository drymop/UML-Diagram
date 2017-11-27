package com.umldiagram.test;

import com.umldiagram.model.Class;
import com.umldiagram.model.Field;
import com.umldiagram.model.Interface;
import com.umldiagram.model.Method;
import com.umldiagram.model.Project;
import com.umldiagram.model.Structure;
import com.umldiagram.model.StructureException;

public class ModelTest {
	public static void main(String[] args) throws StructureException {
		Project p = Project.getInstance();
		Class.Builder cb = p.getClassBuilder();
		Interface.Builder ib = p.getInterfaceBuilder();
		Field.Builder fb = p.getFieldBuilder();
		Method.Builder mb = p.getMethodBuilder();
		
		Method m = mb.withName("amethod")
					 .isAbstract(true)
					 .withArgument(new Method.Argument("x", "int"))
					 .withReturnType("String")
					 .withVisibility(Structure.Visibility.PUBLIC)
					 .build();
		Interface i = ib.withName("AInterface")
						.withMethod(m)
						.build();
		p.add(i);
		
		Class c1 = cb.withName("AClass1")
					 .isAbstract(true)
					 .implement(i)
					 .build();
		Class c2 = cb.withName("AClass2")
					 .extend(c1)
					 .build();
		
		p.add(c1);
		p.add(c2);
	}
}
