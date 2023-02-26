package Graph_Building_and_dijkstra;

import java.util.ArrayList;

public class Dijkstra {
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
     *  Determines how much influence the difference in height between 2 pixels has on the weight of the edge between them
     */
    private static final int HEIGHT_PENALTY_MULTIPLIER = 15;

    /**
     * Edits the dijkstraValues of the target Node if necessary and adds the Node to the queue if it has not yet been processed
     * @param parent the root Node of the Edge
     * @param child the target Node of the Edge
     * @param queue the dijkstraQueue
     */
    private static void processDijkstra(Node parent, Node child, ArrayList<Node> queue){
        int dist = parent.dijkstraValue + child.weight + Math.abs(parent.y - child.y) * HEIGHT_PENALTY_MULTIPLIER;
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

