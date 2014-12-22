class Consumidor implements Runnable {
 
	private Monitor safata;
	 
	public Consumidor( Monitor s ) {
		safata = s;
	}
	 
	 
	public void run() {
		String num;
	 
		for( int i=0; i < 5; i++ )
		{
			num = safata.afagar();
			System.out.println( "Número agafat: "+num );
			try {
				Thread.sleep( (int)(Math.random() * 2000 ) );
			} catch( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}
}