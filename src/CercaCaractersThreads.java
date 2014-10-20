import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;



public class CercaCaractersThreads {
	
	static String file = readFile("C:\\archivosJava\\file.txt");

	
	public static class Cerca implements Callable<Integer>
	{
		Character charToFind;
		public Cerca(Character c)
		{	
			charToFind = c;
		}
		public Integer call() throws Exception {
			
			return new Integer(charsInString(file,charToFind));
		}
	}
	
	
	//FUNCI� QUE RETORNA EL CONTINGUT DE UN ARXIU EN UN STRING.
	public static String readFile(String path)
	{
		//Initzialitzem variables
		FileInputStream fileContent=null;
		String str = "";
		
		
		/*
		 * Necessitem el block Try/Catch/Finalitzar perque pot haver un error en temps de execuci� dins de l'�mbit del try.
		 * Si falla el block Try deixar� de executar el try i anir� al Catch per poder controlar el error.
		 * Tant si executa el Try o el Catch, sempre s'executar� el block Finally.
		 */
		try
		{
			//Creem FileInputStream en la ruta que li hem passat com a par�metre.
			fileContent=new FileInputStream(path);
			
			//Definim una variable amb el tamany total del arxiu
			int size = fileContent.available();

			//Mentre el nombre de iteraci� sigui menor al nombre de bytes del arxiu afegir� el car�cter al String
			for (int i=0; i<size;i++)
			{
				str += (char)fileContent.read();
			}
		}
		catch(IOException e)
		{
			//Si falla el block Try imprimir� el error que s'ha produ�t per consola.
			e.printStackTrace();
		}
		finally
		{
			//Quan acaba de llegir o si falla, intetna tancar el fitxer.
			try
			{
				fileContent.close();
				
			}
			catch(IOException e)
			{
				//Si no pot tancar el fitxer imprimir� l'error per consola.
				e.printStackTrace();
			}
		}
		
		//Retorna el contingut del fitxer en forma de cadena.
		return str;
	}
	
	//M�TODE QUE RETORNA QUANTS CHARS T� UN STRING
	public static int charsInString(String strToSearch, char characterToFind)
	{
		//Initzialitzem
		int charactersFound = 0;
		
		//Recorrem el String car�cter a car�cter
		for(int i=0;i<strToSearch.length();i++)
		{
			//Si el car�cter actual coincideix amb el car�cter que busquem incrementem el comptador
			if (strToSearch.charAt(i) == characterToFind)
				charactersFound++;
		}	
		return charactersFound;
	}
	
	
	//BUSCAR EN UN STRING EL NOMBRE DE VEGADES QUE ES REPETEIX UN CHAR EN UN STRING.
	public static void main(String args[]) throws InterruptedException, ExecutionException 
	{
				
		
	 	//En aquesta variable emmagatamem la llista de car�cters
	 	List<Cerca> llistaTasques= new ArrayList<Cerca>();
	 	
	 	
	 	
	 	llistaTasques.add(new Cerca(new Character('a')));
	 	llistaTasques.add(new Cerca(new Character('b')));
	 	llistaTasques.add(new Cerca(new Character('c')));
	 	llistaTasques.add(new Cerca(new Character('d')));

	 	 //Es crea l'executor i li diem que per fer les tasques es serveixi de tants threads com car�cters a buscar
	 	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(llistaTasques.size());
	 	
        //Es defineix la llista on es guardaran els resultats que s�n Future perqu� un Callable necesita d'un Future per desar-hi el resultat de la operaci�, que aqu� s�n car�cters
	 	List <Future<Integer>> llistaResultats;
        //Es lliure les tasques al executor
	 	llistaResultats = executor.invokeAll(llistaTasques);
        //Es destrueix l'executor, i tot i que portser que hi hagi tasques executant-se, no les mata. S'espera
	 	executor.shutdown();
	 	
	 	 //S'imprimeixen els resultats que estan desats a una llista de Future.
	 	for (int i = 0; i < llistaResultats.size(); i++) 
	 	{
	 		Future<Integer> resultat = llistaResultats.get(i);
	 		try 
	 		{
	 			System.out.println("Hi han " +resultat.get()+ " "+llistaTasques.get(i).charToFind);
	 		}
	 		catch (InterruptedException | ExecutionException e) 
	 		{
	 			e.printStackTrace();
	 		}
	 	}
		
		
	}
}

