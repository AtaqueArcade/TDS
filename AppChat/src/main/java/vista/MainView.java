
package vista;

import javax.swing.JPanel;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.net.MalformedURLException;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class MainView extends JPanel {

	public MainView() throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		setLayout(new BorderLayout(0, 0));

		ContactView sidebar = new ContactView();
		add(sidebar, BorderLayout.WEST);

		ToolBarView toolbar = new ToolBarView();
		add(toolbar, BorderLayout.NORTH);
	
		add(new ChatView(), BorderLayout.CENTER);
	}
}