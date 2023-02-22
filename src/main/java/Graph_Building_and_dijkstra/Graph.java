package Graph_Building_and_dijkstra;

public class Graph {

    /**
     * range in positive and negative direction of how any nodes (/pixels) of the next column a node is connected to
     */
    protected static final int RANGE_OF_NEIGHBOURS = 10;
    protected Node source, target;
    //2 Nodes left and right of the Picture, at the height, of the brightest Point on the left side.
    //this way the final Pixel can only be in a certain distance from the starting-height, that distance is defined in RANGE_OF_NEIGHBOURS
    
    protected Node [][] nodes;
    protected int columns, rows;
    
    /**
     *  Creation of the graph.
     *  A 2 dimensional array of Nodes is created. The color value of each pixel defines the weight of the node.
     * @param picture: A 2 dimensional array of type int containing the rgb value of the pixel converted to an integer
     */
    public void build (int [][]picture){
        this.columns = picture.length;
        this.rows = picture[0].length;
        nodes = new Node[columns][rows];
        for(int i = 0; i < columns; ++i){
            for(int j = 0; j < rows; ++j){
                nodes[i][j] = new Node(i, j, picture[i][j]);
            }
        }

        //finding the brightest Pixel in the first column.
        int smallestIndex = 0;
        int smallestValue = picture[0][0];
        for(int i = 1; i < rows; ++i){
            if(picture[0][i] < smallestValue){
                smallestIndex = i;
                smallestValue = picture[0][i];
            }
        }
        
        source = new Node(-1, smallestIndex, 0);
        target = new Node(columns, smallestIndex, 0);
    }
}
