package GIS.graphviewer;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Model model = new Model();
                final View view = new View(model);
		Controller controller = new Controller(model, view);
                
                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        view.setVisible(true);
                    }
                });
                
		String file1 = "TestFiles/sampleIncorrectGraph";
		controller.loadNeighboursMatrix(file1);
		String file2 = "TestFiles/sampleCorrectCoordinates";
		controller.loadCoordinatesMatrix(file2);
		
		Model.matrixToString(model.getNeighboursMatrix());
		Model.matrixToString(model.getCoordinatesMatrix());
                
	}
}
