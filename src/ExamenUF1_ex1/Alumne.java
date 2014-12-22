package ExamenUF1_ex1;

import java.util.concurrent.Callable;


public class Alumne implements Callable<Integer>{
	private String Nom;
		
	public Alumne(String nom) {
		Nom = nom;
	}

	public int Examinar() {
		//temps que triga a fer l'examen
		try {
			Thread.sleep((long) (Math.random()*2000)+1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//nota de l'examen
		int nota = (int) (Math.random()*10);
		return nota;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return Examinar();
	}

	

	
	
	
}
