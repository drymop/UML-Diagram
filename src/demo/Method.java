import java.util.*;

public class Method extends Structure{
    // the Method is abstract or not
    private boolean Abstract = false;
    // the Method is static or not
    private boolean Static = false;
    // type of Method
    private String type;
    // List of Method's arguments
    private Map<String,Argument> arguments;
    // Parents of the Method
    private Interface parents = null;

    /**
     * Constructor of Method
     */
    public Method(){
        arguments = new HashMap<>();
    }

    /**
     * set type for Method
     * @param typeName of Method
     * @return setting-type ability
     */
    public boolean setType(String typeName) {
        if (!checkValid(typeName)) return false;
        this.type = typeName;
        return true;
    }

    /**
     * return type of Method
     * @return type of Method
     */
    public String getType() {
        return type;
    }

    /**
     * set the Method is static or not
     * @param Static static or not
     */
    public void setStatic(boolean Static) {
        this.Static = Static;
    }

    /**
     * check if the Method is static
     * @return static or not
     */
    public boolean isStatic() {
        return Static;
    }

    /**
     * check if the Method is abstract
     * @return abstract or not
     */
    public boolean isAbstract() {
        return Abstract;
    }

    /**
     * set the Method is abstract or not
     * @param Abstract abstract or not
     */
    public boolean setAbstract(boolean Abstract) {
        if (Static && Abstract) return false;

        if (parents instanceof Class){
            Class _class = (Class) parents;
            if (_class.isAbstract()) {
                this.Abstract = Abstract;
                return true;
            }
            if (Abstract) return false;
            this.Abstract = Abstract;
            return true;
        }

        this.Abstract = Abstract;
        return Abstract;
    }

    /**
     * set parents for this Method
     * @param parents the parents
     */
    public void setParents(Interface parents) {
        this.parents = parents;
    }

//    /**
//     * get parents of this Method
//     * @return the parents
//     */
//    public Interface getParents() {
//        return parents;
//    }

    /**
     * add an Argument to this Method
     * @param argumentName name of the Argument
     * @param argumentType type of the Argument
     * @return adding ability
     */
    public boolean addArgument(String argumentName, String argumentType){
        Argument _argument = new Argument();
        if (!_argument.setName(argumentName)) return false;
        if (!_argument.setType(argumentType)) return false;

        if (arguments.containsKey(argumentName)) {
            System.out.println("Argument '"+argumentName+ "' has already been declared in method "+getName());
            return false;
        }
        arguments.put(_argument.getName(),_argument);
        System.out.println("Added argument "+argumentName+ " to method "+getName());
        return true;
    }

    /**
     * delete an Argument of this Method
     * @param argumentName name of the Argument
     * @return deleting ability
     */
    public boolean deleteArgument(String argumentName){
        if (arguments.containsKey(argumentName)) {
            System.out.println("Romoved argument "+arguments.remove(argumentName).getName()+ " from method "+getName());
            return true;
        }
        System.out.println("argument '" + argumentName + "' has not already been declared in method "+getName());
        return false;
    }

    @Override
    public String toString() {
        String toString = type+ " ";
        toString += (isStatic())? "static " : "";
        toString += (isAbstract())? "abstract " : "";
        toString += getName()+'(';
        if (arguments.size()!=0){
            Set set = arguments.entrySet();
            Iterator i = set.iterator();
            Map.Entry me;
            if (i.hasNext()){
                me = (Map.Entry) i.next();
                toString += (arguments.get(me.getKey()).getType());
            }
            while (i.hasNext()) {
                me = (Map.Entry) i.next();
                toString += (","+arguments.get(me.getKey()).getType());
            }
        }
        toString += ')';

        return toString;
    }
}
