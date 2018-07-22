package cyberport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RentAsker {

	public static void main(final String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	void run()
	{

		try
		{
			final URL url =	new URL("https://lms.cyberport.hk/EnquireUASmartSpace");


			final HttpsURLConnection conn= (HttpsURLConnection)url.openConnection();
			
			
			final InputStream in = conn.getInputStream();
			
			parse(in);
		}
		catch (final IOException ex)
		{
			ex.printStackTrace();
		}
	}



	private void parse(final InputStream in_) throws IOException {
		final BufferedReader in = new BufferedReader(new InputStreamReader(in_));
		
		boolean on = false;
		String line;
		while ((line = in.readLine())!=null)
		{
			if (!on)
			{
				on = line.contains("Office Room");
			}
			else
			{
				
			}
		}
	}
}
