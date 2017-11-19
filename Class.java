import java.util.HashSet;
import java.util.List;

/**
 * Class representation of a class
 */
public class Class extends Structure {
	private boolean isAbstract;
	
	private Class superClass;
	private HashSet<Class> subClasses;
	private HashSet<Interface> interfaces;
	
	private HashSet<Field> fields;
	private HashSet<Method> methods;
}
