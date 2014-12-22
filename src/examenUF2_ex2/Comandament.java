package examenUF2_ex2;

public class Comandament {
	
	boolean taken;
	public int canal;
	public synchronized void Agafa(){
		try {
			while(taken) { 
				//ATHLETES HAS TO WAIT TO THE ANOTHER ATHLETE DROPS THE RELAY
				wait();
			}
			taken = true;
			
			notifyAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public synchronized void Deixa(){
		taken = false;
		notifyAll(); 
	}
}
