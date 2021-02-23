package com.vp.scheduler.studybeanevent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.vp.scheduler.studybeanevent")
public class EventConfig {

	
	public static void main(String[] args) {
		 AnnotationConfigApplicationContext context =
	                new AnnotationConfigApplicationContext(EventConfig.class);
		 
		 DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
		 
		 demoPublisher.publish("hello"); // 印出 接收到publisher發送的訊息：hello
		 
		 context.close();
	}
}
