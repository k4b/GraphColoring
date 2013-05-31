/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.graphviewer;

import java.util.ArrayList;

/**
 * This class is used to store output of DSATUR coloring algorithm. It contains colors matrix 
 * as well as algorithm parameters to show, both for each iteration of the algorithm.
 * @author Karol
 */
public class DSATUROutput {
    
    private ArrayList<ArrayList<Integer>> nodeColors;
    private ArrayList<DSATURInfoItem> infos;
    
    /**
     * Creates this DSATUROutput object with specified arrays
     * @param nodeColors Array of node colors in every iteration of DSATUR algorithm.
     * @param infos Array of DSATURInfoItem objects storing algorithm parameters info.
     */
    public DSATUROutput(ArrayList<ArrayList<Integer>> nodeColors, ArrayList<DSATURInfoItem> infos) {
        this.nodeColors = nodeColors;
        this.infos = infos;
    }

    /**
     * Gets ArrayList of DSATURInfoItem objects.
     * @return ArrayList of DSATURInfoItem objects.
     */
    public ArrayList<DSATURInfoItem> getInfos() {
        return infos;
    }

    /**
     * Gets ArrayList of node colors.
     * @return ArrayList of node colors.
     */
    public ArrayList<ArrayList<Integer>> getNodeColors() {
        return nodeColors;
    }
}
