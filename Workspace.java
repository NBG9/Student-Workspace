/**
 * Represents a workspace
 * A workspace may have multiple topics
 * @version 3.1.0
 * @since 1-4-2022
 * @author Turki Al-Doussari
 * @author R. Nazarbek
 */


import java.io.Serializable;
import java.util.ArrayList;

public class Workspace implements Serializable {

    private NaryTree workspace = null;
    private String name;
    private String passcode;
    private Topic root = null;
    private Topic current = root;
    private ArrayList<Topic> topics;
    private ArrayList<Integer> topicIDs;

    /**
     * Constructor
     * @param name name of workspace
     * @param passcode password
     */
    public Workspace(String name, String passcode) {
        this.workspace = new NaryTree();
        this.topics = new ArrayList<>();
        this.topicIDs = new ArrayList<>();
        this.name = name;
        this.passcode = passcode;
    }

    public Workspace(String name, String passcode, Topic root) {
        this.topics = new ArrayList<>();
        this.topicIDs = new ArrayList<>();
        this.name = name;
        this.passcode = passcode;
        this.workspace = new NaryTree(root);
        this.root = root;
        this.current = root;
        this.topics.add(root);
    }

    /**
     * Changes the name of workspace
     * @param name new name
     */
    public void changeName(String name) {
        this.name = name;
        if (this.name == name) {
            System.out.println("Name changed successfully.");
        } else {
            System.out.println("Error, try again.");
        }
    }

    /**
     * Changes passcode of workspace
     * @param oldPasscode old password
     * @param newPasscode new password
     */
    public void changePasscode(String oldPasscode, String newPasscode) {
        if (oldPasscode.equals(passcode)) {
            this.passcode = newPasscode;
            System.out.println("Passcode reset successfully.");
        } else {
            System.out.println("Error, passcode does not match.");
        }
    }

    /**
     * Add a single topic to the tree
     * @param topic
     */
    public void addTopic(Topic topic) {

        //Start if workspace has not been instantiated yet

        topics.add(topic);
        topicIDs.add(topic.getID());
        if (topics.size() == 0) {
            this.root = topic;
        }

    }

    public void addTopics(ArrayList<Topic> topics) {

        //Start if workspace has not been instantiated yet
        if (workspace == null) {
            this.root = topics.get(0);
            workspace = new NaryTree(root);
            this.current = this.root;
        }

        //this.topics = new ArrayList<>();

        for (Topic t : topics) {
            this.topics.addAll(topics);
            topicIDs.add(t.getID());
        }

        if (this.root == null) {

            this.root = topics.get(0);
            workspace = new NaryTree(root);
            this.current = this.root;

        } else if (this.current == this.root) {

            this.root.addChildren(topics);
            this.current = topics.get(0);

        } else {

            this.current.addChildren(topics);
            this.current = topics.get(0);

        }
        current.displayLinks(0);
    }

    /**
     * Open a topic based on name
     * @param target
     */
    public void openTopic(Topic target) {
        for (Topic t : topics) {
            if (t.getName() == target.getName()) {
                System.out.println(t.getName() + ":\n");
                t.displayLinks();
            }
        }
    }

    /**
     * Open a topic based on ID
     * @param targetID
     */
    public void openTopic(int targetID) {
        for (Topic t : topics) {
            if (t.getID() == targetID) {
                System.out.println(t.getName() + ":\n");
                t.displayLinks();
            }
        }
    }

    public void viewTopics() {
        System.out.printf("Topics in workspace %s:\n", name);
        for (Topic t : topics) {
            System.out.println("Name: " + t.getName() + " ID: " + t.getID());
        }
    }

    /**
     * Browse the children of a topic
     * @param topic
     */
    public void browse(Topic topic, int space) {
        int indent = 4;

        if (topic == null) {
            System.out.println("Null");
        }


        //System.out.println(topic.getName());

        for (Topic t : topic.getChildren()) {

            for (int i = 1; i <= space; i++) {
                System.out.print(" ");
            }

            System.out.println(t.getName());


            System.out.println(t.displayLinks(indent));

            browse(t, space + 4);
        }

    }

    public void addPath(String path, String topicname) {
        if (topics.contains(topicname)) {
            for (Topic t : topics) {
                if (t.getName().equals(topicname)) {
                    t.addLink(new Link(path));
                    System.out.println("Link added.");
                }
            }
        } else {
            System.out.println("Error, no such topic found.");
        }
    }

    /*
    Adds a link to topic through topic ID
     */
    public void addPath(String path, int topicID) {
        if (topicIDs.contains(topicID)) {
            for (Topic t : topics) {
                if (t.getID() == topicID) {
                    t.addLink(new Link(path));
                    System.out.println("Path added successfully.");
                }
            }
        } else {
            System.out.println("No such topic found.");
        }
    }

    public void setRoot(Topic root) {
        this.root = root;
    }


//Getters


    /**
     * @return name of workspace
     */
    public String getName() {
        return name;
    }

    /**
     * @return list of topics
     */
    public ArrayList<Topic> getTopics() {
        return topics;
    }

    /**
     * @return the root
     */
    public Topic getRoot() {
        return root;
    }

    /**
     * @param ID id
     * @return topic corresponiding to ID
     */
    public Topic getTopic(int ID) {
        Topic returner = null;
        for (Topic t : topics) {
            if (t.getID() == ID) {
                returner = t;
            }
        }
        return returner;
    }
}
