import javax.swing.*;

public class MainC{
	public static void main (String args[]){
		GUI gui = new GUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.pack();
		gui.setTitle("Cube Solver");
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		gui.requestFocusInWindow();
	}
}
