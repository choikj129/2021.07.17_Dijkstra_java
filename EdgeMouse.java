package dijkstra;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EdgeMouse extends MouseAdapter{
	
	private MyFrame f;
	private Node n;

	public EdgeMouse(MyFrame f) {
		this.f = f;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		f.setCur(x, y);
		n = f.selectNode(x, y);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();		
		f.dragEdge(x, y);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		f.setDragf();
		int x = e.getX();
		int y = e.getY();
		Node n0 = f.selectNode(x, y);
		Edge ed = f.getEdge(n, n0);
		if (ed==null) {
			f.addEdge(x, y);
			if (n0!=null && n!=null && n!=n0) {
				MyDialog d = new MyDialog(f);
				d.setSize(200,150);
				d.setLocation(f.getX()+x+10, f.getY()+y+10);
				d.setVisible(true);
			}	
		}
		f.repaint();
	}

}
