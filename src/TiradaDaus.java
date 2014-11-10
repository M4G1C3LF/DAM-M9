
class TiradaDaus {
	private int tiradaDau;
	 
	public TiradaDaus (int e) {
		tiradaDau=e;
	}
	 
	public synchronized int getSumaTirada() {
		return tiradaDau;
	}
	 
	public synchronized void setSumaTirada(int e) {
		tiradaDau += e;
	}
}
