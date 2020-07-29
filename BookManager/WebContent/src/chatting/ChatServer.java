package chatting;

import java.net.*;
import java.util.*;

class ChatServer{

	ServerSocket ss;
	Vector<ChatHandler> v = new Vector<ChatHandler>();

	public ChatServer(){
		try{
			ss = new ServerSocket(9000);
			System.out.println("�����غ�Ϸ�");
			while(true){
				Socket so = ss.accept();
				ChatHandler ch = new ChatHandler(this, so); //������ ����
				ch.start(); // ��������� ->���������(run())
			} //while
		}catch(java.io.IOException io){
			io.printStackTrace();
		}
	} //ChatServer()


	public void register(ChatHandler ch){
		v.add(ch);
	}
	public void unregister(ChatHandler ch){
		v.remove(ch);
	}

	public void broadcast(String msg){
		Iterator<ChatHandler> it = v.iterator();
		while(it.hasNext()){
			ChatHandler ch = it.next();
			ch.getPrintWriter().println(msg);
			ch.getPrintWriter().flush();  //flush
		} //while
	}

	public static void main(String[] args){
		new ChatServer();
	}

}
