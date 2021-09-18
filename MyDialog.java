package dijkstra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyDialog extends Dialog{

	private MyFrame f;
	private String id;

	
	public class WH extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			f.removeEdge();
			dispose();
		}
	}
	
	public MyDialog(MyFrame f) {
		super(f, "My Dialog", true);
		this.f = f;
		
		addWindowListener(new WH());
		setLayout(new BorderLayout());
		TextField tf = new TextField();
		add(tf, BorderLayout.CENTER);
		
		Panel p = new Panel(new FlowLayout());
		add(p, BorderLayout.SOUTH);
		
		Button b1 = new Button("OK");
		p.add(b1);
		b1.setPreferredSize(new Dimension(50,20));
		ActionListener ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = tf.getText();

				if (id.length()>0) {
					f.paintDist(id);
					dispose();
				}
				
			}
		};
		b1.addActionListener(ac);
		
		Button b2 = new Button("CANCEL");
		p.add(b2);
		b2.setPreferredSize(new Dimension(50,20));
		ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.removeEdge();		
				dispose();
			}
		};
		b2.addActionListener(ac);
	}
	

}
