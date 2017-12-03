
import java.util.*;

/**
 * class represent for Class
 */

public class Class extends Interface{
    // the Class is Abstract or not
    private boolean Abstract = false;
    // parents of the Class
    private Class parents = null;

    /**
     * Constructor of Class
     */
    public Class(){
        super();
    }

    /**
     * set parents for this Class
     * @param parents the parents
     */
    public void setParents(Class parents) {
        this.parents = parents;
    }

    /**
     * get parents of this Class
     * @return the parents
     */
    public Class getParents() {
        return parents;
    }

    /**
     * check if the Class is abstract
     * @return abstract or not
     */
    public boolean isAbstract() {
        return Abstract;
    }

    /**
     * set the Class is abstract or not
     * @param Abstract abstract or not
     */
    public void setAbstract(boolean Abstract) {
        this.Abstract = Abstract;
    }

    @Override
    public String toString() {
        String toString = "Class: "+getName();
        toString += (isAbstract())? " is abstract\n" : "\n";
        //if (parents!=null) toString += ("  - parents: "+ parents.getName()+"\n");
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
}
