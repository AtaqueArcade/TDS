package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

public class ContactView extends JPanel {
	private final Map<String, ImageIcon> imageMap;

	public ContactView() {
		super(new BorderLayout());
		// TODO recuperar lista de contactos para mostrarla
		String[] nameList = { "Bojack", "Diane", "Caroline", "Todd", "Peanutbutter" };
		imageMap = createImageMap(nameList);
		JList list = new JList(nameList);
		list.setSelectedIndex(0);
		list.setBackground(Color.LIGHT_GRAY);
		list.setCellRenderer(new ListRenderer());

		list.addListSelectionListener(e -> {
			//TODO cargar mensajes de cada chat
			System.out.println(list.getSelectedValue());
		});
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(300, 400));
		this.add(scroll);
		this.setVisible(true);

	}

	public class ListRenderer extends DefaultListCellRenderer {

		Font font = new Font("helvitica", Font.PLAIN, 16);
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			label.setIcon(imageMap.get((String) value));
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setFont(font);
			return label;
		}
	}

	private Map<String, ImageIcon> createImageMap(String[] list) {
		//TODO loop for contacts
		Map<String, ImageIcon> map = new HashMap<>();
		try {
			ImageIcon imageIcon = new ImageIcon(new URL("https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);
			map.put("Bojack", imageIcon);
			map.put("Diane", imageIcon);
			map.put("Caroline", imageIcon);
			map.put("Todd", imageIcon);
			map.put("Peanutbutter", imageIcon);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}
}