package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chatting.ChatClient;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Menu() {
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

		JLabel backLabel = new JLabel("New label");
		backLabel.setIcon(new ImageIcon("img/javalogin.png"));

		JButton sqlButton = new JButton("SQL\uBAA8\uB4DC");
		sqlButton.setBounds(105, 114, 97, 74);
		contentPane.add(sqlButton);

		JButton excelButton = new JButton("Excel\uBAA8\uB4DC");
		excelButton.setBounds(273, 114, 97, 74);
		contentPane.add(excelButton);

		JButton backButton = new JButton("뒤로가기");
		backButton.setBounds(258, 250, 97, 23);
		contentPane.add(backButton);

		JLabel lblNewLabel = new JLabel("\uBAA8\uB4DC\uC120\uD0DD");
		lblNewLabel.setFont(new Font("dialog", Font.PLAIN | Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(155, 34, 154, 35);
		contentPane.add(lblNewLabel);

		JButton chatbtn = new JButton("\uCC44\uD305\uBAA8\uB4DC");
		chatbtn.setBounds(115, 250, 97, 23);
		contentPane.add(chatbtn);

		sqlButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SqlMode SqlModeframe = new SqlMode();
				contentPane.setVisible(false);
				SqlModeframe.setVisible(true);
			}

		});

		excelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ExcelMode excelMode = new ExcelMode();
				contentPane.setVisible(false);
				excelMode.setVisible(true);
			}

		});

		// 뒤로가기 버튼
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Login login = new Login();
				login.setVisible(true);
			}
		});

		chatbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChatClient cc =	new ChatClient();


			}
		});
	}
}

