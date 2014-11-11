import java.util.ArrayList;


public class MultiplesThreads  {

	Integer totalMultiples = 0;
	private BuscaMultiple xobj;
	 
	public MultiplesThreads(BuscaMultiple m) {
		xobj=m;
	}
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException{
		int max = 500;
		ArrayList<Integer> llista = new ArrayList<Integer>();
		ArrayList<Integer> llista2 = new ArrayList<Integer>();
		
		for (int i = 0; i<max;i++)
		{
			llista.add(((int) (Math.random()*9999) + 1));
			llista2.add(((int) (Math.random()*9999) + 1));

		}
		
		TotalMultiples tm = new TotalMultiples();
		

		BuscaMultiple obj1 = new BuscaMultiple(111,llista,tm);
		BuscaMultiple obj2 = new BuscaMultiple(111,llista2,tm);
		
		
		
		obj1.start();
		obj2.start();
		obj1.join();
		obj2.join();
		
		System.out.println(tm.getnMultiples() );
	}
}

	
