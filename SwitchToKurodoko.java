import java.awt.event.ActionListener;


class SwitchToKurodoko extends ChangePuzzle implements ActionListener {

	public SwitchToKurodoko(PuzzleFrame an) {
		super(an);
	}

	@Override
	public void specialDo() {
		an.choosePuzzle(new SetKurodoko(an));
		an.setFrame();
	}
}
