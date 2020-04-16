package com.slq.websocket;

import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
/***
 * 接收websocket注册进来的用户  
 * @author 孙陆泉
 *
 */
//@Component
//@ServerEndpoint("/imserver/{userId}")
public  class WebSocketServer {

	//与客户端的连接会话 通过它来推送数据 
	private Session session;
	//存储连接的用户信息
	private static ConcurrentHashMap<String,WebSocketServer> webSocketMap=new ConcurrentHashMap<String, WebSocketServer>();
	
	//服务连接的时候调用 什么时候可以连接成功!
	//当登录成功的时候可以连接成功!
	@OnOpen
	public void onOpen(Session session) {
		//SecurityUtils.get
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null) {
			org.apache.shiro.session.Session shiro_session=subject.getSession();
			shiro_session.getId();
		}
	}
	@OnMessage
	public void onMessage() {
		
	}
	
	@OnClose
	public void onClose() {
		
	}
	@OnError
	public void onError() {
		
	}
	
}
