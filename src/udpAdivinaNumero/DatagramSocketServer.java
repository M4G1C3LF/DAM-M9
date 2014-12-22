package udpAdivinaNumero;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class DatagramSocketServer {
	final int datagramLenght = 1024;
	DatagramSocket socket;
	MulticastSocket socketM;
	InetAddress multicastIP;
	int number;
	String motd;
	 
	public void init(int port,int number, String motd) throws SocketException, IOException{
		socket = new DatagramSocket(port);
		socketM = new MulticastSocket(port+1);
		multicastIP = InetAddress.getByName("224.0.0.1");
		this.number = number;
		this.motd = motd;
	}
	 
	public void runServer() throws IOException{
		byte [] receivingData = new byte[datagramLenght];
		byte [] sendingData;
		InetAddress clientIP;
		int clientPort;
		
		System.out.println("Servidor de adivinar numero\n\nEscoltant pel port "+socket.getLocalPort());

		//el servidor atén el port indefinidament
		while(true){
			//creació del paquet per rebre les dades
			DatagramPacket packet = new DatagramPacket(receivingData, datagramLenght);
			//espera de les dades
			socket.receive(packet);
			System.out.println("Dades rebudes");
			//processament de les dades rebudes i obtenció de la resposta
			sendingData = processData(packet.getData(), packet.getLength());
			//obtenció de l'adreça del client
			clientIP = packet.getAddress();
			//obtenció del port del client
			clientPort = packet.getPort();
			//creació del paquet per enviar la resposta
			packet = new DatagramPacket(sendingData, sendingData.length,clientIP, clientPort);
			//enviament de la resposta
			socket.send(packet);
		}
	}
	 
	private byte[] processData(byte[] data, int length) {
		/*	CLIENT CODES
		 * 	1	-	RESPOSTA DEL CLIENT
		 * 	10	-	CLIENT HA CONECTAT
		 * 	11	-	CLIENT HA DESCONECTAT
		 * 
		 * 	-1	-	ERROR EN LLEGIR DADES
		 * 
		 */
		
		/*	SERVER CODES
		 * 
		 * 	10	-	RESPOSTA CORRECTA
		 * 	11	-	NUMERO MENOR QUE LA RESPOSTA
		 * 	12	-	NUMERO MAJOR QUE LA RESPOSTA
		 */
		byte[] code = new byte[4];
		byte[] msg = new byte[4];

		code = Arrays.copyOfRange(data, 0, 4);
//		System.out.println(ByteBuffer.wrap(code).getInt());
		msg = Arrays.copyOfRange(data, 4, 8);
//		System.out.println(ByteBuffer.wrap(msg).getInt());
		String playerName = new String(Arrays.copyOfRange(data, 8, 24));
		playerName = playerName.trim();
		
		byte[] ret = null;
		switch (ByteBuffer.wrap(code).getInt())
		{
			case 1:
				if (ByteBuffer.wrap(msg).getInt() > this.number)
				{
					ret = ByteBuffer.allocate(4).putInt(12).array();
				}
				else if(ByteBuffer.wrap(msg).getInt() < this.number)
				{
					ret = ByteBuffer.allocate(4).putInt(11).array();

				}
				else if (ByteBuffer.wrap(msg).getInt() == this.number)
				{
					ret = ByteBuffer.allocate(4).putInt(10).array();
					byte[] winnerMsg= (new String(playerName + " ha guanyat la partida")).getBytes();
					System.out.println(new String(winnerMsg));

					DatagramPacket packet = new DatagramPacket(winnerMsg, winnerMsg.length,multicastIP,socketM.getLocalPort());
					try {
						socketM.send(packet);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 10:
				
				ret = ByteBuffer.allocate(motd.length()).put(motd.getBytes()).array();
				System.out.println(playerName+" s'ha connectat");
				break;
		}
		
		return ret;
		
	}
	
	public static void main(String[] args)
	{
		DatagramSocketServer adivinaServer = new DatagramSocketServer();
		try {
			adivinaServer.init(9999, 500, "Benvingut al servidor de Sergio Balaguer Carmona");
			adivinaServer.runServer();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}