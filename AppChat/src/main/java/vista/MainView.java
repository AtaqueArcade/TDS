package vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.net.MalformedURLException;

@SuppressWarnings("serial")
public class MainView extends JPanel {
	private ToolBarView toolbar;
	private ContactView sidebar;
	private ChatView chat;

	public MainView()
			throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		setLayout(new BorderLayout(0, 0));
		sidebar = new ContactView();
		add(sidebar, BorderLayout.WEST);
		toolbar = new ToolBarView();
		add(toolbar, BorderLayout.NORTH);
		chat = new ChatView();
		add(chat, BorderLayout.CENTER);
	}
}