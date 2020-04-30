package demo;
import java.sql.*;
public class WorkerLogin 
{
	public WorkerLogin(){
	}
	public void insert(int workerId,String password,int privilege){
		Connection con=null;
		Statement stmt=null;
		String strTmp;
		String sql;
		String url="jdbc:mysql://localhost:3306/mytest?"+"user=root&password=12138&useUnicode=true&characterEncoding=UTF8";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url);
			con.setAutoCommit(false);
			stmt=con.createStatement();
			stmt.executeUpdate(strTmp="insert into log values("+workerId+",'"+password+"',"+privilege+")");
			con.commit();

			System.out.println( strTmp );
		}catch(SQLException e){
			System.out.println("MySQL操作错误");
			try{
				con.rollback();
			}catch(SQLException e1){
				e1.printStackTrace();
			}
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
				}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public int search(int workerId,String password){
		Connection con=null;
		Statement stmt=null;
		int flag=0;
		String sql;
		String url="jdbc:mysql://localhost:3306/mytest?"+"user=root&password=12138&useUnicode=true&characterEncoding=UTF8";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url);
			con.setAutoCommit(false);
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from log where workerId="+workerId+"");
			while( rs.next() ){
				Login login=new Login();
				
				login.setWorkerId( rs.getInt(1) );
				login.setPassword(  rs.getString(2) );
				login.setPrivilege(  rs.getInt(3) );
				
				System.out.println("=====================");
				int Id=login.getWorkerId();
				String word=login.getPassword();
				int privilege=login.getPrivilege();
				if(Id==workerId&&word.equals(password))
					flag=1;
				if(Id==workerId&&word.equals(password)&&privilege==2)
					flag=2;
			}
		}catch(SQLException e){
			flag=0;
			System.out.println("MySQL操作错误");
			try{
				con.rollback();
			}catch(SQLException e1){
				e1.printStackTrace();
			}
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return flag;
	}


	public void update(int workerId,String password){
		Connection con=null;
		Statement stmt=null;
		String strTmp;
		String sql;
		String url="jdbc:mysql://localhost:3306/mytest?"+"user=root&password=12138&useUnicode=true&characterEncoding=UTF8";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url);
			con.setAutoCommit(false);
			stmt=con.createStatement();
			stmt.executeUpdate(strTmp="update log set password='"+password+"' where workerId="+workerId+"");
			con.commit();
		}catch(SQLException e){
			System.out.println("MySQL��������");
			try{
				con.rollback();
			}catch(SQLException e1){
				e1.printStackTrace();
			}
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
				try{
				stmt.close();
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}//update()
}//class