package examenUF2_ex2;

public class MembreFamilia extends Thread {
	Comandament com;
	
	public MembreFamilia(Comandament c) {
		com = c;
	}

	@Override
	public void run() {
		//Agafa el comandament
		com.Agafa();
		int canal = (int)((Math.random()*10)+1);
		if (canal == com.canal)
		{
			System.out.println(getName() + " està mirant el mateix canal");
		}
		else
		{
			System.out.println(getName() + " ha posat el canal " + canal );
		}
		com.canal = canal;
		//Mira la tele
		try {
			Thread.sleep((long) (Math.random()*3000)+2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Deixa el comandament
		com.Deixa();
	}
	
	

}
