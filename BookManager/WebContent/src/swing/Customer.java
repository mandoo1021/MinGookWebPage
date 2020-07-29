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
				setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton searchButton = new JButton("�˻�");
		searchButton.setBounds(510, 70, 60, 23);
		contentPane.add(searchButton);

		JLabel lblsearch = new JLabel("���� �˻�");
		lblsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblsearch.setBounds(255, 20, 154, 35);
		lblsearch.setFont(new Font("dialog", Font.PLAIN | Font.BOLD, 20));
		lblsearch.setFont(lblsearch.getFont().deriveFont(30.0f));
		contentPane.add(lblsearch);

		tfsearch = new JTextField();
		tfsearch.setBounds(200, 72, 300, 21);
		contentPane.add(tfsearch);
		tfsearch.setColumns(10);

		JButton backButton = new JButton("�ڷΰ���");
		backButton.setBounds(400, 400, 97, 23);
		contentPane.add(backButton);

		JButton endButton = new JButton("����");
		endButton.setBounds(530, 400, 97, 23);
		contentPane.add(endButton);

		String[] bookKinds = {"���������� �˻�", "���ڸ����� �˻�", "���ǻ�� �˻�", "�帣���� �˻�"};

		JComboBox searchCombo = new JComboBox(bookKinds);
		searchCombo.setBounds(60, 72, 130, 21);
		contentPane.add(searchCombo);



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
				FirstScreen firstScreen = new FirstScreen();
				firstScreen.setVisible(true);
			}
		});

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
		scrollPane.setBounds(54, 130, 580, 250);
		contentPane.add(scrollPane);
		
		
		// �˻���ư (���ڸ� , ���ǻ��, ������ �з� �˻� ��� ��)
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = searchCombo.getSelectedIndex();
				switch(index) {
				case 0 : {
					//�ؽ�Ʈ �ʵ忡�� ���� �����´� (å�̸�, ���ǻ�� �˻�)
					String searchText = tfsearch.getText();
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
					String searchText = tfsearch.getText();
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
					String searchText = tfsearch.getText();
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
					String searchText = tfsearch.getText();
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
