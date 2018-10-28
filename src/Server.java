import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket server;
	private Socket connection;
	private DataInputStream dIn;
	private DataOutputStream dOut;
	private BufferedReader br;
	
	public Server() throws IOException {
		this.server = new ServerSocket(32581);
		this.connection = server.accept(); // pauses code until a client connects
		
		this.dIn = new DataInputStream(connection.getInputStream());
		this.dOut = new DataOutputStream(connection.getOutputStream());
		
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void initiate() throws IOException {
		String inLine="", outLine="";
		
		while (!inLine.equals("end")) {
			inLine = dIn.readUTF();
			System.out.println(inLine);
			outLine = br.readLine();
			dOut.writeUTF(outLine);
			dOut.flush();
		}
		
		connection.close();
	}
}