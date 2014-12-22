import java.util.concurrent.Callable;


public class ProcessCallable {

	private String Nom;

	public ProcessCallable(String Nom) {
		// TODO Auto-generated constructor stub
		this.Nom=Nom;
	}

	public String getNomProces () {
		return Nom;
	}
	
	public String setNomProces(String nouNom ) {
		class renombrar implements Callable<String> {
	        String nom;
	        renombrar(String n) {
	        	nom = n;
	        }
	        public String call() {
	        	Nom = nom;
	          	return new String("El meu nom és: "+ nom);
	        }
	    }
        renombrar r = new renombrar(nouNom);
        return r.call();
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//3a forma de crear un procés
		processRun p3 = new processRun("P3");
//		System.out.println("Sóc el procés:"+p3.getNomProces());
//		System.out.println(p3.setNomProces("pepe"));
		Thread.sleep(5000);
		System.out.println("Sóc el procés:"+p3.getNomProces());
		
	}


}
