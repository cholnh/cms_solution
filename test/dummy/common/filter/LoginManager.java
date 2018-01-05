package com.dummy.common.filter;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginManager implements HttpSessionBindingListener{
	
	private static LoginManager loginManager = null;
	@SuppressWarnings("rawtypes")
	private static Hashtable loginUsers = new Hashtable();
	
	public LoginManager()
	{
		super();
	}
	
	public static synchronized LoginManager getInstance() {
		if(loginManager == null)
			loginManager = new LoginManager();
		return loginManager;
	}
	
	public boolean isValid(String userID, String userPW)
	{
		return true;
	}
	
	//해당 세션에 이미 로그인 되어있는지 체크
	public boolean isLogin(String sessionID)
	{
		boolean isLogin = false;
		@SuppressWarnings("rawtypes")
		Enumeration e = loginUsers.keys();
		HttpSession key = null;
		while(e.hasMoreElements())
		{
			key = (HttpSession)e.nextElement();
			if(sessionID.equals(key.getId()))
				isLogin = true;
		}
		return isLogin;
	}
	
	public void removeIsUsing(String userID)
	{
		@SuppressWarnings("rawtypes")
		Enumeration e = loginUsers.keys();
		HttpSession key = null;
		while(e.hasMoreElements())
		{
			key = (HttpSession)e.nextElement();
			if(userID.equals(loginUsers.get(key)))
			{
				key.invalidate();
			}
		}
	}
	
	public boolean isUsing(String userID)
	{
		boolean isUsing = false;
		@SuppressWarnings("rawtypes")
		Enumeration e = loginUsers.keys();
		HttpSession key = null;
		while(e.hasMoreElements())
		{
			key = (HttpSession)e.nextElement();
			if(userID.equals(loginUsers.get(key)))
				isUsing = true;
		}
		return isUsing;
	}

	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void setSession(HttpSession session, String userID)
	{
		loginUsers.put(session, userID);
		session.setAttribute("login", this.getInstance());
	}

	@Override
	public void valueBound(HttpSessionBindingEvent e) {
		//System.out.println(e.getSession().getId() + " : is bounded");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent e) {
		//System.out.println(e.getSession().getId() + " : is Unbounded");
		try {
			loginUsers.remove(e.getSession());
		}
		catch(Exception ex)
		{
			
		}
	}
}
