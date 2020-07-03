import java.awt.event.ActionListener;


class SwitchToPBN extends ChangePuzzle implements ActionListener {

	public SwitchToPBN(PuzzleFrame an) {
		super(an);
	}

	@Override
	public void specialDo() {
		an.choosePuzzle(new SetPaintByNumber(an));
		an.setFrame();
	}
}
