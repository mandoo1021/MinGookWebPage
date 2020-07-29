package chatting;

import java.net.*;
import java.io.*;

class ChatHandler extends Thread {
	private ChatServer cs;
	private Socket so;
	private BufferedReader br;
	private PrintWriter pw;

	public ChatHandler(ChatServer cs, Socket so) throws IOException{
		this.cs = cs;
		this.so = so;
		br = new BufferedReader(new InputStreamReader(so.getInputStream()));
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(so.getOutputStream())));
	}
	public void run(){
		String name = null;
		try{
			name = br.readLine(); //��ȭ��
			cs.register(this); //���͵��
			cs.broadcast(name + "���� �����ϼ̽��ϴ�.");

			while(true){
				String data = br.readLine(); //������ �о����
				if(data == null || data.toLowerCase().equals("quit"))
					break;
				cs.broadcast("["+name+"]"+data); //��� Ŭ���̾�Ʈ �˷��ֱ�
			} //while
		}catch(IOException io){
			io.printStackTrace();
		}
		// --------------------------------------

		cs.unregister(this); //��������
		cs.broadcast(name + "���� �����ϼ̽��ϴ�.");
		try{
			br.close();
			pw.close();
			so.close();
		}catch(IOException io){
			io.printStackTrace();
		}
	} //run()

	public PrintWriter getPrintWriter(){
		return pw;
	}
}