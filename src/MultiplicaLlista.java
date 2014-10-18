
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MultiplicaLlista {
	static class Multiplicacio implements Callable<Integer> { 
		private int operador1; 
		private int operador2; 
		
        //contructor de la classe, necessita els dos operadors
		public Multiplicacio(int operador1, int operador2) { 
			this.operador1 = operador1; 
			this.operador2 = operador2; 
		}
		
		@Override 
        //Executa la tasca assignada, en aquest cas fa la multiplicació
		public Integer call() throws Exception {
			System.out.println("call " + operador1 + " x " + operador2);
			return operador1 * operador2; 
		}
	}
	
	 public static void main(String[] args) throws InterruptedException, ExecutionException {
         //Es crea l'executor i li diem que per fer les tasques es serveixi de 3 processos/threads
		 	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        //En aquesta variable emmagatamem la llista de multipiclacions(Multiplicació)
		 	List<Multiplicacio> llistaTasques= new ArrayList<Multiplicacio>();
		 	
		 	for (int i = 0; i < 10; i++) { 
                 //generem dos enters aleatoris
		 		int a = (int) (Math.random()*10);
		 		int b = (int) (Math.random()*10);
		 		System.out.println(a + " x " + b);
                 //Es crea l'objecte, la Multiplicació amb els valors generats
		 		Multiplicacio calcula = new Multiplicacio(a,b);
                 //s'afegeix la Multiplicació a la llista
		 		llistaTasques.add(calcula);
		 	}
             //Es defineix la llista on es guardaran els resultats que són Future perquè un Callable necesita d'un Future per desar-hi el resultat de la operació, que aquí són enters
		 	List <Future<Integer>> llistaResultats;
             //Es lliure les tasques al executor
		 	llistaResultats = executor.invokeAll(llistaTasques);
             //Es destrueix l'executor, i tot i que portser que hi hagi tasques executant-se, no les mata. S'espera
		 	executor.shutdown();
		 	
             //S'imprimeixen els resultats que estan desats a una llista de Future.
		 	for (int i = 0; i < llistaResultats.size(); i++) {
		 		Future<Integer> resultat = llistaResultats.get(i);
		 		try {
		 			System.out.println("Resultat tasca "+i+ " és:" + resultat.get());
		 			} catch (InterruptedException | ExecutionException e) {
		 				
		 			}
		 	}
	}
	 
}
