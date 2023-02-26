package Graph_Building_and_dijkstra;

import java.util.ArrayList;

public class Node {
    public int x, y;
    public int weight;
    protected int dijkstraValue;
    protected Node dijkstraParent = null;

    public Node(int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.dijkstraValue = Integer.MAX_VALUE;
    }
}

