package TDS.AppChat;
import vista.Ventana;

public class App {
	//Open the program
	public static void main(String[] args) {
		try {
			Ventana window = new Ventana();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}