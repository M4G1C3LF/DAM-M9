package fomularisDriveConcurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;



public class Filosof implements Runnable{
	
	private Cobert cd;
	private Cobert ci;
	private String nom;
	
	// Constructor - Asigna els valors per defecte del Filósof
	public Filosof(Cobert d, Cobert i, String name) {
		// TODO Auto-generated constructor stub
		this.cd = d;
		this.ci = i;
		this.nom = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cd.deixarCobert();
			ci.deixarCobert();
			System.out.println("Filósof "+nom+" ha menjat");
		}
	}
}