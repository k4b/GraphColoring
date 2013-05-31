/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.graphviewer;

import java.util.ArrayList;

/**
 * This class is used to store information about parameters of one iteration of RLF
 * coloring algorithm.
 * @author Karol
 */
public class RLFInfoItem {
    
    private int iteration;
    private int maxDegree;
    private ArrayList<Integer> setW = new ArrayList<Integer>();
    private ArrayList<Integer> setU = new ArrayList<Integer>();
    private ArrayList<Integer> setC = new ArrayList<Integer>();
    private ArrayList<Integer> setWminusU = new ArrayList<Integer>();
    
    /**
     * Creates empty RLFInfoItem object.
     */
    public RLFInfoItem() {}
    
    /**
     * Creates RLFInfoItem with specified parameters.
     * @param iteration Number of coloring algorithm iteration this object stores data of.
     * @param setW ArrayList of IDs of nodes in set W.
     * @param setU ArrayList of IDs of nodes in set U.
     * @param setC ArrayList of IDs of nodes in set C.
     * @param setWminusU ArrayList of IDs of nodes in set W-U.
     */
    public RLFInfoItem(int iteration, ArrayList<Integer> setW, ArrayList<Integer> setU,
            ArrayList<Integer> setC, ArrayList<Integer> setWminusU) {
        this.iteration = iteration;
        this.setW = setW;
        this.setU = setU;
        this.setC = setC;
        this.setWminusU = setWminusU;
    }
    
    /**
     * Converts contents of specified set to text.
     * @param set A set of IDs to convert to text.
     * @return set of IDs as text.
     */
    public static String setToString(ArrayList<Integer> set) {
        String s = "";
        if(set.size()>1) {
            for(int i = 0; i<set.size();i++){
                if(i != set.size()-1)
                    s += set.get(i).toString() + ", ";
                else {
                    s += set.get(i).toString();
                }
            }
        } else if(set.size()==1) {
            s += set.get(0).toString();
                    
        }
        return s;
    }

    /**
     * Gets set W.
     * @return ArrayList of IDs in set W.
     */
    public ArrayList<Integer> getSetW() {
        return setW;
    }

    /**
     * Gets set U. 
     * @return ArrayList of IDs in set U.
     */
    public ArrayList<Integer> getSetU() {
        return setU;
    }

    /**
     * Gets set C.
     * @return ArrayList of IDs in set C.
     */
    public ArrayList<Integer> getSetC() {
        return setC;
    }

    /**
     * Gets set W-U.
     * @return ArrayList of IDs in set W-U.
     */
    public ArrayList<Integer> getSetWminusU() {
        return setWminusU;
    }
    
    /**
     * Gets number of iteration of coloring algorithm this RLFInfoItem object stores data for
     * @return Iteration number.
     */
    public int getIteration() {
        return iteration;
    }
    
    /**
     * Converts this RLFInfoItem object to text. I.e. "set W: 1, 2, 3, 4 </br> set U: 2, 3</br> set C: 1</br> set W-U: 1, 4".
     * @return 
     */
    @Override
    public String toString() {
        String s = "";
        s += "iteration: " + iteration + "\n";
        s += "set W: " + setToString(setW) + "\n";
        s += "set U: " + setToString(setU) + "\n";
        s += "set C: " + setToString(setC) + "\n";
        s += "set W-U " + setToString(setWminusU) + "\n";
        return s;        
    }
}
