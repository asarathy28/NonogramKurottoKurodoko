import java.awt.*;


public class runProject {

	public static void main(String[] args) {

		//PuzzleFrame frame = new PuzzleFrame();
		//frame.setUpFrame();
		PuzzleFrame frame = new PuzzleFrame();
		frame.choosePuzzle(new SetKurotto(frame));
		frame.setFrame();

		frame.pack();
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(600,600));
		frame.repaint();

		// this method must finish and return before GUI
		// handling begins
	}

}
