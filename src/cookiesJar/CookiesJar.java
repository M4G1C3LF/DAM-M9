package cookiesJar;
import java.util.concurrent.Semaphore;

public class CookiesJar {
	
	static int cookies = 20;
	static boolean hasCookies = true;
	static boolean mutexqe;
	static Semaphore semaphore;
	
	public static void main()
	{
		semaphore = new Semaphore(1);
		
		Mother mum = new Mother();
		Child johnny = new Child();
		
	}
}
