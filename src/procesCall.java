
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


public class procesCall implements Callable<String>{
	private String Nom;

	public procesCall(String nom) {
		// TODO Auto-generated constructor stub
		this.Nom = nom;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Sóc el procés "+Nom);
		return Nom;
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		
		//1a Forma de crear un procés 
		//Instanciem un procés processCall que és una classe que implementa un Callable
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		procesCall p1 = new procesCall("P1");
		Future<String> resultatTasca;
		resultatTasca = executor.submit(p1);
		System.out.println("Procés: "+ resultatTasca.get());
		
		
		//2a Forma de crear un procés
		//Instanciem directament en el zona del codi que necessitem el procés
		Callable<String> p2 = new Callable<String>() {
			String Nom = new String("P2");
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println("Sóc el procés "+ Nom);
				return Nom;
			}
		};
		
		
		
		resultatTasca = executor.submit(p2);
		System.out.println("Procés: "+ resultatTasca.get());
		
		
		
		executor.shutdown();

	}

}
