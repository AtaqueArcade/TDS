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
					frame.setContentPane(new VistaLogin());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Ventana() {
		frame = new JFrame();
		frame.setTitle("AppChat");
		frame.getContentPane().setSize(new Dimension(800, 250));
		frame.setSize(new Dimension(400, 250));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
