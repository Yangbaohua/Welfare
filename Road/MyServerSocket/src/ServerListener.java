import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


public class ServerListener extends Thread{
	
	@Override
	public void run() {
		//�˿ڷ�Χ:1-65535
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(1989);
			while(true) {
				Socket socket = serverSocket.accept();
				//JOptionPane.showMessageDialog(null, "�пͻ������ӵ���1989�˿�");
				System.out.println("�пͻ������ӵ���1989�˿�");
				ChatSocket cs = new ChatSocket(socket);
				cs.start();
				ChatManager.getChatManager().add(cs);
			}
		} catch (IOException e) {
			System.out.println("���Ӵ���");
		}	
	}
}
