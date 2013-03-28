package GIS.graphviewer;

import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FileLoader fl = new FileLoader();
		ArrayList<ArrayList<String>> nodeLinks;
		ArrayList<ArrayList<String>> nodeCoordinates;
		
		nodeLinks = fl.loadFile("sampleGraph");
		printDataMatrix(nodeLinks);
		System.out.println(fl.getNodesNumber());
		nodeCoordinates = fl.loadFile("sampleCoordinates");
		printDataMatrix(nodeCoordinates);
		System.out.println(fl.getNodesNumber());
		
		
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
	
//	public static void makeNodes(ArrayList<ArrayList<String>> nodeLinks, ArrayList<ArrayList<String>> nodeCoordinates)
//	{
//		int id = 1;
//		Node node;
//		for(ArrayList<String> nodeRow : nodeLinks){
//			for(ArrayList<String> neighbour : nodeLinks.get)
//			node = new Node(id, coord)
//		}
//	}
}
