import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuGlowne extends JFrame {
	private JPanel jPanel;
	public MySql X = new MySql();
	public MenuGlowne() {
 		super("Panel Logowania");
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
 		setVisible(true);
		jPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(10, 10, 10, 10);

 		DefaultTableModel model = new DefaultTableModel(); 
 		
 		String zapytanie1="SELECT LotniskoStartowe From loty_ WHERE ID_loty_=1";
 		ArrayList<String> wynik1=X.getTable("LotniskoStartowe"); 
 		ArrayList<String> wynik2=X.getTable("LotniskoDocelowe"); 
 		ArrayList<String> wynik3=X.getTable("Godzina_Odlotu"); 
 		ArrayList<String> wynik4=X.getTable("Godzina_Przylotu"); 
 		ArrayList<String> wynik5=X.getTable("Cena_brutto"); 
 		System.out.print(wynik1);
 		
 		model.addColumn("Lotnisko Startowe"); 
 		model.addColumn("Lotnisko Docelowe");
 		model.addColumn("Godzina Odlotu");
 		model.addColumn("Godzina Przylotu");
 		model.addColumn("Cena (PLN)");
 		JTable table = new JTable(model);
 		model.addRow(new Object[] {"Lotnisko Startowe", "Lotnisko Docelowe", "Godzina Odlotu", "Godzina Przylotu", "Cena (PLN)"});
 		for (int i=0; i<wynik1.size(); i++) {
 			model.addRow(new Object[] {wynik1.get(i),wynik2.get(i), wynik3.get(i), wynik4.get(i), wynik5.get(i)});
 		}
 		
 		table.setPreferredSize(new Dimension(600, 200));
		constraints.gridx = 0;
		constraints.gridy = 0;
		jPanel.add(table, constraints);
		
 		add(jPanel);
 		pack();

 		setLocationRelativeTo(null); 		
 	}
}