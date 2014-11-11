import java.util.ArrayList;


public class BuscaMultiple extends Thread{

	Integer aBuscar;
	ArrayList<Integer> llista;
	Integer trobats = 0;
	
	public TotalMultiples tm;
	public BuscaMultiple(Integer aBuscar, ArrayList<Integer> llista, TotalMultiples tm)
	{
		this.aBuscar = aBuscar;
		this.llista = llista;
		this.tm= tm;
		
	}
	
	public void buscarMultiples()
	{
		
		for (Integer i : llista)
		{
			if (i % aBuscar == 0)
			{
				trobats++;
			}
		}
		tm.sumarMultiples(trobats);
	}

	@Override
	public void run() {
		buscarMultiples();
	}
	
	
	
}
