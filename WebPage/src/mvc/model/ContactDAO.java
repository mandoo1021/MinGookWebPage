package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import mvc.database.DBConnection;

public class ContactDAO {
	
	private static ContactDAO instance;
	
	private ContactDAO() {
		// TODO Auto-generated constructor stub
	}
	
	// private ���������ڷ� ��ȣ�ϸ鼭 ��ü�����Ҽ� �ֵ��� �޼ҵ� ����
	public static ContactDAO getInstance() {
		if(instance == null) instance = new ContactDAO();
		return instance;
	}

	// ADMINCONTACT ���̺��� ���ڵ� ����
	public int getListCount(String items, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		String sql;
		
		if (items == null && text == null)
			sql = "select count(*) from ADMINCONTACT";
		else 
			sql = "select count(*) from ADMINCONTACT where " + items + " like '%" + text + "%'";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt(1);
		} catch (Exception ex) {
			System.out.println("getListCount() ���� : " + ex);
		} finally {
			try {
				if(rs != null)
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return x;
	}
	
	// ADMINCONTACT ���̺��� ���ڵ� ��������
	public ArrayList<ContactDTO> getContactList(int page, int limit, String items, String text){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int total_record = getListCount(items, text);
		int start = (page - 1) * limit;
		int index = start + 1;
		
		String sql;
		
		if (items == null && text == null)
			sql = "select * from ADMINCONTACT order by num desc";
		else
			sql = "select * from ADMINCONTACT where " + items + " like '%" + text + "%' order by num desc";
		
		ArrayList<ContactDTO> list = new ArrayList<ContactDTO>();
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.absolute(index)) {
				ContactDTO contact = new ContactDTO();
				contact.setNum(rs.getInt("num"));
				contact.setUser_id(rs.getString("user_id"));
				contact.setUser_name(rs.getString("user_name"));
				contact.setUser_phone(rs.getString("user_phone"));
				contact.setSubject(rs.getString("subject"));
				contact.setContent(rs.getString("content"));
				contact.setRegist_day(rs.getString("regist_day"));
				contact.setIp(rs.getString("ip"));
				list.add(contact);
				
				if (index < (start + limit) && index <= total_record) index++;
				else break;
			}
			return list;
		} catch (Exception ex) {
			System.out.println("getContactList() ���� : " + ex);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	
	// contactAdmin ���̺��� ������ id�� ����ڸ� ��������
	public String getLoginNameById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name = null;
		String sql = "select * from MEMBER where user_id = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				name = rs.getString("user_name"); 
			}
			return name;
		} catch (Exception ex) {
			System.out.println("getContactByNum() ���� : " + ex);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	
	// ADMINCONTACT ���̺� ���ο� �� �����ϱ�
	public void insertContact(ContactDTO contact) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			
			String sql = "insert into ADMINCONTACT values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, contact.getNum());
			pstmt.setString(2, contact.getUser_id());
			pstmt.setString(3, contact.getUser_name());
			pstmt.setString(4, contact.getUser_phone());
			pstmt.setString(5, contact.getSubject());
			pstmt.setString(6, contact.getContent());
			pstmt.setString(7, contact.getRegist_day());
			pstmt.setString(8, contact.getIp());
			
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("insertContact() ���� : " + ex);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	
	// ���õ� �� �� ���� ��������
	public ContactDTO getContactByNum(int num, int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContactDTO contact = null;
		
		String sql = "select * from ADMINCONTACT where num = ?";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				contact = new ContactDTO();
				contact.setNum(rs.getInt("num"));
				contact.setUser_id(rs.getString("user_id"));
				contact.setUser_name(rs.getString("user_name"));
				contact.setUser_phone(rs.getString("user_phone"));
				contact.setSubject(rs.getString("subject"));
				contact.setContent(rs.getString("content"));
				contact.setRegist_day(rs.getString("regist_day"));
				contact.setIp(rs.getString("ip"));
			}
			return contact;
		} catch (Exception ex) {
			System.out.println("getContactByNum() ���� : " + ex);
		} finally {
			try {
				if(rs != null) rs.close();							
				if(pstmt != null) pstmt.close();				
				if(conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	
	// ���õ� �� �� ���� ��������
	public ContactDTO getUpdateByNum(int num, int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContactDTO contact = null;
		
		String sql = "select * from ADMINCONTACT where num = ?";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				contact = new ContactDTO();
				contact.setNum(rs.getInt("num"));
				contact.setUser_id(rs.getString("user_id"));
				contact.setUser_name(rs.getString("user_name"));
				contact.setUser_phone(rs.getString("user_phone"));
				contact.setSubject(rs.getString("subject"));
				contact.setContent(rs.getString("content"));
				contact.setRegist_day(rs.getString("regist_day"));
				contact.setIp(rs.getString("ip"));
			}
			return contact;
		} catch (Exception ex) {
			System.out.println("getContactByNum() ���� : " + ex);
		} finally {
			try {
				if(rs != null) rs.close();							
				if(pstmt != null) pstmt.close();				
				if(conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	
	// ���õ� �� ���� �����ϱ�
	public void updateContact(ContactDTO contact) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update ADMINCONTACT set user_name=?, user_phone=?, subject=?, content=? where num=?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// ���� ���� ���� ������ �ϳ��� �۾����� ����Ǿ�� �� ��쿡 ������ ������ 
			// �۵� ���ϰ� �ؾ��ϱ� ������ setAutoCommit�� false�� �س��´�
			conn.setAutoCommit(false);
			
			pstmt.setString(1, contact.getUser_name());
			pstmt.setString(2, contact.getUser_phone());
			pstmt.setString(3, contact.getSubject());
			pstmt.setString(4, contact.getContent());
			pstmt.setInt(5, contact.getNum());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (Exception ex) {
			System.out.println("updateContact() ���� : " + ex);
		} finally {
			try {
				if(pstmt != null) pstmt.close();				
				if(conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	
	// ���õ� �� �����ϱ�
	public void deleteContact(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from ADMINCONTACT where num=?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("deleteContact() ���� : " + ex);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
}
