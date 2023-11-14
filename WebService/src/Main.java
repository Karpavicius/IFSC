import java.io.IOException;
import java.net.ServerSocket;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket(8080);
			Thread swh;
			while (true) {
				swh = new Thread(new ServidorWorkerHTTP(ss.accept()));

				swh.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
