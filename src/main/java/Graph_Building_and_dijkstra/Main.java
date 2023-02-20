package Graph_Building_and_dijkstra;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int rows = 800, columns = 800;
        int [][] picture = new int[columns][rows];
        Random rand = new Random();
        for(int i = 0; i < columns; ++i){
            for(int j = 0; j < rows; ++j){
                picture[i][j] = rand.nextInt(0, 255);
            }
        }

        /* Darstellen des Arrays
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < columns; ++j){
                System.out.print(picture[j][i] + "\t");
            }
            System.out.println("");
        }//*/

        //long time = System.currentTimeMillis();
        Graph g = new Graph();
        g.build(columns, rows, picture);

        //System.out.println("Building: " +(System.currentTimeMillis() - time));
        //time = System.currentTimeMillis();

        System.out.println("\n");
        Node []nodes = dijkstra(g.startNode, g.endNode, columns);
        //System.out.println("Dijkstra: " + (System.currentTimeMillis() - time));


        /*Anzeigen des Ergebnisses
        for(int i = 0; i < nodes.length; ++i){
            if(nodes[i] != null)
                System.out.println(nodes[i].weight );
        }
         */

    }

    public static Node[] dijkstra(Node source, Node target, int pixelWidthAmount){
        ArrayList<Node> dijkstraQueue = new ArrayList<>();
        Node currentNode = source;
        dijkstraQueue.add(currentNode);
        source.dijkstraValue = 0;
        while(dijkstraQueue.size() > 0){
            currentNode = getSmallestInQueue(dijkstraQueue);

            if(currentNode.childNodes == null){
                continue;
            }
            for(Node n : currentNode.childNodes){
                int dist = currentNode.dijkstraValue + n.weight + Math.abs(currentNode.y - n.y);
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

        Node [] retVals = new Node[pixelWidthAmount];
        currentNode = target.dijkstraParent;
        int i = pixelWidthAmount - 1;
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

