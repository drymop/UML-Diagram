
import java.util.*;

/**
 * class represent for Interface
 */

public class Interface extends Structure{
    // List of Interfaces that the Interface implements
    protected List<Interface> interfaces;
    // List Properties of the Interface
    protected Map<String , Property> properties;
    // List Methods of the Interface
    protected Map <String , Method> methods;

    /**
     * Constructor of Interface
     */
    public Interface(){
        // create new List of Interfaces, Properties, Methods
        interfaces = new ArrayList<>();
        properties = new HashMap<>();
        methods = new HashMap<>();
    }

    /**
     * add a new Property to this
     * @param propertyName name of the Property
     * @param propertyType type of Property
     * @return adding ability
     */
    public boolean addProperty(String propertyName, String propertyType){
        Property _property = new Property();
        if (!_property.setName(propertyName)) return false;
        if (!_property.setType(propertyType)) return false;

        if (properties.containsKey(propertyName)) {
            System.out.println("Property '"+propertyName+ "' has already been declared in "+getName());
            return false;
        }
        properties.put(_property.getName(),_property);
        System.out.println("Added Property "+propertyName+ " to "+ getName());
        return true;
    }

    /**
     * delete a Property from this
     * @param propertyName name of the Property
     * @return deleting ability
     */
    public boolean deleteProperty(String propertyName){
        if (properties.containsKey(propertyName)) {
            System.out.println("Romoved property "+properties.remove(propertyName).getName()+ " from "+getName());
            return true;
        }
        System.out.println("property '" + propertyName + "' has not already been declared in "+getName());
        return false;
    }

    /**
     * add a new Method to this
     * @param methodName name of the Method
     * @param methodType type of the Method
     * @param isStatic the Method is static or not
     * @param isAbstract the Method is abstract or not
     * @return adding ability
     */
    public boolean addMethod(String methodName, String methodType, boolean isStatic, boolean isAbstract){
        Method _method = new Method();
        if (!_method.setName(methodName)) return false;
        if (!_method.setType(methodType)) return false;
        _method.setParents(this);
        _method.setStatic(isStatic);
        if (!_method.setAbstract(isAbstract)) return false;

        if (methods.containsKey(methodName)) {
            System.out.println("Method '"+methodName+ "' has already been declared in " +getName());
            return false;
        }

        methods.put(methodName,_method);
        System.out.println("Added Method "+methodName+ " to "+getName());
        return true;
    }

    /**
     * delete a Method from this
     * @param methodName name of the Method
     * @return deleting ability
     */
    public boolean deleteMethod(String methodName){
        if (methods.containsKey(methodName)) {
            System.out.println("Romoved method "+methods.remove(methodName).getName()+ " from "+getName());
            return true;
        }
        System.out.println("method '" + methodName + "' has not already been declared in "+getName());
        return false;
    }

    /**
     * add an Argument to a Method of this
     * @param methodName name of the Method
     * @param argumentName name of the Argument
     * @param argumentType type of the Argument
     * @return adding ability
     */
    public boolean addArgumentToMethod(String methodName, String argumentName, String argumentType){
        if (!methods.containsKey(methodName)) return false;

        Method method = methods.get(methodName);
        return method.addArgument(argumentName, argumentType);
    }

    /**
     * delete an Argument of a Method of this
     * @param methodName name of the Method
     * @param argumentName name of the Argument
     * @return deleting ability
     */
    public boolean deleteArgumentOfMethod(String methodName, String argumentName){
        if (!methods.containsKey(methodName)) return false;

        Method method = methods.get(methodName);
        return method.deleteArgument(argumentName);
    }

    /**
     * set this implements another Interface
     * @param _interface the parents Interface
     */
    public void addInterface(Interface _interface){
        interfaces.add(_interface);
    }

    /**
     * get all Interfaces this implements
     * @return a list of Interfaces
     */
    public Interface[] getInterfaces(){
        return interfaces.toArray(new Interface[0]);
    }

    @Override
    public String toString() {
        String toString = "Interface: "+getName();
        if (interfaces.size()!=0) {
            toString += ("  - interface: ");
            for (int i=0; i<interfaces.size(); i++)
                toString += (interfaces.get(i).getName() + "  ");
            toString += ("\n");
        }

        if (properties.size()!=0) {
            toString += ("  - property: \n");
            Set set = properties.entrySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                toString += ("     + "+properties.get(me.getKey()) + '\n');
            }
        }

        if (methods.size()!=0) {
            toString += ("  - method: \n");
            Set set = methods.entrySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                toString += ("     + "+methods.get(me.getKey()) + '\n');
            }
        }
        return toString;
    }

    public Method[] getMethods() {
        return methods.values().toArray(new Method[0]);
    }

    public Property[] getProperties() {
        return properties.values().toArray(new Property[0]);
    }
}