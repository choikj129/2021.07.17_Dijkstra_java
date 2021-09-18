package dijkstra;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Edge {
	private Node n1, n2;
	private boolean sp;
	private String dist = null;
	
	public Edge() {
		
	}
	
	public Edge(Node n1, Node n2) {
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,0));
		if (sp) {
			g2.setColor(Color.red);
			g2.drawLine(n1.getX(), n1.getY(), n2.getX(), n2.getY());
		}
		
		else {
			g.setColor(Color.black);
			g.drawLine(n1.getX(), n1.getY(), n2.getX(), n2.getY());
		}
		
		if (dist!=null) {
			int mx = Math.abs(n1.getX()-n2.getX())/2;
			int my = Math.abs(n1.getY()-n2.getY())/2;
			if (n1.getX()-n2.getX()>0) {
				mx *= -1;				
			}
			if (n1.getY()-n2.getY()>0) {
				my *= -1;				
			}
			g.setColor(Color.blue);
			g.setFont(new Font("고딕", 1, 18));
			g.drawString(dist, n2.getX()-mx, n2.getY()-my);
		}
	}
	
	public Node getN1() {
		return n1;
	}
	
	public Node getN2() {
		return n2;
	}
	
	public void setDist(String a) {
		dist = a;
	}
	
	public String getDist() {
		return dist;
	}
	
	public void setSP(boolean a) {
		sp = a;
	}
}
