package productorConsumidor;

public class ProductorConsumidor {
    public static void main(String[] args) throws InterruptedException, Throwable {

    //INSTANCIEM EL MONITOR, EL PRODUCTOR I ELS CONSUMIDOR
    //INSTANCIEM ELS THREADS I ELS INICIEM
    Monitor mon = new Monitor();
	Productor p = new Productor(mon);
	Consumidor c = new Consumidor(mon);
	Thread productor = new Thread(p);
	Thread consumidor = new Thread(c);
	productor.start();
	consumidor.start();
    }   
}
