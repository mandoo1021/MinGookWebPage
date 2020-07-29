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
import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class SqlMode extends JFrame {

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

	/**
	 * Create the frame.
	 */
	public SqlMode() {

		//�������� �������� DAO�� �̸� �����ص�
		dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 670);
		ImageIcon img = new ImageIcon("images/img2.jpg");
		JLabel imgLabel = new JLabel(img);
//		contentPane = new JPanel();
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0, 0, 1200, 800, null);
				setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 

		JLabel lblNewLabel = new JLabel("\uAD00\uB9AC \uD654\uBA74 (SQL MODE)");
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

		JButton overlapButton = new JButton("�ߺ�üũ");
		overlapButton.setBounds(270, 93, 88, 23);
		contentPane.add(overlapButton);

		//�׼� ������

		JLabel lblNewLabel_7 = new JLabel("���");
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

		JLabel lblNewLabel_10 = new JLabel("��ġ");
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
		
		JLabel floorRemain = new JLabel("���� ������");
		floorRemain.setHorizontalAlignment(SwingConstants.CENTER);
		floorRemain.setBounds(760, 260, 134, 22);
		contentPane.add(floorRemain);
		
		String[] floorKinds = {"1F","2F","3F"};
		JComboBox floorCombo = new JComboBox(floorKinds);
		floorCombo.setBounds(760, 285, 50, 21);
		contentPane.add(floorCombo);
		
		JTextField floorLabel = new JTextField();
		floorLabel.setBounds(817, 285, 75, 22);
		contentPane.add(floorLabel);
		floorLabel.setColumns(10);
		
		JButton saveDBButton = new JButton("DB\uC5D0 \uC800\uC7A5\uD558\uAE30");
		saveDBButton.setBounds(760, 330, 134, 43);
		contentPane.add(saveDBButton);

		JButton loadDBButton = new JButton("DB\uC5D0\uC11C \uAC00\uC838\uC624\uAE30");
		loadDBButton.setBounds(760, 406, 134, 43);
		contentPane.add(loadDBButton);

		JButton resetButton = new JButton("\uD544\uB4DC \uCD08\uAE30\uD654");
		resetButton.setBounds(760, 482, 134, 43);
		contentPane.add(resetButton);

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

		JButton backButton = new JButton("�ڷΰ���");
		backButton.setBounds(582, 569, 110, 23);
		contentPane.add(backButton);

		JButton endButton = new JButton("����");
		endButton.setBounds(750, 569, 110, 23);
		contentPane.add(endButton);

		String[] bookKinds = {"���������� �˻�", "���ڸ����� �˻�", "���ǻ�� �˻�", "�帣���� �˻�"};
		JComboBox searchCombo = new JComboBox(bookKinds);
		searchCombo.setBounds(450, 27, 130, 21);
		contentPane.add(searchCombo);



		//��ü �˻� ���
		String header[] = {"�ڵ�","å�̸�","����","���ǻ�","������","å����","�帣","���","��ġ"};

		String content[][] = {
				{"�ڵ�1","å�̸�1","����1","���ǻ�1","������1","å����1","�帣1","���1","��ġ1"},
				{"�ڵ�2","å�̸�2","����2","���ǻ�2","������2","å����2","�帣2","���2","��ġ2"},
				{"�ڵ�3","å�̸�3","����3","���ǻ�3","������3","å����3","�帣3","���3","��ġ3"}
		};

		//���̺�� ����
		tableModel = new DefaultTableModel(content, header);

		//���̺� �� ���� �ʱ�ȭ
		tableModel.setNumRows(0);

		ArrayList<DTO> dtolistall = new ArrayList<DTO>();

		//������ ���̽� �ȿ� �ִ� ���� ��ü �˻�
		dtolistall = dao.searchAll();

		//������ üũ
		int dtoSizeall = dtolistall.size();

		//���̺� ���� �ϳ��ϳ� ä���ֱ�
		for(int i = 0; i< dtoSizeall; i++) {

			String newcontent[] = new String[9];

			//"�ڵ�","å�̸�","����","���ǻ�","������","å����","�帣","Remain","Category"
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

		//JTable�� ����
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(54, 261, 658, 268);
		contentPane.add(scrollPane);


		//Jtable�� ���콺 Ŭ��(mouseClicked)�� ó���� �̺�Ʈ ���

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				//���콺���� ������ �ٹ�ȣ��� (���� int)
				int selectedrow = table.getSelectedRow();

				//�ڵ�
				String code = (String) tableModel.getValueAt(selectedrow, 0);
				codeLabel.setText(code);

				//å�̸�
				String name = (String) tableModel.getValueAt(selectedrow, 1);
				booknameLabel.setText(name);
				//����
				String author = (String) tableModel.getValueAt(selectedrow, 2);
				authorLabel.setText(author);
				//���ǻ�
				String company = (String) tableModel.getValueAt(selectedrow, 3);
				chulpanLabel.setText(company);
				//������
				String date = (String) tableModel.getValueAt(selectedrow, 4);
				dateLabel.setText(date);
				//å����
				String condition = (String) tableModel.getValueAt(selectedrow, 5);
				conditionLabel.setText(condition);
				//�帣
				String genere = (String) tableModel.getValueAt(selectedrow, 6);
				genreLabel.setText(genere);
				//������
				String remain = (String) tableModel.getValueAt(selectedrow, 7);
				RemainLabel.setText(remain);
				//ī�װ�
				String category = (String) tableModel.getValueAt(selectedrow, 8);
				categoryLabel.setText(category);
			}
		});


		//������ �߰�
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//get text�� ��� ���� �����´�
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

				//������ ������ DTO����

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

				//DAO���� insert �Ѵ�
				//���̵� Ȯ�� ���� �ܼ�â üũ
				System.out.println(author);
				System.out.println(bookname);
				System.out.println(categoryint);
				System.out.println(codetext);
				System.out.println(condition);
				System.out.println(chulpan);
				System.out.println(genre);
				System.out.println(to);
				System.out.println(remainint);
				int result = dao.insert(dto);

				tableModel.setNumRows(0);

				ArrayList<DTO> dtolistaftercreate = new ArrayList<DTO>();

				//������ ���̽� �ȿ� �ִ� ���� ��ü �˻�
				dtolistaftercreate = dao.searchAll();

				//������ üũ
				int dtoSizeall = dtolistaftercreate.size();

				//���̺� ���� �ϳ��ϳ� ä���ֱ�
				for(int i = 0; i< dtoSizeall; i++) {

					String newcontent[] = new String[9];

					//"�ڵ�","å�̸�","����","���ǻ�","������","å����","�帣","Remain","Category"
					newcontent[0] = dtolistaftercreate.get(i).getCode();
					newcontent[1] = dtolistaftercreate.get(i).getBookname();
					newcontent[2] = dtolistaftercreate.get(i).getAuthor();
					newcontent[3] = dtolistaftercreate.get(i).getEditorial();

					Date from = dtolistaftercreate.get(i).getPublish_day();
					SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy/MM/dd");
					String to1 = transFormat1.format(from);
					newcontent[4] = to1;

					newcontent[5] = dtolistaftercreate.get(i).getCondition();
					newcontent[6] = dtolistaftercreate.get(i).getKind();

					int remainvalue = dtolistaftercreate.get(i).getRemain();
					String remainstring = Integer.toString(remainvalue);
					newcontent[7] = remainstring;

					int categoryvalue = dtolistaftercreate.get(i).getCategory();
					String categorystring = Integer.toString(categoryvalue);
					newcontent[8] = categorystring;

					tableModel.insertRow(i, newcontent);

				}

				//���̺� �ٲ������� �ݿ�
				table.updateUI();

				if(result!=0) {
					JOptionPane.showMessageDialog(null, "�Է¿Ϸ�");
				}else {
					JOptionPane.showMessageDialog(null, "�Է½���");
				}

			}

		});


		// �˻���ư (���ڸ� , ���ǻ��, ������ �з� �˻� ��� ��)
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = searchCombo.getSelectedIndex();
				switch(index) {
				case 0 : {
					//�ؽ�Ʈ �ʵ忡�� ���� �����´� (å�̸�, ���ǻ�� �˻�)
					String searchText = searchLabel.getText();
					ArrayList<DTO> dtolist = new ArrayList<DTO>();

					try {			
						dtolist = dao.search(searchText);

					} catch (RecordNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//������ �ִ� Ŭ���� �޼ҵ忡 �˻�� ������. 

					tableModel.setNumRows(0);

					int dtoSize = dtolist.size();


					for(int i = 0; i< dtoSize; i++) {

						String newcontent[] = new String[9];

						//"�ڵ�","å�̸�","����","���ǻ�","������","å����","�帣","Remain","Category"
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

					//���̺� �ٲ������� �ݿ�
					table.updateUI();

				}break;
				case 1 : {
					//�ؽ�Ʈ �ʵ忡�� ���� �����´� (å�̸�, ���ǻ�� �˻�)
					String searchText = searchLabel.getText();
					ArrayList<DTO> dtolist = new ArrayList<DTO>();

					try {			
						dtolist = dao.searchAuthor(searchText);

					} catch (RecordNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//������ �ִ� Ŭ���� �޼ҵ忡 �˻�� ������. 

					tableModel.setNumRows(0);

					int dtoSize = dtolist.size();


					for(int i = 0; i< dtoSize; i++) {

						String newcontent[] = new String[9];

						//"�ڵ�","å�̸�","����","���ǻ�","������","å����","�帣","Remain","Category"
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

					//���̺� �ٲ������� �ݿ�
					table.updateUI();
				}break;
				case 2 : {
					//�ؽ�Ʈ �ʵ忡�� ���� �����´� (å�̸�, ���ǻ�� �˻�)
					String searchText = searchLabel.getText();
					ArrayList<DTO> dtolist = new ArrayList<DTO>();

					try {			
						dtolist = dao.searchEditorial(searchText);

					} catch (RecordNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//������ �ִ� Ŭ���� �޼ҵ忡 �˻�� ������. 

					tableModel.setNumRows(0);

					int dtoSize = dtolist.size();


					for(int i = 0; i< dtoSize; i++) {

						String newcontent[] = new String[9];

						//"�ڵ�","å�̸�","����","���ǻ�","������","å����","�帣","Remain","Category"
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

					//���̺� �ٲ������� �ݿ�
					table.updateUI();
				}break;
				case 3 : {
					//�ؽ�Ʈ �ʵ忡�� ���� �����´� (å�̸�, ���ǻ�� �˻�)
					String searchText = searchLabel.getText();
					ArrayList<DTO> dtolist = new ArrayList<DTO>();

					try {			
						dtolist = dao.searchKind(searchText);

					} catch (RecordNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//������ �ִ� Ŭ���� �޼ҵ忡 �˻�� ������. 

					tableModel.setNumRows(0);

					int dtoSize = dtolist.size();


					for(int i = 0; i< dtoSize; i++) {

						String newcontent[] = new String[9];

						//"�ڵ�","å�̸�","����","���ǻ�","������","å����","�帣","Remain","Category"
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

					//���̺� �ٲ������� �ݿ�
					table.updateUI();
				} break;
				default : break;
				}

			}
		});

		//������ ����
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//get text�� �ڵ� ���� �����´�.
				String codetext = codeLabel.getText();
				dao.delete(codetext);

				//���� �� ���̺� �� ���� �ʱ�ȭ
				tableModel.setNumRows(0);

				ArrayList<DTO> dtolistafterdelete = new ArrayList<DTO>();

				//������ ���̽� �ȿ� �ִ� ���� ��ü �˻�
				dtolistafterdelete = dao.searchAll();

				//������ üũ
				int dtoSizeall = dtolistafterdelete.size();

				//���̺� ���� �ϳ��ϳ� ä���ֱ�
				for(int i = 0; i< dtoSizeall; i++) {

					String newcontent[] = new String[9];

					//"�ڵ�","å�̸�","����","���ǻ�","������","å����","�帣","Remain","Category"
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

				//���̺� �ٲ������� �ݿ�
				table.updateUI();

			}
		});	


		//������ư
		fixButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//get text�� ��� ���� �����´�
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

				//������ ������ DTO����

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

				//���� �� ���̺� �� ���� �ʱ�ȭ
				tableModel.setNumRows(0);

				ArrayList<DTO> dtolistafterfix = new ArrayList<DTO>();

				//������ ���̽� �ȿ� �ִ� ���� ��ü �˻�
				dtolistafterfix = dao.searchAll();

				//������ üũ
				int dtoSizeall = dtolistafterfix.size();

				//���̺� ���� �ϳ��ϳ� ä���ֱ�
				for(int i = 0; i< dtoSizeall; i++) {

					String newcontent[] = new String[9];

					//"�ڵ�","å�̸�","����","���ǻ�","������","å����","�帣","Remain","Category"
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

				//���̺� �ٲ������� �ݿ�
				table.updateUI();
			}
		});

		//�ʱ�ȭ��ư
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//�ڵ�
				codeLabel.setText("");
				//å�̸�
				booknameLabel.setText("");
				//����
				authorLabel.setText("");
				//���ǻ�
				chulpanLabel.setText("");
				//������
				dateLabel.setText("");
				//å����
				conditionLabel.setText("");
				//�帣
				genreLabel.setText("");
				//������
				RemainLabel.setText("");
				//ī�װ�
				categoryLabel.setText("");

			}
		});	

		//DB���� ������ ���� ��������

		loadDBButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//���̺� �� ���� �ʱ�ȭ
				tableModel.setNumRows(0);

				ArrayList<DTO> dtolistall = new ArrayList<DTO>();

				//������ ���̽� �ȿ� �ִ� ���� ��ü �˻�
				dtolistall = dao.searchAll();

				//������ üũ
				int dtoSizeall = dtolistall.size();

				//���̺� ���� �ϳ��ϳ� ä���ֱ�
				for(int i = 0; i< dtoSizeall; i++) {

					String newcontent[] = new String[9];

					//"�ڵ�","å�̸�","����","���ǻ�","������","å����","�帣","Remain","Category"
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

		// �ߺ�üũ ��ư
		overlapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String code1 = codeLabel.getText();
				int tmp = 0;
				for(int i = 0; i < dao.select().size(); i++) {
					String overlapCode = dao.select().get(i).getCode();
					if(code1.equals(overlapCode)) { // AAA124 == AAA123
						JOptionPane.showMessageDialog(null, "�ߺ��� �ڵ� �Դϴ�.");
						codeLabel.setText(" ");
						tmp++;
						break;
					} else if(tmp == 0) {
						JOptionPane.showMessageDialog(null, "��밡���� �ڵ� �Դϴ�.");
						break;
					}

				}
			}
		});
		
		// ���� ��� Ȯ��
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

		//���� ��ư
		endButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// frame.setVisible(true);

		//�ڷΰ��� ��ư
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