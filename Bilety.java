import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

public class Bilety extends JFrame {
	private JPanel jPanel;
	public JLabel biletyLabel;
	public MySql X = new MySql();
	public Bilety() {
		super("Bilety");
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
 		setVisible(true);
		jPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		biletyLabel = new JLabel("Zakupione bilety:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		jPanel.add(biletyLabel, constraints);
		
 		DefaultTableModel model = new DefaultTableModel(); 
 		
 		model.addColumn("ID_biletu");
 		model.addColumn("Numer biletu"); 
 		model.addColumn("Data_wystawienia");
 		model.addColumn("Id_rezerwacji");
 		JTable table = new JTable(model);
 		model.addRow(new Object[] {"ID_biletu", "Numer biletu", "Data_wystawienia", "Id_rezerwacji"});
 		
 		table.setPreferredSize(new Dimension(700, 50));
		constraints.gridx = 0;
		constraints.gridy = 1;
		
		jPanel.add(table, constraints);
				
 		add(jPanel);

 		pack();

 		setLocationRelativeTo(null); 
 	}
}