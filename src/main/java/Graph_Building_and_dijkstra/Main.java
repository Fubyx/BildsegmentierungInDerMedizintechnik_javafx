package Graph_Building_and_dijkstra;

import java.util.ArrayList;
import java.util.Random;

public class Main {

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

        //Graph building and dijkstra
        Graph g= new Graph();
        g.build(picture);
        Node []path = dijkstra(g);

        /*Anzeigen des Ergebnisses
        for(int i = 0; i < path.length; ++i){
            if(path[i] != null)
                System.out.println(path[i].weight );
        }
        //*/


    }

    /**
     *  Finds the shortest way from the node source to the node target, specified in the graph.
     * @param g: Graph on which the algorithm is performed
     * @return Array of Nodes containing the shortest path from g.source to g.target
     */
    public static Node[] dijkstra(Graph g){
        ArrayList<Node> dijkstraQueue = new ArrayList<>();//Queue for the nodes that need to be processed
        Node currentNode = g.source;
        dijkstraQueue.add(currentNode);
        currentNode.dijkstraValue = 0;
        //Finding the shortest path
        while(dijkstraQueue.size() > 0){
            currentNode = getSmallestInQueue(dijkstraQueue);
            if(currentNode == g.target){
                break; //The algorithm has finished, once the target Node has the smallest dijkstraValue in the queue.
            }
            if(currentNode.x == g.columns - 1){//currentNode is in the last colum,n and can therefore only be connected to target.
                if(!(currentNode.y > g.target.y - Graph.RANGE_OF_NEIGHBOURS && currentNode.y < g.target.y + Graph.RANGE_OF_NEIGHBOURS)){//check if target is in Range of the Node
                    continue;
                }
                processDijkstra(currentNode, g.target, dijkstraQueue);
                continue;
            }
            int start = Math.max(0, currentNode.y - Graph.RANGE_OF_NEIGHBOURS);
            int end = Math.min(currentNode.y + Graph.RANGE_OF_NEIGHBOURS, g.rows);
            int x = currentNode.x + 1;
            for(int i = start; i < end; ++i){//all Nodes in range get processed
                processDijkstra(currentNode, g.nodes[x][i], dijkstraQueue);
            }
        }


        //Transforming the shortest path into an array
        Node [] retVals = new Node[g.columns];
        currentNode = g.target.dijkstraParent;
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

    /**
     * Edits the dijkstraValues of the target Node if necessary and adds the Node to the queue if it has not yet been processed
     * @param parent the root Node of the Edge
     * @param child the target Node of the Edge
     * @param queue the dijkstraQueue
     */
    private static void processDijkstra(Node parent, Node child, ArrayList<Node> queue){
        int dist = parent.dijkstraValue + child.weight + Math.abs(parent.y - child.y);
        if(queue.contains(child) && child.dijkstraValue > dist){
            child.dijkstraValue = dist;
            child.dijkstraParent = parent;
        }else if(child.dijkstraParent == null){
            child.dijkstraValue = dist;
            child.dijkstraParent = parent;
            queue.add(child);
        }
    }

    /**
     *  Finds and returns the Node with the smallest dijkstraValue.
     * @param queue: The queue on which the operation is performed. An ArrayList of Nodes
     * @return The node with the smallest dijkstraValue in the queue
     */
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

