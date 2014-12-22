package ExamenUF1_ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


public class Main {

	public static void main(String[] argv) throws InterruptedException
	{
		
		int numAlumnes = 10,te,ti;
		String Modul = "M9";
		
	 	//En aquesta variable emmagatamem la llista de alumnes
	 	List<Alumne> llistaTasques= new ArrayList<Alumne>();
	 	
	 	//Instanciem alumnes
	 	for (int i=0;i<numAlumnes;i++) {
	 		llistaTasques.add(new Alumne(Modul+"-"+i));
	 	}
	 	
	 	//Instanciem executor
	 	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

	 	//Es defineix la llista on es guardaran els resultats que són Future perqué un Callable necesita d'un Future per desar-hi el resultat de la operació, que aquí són Integer
	 	List <Future<Integer>> llistaResultats;
	 	
	 	//comencem a contar el temps
	 	ti = (int) System.currentTimeMillis();
	 	
        //Es lliure les tasques al executor
	 	llistaResultats = executor.invokeAll(llistaTasques);
        //Es destrueix l'executor, i tot i que portser que hi hagi tasques executant-se, no les mata. S'espera
	 	executor.shutdown();
	 	
	 	//Han acabat i agafem el temps final
	 	te = (int) System.currentTimeMillis();
	 	 //S'imprimeixen els resultats que estan desats a una llista de Future.
	 	for (int i = 0; i < llistaResultats.size(); i++) 
	 	{
	 		Future<Integer> resultat = llistaResultats.get(i);
	 		try 
	 		{
	 				System.out.println(llistaTasques.get(i).getNom()+" - NOTA: "+resultat.get());
	 		}
	 		catch (InterruptedException | ExecutionException e) 
	 		{
	 			e.printStackTrace();
	 		}
	 	}
		System.out.println("Han trigat: " + (te - ti)/1000 + " segons");

	}
}
