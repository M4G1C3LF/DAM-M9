import java.io.FileInputStream;
import java.io.IOException;


public class CercaCaracter {
	
	
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
	public static void main(String args[])
	{
		String file 			= readFile("C:\\archivosJava\\file.txt");
		char characterToFind	= 'a';
		
		System.out.println("Hi han "+charsInString(file,characterToFind)+" car�cters.");
		
		
	}
}
