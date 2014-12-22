package SistemaVelocitatMulticastUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.util.Arrays;



public class ClientSpeedometer {
	MulticastSocket socketM;
	InetAddress multicastIP;
	int port;
	ClientSpeedometer(int port)
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
				
				for (int i = 0; i<recievedSpeed;i++)
				{
					System.out.print("#");
				}
				System.out.println("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	public static void main(String[] args)
	{
		ClientSpeedometer client = new ClientSpeedometer(9999);
		client.init();
	}
	
}
