package qlearn;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;


public class Gi extends JFrame {

	private JPanel contentPane;
	static Grid secimgrid;
	static Grid cizimgrid;

	public Gi() {
		//R[0][0]=1;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 600, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(splitPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
		);
		
		//secim grid ekle
		secimgrid = new Grid();
		splitPane.setRightComponent(secimgrid);

		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(3000), new Integer(1), new Integer(100000), new Integer(100)));
		
		JButton gosterButton = new JButton("Göster");
		gosterButton.setBackground(Color.GRAY);
		
		gosterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(Grid.baslangic!=-1 && Grid.hedef!=-1){
					QlearnMain.testiterasyon = (int)spinner.getValue();
					QlearnMain.testbaslangic = Grid.baslangic;
					QlearnMain.testhedef = Grid.hedef;
					QlearnMain.coz();
					
					//cizdirmek icin grid ekle
					
					cizimgrid = new Grid(1);
					splitPane.setRightComponent(cizimgrid);
					secimgrid.removeAll();
				}else{
					//System.out.println("baslangic ya da bitis secilmemis");
					JOptionPane.showMessageDialog(
					        null, "baslangic veya bitis secilmemis", "Baslangic - Bitis", JOptionPane.ERROR_MESSAGE);
				}

				
				
			}
		});
		
		
		
		JButton temizleButton = new JButton("Temizle");
		temizleButton.setBackground(Color.GRAY);
		temizleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(!QlearnMain.yolvector.isEmpty()){
					//eski hedef ve yol temizle
					QlearnMain.labirent.get(Grid.hedef).getkomsu().remove(
							(QlearnMain.labirent.get(Grid.hedef).getkomsu().size()-1)
							);
					QlearnMain.yolvector.removeAllElements();
					
					//baslangic ve bitis temizle
					Grid.baslangic = -1;
					Grid.hedef = -1;
					Grid.secimsay = 0;
					secimgrid = new Grid();
					splitPane.setRightComponent(secimgrid);
					cizimgrid.removeAll();
				}else{
					JOptionPane.showMessageDialog(
					        null, "temizlenecek yol yok", "Temizle", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		

		
		JTextPane txtpnIterasyonSays = new JTextPane();
		txtpnIterasyonSays.setEditable(false);
		txtpnIterasyonSays.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtpnIterasyonSays.setText("iterasyon sayısı:");
		txtpnIterasyonSays.setBackground(getBackground());
		
		JButton qDosyaButton = new JButton("Q Dosyası");
		qDosyaButton.setBackground(Color.LIGHT_GRAY);
		//btnNewButton_2.setEnabled(false);
		qDosyaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Desktop.isDesktopSupported()) {
				    try {
						Desktop.getDesktop().open(new File("outQ.txt"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(
					        null, "Desteklenmiyor", "Q Dosya Aç", JOptionPane.ERROR_MESSAGE);
				}
								
			}
		});
		
		JButton rDosyaButton = new JButton("R Dosyası");
		rDosyaButton.setBackground(Color.LIGHT_GRAY);
		
		rDosyaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Desktop.isDesktopSupported()) {
				    try {
						Desktop.getDesktop().open(new File("outR.txt"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(
					        null, "Desteklenmiyor", "R Dosya Aç", JOptionPane.ERROR_MESSAGE);
				}
								
			}
		});
		
		
		
		JButton pathDosyaButton = new JButton("Path Dosyası");
		pathDosyaButton.setBackground(Color.LIGHT_GRAY);
		
		pathDosyaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Desktop.isDesktopSupported()) {
				    try {
						Desktop.getDesktop().open(new File("outPath.txt"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(
					        null, "Desteklenmiyor", "Path Dosya Aç", JOptionPane.ERROR_MESSAGE);
				}
								
			}
		});
		
		
		
	
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("SansSerif", Font.BOLD, 12));
		textPane.setText("BASLANGIC");
		textPane.setBackground(Color.GREEN);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		textPane_1.setText("YOL");
		textPane_1.setBackground(Color.ORANGE);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		textPane_2.setText("HEDEF");
		textPane_2.setBackground(Color.RED);
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addComponent(textPane_2, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addComponent(temizleButton, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addComponent(gosterButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addComponent(spinner, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addComponent(txtpnIterasyonSays, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addComponent(textPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addComponent(textPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addComponent(qDosyaButton, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addComponent(rDosyaButton, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addComponent(pathDosyaButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(24)
						.addComponent(txtpnIterasyonSays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGap(27)
						.addComponent(gosterButton)
						.addGap(18)
						.addComponent(temizleButton)
						.addGap(38)
						.addComponent(qDosyaButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rDosyaButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pathDosyaButton)
						.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(14))
			);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
	}
}