package GIS.graphviewer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Model object of this Model-View-Controller application. Used to store all data, such as neighbourhood matrix, 
 * coordinates matrix, colors matrix, current node colors, DSATURinfos array, RLFinfos array and used colors array.
 * @author Karol
 */
public class Model {

	private ArrayList<ArrayList<String>> neighboursMatrix;
	private ArrayList<ArrayList<String>> coordinatesMatrix;
	private ArrayList<ArrayList<String>> colorsMatrix;
        private ArrayList<ArrayList<Integer>> colorsIntegerMatrix;
        private ArrayList<Integer> currentColors;
        private ArrayList<Color> colors;
        private ArrayList<DSATURInfoItem> DSATURinfos;
        private ArrayList<RLFInfoItem> RLFinfos;
        
        /**
     * Creates this model object. Creates used colors array.
     */
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

        /**
     * Gets colors matrix as 2-dimensional integer matrix.
     * @return 2-dimensional integer matrix.
     */
    public ArrayList<ArrayList<Integer>> getColorsIntegerMatrix() {
            return colorsIntegerMatrix;
        }

        /**
     * Sets colors matrix as 2-dimensional integer matrix.
     * @param colorsIntegerMatrix 2-dimensional integer matrix of colors.
     */
    public void setColorsIntegerMatrix(ArrayList<ArrayList<Integer>> colorsIntegerMatrix) {
            this.colorsIntegerMatrix = colorsIntegerMatrix;
        }
	
	/**
     * Gets neigbourhood matrix as 2-dimensional String matrix.
     * @return 2-dimensional String matrix of neighbourhood.
     */
    public ArrayList<ArrayList<String>> getNeighboursMatrix() {
		return neighboursMatrix;
	}
	/**
     * Sets neigbourhood matrix as 2-dimensional String matrix.
     * @param neighboursMatrix 2-dimensional String matrix of neighbourhood.
     */
    public void setNeighboursMatrix(ArrayList<ArrayList<String>> neighboursMatrix) {
		this.neighboursMatrix = correctDataMatrix(neighboursMatrix);
	}
	/**
     * Gets coordinates matrix as 2-dimensional String matrix.
     * @return 2-dimensional String matrix of coordinates.
     */
    public ArrayList<ArrayList<String>> getCoordinatesMatrix() {
		return coordinatesMatrix;
	}
	/**
     * Sets coordinates matrix as 2-dimensional String matrix.
     * @param coordinatesMatrix 2-dimensional String matrix of coordinates.
     */
    public void setCoordinatesMatrix(ArrayList<ArrayList<String>> coordinatesMatrix) {
		this.coordinatesMatrix = correctDataMatrix(coordinatesMatrix);
	}
        /**
     * Gets colors ArrayList for current iteration of coloring algorithm.
     * @return ArrayList colors numbers for nodes.
     */
    public ArrayList<Integer> getCurrentColors() {
            return currentColors;
        }

        /**
     * Sets colors ArrayList for current iteration of coloring algorithm.
     * @param colorsIntegerMatrix ArrayList colors numbers for nodes.
     */
    public void setCurrentColors(ArrayList<Integer> colorsIntegerMatrix) {
            this.currentColors = colorsIntegerMatrix;
        }

        /**
     * Gets ArrayList of Color objects used to coloring.
     * @return ArrayList of Color objects used to coloring.
     */
    public ArrayList<Color> getColors() {
            return colors;
        }

        /**
     * Sets ArrayList of Color objects used to coloring.
     * @param colors ArrayList of Color objects used to coloring.
     */
    public void setColors(ArrayList<Color> colors) {
            this.colors = colors;
        }
        
        /**
     * Gets ArrayList of DSATURInfoItem objects used for DSATUR coloring algorithm parameters displaying.
     * @return ArrayList of DSATURInfoItem objects.
     */
    public ArrayList<DSATURInfoItem> getDSATURinfos() {
            return DSATURinfos;
        }

        /**
     * Sets ArrayList of DSATURInfoItem objects used for DSATUR coloring algorithm parameters displaying.
     * @param DSATURinfos ArrayList of DSATURInfoItem objects.
     */
    public void setDSATURinfos(ArrayList<DSATURInfoItem> DSATURinfos) {
            this.DSATURinfos = DSATURinfos;
        }   
        
        /**
     * Gets ArrayList of RLFInfoItem objects used for DSATUR coloring algorithm parameters displaying.
     * @return ArrayList of RLFInfoItem objects.
     */
    public ArrayList<RLFInfoItem> getRLFinfos() {
            return RLFinfos;
        }

        /**
     * Sets ArrayList of RLFInfoItem objects used for DSATUR coloring algorithm parameters displaying.
     * @param RLFinfos ArrayList of RLFInfoItem objects.
     */
    public void setRLFinfos(ArrayList<RLFInfoItem> RLFinfos) {
            this.RLFinfos = RLFinfos;
        }
	
	/**
     * Converts specified 2-dimensional matrix of Strings to single String.
     * @param matrix 2-dimensional matrix of Strings.
     * @return 2-dimensional matrix of Strings as text.
     */
    public static String stringMatrixToString(ArrayList<ArrayList<String>> matrix)
	{
                String output = "";
		for(ArrayList<String> row : matrix){
			for(String s : row){
				output += s + " ";
			}
			output += "\n";
		}
                return output;
	}
        
        /**
     * Converts 2-dimensional ArrayList of Integers to text.
     * @param matrix 2-dimensional ArrayList of Integers.
     * @return 2-dimensional ArrayList of Integers as text.
     */
    public static String integerMatrixToString(ArrayList<ArrayList<Integer>> matrix)
	{
                String output = "";
		for(ArrayList<Integer> row : matrix){
			for(Integer s : row){
				output += s.toString() + " ";
			}
			output += "\n";
		}
                return output;
	}
        
        /**
     * Converts 2-dimensional ArrayList of Strings to 2-dimensional ArrayList of Integers.
     * @param matrix 2-dimensional ArrayList of Strings.
     * @return 2-dimensional ArrayList of Integers.
     */
    public static ArrayList<ArrayList<Integer>> stringMatrixToIntegerMatrix(ArrayList<ArrayList<String>> matrix) {
            ArrayList<ArrayList<Integer>> output = new ArrayList<>();
            for(ArrayList<String> stringsRow : matrix) {
                ArrayList<Integer> row = new ArrayList<>();
                for(String s : stringsRow) {
                    Integer i = Integer.parseInt(s);
                    row.add(i);
                }
                output.add(row);
            }
            return output;
        }
        
        /**
     * Converts ArrayList of Integers to text.
     * @param arraylist ArrayList of Integers
     * @return ArrayList of Integers as text.
     */
    public static String arrayListToString(ArrayList<Integer> arraylist) {
            String s = "";
            for(Integer i : arraylist) {
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
