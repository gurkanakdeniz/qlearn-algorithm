package qlearn;

import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

//input dosyasını arayüz üzerinden al
public class QlearnMain {
	
	static final float ogrenmeKatsayi=(float) 0.8;
	static Vector<Dugum>  labirent = new Vector<Dugum>();
	static int R[][];
	static double Q[][];
	
	static Vector<Integer> yolvector=new Vector<Integer>();
	//hesap yaparken dugumlerin komsularını tutacak vector
	static Vector <Integer> komsu;
	
	static DecimalFormat dformat = new DecimalFormat("0.00");
	  
	static int testbaslangic=0;
	static int testhedef=0;
	static int testiterasyon=3000;
	
	public static void coz(){
		
		int testdurum=testbaslangic;
		int testsonrakidurum=0;
		
		
		//hedef belirlendikten sonra hedeften kendisine yol ekleniyor (kendisine komsu)
		labirent.get(testhedef).getkomsu().add(testhedef);
		R[testhedef][testhedef]=0;
		
		
		//r ilk deger atama hepsi -1 yapma
		for(int i=0;i<R.length;i++){
			for(int j=0;j<R.length;j++){
				R[i][j]=-1;
			}
		}
		
		//q ilk deger atama hepsi 0 yapma
		for(int i=0;i<Q.length;i++){
			for(int j=0;j<Q.length;j++){
				Q[i][j]=0;
			}
				
		}
		
		
		//r olusturma komsulara göre 0 yerleştirme
		for(int a=0;a<labirent.size();a++){
			komsu = labirent.get(a).getkomsu();
			for(int i=0;i<komsu.size();i++){
				R[a][komsu.get(i)]=0;
			}
		}
		
		
		//hedefe yol olanlara 100 degeri ataniyor 0 -> 100
		R[testhedef][testhedef]=100;
		for(int i=0;i<R.length;i++){
			if(R[i][testhedef]==0){
				R[i][testhedef]=100;
			}	
		}
				
//		Q(1, 5) = R(1, 5) + 0.8 * Max[Q(5, 1), Q(5, 4), Q(5, 5)] 
//      Q(0, 1)= R(0,1) + 0.8 * max   1 0   1 3 
				
		System.out.println("iterasyon:"+testiterasyon);
		
		Random random = new Random();
		//q degerlerini bulma 
		for(int i=0;i<testiterasyon;i++){
			
			//testdurum = labirent.get(ThreadLocalRandom.current().nextInt(0, labirent.size())).getIndeks();
			
			//Min + ran.nextInt(Max - Min + 1)
			testdurum = labirent.get(0 + random.nextInt((labirent.size()-1) - 0 + 1)).getIndeks();

			do{
//				testsonrakidurum=labirent.get(testdurum).getkomsu().
//						get(ThreadLocalRandom.current().
//								nextInt(0, labirent.get(testdurum).getkomsu().size())
//							);
				
				//Min + ran.nextInt(Max - Min + 1)
				
				testsonrakidurum=labirent.get(testdurum).getkomsu().
						get(0 + random.nextInt((labirent.get(testdurum).getkomsu().size()-1) - 0 + 1));
				
				komsu = labirent.get(testsonrakidurum).getkomsu();
				
				double max = Double.MIN_VALUE;				
				for(int a=0;a<komsu.size();a++){
					if(Double.compare(Q[testsonrakidurum][komsu.get(a)], max)>0){
						max=Q[testsonrakidurum][komsu.get(a)];
					}
				}		
				
				
				Q[testdurum][testsonrakidurum]= R[testdurum][testsonrakidurum] + (ogrenmeKatsayi*max);
				
				testdurum = testsonrakidurum;
				
			}while(testdurum!=testhedef);
			
		}
			
		
		//yolu bulma ve yazdırma
		System.out.println(testbaslangic+"-"+testhedef);
		testdurum=testbaslangic;
		while(testdurum!=testhedef){
			
			double max2=Q[testdurum][0];
			testsonrakidurum=0;
			for(int a=0;a<Q.length;a++){
				if(Double.compare(Q[testdurum][a], max2)>0){
					testsonrakidurum=a;
					max2=Q[testdurum][a];
				}
			}
			yolvector.add(testdurum);
			
			testdurum=testsonrakidurum;
			
		}
		yolvector.add(testhedef);
		
		System.out.println("yol vector:"+yolvector);
				
		
		//dosyalara yaz
		DosyaOkuYaz.DosyaYolYaz("outPath.txt");
		
		DosyaOkuYaz.DosyaRYaz("outR.txt");
		
		DosyaOkuYaz.DosyaQYaz("outQ.txt");
		
	}
	
	public static void main(String args[]){
		
		//dosyadan labirent oku
		DosyaOkuYaz.DosyaScanner("input.txt");
		
		DosyaOkuYaz.DosyalariOlustur("outQ.txt");
		DosyaOkuYaz.DosyalariOlustur("outR.txt");
		DosyaOkuYaz.DosyalariOlustur("outPath.txt");
		
	
		R = new int [(int)(labirent.size())][(int)(labirent.size())];
		Q = new double [(int)(labirent.size())][(int)(labirent.size())];
	
	
		//r ilk deger atama hepsi -1 yapma
		for(int i=0;i<R.length;i++){
			for(int j=0;j<R.length;j++){
				R[i][j]=-1;
			}
		}
	
		//q ilk deger atama hepsi 0 yapma
		for(int i=0;i<Q.length;i++){
			for(int j=0;j<Q.length;j++){
				Q[i][j]=0;
			}	
		}
	
		//r olusturma komsulara göre 0 yerleştirme
		for(int a=0;a<labirent.size();a++){
			komsu = labirent.get(a).getkomsu();
			for(int i=0;i<komsu.size();i++){
				R[a][komsu.get(i)]=0;
			}
		}
		
//		testhedef = 5;
//		testbaslangic = 2;
//		//testiterasyon = 5;
//		coz();
	
	
		//grafik arayüzü oluştur
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gi frame = new Gi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	    
    

 		
	}
	

}
