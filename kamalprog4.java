import java.net.*;
import java.io.*;


public class kamalprog4 
{
	// UDP port to which service is bound
	public static final int kamal_PORT = 5000;
	//public static final int lab_PORT = 6000;//for work in lab computer

	// Socket used for reading and writing UDP packets
	private DatagramSocket socket;

	public kamalprog4()
	{
		try
		{
			socket = new DatagramSocket( kamal_PORT );
			//socket = new DatagramSocket( lab_PORT );//for work in lab computer
 
			System.out.println ("Server active on port " + socket.getLocalPort() );
		}
		catch (Exception e)
		{
			System.err.println ("Unable to bind port");
		}
	}

	public void serviceClients()
	{
		// Create a buffer large enough for incoming packets
		 byte[] buffer = new byte[16]; // each double is represented by 8 bytes (x,y) 2 double 16 byte

			try
			{
				// Create a DatagramPacket for reading UDP packets
				DatagramPacket packet = new DatagramPacket ( buffer, 16 );

				// Receive incoming packets
				socket.receive(packet);
			     byte[] receivedData = packet.getData();
                             double double1 = bytesToDouble(receivedData, 0);// from 0 to 8
                             double double2 = bytesToDouble(receivedData, 8);// from 8 to 16
                             System.out.println ("x= "+double1 +", y= "+double2);
			     socket.send(packet);
			}
			catch (IOException ioe)
			{
				System.err.println ("Error : " + ioe);
			}
		}	
        //convert one byte in array of byte to double //double need 8 byte
        private static double bytesToDouble(byte[] bytes, int startIndex) {
        long longValue = 0;
        for (int i = 0; i < 8; i++) {
            longValue <<= 8;
            longValue |= (bytes[startIndex + i] & 0xFF);
                          }
        return Double.longBitsToDouble(longValue);
      }
    
	public static void main(String args[])
	{
		labprog4 server = new labprog4();
		server.serviceClients();
	}
}

