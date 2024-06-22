import java.net.*;
import java.io.*;


public class Husamcomputerprog3
{
	// UDP port to which service is bound
	public static final int  Kamal_port = 5000;
	public static final int  lab_port = 6000;


	// Max size of packet
	public static final int BUFSIZE = 256;

	public static void main(String args[])
	{
		if (args.length != 2)
		{
			System.err.println ("Syntax - java husamcomputerprog3 Kamal name , lab name");
			return;
		}

		String Kamal = args[0];
		String lab = args[1];
		

		// Get an InetAddress for the specified hostname
		InetAddress addrKamal = null;
		InetAddress addrlab  = null;
		
		try
		{
			// Resolve the hostname to an InetAddr
			addrKamal = InetAddress.getByName(Kamal);
			addrlab = InetAddress.getByName(lab);
			
		}
		catch (UnknownHostException uhe)
		{
			System.err.println ("Unable to resolve host");
			return;
		}
               ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               
               //assume r, x and y are now known for all.
	    int r1 = 1;
            int x1 = 2;
            int y1 = 3;

            int r2 = 4;
            int x2 = 5;
            int y2 = 6;

            int r3 = 7;
            int x3 = 8;
            int y3 = 9;
            //calculat x , y  
           double A = 2 * (x2 - x1);
           double B = 2 * (y2 - y1);
           double C = (Math.pow(r1, 2) - Math.pow(r2, 2) - Math.pow(x1, 2) + Math.pow(x2, 2) - Math.pow(y1, 2) + Math.pow(y2, 2));
           double D = 2 * (x3 - x2);
           double E = 2 * (y3 - y2);
           double F = (Math.pow(r2, 2) - Math.pow(r3, 2) - Math.pow(x2, 2) + Math.pow(x3, 2) - Math.pow(y2, 2) + Math.pow(y3, 2));


           
		try
		{
				// Bind to any free port
				DatagramSocket socketR = new DatagramSocket();
	
				// Set a timeout value of two seconds
				socketR.setSoTimeout (2 * 1000);
                         	//double x = (C * E - F * B) / (E * A - B * D);
                        	//double y = (C * D - A * F) / (B * D - A * E);
                        	
                                double x = 1;//for test
                                double y = 2;//for test
                                
			        byte[] XBytes = doubleToByteArray(x);//convert double To Byte Array
                        	byte[] YBytes = doubleToByteArray(y);//convert double To Byte Array
                        	byte[] packetData = concatenateByteArrays(XBytes, YBytes);//concatenate two byte arrays into a single byte array for send as one backet
                        	
				// Create a packet to send to the UDP server (Kamal)
				DatagramPacket sendPacketKAMAL = new DatagramPacket(packetData, packetData.length, addrKamal, Kamal_port);
				System.out.println ("Sending packet x,y to " + Kamal+" kamal ");	
				socketR.send (sendPacketKAMAL);
				
				// Create a packet to send to the UDP server (lab)
				DatagramPacket sendPacketlab = new DatagramPacket(packetData, packetData.length, addrlab, lab_port);
				System.out.println ("Sending packet x,y to " + lab +" lab ");	
				socketR.send (sendPacketlab);
				
				
				
				
              }

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		


				catch (IOException ioe)
		{
			System.err.println ("Socket error " + ioe);
		}
		
		
	}
	//method to convert double To Byte Array
	    private static byte[] doubleToByteArray(double value) {
        long longValue = Double.doubleToRawLongBits(value);
        return new byte[] {
            (byte)(longValue >> 56),
            (byte)(longValue >> 48),
            (byte)(longValue >> 40),
            (byte)(longValue >> 32),
            (byte)(longValue >> 24),
            (byte)(longValue >> 16),
            (byte)(longValue >> 8),
            (byte)longValue
        };
    }
        //method to concatenate two byte arrays into a single byte array
    private static byte[] concatenateByteArrays(byte[] array1, byte[] array2) {
        byte[] result = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
}

