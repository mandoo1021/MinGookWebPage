package swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FirstScreen extends JFrame {

	private JPanel contentPane;
	private JPasswordField textField;
	static FirstScreen frame;


	public FirstScreen() {


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		ImageIcon img = new ImageIcon("images/img1.jpg");
		JLabel imgLabel = new JLabel(img);
//		contentPane = new JPanel();
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0, 0, 900, 560, null);
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);




		JButton customerButton = new JButton("도서 검색");
		customerButton.setBounds(650, 120, 150, 75);
		contentPane.add(customerButton);

		JButton managerButton = new JButton("관리자 로그인");
		managerButton.setBounds(650, 240, 150, 75);
		contentPane.add(managerButton);

		JButton exitButton = new JButton("프로그램 종료");
		exitButton.setBounds(650, 360, 150, 75);
		contentPane.add(exitButton);

		JLabel lblTitle = new JLabel("이젠 도서관 프로그램");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(50, 50, 600, 100);
		lblTitle.setFont(new Font("dialog", Font.PLAIN | Font.BOLD, 40));
		lblTitle.setFont(lblTitle.getFont().deriveFont(30.0f));
		lblTitle.setForeground(Color.BLACK);
		contentPane.add(lblTitle);



		// 도서검색 버튼
		customerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Customer customer = new Customer();
				customer.setVisible(true);

			}
		});

		// 관리자 로그인 버튼
		managerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Login login = new Login();
				login.setVisible(true);

			}
		});


		// 종료 버튼
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
	}



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FirstScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
