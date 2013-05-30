/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.graphviewer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;
import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 *
 * @author karol
 */
public class View extends javax.swing.JFrame {
    private static final String DSATURPANEL = "DSATURPANEL";
    private static final String RLFPANEL = "RLFPANEL";
    JTextArea logger = new JTextArea();
    /**
     * Creates new form View
     */
    public View(Model m) {
        model = m;
        initComponents();
        paramsPanel.add(new DSATURInfoPanel(), DSATURPANEL);
        paramsPanel.add(new RLFInfoPanel(), RLFPANEL);
        CardLayout cl = (CardLayout)(paramsPanel.getLayout());
        cl.show(paramsPanel, DSATURPANEL);
        DefaultCaret caret = (DefaultCaret)logger.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        Dimension dim = new Dimension(logger.getWidth(), (int)(0.15*this.getHeight()));
        logger.setMinimumSize(dim);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        paramsPanel = new JPanel(new CardLayout());
        controlsPanel = new javax.swing.JPanel();
        loadGraphBtn = new javax.swing.JButton();
        algBox = new JComboBox(Coloring.ALGORITHM.values());
        ;
        slider = new javax.swing.JSlider();
        runBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        canvasPanel = new CanvasPanel(model);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Graph coloring visualiser");

        sidePanel.setMaximumSize(new java.awt.Dimension(800, 800));

        paramsPanel.setLayout(new java.awt.CardLayout());

        controlsPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

        loadGraphBtn.setText("Load graph");
        controlsPanel.add(loadGraphBtn);
        controlsPanel.add(algBox);

        slider.setMaximum(1000);
        slider.setToolTipText("");
        slider.setValue(500);
        slider.setBorder(javax.swing.BorderFactory.createTitledBorder("Delay"));
        controlsPanel.add(slider);

        runBtn.setText("Run");
        controlsPanel.add(runBtn);

        saveBtn.setText("Save colors matrix");
        controlsPanel.add(saveBtn);

        exitBtn.setText("Exit");
        controlsPanel.add(exitBtn);

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            .addComponent(paramsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addComponent(paramsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        canvasPanel.setBackground(javax.swing.UIManager.getDefaults().getColor("white"));
        canvasPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        canvasPanel.setMaximumSize(new java.awt.Dimension(1000, 1000));
        canvasPanel.setMinimumSize(new java.awt.Dimension(600, 600));
        canvasPanel.setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout canvasPanelLayout = new javax.swing.GroupLayout(canvasPanel);
        canvasPanel.setLayout(canvasPanelLayout);
        canvasPanelLayout.setHorizontalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 798, Short.MAX_VALUE)
        );
        canvasPanelLayout.setVerticalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(canvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(canvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox algBox;
    private javax.swing.JPanel canvasPanel;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton loadGraphBtn;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel paramsPanel;
    private javax.swing.JButton runBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JSlider slider;
    // End of variables declaration//GEN-END:variables
    // Programmer variables declaration
    public static final String LINE_END = "\n";
    private Model model;
    // End of programmer variables declaration

    /**
     * @return the controlsPanel
     */
    public javax.swing.JPanel getControlsPanel() {
        return controlsPanel;
    }

    /**
     * @param controlsPanel the controlsPanel to set
     */
    public void setControlsPanel(javax.swing.JPanel controlsPanel) {
        this.controlsPanel = controlsPanel;
    }

    /**
     * @return the exitBtn
     */
    public javax.swing.JButton getExitBtn() {
        return exitBtn;
    }

    /**
     * @return the jComboBox1
     */
    public javax.swing.JComboBox getAlgBox() {
        return algBox;
    }

    /**
     * @return the jSlider1
     */
    public javax.swing.JSlider getSlider() {
        return slider;
    }

    /**
     * @return the loadGraphBtn
     */
    public javax.swing.JButton getLoadGraphBtn() {
        return loadGraphBtn;
    }

    /**
     * @return the mainPanel
     */
    public javax.swing.JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * @param mainPanel the mainPanel to set
     */
    public void setMainPanel(javax.swing.JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    /**
     * @return the paramsPanel
     */
    public javax.swing.JPanel getParamsPanel() {
        return paramsPanel;
    }

    /**
     * @param paramsPanel the paramsPanel to set
     */
    public void setParamsPanel(javax.swing.JPanel paramsPanel) {
        this.paramsPanel = paramsPanel;
    }

    /**
     * @return the runBtn
     */
    public javax.swing.JButton getRunBtn() {
        return runBtn;
    }

    /**
     * @return the showBtn
     */
    public javax.swing.JButton getSaveBtn() {
        return saveBtn;
    }

    /**
     * @return the sidePanel
     */
    public javax.swing.JPanel getSidePanel() {
        return sidePanel;
    }

    /**
     * @return the logger
     */
    public javax.swing.JTextArea getLogger() {
        return logger;
    }

    /**
     * @param logger the logger to set
     */
    public void setLogger(javax.swing.JTextArea logger) {
        this.logger = logger;
    }

    public void addBtnActionListener(JButton btn, ActionListener al){
        btn.addActionListener(al);
    }
    
    public void addAlgBoxActionListener(ActionListener al){
        algBox.addActionListener(al);
    }
    
    public void addSliderChangeListener(ChangeListener cl){
        slider.addChangeListener(cl);
    }
    
    public void closeWindow(){
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }
    
    public void drawGraph(){
        canvasPanel.repaint();
    }
    
    public void log(String s){
        logger.append(s + "\n");
    }
}
