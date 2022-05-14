/**
 * Represents the user interface
 * Interface interacts directly with user by displaying menus
 * @version 3.1.0
 * @since 1-4-2022
 * @author Turki Al-Doussari
 * @author R. Nazarbek
 */

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class UI implements Serializable {

    private Session session = new Session();
    private Workspace workspace;
    private final Scanner in = new Scanner(System.in);

    /**
     * Begin.
     */
    public void entry() {
        String entryMessage = "Student Workspace Manager\nCreated by Turki Al-Doussari and R. Nazarbek\n\n";
        String description = "This program allows a user to organize the files on their laptop into various 'Topics'.\n" +
                "This will optimize their browsing time, categorize their important files and declutter their devices.\n\n";

        System.out.println(entryMessage);
        System.out.println(description);
        showPrimaryMenu();
    }

    /**
     * primary menu is to further develop a workspace or create a new one.
     */
    public void showPrimaryMenu() {

        /*
        If there is an autosaved workspace in path
         */
        String primaryMenuV1 =
                          "----------------------------------------------" + "\n"
                        + "|                  Main Menu                 |" + "\n"
                        + "----------------------------------------------" + "\n"
                        + "| Build a new workspace            | press 1 |" + "\n"
                        + "| Quit                             | press 2 |" + "\n"
                        + "| Continue working on the workspace| press 3 |" + "\n"
                        +  "---------------------------------------------" + "\n";

        /*
        If there are no autosaved workspaces in path.
         */
        String primaryMenuV2 =
                          "----------------------------------------------" + "\n"
                        + "|                  Main Menu                 |" + "\n"
                        + "----------------------------------------------" + "\n"
                        + "| Build a new workspace            | press 1 |" + "\n"
                        + "| Quit                             | press 2 |" + "\n"
                        +  "---------------------------------------------" + "\n";

        boolean workspaceLocated = true;

        try {
            session.restore();
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            workspaceLocated = false;
        }

        if (!workspaceLocated) {
            System.out.println("It seems like there are no autosaved workspaces available.");
            System.out.println("Please create a new workspace.");
            System.out.print(primaryMenuV2);
        } else {
            System.out.println("Note: an autosaved workspace has been located in given path.");
            System.out.print(primaryMenuV1);
        }

        System.out.print("Your choice: ");
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                buildAWorkspace();
                showSecondaryMenu();
                break;
            case 2:
                System.out.println("Saving.");
                printProgressBar();
                session.backup(workspace);
                showPrimaryMenu();
                break;
            case 3:
                if (workspaceLocated) {
                    try {
                        workspace = session.restore();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    showSecondaryMenu();
                } else {
                    System.out.println("It seems like there are no autosaved workspaces available..");
                    System.out.println("Please create a new workspace.");
                    System.out.println(primaryMenuV2);
                }

                break;
            default:
                System.out.println("Invalid input.");
                if (workspaceLocated) {
                    System.out.println(primaryMenuV1);
                } else {
                    System.out.println(primaryMenuV2);
                }
                break;
        }
    }

    /**
     * Secondary menu is to create topics, files or folders
     */
    public void showSecondaryMenu() {

        String secondaryMenuV1 =
                "----------------------------------------------" + "\n"
                        + "|                 Create Menu                |" + "\n"
                        + "----------------------------------------------" + "\n"
                        + "| Create a topic                   | press 1 |" + "\n"
                        + "| Remove a topic                   | press 2 |" + "\n"
                        + "| Add a file or folder             | press 3 |" + "\n"
                        + "| Remove a file or folder          | press 4 |" + "\n"
                        + "| Show edit menu                   | press 5 |" + "\n"
                        + "| Show main menu                   | press 6 |" + "\n"
                        + "| Show view menu                   | press 7 |" + "\n"
                        + "---------------------------------------------" + "\n";

        String secondaryMenuV2 =
                "----------------------------------------------" + "\n"
                        + "|                 Create Menu                |" + "\n"
                        + "----------------------------------------------" + "\n"
                        + "| Create a topic                   | press 1 |" + "\n"
                        + "| Show edit menu                   | press 5 |" + "\n"
                        + "| Show main menu                   | press 6 |" + "\n"
                        + "| Show view menu                   | press 7 |" + "\n"
                        + "---------------------------------------------" + "\n";

        if (workspace.getTopics().size() == 0) {
            System.out.println("Since you have created a new workspace, you must create a topic" +
                    " before adding files or folders.");
            System.out.print(secondaryMenuV2);
        } else {
            System.out.print(secondaryMenuV1);
        }
        System.out.print("Your choice: ");
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                buildATopic();
                showSecondaryMenu();
                break;
            case 2:
            /*
            Lists all topics
            Asks for the desired topic
            Adds link to desired topic
             */
                removeTopic();
                showSecondaryMenu();
                break;
            case 3:
            /*
            Lists all topics
            Asks for the desired topic
            Adds link to desired topic
             */
                addPath();
                showSecondaryMenu();
                break;
            case 4:
                removePath();
                showSecondaryMenu();
            case 5:
                showTertiaryMenu();
                break;
            case 6:
                showPrimaryMenu();
                break;
            case 7:
                showQuaternaryMenu();
            default:
                System.out.println("Invalid input, try again.");
                showSecondaryMenu();
                break;
        }


}


    /**
     * Tertiary menu is to edit topics, files or folders
     */
    public void showTertiaryMenu() {

        String tertiaryMenu =
                          "----------------------------------------------" + "\n"
                        + "|                  Edit Menu                 |" + "\n"
                        + "----------------------------------------------" + "\n"
                        + "| Edit a topic                     | press 1 |" + "\n"
                        + "| Show main menu                   | press 2 |" + "\n"
                        + "| Show create menu                 | press 3 |" + "\n"
                        + "| Show view menu                   | press 4 |" + "\n"
                        +  "---------------------------------------------" + "\n";

        System.out.println(tertiaryMenu);
        System.out.print("Your choice: ");
        int choice = in.nextInt();

        switch (choice) {
            case 1:

                workspace.viewTopics();
                System.out.println("Enter the ID of the topic you want to edit:");
                int topicID = in.nextInt();
                for (Topic topic : workspace.getTopics()) {
                    if (topic.getID() == topicID) {
                        System.out.println("Enter the new name of the topic:");
                        String newTopicName = in.next();
                        topic.rename(newTopicName);
                        System.out.println("Renaming desired topic.");
                        printProgressBar();
                        System.out.println("Rename Success.");
                    }
                }
                break;
            case 2:
                showPrimaryMenu();
                break;
            case 3:
                showSecondaryMenu();
                break;
            case 4:
                showQuaternaryMenu();
            default:
                System.out.println("Invalid input, try again.\n");
                showTertiaryMenu();
                break;
        }

    }

    /**
     * Quaternary menu is to view the links in topics
     */
    public void showQuaternaryMenu() {

        String quaternaryMenu =
                          "----------------------------------------------" + "\n"
                        + "|                  View Menu                 |" + "\n"
                        + "----------------------------------------------" + "\n"
                        + "| View a topic                     | press 1 |" + "\n"
                        + "| View all topics                  | press 2 |" + "\n"
                        + "| Show main menu                   | press 3 |" + "\n"
                        + "| Show create menu                 | press 4 |" + "\n"
                        + "| Show edit menu                   | press 5 |" + "\n"
                        +  "---------------------------------------------" + "\n";

        System.out.println(quaternaryMenu);
        System.out.print("Your choice: ");
        int choice = in.nextInt();

        switch(choice) {
            case 1:
                workspace.viewTopics();
                System.out.println("Enter the ID of the topic you would like to view.");
                int topicID = in.nextInt();

                workspace.openTopic(topicID);
                System.out.println("Would you like to open the files in the topic? y/n");
                String answer = in.next();
                if (answer.equals("y")) {
                    for (Link link : workspace.getTopic(topicID).getLinks()) {
                        try {
                            open(link.getPath());
                        } catch (Exception e) {

                        }
                    }
                }
                showQuaternaryMenu();
                break;
            case 2:
                workspace.browse(workspace.getRoot(), 4);
                showQuaternaryMenu();
                break;
            case 3:
                showPrimaryMenu();
                break;
            case 4:
                showSecondaryMenu();
                break;
            case 5:
                showTertiaryMenu();
            default:
                System.out.println("Invalid input, try again.\n");
                showQuaternaryMenu();
                break;
        }


    }

    /**
     * Method to create a workspace.
     */
    public void buildAWorkspace() {
        System.out.print("Please enter the name of the workspace.\n");
        System.out.print("Workspace name: ");
        String wsName = in.next();
        System.out.println("\n'" + wsName + "'\n");

        System.out.print("Please create a passcode for entering the workspace.\n" +
                "The code must be between 4 and 8 characters\n");
        System.out.println("Note: this information is confidential, do not give it to anyone.\n");
        System.out.print("Code: ");
        String loginCode = in.next();

        workspace = new Workspace(wsName, loginCode);
        session = new Session();

        System.out.println("Creating your workspace.");
        printProgressBar();

        System.out.println("Workspace has been created successfully.\n");
        showSecondaryMenu();
    }

    /**
     * Method to create a topic
     */
    public void buildATopic() {
        System.out.print("Enter the name of the new topic:-\n");
        System.out.print("Topic name: ");
        String topicName = in.next();
        System.out.println("\n'" + topicName + "'\n");
        if (workspace.getTopics().size() == 0) {
            Topic t = new Topic(topicName);
            workspace.addTopic(t);
            workspace.setRoot(t);
        } else {
            System.out.println("Choose the parent of the topic:-\n");
            workspace.viewTopics();
            int idChoice = in.nextInt();
            Topic t = new Topic(topicName);
            workspace.addTopic(t);
            for (Topic topic : workspace.getTopics()) {
                if (topic.getID() == idChoice) {
                    topic.addChild(t);
                }
            }
        }

        System.out.println("Building topic.");
        printProgressBar();
    }

    /**
     * Method to add a path to a topic
     */
    public void addPath() {
        workspace.viewTopics();
        System.out.print("Enter the topic ID you want to place the path in: ");
        int topicID = in.nextInt();
        System.out.print("Enter the path: ");
        String path = in.next();
        workspace.addPath(path, topicID);
    }

    public void removePath() {
        int removableID = 0;
        workspace.viewTopics();
        System.out.print("Enter the topic ID you want to delete a path from: ");
        int topicID = in.nextInt();
        for (Topic t : workspace.getTopics()) {
            if (t.getID() == topicID) {
                t.displayLinks();
                System.out.print("Enter the link ID you want to delete a path from: ");
                int pathID = in.nextInt();
                for (Link l : t.getLinks()) {
                    if (l.getID() == pathID ) {
                        removableID = l.getID();
                    }
                }
                t.removeLink(removableID);
                System.out.println("Removed!");
            }
        }
    }

    /**
     * Removing a topic from the workspace.
     */
    public void removeTopic() {
        workspace.viewTopics();
        System.out.print("Enter the topic ID you want to delete: ");
        int topicID = in.nextInt();
        workspace.getTopics().removeIf(t -> topicID == t.getID());
    }

    /**
     * Method to print a progress bar (uses threads)
     */
    public void printProgressBar() {
        System.out.println(" ");
        //Progress bar (misc)
        String progress = "===";
        for (int i = 0; i < progress.length(); i++) {
            System.out.print(progress.charAt(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" ");
    }

    /**
     * Method to open files
     * @param path of the file
     * @throws IOException thrown if file does not exist
     */
    public void open(String path) throws IOException {
        try {
            File file = new File(path);
            if (!Desktop.isDesktopSupported()) {
                System.out.println("Not supported.");
                return;
            }
            Desktop dt = Desktop.getDesktop();
            if (file.exists()) {
                dt.open(file);
            }
        } catch (Exception e){

        }
    }
}
