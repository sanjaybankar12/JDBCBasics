package com;

import java.util.ArrayList;
import java.util.List;

import com.dao.UserDao;
import com.model.UserBean;

public class JDBCMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserDao ud=new UserDao();
		//ud.addUser(2,"Ashu");
		//ud.removeUserById(2);
		//ud.updateUserById(3,"Adi");
		
		UserBean u1=new UserBean();
		u1.setId(10);
		u1.setName("Ravi");
		
		UserBean u2=new UserBean();
		u2.setId(11);
		u2.setName("Hrishi");
		
		UserBean u3=new UserBean();
		u3.setId(12);
		u3.setName("Chandu");
		
		List<UserBean> list=new ArrayList<>();
		list.add(u1);
		list.add(u2);
		list.add(u3);
		
		ud.addRecordsByBatch(list);
	}

}
