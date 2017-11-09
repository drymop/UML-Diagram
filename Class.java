package FS;

import java.util.List;

/**
 * Created by OS on 09-Nov-17.
 */

public class Class {
    private String name;
    private boolean isAbstract;
    private Class parents;
    private List<Class> children;
    private List<Interface> interfaces;
    private List<Field> fields;
    private List<Method> methods;

    public Class(){}

    public Class(String name, boolean isAbstract){
        this.name = name;
        this.isAbstract = isAbstract;
    }

    public void show(){

    }
}
