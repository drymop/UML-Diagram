import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	private JFrame frame;
	
	public void createAndShowGui() {
		frame = new JFrame("tuan");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MainGUI());
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println(Hello);
			}
		});
	}
	
}
