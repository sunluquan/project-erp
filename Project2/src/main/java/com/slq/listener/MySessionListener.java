package com.slq.listener;

import javax.servlet.annotation.WebListener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.stereotype.Component;

@WebListener
public class MySessionListener implements SessionListener{

	@Override
	public void onStart(Session session) {
		System.out.println(session.getId());
		System.out.println("session---onStart");
		
	}

	@Override
	public void onStop(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExpiration(Session session) {
		// TODO Auto-generated method stub
		
	}

}
