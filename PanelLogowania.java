import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogowania extends JFrame implements ActionListener {
	private JPanel jPanel;
	private JLabel loginJLabel;
	private JLabel hasloJLabel;
	private JTextField loginTextField;
	private JPasswordField passwordField;
	private JButton loginButton;
	public MySql X = new MySql();
	public PanelLogowania() {
 		super("Panel Logowania");
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
 		setVisible(true);
		jPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		loginJLabel = new JLabel("Login:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		jPanel.add(loginJLabel, constraints);
		hasloJLabel = new JLabel("Has³o:");
		constraints.gridx = 0;
		constraints.gridy = 1;
		jPanel.add(hasloJLabel, constraints);
	
		loginTextField = new JTextField(20);
		constraints.gridx = 1;
		constraints.gridy = 0;
		jPanel.add(loginTextField, constraints);
		passwordField = new JPasswordField(20);
		constraints.gridy = 1;
		jPanel.add(passwordField, constraints);
	
		loginButton = new JButton("LOGIN");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		loginButton.addActionListener(this);
		jPanel.add(loginButton, constraints);
	
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		loginButton.setCursor(cursor);
	
 		add(jPanel);
 		pack();
 		setLocationRelativeTo(null);
 	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String loginInput = loginTextField.getText();
		String hasloInput = String.valueOf(passwordField.getPassword());
		if (e.getSource() == loginButton) {
			if (sprawdzLogowanie(loginInput, hasloInput)) {
			MenuGlowne A = new MenuGlowne();
			dispose();
			} else {
			BladLogowania Y = new BladLogowania();
			Y.setLocationRelativeTo(null);
			Y.setVisible(true);
			}
		}
		pack();
	}

private boolean sprawdzLogowanie(String login, String haslo) {
	String query = "SELECT COUNT(*) FROM operatorzy WHERE email ='" + login+ "' AND haslo ='" + haslo +"'";
	int stan = Integer.parseInt(X.executeQuery(query));
	if(stan == 1) {
		return true;
	}
	else {
		return false;
	}
}
}