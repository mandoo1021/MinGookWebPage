package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField textField;
	static Login frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 346);
		ImageIcon img = new ImageIcon("images/img2.jpg");
		JLabel imgLabel = new JLabel(img);
//		contentPane = new JPanel();
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0, 0, 800, 500, null);
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton loginButton = new JButton("\uB85C\uADF8\uC778");
		loginButton.setBounds(330, 110, 97, 23);
		contentPane.add(loginButton);

		JButton endButton = new JButton("\uC885\uB8CC");
		endButton.setBounds(250, 191, 97, 23);
		contentPane.add(endButton);
		
		JButton backButton = new JButton("뒤로 가기");
		backButton.setBounds(120, 191, 97, 23);
		contentPane.add(backButton);
		
		

		JLabel lblNewLabel = new JLabel("관리자 로그인");
		lblNewLabel.setFont(new Font("dialog", Font.PLAIN | Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(155, 34, 154, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uAD00\uB9AC\uC790\uCF54\uB4DC");
		lblNewLabel_1.setFont(new Font("dialog", Font.PLAIN | Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(45, 116, 100, 15);
		contentPane.add(lblNewLabel_1);

		//관리자 코드
		textField = new JPasswordField();
		textField.setBounds(140, 113, 180, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		// 엔터키 ing~
//		
//		Action ok = new AbstractAction() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//

//		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
//		textField = (JPasswordField) new JTextField();
//		textField.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "ENTER");
//		textField.getActionMap().put("ENTER", ok);



		loginButton.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id="1";

				if(id.equals(textField.getText()))
				{ JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다");

				Menu Menuframe = new Menu();
				contentPane.setVisible(false);
				Menuframe.setVisible(true);



				}else { 
					JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다");
				}


			}

		});

		loginButton.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {

					loginButton.doClick(); 

				}


			}
		});

		// 종료버튼
		endButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		
		// 뒤로가기 버튼
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				contentPane.setVisible(false);
				FirstScreen firstScreen = new FirstScreen();
				firstScreen.setVisible(true);
				
			}
		});
		
		

	}
}