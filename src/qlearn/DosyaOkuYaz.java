package qlearn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class DosyaOkuYaz {
	
	static DecimalFormat qformat = new DecimalFormat("#0.00");
	
	public static void DosyalariOlustur(String dosyaAdi){
	    File f = new File(dosyaAdi);
	    try {
	    	
	        if(!f.exists()){ 
	            f.createNewFile(); 
	        }
	        
	    } catch (IOException e) { 
	        // TODO: handle exception
	        e.printStackTrace();
	    }
	}
	
	public static void DosyaYolYaz(String dosyaAdi){
	    File file = new File(dosyaAdi); 
	        try {
	        	
	            FileOutputStream fos = new FileOutputStream(file);
	            String metin = "";
	        	
	            for(int i=0;i<QlearnMain.yolvector.size();i++){
	            	metin += QlearnMain.yolvector.get(i)+" ";			
	    		}
	            
	            fos.write(metin.getBytes());
	            fos.flush();
	            fos.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
	public static void DosyaRYaz(String dosyaAdi){
	    File file = new File(dosyaAdi);
	        try {
	        	
	            FileOutputStream fos = new FileOutputStream(file);
	            String metin = "";
	        	
	        	//duzenli r yazdirma
	        	for(int i=0;i<QlearnMain.R.length;i++){
	        		for(int j=0;j<QlearnMain.R.length;j++){
	        			metin += QlearnMain.R[i][j]+" ";
	        		}
	        		metin += "\n";
	        	}
	        	
	            fos.write(metin.getBytes());
	            fos.flush();
	            fos.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
	public static void DosyaQYaz(String dosyaAdi){
	    File file = new File(dosyaAdi); 
	        try {
	        	        	
	            FileOutputStream fos = new FileOutputStream(file);
	            
	            String metin = "";
	        	
	        	//duzenli q yazdirma
	        	for(int i=0;i<QlearnMain.Q.length;i++){
	        		for(int j=0;j<QlearnMain.Q.length;j++){
	        			//metin += QlearnMain.Q[i][j]+" ";
	        			//metin += qformat.format(QlearnMain.Q[i][j])+" ";
	        			if(QlearnMain.Q[i][j]==0){
	        				metin += 0+" ";
	        			}else{
	        				metin += (Math.round(QlearnMain.Q[i][j]*100)/100.0)+" ";
	        			}
	        			
	        		}
	        		metin += "\n";
	        	}
	            		
	            fos.write(metin.getBytes());
	            fos.flush();
	            fos.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
    public static void DosyaScanner(String dosyaAdi){
        File file = new File(dosyaAdi);
        try {
 
            Scanner scanner = new Scanner(file);
 
            int i=0;
            while(scanner.hasNextLine()){
                
            	//satir oku
                String satir= scanner.nextLine();
                //virgülleri temizle
                String[] numaralars = satir.split(",");
                
                //virgülden kurtuldugun komsuları int yap
                int [] numaralar= new int[numaralars.length];
   			 	for(int a=0;a<numaralars.length;a++){
   			 		numaralar[a]=Integer.parseInt(numaralars[a]);
   			 	}
   			 	
   			 	//yeni dugum olustur
   			 	QlearnMain.labirent.add(new Dugum());
   			    QlearnMain.labirent.get(i).setIndeks(i);
   			 	
   			    //olusan dugume komsuları yerlestir
   			    for(int a=0;a<numaralar.length;a++){    
   			 		QlearnMain.labirent.get(i).addkomsu(numaralar[a]);
   			 	}
   			    
   			    // okunan satirin numarasi
                i++;
            }
 
            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Dosya Yok..");
        }
 
    }

}
