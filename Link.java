import java.io.Serializable;

public class Link implements Serializable {

    private String path;
    private int ID = 1;
    private static int nextID = 1;

    public Link(String path) {
        this.path = path;
        ID = nextID;
        nextID++;
    }

    public String getPath() {
        return path;
    }

    public int getID() {
        return ID;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
