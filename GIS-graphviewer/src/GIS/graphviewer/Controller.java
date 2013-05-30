package GIS.graphviewer;

import GIS.graphviewer.Coloring.ALGORITHM;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller {
    
    
        private static final String DSATURPANEL = "DSATURPANEL";
        private static final String RLFPANEL = "RLFPANEL";
	private int interval;
        private String algorithm;
	private Model model;
        private View view;
        private Timer animationTimer;
        private AnimationState state = AnimationState.NOT_RUNNING;
        
        private enum AnimationState { RUNNING, PAUSED, NOT_RUNNING };
	
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
                CardLayout cl = (CardLayout)(view.getParamsPanel().getLayout());
                if(algorithm.equals("DSATUR"))
                    cl.show(view.getParamsPanel(), DSATURPANEL);
                else
                    cl.show(view.getParamsPanel(), RLFPANEL);
                
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
//            String algorithm;

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
                    model.setCurrentColors(null);
                    view.drawGraph();
                } else if (ae.getSource() == view.getAlgBox()){
                    algorithm = view.getAlgBox().getSelectedItem().toString();
                    CardLayout cl = (CardLayout)(view.getParamsPanel().getLayout());
                    if(algorithm.equals("DSATUR"))
                        cl.show(view.getParamsPanel(), DSATURPANEL);
                    else {
                        view.getParamsPanel().add(new RLFInfoPanel(), RLFPANEL);
                        cl.show(view.getParamsPanel(), RLFPANEL);
                    }
                    model.setCurrentColors(null);
                    view.drawGraph();
                    if(animationTimer!=null)
                        animationTimer.stop();
                    view.getRunBtn().setText("Run");
                    state = AnimationState.NOT_RUNNING;
                    
                } else if (ae.getSource() == view.getRunBtn()){
                    switch(state) {
                        case NOT_RUNNING :
                            run(algorithm, interval*3);
                            view.getRunBtn().setText("PAUSE (running)");
                            state = AnimationState.RUNNING;
                            break;
                        case RUNNING :
                            animationTimer.stop();
                            view.getRunBtn().setText("Run (paused)");
                            state = AnimationState.PAUSED;
                            break;
                        case PAUSED :
                            animationTimer.setDelay(interval*3);
                            animationTimer.start();
                            view.getRunBtn().setText("PAUSE (running)");
                            state = AnimationState.RUNNING;
                            break;
                    }
                } else if (ae.getSource() == view.getExitBtn()){
                    view.closeWindow();
                }
            }

            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider source = (JSlider)ce.getSource();
                if (!source.getValueIsAdjusting()) {
                    interval = (int)source.getValue();
                }
            }
        
            private void run(String alg, int interval) {
                view.log("Coloring with " + alg + " algorithm" + " and " + interval + " ms interval" + "\n");
                ArrayList<ArrayList<Integer>> colorsMatrix = null;
                ArrayList<ArrayList<String>> nM = model.getNeighboursMatrix();
                ArrayList<ArrayList<Integer>> neighboursMatrix = Model.convertStringToIntegerMatrix(nM);
                
                if(alg.equals(Coloring.ALGORITHM.DSATUR.toString())) {
                    DSATUROutput dso = Coloring.DSATUR(neighboursMatrix);
                    colorsMatrix = dso.getNodeColors();
                    model.setDSATURinfos(dso.getInfos());
                } else if(alg.equals(Coloring.ALGORITHM.RLF.toString())) {
                    RLFOutput rlfo = Coloring.RLF(neighboursMatrix);
                    colorsMatrix = rlfo.getNodeColors();
                    model.setRLFinfos(rlfo.getInfos());
                }
                model.setColorsIntegerMatrix(colorsMatrix);

                animationTimer = new Timer(interval, new AnimationActionListener());
                animationTimer.setInitialDelay(0);
                animationTimer.start();
            }
        }
        
        public class AnimationActionListener implements ActionListener {
            private int counter = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter < model.getColorsIntegerMatrix().size()) {
                    setCurrentColors();;
                    view.drawGraph();
                    if(algorithm.equals(Coloring.ALGORITHM.DSATUR.toString())) {
                        DSATURInfoItem infos = model.getDSATURinfos().get(counter);
                        JPanel infoPanel = new DSATURInfoPanel(infos);
                        view.getParamsPanel().removeAll();
                        view.getParamsPanel().add(infoPanel, DSATURPANEL);
                        CardLayout cl = (CardLayout)(view.getParamsPanel().getLayout());
                        cl.show(view.getParamsPanel(), DSATURPANEL);
                        view.revalidate();
                    } else if(algorithm.equals(Coloring.ALGORITHM.RLF.toString())) {
                        RLFInfoItem info = model.getRLFinfos().get(counter);
                        JPanel infoPanel = new RLFInfoPanel(info);
                        view.getParamsPanel().removeAll();
                        view.getParamsPanel().add(infoPanel, DSATURPANEL);
                        CardLayout cl = (CardLayout)(view.getParamsPanel().getLayout());
                        cl.show(view.getParamsPanel(), DSATURPANEL);
                        view.revalidate();
                    }
                    counter++;
                } else {
                    animationTimer.stop();
                    state = AnimationState.NOT_RUNNING;
                    view.getRunBtn().setText("Run");
                }
            }
            
            private void setCurrentColors() {
                ArrayList<Integer> colors = model.getColorsIntegerMatrix().get(counter);
                model.setCurrentColors(colors);
//                System.out.println("Current colors:");
//                System.out.println(model.arrayListToString(colors));
            }
            
        }
}
