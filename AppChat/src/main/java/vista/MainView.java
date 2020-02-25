
package vista;

import javax.swing.JPanel;

import controlador.Controlador;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class MainView extends JPanel {

	public MainView() {
		setLayout(new BorderLayout(0, 0));

		JPanel sidebar = new JPanel();
		add(sidebar, BorderLayout.WEST);

		JPanel toolbar = new JPanel();
		add(toolbar, BorderLayout.NORTH);

		add(new ChatView(), BorderLayout.CENTER);
	}
}