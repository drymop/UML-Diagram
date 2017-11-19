import javax.swing.JButton;
import javax.swing.JPanel;

public class MainGUI extends JPanel {
	public MainGUI() {
		initComponents();
	}
	
	private void initComponents() {
		add(new JButton("Click me"));
	}
}
