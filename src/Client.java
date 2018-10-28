import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	private Socket connection;
	private DataInputStream dIn;
	private DataOutputStream dOut;
	private BufferedReader br;
	
	public Client() throws IOException {
		this.connection = new Socket("0.0.0.0",32581); // pauses code until a client connects
		
		this.dIn = new DataInputStream(connection.getInputStream());
		this.dOut = new DataOutputStream(connection.getOutputStream());
		
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void initiate() throws IOException {
		String inLine="", outLine="";
		
		while (!inLine.equals("end")) {
			outLine = br.readLine();
			dOut.writeUTF(outLine);
			inLine = dIn.readUTF();
			System.out.println(inLine);
		}
	}
}