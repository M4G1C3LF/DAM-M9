
public class MultiplicaLlistaSeq {
	private int operador1; 
	private int operador2; 
	
	public MultiplicaLlistaSeq(int op1, int op2) {
		// TODO Auto-generated constructor stub
		this.operador1 = op1; 
		this.operador2 = op2; 
	}

	public int Multiplica() {
		return operador1*operador2;
	}

	public static void main(String[] args) {
		
		for (int i = 0; i < 10; i++) { 
	 		int a = (int) (Math.random()*10);
	 		int b = (int) (Math.random()*10);
	 		//System.out.println(a + " x " + b);
	 		MultiplicaLlistaSeq calcula = new MultiplicaLlistaSeq(a,b);
	 		System.out.println(a + " x " + b + " = " + calcula.Multiplica());
	 	}
		
	}
}
