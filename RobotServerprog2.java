import java.net.*;
import java.io.*;

// Chapter 5, Listing 3
public class RobotServerprog2 
{
	// UDP port to which service is bound
	public static final int Robot_PORT = 2000;

	// Max size of packet, large enough for almost any client
	public static final int BUFSIZE = 256;

	// Socket used for reading and writing UDP packets
	private DatagramSocket socket;

	public RobotServerprog2()
	{
		try
		{
			// Bind to the specified UDP port, to listen
			// for incoming data packets
			socket = new DatagramSocket( Robot_PORT );

			System.out.println ("Robot active on port " + socket.getLocalPort() );
		}
		catch (Exception e)
		{
			System.err.println ("Unable to bind port");
		}
	}

	public void serviceClients()
	{
		// Create a buffer large enough for incoming packets
		byte[] bufferStop = new byte[BUFSIZE];
		DatagramPacket receivePacket=null;
		InetAddress addr =null;
		int senderPort=0;
			try
			{
				while (true)
		                 {
				// Create a DatagramPacket for reading UDP packets
				 receivePacket = new DatagramPacket ( bufferStop, BUFSIZE );

				// Receive incoming packets
				socket.receive(receivePacket);
				System.out.println (" receive Packet ");
                                if (data(bufferStop).toString().equals("STOP"))
			       {
				System.out.println("Client- sent Stop ... EXIT.");
				senderPort = receivePacket.getPort();
			         addr = receivePacket.getAddress();
			         String message = "Ok ";
				byte[] okBytes = message.getBytes();
				// Create a packet to send to the UDP server
				DatagramPacket sendPacket = new DatagramPacket(okBytes, okBytes.length,addr , senderPort);
				System.out.println ("Sending packet ( Ok ) to " + addr);	
				socket.send (sendPacket);
				break;
			        }
			        bufferStop = new byte[BUFSIZE];
			        }
 
					
			}
			catch (IOException ioe)
			{
				System.err.println ("Error : " + ioe);
			}
		}	

	public static void main(String args[])
	{
		RobotServerprog2 server = new RobotServerprog2();
		server.serviceClients();
	}
		public static StringBuilder data(byte[] a)
	{
		if (a == null)
			return null;
		StringBuilder ret = new StringBuilder();
		int i = 0;
		while (a[i] != 0)
		{
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}
}

