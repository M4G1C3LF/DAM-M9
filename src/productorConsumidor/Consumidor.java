package productorConsumidor;

class Consumidor implements Runnable {
	 
	private Monitor safata;
 
	public Consumidor( Monitor s ) {
		safata = s;
	}
 
	public String transformarPalabra(String str){
		for(int i=0;i<=str.length();i++){
			str=str.replace('a', '4');
//			str=str.replace("a","o");
		}
		return str;
	}
        public void exit() throws Throwable{
            finalize();
        }
	public void run() {
		
		String cadena;
		while(!(safata.isTerminated && safata.x == 0))
		{
			cadena = safata.afagar();
			if (cadena!=null){
                        	cadena=transformarPalabra(cadena);
                        
                                System.out.println( "Cadena agafada: "+cadena);
			try {
				Thread.sleep( (int)(Math.random() * 1000 ) );
			} catch( InterruptedException e ) {e.printStackTrace();}
                        
                        }
		}
	}
}