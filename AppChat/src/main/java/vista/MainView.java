
package vista;

import javax.swing.JPanel;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MainView extends JPanel {

	public MainView() {
		setLayout(new BorderLayout(0, 0));

		ContactView sidebar = new ContactView();
		add(sidebar, BorderLayout.WEST);

		JPanel toolbar = new JPanel();
		add(toolbar, BorderLayout.NORTH);
		
		JButton btnNewButton_3 = new JButton("New button");
		toolbar.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("New button");
		toolbar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		toolbar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		toolbar.add(btnNewButton_2);

		add(new ChatView(), BorderLayout.CENTER);
	}
}