package productorConsumidor;

class Productor implements Runnable {
	private Monitor safata;
        boolean isTerminated=false;

	public Productor( Monitor s ) {
			safata = s;
	}
	private String[] cadena = new String[]{"hola","que","ases","tt","tus huebos"};
	
	public void run() {
        
	// 	Posa 10 lletres a la canonada
		for( int i=0; i < 5; i++ )
		{
			System.out.println( "Cadena produida: "+cadena[i].toString() );
                        safata.deixar(cadena[i]);
			try {
				Thread.sleep( (int)(Math.random() * 1000 ) );
			} catch( InterruptedException e ) {;}
		}
                safata.isTerminated=true;
	}
}