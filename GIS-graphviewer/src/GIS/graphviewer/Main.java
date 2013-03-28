package GIS.graphviewer;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model);
		String file1 = "TestFiles/sampleIncorrectGraph";
		controller.loadNeighboursMatrix(file1);
		String file2 = "TestFiles/sampleCorrectCoordinates";
		controller.loadCoordinatesMatrix(file2);
		
		Model.printDataMatrix(model.getNeighboursMatrix());
		Model.printDataMatrix(model.getCoordinatesMatrix());
	}
}
