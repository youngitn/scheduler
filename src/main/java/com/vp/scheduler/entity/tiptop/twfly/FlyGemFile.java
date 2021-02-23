package com.vp.scheduler.entity.tiptop.twfly;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "GEM_File", schema = "TWFLY")

@Entity
@Data
public class FlyGemFile implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private String gem01;

	@Column
	private String gem02;
}
