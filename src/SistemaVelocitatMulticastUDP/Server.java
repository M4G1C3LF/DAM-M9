package SistemaVelocitatMulticastUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;


public class Server {
	
	final int datagramLenght = 1024;
	MulticastSocket socketM;
	InetAddress multicastIP;
	int speed;
	
	public void init(int port) throws SocketException, IOException{
		socketM = new MulticastSocket(port);
		multicastIP = InetAddress.getByName("224.0.0.1");
	}
	public void runServer() throws IOException, InterruptedException{

		
		System.out.println("Servidor de sistema de velocitat\n\nEscoltant pel port "+socketM.getLocalPort());

		//el servidor atén el port indefinidament
		while(true){
			speed = (int) (Math.random() * 80) + 1;
			DatagramPacket packet = new DatagramPacket(ByteBuffer.allocate(4).putInt(speed).array(), 4,multicastIP,socketM.getLocalPort());
			socketM.send(packet);
			Thread.sleep(100);
		}
	}
	
	public static void main (String[] args)
	{
		Server adivinaServer = new Server();
		try {
			adivinaServer.init(9999);
			adivinaServer.runServer();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
