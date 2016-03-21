package magic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Writer {

	
	final private BufferedWriter writer;
	final private BufferedReader reader;

	private Writer() throws IOException
	{
		InetAddress addr = InetAddress.getByName("127.0.0.1");
		Socket c = new Socket(addr, 9821);
		writer = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
		reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
	}
	
	public void print(String s)
	{
		try {
			writer.write(s);
			writer.newLine();
			writer.flush();
			final int x = reader.read();
			if (x!=0)
			{
				System.err.println("Difference at " +s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
