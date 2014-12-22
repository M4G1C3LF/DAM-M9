package udpAdivinaNumero;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

public class MulticastSocketClient implements Runnable{
	MulticastSocket socketM;
	InetAddress multicastIP;
	int port;
	MulticastSocketClient(int port)
	{
		this.port = port;
	}
	@Override
	public void run() {
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
				byte [] receivedData = new byte[1024];
				DatagramPacket packet;
				//creació del paquet per rebre les dades
				packet = new DatagramPacket(receivedData, 1024);
				//espera de les dades
			
				socketM.receive(packet);
				receivedData = DatagramSocketClient.getDataToRequest(packet.getData(), packet.getLength());
				String serverMessage = new String(Arrays.copyOfRange(receivedData, 0, 1024));
				serverMessage = serverMessage.trim();
				
				System.out.println(serverMessage);
				
				System.exit(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
