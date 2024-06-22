import java.net.*;
import java.io.*;


public class labprog4 
{
	// UDP port to which service is bound
	public static final int lab_PORT = 6000;

	// Socket used for reading and writing UDP packets
	private DatagramSocket socket;

	public labprog4()
	{
		try
		{
			// Bind to the specified UDP port, to listen
			// for incoming data packets
			socket = new DatagramSocket( lab_PORT );

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
		 byte[] buffer = new byte[16]; // each double is represented by 8 bytes

			try
			{
				// Create a DatagramPacket for reading UDP packets
				DatagramPacket packet = new DatagramPacket ( buffer, 16 );

				// Receive incoming packets
				socket.receive(packet);
			     byte[] receivedData = packet.getData();
                             double double1 = bytesToDouble(receivedData, 0);
                             double double2 = bytesToDouble(receivedData, 8);
                             System.out.println ("x= "+double1 +", y= "+double2);
				socket.send(packet);
			}
			catch (IOException ioe)
			{
				System.err.println ("Error : " + ioe);
			}
		}	

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

