
package dao;

import java.util.ArrayList;
import java.sql.*;
import model.Clothes;

public class ClothesRepository{
	
	private ArrayList<Clothes> listOfProducts = new ArrayList<Clothes>();
	private static ClothesRepository instance = new ClothesRepository();
	
	public static ClothesRepository getInstance() {
		return instance;
	}
	
    public ClothesRepository(){
    	
    	Connection conn = null;
    	PreparedStatement pstmt = null;	
    	ResultSet rs = null;

    		
		try{
			String url="jdbc:mysql://localhost/mandoo1021";
			String user="mandoo1021";
			String password="alsrnr12";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from PRODUCT";
	    	pstmt = conn.prepareStatement(sql);
	    	rs = pstmt.executeQuery();
	    	while(rs.next()) {
	    		 Clothes dbValue = new Clothes();
	    		 dbValue.setStyleNo(rs.getString("styleno"));
	    		 dbValue.setCname(rs.getString("c_name"));
	    		 dbValue.setColor(rs.getString("color"));
	    		 dbValue.setUnitPrice(Integer.parseInt(rs.getString("unitPrice")));
	    		 dbValue.setSize(rs.getString("size"));
	    		 dbValue.setDescription(rs.getString("description"));
	    		 dbValue.setManufacturer(rs.getString("manufacturer"));
	    		 dbValue.setCategory(rs.getString("category"));
	    		 dbValue.setUnitsInStock(Long.parseLong(rs.getString("unitsInStock")));
	    		 dbValue.setFileName(rs.getString("filename"));
	    		 
	    		 listOfProducts.add(dbValue);
	    	}
		} catch(Exception ex){
			System.out.println("데이터베이스 연결이 실패했습니다.<br>");
			System.out.println("SQLException : " + ex.getMessage());
		} finally {
			try {
					if(conn != null) conn.close();
					if(pstmt != null) pstmt.close();
					if(rs != null) rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
    	
    }

    public ArrayList<Clothes> getAllProducts(){
        return listOfProducts;
    }
    public Clothes getProductById(String styleno) {
		Clothes productById = null;
		
		for(int i=0; i< listOfProducts.size(); i++) {
			Clothes product = listOfProducts.get(i);
			if(product != null && product.getStyleNo() != null && product.getStyleNo().equals(styleno)) {
				productById = product;
				break;
			}
		}
		return productById;
	}

}
