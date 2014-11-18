package productorConsumidor;

public class ProductorConsumidor {
    public static void main(String[] args) throws InterruptedException, Throwable {
        String[] cadena = new String[]{"hola","que","ases","tt","tus huebos"};
	Monitor saf = new Monitor();
	Productor p = new Productor(saf);
	Consumidor c = new Consumidor(saf);
	Thread productor = new Thread(p);
	Thread consumidor = new Thread(c);
	productor.start();
	consumidor.start();
    }   
}
