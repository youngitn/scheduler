package com.vp.scheduler.studybeanevent;

public class Gevent extends GenericSpringEvent<Gobj>{

	
	private static final long serialVersionUID = 1L;
	public boolean success;
	Gobj what;
	public Gevent(Gobj what, boolean success) {
		super(what, success);
		this.what = what;
		this.success = success;
	}
	
	@Override
	public String getWhat() {
		// TODO Auto-generated method stub
		return "what Gevent....."+this.what.getName();
	}

}
