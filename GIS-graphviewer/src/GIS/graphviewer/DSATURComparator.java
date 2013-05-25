/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.graphviewer;

import java.util.Comparator;

/**
 *
 * @author Karol
 */
public class DSATURComparator implements Comparator<DSATURitem>{

    @Override
    public int compare(DSATURitem o1, DSATURitem o2) {
        int nas;
        int stw;
        if(o1.getNasycenie()-o2.getNasycenie() > 0) {
            nas = 1;
            return nas;
        } else if(o1.getNasycenie()-o2.getNasycenie() == 0) {
            nas = 0;
            if(o1.getStWirzcholkowy() - o2.getStWirzcholkowy() >= 0) {
                stw = 1;
            } else {
                stw = -1;
            }
            return stw;
        }
        else {
            nas = -1;
            return nas;
        }
    }
    
}
