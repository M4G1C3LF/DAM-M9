import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class ClasseURLConnection {

	public static void main (String[] args) throws IOException
	{
		//URL myUrl = new URL("http://192.168.18.178/resp.php"); //Servidor profe
		for (int i=0; i< 1000; i++)
		{
		URL myUrl = new URL("https://docs.google.com/forms/d/1QEqOG8RfcOWDd6NKXvTFeIhH7DZSEpKXqN-laO7nQ04/formResponse"); //GOOGLE DRIVE
		URLConnection cWeb = myUrl.openConnection();
		
		//HEM DE DIRLI A LA CONEXIÓ QUE ACCEPTI FER ENVIAMENT DE DADES
		cWeb.setDoOutput(true);
		
		OutputStreamWriter salida = new OutputStreamWriter(cWeb.getOutputStream());
		
		//MANEM LES DADES PER POST
		salida.write("entry.1642773041=No");
		salida.close();
		
		//LLEGIM LES DADES
		InputStreamReader entrada = new InputStreamReader(cWeb.getInputStream());
		BufferedReader in = new BufferedReader(entrada);

		String linia;
		while ((linia = in.readLine()) != null)
		          System.out.println(linia);
		in.close();
		}
		
	}
}
