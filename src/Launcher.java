import java.io.IOException;
import java.util.Scanner;

public class Launcher {	
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		
		System.out.println("CHOOSE AN OPTION");
		System.out.println("1. Client");
		System.out.println("2. Server");
		System.out.print("--> ");
		
		String userInput = reader.nextLine();
		
		if (userInput.equals("1")) {
			Client client = new Client();
			client.initiate();
		} else if (userInput.equals("2")) {
			Server server = new Server();
			server.initiate();
		}
		
		reader.close();
	}

}