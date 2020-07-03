//import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MenuButton extends JButton implements ActionListener {

	private Color c = Color.BLUE;
	private String text = "";
	private PuzzleFrame frame;

	public MenuButton(PuzzleFrame fr) {
		//this.c = c;
		frame = fr;
		this.addActionListener(this);
	}
	/*
	* The method paintComponent(Graphics g) is called when
	* java needs to draw the component
	*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(c);
		g.drawString(text,5,15);
	}

	public void beenHere() {
		//c = Color.gray;
		ImageIcon x = new ImageIcon("X.gif");
		// setIcon adds an image to a button
		this.setIcon(x);
		//this.setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		beenHere();

	}

	public void setText (String x) {
		text = x;
	}

}
