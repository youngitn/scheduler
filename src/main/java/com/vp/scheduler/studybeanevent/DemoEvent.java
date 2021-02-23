package com.vp.scheduler.studybeanevent;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent{
    private static final long serialVersionUID = 1L;
    public String msg;

    public DemoEvent(Object source,String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
