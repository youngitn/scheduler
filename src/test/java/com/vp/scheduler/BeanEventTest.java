package com.vp.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import com.vp.scheduler.studybeanevent.DemoPublisher;
import com.vp.scheduler.studybeanevent.Gevent;
import com.vp.scheduler.studybeanevent.Gobj;

@SpringBootTest
class BeanEventTest {
	@Autowired
	 private ApplicationEventPublisher applicationEventPublisher;
	@Test
	void test() {
		
		DemoPublisher dp = new DemoPublisher();
		dp.publish("5");
		dp.publish("6");
		dp.publish("7");
		dp.publish("7");
		dp.publish("7");
		dp.publish("7");
		
		applicationEventPublisher.publishEvent(new Gevent(new Gobj("TTAA","4444"), true));
		
//		 AnnotationConfigApplicationContext context =
//	                new AnnotationConfigApplicationContext(EventConfig.class);
//		 
//		 DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
//		 
//		 demoPublisher.publish("5"); // 印出 接收到publisher發送的訊息：hello
//		 
//		 context.close();
	}

}
