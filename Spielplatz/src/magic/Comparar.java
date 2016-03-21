package magic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Comparar {

	public static void main(String[] args) {
		new Comparar().run();
	}
		
	
	public void run()
	{
		try {
			ServerSocket ss = new ServerSocket(9821);
			Socket sockets[] = new Socket[2];
			
			for (int i = 0; i < sockets.length; i++) {				
				sockets[i] = ss.accept();
			}
			
			
			compare(sockets);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	private void compare(Socket[] sockets) throws IOException {
		BufferedReader readers[] = new BufferedReader[sockets.length];
		OutputStreamWriter writers[] = new OutputStreamWriter[sockets.length];
		
		for (int i = 0; i < readers.length; i++) {
			readers[i] = new BufferedReader(new InputStreamReader(sockets[i].getInputStream()));
			writers[i] = new OutputStreamWriter(sockets[i].getOutputStream());
		}
		
		boolean good =true;
		
		while (good)
		{
			String x1 = readers[0].readLine();
			for (int i = 1; i < readers.length; i++) {
				final String x2 = readers[i].readLine();
				good = x1.equals(x2);
				if (!good)
					break;
			}
			for (int i = 0; i < writers.length; i++) {
				writers[i].write(good?'0':'X');
				writers[i].flush();
			}
		}
		
		for (int i = 0; i < sockets.length; i++) {
			sockets[i].close();
		}

	}
}
