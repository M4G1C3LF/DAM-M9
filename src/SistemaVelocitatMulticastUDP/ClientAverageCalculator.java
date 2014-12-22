package SistemaVelocitatMulticastUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.util.Arrays;



public class ClientAverageCalculator {
	MulticastSocket socketM;
	InetAddress multicastIP;
	int port;
	int nResults = 0;
	long total = 0;
	ClientAverageCalculator(int port)
	{
		this.port = port;
	}
	
	public void init() {
		try {
			multicastIP = InetAddress.getByName("224.0.0.1");
			socketM = new MulticastSocket(port);
			socketM.joinGroup(multicastIP);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while(true)
		{
			try {
				byte [] receivedData = new byte[4];
				DatagramPacket packet;
				//creació del paquet per rebre les dades
				packet = new DatagramPacket(receivedData, 4);
				//espera de les dades
			
				socketM.receive(packet);
				receivedData = packet.getData();
				int recievedSpeed = ByteBuffer.wrap(Arrays.copyOfRange(receivedData, 0, 1024)).getInt();
				
				nResults++;
				total += (long) recievedSpeed;
				
				Double average = ((double) total)/nResults;
				System.out.println("Velocitat mitjana; "+average+"km/h" );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	public static void main(String[] args)
	{
		ClientAverageCalculator client = new ClientAverageCalculator(9999);
		client.init();
	}
	
}
