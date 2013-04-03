package GIS.graphviewer;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
            try {
                    // Set System L&F
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } 
            catch (UnsupportedLookAndFeelException e) {
               // handle exception
            }
            catch (ClassNotFoundException e) {
               // handle exception
            }
            catch (InstantiationException e) {
               // handle exception
            }
            catch (IllegalAccessException e) {
               // handle exception
            }

            Model model = new Model();
            final View view = new View(model);
            Controller controller = new Controller(model, view);
            
            String file1 = "TestFiles/sampleCorrectGraph";
            controller.loadNeighboursMatrix(file1);
            String file2 = "TestFiles/sampleCorrectCoordinates";
            controller.loadCoordinatesMatrix(file2);

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    view.setVisible(true);
                }
            });

            
                
	}
}
