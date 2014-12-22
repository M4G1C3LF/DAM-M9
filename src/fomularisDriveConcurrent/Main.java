package fomularisDriveConcurrent;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
				
		// Coberts a utilitzar 
		Cobert c1 = new Cobert(1);
		Cobert c2 = new Cobert(2);
		Cobert c3 = new Cobert(3);
		Cobert c4 = new Cobert(4);
		Cobert c5 = new Cobert(5);
		
		// Filosof i coberts que utilitzen
		
		ArrayList<Filosof> llista = new ArrayList<Filosof>();
		
		for (int i=0; i<100;i++)
		llista.add(new Filosof(c1, c5, "Sócrates"));
		
		for(Filosof f : llista)
		{
			executor.execute(f);
		}
		
		
		executor.shutdown();
	}
}