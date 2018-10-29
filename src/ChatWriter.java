import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatWriter implements Runnable{
	private DataOutputStream dOut;
	private BufferedReader br;
	
	public ChatWriter(DataOutputStream dOut) {
		this.dOut = dOut;
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void run() {
		String outLine = "";
		String line;
		
		try {
			while (true) {
				outLine = br.readLine();
				dOut.writeUTF(outLine);
				dOut.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
