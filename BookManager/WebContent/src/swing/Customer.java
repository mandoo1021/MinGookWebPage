package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import errorAndfalse.RecordNotFoundException;

//import org.w3c.dom.events.MouseEvent;

import ezenproject.DAO;
import ezenproject.DTO;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Customer extends JFrame {

	private JPanel contentPane;
	private JTextField tfsearch;
	static Customer frame;
	DefaultTableModel tableModel;
	DAO dao;
	private JTable table;

	public Customer() {
		
		dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
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

		JButton searchButton = new JButton("검색");
		searchButton.setBounds(510, 70, 60, 23);
		contentPane.add(searchButton);

		JLabel lblsearch = new JLabel("도서 검색");
		lblsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblsearch.setBounds(255, 20, 154, 35);
		lblsearch.setFont(new Font("dialog", Font.PLAIN | Font.BOLD, 20));
		lblsearch.setFont(lblsearch.getFont().deriveFont(30.0f));
		contentPane.add(lblsearch);

		tfsearch = new JTextField();
		tfsearch.setBounds(200, 72, 300, 21);
		contentPane.add(tfsearch);
		tfsearch.setColumns(10);

		JButton backButton = new JButton("뒤로가기");
		backButton.setBounds(400, 400, 97, 23);
		contentPane.add(backButton);

		JButton endButton = new JButton("종료");
		endButton.setBounds(530, 400, 97, 23);
		contentPane.add(endButton);

		String[] bookKinds = {"도서명으로 검색", "저자명으로 검색", "출판사로 검색", "장르별로 검색"};

		JComboBox searchCombo = new JComboBox(bookKinds);
		searchCombo.setBounds(60, 72, 130, 21);
		contentPane.add(searchCombo);



		//종료 버튼
		endButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// frame.setVisible(true);

		//뒤로가기 버튼
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				FirstScreen firstScreen = new FirstScreen();
				firstScreen.setVisible(true);
			}
		});

		//전체 검색 기능
		String header[] = {"코드","책이름","저자","출판사","출판일","책상태","장르","재고량","위치"};

		String content[][] = {
				{"코드1","책이름1","저자1","출판사1","출판일1","책상태1","장르1","재고량1","위치1"},
				{"코드2","책이름2","저자2","출판사2","출판일2","책상태2","장르2","재고량2","위치2"},
				{"코드3","책이름3","저자3","출판사3","출판일3","책상태3","장르3","재고량3","위치3"}
		};

		//테이블모델 생성
		tableModel = new DefaultTableModel(content, header);

		//테이블 모델 값들 초기화
		tableModel.setNumRows(0);

		ArrayList<DTO> dtolistall = new ArrayList<DTO>();

		//데이터 베이스 안에 있는 값들 전체 검색
		dtolistall = dao.searchAll();

		//사이즈 체크
		int dtoSizeall = dtolistall.size();

		//테이블 값에 하나하나 채워넣기
		for(int i = 0; i< dtoSizeall; i++) {

			String newcontent[] = new String[9];

			//"코드","책이름","저자","출판사","출판일","책상태","장르","Remain","Category"
			newcontent[0] = dtolistall.get(i).getCode();
			newcontent[1] = dtolistall.get(i).getBookname();
			newcontent[2] = dtolistall.get(i).getAuthor();
			newcontent[3] = dtolistall.get(i).getEditorial();

			Date from = dtolistall.get(i).getPublish_day();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd");
			String to = transFormat.format(from);

			newcontent[4] = to;
			newcontent[5] = dtolistall.get(i).getCondition();
			newcontent[6] = dtolistall.get(i).getKind();

			int remainvalue = dtolistall.get(i).getRemain();
			String remainstring = Integer.toString(remainvalue);
			newcontent[7] = remainstring;			
			int categoryvalue = dtolistall.get(i).getCategory();
			String categorystring = Integer.toString(categoryvalue);
			newcontent[8] = categorystring;

			tableModel.insertRow(i, newcontent);

		}
		//JTable을 생성
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(54, 130, 580, 250);
		contentPane.add(scrollPane);
		
		
		// 검색버튼 (저자명 , 출판사명, 도서명 분류 검색 기능 ㅇ)
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = searchCombo.getSelectedIndex();
				switch(index) {
				case 0 : {
					//텍스트 필드에서 값을 가져온다 (책이름, 출판사로 검색)
					String searchText = tfsearch.getText();
					ArrayList<DTO> dtolist = new ArrayList<DTO>();

					try {			
						dtolist = dao.search(searchText);

					} catch (RecordNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//쿼리문 있는 클래스 메소드에 검색어를 보낸다. 

					tableModel.setNumRows(0);

					int dtoSize = dtolist.size();


					for(int i = 0; i< dtoSize; i++) {

						String newcontent[] = new String[9];

						//"코드","책이름","저자","출판사","출판일","책상태","장르","Remain","Category"
						newcontent[0] = dtolist.get(i).getCode();
						newcontent[1] = dtolist.get(i).getBookname();
						newcontent[2] = dtolist.get(i).getAuthor();
						newcontent[3] = dtolist.get(i).getEditorial();

						Date from = dtolist.get(i).getPublish_day();
						SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy/MM/dd");
						String to1 = transFormat1.format(from);
						newcontent[4] = to1;

						newcontent[5] = dtolist.get(i).getCondition();
						newcontent[6] = dtolist.get(i).getKind();

						int remainvalue = dtolist.get(i).getRemain();
						String remainstring = Integer.toString(remainvalue);
						newcontent[7] = remainstring;

						int categoryvalue = dtolist.get(i).getCategory();
						String categorystring = Integer.toString(categoryvalue);
						newcontent[8] = categorystring;

						tableModel.insertRow(i, newcontent);

					}

					//테이블에 바뀐정보를 반영
					table.updateUI();
					
				}break;
				case 1 : {
					//텍스트 필드에서 값을 가져온다 (책이름, 출판사로 검색)
					String searchText = tfsearch.getText();
					ArrayList<DTO> dtolist = new ArrayList<DTO>();

					try {			
						dtolist = dao.searchAuthor(searchText);

					} catch (RecordNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//쿼리문 있는 클래스 메소드에 검색어를 보낸다. 

					tableModel.setNumRows(0);

					int dtoSize = dtolist.size();


					for(int i = 0; i< dtoSize; i++) {

						String newcontent[] = new String[9];

						//"코드","책이름","저자","출판사","출판일","책상태","장르","Remain","Category"
						newcontent[0] = dtolist.get(i).getCode();
						newcontent[1] = dtolist.get(i).getBookname();
						newcontent[2] = dtolist.get(i).getAuthor();
						newcontent[3] = dtolist.get(i).getEditorial();

						Date from = dtolist.get(i).getPublish_day();
						SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy/MM/dd");
						String to1 = transFormat1.format(from);
						newcontent[4] = to1;

						newcontent[5] = dtolist.get(i).getCondition();
						newcontent[6] = dtolist.get(i).getKind();

						int remainvalue = dtolist.get(i).getRemain();
						String remainstring = Integer.toString(remainvalue);
						newcontent[7] = remainstring;

						int categoryvalue = dtolist.get(i).getCategory();
						String categorystring = Integer.toString(categoryvalue);
						newcontent[8] = categorystring;

						tableModel.insertRow(i, newcontent);

					}

					//테이블에 바뀐정보를 반영
					table.updateUI();
				}break;
				case 2 : {
					//텍스트 필드에서 값을 가져온다 (책이름, 출판사로 검색)
					String searchText = tfsearch.getText();
					ArrayList<DTO> dtolist = new ArrayList<DTO>();

					try {			
						dtolist = dao.searchEditorial(searchText);

					} catch (RecordNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//쿼리문 있는 클래스 메소드에 검색어를 보낸다. 

					tableModel.setNumRows(0);

					int dtoSize = dtolist.size();


					for(int i = 0; i< dtoSize; i++) {

						String newcontent[] = new String[9];

						//"코드","책이름","저자","출판사","출판일","책상태","장르","Remain","Category"
						newcontent[0] = dtolist.get(i).getCode();
						newcontent[1] = dtolist.get(i).getBookname();
						newcontent[2] = dtolist.get(i).getAuthor();
						newcontent[3] = dtolist.get(i).getEditorial();

						Date from = dtolist.get(i).getPublish_day();
						SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy/MM/dd");
						String to1 = transFormat1.format(from);
						newcontent[4] = to1;

						newcontent[5] = dtolist.get(i).getCondition();
						newcontent[6] = dtolist.get(i).getKind();

						int remainvalue = dtolist.get(i).getRemain();
						String remainstring = Integer.toString(remainvalue);
						newcontent[7] = remainstring;

						int categoryvalue = dtolist.get(i).getCategory();
						String categorystring = Integer.toString(categoryvalue);
						newcontent[8] = categorystring;

						tableModel.insertRow(i, newcontent);

					}

					//테이블에 바뀐정보를 반영
					table.updateUI();
				}break;
				case 3 : {
					//텍스트 필드에서 값을 가져온다 (책이름, 출판사로 검색)
					String searchText = tfsearch.getText();
					ArrayList<DTO> dtolist = new ArrayList<DTO>();

					try {			
						dtolist = dao.searchKind(searchText);

					} catch (RecordNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//쿼리문 있는 클래스 메소드에 검색어를 보낸다. 

					tableModel.setNumRows(0);

					int dtoSize = dtolist.size();


					for(int i = 0; i< dtoSize; i++) {

						String newcontent[] = new String[9];

						//"코드","책이름","저자","출판사","출판일","책상태","장르","Remain","Category"
						newcontent[0] = dtolist.get(i).getCode();
						newcontent[1] = dtolist.get(i).getBookname();
						newcontent[2] = dtolist.get(i).getAuthor();
						newcontent[3] = dtolist.get(i).getEditorial();

						Date from = dtolist.get(i).getPublish_day();
						SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy/MM/dd");
						String to1 = transFormat1.format(from);
						newcontent[4] = to1;

						newcontent[5] = dtolist.get(i).getCondition();
						newcontent[6] = dtolist.get(i).getKind();

						int remainvalue = dtolist.get(i).getRemain();
						String remainstring = Integer.toString(remainvalue);
						newcontent[7] = remainstring;

						int categoryvalue = dtolist.get(i).getCategory();
						String categorystring = Integer.toString(categoryvalue);
						newcontent[8] = categorystring;

						tableModel.insertRow(i, newcontent);

					}

					//테이블에 바뀐정보를 반영
					table.updateUI();
				} break;
				default : break;
				}
				
			}
		});

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Customer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}


}
