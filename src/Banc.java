

public class Banc {
    
	
	private int Saldo;
	private Semafor s;
	
	public Banc() {
		// TODO Auto-generated constructor stub
		s = new Semafor(1);
		Saldo = 0;
	}
	
	public int getSaldo() {
		return Saldo;
	}

	private void setSaldo(int saldo) {
		Saldo = saldo;
	}

	public void ingres(int diners) {
		class ingressar implements Runnable {
	        int dins;
	        ingressar(int d) { dins = d; }
	        public void run() {
	        	s.sendWait();
	        	int aux;
	        	aux=getSaldo();
	        	aux=aux+dins;
	        	setSaldo(aux);
	        	System.out.print("Ingresso "+ dins + '\n');
	        	try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	s.sendSignal(); 
	        }
	    }
	    //ingressar i = new ingressar(diners);
    	//i.run();
	    Thread t = new Thread(new ingressar(diners));
	    t.start();
	}
	
	public void treu(int diners) {
		class treure implements Runnable {
	        int dins;
	        treure(int d) { dins = d; }
	        public void run() {
	        	s.sendWait();
	        	int aux;
	        	aux=getSaldo();
	        	aux=aux-dins;
	        	setSaldo(aux);
	        	System.out.print("trec "+dins+ '\n');
	        	try {
					Thread.sleep(000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	s.sendSignal(); 
	        }
	    }
	    //treure t = new treure(diners);
    	//t.run();
	    Thread t = new Thread(new treure(diners));
	    t.start();
	    
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sal=0;
		Banc b = new Banc();
		b.ingres(100);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Saldo: "+ b.getSaldo());
		b.ingres(150);
		b.treu(50);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Saldo: "+ b.getSaldo());
		
	}

	
}
