import java.awt.event.ActionListener;


class SwitchToKurotto extends ChangePuzzle implements ActionListener {

	public SwitchToKurotto(PuzzleFrame an) {
		super(an);
	}

	@Override
	public void specialDo() {
		an.choosePuzzle(new SetKurotto(an));
		an.setFrame();
	}
}
