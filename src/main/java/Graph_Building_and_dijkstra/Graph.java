package Graph_Building_and_dijkstra;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    Node startNode, endNode;
    private final int rangeOfNeighbours = 5;

    Node [][] nodes;
    int columns, rows;
    ArrayList<Node> done = new ArrayList<>();

    public void build (int columns, int rows, int [][]picture){
        this.columns = columns;
        this.rows = rows;
        nodes = new Node[columns][rows];
        for(int i = 0; i < columns; ++i){
            for(int j = 0; j < rows; ++j){
                nodes[i][j] = new Node(i, j, picture[i][j]);

            }
        }


        int startIndex = rows/2;
        startNode = new Node(-1, startIndex, 0);
        endNode = new Node(columns, startIndex, 0);

        buildChildren(0, startNode);
        done.clear();
        done = null;
    }

    public void buildChildren(int x, Node start){
        if(x > columns){
            return;
        }
        if(done.contains(start)){
            return;
        }


        start.childNodes = new ArrayList<>();

        if(x == columns) {
            start.childNodes.add(endNode);
            return;
        }

        start.childNodes.addAll(Arrays.asList(nodes[x]).subList(Math.max(start.y - rangeOfNeighbours, 0), Math.min(start.y + rangeOfNeighbours, rows)));


        for(int i = 0; i < start.childNodes.size(); ++i){
            buildChildren(x + 1, start.childNodes.get(i));
        }
        done.add(start);

    }

}
