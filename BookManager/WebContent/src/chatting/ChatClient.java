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
		output.setFont(new Font("�������ü",Font.BOLD, 14));
		output.setForeground(new Color(0,0,255));
		input = new JTextField(20);
		input.setFont(new Font("�������ü",Font.BOLD, 12));
		sendB = new JButton("������");

		JPanel p = new JPanel();// FlowLayout(������ġ - �߾�)
		p.setLayout(new BorderLayout());

		p.add("Center",input);
		p.add("East",sendB);

		getContentPane().add("Center", op);
		getContentPane().add("South",p);

		setTitle("ChatClient");
		setBounds(200, 100, 400, 400);
		setVisible(true);
		//setDefaultcloseOperation(EXIT_ON_CLOSE); //����

		//���� IP�Է�
		serverIP = JOptionPane.showInputDialog(
				this,
				"����IP�� �Է����ּ���.",
				"����IP",
				JOptionPane.QUESTION_MESSAGE);

		if(serverIP == null || serverIP.trim().length()<1)System.exit(0);

		//��ȭ���Է�
		name = JOptionPane.showInputDialog(
				this,
				"�����ڸ��� �Է����ּ���.",
				"�����ڸ�",
				JOptionPane.QUESTION_MESSAGE);

		//���ϻ��� - IO
		try{
			so = new Socket(serverIP, 5010);
			br = new BufferedReader(new InputStreamReader(so.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(so.getOutputStream())));

			pw.println(name);// ������ ��ȭ�� ������
			pw.flush(); //���۸� ����

			//Thread
			t = new Thread(this); //������ ����
			t.start(); //������ ���� -> ������ ���� run()
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException io){
			io.printStackTrace();
		}
		//�̺�Ʈ

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
		data = input.getText(); //JtextField�� �ִ� ������ ������
		pw.println(data);//������ ������ ������
		pw.flush();
		input.setText(""); //JTextField �ʱ�ȭ
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
				output.append(data+ "\n"); //JTextArea �� �Ѹ���

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