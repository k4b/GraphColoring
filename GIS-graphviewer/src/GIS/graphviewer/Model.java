package GIS.graphviewer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {

	private ArrayList<ArrayList<String>> neighboursMatrix;
	private ArrayList<ArrayList<String>> coordinatesMatrix;
	private ArrayList<ArrayList<String>> colorsMatrix;
	private ArrayList<Node> nodes;
	
	public ArrayList<ArrayList<String>> getNeighboursMatrix() {
		return neighboursMatrix;
	}
	public void setNeighboursMatrix(ArrayList<ArrayList<String>> neighboursMatrix) {
		this.neighboursMatrix = correctDataMatrix(neighboursMatrix);
	}
	public ArrayList<ArrayList<String>> getCoordinatesMatrix() {
		return coordinatesMatrix;
	}
	public void setCoordinatesMatrix(ArrayList<ArrayList<String>> coordinatesMatrix) {
		this.coordinatesMatrix = correctDataMatrix(coordinatesMatrix);
	}
	public ArrayList<ArrayList<String>> getColorsMatrix() {
		return colorsMatrix;
	}
	public void setColorsMatrix(ArrayList<ArrayList<String>> colorsMatrix) {
		this.colorsMatrix = colorsMatrix;
	}
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public static String matrixToString(ArrayList<ArrayList<String>> g)
	{
                String output = "";
		for(ArrayList<String> row : g){
			for(String s : row){
				output += s + " ";
			}
			output += View.LINE_END;
		}
                return output;
	}
	
	private static ArrayList<ArrayList<String>> correctDataMatrix(ArrayList<ArrayList<String>> matrix)
	{
		Pattern p = Pattern.compile("\\d+");
		Matcher m;
		ArrayList<ArrayList<String>> newMatrix = new ArrayList<>();
		ArrayList<String> newRow;
		for(ArrayList<String> row : matrix){
			newRow = new ArrayList<>();
			for( String token : row){
				m = p.matcher(token); 
				while (m.find()) {
					String s = m.group();
					if(s.length()>3)
						s = s.substring(0, 3);
					newRow.add(s);
				}
			}
			newMatrix.add(newRow);
		}
		return newMatrix;
	}
}
