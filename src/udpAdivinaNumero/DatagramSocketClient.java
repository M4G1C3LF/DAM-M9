package udpAdivinaNumero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DatagramSocketClient {
	InetAddress serverIP;
	protected int serverPort;
	DatagramSocket socket;
	
	
	String playerName = "M4G1C3LF";
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String introducirString()
	{
		try
		{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader buff = new BufferedReader(isr);
			String cadena = buff.readLine();
			
			return cadena;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public void init(String host, int port) throws SocketException,	UnknownHostException, IOException{
		serverIP = InetAddress.getByName(host);
		
		serverPort = port;
		socket = new DatagramSocket();
		
		
		
		
		
	}
	 
	public void runClient() throws IOException{
		byte [] receivedData = new byte[1024];
		 
		//a l'inici
		connect();
		//el servidor atén el port indefinidament
		while(true){
			System.out.print("Introduce un número: ");
			int num = Integer.parseInt(introducirString());
			byte [] sendingData = new byte[24];
			
			int i = 0;
			byte[] codeToSend = ByteBuffer.allocate(4).putInt(1).array(); for (byte b : codeToSend){sendingData[i]=b;i++;}
			byte[] numberToSend = ByteBuffer.allocate(4).putInt(num).array(); for (byte b : numberToSend){sendingData[i]=b;i++;}
			for (byte b : playerName.getBytes()){sendingData[i] = b;i++;} 
			
			DatagramPacket packet = new DatagramPacket(sendingData,sendingData.length,serverIP,serverPort);
			//enviament de la resposta
			socket.send(packet);
			 
			//creació del paquet per rebre les dades
			packet = new DatagramPacket(receivedData, 1024);
			//espera de les dades
			socket.receive(packet);
			//processament de les dades rebudes i obtenció de la resposta
			receivedData = getDataToRequest(packet.getData(), packet.getLength());
			
			byte[] code = Arrays.copyOfRange(receivedData, 0, 4);
			switch (ByteBuffer.wrap(code).getInt())
			{
				case 10:
					System.out.println("Resposta Correcta");
					break;
				case 11:
					System.out.println("El numero introduït es menor que la resposta");
					break;
				case 12:
					System.out.println("El numero introduït es major que la resposta");
					break;
			}
		}
	}
	 
	public static byte[] getDataToRequest(byte[] data, int length) {
		return data;
		//procés diferent per cada aplicació
	}
	 
	private void connect() {
		System.out.println("Connecting");
		byte [] receivedData = new byte[1024];
		byte[] code = ByteBuffer.allocate(4).putInt(10).array();
		byte[] msg = ByteBuffer.allocate(4).putInt(0).array();
		
		byte[] sendingData = new byte[24];
		
		int i=0;
		for (byte b : code)
		{
			sendingData[i] = b;
			i++;
		}
		for (byte b : msg)
		{
			sendingData[i] = b;
			i++;
		}
		for (byte b : playerName.getBytes())
		{
			sendingData[i] = b;
			i++;
		}
		DatagramPacket packet = new DatagramPacket(sendingData, sendingData.length, serverIP, serverPort);
		
			try
			{
				//enviament de la resposta
				socket.send(packet);
				System.out.println("Packet sended");
				//creació del paquet per rebre les dades
				packet = new DatagramPacket(receivedData, 1024);
				//espera de les dades
				socket.receive(packet);
				System.out.println(new String(receivedData));

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	 
	private boolean mustContinue(byte[] sendingData) {
		return false;
		//procés diferent per cada aplicació
	}
	
	public static void main(String args[])
	{
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		DatagramSocketClient client = new DatagramSocketClient();
		try {
			client.init("127.0.0.1", 9999);
			executor.execute(new MulticastSocketClient(client.serverPort+1));
			client.runClient();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}