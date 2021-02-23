package com.vp.scheduler.studybeanevent;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoPublisher {
	@Autowired
	 private ApplicationContext context; // 用ApplicationContext發布事件

	
	private static DemoPublisher mythis;
	
	@PostConstruct
	void init() {
		mythis = this;
	}
	public void publish(String msg) {
		DemoEvent customSpringEvent = new DemoEvent(this, msg);
		// 透過publishEvent方法發布
		mythis.context.publishEvent(customSpringEvent);
	}

}