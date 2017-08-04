package com.dao;

import java.sql.*;
import java.util.List;

import com.db.DBConnection;
import com.model.UserBean;

public class UserDao {

	public void addUser(int id,String name)
	{
		try{
			
			Connection c=DBConnection.getConnection();
			Statement st=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery("select * from user_tab");
			
			rs.moveToInsertRow();
			rs.updateInt(1,id);
			rs.updateString(2,name);
			rs.insertRow();
			
			System.out.println("New Row Inserted...!!");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void removeUserById(int id)
	{
		try{
			Connection c=DBConnection.getConnection();
			Statement st=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery("select * from user_tab");
			
			int count=0;
			while(rs.next())
			{
				if(id==rs.getInt(1))
				{
					rs.deleteRow();
					count=1;
					System.out.println("Row deleted...!!");
					return;
				}
				
			}
			
			if(count==0)
				System.out.println("No Record Found...!!");
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateUserById(int id,String name)
	{
		try{
			Connection c=DBConnection.getConnection();
			Statement st=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery("select * from user_tab");
			
			int count=0;
			while(rs.next())
			{
				if(id==rs.getInt(1))
				{
					rs.updateString(2,name);
					rs.updateRow();		
					count=1;
					System.out.println("Row Updated...!!");
					return;
				}				
			}
			
			if(count==0)
				System.out.println("Record not Found...!!");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addRecordsByBatch(List<UserBean> list)
	{
		try{
			Connection c=DBConnection.getConnection();
			Statement st=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			c.setAutoCommit(false);
			for(UserBean ub:list)
			{
				st.addBatch("insert into user_tab(id,name)values("+ub.getId()+",'"+ub.getName()+"')");
			}
			
			int[] arr=st.executeBatch();
			if(arr.length==list.size())
			{
				c.commit();
				System.out.println("Records added Successfully..!!");
			}
			else
			{
				c.rollback();
				System.out.println("Fail to add Records..!!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
