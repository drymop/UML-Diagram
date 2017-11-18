import com.sun.prism.PixelFormat;
import javafx.scene.chart.PieChart;

import javax.lang.model.type.PrimitiveType;
import java.util.*;

/**
 * class manage structures
 */

public class StructureManagement {
    // List of classes
    private Map <String , Class> classes;
    // List of interfaces
    private Map <String , Interface> interfaces;

    /**
     * Constructor of StructureManagement
     */
    public StructureManagement() {
        classes = new HashMap <>();
        interfaces = new HashMap <>();
    }

    /**
     * Add a new Class to the Map
     * @param className name of the Class
     * @return adding ability
     */
    public boolean addClass(String className) {
        Class _class = new Class();
        if (!_class.setName(className)) return false;

        if (classes.containsKey(className) || interfaces.containsKey(className) ) {
            System.out.println("The name '"+className+ "' has already been declared");
            return false;
        }
        classes.put(_class.getName(),_class);
        System.out.println("Added class "+className);
        return true;
    }

    /**
     * Delete a Class
     * @param className name of the Class
     * @return deleting ability
     */
    public boolean deleteClass(String className){
        if (classes.containsKey(className)){
            System.out.println("Removed Class: "+classes.remove(className).getName());
            return true;
        }
        System.out.println("class "+className+" does not exist");
        return false;
    }

    /**
     * Add a new Interface to the Map
     * @param interfaceName name of the Interface
     * @return adding ability
     */
    public boolean addInterface(String interfaceName) {
        Interface _interface = new Interface();
        if (!_interface.setName(interfaceName)) return false;

        if (classes.containsKey(interfaceName) || interfaces.containsKey(interfaceName)) {
            System.out.println("The name '"+interfaceName+ "' has already been declared");
            return false;
        }
        interfaces.put(_interface.getName(),_interface);
        System.out.println("Added interface "+interfaceName);
        return true;
    }

    /**
     * Delete an Interface
     * @param interfaceName name of the Interface
     * @return deleting ability
     */
    public boolean deleteInterface(String interfaceName){
        if (interfaces.containsKey(interfaceName)){
            System.out.println("Removed Interface: "+interfaces.remove(interfaceName).getName());
            return true;
        }
        System.out.println("interface "+interfaceName+" does not exist");
        return false;
    }

    /**
     * add a new Property to a Class
     * @param propertyName name of the Property
     * @param propertyType type of the Property
     * @param className name of the Class
     * @return adding ability
     */
    public boolean addPropertyToClass(String propertyName, String propertyType, String className){
        if (!classes.containsKey(className)) {
            System.out.println("Class '" + className + "' has not already been declared");
            return false;
        }
        Class _class = classes.get(className);
        return _class.addProperty(propertyName, propertyType);
    }

    /**
     * add a new Property to an Interface
     * @param propertyName name of the Property
     * @param propertyType type of the Property
     * @param interfaceName name of the Interface
     * @return adding ability
     */
    public boolean addPropertyToInterface(String propertyName, String propertyType, String interfaceName){
        if (!interfaces.containsKey(interfaceName)) {
            System.out.println("Interface '" + interfaceName + "' has not already been declared");
            return false;
        }
        Interface _interface = interfaces.get(interfaceName);
        return _interface.addProperty(propertyName, propertyType);
    }

    /**
     * delete a Property of a Class
     * @param propertyName name of the Property
     * @param className name of the Class
     * @return deleting ability
     */
    public boolean deletePropertyOfClass(String propertyName, String className){
        if (!classes.containsKey(className)) {
            System.out.println("Class '" + className + "' has not already been declared");
            return false;
        }
        Class _class = classes.get(className);
        return _class.deleteProperty(propertyName);
    }

    /**
     * delete a Property of an Interface
     * @param propertyName name of the Property
     * @param interfaceName name of the Interface
     * @return deleting ability
     */
    public boolean deletePropertyOfInterface(String propertyName, String interfaceName){
        if (!interfaces.containsKey(interfaceName)) {
            System.out.println("Interface '" + interfaceName + "' has not already been declared");
            return false;
        }
        Interface _interface = interfaces.get(interfaceName);
        return _interface.deleteProperty(propertyName);
    }

    /**
     * add a new Method to a Class
     * @param methodName name of the Method
     * @param methodType type of the Method
     * @param className name of the Class
     * @return adding ability
     */
    public boolean addMethodToClass(String methodName, String methodType, String className){
        if (!classes.containsKey(className)) {
            System.out.println("Class '" + className + "' has not already been declared");
            return false;
        }
        Class _class = classes.get(className);
        return _class.addMethod(methodName, methodType);
    }

    /**
     * add a new Method to an Interface
     * @param methodName name of the Method
     * @param methodType type of the Method
     * @param interfaceName name of the Interface
     * @return adding ability
     */
    public boolean addMethodToInterface(String methodName, String methodType, String interfaceName){
        if (!interfaces.containsKey(interfaceName)) {
            System.out.println("interface '" + interfaceName + "' has not already been declared");
            return false;
        }
        Interface _interface = interfaces.get(interfaceName);
        return _interface.addMethod(methodName, methodType);
    }

