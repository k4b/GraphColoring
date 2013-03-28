package GIS.graphviewer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileLoader {
	int nodesNumber;
	int currentRow=0;
	ArrayList<ArrayList<String>> dataArray = null;
	StringTokenizer st = null;
	FileReader fr = null;
	String line = "";
	
	public int getNodesNumber()
	{
		return nodesNumber;
	}

	public ArrayList<ArrayList<String>> loadFile(String path)
	{
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> tokens = new ArrayList<>();
		
		try {
			if((line = br.readLine())!=null)
				initialize();
			while((line = br.readLine())!=null){
				tokens = parseLine(line);
				placeTokens(tokens);
				currentRow++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataArray;
	}
	
	private ArrayList<String> parseLine(String line)
	{
		st = new StringTokenizer(line);
		ArrayList<String> tokens = new ArrayList<>();
		
		while(st.hasMoreTokens()){
			tokens.add(st.nextToken());
		}
		
		return tokens;
	}
	
	private void initialize()
	{
		ArrayList<String> firstLine = parseLine(line);
		nodesNumber = new Integer(firstLine.get(0));
		dataArray = new ArrayList<>();
	}
	
	private void placeTokens(ArrayList<String> t)
	{
			dataArray.add(t);
	}
}
