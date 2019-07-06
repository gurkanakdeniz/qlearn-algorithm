package qlearn;

import java.util.Vector;

public class Dugum {
	
	private int indeks;
	
	private Vector <Integer> komsular = new Vector<Integer>();
	
	
	public int getIndeks() {
		return indeks;
	}
	public void setIndeks(int indeks) {
		this.indeks = indeks;
	}
	public void addkomsu(int a){
		komsular.add(a);
	}
	public Vector<Integer> getkomsu(){
		return komsular;
	}

}
