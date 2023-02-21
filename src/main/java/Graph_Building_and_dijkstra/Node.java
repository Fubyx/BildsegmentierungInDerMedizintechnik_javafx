package Graph_Building_and_dijkstra;

import java.util.ArrayList;

public class Node {
    int x, y, weight;
    int dijkstraValue;
    Node dijkstraParent = null;

    Node(int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.dijkstraValue = Integer.MAX_VALUE;
    }
}

