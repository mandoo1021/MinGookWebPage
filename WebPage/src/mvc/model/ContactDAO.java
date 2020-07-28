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
	
	// private 접근제한자로 보호하면서 객체생성할수 있도록 메소드 생성
	public static ContactDAO getInstance() {
		if(instance == null) instance = new ContactDAO();
		return instance;
	}

	// ADMINCONTACT 테이블의 레코드 갯수
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
			System.out.println("getListCount() 에러 : " + ex);
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
	
	// ADMINCONTACT 테이블의 레코드 가져오기
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
			System.out.println("getContactList() 에러 : " + ex);
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
	
	// contactAdmin 테이블에서 인증된 id의 사용자명 가져오기
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
			System.out.println("getContactByNum() 에러 : " + ex);
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
	
	// ADMINCONTACT 테이블에 새로운 글 삽입하기
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
			System.out.println("insertContact() 에러 : " + ex);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	
	// 선택된 글 상세 내용 가져오기
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
			System.out.println("getContactByNum() 에러 : " + ex);
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
	
	// 선택된 글 상세 내용 가져오기
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
			System.out.println("getContactByNum() 에러 : " + ex);
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
	
	// 선택된 글 내용 수정하기
	public void updateContact(ContactDTO contact) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update ADMINCONTACT set user_name=?, user_phone=?, subject=?, content=? where num=?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			// 여러 개의 쿼리 문장이 하나의 작업으로 수행되어야 할 경우에 각각의 문장이 
			// 작동 못하게 해야하기 때문에 setAutoCommit을 false로 해놓는다
			conn.setAutoCommit(false);
			
			pstmt.setString(1, contact.getUser_name());
			pstmt.setString(2, contact.getUser_phone());
			pstmt.setString(3, contact.getSubject());
			pstmt.setString(4, contact.getContent());
			pstmt.setInt(5, contact.getNum());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (Exception ex) {
			System.out.println("updateContact() 에러 : " + ex);
		} finally {
			try {
				if(pstmt != null) pstmt.close();				
				if(conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	
	// 선택된 글 삭제하기
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
			System.out.println("deleteContact() 에러 : " + ex);
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
