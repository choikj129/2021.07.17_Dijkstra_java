package dijkstra;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectMouse extends MouseAdapter{
	
	private MyFrame f;
	private Node n;

	public SelectMouse(MyFrame f) {
		this.f = f;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {		
		int x = e.getX();
		int y = e.getY();
		n = f.selectNode(x, y);
		
		if (e.getButton()==3 && n!=null) {
			f.popup(x,y);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (n!=null) {
			n.setX(x);
			n.setY(y);
			f.repaint();
		}
	}
	

}