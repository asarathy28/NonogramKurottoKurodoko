import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SpaceButton extends JButton implements ActionListener {

	private Color c = Color.gray;
	private Color multi;
	private Boolean picked;
	private GameInterface game;
	private PuzzleFrame frame;
	private int row;
	private int col;

	public SpaceButton(Color y, GameInterface g, PuzzleFrame fr, int r, int c) {
		this.multi = y;
		this.addActionListener(this);
		game = g;
		frame = fr;
		row = r;
		col = c;
		picked = game.isCellColored(row, col);

	}

	/*
	* The method paintComponent(Graphics g) is called when
	* java needs to draw the component
	*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x, y;
		setBackground(c);
		setOpaque(true);

		for (int i=0; i<30; i++) {
			for (int j=0; j<30; j++) {
				if (picked) {
						randomSet();
						c = multi;
				 }
				else c = Color.gray;
				g.setColor(c);
				x = 0 + (int) (j*20);
				y = 0 + (int) (i*20);
				g.fillOval(x,y,20,20);
			}
			//	x = 150 + (int) (150*Math.sin((i+offset)*Math.PI*2.0/number));
			//	y = 150 + (int) (150*Math.cos((i+offset)*Math.PI*2.0/number));



		}

	}
	public void randomSet() {

		multi = makeRandomColor();

	}


	// Math.random() returns a number between 0 and 1

	private Color makeRandomColor() {
		int red = (int) (Math.random()*255);
		int green = (int) (Math.random()*255);
		int blue = (int) (Math.random()*255);

		Color col = new Color(red,green,blue);
		return col;
	}

	public void beenHere() {
		randomSet();
		if (picked) {
			 c = multi;
			 game.makeCellDark(row, col);
		 }
		else {
			c = Color.gray;
			game.makeCellBlank(row, col);
		}

		//ImageIcon x = new ImageIcon("X.gif");
		// setIcon adds an image to a button
		//this.setIcon(x);
		//this.setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		picked = !picked;
		beenHere();
		frame.repaint();
	}

}
