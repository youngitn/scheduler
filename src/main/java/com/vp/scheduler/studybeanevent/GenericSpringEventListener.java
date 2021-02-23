package com.vp.scheduler.studybeanevent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GenericSpringEventListener {
	
	@EventListener(condition = "#event.success")
	public void handleSuccessful(GenericSpringEvent event) {
		System.out.println("Received spring generic event - " + event.getWhat());
	}
}