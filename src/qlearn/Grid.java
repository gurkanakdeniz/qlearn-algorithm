package qlearn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;


public class Grid extends JPanel {
	Color background;
	
	int labirentboyut=(int) Math.sqrt(QlearnMain.labirent.size());
	static int secimsay=0;
	static int baslangic=-1;
	static int hedef=-1;
	
	
	//baslangic ve bitis almak icin kullanılan kurucu
	public Grid(){
		setLayout(new GridBagLayout());
		
        GridBagConstraints gbc = new GridBagConstraints();
        
        for (int i = 0; i <labirentboyut ; i++) {
            for (int j = 0; j < labirentboyut; j++) {
                gbc.gridx = j;
                gbc.gridy = i;

                JPanel panel = new JPanel();
                //PanelSecim panel = new PanelSecim();
                panel.setPreferredSize(new Dimension(70, 70));
		
                Border border = null;                
                
                int yan=1;
                int alt=1;
                Dugum kutu= new Dugum();
                Vector <Integer> kutukomsu;
                
                kutu= QlearnMain.labirent.get(labirentboyut*i+j);
                kutukomsu = kutu.getkomsu();
                
                for(int a=0;a<kutukomsu.size();a++){
                	
                	//kutu yan tarafla komsu mu?
                	if(kutukomsu.get(a)==(labirentboyut*i+j+1)){
                		yan=0; // yana cizme
                	}
                	
                	//kutu alt tarafla komsu mu?
                	if(kutukomsu.get(a)==(labirentboyut*i+j+labirentboyut)){
                		alt=0; //alta cizme
                	}  	
                	
                }
                
                
                border = new CompoundBorder();
                //en üst kenarlarık için çerçeve
                if(i==0){
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));
                }
                //en sol kenarlık için çerçeve
                if(j==0){
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK));
                }
                
                if(yan==0&&alt==0){
                	
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 0, 3, getBackground()));
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 3, 0, getBackground()));
                	
                }else if(yan==0&&alt==1){
                	
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 0, 3, getBackground()));
          
                }else if(yan==1&&alt==0){
                	
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK));
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 3, 0, getBackground()));
                	
                }else if(yan==1&&alt==1){
                	
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK));
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
                	
                }else{
                	System.out.println("grid olusurken hata");
                }
                
                //oda numaraları
                String numara=""+(labirentboyut*i+j);
                
                panel.add(new JLabel(numara));
                
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                 	   background = panel.getBackground();
                 	   
                 	   if(background==Color.RED || background==Color.GREEN){
                 		   //bir sey yapma
                 	   }else{
                 		   panel.setBackground(Color.MAGENTA); 
                 	   }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                 	   if(background!=Color.RED)
                 		   panel.setBackground(background);
                    }
                    
                    @Override
                    public void mouseClicked(MouseEvent e){
                 	   if(secimsay==0){
                 		   panel.setBackground(Color.GREEN);
                 		   background = panel.getBackground();
                 		   
                 		   baslangic= Integer.parseInt(numara);
                 		   System.out.println("bas:"+baslangic);
                     	   secimsay++;
                 	   }else if(Grid.secimsay==1){
                 		   panel.setBackground(Color.RED);
                 		   background = panel.getBackground();
                 		   
                 		   hedef = Integer.parseInt(numara);
                		   System.out.println("bit:"+hedef);
                     	   secimsay++;
                 	   }
                 	   
                    }
                });            
            	
                panel.setBorder(border);
                add(panel, gbc);
            }
        }
		
       
		
		
	}
	
	//cizdirme icin kullanılan kurucu
	public Grid (int te){
		setLayout(new GridBagLayout());
		
        GridBagConstraints gbc = new GridBagConstraints();
        
        for (int i = 0; i <labirentboyut ; i++) {
            for (int j = 0; j < labirentboyut; j++) {
                gbc.gridx = j;
                gbc.gridy = i;

                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(70, 70));
		
                Border border = null;
                
            	//yol vectorunda kutu varsa arkaplanı degistir
                if(QlearnMain.yolvector.contains(labirentboyut*i+j)){
                	
                	String numara=""+(labirentboyut*i+j);
                	panel.add(new JLabel(numara));
                	
            		if((labirentboyut*i+j)==QlearnMain.testbaslangic){
            			 panel.setBackground(Color.GREEN);         		
            		}else if((labirentboyut*i+j)==QlearnMain.testhedef){
            			panel.setBackground(Color.RED);
            		}else{
            			panel.setBackground(Color.ORANGE);  
            		}
            		
            	}            	
                
                int yan=1;
                int alt=1;
                Dugum kutu= new Dugum();
                Vector <Integer> kutukomsu;
                
                kutu= QlearnMain.labirent.get(labirentboyut*i+j);
                kutukomsu = kutu.getkomsu();
                
                for(int a=0;a<kutukomsu.size();a++){
                	
                	//kutu yan tarafla komsu mu?
                	if(kutukomsu.get(a)==(labirentboyut*i+j+1)){
                		yan=0;
                		//System.out.println("yana çizme");
                	}
                	
                	//kutu alt tarafla komsu mu?
                	if(kutukomsu.get(a)==(labirentboyut*i+j+labirentboyut)){
                		alt=0;
                	}
                	
                	
                }
                
                border = new CompoundBorder();
                //en üst kenarlarık için çerçeve
                if(i==0){
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));
                }
                //en sol kenarlık için çerçeve
                if(j==0){
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK));
                }
                
            	//0, 0, 0, 1  yan taraf
                //0, 0, 1, 0, alt taraf
                //0 yok 1 var
                if(yan==0&&alt==0){
                	
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 0, 3, getBackground()));
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 3, 0, getBackground()));
                	
                }else if(yan==0&&alt==1){
                	
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 0, 3, getBackground()));
          
                }else if(yan==1&&alt==0){
                	
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK));
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 3, 0, getBackground()));
                	
                }else if(yan==1&&alt==1){
                	
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK));
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
                	
                }else{
                	System.out.println("hata");
                }
            	
                panel.setBorder(border);
                add(panel, gbc);
            }
        }
		

		
	}
	
	

}
