
//IMPORTEM LLIBRERÍES
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
 
//DECLAREM CLASSE 
public class MatematiquesExecutor 
{
 
	//CLASSE ANIUADA
	static class Divisor implements Callable<Boolean> 
	{
		//DECLAREM ATRIBUTS DE CLASE ANIUADA
		private int numero;
		private int divisor;
 
		//CONSTRUCTOR AMB DOS PARÀMETRES
		public Divisor(int numero,int divisor ) 
		{
			//ASIGNACIÓ DE VALORS
			this.numero = numero;
			this.divisor = divisor;
		}
		@Override
		//FUNCIÓ OVERRIDE DE CALL, FARÀ LA DIVISIÓ
		public Boolean call() throws Exception 
		{
			if((numero%divisor)==0)
				return true;
			return false;
		}
	}
	//MAIN
	public static void main(String[] args) throws InterruptedException, ExecutionException 
	{
		//CREEM ARRAY DE INT DATA DE 100 POSICIÓNS
		int[] data = new int[100];
		//INICIALITZEM ARRAY DE INTS AMB EL VALOR DE I (i[0]=0...i[99]=99)
		for (int i = 0; i < data.length; i++) 
		{
			data[i]= i;
		}
		//INSTANCIEM UN EXECUTOR AMB UN POOL DE 3 THREADS (fent un casting a ExecutorService)
		ExecutorService executor = (ExecutorService) Executors.newFixedThreadPool(3);
		//INIALITZEM DOS LLISTES DE Divisor (un pels divisors de 2 i un pels divisors de 3)
		List<Divisor> llistaTasques2= new ArrayList<Divisor>();
		List<Divisor> llistaTasques3= new ArrayList<Divisor>();
		//CALCULEM ELS DIVISORS EN ELS DOS CASES FINS A 99.
		for (int i = 0; i < 100; i++) 
		{
			Divisor calculaDivisor2 = new Divisor(i,2);
			llistaTasques2.add(calculaDivisor2);
			Divisor calculaDivisor3 = new Divisor(i,3);
			llistaTasques3.add(calculaDivisor3);
		}
		//INSTANCIEM LLISTES DE FUTURS DE BOOLEANS
		List <Future<Boolean>> llistaResultats2;
		llistaResultats2 = executor.invokeAll(llistaTasques2);
		List <Future<Boolean>> llistaResultats3;
		llistaResultats3 = executor.invokeAll(llistaTasques3);
		
		//NO PERMITIM QUE LI ENTRIN MÉS TASQUES ALS THREADS
		executor.shutdown();
		
		//IMPRIMIM ELS RESULTATS
		for (int i = 0; i < llistaResultats2.size(); i++) 
		{
			Future<Boolean> resultat2 = llistaResultats2.get(i);
			Future<Boolean> resultat3 = llistaResultats3.get(i);
			try 
			{
				if (resultat2.get() && resultat3.get())
					System.out.println("Número "+i+ " és divisible per 2 i per 3");
					//System.out.println("Número "+i+ " és divisible per 2 :" + resultat2.get() + " i divisible per 3: "+ resultat3.get());
			} 
			catch (InterruptedException | ExecutionException e) 
			{
				e.printStackTrace();
			}
		}
 
	}
}