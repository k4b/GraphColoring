/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.graphviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Implementation of JPanel used to draw graphs on it. Drawing algoritm is 
 * built-in paintComponent() method. Scales its size to fit nodes with coordinates from 0-999 range.
 * @author karol
 */
public class CanvasPanel extends JPanel{
    
    private Model model;
    private double xStep, yStep;
    private double resolution = 1000.0;
    private double nodeCentersBoundary = 800.0;
    private double diameter;
    private Graphics2D g2d;
    
    /**
     * Constructs this CanvasPanel. 
     * @param model Data model of this app
     */
    public CanvasPanel(Model model){
        this.model = model;
    }
    /**
     * Overrides common paintComponent() method for JPanel. Implements algorithm 
     * for graph drawing based on data model of this app.
     * @param g Graphis object used to drawing
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        scaleResolution();
        
        g2d = (Graphics2D)g;
        
        ArrayList<Integer> nodeCoordinates;
        ArrayList<ArrayList<Integer>> nodesCoordinates = new ArrayList<>();
        
        //Drawing Graph
        
        if(model.getNeighboursMatrix()!=null && model.getCoordinatesMatrix()!=null){
            int howMany = Math.min(model.getNeighboursMatrix().size(), model.getCoordinatesMatrix().size());
            
            for(int i = 0; i<howMany; i++){
                ArrayList<String> coordinates = model.getCoordinatesMatrix().get(i);
                if(coordinates.size() >= 3) {
                    int x = Integer.parseInt(coordinates.get(0));
                    int y = Integer.parseInt(coordinates.get(1));
                    int z = Integer.parseInt(coordinates.get(2));

                    nodeCoordinates = new ArrayList<>();
                    nodeCoordinates.add(x);
                    nodeCoordinates.add(y);
                    nodeCoordinates.add(z);



                    //Drawing links
                    ArrayList<String> links = model.getNeighboursMatrix().get(i);

                    for(int j = 0; j<howMany;j++){
                        if(i!=j && links.size()>=howMany && !links.get(j).equals("0")){
                            ArrayList<String> coordinatesTo = model.getCoordinatesMatrix().get(j);
                            if(coordinatesTo.size() >= 3) {
                                int xTo = Integer.parseInt(coordinatesTo.get(0));
                                int yTo = Integer.parseInt(coordinatesTo.get(1));
                                int zTo = Integer.parseInt(coordinatesTo.get(2));

                                drawLink(x*xStep, y*yStep, xTo*xStep, yTo*yStep);
                            } else {
                                System.out.println("Coordinates data error!");
                            }
                        }
                    }
                    nodesCoordinates.add(nodeCoordinates);
                } else {
                    System.out.println("Coordinates data error!");
                }
                //Drawing nodes
                drawNodes(nodesCoordinates);
            }
        }
    }
    
    private void scaleResolution(){
        double scale = nodeCentersBoundary/resolution/getHeight();
        
//        xStep = d.getWidth()/resolution;
//        yStep = d.getHeight()/resolution;
        xStep = getWidth()*scale;
        yStep = getHeight()*scale;
        diameter = xStep*60;
//        System.out.println(getSize() + " xstep=" + xStep + " ystep=" + yStep);
    }
    
    private void drawNodes(ArrayList<ArrayList<Integer>> nodesCoordinates) {
        int nodeID = 0;
            ArrayList<Integer> nodeColors = model.getCurrentColors();
            if(nodeColors!=null && nodeColors.size()>0) {
                for(ArrayList<Integer> coords : nodesCoordinates){
                    drawNode(""+(nodeID), (int)(coords.get(0)*xStep), (int)(coords.get(1)*yStep), (int)diameter, 
                            model.getColors().get(nodeColors.get(nodeID)));
                    
                    nodeID++;
                }
            } else {
                for(ArrayList<Integer> coords : nodesCoordinates){
                    drawNode(""+(nodeID), (int)(coords.get(0)*xStep), (int)(coords.get(1)*yStep), (int)diameter, Color.white);
                    nodeID++;
                }
            }
    }
    
    private void drawNode(String id, int x, int y, int diameter, Color background){
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
        g2d.setColor(background);
        g2d.fill(circle);
        g2d.setColor(Color.BLACK);
        g2d.draw(circle);
        drawCenteredString(id, g2d, x, y, diameter);
    }
    
    private void drawCenteredString(String id, Graphics g, int x, int y, int diameter) {
        FontMetrics fm = g.getFontMetrics();
        int totalWidth = (fm.stringWidth(id)*2) + 4;
        
        x += (int)diameter/2 - fm.stringWidth(id)/2;
        y = y + diameter/2 + fm.getAscent()/2;
        g.drawString(id, x, y);        
    }
    
    private void drawLink(double xFrom, double yFrom, double xTo, double yTo){
        g2d.draw(new Line2D.Double(xFrom+diameter/2, yFrom+diameter/2, xTo+diameter/2, yTo+diameter/2));
    }
}
