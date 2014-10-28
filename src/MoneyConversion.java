
public class MoneyConversion {
	
	public static Double eurosToUSD(Double euros)
	{
			Double diff = 1.268215;
			Double usd = euros*diff;
		
			return usd;
	}
	public static Double USDToEuros(Double usd)
	{
			Double diff = 0.788509835;
			Double euros = usd*diff;
		
			return euros;
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("10€ are "+eurosToUSD(10d)+"$");
		System.out.println("10$ are "+USDToEuros(10d)+"€");
		System.out.println("23€ are "+eurosToUSD(23d)+"$");
		System.out.println("11$ are "+USDToEuros(11d)+"€");
		System.out.println("63€ are "+eurosToUSD(63d)+"$");
		
	}
}
