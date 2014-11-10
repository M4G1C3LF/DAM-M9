package filosof;



public class Filosof implements Runnable{
	
	private Cobert cd;
	private Cobert ci;
	private String nom;
	
	// Constructor - Asigna els valors per defecte del Filósof
	public Filosof(Cobert d, Cobert i, String name) {
		// TODO Auto-generated constructor stub
		this.cd = d;
		this.ci = i;
		this.nom = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cd.agafarCobert(nom);
			ci.agafarCobert(nom);
			System.out.println("Filósof "+nom+" está menjant");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cd.deixarCobert();
			ci.deixarCobert();
			System.out.println("Filósof "+nom+" ha menjat");
		}
	}
}