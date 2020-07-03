import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class ChangePuzzle implements ActionListener {

	PuzzleFrame an;

	public ChangePuzzle(PuzzleFrame x) {
		this.an = x;
	}

	public void specialDo() {
		an.choosePuzzle(new SetPuzzle(an));
	}

	// removes all the components are reinitializes the Frame
	public void actionPerformed(ActionEvent e) {
		an.getContentPane().removeAll();
		specialDo();
		an.validate();
		an.repaint();
	}
}
