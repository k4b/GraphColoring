package GIS.graphviewer;

public class Controller {

	private int interval = 1000;
	private Model model;
	
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
	
	public Controller(Model m)
	{
		model = m;
	}
	
	public void loadNeighboursMatrix(String path)
	{
		model.setNeighboursMatrix(FileLoader.loadFile(path));
	}
	
	public void loadCoordinatesMatrix(String path)
	{
		model.setCoordinatesMatrix(FileLoader.loadFile(path));
	}
}
