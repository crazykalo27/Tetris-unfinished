import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TetrisFrame extends JFrame{
	
	TetrisFrame()
	{
		TetrisPanel panel = new TetrisPanel();
		this.add(panel);
		this.setTitle("Tetrisy");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		//puts it in the center
		this.setLocationRelativeTo(null);
	}

}
