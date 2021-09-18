package dijkstra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class MyFrame extends Frame{
	
	private ActionListener ac;
	private MouseListener ml;
	private MouseMotionListener mml;
	private NodeMouse nm;
	private EdgeMouse em;
	private SelectMouse sm;
	private PopupMenu pm;
	private Label l;
	private Node cur, cur0, start, end;
	private ArrayList<Node> nlist;
	private ArrayList<Edge> elist;
	private int dist, x, y;
	private boolean drag;
	
	public class WH extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	public MyFrame() {
		cur = null;
		cur0 = null;
		start = null;
		end = null;
		
		nlist = new ArrayList<Node>();
		elist = new ArrayList<Edge>();
		
		nm = new NodeMouse(this);
		em = new EdgeMouse(this);
		sm = new SelectMouse(this);
		
		addMouseListener(sm);
		addMouseMotionListener(sm);
		ml = sm;
		mml = sm;
		
		addWindowListener(new WH());
		
		setLayout(new BorderLayout());
		
		Panel pn = new Panel(new FlowLayout());
		
		Button bn = new Button("Node");
		pn.add(bn);
		ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeMouseListener(ml);
				removeMouseMotionListener(mml);
				ml = nm;
				mml = nm;
				addMouseListener(nm);
				addMouseMotionListener(nm);
			}
		};
		bn.addActionListener(ac);
		
		Button be = new Button("Edge");
		pn.add(be);
		ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeMouseListener(ml);
				removeMouseMotionListener(mml);
				ml = em;
				mml = em;
				addMouseListener(em);
				addMouseMotionListener(em);
			}
		};
		be.addActionListener(ac);
		
		Button bs = new Button("Select");
		pn.add(bs);
		ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeMouseListener(ml);
				removeMouseMotionListener(mml);
				ml = sm;
				mml = sm;
				addMouseListener(sm);
				addMouseMotionListener(sm);
			}
		};
		bs.addActionListener(ac);
		
		Button bcl = new Button("Clear");
		pn.add(bcl);
		ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeMouseListener(ml);
				removeMouseMotionListener(mml);
				
				nlist = new ArrayList<Node>();
				elist = new ArrayList<Edge>();
				
				cur = null;
				cur0 = null;
				start = null;
				end = null;
				repaint();
			}
		};
		bcl.addActionListener(ac);
		add(pn, BorderLayout.NORTH);
		
		Panel ps = new Panel(new FlowLayout());
		l = new Label("Shortest Path is not found");
		l.setFont(new Font("고딕",1,16));
		ps.add(l);
		add(ps, BorderLayout.SOUTH);
	}
	
	public void paint(Graphics g) {
		if (drag && cur!=null) {
			g.setColor(Color.pink);
			g.drawLine(cur.getX(), cur.getY(), x, y);
		}
//		Edge
		for (int i=0; i<elist.size(); i++) {
			elist.get(i).paint(g);
		}
//		Node
		for (int i=0; i<nlist.size(); i++) {
			Node n = nlist.get(i);
			n.paint(g);
			g.setColor(Color.black);
			g.setFont(new Font("고딕", 1, 25));
			if (i>8) {
				g.drawString(Integer.toString(i+1), n.getX()-15, n.getY()+10);
				continue;
			}
			g.drawString(Integer.toString(i+1), n.getX()-7, n.getY()+10);
		}
	}
	
	public void paintNode(int a, int b) {
		cur = new Node(a,b);
		nlist.add(cur);
		repaint();
	}
	
	public void removeNode() {
		nlist.remove(nlist.size()-1);
		repaint();
	}
	
	public Node selectNode(int a, int b) {
		for (int i=0; i<nlist.size(); i++) {
			Node n = nlist.get(i);
			int nx = n.getX();
			int ny = n.getY();
			if ((a>nx-25 && a<nx+25) && (b>ny-25 && b<ny+25)) {
				return n;
			}
		}
		return null;
	}
	
	public void setCur(int a, int b) {
		cur = selectNode(a,b);					
	}
	
	public void addEdge(int a, int b) {
		if (selectNode(a,b)!=null && cur!=null) {
			cur0 = selectNode(a,b);
			if (cur!=cur0) {
				elist.add(new Edge(cur, cur0));
			}
		}	
		repaint();
	}
	
	public void dragEdge(int a, int b) {
		drag = true;
		x = a;
		y = b;
		repaint();
	}

	public void removeEdge() {
		if (cur!=null && cur0!=null && elist.size()>0) {
			elist.remove(elist.size()-1);
		}
		repaint();
	}
	
	public Edge getEdge(Node n1, Node n2) {
		for (int i=0; i<elist.size(); i++) {
			Node a = elist.get(i).getN1();
			Node b = elist.get(i).getN2();
			if ((a==n1 && b==n2) || (a==n2 && b==n1)) {	
				return elist.get(i);
			}
		}
		return null;
	}
	
	public void paintDist(String dist) {
		if (cur!= null && dist!=null) {
			elist.get(elist.size()-1).setDist(dist);
			repaint();
		}
	}

	public void resetSE(String a) {
		for (int i=0; i<nlist.size(); i++) {
			if (nlist.get(i).getSE()==a) {
				nlist.get(i).setSE(null);
			}
		}
	}
	
	public void setDragf() {
		drag = false;
	}
	
	public void shortest() {
		start.setDist(0);
		Node ncur = start;
		while (true) {
			ncur.setVisit();
			if (end.getVisit()) {
				break;
			}
			int d = ncur.getDist();
			int min = 999;
			
			for (int i=0; i<nlist.size(); i++) {
				Node n = nlist.get(i);
				if (n.getVisit()) {
					continue;
				}
				int ndist = n.getDist();
				
				if (getEdge(ncur,n)!=null) {
					dist = Integer.parseInt(getEdge(ncur,n).getDist());
					if (d+dist<ndist) {
						ndist = d+dist;
						n.setDist(ndist);
						n.setPre(ncur);
					}
				}
				
				if (ndist<min) {
					min = ndist;
					cur0 = n;
				}
			}
			ncur = cur0;
		}
		
		ncur = end;
		while (ncur.getPre()!=null) {
			cur0 = ncur.getPre();
			Edge e = getEdge(cur0,ncur);
			e.setSP(true);
			ncur = cur0;
		}
		l.setText("Shortest Path is       "+end.getDist());
	}
	
	public void popup(int a, int b) {
		setCur(a,b);
		pm = new PopupMenu();
		
		MenuItem ms = new MenuItem("Start");
		pm.add(ms);
		ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetSE("start");
			    start = cur;
			    start.setSE("start");
			    repaint();
			}		
		};
		ms.addActionListener(ac);
		
		MenuItem me = new MenuItem("End");
		pm.add(me);
		ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetSE("end");
				end = cur;
				end.setSE("end");
				repaint();
			}		
		};
		me.addActionListener(ac);
		
		MenuItem msp = new MenuItem("ShortPath");
		pm.add(msp);
		ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shortest();
				repaint();
			}		
		};
		msp.addActionListener(ac);
		
		pm.addSeparator();
		MenuItem mr = new MenuItem("Reset");
		pm.add(mr);
		ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start = null;
				end = null;
				l.setText("Shortest Path is not found");
				for (int i=0; i<nlist.size(); i++) {
					nlist.get(i).setSE(null);
				}
				for (int i=0; i<elist.size(); i++) {
					elist.get(i).setSP(false);
				}
				repaint();
			}		
		};
		mr.addActionListener(ac);	
		
		add(pm);
		pm.show(this, cur.getX(), cur.getY());
	}

}
