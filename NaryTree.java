/**
 * Represents an n-ary tree.
 * This is the structure of the workspace.
 * @version 3.1.0
 * @since 1-4-2022
 * @author Turki Al-Doussari
 * @author R. Nazarbek
 */

import java.io.Serializable;

public class NaryTree implements Serializable {

    Topic root;

    /**
     * Constructor.
     * @param root of the tree.
     */
    public NaryTree(Topic root) {
        this.root = root;
    }

    /**
     * Constructor.
     */
    public NaryTree() {
        root = null;
    }

}
