package GIS.graphviewer;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Node {
	private int id;
	private Color color = null;
	private Point coordinates = null;
	ArrayList<Integer> neighbours = null;
	
	public Node(int number, Point coord, ArrayList<Integer> neighb)
	{
		id = number;
		coordinates = coord;
		neighbours = neighb;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Point getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
