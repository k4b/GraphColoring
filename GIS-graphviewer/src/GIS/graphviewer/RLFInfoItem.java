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
public class RLFInfoItem {
    
    private int iteration;
    private int maxDegree;
    private ArrayList<Integer> setW = new ArrayList<Integer>();
    private ArrayList<Integer> setU = new ArrayList<Integer>();
    private ArrayList<Integer> setC = new ArrayList<Integer>();
    private ArrayList<Integer> setWminusU = new ArrayList<Integer>();
    
    public RLFInfoItem() {}
    
    public RLFInfoItem(int iteration, ArrayList<Integer> setW, ArrayList<Integer> setU,
            ArrayList<Integer> setC, ArrayList<Integer> setWminusU) {
        this.iteration = iteration;
        this.setW = setW;
        this.setU = setU;
        this.setC = setC;
        this.setWminusU = setWminusU;
    }
    
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

    public ArrayList<Integer> getSetW() {
        return setW;
    }

    public ArrayList<Integer> getSetU() {
        return setU;
    }

    public ArrayList<Integer> getSetC() {
        return setC;
    }

    public ArrayList<Integer> getSetWminusU() {
        return setWminusU;
    }
    
    public int getIteration() {
        return iteration;
    }

    public int getMaxDegree() {
        return maxDegree;
    }
    
    @Override
    public String toString() {
        String s = "";
        s += "set W: " + setToString(setW) + "\n";
        s += "set U: " + setToString(setU) + "\n";
        s += "set C: " + setToString(setC) + "\n";
        s += "set W-U " + setToString(setWminusU) + "\n";
        return s;        
    }
}
