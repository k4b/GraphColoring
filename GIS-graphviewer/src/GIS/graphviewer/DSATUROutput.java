/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.graphviewer;

import java.util.ArrayList;

/**
 *
 * @author Karol
 */
public class DSATUROutput {
    
    private ArrayList<ArrayList<Integer>> nodeColors;
    private ArrayList<DSATURInfoItem> infos;
    
    public DSATUROutput(ArrayList<ArrayList<Integer>> nodeColors, ArrayList<DSATURInfoItem> infos) {
        this.nodeColors = nodeColors;
        this.infos = infos;
    }

    public ArrayList<DSATURInfoItem> getInfos() {
        return infos;
    }

    public ArrayList<ArrayList<Integer>> getNodeColors() {
        return nodeColors;
    }
}
