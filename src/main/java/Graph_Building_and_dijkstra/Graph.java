package Graph_Building_and_dijkstra;

public class Graph {
    Node startNode, endNode;

    Node [][] nodes;
    int columns, rows;

    public void build (int columns, int rows, int [][]picture){
        this.columns = columns;
        this.rows = rows;
        nodes = new Node[columns][rows];
        for(int i = 0; i < columns; ++i){
            for(int j = 0; j < rows; ++j){
                nodes[i][j] = new Node(i, j, picture[i][j]);
            }
        }

        int smallestIndex = 0;
        int smallestValue = picture[0][0];
        for(int i = 1; i < rows; ++i){
            if(picture[0][i] < smallestValue){
                smallestIndex = i;
                smallestValue = picture[0][i];
            }
        }
        startNode = new Node(-1, smallestIndex, 0);
        endNode = new Node(columns, smallestIndex, 0);
    }
}
