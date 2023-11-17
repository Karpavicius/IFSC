import java.io.IOException;
import java.net.ServerSocket;
import java.text.ParseException;

public class Main {

	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(6666);
			ServerCristian sc = new ServerCristian(ss);
			sc.startListen();
			
		} catch (IOException | ParseException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
