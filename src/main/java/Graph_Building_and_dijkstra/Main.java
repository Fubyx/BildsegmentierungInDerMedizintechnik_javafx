package Graph_Building_and_dijkstra;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static final int rangeOfNeighbours = 10;
    public static void main(String[] args) {
        int rows = 5, columns = 5;
        int [][] picture = new int[columns][rows];
        Random rand = new Random();
        for(int i = 0; i < columns; ++i){
            for(int j = 0; j < rows; ++j){
                picture[i][j] = rand.nextInt(0, 255);
            }
        }
        /*Darstellen des Arrays
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < columns; ++j){
                System.out.print(picture[j][i] + "\t");
            }
            System.out.println("");
        }//*/


        Graph g= new Graph();
        g.build(columns, rows, picture);
        Node []nodes = dijkstra(g);

        /*Anzeigen des Ergebnisses
        for(int i = 0; i < nodes.length; ++i){
            if(nodes[i] != null)
                System.out.println(nodes[i].weight );
        }
        //*/


    }

    public static Node[] dijkstra(Graph g){
        ArrayList<Node> dijkstraQueue = new ArrayList<>();
        Node currentNode = g.startNode;
        dijkstraQueue.add(currentNode);
        currentNode.dijkstraValue = 0;
        while(dijkstraQueue.size() > 0){
            currentNode = getSmallestInQueue(dijkstraQueue);
            if(currentNode.x > g.columns - 1){
                break;
            }
            if(currentNode.x == g.columns - 1){
                if(!(currentNode.y > g.endNode.y - rangeOfNeighbours && currentNode.y < g.endNode.y + rangeOfNeighbours)){
                    continue;
                }
                int dist = currentNode.dijkstraValue + g.endNode.weight + Math.abs(currentNode.y - g.endNode.y);
                if(dijkstraQueue.contains(g.endNode) && g.endNode.dijkstraValue > dist){
                    g.endNode.dijkstraValue = dist;
                    g.endNode.dijkstraParent = currentNode;
                }else if(g.endNode.dijkstraParent == null){
                    g.endNode.dijkstraValue = dist;
                    g.endNode.dijkstraParent = currentNode;
                    dijkstraQueue.add(g.endNode);
                }
                continue;
            }
            int start = Math.max(0, currentNode.y - rangeOfNeighbours);
            int end = Math.min(currentNode.y + rangeOfNeighbours, g.rows);
            int x = currentNode.x + 1;
            for(int i = start; i < end; ++i){
                Node n = g.nodes[x][i];
                int dist = currentNode.dijkstraValue + n.weight + Math.abs(currentNode.y - i);
                if(dijkstraQueue.contains(n) && n.dijkstraValue > dist){

                    n.dijkstraValue = dist;
                    n.dijkstraParent = currentNode;
                }else if(n.dijkstraParent == null){
                    n.dijkstraValue = dist;
                    n.dijkstraParent = currentNode;
                    dijkstraQueue.add(n);
                }
            }
        }

        Node [] retVals = new Node[g.columns];
        currentNode = g.endNode.dijkstraParent;
        int i = g.columns - 1;
        while (i >= 0){
            if(currentNode == null)
                break;
            retVals[i] = currentNode;
            currentNode = currentNode.dijkstraParent;
            --i;
        }
        return retVals;
    }

    private static Node getSmallestInQueue (ArrayList<Node> queue){
        int smallest = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < queue.size(); ++i){
            if(queue.get(i).dijkstraValue < smallest){
                smallest = queue.get(i).dijkstraValue;
                index = i;
            }
        }
        Node smallestNode = queue.get(index);
        queue.remove(smallestNode);
        return smallestNode;
    }
}

