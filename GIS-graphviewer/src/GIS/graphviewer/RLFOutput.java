/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.graphviewer;

import java.util.ArrayList;

/**
 * This class is used to store output of RLF coloring algorithm. It contains colors matrix 
 * as well as algorithm parameters to show, both for each iteration of the algorithm.
 * @author Karol
 */
public class RLFOutput {
    
    private ArrayList<ArrayList<Integer>> nodeColors;
    private ArrayList<RLFInfoItem> infos;
    
    /**
     * Creates this RLFOutput object with specified ArrayLists.
     * @param nodeColors 2-dimensional ArrayList of node colors in every iteration of RLF algorithm.
     * @param infos ArrayList of RLFInfoItem objects storing algorithm parameters info.
     */
    public RLFOutput(ArrayList<ArrayList<Integer>> nodeColors, ArrayList<RLFInfoItem> infos) {
        this.nodeColors = nodeColors;
        this.infos = infos;
    }

    /**
     * Gets ArrayList of node colors.
     * @return ArrayList of node colors.
     */
    public ArrayList<ArrayList<Integer>> getNodeColors() {
        return nodeColors;
    }

    /**
     * Gets ArrayList of RLFInfoItem objects.
     * @return ArrayList of RLFInfoItem objects.
     */
    public ArrayList<RLFInfoItem> getInfos() {
        return infos;
    }
}
