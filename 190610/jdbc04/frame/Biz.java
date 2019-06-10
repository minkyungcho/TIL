
	package frame;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.util.ArrayList;


	public abstract class Biz<K,V> {
		
		String id;
		String pwd;
		String url;
		
		public Biz() {
			// 1. JDBC Driver Loading..
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");  //자동으로 오라클에 접속하는 프로그램으로 바뀐다
			} catch (ClassNotFoundException e) {
				System.out.println("Driver Loading Error");
			}
			id = "db";
			pwd = "db";
			url = "jdbc:oracle:thin:@70.12.50.234:1521:xe";
			
		}
		
		public Connection getCon() {   // connection
			Connection con = null;
			try {
				con =
				DriverManager.getConnection(url,id,pwd);
				con.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		
		public void close(Connection con) {     //connection close
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public abstract void insert(V v) throws Exception;

		public abstract void delete(K k) throws Exception;

		public abstract void update(V v) throws Exception;

		public abstract V select(K k) throws Exception;

		public abstract ArrayList<V> selectAll() throws Exception;
		
	 
	}

