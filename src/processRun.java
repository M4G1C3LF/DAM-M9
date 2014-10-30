

public class processRun {
	private String Nom;

	public processRun(String Nom) {
		// TODO Auto-generated constructor stub
		this.Nom=Nom;
	}

	public String getNomProces () {
		return Nom;
	}
	
	public void setNomProces(String nouNom ) {
		class renombrar implements Runnable {
	        String nom;
	        renombrar(String n) {
	        	nom = n;
	        }
	        public void run() {
	        	Nom = nom;
	          	System.out.println("Posant el nou nom: "+ nom);
	        }
	    }
        //renombrar r = new renombrar(nouNom);
        //r.run();
	    Thread t = new Thread(new renombrar(nouNom));
	    t.start();
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//3a forma de crear un procés
		processRun p3 = new processRun("P3");
		System.out.println("Sóc el procés:"+p3.getNomProces());
		p3.setNomProces("P3-2");
		Thread.sleep(5000);
		System.out.println("Sóc el procés:"+p3.getNomProces());
		
	}

}
