
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



public class BancMain {
	
	public static void main(String[] args)
	{
		Banc banc = new Banc(1000f);
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		Runnable ing = new Runnable(){
			public void run()
			{
				banc.ingressar(10f);
			}
		};
		
		Runnable tre = new Runnable(){
			public void run()
			{
				banc.treure(10f);
			}
		};
		
		for (int i = 0;i<10000;i++)
		{
			executor.execute(ing);
			executor.execute(tre);
		}
	 	
		executor.shutdown();
		System.out.println("El saldo és de: "+banc.getSaldo());
	}
}
