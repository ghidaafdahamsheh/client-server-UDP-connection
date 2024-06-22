import java.net.*;
import java.io.*;


public class prog1 
{
	// UDP port to which service is bound
	public static final int  Super_Robot_port = 2000;
	public static final int  Husam_port = 3000;
        // public static final int  kamal_port = 7000; //and work with it as Super_Robot_port and Husam_port 

	// Max size of packet
	public static final int BUFSIZE = 256;

	public static void main(String args[])
	{
		if (args.length != 2)
		{
			System.err.println ("Syntax - java prog1 Robotname , Husam ");
			return;
		}

		String Super_Robot = args[0];
		String Husam = args[1];
		

		// Get an InetAddress for the specified hostname
		InetAddress addrSuper_Robot = null;
		InetAddress addrHusam  = null;
		
		try
		{
			// Resolve the hostname to an InetAddr
			addrSuper_Robot = InetAddress.getByName(Super_Robot);
			addrHusam = InetAddress.getByName(Husam);
		}
		catch (UnknownHostException uhe)
		{
			System.err.println ("Unable to resolve host");
			return;
		}
               ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		try
		{
			// Bind to any free port
			DatagramSocket socketR = new DatagramSocket();

			// Set a timeout value of two seconds
			socketR.setSoTimeout (2 * 1000);
                        String message = "STOP ";
				byte[] stopBytes = message.getBytes();
				// Create a packet to send to the UDP server
				DatagramPacket sendPacket = new DatagramPacket(stopBytes, stopBytes.length, addrSuper_Robot, Super_Robot_port);
				System.out.println ("Sending packet ( STOP ) to " + Super_Robot);	
				socketR.send (sendPacket);
				System.out.print ("Waiting for packet (ok)");				
				byte[] recok = new byte[BUFSIZE];
				DatagramPacket receivePacketok = new DatagramPacket(recok, BUFSIZE);
				boolean timeout = false;

				// Catch any InterruptedIOException that is thrown
				// while waiting to receive a UDP packet
				try
				{
					socketR.receive (receivePacketok);
				}
				catch (InterruptedIOException ioe)
				{
					timeout = true;
				}

				if (!timeout)
				{
					System.out.println ("packet received!");
				

					// Obtain a byte input stream to read the UDP packet
					ByteArrayInputStream bin = new ByteArrayInputStream (
					  receivePacketok.getData(), 0, receivePacketok.getLength() );

					// Connect a reader for easier access
					BufferedReader reader = new BufferedReader ( 
					  new InputStreamReader ( bin ) );
					  String line = reader.readLine();
					  System.out.println (line);

				
				}
				else
				{
					System.out.println ("........packet lost!");
				}
				}

		catch (IOException ioe)
		{
			System.err.println ("Socket error " + ioe);
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		try
		{
			        // Bind to any free port
			         DatagramSocket socketh = new DatagramSocket();
			         byte[] rxy = { 10, 20, 30 };//) assume r1, x1 and y1 are now known. Send this information in one packet to Husam’s computer.
				// Create a packet to send to the UDP server
				DatagramPacket sendPacket = new DatagramPacket(rxy, rxy.length, addrHusam, Husam_port);
				System.out.println ("Sending packet ( r(x,y) ) to " + addrHusam);	
				socketh.send (sendPacket);
				
				}

				catch (IOException ioe)
		{
			System.err.println ("Socket error " + ioe);
		}
		
		
	}
}

