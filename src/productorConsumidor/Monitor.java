package productorConsumidor;

class Monitor {
	private final int MAX=4;
	public int x=0;
        
                boolean isTerminated=false;

        String[] cadena = new String[MAX];
	void Monitor(){
		String[] cadena = new String[]{};
	}
		 
	public synchronized String afagar() {
            if(x!=0){
                String palabra;
                palabra=cadena[x-1];
                x--;
                return palabra;
            }
            return null;
	}
        
        public synchronized void deixar(String palabra){
            if(x<MAX){

                cadena[x]=palabra;
                x++;
           
            }
        }

		 
	/**public synchronized void deixar( String cadenasa ) {
		this.cadena = cadenasa;
	}**/
}
