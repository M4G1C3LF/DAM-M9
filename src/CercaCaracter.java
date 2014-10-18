import java.io.FileInputStream;
import java.io.IOException;


public class CercaCaracter {

	public static String readFile(String path)
	{
		FileInputStream f1=null;
		String str = "";
		
		try
		{
			String input = (path);
			f1=new FileInputStream(input);
			int size = f1.available();

			for (int i=0; i<size;i++)
			{
				str += (char)f1.read();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				f1.close();
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
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
