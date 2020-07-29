package ezenproject;

import java.util.*;
import java.util.Date;

import errorAndfalse.RecordNotFoundException;

import java.sql.*;

public class DAO {

	String driver = "oracle.jdbc.driver.OracleDriver";

	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	String userid = "System";

	String passwd = "826400";

	public DAO( ) {

		try {

			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	// å�̸� �˻� (������,���� ��λ��)

	public ArrayList<DTO> select() {

		ArrayList<DTO> list = new ArrayList<DTO>();

		Connection con = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			String query = "SELECT * FROM abookmanage";

			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));

				dto.setBookname(rs.getString("bookname"));

				dto.setAuthor(rs.getString("author"));

				dto.setEditorial(rs.getString("editorial"));

				dto.setPublish_day(rs.getDate("publish_day"));

				dto.setCondition(rs.getString("condition"));

				dto.setKind(rs.getString("kind"));
				// ������ ���� ����� ���� ��Ʈ
				dto.setRemain(rs.getInt("remain"));
				// ������ ���� ī�װ��� ���� ���� ���ڷ� �޴� ���� ���ϴ�
				dto.setCategory(rs.getInt("category"));

				list.add(dto);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return list;

	} // end å�̸� �˻�

	// å���� ����(������)

	public int insert(DTO dto) {

		Connection con = null;

		PreparedStatement pstmt = null;

		int n = 0;



		try {
			con = DriverManager.getConnection(url, userid, passwd);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


		String sql = "INSERT INTO abookmanage(code,bookname,author,editorial,publish_day,condition,kind,remain,category)"
				+
				" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			pstmt = con.prepareStatement(sql);

			System.out.println(dto.getCode());

			pstmt.setString(1, dto.getCode());

			pstmt.setString(2, dto.getBookname());

			pstmt.setString(3, dto.getAuthor());

			pstmt.setString(4, dto.getEditorial());

			pstmt.setDate(5, convertToSQL(dto.getPublish_day()));

			pstmt.setString(6, dto.getCondition());

			pstmt.setString(7, dto.getKind());
			// ������ ���� ����� ���� ��Ʈ
			pstmt.setInt(8, dto.getRemain());
			// ������ ���� ī�װ��� ���� ���� ���ڷ� �޴� ���� ���ϴ�
			pstmt.setInt(9, dto.getCategory());

			n = pstmt.executeUpdate();

			con.commit();

		} catch (Exception e) {

			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();

		} finally {

			try {

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return n;

	} // end ȸ������ ����

	// å���� ����
	public int delete(String bookcode) {

		Connection con = null;

		PreparedStatement pstmt = null;

		int n = 0;

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			String sql = "DELETE FROM abookmanage WHERE code = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bookcode);

			n = pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return n;

	} // end å����

	// å���� ����

	public int update(DTO dto) {

		Connection con = null;
		PreparedStatement pstmt = null;

		int n = 0;
		try {

			con = DriverManager.getConnection(url, userid, passwd);

			String sql = "UPDATE abookmanage SET bookname=?,  author=?, editorial=?, publish_day=?, condition=?, " +

               " kind=?, remain=?, category=? WHERE code = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getBookname());
			pstmt.setString(2, dto.getAuthor());

			pstmt.setString(3, dto.getEditorial());

			pstmt.setDate(4, convertToSQL(dto.getPublish_day()));

			pstmt.setString(5, dto.getCondition());

			pstmt.setString(6, dto.getKind());
			// ������ ���� ����� ���� ��Ʈ
			pstmt.setInt(7, dto.getRemain());
			// ������ ���� ī�װ��� ���� ���� ���ڷ� �޴� ���� ���ϴ�
			pstmt.setInt(8, dto.getCategory());

			pstmt.setString(9, dto.getCode());

			n = pstmt.executeUpdate();

			con.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return n;

	} // end å���� ����

	// å���� ��ȸ ���

	public boolean isExist(String bookname) {

		boolean result = false;

		Connection con = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			String sql = "SELECT * FROM abookmange WHERE bookname = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bookname);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				result = true;

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return result;

	}

	//��ü å ��ȸ

	public ArrayList<DTO> searchAll(){

		Connection con = null;

		PreparedStatement pstmt = null;

		//�˻��� ���� �ִ� ����
		ResultSet rs = null;

		//DTO���� ������ �ִ� ��� ����Ʈ�� ����
		ArrayList<DTO> dtolist = new ArrayList<DTO>();

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			// String sql = "SELECT * FROM abookmanage WHERE bookname = ?";
			//���ϵ� ī�� �˻����� �˻�� ���Ե� ���� �˻�
			String sql = "SELECT * FROM abookmanage";

			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, " %"+bookname+"%");
			rs = pstmt.executeQuery();

			//rs�� ����� 1�پ� ����ִ� ��ü
			//while�� ���پ� ����
			while (rs.next()) {

				//�˻��� ����� ��� ���� 1���� ������ �ִ� DTO�� ����
				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));
				dto.setBookname(rs.getString("bookname"));
				dto.setAuthor(rs.getString("author"));
				dto.setEditorial(rs.getString("editorial"));	
				//System.out.println(rs.getDate("publish_day").getTime().toString());
				dto.setPublish_day(new Date(rs.getDate("publish_day").getTime()));
				dto.setCondition(rs.getString("condition"));
				dto.setKind(rs.getString("kind"));
				// ������ ���� ����� ���� ��Ʈ
				dto.setRemain(rs.getInt("remain"));
				// ������ ���� ī�װ��� ���� ���� ���ڷ� �޴� ���� ���ϴ�
				dto.setCategory(rs.getInt("category"));

				//DTO���� ���� �� �ִ� ��� ����Ʈ�� �߰�
				dtolist.add(dto);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}


		return dtolist;

	}

