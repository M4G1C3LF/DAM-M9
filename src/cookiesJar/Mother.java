package cookiesJar;

public class Mother implements Runnable {
	CookiesJar cj;
	public void run()
	{
		while (true)
		{
			//wait(NohayGalletas)
			//wait(mutex)
			fillCookiesJar();
			//Signal(mutex)
			//signal(HayGalletas)
		}
	}
	
	public void fillCookiesJar()
	{
		cj.cookies = 20;
	}

}
