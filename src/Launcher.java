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
		String chatType = "";
		
		if (userInput.equals("1")) {
			chatType = "Client";
		} else if (userInput.equals("2")) {
			chatType = "Server";
			
		} else {
			System.out.println("Wrong input. Terminating.");
			return;
		}

		Runnable r = new GroupChat(chatType);
		new Thread(r).start();
	}
}