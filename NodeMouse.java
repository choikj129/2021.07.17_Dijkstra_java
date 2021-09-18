package dijkstra;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NodeMouse extends MouseAdapter{
	
	private MyFrame f;

	public NodeMouse(MyFrame f) {
		this.f = f;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {		
		int x = e.getX();
		int y = e.getY();
		f.paintNode(x, y);
		
		f.selectNode(x, y);
		
	}
	

}
