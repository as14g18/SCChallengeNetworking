import java.io.DataInputStream;
import java.io.IOException;

public class ChatListener implements Runnable{
	private DataInputStream dIn;
	
	public ChatListener(DataInputStream dIn) {
		this.dIn = dIn;
	}

	@Override
	public void run() {
		String inLine = "";
		
		while (!inLine.equals("end")) {
			try {
				inLine = dIn.readUTF();
				System.out.println(inLine);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
