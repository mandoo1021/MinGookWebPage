package swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import errorAndfalse.RecordNotFoundException;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class ExcelMode extends JFrame {

	private JPanel contentPane;
	private JTextField searchLabel;
	private JTextField codeLabel;
	private JTextField chulpanLabel;
	private JTextField genreLabel;
	private JTextField booknameLabel;
	private JTextField dateLabel;
	private JTextField RemainLabel;
	private JTextField authorLabel;
	private JTextField conditionLabel;
	private JTextField categoryLabel;
	private JTable table;
	DAO dao;
	DefaultTableModel tableModel;
	String[][] content;
	int selectedrow = -1;

	/**
	 * Create the frame.
	 */
	public ExcelMode() {

		manageExcel excel = new manageExcel();
		//쿼리문을 쓰기위해 DAO를 미리 생성해둠
		dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 670);
		ImageIcon img = new ImageIcon("images/img2.jpg");
		JLabel imgLabel = new JLabel(img);
//		contentPane = new JPanel();
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0, 0, 1200, 800, null);
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uAD00\uB9AC \uD654\uBA74 (Excel Mode)");
		lblNewLabel.setFont(new Font("dialog", Font.PLAIN | Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(56, 26, 250, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uCF54\uB4DC");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(70, 97, 57, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\uCC45\uC774\uB984");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(386, 100, 57, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\uCD9C\uD310\uC0AC");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(70, 148, 57, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("\uC7A5\uB974");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(70, 202, 57, 15);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("\uCD9C\uD310\uC77C");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(386, 151, 57, 15);
		contentPane.add(lblNewLabel_6);

		searchLabel = new JTextField();
		searchLabel.setBounds(609, 27, 116, 21);
		contentPane.add(searchLabel);
		searchLabel.setColumns(10);

		JButton searchButton = new JButton("\uAC80\uC0C9");
		searchButton.setBounds(752, 26, 97, 23);
		contentPane.add(searchButton);

		JButton overlapButton = new JButton("중복체크");
		overlapButton.setBounds(270, 93, 88, 23);
		contentPane.add(overlapButton);

		//액션 리스너

		JLabel lblNewLabel_7 = new JLabel("재고");
		lblNewLabel_7.setBounds(401, 205, 57, 15);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("\uC800\uC790");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(665, 94, 57, 15);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("\uCC45\uC0C1\uD0DC");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(665, 145, 57, 15);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("위치");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(665, 199, 57, 15);
		contentPane.add(lblNewLabel_10);

		codeLabel = new JTextField();
		codeLabel.setBounds(149, 94, 116, 21);
		contentPane.add(codeLabel);
		codeLabel.setColumns(10);

		chulpanLabel = new JTextField();
		chulpanLabel.setBounds(149, 145, 116, 21);
		contentPane.add(chulpanLabel);
		chulpanLabel.setColumns(10);

		genreLabel = new JTextField();
		genreLabel.setBounds(149, 199, 116, 21);
		contentPane.add(genreLabel);
		genreLabel.setColumns(10);

		booknameLabel = new JTextField();
		booknameLabel.setBounds(455, 97, 116, 21);
		contentPane.add(booknameLabel);
		booknameLabel.setColumns(10);

		dateLabel = new JTextField("ex) xxxx/xx/xx");
		dateLabel.setBounds(455, 148, 116, 21);
		contentPane.add(dateLabel);
		dateLabel.setColumns(10);

		RemainLabel = new JTextField();
		RemainLabel.setBounds(455, 202, 116, 21);
		contentPane.add(RemainLabel);
		RemainLabel.setColumns(10);

		authorLabel = new JTextField();
		authorLabel.setBounds(734, 91, 116, 21);
		contentPane.add(authorLabel);
		authorLabel.setColumns(10);

		conditionLabel = new JTextField();
		conditionLabel.setBounds(734, 142, 116, 21);
		contentPane.add(conditionLabel);
		conditionLabel.setColumns(10);

		categoryLabel = new JTextField();
		categoryLabel.setBounds(734, 196, 116, 21);
		contentPane.add(categoryLabel);
		categoryLabel.setColumns(10);

		JButton addButton = new JButton("\uB370\uC774\uD130 \uCD94\uAC00");
		addButton.setBounds(80, 569, 110, 23);
		contentPane.add(addButton);

		JButton fixButton = new JButton("\uC218\uC815");
		fixButton.setBounds(415, 569, 110, 23);
		contentPane.add(fixButton);

		JButton deleteButton = new JButton("\uB370\uC774\uD130 \uC0AD\uC81C");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		deleteButton.setBounds(247, 569, 110, 23);
		contentPane.add(deleteButton);

		JButton backButton = new JButton("뒤로가기");
		backButton.setBounds(582, 569, 110, 23);
		contentPane.add(backButton);

		JButton endButton = new JButton("종료");
		endButton.setBounds(750, 569, 110, 23);
		contentPane.add(endButton);

		JButton makeButton = new JButton("Excel\uD30C\uC77C \uB9CC\uB4E4\uAE30");
		makeButton.setBounds(772, 482, 134, 43);
		contentPane.add(makeButton);

		JLabel floorRemain = new JLabel("층별 재고수량");
		floorRemain.setHorizontalAlignment(SwingConstants.CENTER);
		floorRemain.setBounds(772, 260, 134, 22);
		contentPane.add(floorRemain);

		String[] floorKinds = {"1F","2F","3F"};
		JComboBox floorCombo = new JComboBox(floorKinds);
		floorCombo.setBounds(772, 285, 50, 21);
		contentPane.add(floorCombo);

		JTextField floorLabel = new JTextField();
		floorLabel.setBounds(829, 285, 75, 22);
		contentPane.add(floorLabel);
		floorLabel.setColumns(10);


		String[] bookKinds = {"도서명으로 검색", "저자명으로 검색", "출판사로 검색", "장르별로 검색"};
		JComboBox searchCombo = new JComboBox(bookKinds);
		searchCombo.setBounds(450, 27, 130, 21);
		contentPane.add(searchCombo);

		//전체 검색 기능
		String header[] = {"코드","책이름","저자","출판사","출판일","책상태","장르","재고량","위치"};

		String content[][] = {
				{"코드1","책이름1","저자1","출판사1","출판일1","책상태1","장르1","재고량1","위치1"},
				{"코드2","책이름2","저자2","출판사2","출판일2","책상태2","장르2","재고량2","위치2"},
				{"코드3","책이름3","저자3","출판사3","출판일3","책상태3","장르3","재고량3","위치3"}
		};

		//테이블모델 생성
		tableModel = new DefaultTableModel(content, header);

		//JTable을 생성
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(54, 261, 706, 268);
		contentPane.add(scrollPane);

		JButton saveDBButton = new JButton("DB\uC5D0 \uC800\uC7A5\uD558\uAE30");
		saveDBButton.setBounds(772, 323, 134, 43);
		contentPane.add(saveDBButton);

		JButton loadDBButton = new JButton("DB\uC5D0\uC11C \uAC00\uC838\uC624\uAE30");
		loadDBButton.setBounds(772, 398, 134, 43);
		contentPane.add(loadDBButton);

		// 검색버튼 (저자명 , 출판사명, 도서명 분류 검색 기능 ㅇ)
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = searchCombo.getSelectedIndex();
				switch(index) {
				case 0 : {
					//텍스트 필드에서 값을 가져온다 (책이름, 출판사로 검색)
					String searchText = searchLabel.getText();
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
					String searchText = searchLabel.getText();
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
					String searchText = searchLabel.getText();
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
					String searchText = searchLabel.getText();
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

		//데이터 삭제
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//get text로 코드 값을 가져온다.
				String codetext = codeLabel.getText();
				dao.delete(codetext);

				//삭제 후 테이블 모델 값들 초기화
				tableModel.setNumRows(0);

				ArrayList<DTO> dtolistafterdelete = new ArrayList<DTO>();

				//데이터 베이스 안에 있는 값들 전체 검색
				dtolistafterdelete = dao.searchAll();

				//사이즈 체크
				int dtoSizeall = dtolistafterdelete.size();

				//테이블 값에 하나하나 채워넣기
				for(int i = 0; i< dtoSizeall; i++) {

					String newcontent[] = new String[9];

					//"코드","책이름","저자","출판사","출판일","책상태","장르","Remain","Category"
					newcontent[0] = dtolistafterdelete.get(i).getCode();
					newcontent[1] = dtolistafterdelete.get(i).getBookname();
					newcontent[2] = dtolistafterdelete.get(i).getAuthor();
					newcontent[3] = dtolistafterdelete.get(i).getEditorial();

					Date from = dtolistafterdelete.get(i).getPublish_day();
					SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy/MM/dd");
					String to1 = transFormat1.format(from);
					newcontent[4] = to1;

					newcontent[5] = dtolistafterdelete.get(i).getCondition();
					newcontent[6] = dtolistafterdelete.get(i).getKind();

					int remainvalue = dtolistafterdelete.get(i).getRemain();
					String remainstring = Integer.toString(remainvalue);
					newcontent[7] = remainstring;

					int categoryvalue = dtolistafterdelete.get(i).getCategory();
					String categorystring = Integer.toString(categoryvalue);
					newcontent[8] = categorystring;

					tableModel.insertRow(i, newcontent);

				}

				//테이블에 바뀐정보를 반영
				table.updateUI();

			}
		});	

		//마우스 클릭 삭제용으로
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// 마우스에서 선택한 줄번호취득
				selectedrow = table.getSelectedRow();

			}	
		});


		//수정버튼
		fixButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//get text로 모든 값을 가져온다
				String codetext = codeLabel.getText();
				String chulpan = chulpanLabel.getText();
				String genre = genreLabel.getText();
				String bookname = booknameLabel.getText();

				String date = dateLabel.getText();
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date to = new Date();
				try {
					to = transFormat.parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String Remain = RemainLabel.getText();
				int remainint = Integer.parseInt(Remain);

				String author = authorLabel.getText();
				String condition = conditionLabel.getText();

				String category = categoryLabel.getText();
				int categoryint = Integer.parseInt(category);

				//가져온 값으로 DTO생성

				DTO dto = new DTO();

				dto.setAuthor(author);
				dto.setBookname(bookname);
				dto.setCategory(categoryint);
				dto.setCode(codetext);
				dto.setCondition(condition);
				dto.setEditorial(chulpan);
				dto.setKind(genre);
				dto.setPublish_day(to);
				dto.setRemain(remainint);				

				dao.update(dto);

				//수정 후 테이블 모델 값들 초기화
				tableModel.setNumRows(0);

				ArrayList<DTO> dtolistafterfix = new ArrayList<DTO>();

				//데이터 베이스 안에 있는 값들 전체 검색
				dtolistafterfix = dao.searchAll();

				//사이즈 체크
				int dtoSizeall = dtolistafterfix.size();

				//테이블 값에 하나하나 채워넣기
				for(int i = 0; i< dtoSizeall; i++) {

					String newcontent[] = new String[9];

					//"코드","책이름","저자","출판사","출판일","책상태","장르","Remain","Category"
					newcontent[0] = dtolistafterfix.get(i).getCode();
					newcontent[1] = dtolistafterfix.get(i).getBookname();
					newcontent[2] = dtolistafterfix.get(i).getAuthor();
					newcontent[3] = dtolistafterfix.get(i).getEditorial();

					Date from = dtolistafterfix.get(i).getPublish_day();
					SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy/MM/dd");
					String to1 = transFormat1.format(from);
					newcontent[4] = to1;

					newcontent[5] = dtolistafterfix.get(i).getCondition();
					newcontent[6] = dtolistafterfix.get(i).getKind();

					int remainvalue = dtolistafterfix.get(i).getRemain();
					String remainstring = Integer.toString(remainvalue);
					newcontent[7] = remainstring;

					int categoryvalue = dtolistafterfix.get(i).getCategory();
					String categorystring = Integer.toString(categoryvalue);
					newcontent[8] = categorystring;

					tableModel.insertRow(i, newcontent);

				}

				//테이블에 바뀐정보를 반영
				table.updateUI();
			}
		});


		//엑셀 파일 만들기 버튼
		makeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int rowcount = tableModel.getRowCount();
				System.out.println(rowcount);
				ArrayList<Object[]> datalist = new ArrayList<Object[]>();

				for(int i = 0; i< rowcount; i++) {
					Object[] objectArray = new Object[9];
					objectArray[0] = tableModel.getValueAt(i, 0);
					objectArray[1] = tableModel.getValueAt(i, 1);
					objectArray[2] = tableModel.getValueAt(i, 2);
					objectArray[3] = tableModel.getValueAt(i, 3);
					objectArray[4] = tableModel.getValueAt(i, 4);
					objectArray[5] = tableModel.getValueAt(i, 5);
					objectArray[6] = tableModel.getValueAt(i, 6);
					objectArray[7] = tableModel.getValueAt(i, 7);
					objectArray[8] = tableModel.getValueAt(i, 8);
					datalist.add(objectArray);
				}

				excel.createExcelfile(datalist);
				
				JOptionPane.showMessageDialog(null, "파일이 저장되었습니다.\n경로:C:\\Temp\\ezenBook.xlsx");

			}
		});	

		//테이블 1출 추가 버튼
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String newcontent[] = new String[9];

				// "코드","책이름","저자","출판사","출판일","책상태","장르","Remain","Category"
				newcontent[0] = codeLabel.getText();
				newcontent[1] = booknameLabel.getText();
				newcontent[2] = authorLabel.getText();
				newcontent[3] = chulpanLabel.getText();
				newcontent[4] = dateLabel.getText();
				newcontent[5] = conditionLabel.getText();
				newcontent[6] = genreLabel.getText();
				newcontent[7] = RemainLabel.getText();
				newcontent[8] = categoryLabel.getText();

				tableModel.addRow(newcontent);

				// 테이블에 바뀐정보를 반영
				table.updateUI();

			}
		});	

		//DB에서 데이터 전부 가져오기

		loadDBButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

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

			}
		});	

		saveDBButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int rowcount = tableModel.getRowCount();
				System.out.println(rowcount);

				for(int i = 0; i< rowcount; i++) {

					DTO dto = new DTO();

					dto.setCode((String)tableModel.getValueAt(i, 0));
					dto.setBookname((String)tableModel.getValueAt(i, 1));
					dto.setAuthor((String)tableModel.getValueAt(i, 2));
					dto.setEditorial((String)tableModel.getValueAt(i, 3));

					String date = (String)tableModel.getValueAt(i, 4);
					SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date to = new Date();
					try {
						to = transFormat.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dto.setPublish_day(to);

					dto.setCondition((String)tableModel.getValueAt(i, 5));
					dto.setKind((String)tableModel.getValueAt(i, 6));

					String Remain =(String)tableModel.getValueAt(i, 7);
					int remainint = Integer.parseInt(Remain);
					dto.setRemain(remainint);

					String category = (String)tableModel.getValueAt(i, 8);
					int categoryint = Integer.parseInt(category);
					dto.setCategory(categoryint);

					dao.insert(dto);
				}

			}
		});	

		// 중복체크 버튼
		overlapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String code1 = codeLabel.getText();
				int tmp = 0;
				for(int i = 0; i < dao.select().size(); i++) {
					String overlapCode = dao.select().get(i).getCode();
					if(code1.equals(overlapCode)) { // AAA124 == AAA123
						JOptionPane.showMessageDialog(null, "중복된 코드 입니다.");
						break;
					} else if(tmp == 0) {
						JOptionPane.showMessageDialog(null, "사용가능한 코드 입니다.");
						break;
					}

				}

			}
		});

		// 층별 재고 확인
		floorCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = floorCombo.getSelectedIndex();
				int tmp = 0;
				switch(index) {
				case 0 : {
					for(int i = 0; i < dao.select().size(); i++) {
						if(dao.select().get(i).getCategory() == 1) {
							tmp += dao.select().get(i).getRemain();
						}
					}
					floorLabel.setText(String.valueOf(tmp));
				}break;
				case 1 : {
					for(int i = 0; i < dao.select().size(); i++) {
						if(dao.select().get(i).getCategory() == 2) {
							tmp += dao.select().get(i).getRemain();
						}
					}
					floorLabel.setText(String.valueOf(tmp));
				}break;
				case 2 : {
					for(int i = 0; i < dao.select().size(); i++) {
						if(dao.select().get(i).getCategory() == 3) {
							tmp += dao.select().get(i).getRemain();
						}
					}
					floorLabel.setText(String.valueOf(tmp));
				}break;
				default : break;
				}

			}
		});

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
				Menu menu = new Menu();
				menu.setVisible(true);

			}
		});

	}
}