package com.cloudcomputing.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudcomputing.usermanage.model.User;


public class GaeConnection {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		String driverClassName = "com.mysql.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://173.194.85.197/robocode";
		String dbUser = "himanshi";
		String dbPwd = "aggarwal";
		Class.forName(driverClassName);
		Connection conn=DriverManager.getConnection(connectionUrl,dbUser,dbPwd);
		return conn;
	}
	public void saveUser(User user) throws SQLException, ClassNotFoundException{
		Connection conn=getConnection();
		if(conn==null){
			System.out.println("Connection Not made");
		}
		else{
			System.out.println("Successfull Connection");
			Statement st=conn.createStatement();
			st.executeUpdate("Insert Into users (username,password, location) values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getLocation()+"');");
			System.out.println("success");
		}
	}
	public void updateUser(User user) throws SQLException, ClassNotFoundException{
		Connection conn=getConnection();
		if(conn==null){
			System.out.println("Connection Not made");
		}
		else{
			System.out.println("Successful Connection");
			String s="Update users SET password=?, location=? where username=?;";
			PreparedStatement ps=conn.prepareStatement(s);
			ps.setString(1,user.getPassword());
			ps.setString(2, user.getLocation());
			ps.setString(3, user.getUsername());
			ps.executeUpdate();
			ps.close();
		}
	}
	public String removeUser(String username) throws SQLException, ClassNotFoundException{
		Connection conn=getConnection();
		if(conn==null){
			System.out.println("Connection Not made");
			return null;
		}
		else{
			System.out.println("Successfull Connection");
			String s="Delete from users where username="+username+";";
			PreparedStatement ps=conn.prepareStatement(s);
			ps.executeUpdate();
			ps.close();
			return username;
		}
	}
	public Collection<User> findallUser() throws SQLException, ClassNotFoundException{
		List<User> users=new ArrayList<>();
		Connection conn=getConnection();
		if(conn==null){
			System.out.println("Connection Not made");
			return null;
		}
		else{
			System.out.println("Successfull Connection");
			String s="select * from users;";
			PreparedStatement ps=conn.prepareStatement(s);
			ResultSet rs=ps.executeQuery();
			if(rs==null){
				return null;
			}
			else
			{	
				while(rs.next()){
					User user= new User();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setLocation(rs.getString("location"));
					user.setRole(rs.getString("role"));
					System.out.println(rs.getString("username"));
					users.add(user);
				}
			}
			return users;
		}
	}
	public User findoneUser(String username) throws SQLException, ClassNotFoundException{
		Connection conn=getConnection();
		if(conn==null){
			System.out.println("Connection Not made");
			return null;
		}
		else{
			System.out.println("Successfull Connection");
			String s="select * from users where username='"+username+"';";
			PreparedStatement ps=conn.prepareStatement(s);
			ResultSet rs=ps.executeQuery();
			if(rs==null){
				return null;
			}
			else
			{	
				User user= new User();
				while(rs.next()){
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setLocation(rs.getString("location"));
					user.setRole(rs.getString("role"));
				}
				return user;
			}
		}
	}
}
