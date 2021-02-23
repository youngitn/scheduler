package com.vp.scheduler.entity.tiptop.twvp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;

@Table(name="GEM_File",schema = "TWVP")
@Entity
@Data
public class GemFile implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private String gem01;
	
	@Column
	private String gem02;
	
	
}
