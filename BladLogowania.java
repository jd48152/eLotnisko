import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BladLogowania extends JDialog implements ActionListener {
	private JButton okButton;
	public BladLogowania() {
		setTitle("Blad logowania");
		setLocationRelativeTo(null);
		JPanel jPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		JLabel bladPowiadomienie = new JLabel("B³êdny login lub has³o.");
		constraints.gridx = 0;
		constraints.gridy = 0;
		jPanel.add(bladPowiadomienie, constraints);
		
		okButton = new JButton("OK");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		okButton.addActionListener(this);
		jPanel.add(okButton, constraints);
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		okButton.setCursor(cursor);
	
		add(jPanel);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == okButton) {
			dispose();
		}
	}
}