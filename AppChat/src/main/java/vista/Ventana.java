package vista;

import java.awt.Dimension;
import java.net.MalformedURLException;
import javax.swing.JFrame;
import javax.swing.UIManager;

import controlador.CatalogoUsuarios;

public class Ventana {
	public static JFrame frame;

	public Ventana() {
		// Loads the acces view by default
		if (frame != null) {
			frame.getContentPane().removeAll();
			frame.setVisible(false);
			frame.dispose();
		}
		frame = new JFrame();
		frame.setTitle("AppChat: User access");
		frame.setSize(new Dimension(400, 250));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new LoginView());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadAppView()
			throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// Frees the loaded panel
		frame.getContentPane().removeAll();
		frame.setVisible(false);
		frame.dispose();
		// Loads the main interface
		frame = new JFrame();
		frame.setTitle("AppChat");
		frame.setSize(new Dimension(1000, 750));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new MainView());
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

}
