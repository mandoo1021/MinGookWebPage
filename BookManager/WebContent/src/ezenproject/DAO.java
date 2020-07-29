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
	

	// 책이름 검색 (관리자,유저 모두사용)

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
				// 계산식을 위해 재고의 값은 인트
				dto.setRemain(rs.getInt("remain"));
				// 계산식을 위해 카테고리의 리턴 값을 숫자로 받는 것이 편하다
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

	} // end 책이름 검색

	// 책정보 저장(관리자)

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
			// 계산식을 위해 재고의 값은 인트
			pstmt.setInt(8, dto.getRemain());
			// 계산식을 위해 카테고리의 리턴 값을 숫자로 받는 것이 편하다
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

	} // end 회원정보 저장

	// 책정보 삭제
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

	} // end 책정보

	// 책정보 수정

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
			// 계산식을 위해 재고의 값은 인트
			pstmt.setInt(7, dto.getRemain());
			// 계산식을 위해 카테고리의 리턴 값을 숫자로 받는 것이 편하다
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

	} // end 책정보 수정

	// 책정보 조회 사용

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

	//전체 책 조회

	public ArrayList<DTO> searchAll(){

		Connection con = null;

		PreparedStatement pstmt = null;

		//검색된 값을 넣는 문법
		ResultSet rs = null;

		//DTO들을 담을수 있는 어레이 리스트를 생성
		ArrayList<DTO> dtolist = new ArrayList<DTO>();

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			// String sql = "SELECT * FROM abookmanage WHERE bookname = ?";
			//와일드 카드 검색으로 검색어가 포함된 값을 검색
			String sql = "SELECT * FROM abookmanage";

			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, " %"+bookname+"%");
			rs = pstmt.executeQuery();

			//rs는 결과값 1줄씩 담고있는 객체
			//while로 한줄씩 뜯어본다
			while (rs.next()) {

				//검색된 결과를 담는 변수 1줄을 넣을수 있는 DTO를 생성
				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));
				dto.setBookname(rs.getString("bookname"));
				dto.setAuthor(rs.getString("author"));
				dto.setEditorial(rs.getString("editorial"));	
				//System.out.println(rs.getDate("publish_day").getTime().toString());
				dto.setPublish_day(new Date(rs.getDate("publish_day").getTime()));
				dto.setCondition(rs.getString("condition"));
				dto.setKind(rs.getString("kind"));
				// 계산식을 위해 재고의 값은 인트
				dto.setRemain(rs.getInt("remain"));
				// 계산식을 위해 카테고리의 리턴 값을 숫자로 받는 것이 편하다
				dto.setCategory(rs.getInt("category"));

				//DTO들을 넣을 수 있는 어레이 리스트에 추가
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

	// 책정보 조회

	public ArrayList<DTO> search(String bookname) throws RecordNotFoundException {


		//if (!isExist(bookname)) {
		//	throw new RecordNotFoundException("\"" + bookname + "\"는 없습니다.");
		//}

		Connection con = null;

		PreparedStatement pstmt = null;

		//검색된 값을 넣는 문법
		ResultSet rs = null;

		//DTO들을 담을수 있는 어레이 리스트를 생성
		ArrayList<DTO> dtolist = new ArrayList<DTO>();

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			// String sql = "SELECT * FROM abookmanage WHERE bookname = ?";
			//와일드 카드 검색으로 검색어가 포함된 값을 검색
			String sql = "SELECT * FROM abookmanage WHERE bookname LIKE '%"+bookname+"%'";

			System.out.println(sql);
			System.out.println(bookname);

			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, " %"+bookname+"%");
			rs = pstmt.executeQuery();

			//rs는 결과값 1줄씩 담고있는 객체
			//while로 한줄씩 뜯어본다
			while (rs.next()) {

				System.out.println("111");

				//검색된 결과를 담는 변수 1줄을 넣을수 있는 DTO를 생성
				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));
				dto.setBookname(rs.getString("bookname"));
				dto.setAuthor(rs.getString("author"));
				dto.setEditorial(rs.getString("editorial"));	
				//System.out.println(rs.getDate("publish_day").getTime().toString());
				dto.setPublish_day(new Date(rs.getDate("publish_day").getTime()));
				dto.setCondition(rs.getString("condition"));
				dto.setKind(rs.getString("kind"));
				// 계산식을 위해 재고의 값은 인트
				dto.setRemain(rs.getInt("remain"));
				// 계산식을 위해 카테고리의 리턴 값을 숫자로 받는 것이 편하다
				dto.setCategory(rs.getInt("category"));

				//DTO들을 넣을 수 있는 어레이 리스트에 추가
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

	} // end 책정보 정보 조회

	// 저자명으로 검색
	public ArrayList<DTO> searchAuthor(String author) throws RecordNotFoundException {


		//if (!isExist(bookname)) {
		//	throw new RecordNotFoundException("\"" + bookname + "\"는 없습니다.");
		//}

		Connection con = null;

		PreparedStatement pstmt = null;

		//검색된 값을 넣는 문법
		ResultSet rs = null;

		//DTO들을 담을수 있는 어레이 리스트를 생성
		ArrayList<DTO> dtolist = new ArrayList<DTO>();

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			// String sql = "SELECT * FROM abookmanage WHERE bookname = ?";
			//와일드 카드 검색으로 검색어가 포함된 값을 검색
			String sql = "SELECT * FROM abookmanage WHERE author LIKE '%"+author+"%'";

			System.out.println(sql);
			System.out.println(author);

			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, " %"+bookname+"%");
			rs = pstmt.executeQuery();

			//rs는 결과값 1줄씩 담고있는 객체
			//while로 한줄씩 뜯어본다
			while (rs.next()) {

				System.out.println("111");

				//검색된 결과를 담는 변수 1줄을 넣을수 있는 DTO를 생성
				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));
				dto.setBookname(rs.getString("bookname"));
				dto.setAuthor(rs.getString("author"));
				dto.setEditorial(rs.getString("editorial"));	
				//System.out.println(rs.getDate("publish_day").getTime().toString());
				dto.setPublish_day(new Date(rs.getDate("publish_day").getTime()));
				dto.setCondition(rs.getString("condition"));
				dto.setKind(rs.getString("kind"));
				// 계산식을 위해 재고의 값은 인트
				dto.setRemain(rs.getInt("remain"));
				// 계산식을 위해 카테고리의 리턴 값을 숫자로 받는 것이 편하다
				dto.setCategory(rs.getInt("category"));

				//DTO들을 넣을 수 있는 어레이 리스트에 추가
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

	// 출판사명으로 검색
	public ArrayList<DTO> searchEditorial(String editorial) throws RecordNotFoundException {


		//if (!isExist(bookname)) {
		//	throw new RecordNotFoundException("\"" + bookname + "\"는 없습니다.");
		//}

		Connection con = null;

		PreparedStatement pstmt = null;

		//검색된 값을 넣는 문법
		ResultSet rs = null;

		//DTO들을 담을수 있는 어레이 리스트를 생성
		ArrayList<DTO> dtolist = new ArrayList<DTO>();

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			// String sql = "SELECT * FROM abookmanage WHERE bookname = ?";
			//와일드 카드 검색으로 검색어가 포함된 값을 검색
			String sql = "SELECT * FROM abookmanage WHERE editorial LIKE '%"+editorial+"%'";

			System.out.println(sql);
			System.out.println(editorial);

			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, " %"+bookname+"%");
			rs = pstmt.executeQuery();

			//rs는 결과값 1줄씩 담고있는 객체
			//while로 한줄씩 뜯어본다
			while (rs.next()) {

				System.out.println("111");

				//검색된 결과를 담는 변수 1줄을 넣을수 있는 DTO를 생성
				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));
				dto.setBookname(rs.getString("bookname"));
				dto.setAuthor(rs.getString("author"));
				dto.setEditorial(rs.getString("editorial"));	
				//System.out.println(rs.getDate("publish_day").getTime().toString());
				dto.setPublish_day(new Date(rs.getDate("publish_day").getTime()));
				dto.setCondition(rs.getString("condition"));
				dto.setKind(rs.getString("kind"));
				// 계산식을 위해 재고의 값은 인트
				dto.setRemain(rs.getInt("remain"));
				// 계산식을 위해 카테고리의 리턴 값을 숫자로 받는 것이 편하다
				dto.setCategory(rs.getInt("category"));

				//DTO들을 넣을 수 있는 어레이 리스트에 추가
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

	// 장르별로 검색
	public ArrayList<DTO> searchKind(String kind) throws RecordNotFoundException {


		//if (!isExist(bookname)) {
		//	throw new RecordNotFoundException("\"" + bookname + "\"는 없습니다.");
		//}

		Connection con = null;

		PreparedStatement pstmt = null;

		//검색된 값을 넣는 문법
		ResultSet rs = null;

		//DTO들을 담을수 있는 어레이 리스트를 생성
		ArrayList<DTO> dtolist = new ArrayList<DTO>();

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			// String sql = "SELECT * FROM abookmanage WHERE bookname = ?";
			//와일드 카드 검색으로 검색어가 포함된 값을 검색
			String sql = "SELECT * FROM abookmanage WHERE kind LIKE '%"+kind+"%'";

			System.out.println(sql);
			System.out.println(kind);

			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, " %"+bookname+"%");
			rs = pstmt.executeQuery();

			//rs는 결과값 1줄씩 담고있는 객체
			//while로 한줄씩 뜯어본다
			while (rs.next()) {

				System.out.println("111");

				//검색된 결과를 담는 변수 1줄을 넣을수 있는 DTO를 생성
				DTO dto = new DTO();

				dto.setCode(rs.getString("code"));
				dto.setBookname(rs.getString("bookname"));
				dto.setAuthor(rs.getString("author"));
				dto.setEditorial(rs.getString("editorial"));	
				//System.out.println(rs.getDate("publish_day").getTime().toString());
				dto.setPublish_day(new Date(rs.getDate("publish_day").getTime()));
				dto.setCondition(rs.getString("condition"));
				dto.setKind(rs.getString("kind"));
				// 계산식을 위해 재고의 값은 인트
				dto.setRemain(rs.getInt("remain"));
				// 계산식을 위해 카테고리의 리턴 값을 숫자로 받는 것이 편하다
				dto.setCategory(rs.getInt("category"));

				//DTO들을 넣을 수 있는 어레이 리스트에 추가
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