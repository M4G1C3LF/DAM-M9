package filosof;

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
		Filosof f1 = new Filosof(c1, c5, "Sócrates");
		Filosof f2 = new Filosof(c1, c2, "Heráclito");
		Filosof f3 = new Filosof(c2, c3, "Hegel");
		Filosof f4 = new Filosof(c3, c4, "Nietzsche");
		Filosof f5 = new Filosof(c4, c5, "Diógenes");
		
		executor.execute(f1);
		executor.execute(f2);
		executor.execute(f3);
		executor.execute(f4);
		executor.execute(f5);
		
		executor.shutdown();
	}
}