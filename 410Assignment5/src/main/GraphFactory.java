package main;
/**
 * Factory used for creating graphs
 */
public final class GraphFactory {

    public static Graph create() {
        return new MyGraph();
    }

}