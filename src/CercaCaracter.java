import java.io.FileInputStream;
import java.io.IOException;


public class CercaCaracter {
	
	
	//FUNCIÓ QUE RETORNA EL CONTINGUT DE UN ARXIU EN UN STRING.
	public static String readFile(String path)
	{
		//Initzialitzem variables
		FileInputStream fileContent=null;
		String str = "";
		
		
		/*
		 * Necessitem el block Try/Catch/Finalitzar perque pot haver un error en temps de execució dins de l'àmbit del try.
		 * Si falla el block Try deixarà de executar el try i anirá al Catch per poder controlar el error.
		 * Tant si executa el Try o el Catch, sempre s'executarà el block Finally.
		 */
		try
		{
			//Creem FileInputStream en la ruta que li hem passat com a paràmetre.
			fileContent=new FileInputStream(path);
			
			//Definim una variable amb el tamany total del arxiu
			int size = fileContent.available();

			//Mentre el nombre de iteració sigui menor al nombre de bytes del arxiu afegirá el carácter al String
			for (int i=0; i<size;i++)
			{
				str += (char)fileContent.read();
			}
		}
		catch(IOException e)
		{
			//Si falla el block Try imprimirà el error que s'ha produït per consola.
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
				//Si no pot tancar el fitxer imprimirà l'error per consola.
				e.printStackTrace();
			}
		}
		
		//Retorna el contingut del fitxer en forma de cadena.
		return str;
	}
	
	//MÉTODE QUE RETORNA QUANTS CHARS TÉ UN STRING
	public static int charsInString(String strToSearch, char characterToFind)
	{
		//Initzialitzem
		int charactersFound = 0;
		
		//Recorrem el String caràcter a caràcter
		for(int i=0;i<strToSearch.length();i++)
		{
			//Si el caràcter actual coincideix amb el caràcter que busquem incrementem el comptador
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
		
		System.out.println("Hi han "+charsInString(file,characterToFind)+" caràcters.");
		
		
	}
}
