package jocsOlimpics;

public class Relay {
	private boolean taken;
   
	
	long initTime;
	
	long totalTime = 0;
	

	public synchronized void take(String name){
		try {
			while(taken) { 
				//ATHLETES HAS TO WAIT TO THE ANOTHER ATHLETE DROPS THE RELAY
				initTime = System.currentTimeMillis();
				wait();
			}
			taken = true;
			
			System.out.println("Relay has been taken by "+name); 
			notifyAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public synchronized void drop(String name){
		taken = false;
		System.out.println(name+" has dropped the relay");
		totalTime += System.currentTimeMillis()-initTime;
		notifyAll(); 
	}
}
