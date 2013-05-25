package GIS.graphviewer;

import GIS.graphviewer.Coloring.ALGORITHM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller {
    
	private int interval;
        private String algorithm;
	private Model model;
        private View view;
	
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	
	public Controller(Model m, View v)
	{
		model = m;
                view = v;
                ALGORITHM[] algorithms = Coloring.ALGORITHM.values();
                algorithm = algorithms[0].toString();
                interval = view.getSlider().getValue();
                
                // add listeners to the view
                ControlsActionListener listener = new ControlsActionListener();
                view.addBtnActionListener(view.getLoadGraphBtn(), listener);
                view.addBtnActionListener(view.getLoadCoordBtn(), listener);
                view.addBtnActionListener(view.getShowBtn(), listener);
                view.addAlgBoxActionListener(listener);
                view.addSliderChangeListener(listener);
                view.addBtnActionListener(view.getRunBtn(), listener);
                view.addBtnActionListener(view.getExitBtn(), listener);
	}
	
	public void loadNeighboursMatrix(String path)
	{
		model.setNeighboursMatrix(FileLoader.loadFile(path));
                view.log("Graph loaded:" + View.LINE_END);
                view.log(Model.matrixToString(model.getNeighboursMatrix())+View.LINE_END);
	}
        
        public void loadNeighboursMatrix(File f)
	{
		model.setNeighboursMatrix(FileLoader.loadFile(f));
                view.log("Graph loaded:" + View.LINE_END);
                view.log(Model.matrixToString(model.getNeighboursMatrix())+View.LINE_END);
	}
	
	public void loadCoordinatesMatrix(String path)
	{
		model.setCoordinatesMatrix(FileLoader.loadFile(path));
                view.log("Coordinates loaded:" + View.LINE_END);
                view.log(Model.matrixToString(model.getCoordinatesMatrix())+View.LINE_END);
	}
        
        public void loadCoordinatesMatrix(File f)
	{
		model.setCoordinatesMatrix(FileLoader.loadFile(f));
                view.log("Coordinates loaded:" + View.LINE_END);
                view.log(Model.matrixToString(model.getCoordinatesMatrix())+View.LINE_END);
	}
        
        /**
         * 
         */
        public class ControlsActionListener implements ActionListener, ChangeListener{
            
            JFileChooser fc;
            int returnVal;
            File file;

            @Override
            public void actionPerformed(ActionEvent ae) {
                //handle load graph
                if(ae.getSource() == view.getLoadGraphBtn()){
                    fc = new JFileChooser();
                    returnVal = fc.showOpenDialog(view);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = fc.getSelectedFile();
                        loadNeighboursMatrix(file);
                        //This is where a real application would open the file.
                        view.log("Graph loaded:" + View.LINE_END);
                        view.log(Model.matrixToString(model.getNeighboursMatrix()));
                    } else {
                        view.log("Graph not loaded!" + View.LINE_END);
                    }
                } else if (ae.getSource() == view.getLoadCoordBtn()){
                    fc = new JFileChooser();
                    returnVal = fc.showOpenDialog(view);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = fc.getSelectedFile();
                        loadCoordinatesMatrix(file);
                        //This is where a real application would open the file.
                        view.log("Coordinates loaded:" + View.LINE_END);
                        view.log(Model.matrixToString(model.getCoordinatesMatrix()));
                    } else {
                        view.log("Coordinates not loaded!" + View.LINE_END);
                    }
                } else if (ae.getSource() == view.getShowBtn()){
                    view.drawGraph();
                } else if (ae.getSource() == view.getAlgBox()){
                    algorithm = view.getAlgBox().getSelectedItem().toString();
                    model.setNodeColors(null);
                    view.repaint();
                } else if (ae.getSource() == view.getRunBtn()){
                    run(algorithm, interval);
                } else if (ae.getSource() == view.getExitBtn()){
                    view.closeWindow();
                }
            }

        @Override
        public void stateChanged(ChangeEvent ce) {
            JSlider source = (JSlider)ce.getSource();
            if (!source.getValueIsAdjusting()) {
                interval = (int)source.getValue();
//                if (fps == 0) {
//                    if (!frozen) stopAnimation();
//                } else {
//                    delay = 1000 / fps;
//                    timer.setDelay(delay);
//                    timer.setInitialDelay(delay * 10);
//                    if (frozen) startAnimation();
//                }
            }
        }
        
        private void run(String alg, int interval) {
            view.log("Coloring with " + alg + " algorithm" + " and " + interval + " interval" + "\n");
            ArrayList<ArrayList<Integer>> colorsMatrix;
            ArrayList<ArrayList<String>> nM = model.getNeighboursMatrix();
            ArrayList<ArrayList<Integer>> neighboursMatrix = Model.convertStringToIntegerMatrix(nM);
            colorsMatrix = Coloring.DSATUR(neighboursMatrix);
            model.setColorsIntegerMatrix(colorsMatrix);
            System.out.println("kolory:");
            System.out.println(Coloring.macierzToString(colorsMatrix));
            view.repaint();
        }
    }
}
