
public class Property extends Structure {
    // type of Property
    private String type;

    /**
     * set type for Property
     * @param typeName of Property
     * @return setting-type ability
     */
    public boolean setType(String typeName) {
        if (!checkValid(typeName)) return false;
        this.type = typeName;
        return true;
    }

    /**
     * return type of Property
     * @return type of Property
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type+" "+getName();
    }
}
