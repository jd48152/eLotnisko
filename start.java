import java.awt.EventQueue;

public class start {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new PanelLogowania();
			}
		});
	}
}
