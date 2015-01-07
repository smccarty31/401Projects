package main;
/**
 * Factory used for creating nodes
 */
public final class NodeFactory {

    public static Node create(String name) {
        return new MyNode(name);
    }

}