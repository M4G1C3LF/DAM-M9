
class Productor implements Runnable {
	private Monitor safata;
	 
	public Productor( Monitor s ) {
		safata = s;
	}
	private int numero =0;
	 
	public void run() {
	 
		// Posa 10 lletres a la canonada
		for( int i=0; i < 5; i++ )
		{
			numero= (int)(10*Math.random());
			System.out.println( "Produït el número "+numero );
			try {
				Thread.sleep( (int)(Math.random() * 1000 ) );
			} catch( InterruptedException e ) {
				
				e.printStackTrace();
			}
		}
	}
}