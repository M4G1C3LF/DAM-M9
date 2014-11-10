import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;



public class MoneyConversionThreads {
	

	//Creem una clase aniuada que implementi Callable y que pugui retornar Double
	public static class Convert implements Callable<Double>
	{
		//Creem els atributs de la clase aniuada
		Double amount;		//Fa referència als diners
		Character badge;	//Fa referència a la divisa
		//En el constructor per defecte asignem la divisa i els diners
		public Convert(Double amount, Character badge)
		{	
			this.amount = amount;
			this.badge = badge;
		}
		//Si la divisa es d retorna euros i si la divisa es e retorna dolars.
		public Double call() throws Exception {
			
			if (badge == 'd')
				return USDToEuros(amount);
			else if(badge == 'e')
				return eurosToUSD(amount);
			return null;
		}
	}
	
	
	//MÉTODE QUE RETORNA LA CONVERSIÓ DE EUROS A DOLARS
	public static Double eurosToUSD(Double euros)
	{
			Double diff = 1.268215;
			Double usd = euros*diff;
		
			return usd;
	}
	
	//MÉTODE QUE RETORNA LA CONVERSIÓ DE DOLARS A EUROS
	public static Double USDToEuros(Double usd)
	{
			Double diff = 0.788509835;
			Double euros = usd*diff;
		
			return euros;
	}
	
	
	//BUSCAR EN UN STRING EL NOMBRE DE VEGADES QUE ES REPETEIX UN CHAR EN UN STRING.
	public static void main(String args[]) throws InterruptedException, ExecutionException 
	{
				
		
	 	//En aquesta variable emmagatamem la llista de converions que farem.
	 	List<Convert> llistaTasques= new ArrayList<Convert>();
	 	
	 	llistaTasques.add(new Convert(20d,'e'));
	 	llistaTasques.add(new Convert(10d,'d'));
	 	llistaTasques.add(new Convert(123d,'e'));
	 	llistaTasques.add(new Convert(45d,'d'));
	 	llistaTasques.add(new Convert(34d,'e'));
	 	

	 	 //Es crea l'executor i li diem que per fer les tasques es serveixi de tants threads com conversions a realitzar
	 	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(llistaTasques.size());
	 	
        //Es defineix la llista on es guardaran els resultats que són Future perqué un Callable necesita d'un Future per desar-hi el resultat de la operació, que aquí són Double
	 	List <Future<Double>> llistaResultats;
        //Es lliure les tasques al executor
	 	llistaResultats = executor.invokeAll(llistaTasques);
        //Es destrueix l'executor, i tot i que portser que hi hagi tasques executant-se, no les mata. S'espera
	 	executor.shutdown();
	 	
	 	 //S'imprimeixen els resultats que estan desats a una llista de Future.
	 	for (int i = 0; i < llistaResultats.size(); i++) 
	 	{
	 		Future<Double> resultat = llistaResultats.get(i);
	 		try 
	 		{
	 			//Si el valor de la divisa a la tasca es un e, imprimirà en canvi de euros a dòlars, si el valor de divisa es d ho farà al revés.
	 			if (llistaTasques.get(i).badge == 'e'){
	 				System.out.println(llistaTasques.get(i).amount+"€ are "+resultat.get()+"$");
	 			}else if (llistaTasques.get(i).badge == 'd'){
	 				System.out.println(llistaTasques.get(i).amount+"$ are "+resultat.get()+"€");
	 			}
	 			
	 		}
	 		catch (InterruptedException | ExecutionException e) 
	 		{
	 			e.printStackTrace();
	 		}
	 	}
		
		
	}
}


