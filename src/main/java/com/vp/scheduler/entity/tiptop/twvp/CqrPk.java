package com.vp.scheduler.entity.tiptop.twvp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class CqrPk implements Serializable {
	
	
		// default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;

		
		private String cqr01;
		
		
		private Date cqr02;
		
		private String cqr03;

		


	
}
