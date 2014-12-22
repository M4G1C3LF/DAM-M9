import java.net.InetAddress;
import java.net.UnknownHostException;


public class IpRetornaCanonicalName {
	
	private static boolean isIp(String cad){
		
		boolean ret;
		String[] strBytes = cad.split("\\.");
		ret = strBytes.length==4;
		ret = ret&& strBytes[0].matches("\\d{1,3}");
		return ret;
	}
    
	public static void main(String[] args)
	{
    	String ip = "192.168.0.8";
		if (isIp(ip))
		{
			try {
				
				InetAddress addr = InetAddress.getByName(ip);
				System.out.println(addr.getCanonicalHostName());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
