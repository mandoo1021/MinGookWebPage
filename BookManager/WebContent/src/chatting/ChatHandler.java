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
			name = br.readLine(); //대화명
			cs.register(this); //벡터등록
			cs.broadcast(name + "님이 입장하셨습니다.");

			while(true){
				String data = br.readLine(); //데이터 읽어오기
				if(data == null || data.toLowerCase().equals("quit"))
					break;
				cs.broadcast("["+name+"]"+data); //모든 클라이언트 알려주기
			} //while
		}catch(IOException io){
			io.printStackTrace();
		}
		// --------------------------------------

		cs.unregister(this); //벡터제거
		cs.broadcast(name + "님이 퇴장하셨습니다.");
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