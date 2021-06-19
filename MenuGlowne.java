import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MenuGlowne extends JFrame implements ActionListener {
	private JPanel jPanel;
	public JLabel lotyLabel;
	private JButton rezerwacjaButton;
	private JButton biletyButton;
	private JButton anulowanieButton;
	public MySql X = new MySql();
	public MenuGlowne() {
 		super("Menu G³ówne");
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
 		setVisible(true);
		jPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		lotyLabel = new JLabel("Dostêpne po³¹czenia:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		jPanel.add(lotyLabel, constraints);
		
 		DefaultTableModel model = new DefaultTableModel(); 
 		
 		String query1 = "SELECT LotniskoStartowe, Nazwa_lotniska FROM loty, lotniska WHERE LotniskoStartowe=lotniska.Id_lotniska";
 		ArrayList<String> wynik1=X.executeQuery1(query1);
 		String query2 = "SELECT LotniskoDocelowe, Nazwa_lotniska FROM loty, lotniska WHERE LotniskoDocelowe=lotniska.Id_lotniska";
 		ArrayList<String> wynik2=X.executeQuery1(query2);
 		ArrayList<String> wynik3=X.getTable("CzasOdlotu", "loty"); 
 		ArrayList<String> wynik4=X.getTable("CzasPrzylotu", "loty"); 
 		ArrayList<String> wynik5=X.getTable("CenaBrutto", "loty");		
 		
 		model.addColumn("ID_polaczenia");
 		model.addColumn("Lotnisko Startowe"); 
 		model.addColumn("Lotnisko Docelowe");
 		model.addColumn("Godzina Odlotu");
 		model.addColumn("Godzina Przylotu");
 		model.addColumn("Cena (PLN)");
 		JTable table = new JTable(model);
 		model.addRow(new Object[] {"ID_polaczenia", "Lotnisko Startowe", "Lotnisko Docelowe", "Godzina Odlotu", "Godzina Przylotu", "Cena (PLN)"});
 		for (int i=0; i<wynik1.size(); i++) {
 			model.addRow(new Object[] {i+1, wynik1.get(i),wynik2.get(i), wynik3.get(i), wynik4.get(i), wynik5.get(i)});
 		}
 		
 		table.setPreferredSize(new Dimension(900, 50));
		constraints.gridx = 0;
		constraints.gridy = 1;
		
		jPanel.add(table, constraints);
		
		rezerwacjaButton = new JButton("Rezerwacje");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		rezerwacjaButton.addActionListener(this);
		jPanel.add(rezerwacjaButton, constraints);
		
		biletyButton = new JButton("Bilety");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.WEST;
		biletyButton.addActionListener(this);
		jPanel.add(biletyButton, constraints);
		
		anulowanieButton = new JButton("Anulowanie biletu/rezerwacji");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.EAST;
		anulowanieButton.addActionListener(this);
		jPanel.add(anulowanieButton, constraints);
		
 		add(jPanel);

 		pack();

 		setLocationRelativeTo(null); 		
 	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == biletyButton) {
			Bilety A = new Bilety();
			dispose();
			} 
		else if (e.getSource() == rezerwacjaButton) {
			Bilety A = new Bilety();
			dispose();
			}
		else if (e.getSource() == anulowanieButton) {
			Bilety A = new Bilety();
			dispose();
			}
	}
}