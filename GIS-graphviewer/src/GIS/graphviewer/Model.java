package GIS.graphviewer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {

	private ArrayList<ArrayList<String>> neighboursMatrix;
	private ArrayList<ArrayList<String>> coordinatesMatrix;
	private ArrayList<ArrayList<String>> colorsMatrix;
        private ArrayList<ArrayList<Integer>> colorsIntegerMatrix;
        private ArrayList<Integer> currentColors;
	private ArrayList<Node> nodes;
        private ArrayList<Color> colors;
        
        public Model() {
            colors = new ArrayList<>();
            colors.add(Color.white);
            colors.add(Color.yellow);
            colors.add(Color.green);
            colors.add(Color.orange);
            colors.add(Color.pink);
            colors.add(Color.gray);
            colors.add(Color.cyan);
            colors.add(Color.magenta);
            colors.add(Color.blue);
            colors.add(Color.lightGray);
        }

        public ArrayList<ArrayList<Integer>> getColorsIntegerMatrix() {
            return colorsIntegerMatrix;
        }

        public void setColorsIntegerMatrix(ArrayList<ArrayList<Integer>> colorsIntegerMatrix) {
            this.colorsIntegerMatrix = colorsIntegerMatrix;
        }
	
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
        
        public ArrayList<Integer> getCurrentColors() {
            return currentColors;
        }

        public void setCurrentColors(ArrayList<Integer> colorsIntegerMatrix) {
            this.currentColors = colorsIntegerMatrix;
        }

        public ArrayList<Color> getColors() {
            return colors;
        }

        public void setColors(ArrayList<Color> colors) {
            this.colors = colors;
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
        
        public static String integerMatrixToString(ArrayList<ArrayList<Integer>> g)
	{
                String output = "";
		for(ArrayList<Integer> row : g){
			for(Integer s : row){
				output += s.toString() + " ";
			}
			output += View.LINE_END;
		}
                return output;
	}
        
        public static ArrayList<ArrayList<Integer>> convertStringToIntegerMatrix(ArrayList<ArrayList<String>> m) {
            ArrayList<ArrayList<Integer>> output = new ArrayList<>();
            for(ArrayList<String> stringsRow : m) {
                ArrayList<Integer> row = new ArrayList<>();
                for(String s : stringsRow) {
                    Integer i = Integer.parseInt(s);
                    row.add(i);
                }
                output.add(row);
            }
            return output;
        }
        
        public static String arrayListToString(ArrayList<Integer> a) {
            String s = "";
            for(Integer i : a) {
                s += i.toString() + " ";
            }
            return s;
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
