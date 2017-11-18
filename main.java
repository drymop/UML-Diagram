import java.util.Scanner;

/**
 * the main class
 * 
 * @author dgbttn & Duck
 */

public class main {
    // InputStream
    static Scanner sc = new Scanner(System.in);
    // main structure management
    static StructureManagement structureManagement; 

    /**
     * add a new class 
     * @param className of new class
     */
    public static void addClass(String className){
        structureManagement.addClass(className);
    }

    /**
     * add a new interface
     * @param interfaceName of new interface
     */
    public static void addInterface(String interfaceName){
        structureManagement.addInterface(interfaceName);
    }

    /**
     * delete  interface that named 'name'
     * @param name of interface
     */
    public static void deleteInterface(String name){
        structureManagement.deleteInterface(name);
    }

    /**
     * delete class 'name'
     * @param name of class
     */
    public static void deleteClass(String name){
        structureManagement.deleteClass(name);
    }

    /**
     * add a Property to a Class
     * @param propertyName name of the Property
     * @param propertyType type of the Property
     * @param className name of the Class
     */
    public static void addPropertyToClass(String propertyName, String propertyType, String className) {
        structureManagement.addPropertyToClass(propertyName, propertyType, className);
    }

    public static void main(String[] args){

        // create structure management
        structureManagement = new StructureManagement();

        // Command of User
        String command;
        System.out.println("WHAT DO YOU WANT TO DO?");
        /**
        while (true){
            // Read next command
            command= sc.nextLine();

            // Stop-program command
            if (command.equals("stop")) {
                System.out.println("Good bye!");
                break;
            }

            // Add-something command
            if (command.substring(0,3).equals("add")) {
                // Add a new class
                if ((command.length()>=11 ) && command.substring(4,9).equals("class"))
                    addClass(command.substring(10,command.length()));
                // Add a new interface
                if ((command.length()>=15 ) && command.substring(4,13).equals("interface"))
                    addInterface(command.substring(14,command.length()));
                continue;
            }

            // Delete-something command
            if (command.substring(0,6).equals("delete")) {
                if ((command.length()>=14) && command.substring(7,12).equals("class"))
                    deleteClass(command.substring(13,command.length()));
                if ((command.length()>=18) && command.substring(7,16).equals("interface"))
                    deleteInterface(command.substring(17,command.length()));
                continue;
            }

            // Show-everything command
            if (command.equals("show all")){
                structureManagement.showAll();
                continue;
            }
        } */

        structureManagement.addClass("c1");
        structureManagement.addClass("c1");
        structureManagement.addClass("c2");
        structureManagement.addInterface("i1");
        structureManagement.addMethodToInterface("me","int","i2");
        structureManagement.addMethodToClass("me","int","c2");
        structureManagement.showAll();

    }

}
