package jocsOlimpics;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainRelayRace {

	public static void main(String[] args)
	{
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		Athlete a1 = new Athlete("Lory",22);
		Athlete a2 = new Athlete("Suuuuuup",31);
		Athlete a3 = new Athlete("uripro1",22);
		Athlete a4 = new Athlete("netBeans",34);
		Athlete a5 = new Athlete("Pablo Iglesias",35);
		Athlete a6 = new Athlete("Mariano Rajoy",60);
		Athlete a7 = new Athlete("Pedro Sánchez",35);
		Athlete a8 = new Athlete("Francisco Franco",999999999);
		
		Team t1 = new Team("Swagggg");
		Team t2 = new Team("España");
		
		t1.setAthlete(a1);
		t1.setAthlete(a2);
		t1.setAthlete(a3);
		t1.setAthlete(a4);
		
		t2.setAthlete(a5);
		t2.setAthlete(a6);
		t2.setAthlete(a7);
		t2.setAthlete(a8);
		
		executor.execute(a1);
		executor.execute(a2);
		executor.execute(a3);
		executor.execute(a4);
		executor.execute(a5);
		executor.execute(a6);
		executor.execute(a7);
		executor.execute(a8);		
		
		executor.shutdown();
		
		while (!executor.isTerminated())
		{
			
		}
		
		if (t1.getRelay().totalTime < t2.getRelay().totalTime)
		{
			System.out.println("Team "+t1.getName()+" won with time "+(double)t1.getRelay().totalTime/1000+" s");
			System.out.println("Team "+t2.getName()+" lost with time "+(double)t2.getRelay().totalTime/1000+" s");
		}
		else
		{
			System.out.println("Team "+t2.getName()+" won with time "+(double)t2.getRelay().totalTime/1000+" s");
			System.out.println("Team "+t1.getName()+" lost with time "+(double)t1.getRelay().totalTime/1000+" s");
		}
	}
}
