import java.awt.*;

import javax.swing.JPanel;


//
// JPanels are useful as components to draw on
//

public class CellPanel extends JPanel {

	private Color col;
	private Color col2;
	private String words;

	public CellPanel() {
		// set size, otherwise Java will assume 0 x 0 and component may disappear

		setPreferredSize(new Dimension(300,300));
		col2 = makeRandomColor();
		words = "";

	}

	public void setWords(String x){
		words = x;
	}

	public void randomSet() {

		col = makeRandomColor();

	}
	/*
	 * This method is called whenever java wants to draw the panel
	 * Change this to change the appearance of the panel
	 *
	 */
	public void paintComponent(Graphics g) {

		col = makeRandomColor();
		g.setColor(col);

		g.drawString(words,5,15);;
		col2 = makeRandomColor();
		g.setColor(col2);

		//g.fillOval(10,10,60,20);

	}

	// Math.random() returns a number between 0 and 1

	private Color makeRandomColor() {
		int red = (int) (Math.random()*255);
		int green = (int) (Math.random()*255);
		int blue = (int) (Math.random()*255);

		Color col = new Color(red,green,blue);
		return col;
	}

}
