package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {
	private static final long serialVersionUID = -2135190122896866375L;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5 = new JButton("Play");
	private JButton b6 = new JButton("Back");
	private JPanel pnl = new JPanel(new GridLayout(4, 1, 20, 20));

	public Menu() {
		JFrame c = new JFrame();
		b1 = new JButton("Start");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnl.removeAll();
				pnl.revalidate();
				pnl.repaint();
				pnl.add(b5);
				b5.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pnl.removeAll();
						pnl.revalidate();
						pnl.repaint();
					}
				});

				pnl.add(b6);
				b6.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pnl.removeAll();
						pnl.revalidate();
						pnl.repaint();
						pnl.add(b1);
						pnl.add(b2);
						pnl.add(b3);
						pnl.add(b4);
					}
				});
			}
		});
		//b1.setBounds(80,40, 40, 40);

		b2 = new JButton("Edit");
		b2.addActionListener(new ButtonListener1());
		//b2.setBounds(80, 40, 40, 40);

		b3 = new JButton("Options");
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		b4 = new JButton("Exit");
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		pnl.setSize(new Dimension(400, 600));
		pnl.add(b1);
		pnl.add(b2);
		pnl.add(b3);
		pnl.add(b4);
		this.pack();
		c.add(pnl);
		c.setVisible(true); 
		c.setSize(480, 640);
		c.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private class ButtonListener1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}

	public static void main(String[] args) {
		new Menu();


	}




}
