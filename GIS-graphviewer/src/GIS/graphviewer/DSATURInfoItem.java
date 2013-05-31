/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.graphviewer;

/**
 * This class is used to store information about parameters of one iteration of DSATUR
 * coloring algorithm.
 * @author Karol
 */
public class DSATURInfoItem {
    
    
    private int iteration;
    private int maxSaturation;
    private String saturationNodes;
    private int maxNodeDegree;
    private String degreeNodes;
    private int nextNode;
    
    /**
     * Creates empty DSATURInfoItem object.
     */
    public DSATURInfoItem(){}
    
    /**
     * Creates DSATURInfoItem object with specified parameters.
     * @param iteration Number of coloring algorithm iteration this object stores data of.
     * @param maxSaturation Value of maximum node saturation.
     * @param saturationNodes Text containing IDs of nodes having maximum node saturation.
     * @param maxNodeDegree Value of maximum node degree.
     * @param degreeNodes Text containing IDs of nodes having maximum node degree.
     * @param nextNode ID of next node to color.
     */
    public DSATURInfoItem(int iteration, int maxSaturation, String saturationNodes, int maxNodeDegree, String degreeNodes, int nextNode) {
        this.iteration = iteration;
        this.maxSaturation = maxSaturation;
        this.saturationNodes = saturationNodes;
        this.maxNodeDegree = maxNodeDegree;
        this.degreeNodes = degreeNodes;
        this.nextNode = nextNode;
    }

    /**
     * Gets value of maximum node saturation.
     * @return Value of maximum node saturation.
     */
    public int getMaxSaturation() {
        return maxSaturation;
    }

    /**
     * Gets text containing IDs of nodes having maximum node saturation.
     * @return Text containing IDs of nodes having maximum node saturation.
     */
    public String getSaturationNodes() {
        return saturationNodes;
    }

    /**
     * Gets value of maximum node degree.
     * @return Value of maximum node degree.
     */
    public int getMaxNodeDegree() {
        return maxNodeDegree;
    }

    /**
     * Gets text containing IDs of nodes having maximum node degree.
     * @return Text containing IDs of nodes having maximum node degree
     */
    public String getDegreeNodes() {
        return degreeNodes;
    }

    /**
     * Gets ID of next node to color.
     * @return ID of next node to color.
     */
    public int getNextNode() {
        return nextNode;
    }

    /**
     * Gets number of coloring algorithm iteration this object stores data of.
     * @return Number of coloring algorithm iteration this object stores data of.
     */
    public int getIteration() {
        return iteration;
    }
    
    /**
     * Gets this DSATURInfoItem as string. I.e. "iteration=7 maxSaturation=1 SatNodes=[ 1, 2, 3, 4, 5, 6, 8,] maxDegree=1 degreeNodes=[ 5, 6, 8,] nextNode=8".
     * @return DSATURInfoItem as string
     */
    @Override
    public String toString() {
        String s = "iteration=" + iteration + " maxSaturation=" + maxSaturation
                + " SatNodes=[" + saturationNodes + "] maxDegree=" + maxNodeDegree
                + " degreeNodes=[" + degreeNodes + "] nextNode=" + nextNode;
        return s;
    }
}
