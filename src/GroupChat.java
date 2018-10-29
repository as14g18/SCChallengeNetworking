import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Version 2
 * -------------------------------------------------------------------------
 * Program asks whether to open a server
 * If yes, creates new server socket (possibly searches for available ports)
 * Otherwise connects to existing server socket (asks for IP and username)
 * Sets up data streams and buffered reader
 * Each individual instance of the program uses 3 threads each
 * Thread 1: The current instance itself
 * Thread 2: Listener, listens to and outputs data being streamed to it
 * Thread 3: Writer, writes data to output stream
 * Each thread will be a separate class
 * -------------------------------------------------------------------------
 */

public class GroupChat implements Runnable {
	private ServerSocket server;
	private Socket connection;
	private DataInputStream dIn;
	private DataOutputStream dOut;
	
	private ChatListener chatListener;
	private ChatWriter chatWriter;
	
	private String chatType;
	private static final int PORT_NUMBER = 32582;
	
	public GroupChat(String chatType) throws IOException {
		this.chatType = chatType;
	}
	
	@Override
	public void run() {
		try {
			if (chatType.equals("Server")) {
				this.server = new ServerSocket(PORT_NUMBER);
				this.connection = server.accept(); // pauses code until a client connects
			} else if (chatType.equals("Client"))  {
				this.connection = new Socket("localhost", PORT_NUMBER);
			}
			 
			this.dIn = new DataInputStream(connection.getInputStream());
			this.dOut = new DataOutputStream(connection.getOutputStream());
			this.chatListener = new ChatListener(dIn);
			this.chatWriter = new ChatWriter(dOut);
			Runnable listenThread = chatListener, writeThread = chatWriter;
			new Thread(listenThread).start();
			new Thread(writeThread).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
}