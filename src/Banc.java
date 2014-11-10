
public class Banc {

	private float saldo;

	/*
	 * CONSTRUCTOR
	 */
	public Banc(float saldo)
	{
		this.saldo=saldo;
	}
	
	/*
	 * GETTERS I SETTERS
	 */
	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	/*
	 * MÉTODES DE LA CLASSE
	 */
	public synchronized void ingressar(float ingres)
	{
		saldo += ingres;
	}
	
	public synchronized void treure(float extraccio)
	{
		saldo -= extraccio;
	}
}
