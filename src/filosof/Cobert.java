package filosof;

public class Cobert {
	private int num;  
	private boolean cobertOcupat;
   
	// Constructor - Asigna els valors per defecte del cobert
	public Cobert(int num){  
		this.num = num;  
	}  
	
	public synchronized void agafarCobert(String name){
		try {
			while(cobertOcupat) { 
				System.out.println("Espera el filósof "+name);
				wait();
			}
			cobertOcupat = true;
			System.out.println("Cobert "+num+" está ocupat"); 
			notifyAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public synchronized void deixarCobert(){
		cobertOcupat = false;
		System.out.println("Cobert "+num+" está disponible"); 
		notifyAll(); 
	}
}