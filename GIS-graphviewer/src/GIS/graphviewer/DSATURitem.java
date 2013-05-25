/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.graphviewer;

/**
 *
 * @author Karol
 */
public class DSATURitem {
    private int nasycenie;
    private int stWirzcholkowy;
    
    public DSATURitem(int nasycenie,int stWirzcholkowy) {
        this.stWirzcholkowy = stWirzcholkowy;
        this.nasycenie = nasycenie;
    }

    public int getStWirzcholkowy() {
        return stWirzcholkowy;
    }

    public void setStWirzcholkowy(int stWirzcholkowy) {
        this.stWirzcholkowy = stWirzcholkowy;
    }

    public int getNasycenie() {
        return nasycenie;
    }

    public void setNasycenie(int nasycenie) {
        this.nasycenie = nasycenie;
    }
    
}
