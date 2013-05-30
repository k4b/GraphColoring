package GIS.testers;

import java.util.ArrayList;

import GIS.graphviewer.FileUtility;

public class FileLoadingTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FileUtility fl = new FileUtility();
		ArrayList<ArrayList<String>> nodeLinks;
		ArrayList<ArrayList<String>> nodeCoordinates;
		
		String file1 = "TestFiles/sampleCorrectGraph";
		System.out.println(file1);
		nodeLinks = fl.loadFile(file1);
		printDataMatrix(nodeLinks);
		System.out.println();

		String file2 = "TestFiles/sampleCorrectCoordinates";
		System.out.println(file2);
		nodeCoordinates = fl.loadFile(file2);
		printDataMatrix(nodeCoordinates);
		System.out.println();
		
		String file3 = "TestFiles/sampleIncorrectGraph";
		System.out.println(file3);
		nodeCoordinates = fl.loadFile(file3);
		printDataMatrix(nodeCoordinates);
		System.out.println();
		
		
	}
	
	public static void printDataMatrix(ArrayList<ArrayList<String>> g)
	{
		for(ArrayList<String> row : g){
			for(String s : row){
				System.out.print(s + " ");
			}
			System.out.println();
		}
//		for(int i=0; i<g.length;i++){
//			for( char c : g[i]){
//				System.out.print(c + " ");
//			}
//			System.out.println();
//		}
	}

}
