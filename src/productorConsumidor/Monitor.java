package productorConsumidor;

class Monitor {
	
	private final int MAX=4;	//TAMANY MÀXIM DEL ARRAY
	public int x=0;		//ITERADOR
        
	boolean isTerminated=false;	//CONTROLADOR PER SAVER SI EL PRODUCTOR HA ACABAT LA SEVA CUA DE PRODUCCIÓ

    String[] cadena = new String[MAX];
	void Monitor(){
		String[] cadena = new String[]{};
	}
		 
	public synchronized String afagar() {
		//SEMPRE QUE HI HAGI ALGO AL ARRAY PODREM AGAFAR UNA PARAULA, SINÓ RETORNA NULL
            if(x!=0){
                String palabra;
                palabra=cadena[x-1];
                x--;
                return palabra;
            }
            return null;
	}
        
        public synchronized boolean deixar(String palabra){
        	//SEMPRE QUE EL ARRAY NO ESTIGUI PLE PODRÉM DEIXAR UNA PARAULA DINTRE
            if(x<MAX){

                cadena[x]=palabra;
                x++;
                return true;
            }
            return false;
        }
}
