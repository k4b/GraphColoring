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
public class RLFOutput {
    
    private ArrayList<ArrayList<Integer>> nodeColors;
    private ArrayList<RLFInfoItem> infos;
    
    public RLFOutput(ArrayList<ArrayList<Integer>> nodeColors, ArrayList<RLFInfoItem> infos) {
        this.nodeColors = nodeColors;
        this.infos = infos;
    }

    public ArrayList<ArrayList<Integer>> getNodeColors() {
        return nodeColors;
    }

    public ArrayList<RLFInfoItem> getInfos() {
        return infos;
    }
}
