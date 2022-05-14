/**
 * Represents a topic
 * A topic will have paths to files and folders
 * @version 3.1.0
 * @since 1-4-2022
 * @author Turki Al-Doussari
 * @author R. Nazarbek
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Topic implements Serializable {


    //For the tree
    private ArrayList<Topic> children;
    private Topic root = null;


    //For the topic itself
    private ArrayList<Link> links;
    private String name;
    private int ID = 1;
    private static int nextID = 1;

    //Constructor

    /**
     * Constructor
     * @param links, list of paths to files and folders
     * @param name, name of topic
     */
    public Topic(ArrayList<Link> links, String name) {
        children = new ArrayList<>();
        this.links = links;
        this.name = name;
        ID = nextID;
        nextID++;
    }

    /**
     * Constructor
     * @param name, name of topic
     */
    public Topic(String name) {
        children = new ArrayList<>();
        links = new ArrayList<>();
        this.name = name;
        ID = nextID;
        nextID++;
    }

    //Accessors

    /**
     * @return name of topic
     */
    public String getName () {
        return name;
    }

    /**
     * @return ID of topic
     */
    public int getID() {
        return ID;
    }

    //Methods for the "node"

    /**
     * add one child
     * @param child, the new child of topic.
     * @return the added child.
     */
    public Topic addChild(Topic child) {
        this.children.add(child);
        return child;
    }

    /**
     * Add multiple children
     * @param children, the new children of topic.
     * @return the last added child.
     */
    public Topic addChildren(ArrayList<Topic> children) {

        this.children.addAll(children);
        return (children.get(children.size() - 1));

    }

    //Methods for the "Topic"

    /**
     * Change the name of topic
     * @param name, name of topic
     */
    public void rename(String name) {
        this.name = name;
    }

    /**
     *
     * @param space, indentation.
     * @return a string of all links in topic.
     */
    public String displayLinks(int space) {

        String links = "";
        links += String.format("Links in %s topic:\n", name);

        for (Link link : this.links) {
            for (int i = 0; i <= space; i++) {
                links += " ";
            }
            links += link.getPath() + "\n";
        }
        return links;
    }

    /**
     * @return a string of all links in topic.
     */
    public void displayLinks() {

        System.out.printf("Topics in workspace %s:\n", name);
        for (Link l : links) {
            System.out.println(" ID: " + l.getID() + "\n" + l.getPath());
        }
    }

    public void removeLink(int ID) {
        links.removeIf(l -> l.getID() == ID);
    }

    /**
     * Add a path to a topic.
     * @param link, the path to a file or folder.
     */
    public void addLink(Link link) {
        this.links.add(link);
    }

    /**
     * Add multiple paths to a topic.
     * @param links, the paths to a file or folder.
     */
    public void addLinks(ArrayList<Link> links) {
        this.links.addAll(links);
    }

    /**
     * @return list of children of this topic.
     */
    public ArrayList<Topic> getChildren() {
        return this.children;
    }

    /**
     * @return list of paths of this topic.
     */
    public ArrayList<Link> getLinks() {
        return this.links;
    }



}
