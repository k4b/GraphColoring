/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.graphviewer;

/**
 *
 * @author Karol
 */
public class DSATURInfoItem {
    
    
    private int iteration;
    private int maxSaturation;
    private String saturationNodes;
    private int maxNodeDegree;
    private String degreeNodes;
    private int nextNode;
    
    public DSATURInfoItem(){}
    
    public DSATURInfoItem(int i, int maxS, String satn, int maxd, String deg, int next) {
        iteration = i;
        maxSaturation = maxS;
        saturationNodes = satn;
        maxNodeDegree = maxd;
        degreeNodes = deg;
        nextNode = next;
    }

    public int getMaxSaturation() {
        return maxSaturation;
    }

    public String getSaturationNodes() {
        return saturationNodes;
    }

    public int getMaxNodeDegree() {
        return maxNodeDegree;
    }

    public String getDegreeNodes() {
        return degreeNodes;
    }

    public int getNextNode() {
        return nextNode;
    }

    public int getIteration() {
        return iteration;
    }
    
    @Override
    public String toString() {
        String s = "iteration=" + iteration + " maxSaturation=" + maxSaturation
                + " SatNodes=[" + saturationNodes + "] maxDegree=" + maxNodeDegree
                + " degreeNodes=[" + degreeNodes + "] nextNode=" + nextNode;
        return s;
    }
}
