package productorConsumidor;

class Productor implements Runnable {
	private Monitor safata;	//MONITOR

	public Productor( Monitor s ) {
			safata = s;
	}
	
	//ARRAY DE STRINGS ON GUARDEM LES STRINGS A TRACTAR
	private String[] cadena = new String[]{"hola","palabra","abrir","ala","silla","palabra","abuela","acelgas"};
	
	public void run() {
        
		//BUCLE ON FIQUEM ELS STRINGS A TRACTAR
		for( int i=0; i < cadena.length; i++ )
		{
			System.out.println( "Cadena produida: "+cadena[i].toString() );
            //SI NO HA POGUT DEIXAR LA PARAULA HO TORNARÀ A INTENTAR
			if (!safata.deixar(cadena[i]))
            {
            	i--;
            }
			try {
				Thread.sleep( (int)(Math.random() * 1000 ) );
			} catch( InterruptedException e ) {;}
		}
		//SI HA ACABAT, COMUNICA QUE HA ACABAT DE AFEGIR PARAULES.
		safata.isTerminated=true;
	}
}