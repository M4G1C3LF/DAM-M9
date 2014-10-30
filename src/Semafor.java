
public class Semafor {

	private int semafor;
	 
	//Constructor seria l'operació Initial
	public Semafor(int s)	{
		this.semafor = s;
	}
	 
	public void sendSignal()	{
		semafor=1;
	}
	 	 
	public void sendWait()	{
		while(semafor <= 0) {	 
		}
		semafor=0;
	}

}
