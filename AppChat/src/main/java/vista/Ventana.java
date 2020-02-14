package vista;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Ventana {
	public static JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Ventana() {
			//Loads the acces view by default
			frame = new JFrame();
			frame.setTitle("AppChat: User access");
			frame.setSize(new Dimension(400, 250));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(new LoginView());
			frame.setVisible(true);
	}
	public static void loadAppView(){
		//Frees the loaded panel
		frame.getContentPane().removeAll();
		frame.setVisible(false);
		frame.dispose();
		//Loads the main interface
		frame = new JFrame();
		frame.setTitle("AppChat");
		frame.setSize(new Dimension(1000, 750));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new MainView());
		frame.setVisible(true);
	}
}
