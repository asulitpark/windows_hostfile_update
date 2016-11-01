import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HostFileChangeMain {
	private static HostfileChanger hostfileChanger = new HostfileChanger();
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(7969);
		
		// System.out.println("Listen...");
		
		while(true) {
			Socket socket = serverSocket.accept();
			String address = socket.getInetAddress().getHostAddress();
			hostfileChanger.change(address);
			socket.close();
		}
	}
}
