package db1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAll {

	public static void main(String[] args) {
		// 1. JDBC Driver Loading..
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading Error");
		}
		
		// 2. Connection 1521 db db

		String id = "db";
		String password = "db";
		String url = "jdbc:oracle:thin:@70.12.50.234:1521:xe";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, id, password);
		} catch (SQLException e) {
			System.out.println("Connection Error");
			e.printStackTrace();
		}
		
		// 3. SQL Àü¼Û & Receive
		
		String sql = "SELECT * FROM T_PRODUCT";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, "id03");
			rset = pstmt.executeQuery(); 
//			for(int i=0; i<pstmt.getFetchSize(); i++) {
//				rset.next();
//				String uid = rset.getString("ID");
//				String upwd = rset.getString("PWD");
//				String uname = rset.getString("NAME");
//				System.out.println(uid+" "+upwd+" "+uname);
//				if(!rset.next()) {
//					break;
//				}
//			}
			while(rset.next()) {
				String uid = rset.getString("ID");
				String uname = rset.getString("NAME");
				String uprice = rset.getString("PRICE");
				System.out.println(uid+" "+uname+" "+uprice);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 4. close
		
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
