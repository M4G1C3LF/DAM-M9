package examenUF2_ex2;

public class Llar {

	public static void main(String[] args) throws InterruptedException {
		Comandament c = new Comandament();
		int numMembres = 5;
		
		//Es creen el membres de la família, se'ls dona un nom i es posen a mirar la tele
		MembreFamilia[] fam = new MembreFamilia[numMembres];
			for(int i=0; i<numMembres; i++) {
			fam[i] = new MembreFamilia(c);
			fam[i].setName("Membre-"+i);
			fam[i].start();
		}
		//Esperem que tots acabin de mirar la tele
		for(int i=0; i<numMembres; i++) {
			fam[i].join();
		}
		
		System.out.println("Tots han acabat de mirar la tele!");
		
	}

}
