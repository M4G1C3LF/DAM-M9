package cookiesJar;

public class Child implements Runnable {

		CookiesJar cj;
		public void run()
		{
			while (true)
			{
				//wait(mutex)
				
				if (cj.cookies==0)
				{
					//signal(NoHayGalletas)
					//wait(haygalletas)
				}
				else
				{
					cj.cookies--;
				}
				
				//signal(mutex)
			}
		}
}