	// å���� ��ȸ

	public ArrayList<DTO> search(String bookname) throws RecordNotFoundException {


		//if (!isExist(bookname)) {
		//	throw new RecordNotFoundException("\"" + bookname + "\"�� �����ϴ�.");
		//}

		Connection con = null;

		PreparedStatement pstmt = null;

		//�˻��� ���� �ִ� ����
		ResultSet rs = null;

		//DTO���� ������ �ִ� ��� ����Ʈ�� ����
		ArrayList<DTO> dtolist = new ArrayList<DTO>();

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			// String sql = "SELECT * FROM abookmanage WHERE bookname = ?";
			//���ϵ� ī�� �˻����� �˻�� ���Ե� ���� �˻�
			String sql = "SELECT * FROM abookmanage WHERE bookname LIKE '%"+bookname+"%'";

			System.out.println(sql);
			System.out.println(bookname);

			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, " %"+bookname+"%");
			rs = pstmt.executeQuery();

			//rs�� ����� 1�پ� ����ִ� ��ü
			//while�� ���پ� ����
			while (rs.next()) {

				System.out.println("111");

				//�˻��� ����� ��� ���� 1���� ������ �ִ� DTO�� ����
				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));
				dto.setBookname(rs.getString("bookname"));
				dto.setAuthor(rs.getString("author"));
				dto.setEditorial(rs.getString("editorial"));	
				//System.out.println(rs.getDate("publish_day").getTime().toString());
				dto.setPublish_day(new Date(rs.getDate("publish_day").getTime()));
				dto.setCondition(rs.getString("condition"));
				dto.setKind(rs.getString("kind"));
				// ������ ���� ����� ���� ��Ʈ
				dto.setRemain(rs.getInt("remain"));
				// ������ ���� ī�װ��� ���� ���� ���ڷ� �޴� ���� ���ϴ�
				dto.setCategory(rs.getInt("category"));

				//DTO���� ���� �� �ִ� ��� ����Ʈ�� �߰�
				dtolist.add(dto);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return dtolist;

	} // end å���� ���� ��ȸ

	// ���ڸ����� �˻�
	public ArrayList<DTO> searchAuthor(String author) throws RecordNotFoundException {


		//if (!isExist(bookname)) {
		//	throw new RecordNotFoundException("\"" + bookname + "\"�� �����ϴ�.");
		//}

		Connection con = null;

		PreparedStatement pstmt = null;

		//�˻��� ���� �ִ� ����
		ResultSet rs = null;

		//DTO���� ������ �ִ� ��� ����Ʈ�� ����
		ArrayList<DTO> dtolist = new ArrayList<DTO>();

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			// String sql = "SELECT * FROM abookmanage WHERE bookname = ?";
			//���ϵ� ī�� �˻����� �˻�� ���Ե� ���� �˻�
			String sql = "SELECT * FROM abookmanage WHERE author LIKE '%"+author+"%'";

			System.out.println(sql);
			System.out.println(author);

			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, " %"+bookname+"%");
			rs = pstmt.executeQuery();

			//rs�� ����� 1�پ� ����ִ� ��ü
			//while�� ���پ� ����
			while (rs.next()) {

				System.out.println("111");

				//�˻��� ����� ��� ���� 1���� ������ �ִ� DTO�� ����
				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));
				dto.setBookname(rs.getString("bookname"));
				dto.setAuthor(rs.getString("author"));
				dto.setEditorial(rs.getString("editorial"));	
				//System.out.println(rs.getDate("publish_day").getTime().toString());
				dto.setPublish_day(new Date(rs.getDate("publish_day").getTime()));
				dto.setCondition(rs.getString("condition"));
				dto.setKind(rs.getString("kind"));
				// ������ ���� ����� ���� ��Ʈ
				dto.setRemain(rs.getInt("remain"));
				// ������ ���� ī�װ��� ���� ���� ���ڷ� �޴� ���� ���ϴ�
				dto.setCategory(rs.getInt("category"));

				//DTO���� ���� �� �ִ� ��� ����Ʈ�� �߰�
				dtolist.add(dto);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return dtolist;

	}

	// ���ǻ������ �˻�
	public ArrayList<DTO> searchEditorial(String editorial) throws RecordNotFoundException {


		//if (!isExist(bookname)) {
		//	throw new RecordNotFoundException("\"" + bookname + "\"�� �����ϴ�.");
		//}

		Connection con = null;

		PreparedStatement pstmt = null;

		//�˻��� ���� �ִ� ����
		ResultSet rs = null;

		//DTO���� ������ �ִ� ��� ����Ʈ�� ����
		ArrayList<DTO> dtolist = new ArrayList<DTO>();

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			// String sql = "SELECT * FROM abookmanage WHERE bookname = ?";
			//���ϵ� ī�� �˻����� �˻�� ���Ե� ���� �˻�
			String sql = "SELECT * FROM abookmanage WHERE editorial LIKE '%"+editorial+"%'";

			System.out.println(sql);
			System.out.println(editorial);

			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, " %"+bookname+"%");
			rs = pstmt.executeQuery();

			//rs�� ����� 1�پ� ����ִ� ��ü
			//while�� ���پ� ����
			while (rs.next()) {

				System.out.println("111");

				//�˻��� ����� ��� ���� 1���� ������ �ִ� DTO�� ����
				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));
				dto.setBookname(rs.getString("bookname"));
				dto.setAuthor(rs.getString("author"));
				dto.setEditorial(rs.getString("editorial"));	
				//System.out.println(rs.getDate("publish_day").getTime().toString());
				dto.setPublish_day(new Date(rs.getDate("publish_day").getTime()));
				dto.setCondition(rs.getString("condition"));
				dto.setKind(rs.getString("kind"));
				// ������ ���� ����� ���� ��Ʈ
				dto.setRemain(rs.getInt("remain"));
				// ������ ���� ī�װ��� ���� ���� ���ڷ� �޴� ���� ���ϴ�
				dto.setCategory(rs.getInt("category"));

				//DTO���� ���� �� �ִ� ��� ����Ʈ�� �߰�
				dtolist.add(dto);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return dtolist;

	}

	// �帣���� �˻�
	public ArrayList<DTO> searchKind(String kind) throws RecordNotFoundException {


		//if (!isExist(bookname)) {
		//	throw new RecordNotFoundException("\"" + bookname + "\"�� �����ϴ�.");
		//}

		Connection con = null;

		PreparedStatement pstmt = null;

		//�˻��� ���� �ִ� ����
		ResultSet rs = null;

		//DTO���� ������ �ִ� ��� ����Ʈ�� ����
		ArrayList<DTO> dtolist = new ArrayList<DTO>();

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			// String sql = "SELECT * FROM abookmanage WHERE bookname = ?";
			//���ϵ� ī�� �˻����� �˻�� ���Ե� ���� �˻�
			String sql = "SELECT * FROM abookmanage WHERE kind LIKE '%"+kind+"%'";

			System.out.println(sql);
			System.out.println(kind);

			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, " %"+bookname+"%");
			rs = pstmt.executeQuery();

			//rs�� ����� 1�پ� ����ִ� ��ü
			//while�� ���پ� ����
			while (rs.next()) {

				System.out.println("111");

				//�˻��� ����� ��� ���� 1���� ������ �ִ� DTO�� ����
				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));
				dto.setBookname(rs.getString("bookname"));
				dto.setAuthor(rs.getString("author"));
				dto.setEditorial(rs.getString("editorial"));	
				//System.out.println(rs.getDate("publish_day").getTime().toString());
				dto.setPublish_day(new Date(rs.getDate("publish_day").getTime()));
				dto.setCondition(rs.getString("condition"));
				dto.setKind(rs.getString("kind"));
				// ������ ���� ����� ���� ��Ʈ
				dto.setRemain(rs.getInt("remain"));
				// ������ ���� ī�װ��� ���� ���� ���ڷ� �޴� ���� ���ϴ�
				dto.setCategory(rs.getInt("category"));

				//DTO���� ���� �� �ִ� ��� ����Ʈ�� �߰�
				dtolist.add(dto);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return dtolist;

	}


	public java.sql.Date convertToSQL(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}

	public java.util.Date convertToUtil(java.sql.Date uDate) {
		java.util.Date sDate = new java.util.Date(uDate.getTime());
		return sDate;
	}

}