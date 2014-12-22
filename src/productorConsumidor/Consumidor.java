package productorConsumidor;

class Consumidor implements Runnable {
	 
	private Monitor safata;	//MONITOR
 
	public Consumidor( Monitor s ) {
		safata = s;
	}
	
	//FUNCIÓ QUE CANVÍA UN CARÀCTER PER UN ALTRE.
	public String transformarPalabra(String str, char seek, char replace){
		for(int i=0;i<=str.length();i++){
			str=str.replace(seek, replace);
		}
		return str;
	}

	public void run() {
		
		String cadena;
		//MENTRE NO HAGI TERMINAT I NO ESTIGUI EN LA POSICIÓ 0 DEL ARRAY
		//TRACTARÀ LES CADENES DEL ARRAY DEL MONITOR
		while(!(safata.isTerminated && safata.x == 0))
		{
			cadena = safata.afagar();
			
			//SI LA CADENA NO VAL NULL LA TRACTAREM I SI NO, NO.
			if (cadena!=null){
				cadena=transformarPalabra(cadena, 'a', '4');
				System.out.println( "Cadena agafada: "+cadena);
			try {
				Thread.sleep( (int)(Math.random() * 1000 ) );
			} catch( InterruptedException e ) {e.printStackTrace();}
                        
			}
		}
	}
}