    /**
     * delete a Method of a Class
     * @param methodName name of the Method
     * @param className name of the Class
     * @return deleting ability
     */
    public boolean deleteMethodOfClass(String methodName, String className){
        if (!classes.containsKey(className)) {
            System.out.println("Class '" + className + "' has not already been declared");
            return false;
        }
        Class _class = classes.get(className);
        return _class.deleteMethod(methodName);
    }

    /**
     * delete a Method of an Interface
     * @param methodName name of the Method
     * @param interfaceName name of the Interface
     * @return deleting ability
     */
    public boolean deleteMethodOfInterface(String methodName, String interfaceName){
        if (!interfaces.containsKey(interfaceName)) {
            System.out.println("Interface '" + interfaceName + "' has not already been declared");
            return false;
        }
        Interface _interface = interfaces.get(interfaceName);
        return _interface.deleteMethod(methodName);
    }

    /**
     * set a class to be parents of another class
     * @param childName name of child Class
     * @param parentsName name of parents Class
     * @return setting ability
     */
    public boolean setClassParentsFor(String childName, String parentsName){
        if (!classes.containsKey(parentsName)) {
            System.out.println("Class '" + parentsName + "' has not already been declared");
            return false;
        }
        if (!classes.containsKey(childName)) {
            System.out.println("Class '" + childName + "' has not already been declared");
            return false;
        }
        Class child = classes.get(childName);
        Class parents = classes.get(parentsName);
        child.setParents(parents);
        return true;
    }

    /**
     * add an Argument to a Method of a Class
     * @param className name of the Class
     * @param methodName name of the Method
     * @param argumentName name of the Argument
     * @param argumentType type of the Argument
     * @return adding ability
     */
    public boolean addArgumentToMethodOfClass(String className, String methodName, String argumentName, String argumentType){
        if (!classes.containsKey(className)) {
            System.out.println("Class '" + className + "' has not already been declared");
            return false;
        }
        Class _class = classes.get(className);
        return _class.addArgumentToMethod(methodName, argumentName, argumentType);
    }

    /**
     * add an Argument to a Method of an Interface
     * @param interfaceName name of the Interface
     * @param methodName name of the Method
     * @param argumentName name of the Argument
     * @param argumentType type of the Argument
     * @return adding ability
     */
    public boolean addArgumentToMethodOfInterface(String interfaceName, String methodName, String argumentName, String argumentType){
        if (!interfaces.containsKey(interfaceName)) {
            System.out.println("Interface '" + interfaceName + "' has not already been declared");
            return false;
        }
        Interface _interface = interfaces.get(interfaceName);
        return _interface.addArgumentToMethod(methodName, argumentName, argumentType);
    }

    /**
     * delete an Argument of a Method of a Class
     * @param className name of the Class
     * @param methodName name of the Method
     * @param argumentName name of the Argument
     * @return deleting ability
     */
    public boolean deleteArgumentOfMethodOfClass(String className, String methodName, String argumentName){
        if (!classes.containsKey(className)) {
            System.out.println("Class '" + className + "' has not already been declared");
            return false;
        }
        Class _class = classes.get(className);
        return _class.deleteArgumentOfMethod(methodName,argumentName);
    }

    /**
     * delete an Argument of a Method of an Interface
     * @param interfaceName name of the Interface
     * @param methodName name of the Method
     * @param argumentName name of the Argument
     * @return deleting ability
     */
    public boolean deleteArgumentOfMethodOfInterface(String interfaceName, String methodName, String argumentName){
        if (!interfaces.containsKey(interfaceName)) {
            System.out.println("Interface '" + interfaceName + "' has not already been declared");
            return false;
        }
        Interface _interface = interfaces.get(interfaceName);
        return _interface.deleteArgumentOfMethod(methodName,argumentName);
    }

    /**
     * set a Class implement an Interface
     * @param className name of the Class
     * @param interfaceName name of the Interface
     * @return setting ability
     */
    public boolean addInterfaceToClass(String className, String interfaceName) {
        if (!interfaces.containsKey(interfaceName)) {
            System.out.println("Interface '" + interfaceName + "' has not already been declared");
            return false;
        }
        if (!classes.containsKey(className)) {
            System.out.println("Class '" + className + "' has not already been declared");
            return false;
        }
        Class _class = classes.get(className);
        Interface _interface = interfaces.get(interfaceName);
        _class.addInterface(_interface);
        return true;
    }

    /**
     * set a Interface implement another Interface
     * @param interfaceName name of the child Interface
     * @param interfaceParentsName name of the parents Interface
     * @return setting ability
     */
    public boolean addInterfaceToInterface(String interfaceName, String interfaceParentsName) {
        if (!interfaces.containsKey(interfaceParentsName)) {
            System.out.println("Interface '" + interfaceParentsName + "' has not already been declared");
            return false;
        }
        if (!interfaces.containsKey(interfaceName)) {
            System.out.println("Interface '" + interfaceName + "' has not already been declared");
            return false;
        }
        Interface _interface = interfaces.get(interfaceName);
        Interface _parentsInterface = interfaces.get(interfaceParentsName);
        _interface.addInterface(_parentsInterface);
        return true;
    }

    /**
     * Show everything
     */
    public void showAll(){
        // Show all classes
        Set set = classes.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.println(classes.get(me.getKey()));
        }

        // Show all interfaces
        set = interfaces.entrySet();
        i = set.iterator();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.println(interfaces.get(me.getKey()));
        }
    }
}
