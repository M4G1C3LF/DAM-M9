
public class TotalMultiples {

	Integer nMultiples = 0;

	public synchronized Integer getnMultiples() {
		return nMultiples;
	}

	public synchronized void setnMultiples(Integer nMultiples) {
		this.nMultiples = nMultiples;
	}
	
	public synchronized void sumarMultiples(Integer multiples){
		this.nMultiples += multiples;
	}
	
	
}
