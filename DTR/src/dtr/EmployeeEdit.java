package dtr;
// NOTE ---- BEWARE OF THE METHODS SEQUENCE ---- 
import java.awt.EventQueue;

import javax.swing.JFrame;

public class EmployeeEdit {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeEdit window = new EmployeeEdit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeEdit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 738, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
