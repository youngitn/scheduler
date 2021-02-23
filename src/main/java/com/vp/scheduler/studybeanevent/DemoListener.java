package com.vp.scheduler.studybeanevent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
// 需實現ApplicationListener，並指定事件類型
public class DemoListener {

	@EventListener(condition = "#event.msg == '5'")
	// 使用onApplicationEvent方法，對事件消息進行接受處理
	public void printEventSomething(DemoEvent event) {

		String msg = event.getMsg();

		System.out.println("接收到publisher發送的訊息555：" + msg);
	}
	
	@EventListener(condition = "#event.msg == '6'")
	// 使用onApplicationEvent方法，對事件消息進行接受處理
	public void printEventSomething6(DemoEvent event) {

		String msg = event.getMsg();

		System.out.println("接收到publisher發送的訊息666：" + msg);
	}
	
	@EventListener(condition = "#event.msg > '6'")
	// 使用onApplicationEvent方法，對事件消息進行接受處理
	public void printEventSomething999(DemoEvent event) {

		String msg = event.getMsg();

		System.out.println("else：" + msg);
	}
}