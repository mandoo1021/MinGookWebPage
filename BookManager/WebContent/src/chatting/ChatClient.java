package chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends JFrame implements ActionListener,Runnable {
	JTextArea output;
	JTextField input;
	JButton sendB;
	String serverIP, name;
	Socket so;
	BufferedReader br;
	PrintWriter pw;
	Thread t;
	public ChatClient(){

		output = new JTextArea();
		JScrollPane op = new JScrollPane(output);
		output.setEditable(false);
		output.setFont(new Font("맑은고딕체",Font.BOLD, 14));
		output.setForeground(new Color(0,0,255));
		input = new JTextField(20);
		input.setFont(new Font("맑은고딕체",Font.BOLD, 12));
		sendB = new JButton("보내기");

		JPanel p = new JPanel();// FlowLayout(순서배치 - 중앙)
		p.setLayout(new BorderLayout());

		p.add("Center",input);
		p.add("East",sendB);

		getContentPane().add("Center", op);
		getContentPane().add("South",p);

		setTitle("ChatClient");
		setBounds(200, 100, 400, 400);
		setVisible(true);
		//setDefaultcloseOperation(EXIT_ON_CLOSE); //종료

		//서버 IP입력
		serverIP = JOptionPane.showInputDialog(
				this,
				"서버IP를 입력해주세요.",
				"서버IP",
				JOptionPane.QUESTION_MESSAGE);

		if(serverIP == null || serverIP.trim().length()<1)System.exit(0);

		//대화명입력
		name = JOptionPane.showInputDialog(
				this,
				"관리자명을 입력해주세요.",
				"관리자명",
				JOptionPane.QUESTION_MESSAGE);

		//소켓생성 - IO
		try{
			so = new Socket(serverIP, 5010);
			br = new BufferedReader(new InputStreamReader(so.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(so.getOutputStream())));

			pw.println(name);// 서버로 대화명 보내기
			pw.flush(); //버퍼를 비운다

			//Thread
			t = new Thread(this); //스레드 생성
			t.start(); //스레드 시작 -> 스레스 실행 run()
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException io){
			io.printStackTrace();
		}
		//이벤트

		input.addActionListener(this);
		sendB.addActionListener(this);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				pw.println("quit");
				pw.flush();

				try{
					br.close();
					pw.close();
					so.close();
				}catch(IOException io){
					io.printStackTrace();
				}
				//System.exit(0);
				dispose();
			}
		});
	} // ChatClient()

	public void actionPerformed(ActionEvent e){
		String data = null;
		data = input.getText(); //JtextField에 있는 데이터 얻어오기
		pw.println(data);//서버로 데이터 보내기
		pw.flush();
		input.setText(""); //JTextField 초기화
	}

	public void run(){
		String data = null;
		while(true){
			try{
				data = br.readLine();
				if(data == null || data.toLowerCase().equals("quit")){
					try{
						br.close();
						pw.close();
						so.close();
					}catch(IOException io){
						io.printStackTrace();
					}
					//System.exit(0);
					dispose();
				}
				output.append(data+ "\n"); //JTextArea 에 뿌리기

				int pos = output.getText().length();
				output.setCaretPosition(pos);

			}catch(IOException io){
				io.printStackTrace();
			}
		} //while
	} //run()

	public static void main(String[] args){
		new ChatClient();
	}
}