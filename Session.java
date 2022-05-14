/**
 * Represents a Session
 * A session stores the last saved tree
 * @version 3.1.0
 * @since 1-4-2022
 * @author Turki Al-Doussari
 * @author R. Nazarbek
 */


import java.io.Serializable;
import java.io.*;


public class Session implements Serializable {

    Workspace backup;
    //private static final String path = "\\Users\\Uni\\Desktop\\LJMU\\Object-Oriented-Systems-Development\\Submission\\Workspace-V3\\New";
    private static final String path = "\\Users\\Uni\\Desktop\\LJMU\\Object-Oriented-Systems-Development\\Submission\\Workspace-V3\\TestData";

    /**
     * Save the workspace in path.
     * @param backupWS the workspace to be saved.
     */
    public void backup(Workspace backupWS) {

        backup = backupWS;
        writeToFile();

    }

    /**
     * Write the root on an object file.
     */
    public void writeToFile() {

        try {
            FileOutputStream outputFile = new FileOutputStream(path);
            ObjectOutputStream outputObject = new ObjectOutputStream(outputFile);
            outputObject.writeObject(backup);
            outputObject.close();
            System.out.println("File has been written successfully.\n");

        } catch (Exception e) {
            //System.out.println("qwerty");
            //e.printStackTrace();
        }
    }

    /**
     * @return the last saved workspace
     */
    public Workspace restore() throws FileNotFoundException {

        Object retrievedWS;

        try {

            FileInputStream inputFile = new FileInputStream(path);
            ObjectInputStream inputObject = new ObjectInputStream(inputFile);

            retrievedWS = inputObject.readObject();

            inputObject.close();

        } catch (Exception e) {
            throw new FileNotFoundException("File not found.");
        }
        return (Workspace) retrievedWS;
    }
}
