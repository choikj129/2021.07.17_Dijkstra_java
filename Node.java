package dijkstra;

import java.awt.Color;
import java.awt.Graphics;

public class Node {
	private int x, y, dist;
	private boolean visited;
	private String se;
	private Node pre;

	public Node() {	
	}
	
	public Node(int a, int b) {
		x = a;
		y = b;
		dist = 999;
		pre = null;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.green);
		if (se=="start") {
			g.setColor(Color.yellow);
		}
		else if (se=="end") {
			g.setColor(Color.cyan);
		}
		g.fillOval(x-25,y-25,50,50);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int a) {
		x = a;
	}
	
	public void setY(int a) {
		y = a;
	}
	
	public void setDist(int a) {
		dist = a;
	}
	
	public int getDist() {
		return dist;
	}
	
	public void setVisit() {
		visited = true;
	}
	
	public boolean getVisit() {
		return visited;
	}
	
	public void setPre(Node n) {
		pre = n;
	}
	
	public Node getPre() {
		return pre;
	}
	
	public void setSE(String a) {
		se = a;
	}
	
	public String getSE() {
		return se;
	}
}
