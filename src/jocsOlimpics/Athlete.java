package jocsOlimpics;

public class Athlete implements Runnable{
	String name;
	int age;
	Relay relay;
	
	long initTime;
	long totalTime;
	
	public Athlete(String name, int age)
	{
		this.name		=	name;
		this.age		=	age;
	}
	
	public Relay getRelay()
	{
		return relay;
	}
	public void setRelay(Relay relay)
	{
		this.relay = relay;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public void run() {
		try {
			if(relay!= null)
			{
				relay.take(getName());
				Thread.sleep((int) (Math.random() * 4000) + 4000);
				relay.drop(this.getName());
			}
			else
			{
				initTime = System.currentTimeMillis();
				Thread.sleep((int) (Math.random() * 4000) + 4000);
				totalTime += System.currentTimeMillis()-initTime;
				System.out.println(name+" has finished with time "+(double)totalTime/1000 );
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}
