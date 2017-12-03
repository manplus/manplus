package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	public String findUsername(String username){
		String psw=null;
		Connection con= null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try{
			String driver="com.oracle.jdbc.Driver";
			String url="jdbc:oracle://localhost:1521/dbname";
			String user="sys";
			String password="123456";
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			String sql ="select * from username where name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,username);
			rs = pstmt.executeQuery();
			if(rs==null){
				return null;
			}
			if(rs.next()){
				psw=rs.getString("password");
			}else{
				psw=null;
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
				if (con!=null)con.close();
			
		}catch(SQLException e){}
	}
		return psw;
}
}