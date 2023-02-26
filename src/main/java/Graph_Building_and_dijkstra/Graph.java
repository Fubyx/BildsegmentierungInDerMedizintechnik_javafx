package Graph_Building_and_dijkstra;

public class Graph {

    /**
     * range in positive and negative direction of how any nodes (/pixels) of the next column a node is connected to
     */
    protected static final int RANGE_OF_NEIGHBOURS = 10;
    /**
     *  2 Nodes left and right of the Picture, at the height, of the brightest Point on the left side.
     *  this way the final Pixel can only be in a certain distance from the starting-height, that distance is defined in RANGE_OF_NEIGHBOURS
     */
    protected Node source, target;
    protected Node [][] nodes;
    protected int columns, rows;
    
    /**
     *  Creation of the graph.
     *  A 2 dimensional array of Nodes is created. The color value of each pixel defines the weight of the node.
     * @param picture: A 2 dimensional array of type int containing the gray-map value of the pixel converted to an integer
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
        int startIndex = rows/4;//starting a little below the top, to evade the white circles almost at the center of the image
        int smallestIndex = startIndex;
        int smallestValue = picture[0][startIndex];
        for(int i = startIndex; i < rows - startIndex; ++i){//stopping above the bottom, to evade the white lines at the bottom
            if(picture[0][i] < 10){
                continue;
            }
            if(picture[0][i] < smallestValue){
                smallestIndex = i;
                smallestValue = picture[0][i];
            }
        }
        rows -= 10; //so the white border at the bottom of the picture is ignored

        source = new Node(-1, smallestIndex, 0);
        target = new Node(columns, smallestIndex, 0);
    }
}
