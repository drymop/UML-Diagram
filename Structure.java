/**
 * Class represent for Structure
 */

abstract public class Structure {
    // Name of Structure
    protected String name = null;

    /**
     * Default constructor
     */
    public Structure() {}

    /**
     * check a name is valid or not
     * @param name the name
     * @return true or false
     */
    public boolean checkValid(String name){
        if (name == null){
            System.out.println(name+" is an invalid name");
            return false;
        }
        int x;
        for (int i=0; i<name.length(); i++){
            x = (int) name.charAt(i);
            if ((x>=48 && x<=57) || (x>=65 && x<=90) || (x>=97 && x<=122)) continue;
            System.out.println(name+" is an invalid name");
            return false;
        }
        x = (int) name.charAt(0);
        if (x>=48 && x<=57) {
            System.out.println(name+" is an invalid name");
            return false;
        }
        return true;
    }

    /**
     * set name for Structure
     * @param name of Structure
     * @return setting-name ability
     */
    public boolean setName(String name) {
        if (!checkValid(name)) return false;
        this.name = name;
        return true;
    }

    /**
     * return name of Structure
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }
}
